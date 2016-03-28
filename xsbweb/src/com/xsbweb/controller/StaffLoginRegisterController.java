package com.xsbweb.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
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
import com.xsbweb.controller.app.AppCustomerController;
import com.xsbweb.exception.ApplicationException;
import com.xsbweb.service.StaffService;
import com.xsbweb.util.BaseUtil;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.util.GetIp;
import com.xsbweb.util.MD5;
import com.xsbweb.util.RedisUtil;
import com.xsbweb.util.SmsUtil;
import com.xsbweb.vo.Staff;
import com.xsbweb.vo.extend.CustomerVO;

/**
 * 用于员工登录、注册控制
 * @author Administrator
 *
 */
@Controller
public class StaffLoginRegisterController {
private Logger log = Logger.getLogger(StaffLoginRegisterController.class);
	
	private static final String VALIDATE_PHONE_CODE = "VALIDATE_PHONE_CODE";  
    private static final String VALIDATE_PHONE = "VALIDATE_PHONE";  
    private static final String SEND_CODE_TIME = "SEND_CODE_TIME"; 
    private static final String LOGIN_TIME = "loginTime";
    private static final String CUSTOMERID = "customerId";
    private static final String RESULTCODE = "resultCode";
    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private StaffService staffService;
    
    @RequestMapping(value="/staffLogin.do",method=RequestMethod.GET)
    public String staffToLogin(){
    	return "staffLogin";
    }
    
    @RequestMapping(value="/staffToRegister.do")
    public String staffToRegister(){
    	return "staffRegister";
    }
    
    @RequestMapping(value="/staffToForgetPwd.do")
    public String staffToForgetPwd(){
    	return "staffForgetPwd";
    }
    
    /**
	 * 员工登录判断
	 * @param customerVO
	 * @return
	 */
	@RequestMapping(value="/staffLogin.do",params="method=login",method=RequestMethod.POST)
	public String staffLogin(ModelMap mode,HttpServletRequest request,HttpServletResponse response,Staff staffVO)throws Exception{
		//MD5加密
		staffVO.setStaffPassword(MD5.get(staffVO.getStaffPassword()));
		//判断用户是否存在
		Staff staff = staffService.checkStaff(staffVO);
		if(staff==null){
			//提示用户名
			mode.put("error", "用户名不存在");
			return "staffLogin";
		}
		staff = staffService.validateStaffLogin(staffVO);
		if(staff!=null && CommonUtils.isNotBlank(staff.getStaffId())){
			log.info("########staff:"+staff+"################");
			log.info("########staffId:"+staff.getStaffId()+"################");	
			//根据customerId查询用户信息、权限
			//CustomerVO customer = loginRegisterService.getCustomerById(cuVO.getCustomerId());			
			//获取权限信息----------------------
		    /*List<String> roleEnumList = loginRegisterService.getRoleEnumByCustomerId(cuVO.getCustomerId());
		    //权限机制：用户登录的时候查询出该用户的权限存入redis中，修改用户权限时进行替换
		    redisUtil.addListToJedis("role:"+cuVO.getCustomerId(), roleEnumList, -1, null);*/
			//------------------
			
			//验证通过，在session中存入用户信息
			/*HttpSession session = request.getSession();
			session.setAttribute(CUSTOMERID, staff.getStaffId());
			session.setAttribute(LOGIN_TIME, new Date().getTime());
			log.info("##########staffId:"+staff.getStaffId()+"############loginTime:"+new Date().getTime()+"####################");
			mode.put("staff", staff);
			if(1==1){
				
				//mode.addAttribute("customerName",staff.getStaffName());
				session.setAttribute("customerName", staff.getStaffName());
				//request.setAttribute("customerName",staff.getStaffName());
				return "redirect:/manage/toAdminIndex.do?customerId="+staff.getStaffId();
																																			
			}else{
				mode.put("error", "用户名密码错误");
				return "staffLogin";
			}*/
			//验证通过，存入用户信息进入cookie
			BaseUtil.addCookie(CUSTOMERID, staff.getStaffId(), response);
			BaseUtil.addCookie("customerName", URLEncoder.encode(staff.getStaffName(),"UTF-8"), response);
			//-----判断用户权限，根据权限跳转到后台管理页面或者前台页面-------
			return "redirect:/manage/toAdminIndex.do?customerId="+staff.getStaffId();
		}else{
			//提示用户名或密码错误
			mode.put("error", "密码错误");
			return "staffLogin";
		}
	}
	
