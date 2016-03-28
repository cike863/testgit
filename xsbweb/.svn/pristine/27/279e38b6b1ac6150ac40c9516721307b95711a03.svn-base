package com.xsbweb.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsbweb.common.bean.ResultCode;
import com.xsbweb.service.ConfigService;
import com.xsbweb.service.TrsProjectService;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.util.RedisUtil;
import com.xsbweb.vo.TrsMedia;
import com.xsbweb.vo.TrsProject;

@Controller
public class AppTrsMediaController {

	private Logger log = Logger.getLogger(AppTrsProjectController.class);
	@Autowired
	private ConfigService configService;
	
	@Resource
	private RedisUtil redisUtil;
	
	@RequestMapping(value="/app/media/getTrsMediaList.do",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getTrsMediaList(
			HttpServletRequest request,
			@RequestParam("pageNo") Integer pageNo,
			@RequestParam("tmpToken") String tmpToken,
			@RequestParam("customerPhoneNo") String customerPhoneNo
			){
		Map<String,Object> map = new HashMap<String,Object>();
		//参数错误
		if(pageNo==null && CommonUtils.isBlank(customerPhoneNo)&&CommonUtils.isBlank(tmpToken)){
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			log.info("#######################"+customerPhoneNo+" 访问参数有误！");
			return map;
		}
		//token验证
		String token = redisUtil.getString("tmp:"+customerPhoneNo+":token");
		if(!tmpToken.equals(token)){
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_REQUEST);
			log.info("#######################"+customerPhoneNo+" 非法请求！");
			return map;
		}
		
		TrsMedia trsMedia = new TrsMedia();
		trsMedia.setPageNo(pageNo==0?0:(pageNo-1));
		trsMedia.setPageSize(10);
		List<TrsMedia> trsMediaList = null;
		try {
			trsMediaList = configService.getTrsMediaList(trsMedia);
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
			map.put("trsMediaList", trsMediaList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//系统错误
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_SYSTEM);
			log.info("############AppTrsMediaController--getTrsMediaList--configService.getTrsMediaList(trsMedia)报错#######################");
			e.printStackTrace();
		}finally{
			request.setAttribute(ResultCode.RESULT_OBJECT, map);
		}
		return map;
	}
	
	@RequestMapping(value="/app/media/getTrsMediaByMediaNo.do",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getTrsMediaByMediaNo(
			HttpServletRequest request,
			@RequestParam("mediaNo") String mediaNo,
			@RequestParam("tmpToken") String tmpToken,
			@RequestParam("customerPhoneNo") String customerPhoneNo
			){
		Map<String,Object> map = new HashMap<String,Object>();
		//参数错误
		if(CommonUtils.isBlank(mediaNo)&&CommonUtils.isBlank(customerPhoneNo)&&CommonUtils.isBlank(tmpToken)){
			log.info("###########getTrsMediaByMediaNo(mediaNo)访问参数错误###########");
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		//token验证
		String token = redisUtil.getString("tmp:"+customerPhoneNo+":token");
		if(!tmpToken.equals(token)){
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_REQUEST);
			log.info("#######################"+customerPhoneNo+" 非法请求！");
			return map;
		}
		TrsMedia trsMedia = null;
		try {
			trsMedia = configService.getTrsMediaByMediaNo(mediaNo);
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
			map.put("trsMedia", trsMedia);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//系统错误
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_SYSTEM);
			log.info("############AppTrsMediaController--getTrsMediaByMediaNo--configService.getTrsMediaByMediaNo(mediaNo)报错#######################");
			e.printStackTrace();
		}finally{
			request.setAttribute(ResultCode.RESULT_OBJECT, map);
		}
		return map;
	}
}
