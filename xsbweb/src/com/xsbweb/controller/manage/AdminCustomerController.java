package com.xsbweb.controller.manage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.common.bean.ResultCode;
import com.xsbweb.common.bean.XsbBusinessConstant;
import com.xsbweb.controller.app.AppLoginRegistController;
import com.xsbweb.service.ConfigService;
import com.xsbweb.service.CustomerService;
import com.xsbweb.service.XSBBaseService;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.util.ExcelUtil;
import com.xsbweb.util.MD5;
import com.xsbweb.util.RedisUtil;
import com.xsbweb.util.SmsUtil;
import com.xsbweb.vo.Customer;
import com.xsbweb.vo.extend.CustomerVO;
import com.xsbweb.vo.extend.EnumVO;


@Controller
public class AdminCustomerController {
	
	private Logger log = Logger.getLogger(AppLoginRegistController.class);
	
   @Autowired
	private ConfigService configService;  
    
   @Autowired
   private CustomerService customerService;
    
    @Autowired
    private XSBBaseService xsbBaseService;
    
    private RedisUtil redisUtil = new RedisUtil();
    
    @RequestMapping(value="/admin/customer/toAdminCustomer.do")
    public ModelAndView toAdminCustomer(CustomerVO user)throws Exception{
    	ModelAndView mav = new ModelAndView();
    	//获取总数
    	int totalCounts = customerService.getCustomerCounts(user);
    	List<Customer> customerList = customerService.getAllCustomerList(user);
    	user.setTotalRecord(totalCounts);
    	if("1".equals(user.getAuthentStatus())||"2".equals(user.getAuthentStatus())||"3".equals(user.getAuthentStatus())){
    		mav.setViewName("manage/customer/queryCertifyCustomerList");
    	}else{
    		mav.setViewName("manage/customer/queryCustomerList");
    	}
    	mav.addObject("customerList",customerList);
    	mav.addObject("customer",user);
    	return mav;
    }
    
    
    @RequestMapping(value="/admin/customer/toAddCustomer.do")
    public ModelAndView toAddCustomer()throws Exception{
    	ModelAndView mav = new ModelAndView();
    	Customer user = new Customer();
//    	List<EnumVO> newsTypeEnumList = xsbBaseService.getEnumVOListByColumnFullName(XsbBusinessConstant.NEWS_TYPE_ENUM);
//    	user.setNewsTypeEnumList(newsTypeEnumList);
    	mav.addObject("customer",user);
    	mav.setViewName("manage/customer/addCustomer");
    	return mav;
    }
    
