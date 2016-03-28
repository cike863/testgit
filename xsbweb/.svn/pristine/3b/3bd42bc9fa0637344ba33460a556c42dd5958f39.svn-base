package com.xsbweb.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
import com.xsbweb.vo.RObject;
import com.xsbweb.vo.extend.NewsVO;

@Controller
public class AppNewsController {

	@Autowired 
	private NewsService newsService; 
	
	/**
	 * 进入三板行情页面
	 * @param newsNo
	 * @return
	 */
	@RequestMapping(value="/app/news/getNewsIndexData.do",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getNewsIndexData(
			HttpServletRequest request,
			@RequestParam("loginMethod") String loginMethod
			){
		Map<String,Object> map = new HashMap<String, Object>();
		//请求参数有误
		if(CommonUtils.isBlank(loginMethod)){
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		List<RObject> newsScrollList = null; 
		List<RObject> newsIndexList = null; 
		RObject rObject = new RObject();
		rObject.setPageNo(1);
		rObject.setPageSize(10);
		//默认获取要闻列表
		rObject.setNewsType(XsbBusinessConstant.NEWS_PRIMARY);
		try {
			newsScrollList = newsService.getNewsScrollList();
			newsIndexList = newsService.getNewsIndexListByType(rObject); 
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
			map.put("newsScrollList", newsScrollList);
			map.put("newsIndexList", newsIndexList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_SYSTEM);
			e.printStackTrace();
		}finally{
			request.setAttribute(ResultCode.RESULT_OBJECT, map);
		}
		return map;
	}
	
	/**
	 * 根据新闻类型获取新闻列表集合
	 * @param newsNo
	 * @return
	 */
	@RequestMapping(value="/app/news/getNewsIndexListByType.do",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getNewsIndexData(
			HttpServletRequest request,
			@RequestParam(value="type",required=false) String type,
			@RequestParam("pageNo") String pageNo,
			@RequestParam("loginMethod") String loginMethod
			){
		Map<String,Object> map = new HashMap<String, Object>();
		//请求参数有误
		if(CommonUtils.isBlank(loginMethod)||CommonUtils.isBlank(pageNo)){
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		//如果type没传或者为空，则默认查要闻
		if(CommonUtils.isBlank(type)){
			type = XsbBusinessConstant.NEWS_PRIMARY;
		}
		List<RObject> newsIndexList = null; 
		RObject rObject = new RObject();
		rObject.setPageNo(Integer.valueOf(pageNo));
		rObject.setPageSize(10);
		//默认获取要闻列表
		rObject.setNewsType(type);
		List<RObject> newsScrollList = null; 
		try {
			newsScrollList = newsService.getNewsScrollList();
			newsIndexList = newsService.getNewsIndexListByType(rObject); 
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
			map.put("newsIndexList", newsIndexList);
			map.put("newsScrollList", newsScrollList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_SYSTEM);
			e.printStackTrace();
		}finally{
			request.setAttribute(ResultCode.RESULT_OBJECT, map);
		}
		return map;
	}
	
	/**
	 * 根据新闻no获取新闻内容
	 * @param newsNo
	 * @return
	 */
	@RequestMapping(value="/app/news/getNewsDetail.do",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getNewsContentByNewsNo(
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
			if(news!=null){
				List<News> relatedNewsList = newsService.getRelatedReadList(news);
				map.put("relatedNewsList", relatedNewsList);
			}else{
				map.put("relatedNewsList", null);
			}
			map.put("news", news);
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
	
	/**
	 * 根据新闻模块获取新闻列表集合
	 * @param newsConfig
	 * @return
	 */
	@RequestMapping(value="/app/news/getNewsByConfig.do",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getNewsListByNewsConfig(
			HttpServletRequest request,
			@RequestParam("newsConfig") String newsConfig
			){
		Map<String,Object> map = new HashMap<String, Object>();
		/*//请求参数有误
		if(CommonUtils.isBlank(newsConfig)){
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}*/
		//如果newsConfig为空，默认查询“要闻”模块新闻
		if(CommonUtils.isBlank(newsConfig)){
			//newsConfig = 
		}
		NewsVO newsVO;
		try {
			newsVO = newsService.getNewsIndexListByPrc(newsConfig);
			if(newsVO.getPrcFlag()==1){
				map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
				map.put("newsList", newsVO.getNewsList());
			}else{
				map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
			}
		} catch (Exception e) {
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_SYSTEM);
			e.printStackTrace();
		}finally{
			request.setAttribute(ResultCode.RESULT_OBJECT, map);
		}
		return map;
	}
	
	/**
	 * 根据新闻no获取新闻内容
	 * @param newsNo
	 * @return
	 */
	@RequestMapping(value="/app/news/getNewsDetail2.do",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getNewsContentByNewsNo2(
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
		NewsVO newsVO = new NewsVO();
		News news;
		try {
			news = newsService.getNewsContentByNewsNo(newsNo);
			if(news!=null){
				newsVO.setNews(news);
			}
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
			map.put("news", newsVO.getNews());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_SYSTEM);
			e.printStackTrace();
		}finally{
			request.setAttribute(ResultCode.RESULT_OBJECT, map);
		}
		return map;
	}
	
	/**
	 * 点赞
	 * @param request
	 * @param objectId
	 * @param loginMethod
	 * @return
	 */
	@RequestMapping(value="/app/comment/praise",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> praise(
			HttpServletRequest request,
			@RequestParam("objectId") String objectId,
			@RequestParam("loginMethod") String loginMethod
			){
		Map<String,Object> map = new HashMap<String, Object>();
		//请求参数有误
		if(CommonUtils.isBlank(objectId)||CommonUtils.isBlank(loginMethod)){
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		try {
			//点赞
			int flag = newsService.praiseComment(objectId);
			if(flag>0){
				map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
			}else{
				map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_SYSTEM);
			e.printStackTrace();
		}finally{
			request.setAttribute(ResultCode.RESULT_OBJECT, map);
		}
		return map;
	}
	
	/**
	 * 三板头条搜索接口年，临时的
	 * @param request
	 * @param searchLabel
	 * @param loginMethod
	 * @return
	 */
	@RequestMapping(value="/app/news/search",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> searchNewsTmp(
			HttpServletRequest request,
			@RequestParam("searchLabel") String searchLabel,
			@RequestParam("loginMethod") String loginMethod
			){
		Map<String,Object> map = new HashMap<String, Object>();
		//请求参数有误
		if(CommonUtils.isBlank(searchLabel)||CommonUtils.isBlank(loginMethod)){
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		try {
			News news = new News();
			news.setNewsTitle(searchLabel);
			news.setPageNo(1);
			news.setPageSize(5);
			List<News> newsList = newsService.getAllNewsList(news);
			map.put("newsList", newsList);
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
