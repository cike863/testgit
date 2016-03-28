package com.xsbweb.controller.manage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.common.bean.DataBaseConstant;
import com.xsbweb.common.bean.ResultCode;
import com.xsbweb.common.bean.XsbBusinessConstant;
import com.xsbweb.controller.app.AppLoginRegistController;
import com.xsbweb.service.ConfigService;
import com.xsbweb.service.NewsService;
import com.xsbweb.service.XSBBaseService;
import com.xsbweb.util.BaseUtil;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.util.MathUtil;
import com.xsbweb.util.MultipleDataSource;
import com.xsbweb.util.RedisUtil;
import com.xsbweb.vo.News;
import com.xsbweb.vo.SearchWord;
import com.xsbweb.vo.TrsMedia;
import com.xsbweb.vo.extend.EnumVO;

@Controller
public class TrsNewsController {

	private Logger log = Logger.getLogger(TrsNewsController.class);
    @Autowired
	private ConfigService configService;  
    
    @Autowired
    private NewsService newsService;
    
    @Autowired
    private XSBBaseService xsbBaseService;
    
    private RedisUtil redisUtil = new RedisUtil();
    
    @RequestMapping(value="/admin/news/toAdminNews.do")
    public ModelAndView toAdminNews(News news )throws Exception{

    	ModelAndView mav = new ModelAndView();
    	//NewsVO newsVO =  new NewsVO();
    	//获取总数
    	int totalCounts = newsService.getNewsCounts(news);
    	List<News> newsList = newsService.getAllNewsList(news);
    	EnumVO enums = new EnumVO();
    	enums.setEnumFullName("role");
    	enums.setEnumGroupCode("news");
    	List<EnumVO> newsTypeEnumList = xsbBaseService.getEnumVOListByNameAndCode(enums);
    	for(News news1 :newsList){
    		news1.setNewsType(toNewTypes(news1.getNewsRole(),newsTypeEnumList));
    	}
    	news.setTotalRecord(totalCounts);
    	//newsVO.setNewsList(newsList);
    	//newsVO.setNews(news);
    	mav.setViewName("manage/news/queryTrsNewsList");
    	mav.addObject("newsList",newsList);
    	mav.addObject("news",news);
    	return mav;
    }
    
    @RequestMapping(value="/admin/news/toAddNews.do")
    public ModelAndView toAddNews(EnumVO enums,News news)throws Exception{
    	ModelAndView mav = new ModelAndView();
    	//News news = new News();
    	//设置Enums中的enumFullName以及enumGroupCode的值，后期前台传入
    	enums.setEnumFullName("role");
    	enums.setEnumGroupCode("news");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	news.setNewsDate(sdf.format(new Date()).toString());
    	news.setFakeCounts((int)(Math.random()*500)+"");
    	//List<EnumVO> newsTypeEnumList = xsbBaseService.getEnumVOListByColumnFullName(XsbBusinessConstant.NEWS_TYPE_ENUM);
    	List<EnumVO> newsTypeEnumList = xsbBaseService.getEnumVOListByNameAndCode(enums);
    	news.setNewsTypeEnumList(newsTypeEnumList);
    	mav.addObject("news",news);
    	mav.setViewName("manage/news/addTrsNews");
    	return mav;
    }
    
    /**
     * 新增新闻
     * @param news
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/news/addNews.do")
    public ModelAndView addNews(News news,HttpServletRequest request)throws Exception{
    	//分别从session中取projListMediaNo以及projShareMediaNo
    			ModelAndView mav = new ModelAndView();
    			/*TrsMedia newsMediaNo = (TrsMedia) request.getSession().getAttribute("newsMediaNo");
    			TrsMedia newsShareMediaNo = (TrsMedia)  request.getSession().getAttribute("newsShareMediaNo");
    			TrsMedia newsBigMediaNo = (TrsMedia)  request.getSession().getAttribute("newsBigMediaNo");
    			request.getSession().removeAttribute("newsMediaNo");
    			request.getSession().removeAttribute("newsBigMediaNo");
    			request.getSession().removeAttribute("newsBigMediaNo");
    			if(newsMediaNo!=null){		
    				news.getTrsMediaList().add(newsMediaNo);			
    			}
    			if(newsShareMediaNo!=null){
    				news.getTrsMediaList().add(newsShareMediaNo);
    			}
    			if(newsBigMediaNo!=null){
    				news.getTrsMediaList().add(newsBigMediaNo);
    			}*/
    	MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
    	news.setCreateBy(BaseUtil.getCookieValue("customerId", request));
    	//news图片路径替换
    	if(news.getNewsContent().contains((XsbBusinessConstant.DYLY_URL+"/"))){
    		news.setNewsContent(news.getNewsContent().replaceAll((XsbBusinessConstant.DYLY_URL+"/"),"/dyly/"));
    	}
    	int result = newsService.addNews(news);
    	
