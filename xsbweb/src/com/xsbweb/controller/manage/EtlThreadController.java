package com.xsbweb.controller.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.common.bean.ResultCode;
import com.xsbweb.service.EtlConfigService;
import com.xsbweb.service.StaffService;
import com.xsbweb.util.BaseUtil;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.util.IdlSDK;
import com.xsbweb.vo.EtlThread;

@Controller
@RequestMapping(value="/admin")
public class EtlThreadController {
	//开始线程接口
	private static final String ETL_STAR = "/XSBETLWeb/schedule/toStar";
	//停止线程接口
	private static final String ETL_STOP = "/XSBETLWeb/schedule/toStop";
	//获取线程状态接口
	private static final String ETL_STATUS = "/XSBETLWeb/schedule/getStatus";
	@Autowired
	private static String RESULT_CODE = "resultCode";
	//线程已经启动
	//private static String THREAD_IS_STAR = "20001";
	//线程已经停止
	//private static String THREAD_IS_STOP = "20002";
	@Autowired
	private EtlConfigService etlConfigService;
	private static String SUCCESS = "1";
	 private static Logger log = Logger.getLogger(TrsObjectController.class);
	/**
	 * 根据ip、端口号和线程名称启动etl抽取线程
	 * @param request
	 * @param threadName
	 * @param threadHost
	 * @param threadPort
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/etl/toStar",method=RequestMethod.GET)
	public @ResponseBody String toDoSchedule(
			HttpServletRequest request,
			@RequestParam("threadName") String threadName,
			@RequestParam("threadHost") String threadHost,
			@RequestParam("threadPort") String threadPort
			) throws Exception{
		
		Map<String,Integer> map = new HashMap<String, Integer>();
		//组装请求路径
		StringBuffer urlbuffer = new StringBuffer("http://");
		urlbuffer.append(threadHost)
		.append(":")
		.append(threadPort)
		.append(ETL_STAR);
		//组装请求参数
		StringBuffer parambuffer = new StringBuffer("threadName=");
		parambuffer.append(threadName).append("&threadHost=").append(threadHost)
		.append("&threadPort=").append(threadPort);
		Map<String, Object> result;
		try {
			result = IdlSDK.getURLByGet(urlbuffer.toString(), parambuffer.toString());
			//20001:线程已经启动,20002:线程已经停止
			if(result==null){
				return "0";
			}
			//启动成功，则修改线程管理任务状态
			if(SUCCESS.equals(result.get(RESULT_CODE))){
				EtlThread etlThread = new EtlThread();
				etlThread.setThreadHost(threadHost);
				etlThread.setThreadName(threadName);
				etlThread.setThreadPort(threadPort);
				//etlThread.setLastStartTime(CommonUtils.getInstanceDate());
				//状态改为已启动
				etlThread.setThreadStatus("1");
				etlConfigService.editEtlThread(etlThread);
			}
			return result.get(RESULT_CODE).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "0";
		}
	}
	
	/**
	 * 根据ip、端口号和线程名称停止etl抽取线程
	 * @param request
	 * @param threadName
	 * @param threadHost
	 * @param threadPort
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/etl/toStop",method=RequestMethod.GET)
	public @ResponseBody Object toStopSchedule(
			HttpServletRequest request,
			@RequestParam("threadName") String threadName,
			@RequestParam("threadHost") String threadHost,
			@RequestParam("threadPort") String threadPort
			) throws Exception{
		
		Map<String,Integer> map = new HashMap<String, Integer>();
		//组装请求路径
		StringBuffer urlbuffer = new StringBuffer("http://");
		urlbuffer.append(threadHost)
		.append(":")
		.append(threadPort)
		.append(ETL_STOP);
		//组装请求参数
		StringBuffer parambuffer = new StringBuffer("threadName=");
		parambuffer.append(threadName);
		Map<String,Object> result = null;
		try {
			result = IdlSDK.getURLByGet(urlbuffer.toString(), parambuffer.toString());
			//20001:线程已经启动,20002:线程已经停止
			if(result==null){
				return "0";
			}
			//启动成功，则修改线程管理任务状态
			if(SUCCESS.equals(result.get(RESULT_CODE))){
				EtlThread etlThread = new EtlThread();
				etlThread.setThreadHost(threadHost);
				etlThread.setThreadName(threadName);
				etlThread.setThreadPort(threadPort);
				//etlThread.setLastStopTime(CommonUtils.getInstanceDate());
				//状态改为已停止
				etlThread.setThreadStatus("0");
				etlConfigService.editEtlThread(etlThread);
			}
			return result.get(RESULT_CODE).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "0";
		}
	}
	
	/**
	 * 根据ip、端口号和线程名称获取线程状态
	 * @param request
	 * @param threadName
	 * @param threadHost
	 * @param threadPort
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/etl/getStatus",method=RequestMethod.GET)
	public @ResponseBody Object getStatus(
			HttpServletRequest request,
			@RequestParam("threadName") String threadName,
			@RequestParam("threadHost") String threadHost,
			@RequestParam("threadPort") String threadPort
			) throws Exception{
		
		Map<String,Integer> map = new HashMap<String, Integer>();
		//组装请求路径
		StringBuffer urlbuffer = new StringBuffer("http://");
		urlbuffer.append(threadHost)
		.append(":")
		.append(threadPort)
		.append(ETL_STATUS);
		//组装请求参数
		StringBuffer parambuffer = new StringBuffer("threadName=");
		parambuffer.append(threadName);
		Map<String,Object> result = null;
		try {
			result = IdlSDK.getURLByGet(urlbuffer.toString(), parambuffer.toString());
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1";
		}
	}
	
	/**
	 * etlList
	 * @param etlThread
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/etl",method=RequestMethod.GET)
	public ModelAndView getEtlThreadList(EtlThread etlThread) throws Exception{
		ModelAndView mav = new ModelAndView();
		if(CommonUtils.isBlank(etlThread.getTaskTable())){
			mav.setViewName("manage/etlThread/queryEtlThread");
		}else{
			mav.setViewName("manage/etlThread/queryChooseEtlThread");
			mav.addObject("taskTable", etlThread.getTaskTable());
		}
		List<EtlThread>etlThreadList = etlConfigService.getEtlThread(etlThread);
		//int count = getEtlThreadListCount(thread);
		mav.addObject("etlThreadList",etlThreadList);
		mav.addObject("etlThread",etlThread);
		return mav;
	}
	/**
	 * 新增页面跳转
	 * @param etlThread
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/etl/toAddData.do",method=RequestMethod.GET)
	public ModelAndView toAddEtlThread(EtlThread etlThread) throws Exception{
		ModelAndView mav = new ModelAndView("manage/etlThread/addEtlThread");
		mav.addObject("etlThread",etlThread);
		return mav;
	}
	/**
	 * 新增数据提交
	 * @param etlThread
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/etl",method=RequestMethod.PUT)
	public ModelAndView addEtlThread(HttpServletRequest request,EtlThread etlThread) throws Exception{
		ModelAndView mav = new ModelAndView();
		etlThread.setCreatedBy(BaseUtil.getCookieValue("customerId", request));
		log.info("customerId:"+BaseUtil.getCookieValue("customerId", request));
		int result = etlConfigService.addEtlThread(etlThread);
		if(result>0){
			mav.setViewName("redirect:/admin/etl");
		}else{
			mav.addObject("etlThread",etlThread);
			mav.setViewName("manage/etlThread/addEtlThread");
		}
		return mav;
	}
	/**
	 * 更新页面跳转
	 * @param etlThread
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/etl/{threadHost}/{threadPort}/{threadName}",method= RequestMethod.GET)
	public ModelAndView toUpdateEtlThread(
			@PathVariable("threadHost") String threadHost,
			@PathVariable("threadPort") String threadPort,
			@PathVariable("threadName") String threadName
			) throws Exception{
		ModelAndView mav = new ModelAndView("manage/etlThread/editForeEtlThread");
		EtlThread etlThread = new EtlThread();
		etlThread.setThreadHost(threadHost);
		etlThread.setThreadPort(threadPort);
		etlThread.setThreadName(threadName);
		List<EtlThread>etlThreadList=etlConfigService.getEtlThread(etlThread);
		if(etlThreadList!=null &&!etlThreadList.isEmpty()){
			etlThread=etlThreadList.get(0);
		}
		mav.addObject("etlThread",etlThread);
		return mav;
	}
	/**
	 * 编辑提交
	 * @param etlThread
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/etl/{threadHost}/{threadPort}/{threadName}/upt",method= RequestMethod.PUT)
	public ModelAndView updateEtlThread(HttpServletRequest request,EtlThread etlThread) throws Exception{
		ModelAndView mav = new ModelAndView();
		etlThread.setLastUpdateBy(BaseUtil.getCookieValue("customerId", request));
		int result = etlConfigService.editEtlThread(etlThread);
		if(result>0){
			mav.setViewName("redirect:/admin/etl");
		}else{
			mav.addObject("etlThread",etlThread);
			mav.setViewName("manage/etlThread/editForeEtlThread");
		}
		return mav;
	}
	/**
	 * 删除EtlThread
	 * @param threadHost
	 * @param threadPort
	 * @param threadName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/etl/{threadHost}/{threadPort}/{threadName}/del",method= RequestMethod.DELETE)
	public @ResponseBody Map<String,Object>deleteEtlThread(
			@PathVariable("threadHost") String threadHost,
			@PathVariable("threadPort") String threadPort,
			@PathVariable("threadName") String threadName)throws Exception{
		Map<String,Object>map = new HashMap<String,Object>();
		EtlThread etlThread = new EtlThread();
		etlThread.setThreadHost(threadHost);
		etlThread.setThreadPort(threadPort);
		etlThread.setThreadName(threadName);
		int result = etlConfigService.deleteEtlThread(etlThread);
		if(result>0){
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
		}else{
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		}
		return map;
	}
	
	/**
	 * 该操作只有在上次服务器停掉的情况下才能操作，该操作将会改变数据库记录状态，而不会实际改变线程状态！
	 * @param threadHost
	 * @param threadPort
	 * @param threadName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/etl/{threadHost}/{threadPort}/{threadName}/gw",method= RequestMethod.POST)
	public @ResponseBody int gw(
			@PathVariable("threadHost") String threadHost,
			@PathVariable("threadPort") String threadPort,
			@PathVariable("threadName") String threadName)throws Exception{
		EtlThread etlThread = new EtlThread();
		etlThread.setThreadHost(threadHost);
		etlThread.setThreadName(threadName);
		etlThread.setThreadPort(threadPort);
		//状态改为已停止
		etlThread.setThreadStatus("0");
		int result = etlConfigService.editEtlThread(etlThread);
		if(result>0){
			return ResultCode.RESPONSE_SUCCESS;
		}else{
			return ResultCode.RESPONSE_FAIL;
		}
	}
}