    /**
     * 新增用户
     * @param news
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/customer/addCustomer.do")
    public String addCustomer(CustomerVO user)throws Exception{
    	//获取当前时间，精确到毫秒
    	//MD5加密
    	if(CommonUtils.isNotBlank(user.getCustomerPassword())){
    		user.setCustomerPassword(MD5.get(user.getCustomerPassword()));
    	}
    	customerService.addCustomer(user);
    	return "redirect:/admin/customer/toAdminCustomer.do";
    }
    
    /**
     * 跳转到用户编辑页面
     * @param newsNo
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/customer/toEditCustomer.do")
    public ModelAndView toEditCustomer(String userNo)throws Exception{
    	ModelAndView mav = new ModelAndView();
		List<EnumVO> gzIndustryEnumList = xsbBaseService.getEnumVOListByColumnFullName(XsbBusinessConstant.GZ_INDUSTRY);
		List<EnumVO> capitalScaleEnumList = xsbBaseService.getEnumVOListByColumnFullName(XsbBusinessConstant.CAPITAL_SCALE);
		List<EnumVO> customerTypeEnumList = xsbBaseService.getEnumVOListByColumnFullName(XsbBusinessConstant.CUSTOMER_TYPE);
        CustomerVO user = customerService.getCustomerById(userNo,gzIndustryEnumList,capitalScaleEnumList,customerTypeEnumList);
//    	List<EnumVO> newsTypeEnumList = xsbBaseService.getEnumVOListByColumnFullName(XsbBusinessConstant.NEWS_TYPE_ENUM);
//    	if(news!=null){
//    		news.setNewsTypeEnumList(newsTypeEnumList);
//    	}
        log.info("authentStatus："+user.getAuthentStatus());
        if(user!=null){
        	if("1".equals(user.getAuthentStatus())){
        		mav.setViewName("manage/customer/editCertifyCustomer");
        	}else{
        		mav.setViewName("manage/customer/editCustomer");
        	}
    	
    	mav.addObject("customer",user);
        }
    	return mav;
    }
    
    /**
     * 编辑用户
     * @param 
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/customer/editCustomer.do")
    public String editCustomer(CustomerVO user,ModelMap mode,HttpServletRequest request)throws Exception{
    	if(user==null || CommonUtils.isBlank(user.getCustomerId())){
    		mode.addAttribute("customer", user);
    		mode.addAttribute("error","用户customer_no缺失！");
    		log.info("####################用户customer_no缺失！");
    		return "manage/customer/editCustomer";
    	}
    	//编辑
    	//MD5加密
    	if(user.getCustomerPassword()!=null&&!"".equals(user.getCustomerPassword())){
    		user.setCustomerPassword(MD5.get(user.getCustomerPassword()));
    	}
    	int flag = customerService.editCustomer(user);
    	String content="【第一路演】恭喜您已通过“投资者认证”审核，您现可以使用第一路演的全部功能！";
    	//int flag = 1;
    	if(flag>0){
    		//return "redirect:/admin/customer/toAdminCustomer.do";
    		//sendCertifyInfo(user.getCustomerPhoneNo(),user.getAuthentStatus(),request);
    		if("2".equals(user.getAuthentStatus())||"3".equals(user.getAuthentStatus())){
    			if("2".equals(user.getAuthentStatus())){
    				SmsUtil.sendPhoneSms(user.getCustomerPhoneNo(), content);
    			}else{
    				content="【第一路演】抱歉，您申请的认证尚未审核通过，您可以完善资料并继续使用第一路演的所有非限制功能！如有疑问，欢迎拨打客服热线400-777-5768。";
    				SmsUtil.sendPhoneSms(user.getCustomerPhoneNo(), content);
    			}
    			return "redirect:/admin/customer/toAdminCustomer.do?authentStatus=1";
    			//return "redirect:/admin/customer/sendCertifyInfo.do?customerPhoneNo=isCertify";
    		}else{
    			return "redirect:/admin/customer/toAdminCustomer.do";
    		}
    	}else{
    		mode.addAttribute("customer", user);
    		mode.addAttribute("error","修改失败！");
    		if("2".equals(user.getAuthentStatus())||"3".equals(user.getAuthentStatus())){
    			return "manage/customer/editCertifyCustomer";
    		}else{
    			return "manage/customer/editCustomer";
    		}
    	}
    }
    
    /**
     * 删除
     * @param customer
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/customer/deleteCustomer.do",method=RequestMethod.GET)
    public String deleteCustomer(String userNo,ModelMap mode,Customer user)throws Exception{
    	int flag = customerService.delCustomer(userNo);
    	if(flag==1){
    		return "redirect:/admin/customer/toAdminCustomer.do";
    	}else{
    		mode.addAttribute("error","删除失败");
    		return "manage/customer/queryCustomerList";
    	}
    }
    /**
     * excel下载校验
     * @param user
     * @return
     * @throws Exception
     */
   @RequestMapping(value="/admin/toCheckCustomerExcel.do",method=RequestMethod.GET)
   @ResponseBody
   public Map<String,Object>toCheckCustomerExcel(CustomerVO user) throws Exception{
	   Map<String,Object> map = new HashMap<String,Object>();
	   int totalCounts = customerService.getCustomerCounts(user);
	   ResourceBundle bundle = ResourceBundle.getBundle("config");  
		String	excelCount = bundle.getString("excelCount");  //excel最大记录数
	   if(Integer.parseInt(excelCount)<totalCounts){
		   map.put(ResultCode.RESULT_CODE, ResultCode.EXCEL_COUNT_TOMAX);
		   map.put("excelCount", excelCount);
	   }else{
		   map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
	   }
	   return map;
   }
   /**
	 * excel文件下载
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/admin/toCustomerExcel.do",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> toExcel(CustomerVO user,HttpServletResponse response) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		ResourceBundle bundle = ResourceBundle.getBundle("config");  
		String	excelCount = bundle.getString("excelCount");  //excel最大记录数
		user.setPageSize(Integer.parseInt(excelCount));
		List<Customer> customerList = customerService.getAllCustomerList(user);
		List<EnumVO> gzIndustryEnumList = xsbBaseService.getEnumVOListByColumnFullName(XsbBusinessConstant.GZ_INDUSTRY);
		List<EnumVO> capitalScaleEnumList = xsbBaseService.getEnumVOListByColumnFullName(XsbBusinessConstant.CAPITAL_SCALE);
		List<EnumVO> customerTypeEnumList = xsbBaseService.getEnumVOListByColumnFullName(XsbBusinessConstant.CUSTOMER_TYPE);
		try{
			ExcelUtil.toExcel(customerList,response,XsbBusinessConstant.Customer_EXCEL,gzIndustryEnumList,capitalScaleEnumList,customerTypeEnumList);
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
		}catch(Exception e){
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		} 
		return map;
	}
}
