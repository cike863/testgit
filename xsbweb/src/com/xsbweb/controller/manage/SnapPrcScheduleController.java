package com.xsbweb.controller.manage;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.service.ConfigService;
import com.xsbweb.vo.SnapPrcSchedule;

@Controller
public class SnapPrcScheduleController {

	@Resource
	private ConfigService configService;
	
	/**
	 * 查询列表
	 * @param PrcScheduleVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/prcSchedule/getPrcScheduleList.do")
	public @ResponseBody List<SnapPrcSchedule> getPrcScheduleList(SnapPrcSchedule prcScheduleVO)throws Exception{
		if(prcScheduleVO == null){
			return new ArrayList<SnapPrcSchedule>();
		}
		List<SnapPrcSchedule> list = configService.getPrcScheduleList(prcScheduleVO);
		return list;
	}
	
	/**
	 * 新增
	 * @param PrcScheduleVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/prcSchedule/addPrcSchedule.do")
	public ModelAndView addPrcSchedule(SnapPrcSchedule prcScheduleVO)throws Exception{
		ModelAndView mav= new ModelAndView();
		int flag = configService.addPrcSchedule(prcScheduleVO);
		if(flag<1){
			
		}
		//现在成功跳转到登录页面
		mav.setViewName("success");
		return mav;
	}
	
	/**
	 * 编辑
	 * @param PrcScheduleVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/prcSchedule/editPrcSchedule.do")
	public ModelAndView editPrcSchedule(SnapPrcSchedule prcScheduleVO)throws Exception{
		ModelAndView mav= new ModelAndView();
		int flag = configService.updatePrcSchedule(prcScheduleVO);
		if(flag<1){
			
		}
		//现在成功跳转到登录页面
		mav.setViewName("success");
		return mav;
	}
	
	/**
	 * 删除
	 * @param PrcScheduleVO
	 * @param taskPrc
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/prcschedule/deletePrcSchedule.do")
	public @ResponseBody String deletePrcSchedule(SnapPrcSchedule prcScheduleVO,@RequestParam("taskPrc") String taskPrc)throws Exception{
		SnapPrcSchedule pVO = new SnapPrcSchedule();
		pVO.setTaskPrc(taskPrc);
		int flag = configService.deletePrcSchedule(pVO);
		if(flag<1){
			
		}
		//现在成功跳转到登录页面
		return "success";
	}
	
	/**
	 * 跳转到新增页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/prcSchedule/toAddPrcSchedule.do")
	public ModelAndView toAddPrcSchedule() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.addObject("prcScheduleVO",new SnapPrcSchedule());
		mav.setViewName("WEB-INF/jsp/manage/schedule/addPrcSchedule");
		return mav;
	}
	
	/**
	 * 跳转到编辑页面
	 * @param taskTable
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/prcSchedule/toEditPrcSchedule.do")
	public ModelAndView toEditPrcSchedule(@RequestParam("taskPrc") String taskPrc) throws Exception{
		ModelAndView mav = new ModelAndView();
		SnapPrcSchedule prcScheduleVO = configService.getPrcScheduleByTaskPrc(taskPrc);
		mav.addObject("PrcScheduleVO",prcScheduleVO);
		mav.setViewName("WEB-INF/jsp/manage/schedule/editPrcSchedule");
		return mav;
	}
}