	/**
	 * 用户登出判断
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/staffLogout.do")
	public String logout(HttpSession session){
		//清除session
		session.invalidate();
		return "staffLogin";
	}
	/**
	 * 发送手机验证码
	 * @return
	 */
	@RequestMapping(value="staffRegister/sendCode.do",method=RequestMethod.GET)
	public @ResponseBody String sendPhoneCode(
					@RequestParam("staffPhoneNo") String staffPhoneNo,
					HttpServletRequest request)throws Exception{
		if(CommonUtils.isBlank(staffPhoneNo)){
			return "0";
		}
		String u = "http://121.199.50.122:8888/smsGBK.aspx?action=send&userid=956&account=SZXGCM&password=12345678&mobile=";
		StringBuffer urlsb = new StringBuffer(u);
		//StringBuffer urlsb = new StringBuffer(XsbBusinessConstant.SMSURL);
		int code = SmsUtil.createSmsCode();
		log.info("#################################手机验证码："+code);
		String message = "您申请的手机验证码是："+code+",请输入后进行验证，谢谢！【第一路演】";
		urlsb.append(staffPhoneNo).append("&content=").append(message);
		URL url = new URL(urlsb.toString());
		HttpURLConnection  conn = (HttpURLConnection )url.openConnection();
		//conn.setRequestMethod("GET");
		//conn.setRequestProperty("Accept-Charset", "utf-8");
		//conn.setRequestProperty("contentType", "utf-8");
		conn.setRequestProperty("Accept-Charset", "GBK");
		conn.setRequestProperty("contentType", "GBK");
		conn.connect();
		//读取数据
		//BufferedReader bufIn = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(conn.getInputStream(),"GBK"));
		String line = null;
		StringBuffer contentBuffer = new StringBuffer();
		while((line = bufIn.readLine())!=null)
		{
			contentBuffer.append(line);
		}	
		if(contentBuffer.length()>0 && contentBuffer.toString().indexOf("Success")>0){
			//发送成功则在session中存入手机号码、验证码、发送时间
			HttpSession session = request.getSession();  
	        session.setAttribute(VALIDATE_PHONE, staffPhoneNo);  
	        session.setAttribute(VALIDATE_PHONE_CODE, code);  
	        session.setAttribute(SEND_CODE_TIME, new Date().getTime());  
			return "success";
		}else{
			return "fail";
		}
	}
	/**
	 * 忘记密码，根据手机找回密码
	 * @param customerPhoneNo
	 * @return
	 */
	@RequestMapping(value="login/staffForgetPwd.do",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> forgetPwdByPhone(Staff staff,HttpServletRequest request)throws Exception{
		//首先验证手机验证码是否正确,是否超时
		Map<String,Object> map = new HashMap<String,Object>();
		HttpSession session = request.getSession();  
		String code = "";
		String phone = "";
		if(session.getAttribute(VALIDATE_PHONE_CODE)!=null){
			code = session.getAttribute(VALIDATE_PHONE_CODE).toString();  
		}
		if(session.getAttribute(VALIDATE_PHONE)!=null){
			phone = session.getAttribute(VALIDATE_PHONE).toString();  
		}
		Long codeDate = (Long)session.getAttribute(SEND_CODE_TIME);
        String phoneCode = staff.getPhoneCode();  
        String staffPhoneNo = staff.getStaffPhoneNo();  
        Staff existStaff = staffService.checkRegisteredByPhoneNo(staffPhoneNo);
        //如果customerId不存在，提示用户，手机号码没有注册
        if(existStaff==null){
        	map.put(RESULTCODE, ResultCode.ERROR_PHONENO_NULL);
        	return map;
        }
        //customerVO.setCustomerId(customerId);
        //验证码是否正确
        if(phone.equals(staffPhoneNo) && code.equalsIgnoreCase(phoneCode)){  
        	//验证码正确后，验证验证码是否超时（存在时间5分钟）
        	if(codeDate!=null){
        		System.out.println(CommonUtils.getInstanceTime().longValue());
        		 if(CommonUtils.getInstanceTime().longValue()-codeDate.longValue()>300000){
        			map.put(RESULTCODE, ResultCode.ERROR_CODE_OUTTIME);
        			return map;
        		 }
        	}
        }else{  
        	map.put(RESULTCODE, ResultCode.ERROR_CODE_ERROR);
        	return map;
        }  
        //MD5加密
  		staff.setStaffPassword(MD5.get(staff.getStaffPassword()));
		//修改用户信息
  		try{
  			staffService.updatePassWord(staff);
  			map.put(RESULTCODE, ResultCode.RESPONSE_SUCCESS);
			return map;
  		}catch(Exception e){
  			map.put(RESULTCODE, ResultCode.RESPONSE_FAIL);
			return map;
  		}
	}
	
	/**
	 * 用户电话号码实时校验
	 * @param customerVO
	 * @return
	 */
	@RequestMapping(value="staffRegister/validate.do",method=RequestMethod.GET)
	public @ResponseBody String validateCustomerName(@RequestParam("staffPhoneNo") String staffPhoneNo,
													@RequestParam("staffEmail") String staffEmail)throws Exception{
		Staff staff = new Staff();
		staff.setStaffPhoneNo(staffPhoneNo);
		staff.setStaffEmail(staffEmail);		
		Boolean result=staffService.selectStaffByPhoneNoOrEmail(staff);
		return result.toString();
	}
	/**
	 * 注册用户
	 * @param customerVO
	 * @return
	 */
	@RequestMapping(value="/staffRegister.do",params="method=register",method=RequestMethod.POST)
	public ModelAndView register(Staff staff,HttpServletRequest request)throws Exception{
		ModelAndView mav= new ModelAndView();
		//验证用户是否存在
		//String ss = URLDecoder.decode(customerVO.getCustomerName(),"utf-8");
		/*Integer unameflag = loginRegisterService.validateRegister(customerVO);
		if(unameflag!=null && unameflag==0){
			 mav.setViewName("register");
        	 mav.addObject("error","邮箱或号码已存在！");
        	 mav.addObject("customerVO",customerVO);
        	 return mav;
		}
		customerVO.setCustomerEmail(customerVO.getCustomerPhoneNo());*/
		//首先验证手机验证码是否正确,是否超时
		HttpSession session = request.getSession();  
		String code = "";
		String phone = "";
		if(session.getAttribute(VALIDATE_PHONE_CODE)!=null){
			code = session.getAttribute(VALIDATE_PHONE_CODE).toString();  
		}
		if(session.getAttribute(VALIDATE_PHONE)!=null){
			phone = session.getAttribute(VALIDATE_PHONE).toString();  
		}
		Long codeDate = (Long)session.getAttribute(SEND_CODE_TIME);
        String phoneCode = staff.getPhoneCode();  
        String staffPhoneNo = staff.getStaffPhoneNo();  
        //验证码是否正确
        if(phone.equals(staffPhoneNo) && code.equalsIgnoreCase(phoneCode)){  
        	//验证码正确后，验证验证码是否超时（存在时间5分钟）
        	if(codeDate!=null){
        		System.out.println(CommonUtils.getInstanceTime().longValue());
        		 if(CommonUtils.getInstanceTime().longValue()-codeDate.longValue()>300000){
        			 mav.setViewName("register");
    	        	 mav.addObject("error","验证码超时，请重新申请发送");
    	        	 mav.addObject("staff",staff);
    	        	 return mav;
        		 }
        	}
        }else{  
        	mav.setViewName("register");
        	mav.addObject("error","验证码错误");
        	mav.addObject("staff",staff);
        	return mav;
        }  
        //MD5加密
        staff.setStaffPassword(MD5.get(staff.getStaffPassword()));
		//新增用户信息
		try{
			staffService.addStaff(staff);
			mav.setViewName("index");
			mav.addObject("staff",staff);
		}catch(Exception e){
			throw new ApplicationException("注册失败，请稍后再试，如果多次出现该问题，请联系在线服务人员");
		}

		return mav;
	}
}
