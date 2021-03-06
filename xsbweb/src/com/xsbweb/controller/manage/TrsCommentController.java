package com.xsbweb.controller.manage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.common.bean.DataBaseConstant;
import com.xsbweb.common.bean.ResultCode;
import com.xsbweb.service.CustomerService;
import com.xsbweb.service.TrsCommentService;
import com.xsbweb.service.XSBBaseService;
import com.xsbweb.util.BaseUtil;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.util.MultipleDataSource;
import com.xsbweb.vo.Comment;
import com.xsbweb.vo.Customer;
import com.xsbweb.vo.TrsComment;
import com.xsbweb.vo.extend.CustomerVO;

@Controller
public class TrsCommentController {
	@Autowired
	private TrsCommentService trsCommentService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private XSBBaseService xsbBaseService;
	/**
	 * 依据projectNo projName获得用户信息，customer用户搜索
	 * @param projectNo
	 * @param projName
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/comment")
	public ModelAndView getCommentByProjectNo(@RequestParam("projectNo")String projectNo,@RequestParam("projName")String projName,CustomerVO customer) throws Exception{
		ModelAndView mav = new ModelAndView("manage/projectServices/commonProjectCoustomersList");
		TrsComment trsComment= new TrsComment();
		trsComment.setCommentAddress(projectNo);
		List<TrsComment> comments=trsCommentService.getTrsCommentByAddress(trsComment);
		if(comments.isEmpty()){
			mav.addObject(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		}else{
			List<Customer>customerList= customerService.getCustomerListByIds(comments,customer);
			int count = customerService.getCustomerCountByIds(comments,customer);
			customer.setTotalRecord(count);
			mav.addObject("customerList", customerList);
		}
		mav.addObject("customer", customer);
		mav.addObject("projectNo", projectNo);
		mav.addObject("projName", projName);
		return mav;
	}
	/**
	 * 通过projectNo  customerId获得用户以及客服人员留言信息
	 * @param projectNo
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping("/admin/comment/getViewDetInformMes")
	public ModelAndView getViewDetInformMes(@RequestParam("projectNo")String projectNo,
											@RequestParam("customerId")String customerId) throws Exception{
		ModelAndView mav = new ModelAndView("manage/projectServices/projectServicesDetInformList");
		TrsComment trsComment= new TrsComment();
		trsComment.setCommentAddress(projectNo);
		trsComment.setCommenter(customerId);
		List<TrsComment> commentlist=trsCommentService.getTrsCommentByAddress(trsComment);
		mav.addObject("commentlist", commentlist);
		mav.addObject("customerId", customerId);
		mav.addObject("projectNo", projectNo);
		return mav;
	}
	/**
	 * 新增评论、留言
	 * @param objectId
	 * @param loginMethod
	 * @param customerId
	 * @param commentContent  /app/comment/{id}  method post put delete get 
	 * @return
	 */
	@RequestMapping(value="/admin/comment",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addComment(
			HttpServletRequest request,Comment comment,
			@RequestParam("loginMethod") String loginMethod){
		Map<String,Object> map = new HashMap<String, Object>();
		//请求参数有误
		if(CommonUtils.isBlank(comment.getCommentContent())||CommonUtils.isBlank(comment.getCommentAddress())
				||CommonUtils.isBlank(loginMethod)||CommonUtils.isBlank(comment.getCommentPlatform())){
			map.put("resultCode", ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		comment.setCommenter(BaseUtil.getCookieValue("customerId", request));
		int flag = 0;
		try {
			flag = xsbBaseService.addComment(comment);
			if(flag>0){
				map.put("resultCode", ResultCode.RESPONSE_SUCCESS);
			}else{
				map.put("resultCode", ResultCode.RESPONSE_FAIL);
			}			
		}catch(Exception e){
			map.put("resultCode", ResultCode.ERROR_SYSTEM);
			e.printStackTrace();
			return map;
		}finally{
			//request.setAttribute("resultObject", map);
		}
		return map;
	}
	@RequestMapping(value="admin/trsComment",method=RequestMethod.GET)
	public ModelAndView getComment(TrsComment trsComment) throws Exception{
		ModelAndView mav = new ModelAndView("manage/comment/queryCommentList");
		int count = trsCommentService.getCommentListCount(trsComment);
		trsComment.setTotalRecord(count);
		List<TrsComment> commentList=trsCommentService.getTrsCommentByAddress(trsComment);
		mav.addObject("trsComment", trsComment);
		mav.addObject("commentList", commentList);
		return mav;
	}
	@RequestMapping(value="admin/trsComment/{trsCommentNoArrs}/del",method=RequestMethod.DELETE)
	@ResponseBody
	public Map<String,Integer> bathDeleteProject(
			@PathVariable("trsCommentNoArrs") String[] trsCommentNoArrs)
			throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int result = trsCommentService.bathDeleteProject(trsCommentNoArrs);
		if (result > 0) {
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
		} else {
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		}
		return map;
	}
}
