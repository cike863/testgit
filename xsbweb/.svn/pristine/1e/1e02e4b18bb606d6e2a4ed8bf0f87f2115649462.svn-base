package com.xsbweb.controller.manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.common.bean.ResultCode;
import com.xsbweb.controller.validation.ValidGroup1;
import com.xsbweb.service.ConfigService;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.vo.SnapEtlSchedule;
import com.xsbweb.vo.extend.*;

@Controller
public class SnapEtlScheduleController {

	@Resource
	private ConfigService configService;
	
	/**
	 * 查询列表
	 * @param etlScheduleVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/etlschedule/getEtlScheduleList.do")
	public ModelAndView getEtlScheduleList(SnapEtlSchedule snapEtlSchedule)throws Exception{
		ModelAndView mvc = new ModelAndView();
		//SnapEtlSchedule etlScheduleVO = new SnapEtlSchedule();
		List<SnapEtlSchedule> etlSchedulelist = configService.getEtlScheduleList(snapEtlSchedule);
		int count =  configService.getEtlScheduleCount(snapEtlSchedule);
		snapEtlSchedule.setTotalRecord(count);
		mvc.setViewName("manage/schedule/queryEtlScheduleList");
		mvc.addObject("snapEtlSchedule",snapEtlSchedule);
		mvc.addObject("etlScheduleList",etlSchedulelist);
		return mvc;
	}
	
	/**
	 * 查询列表
	 * @param etlScheduleVO
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value="/admin/etlschedule/toEtlScheduleList.do")
	public ModelAndView toEtlScheduleList(SnapEtlSchedule etlScheduleVO)throws Exception{
		ModelAndView mvc = new ModelAndView();
		//SnapEtlSchedule etlScheduleVO = new SnapEtlSchedule();
		List<SnapEtlSchedule> etlSchedulelist = configService.getEtlScheduleList(etlScheduleVO);
		mvc.setViewName("WEB-INF/jsp/manage/schedule/queryEtlScheduleList");
		mvc.addObject("etlScheduleList",etlSchedulelist);
		return mvc;
	}*/
	
	/**
	 * 新增
	 * @param etlScheduleVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/etlschedule/addEtlSchedule.do",method=RequestMethod.POST)
	public ModelAndView addEtlSchedule(
			@Valid @ModelAttribute("etlScheduleMode") SnapEtlSchedule etlScheduleVO,
			BindingResult bindingResult)throws Exception{
		/*if(etlScheduleVO == null){
			return;
		}*/
		ModelAndView mav= new ModelAndView();
		// 获取校验错误信息
		if (bindingResult.hasErrors()) {
			// 输出错误信息
			List<ObjectError> allErrors = bindingResult.getAllErrors();

			for (ObjectError objectError : allErrors) {
				// 输出错误信息
				System.out.println(objectError.getDefaultMessage());

			}
			// 将错误信息传到页面
			mav.addObject("allErrors", allErrors);
			mav.addObject("etlScheduleVO", etlScheduleVO);
			mav.setViewName("manage/schedule/addEtlSchedule");
			return mav;
		}
		int flag = configService.addEtlSchedule(etlScheduleVO);
		if(flag<1){
			mav.setViewName("manage/schedule/addEtlSchedule");
			mav.addObject("etlScheduleVO", etlScheduleVO);
		}else{
			mav.setViewName("redirect:/admin/etlschedule/getEtlScheduleList.do");
		}
		//mav.setViewName("success");
		return mav;
	}
	
	/**
	 * 编辑/更新
	 * @param etlScheduleVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/etlschedule/editEtlSchedule.do",method=RequestMethod.POST)
	public ModelAndView editEtlSchedule(SnapEtlSchedule etlScheduleVO)throws Exception{
		ModelAndView mav= new ModelAndView();
		int flag = configService.updateEtlSchedule(etlScheduleVO);
		if(flag<1){
			mav.setViewName("manage/schedule/editEtlSchedule");
			mav.addObject("etlScheduleVO", etlScheduleVO);
		}else{
			mav.setViewName("redirect:/admin/etlschedule/getEtlScheduleList.do");
		}
		return mav;
	}
	
	/**
	 * 删除
	 * @param etlScheduleVO
	 * @param taskTable
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/etlschedule/deleteEtlSchedule.do")
	public  String deleteEtlSchedule(SnapEtlSchedule etlScheduleVO,@RequestParam("taskTable") String taskTable)throws Exception{
		/*if(etlScheduleVO == null){
			return;
		}*/
		SnapEtlSchedule eVO = new SnapEtlSchedule();
		eVO.setTaskTable(taskTable);
		int flag = configService.deleteEtlSchedule(eVO);
		if(flag<1){
			return "删除失败";
		}
		//现在成功跳转到登录页面
		return "redirect:/admin/etlschedule/getEtlScheduleList.do";
	}
	
	/**
	 * 跳转到新增页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/etlschedule/toAddEtlSchedule.do")
	public ModelAndView toAddEtlSchedule() throws Exception{
		ModelAndView mav = new ModelAndView();
		/*if(CommonUtils.isBlank(taskTable)){
		 * 
		}*/
		//SnapEtlSchedule etlScheduleVO = configService.getEtlScheduleByTaskTable(taskTable);
		mav.addObject("etlScheduleVO",new SnapEtlSchedule());
		mav.setViewName("manage/schedule/addEtlSchedule");
		return mav;
	}
	
	/**
	 * 跳转到编辑页面
	 * @param taskTable
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/etlschedule/toEditEtlSchedule.do")
	public ModelAndView toEditEtlSchedule(@RequestParam("taskTable") String taskTable) throws Exception{
		ModelAndView mav = new ModelAndView();
		/*if(CommonUtils.isBlank(taskTable)){
		 * 
		}*/
		SnapEtlSchedule etlScheduleVO2 = configService.getEtlScheduleByTaskTable(taskTable);
		mav.addObject("etlScheduleVO",etlScheduleVO2);
		mav.setViewName("manage/schedule/editEtlSchedule");
		return mav;
	}
	/**
	 * 关联线程更新
	 * @param etlScheduleVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/etlschedule/updateRelevanceEtlSchedule.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateRelevanceEtlSchedule(SnapEtlSchedule etlScheduleVO)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		int flag = configService.updateEtlSchedule(etlScheduleVO);
		if(flag<1){
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		}else{
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
		}
		return map;
	}
}
