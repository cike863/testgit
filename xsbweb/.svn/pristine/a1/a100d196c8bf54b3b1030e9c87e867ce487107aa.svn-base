package com.xsbweb.controller.manage;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.service.ConfigService;
import com.xsbweb.vo.TrsExeLog;

@Controller
public class TrsExeLogController {

	@Resource
	private ConfigService configService;
	
	/**
	 * 查询列表
	 * @param TrsExeLogVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/trExeLog/getTrsExeLogList.do")
	public @ResponseBody List<TrsExeLog> getTrsExeLogList(TrsExeLog trExeLogVO)throws Exception{
		if(trExeLogVO == null){
			return new ArrayList<TrsExeLog>();
		}
		List<TrsExeLog> list = configService.getTrsExeLogList(trExeLogVO);
		return list;
	}
	
	/**
	 * 新增
	 * @param TrsExeLogVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/trExeLog/addTrsExeLog.do")
	public ModelAndView addTrsExeLog(TrsExeLog trExeLogVO)throws Exception{
		ModelAndView mav= new ModelAndView();
		int flag = configService.addTrsExeLog(trExeLogVO);
		if(flag<1){
			
		}
		//现在成功跳转到登录页面
		mav.setViewName("success");
		return mav;
	}
	
	/**
	 * 编辑
	 * @param TrsExeLogVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/trExeLog/editTrsExeLog.do")
	public ModelAndView editTrsExeLog(TrsExeLog trExeLogVO)throws Exception{
		ModelAndView mav= new ModelAndView();
		int flag = configService.updateTrsExeLog(trExeLogVO);
		if(flag<1){
			
		}
		//现在成功跳转到登录页面
		mav.setViewName("success");
		return mav;
	}
	
	/**
	 * 删除
	 * @param TrsExeLogVO
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/prcschedule/deleteTrsExeLog.do")
	public @ResponseBody String deleteTrsExeLog(TrsExeLog trExeLogVO,@RequestParam("taskId") String taskId)throws Exception{
		TrsExeLog pVO = new TrsExeLog();
		pVO.setTaskId(taskId);
		int flag = configService.deleteTrsExeLog(pVO);
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
	@RequestMapping(value="/admin/trExeLog/toAddTrsExeLog.do")
	public ModelAndView toAddTrsExeLog() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.addObject("trExeLogVO",new TrsExeLog());
		mav.setViewName("WEB-INF/jsp/manage/schedule/addTrsExeLog");
		return mav;
	}
	
	/**
	 * 跳转到编辑页面
	 * @param taskTable
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/trExeLog/toEditTrsExeLog.do")
	public ModelAndView toEditTrsExeLog(@RequestParam("taskId") String taskId) throws Exception{
		ModelAndView mav = new ModelAndView();
		TrsExeLog trExeLogVO = configService.getTrsExeLogByTaskId(taskId);
		mav.addObject("TrsExeLogVO",trExeLogVO);
		mav.setViewName("WEB-INF/jsp/manage/schedule/editTrsExeLog");
		return mav;
	}
	

}
