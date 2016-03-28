package com.xsbweb.controller.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.common.bean.ResultCode;
import com.xsbweb.service.ConfigService;
import com.xsbweb.service.IndustryBaikeService;
import com.xsbweb.service.XSBBaseService;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.util.MD5;
import com.xsbweb.util.RedisUtil;
import com.xsbweb.vo.IndustryBaike;
import com.xsbweb.vo.Staff;

@Controller
public class AdminBaikeController {
	private Logger log = Logger.getLogger(AdminBaikeController.class);
	
	   @Autowired
		private ConfigService configService;  
	    
	   @Autowired
	   private IndustryBaikeService bkService;
	    
	   @Autowired
	   private  XSBBaseService xsbBaseService;
	    
	    private RedisUtil redisUtil = new RedisUtil();
	    /**
	     * 三板百科列表页面
	     * @param industry baike 
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value="/admin/encyclopedias",method=RequestMethod.GET)
	    public ModelAndView toAdminBaike(IndustryBaike bk)throws Exception{
	    	ModelAndView mav = new ModelAndView();
	    	//获取总数
	    	int totalCounts = bkService.getIndustryBaikeCounts(bk);
	    	List<IndustryBaike> bkList = bkService.getAllIndustryBaikeList(bk);
	    	bk.setTotalRecord(totalCounts);
	    	mav.setViewName("manage/encyclopedias/queryEncyclopediasList");
	    	mav.addObject("bkList",bkList);
	    	mav.addObject("bk",bk);
	    	return mav;
	    }
	    
	    /**
	     * 删除
	     * @param industry baike 
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value="/admin/encyclopedias/deleteBaike.do",method=RequestMethod.GET)
	    public String deleteBaike(String id,ModelMap mode,Staff staff)throws Exception{
	    	int flag =bkService.delIndustryBaike(id);
	    	if(flag==1){
	    		return "redirect:/admin/encyclopedias.do";
	    	}else{
	    		mode.addAttribute("error","删除失败");
	    		return "admin/encyclopedias";
	    	}
	    }
	    
	    /**
	     * 跳转新增用户
	     * @param bk
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value="/admin/encyclopedias/toAddBaike.do")
	    public ModelAndView toAddBaike()throws Exception{
		    	ModelAndView mav = new ModelAndView();
		    	IndustryBaike bk = new IndustryBaike();
		    	mav.addObject("bk",bk);
		    	mav.setViewName("manage/encyclopedias/addEncyclopedias");
	    	return mav;
		    }
	    
	    /**
	     * 新增用户
	     * @param news
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value="/admin/encyclopedias/addBaike.do")
	    public String addBaike(IndustryBaike bk)throws Exception{
	    	bk.setBaikeId(bkService.getMaxId());
	    	bkService.insertBaike(bk);
	    	return "redirect:/admin/encyclopedias.do";
	    }
	    
	    /**
	     * 跳转到用户编辑页面
	     * @param id
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value="/admin/encyclopedias/toEditBaike.do")
	    public ModelAndView toEditBaike(String id)throws Exception{
	    	ModelAndView mav = new ModelAndView();
	    	IndustryBaike bk = bkService.getIndustryBaikeById(id);
	        if(bk!=null){
	    	mav.setViewName("manage/encyclopedias/editEncyclopedias");
	    	mav.addObject("bk",bk);
	        }
	        return mav;
	    }
    
	    /**
	     * 编辑用户
	     * @param 
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value="/admin/encyclopedias/editBaike.do")
	    public String editCustomer(IndustryBaike bk,ModelMap mode)throws Exception{
	    	if(bk==null || bk.getBaikeId()<0){
	    		mode.addAttribute("bk", bk);
	    		mode.addAttribute("error","用户id缺失！");
	    		log.info("####################用户id缺失！");
	    		return "manage/encyclopedias/toEditBaike.do";
	    	}
	    	//编辑
	    	int flag = bkService.updateBaike(bk);
	    	if(flag>0){
	    		return "redirect:/admin/encyclopedias.do";
	    	}else{
	    		mode.addAttribute("bk", bk);
	    		mode.addAttribute("error","修改失败！");
	    		return "manage/encyclopedias/toEditBaike.do";
	    		}
	    }
	    /**
	     * 批量删除百科
	     * @param industry baike 
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value="/admin/encyclopedias/{baikeIdArrs}/del",method=RequestMethod.DELETE)
	    @ResponseBody
	    public Map<String,Object> batchDeleteBaike(@PathVariable("baikeIdArrs")String[] baikeIdArrs)throws Exception{
	    	int flag =bkService.batchDeleteIndustryBaike(baikeIdArrs);
	    	Map<String,Object>map = new HashMap<String,Object>();
	    	if(flag>0){
	    		map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
	    	}else{
	    		map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
	    	}
	    	return map;
	    }

}