    	if(news.getSearchWord()!=null&&!news.getSearchWord().isEmpty()&&!"".equals(news.getSearchWord().get(0).getWord())){
    	/*	MultipleDataSource.clearDataSourceType();
    		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);*/
    		result*=newsService.addWords(news.getSearchWord(), news.getNewsNo());
    	}
    	/*if(news.getTrsMediaList()!=null&&!news.getTrsMediaList().isEmpty()){
    		MultipleDataSource.clearDataSourceType();
    		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
    		result *= newsService.addTrsMedia(news.getTrsMediaList());
		}*/
    	if(result>0){
    		if(news.getProjectNo()!=null&&!"".equals(news.getProjectNo())){
    			mav.setViewName("redirect:/admin/news/toAdminNews.do?projectNo="+news.getProjectNo());
    		}else{
    			mav.setViewName("redirect:/admin/news/toAdminNews.do");
    		}
    		
    	}else{
    		mav.setViewName("manage/news/addTrsNews");
    		mav.addObject("news", news);
    	}
    	return mav;
    }
    
    /**
     * 跳转到新闻编辑页面
     * @param newsNo
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/news/toEditNews.do")
    public ModelAndView toEditNews(String newsNo,String projectNo,EnumVO enums)throws Exception{
    	ModelAndView mav = new ModelAndView();
    	if(CommonUtils.isBlank(newsNo)){
    		mav.addObject("error","新闻newsNo缺失！");
    		log.info("####################新闻newsNo缺失！");
    		mav.setViewName("manage/news/editTrsNews");
    		return mav;
    	}
    	
    	//设置Enums中的enumFullName以及enumGroupCode的值，后期前台传入
    	enums.setEnumFullName("role");
    	enums.setEnumGroupCode("news");
    	//MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
    	News news = newsService.getNewsContentByNewsNo(newsNo);
    	news.setProjectNo(projectNo);
    	//new图片途径替换
    	news.setNewsContent((news.getNewsContent().replaceAll("/dyly/",(XsbBusinessConstant.DYLY_URL+"/"))));
    	/*if(news.getNewsContent().contains("&amp;emsp;")){
    		news.setNewsContent((news.getNewsContent().replaceAll("&amp;emsp;","&nbsp;")));
		}*/
    	List<SearchWord> words =newsService.getSearchWordListByNewsNo(newsNo);
    	if(words!=null&&!words.isEmpty()){
			news.setSearchWord(words);
		}
    	//新闻类型
    	//List<EnumVO> newsTypeEnumList = xsbBaseService.getEnumVOListByColumnFullName(XsbBusinessConstant.NEWS_TYPE_ENUM);    	
    	if(news!=null){
    		List<EnumVO> newsTypeEnumList = xsbBaseService.getEnumVOListByNameAndCode(enums);
    		news.setNewsTypeEnumList(newsTypeEnumList);
    	}
    	if(news.getNewsRole()!=null){
    		List<Long> checkedCode =MathUtil.Fun(Long.parseLong(news.getNewsRole()));
    		mav.addObject("checkedCode",checkedCode);
    	}
    	mav.setViewName("manage/news/editTrsNews");
    	mav.addObject("news",news);
    	return mav;
    }
    
    /**
     * 编辑新闻
     * @param news
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/news/editNews.do")
    public String editNews(News news,ModelMap mode,HttpServletRequest request)throws Exception{
    	if(news==null || CommonUtils.isBlank(news.getNewsNo())){
    		mode.addAttribute("news", news);
    		mode.addAttribute("error","新闻news_no缺失！");
    		log.info("####################新闻news_no缺失！");
    		return "manage/news/editTrsNews";
    	}
    	//编辑
    	//分别从session中取projListMediaNo以及projShareMediaNo
		/*TrsMedia newsMediaNo = (TrsMedia) request.getSession().getAttribute("newsMediaNo");
		TrsMedia newsShareMediaNo = (TrsMedia)  request.getSession().getAttribute("newsShareMediaNo");
		TrsMedia newsBigMediaNo = (TrsMedia)  request.getSession().getAttribute("newsBigMediaNo");
		request.getSession().removeAttribute("newsMediaNo");
		request.getSession().removeAttribute("newsBigMediaNo");
		request.getSession().removeAttribute("newsBigMediaNo");
		if(newsMediaNo!=null){		
			news.getTrsMediaList().add(newsMediaNo);			
		}
		if(newsShareMediaNo!=null){
			news.getTrsMediaList().add(newsShareMediaNo);
		}
		if(newsBigMediaNo!=null){
			news.getTrsMediaList().add(newsBigMediaNo);
		}*/
		
    	MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
    	if(news.getNewsRole().contains(",")){
    		String [] resultRole = news.getNewsRole().split(",");
        	long output = 0;
        	for(int i=0;i<resultRole.length;i++){
        		output+=Long.parseLong(resultRole[i]);
        	}	    	
        	news.setNewsRole(output+"");
    	}
    	/*if(news.getNewsDate()!=null &&!"".equals(news.getNewsDate())&&news.getNewsDate().length()>=10){
    		String date=news.getNewsDate();
    		news.setNewsDate(date.substring(8, 10));
    		news.setNewsMonth(date.substring(5, 7));
        	news.setNewsYear(date.substring(0, 4));
    	}*/
    	String [] oldWordListword=request.getParameterValues("oldWordListword");
    	String [] Listword=request.getParameterValues("Listword");
    	if(Listword!=null&&Listword.length>0){
    		for(int i=0;i<Listword.length;i++){
    			SearchWord oldWord = new SearchWord();
    			oldWord.setWord(Listword[i]);
    			oldWord.setOldWord(oldWordListword[i]);
    			oldWord.setObject(news.getNewsNo());
    			news.getOldWordList().add(oldWord);
    		}
    	}
    	if(news.getNewsContent().contains((XsbBusinessConstant.DYLY_URL+"/"))){
    		news.setNewsContent(news.getNewsContent().replaceAll((XsbBusinessConstant.DYLY_URL+"/"),"/dyly/"));
    	}
    	/*if(news.getNewsContent().contains("&amp;emsp;")){
    		news.setNewsContent((news.getNewsContent().replaceAll("&amp;emsp;","&nbsp;")));
		}*/
    	news.setLastUpdateBy(BaseUtil.getCookieValue("customerId", request));
    	int flag = newsService.editNews(news);
    	MultipleDataSource.clearDataSourceType();
    	MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
    	flag*=newsService.editWords(news);
    	/*if(news.getTrsMediaList()!=null&&!news.getTrsMediaList().isEmpty()){
    		flag *= newsService.addTrsMedia(news.getTrsMediaList());
		}*/
    	if(flag>0){
    		if(news.getProjectNo()!=null&&!"".equals(news.getProjectNo())){
    			return "redirect:/admin/news/toAdminNews.do?projectNo="+news.getProjectNo();
    		}else{
    			return "redirect:/admin/news/toAdminNews.do";
    		}
    		
    	}else{
    		mode.addAttribute("news", news);
    		mode.addAttribute("error","修改失败！");
    		return "manage/news/editTrsNews";
    	}
    }
    
    /**
     * 删除
     * @param news
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/news/deleteNews.do",method=RequestMethod.GET)
    public String deleteNews(String newsNo,ModelMap mode,News news)throws Exception{
    	if(CommonUtils.isBlank(newsNo)){
    		mode.addAttribute("error","新闻newsNo缺失！");
    		log.info("####################新闻newsNo缺失！");
    		return "manage/news/queryTrsNewsList";
    	}
    	
    	MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
    	int flag = newsService.deleteNews(newsNo);
    	
    	if(flag==1){
    		MultipleDataSource.clearDataSourceType();
        	MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
        	newsService.deleteWordByObject(newsNo);
        	if(news.getProjectNo()!=null&&!"".equals(news.getProjectNo())){
    			return "redirect:/admin/news/toAdminNews.do?projectNo="+news.getProjectNo();
    		}else{
    			return "redirect:/admin/news/toAdminNews.do";
    		}
    	}else{
    		mode.addAttribute("error","删除失败");
    		return "manage/news/queryTrsNewsList";
    	}
    }
    
    /**
     * 置顶操作
     * @param news
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/news/newsToTop.do",method=RequestMethod.GET)
    public ModelAndView newsToTop(String newsNo,String projectNo)throws Exception{
    	ModelAndView mav = new ModelAndView();
    	//置顶操作
    	MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
    	int flag = newsService.newsToTop(newsNo);
    	if(flag>0){
    		if(projectNo!=null&&!"".equals(projectNo)){
    			mav.setViewName( "redirect:/admin/news/toAdminNews.do?projectNo="+projectNo);
    		}else{
    			mav.setViewName( "redirect:/admin/news/toAdminNews.do");
    		}
    		/*List<News> newsList = newsService.getAllNewsList(new News());
    		mav.addObject("newsList",newsList);*/
    	}else{
    		mav.addObject("error","置顶操作失败！");
    	}
    	/*mav.setViewName("manage/news/queryTrsNewsList");*/
    	return mav;
    }
    
    /**
     * 取消置顶操作
     * @param news
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/news/newsCancelTop.do",method=RequestMethod.GET)
    public ModelAndView newsCancelTop(String newsNo)throws Exception{
    	ModelAndView mav = new ModelAndView();
    	//置顶操作
    	MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
    	int flag = newsService.newsCancelTop(newsNo);
    	if(flag>0){
    		/*List<News> newsList = newsService.getAllNewsList(new News());
    		mav.addObject("newsList",newsList);*/
    	}else{
    		mav.addObject("error","取消置顶操作失败！");
    	}
    	/*mav.setViewName("manage/news/queryTrsNewsList");*/
    	return mav;
    }
    /**
     * 词条删除
     * @param newsNo
     * @param word
     * @return
     */
    @RequestMapping(value="/admin/news/deleteWord")
    @ResponseBody
    public Map<String,Integer> deleteWord(@RequestParam("newsNo") String newsNo,@RequestParam("word") String word){
    	Map<String,Integer> map = new HashMap<String,Integer>();
    	SearchWord words = new SearchWord();
    	words.setObject(newsNo);
    	words.setWord(word);
		try{
			MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
			newsService.deleteWordByObjectAndWord(words);
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
		}catch(Exception e){
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		}
		return map;
    }
    
    /**
     * 批量删除新闻
     * @param news
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/news/{newsNoArrs}/del",method=RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> batchDeleteNews(
    		@PathVariable("newsNoArrs") String[] newsNoArrs,
    		@RequestParam(value="projectNo",required=false)String projectNo)throws Exception{
    	/*if(CommonUtils.isBlank(newsNoArrs)){
    		mode.addAttribute("error","新闻newsNo缺失！");
    		log.info("####################新闻newsNo缺失！");
    		return "manage/news/queryTrsNewsList";
    	}*/
    	Map<String,Object> map = new HashMap<String,Object>();
    	MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
    	int flag = newsService.batchDeleteNews(newsNoArrs);
    	if(flag>0){
    		map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
        	/*if(projectNo!=null&&!"".equals(projectNo)){
    			return "redirect:/admin/news/toAdminNews.do?projectNo="+projectNo;
    		}else{
    			return "redirect:/admin/news/toAdminNews.do";
    		}*/
    	}else{
    		map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
    	}
    	return map;
    }
    /**
     * 关注行业回显
     * @param gzIndustry
     * @return
     */
    public static String toNewTypes(String newTypes,List<EnumVO> newTypesEnumList){
    	StringBuffer newType = new StringBuffer();
    	newType.append("");
    	if(newTypes==null||"".equals(newTypes)||"null".equals(newTypes)||"0".equals(newTypes)){
    		return "";
    	}
    	List<Long> arry = MathUtil.Fun(Long.parseLong(newTypes));
    	for(int i=0;i<arry.size();i++){
    		for(EnumVO enumVO:newTypesEnumList){
    			if(arry.get(i)==Integer.parseInt(enumVO.getEnumCode()) ){
    				newType.append(enumVO.getEnumDescCn()+",");
    			}
    		}
    	}
    	if("".equals(newType)){
    		return newTypes;
    	}else{
    		return newType.substring(0, newType.length()-1).toString();
    	}   	
    }
}
