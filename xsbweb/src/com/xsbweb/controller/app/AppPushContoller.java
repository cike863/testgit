package com.xsbweb.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsbweb.common.bean.ResultCode;
import com.xsbweb.common.bean.XsbBusinessConstant;
import com.xsbweb.service.NewsService;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.vo.News;

@Controller
public class AppPushContoller {

	@Autowired
	private NewsService newsService;
	/**
	 * 根据新闻no获取新闻内容
	 * @param newsNo
	 * @return
	 */
	@RequestMapping(value="/app/push/news",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getNewsInfoByPush(
			HttpServletRequest request,
			@RequestParam("newsNo") String newsNo,
			@RequestParam("loginMethod") String loginMethod
			){
		Map<String,Object> map = new HashMap<String, Object>();
		//请求参数有误
		if(CommonUtils.isBlank(newsNo)||CommonUtils.isBlank(loginMethod)){
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		News news = null;
		try {
			news = newsService.getNewsContentByNewsNo(newsNo);
			Map<String,Object> newsMap = new HashMap<String, Object>();
			if(news!=null){
				newsMap.put("newNo", news.getNewsNo());
				newsMap.put("sharePicPath", news.getSharePicPath());
				//分享路径
				StringBuffer shareUrl = new StringBuffer(XsbBusinessConstant.WAP_BASE_URL);
				shareUrl.append("/getWebNewsContent.do?loginMethod=wap&newsNo=").append(newsNo);
				newsMap.put("shareUrl", shareUrl.toString());
				newsMap.put("shareContent", news.getNewsExTitle());
			}
			map.put("news", newsMap);
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_SYSTEM);
			e.printStackTrace();
		}finally{
			request.setAttribute(ResultCode.RESULT_OBJECT, map);
		}
		return map;
	}
}
