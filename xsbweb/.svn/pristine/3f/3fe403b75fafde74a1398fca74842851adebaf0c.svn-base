package com.xsbweb.service;

import java.util.List;

import com.xsbweb.vo.News;
import com.xsbweb.vo.RObject;
import com.xsbweb.vo.SearchWord;
import com.xsbweb.vo.TrsMedia;
import com.xsbweb.vo.extend.NewsVO;

public interface NewsService {

	public News getNewsContentByNewsNo(String newsNo)throws Exception;
	public List<SearchWord> getSearchWordListByNewsNo(String newsNo) throws Exception;
	
	public NewsVO getNewsIndexListByPrc(String newsConfig)throws Exception;
	
	public List<News> getAllNewsList(News news)throws Exception;
	
	public int addNews(News news)throws Exception;
	
	public int editNews(News news)throws Exception;
	
	public int deleteNews(String newsDtlId)throws Exception;
	
	/**
	 * 新闻置顶
	 * @param newsNo
	 * @return
	 * @throws Exception
	 */
	public int newsToTop(String newsNo)throws Exception;
	/**
	 * 新闻取消置顶
	 * @param newsNo
	 * @return
	 * @throws Exception
	 */
	public int newsCancelTop(String newsNo)throws Exception;
	
	/**
	 * 获取轮播图
	 * @return
	 * @throws Exception
	 */
	public List<RObject> getNewsScrollList()throws Exception;
	
	/**
	 * 根据类型获取新闻列表集合
	 * @return
	 * @throws Exception
	 */
	public List<RObject> getNewsIndexListByType(RObject rObject)throws Exception;
	
	/**
	 * 获取新闻总数
	 * @param news
	 * @return
	 * @throws Exception
	 */
	public int getNewsCounts(News news)throws Exception;
	
	/**
	 * 获取相关新闻
	 * @param news
	 * @return
	 * @throws Exception
	 */
	public List<News> getRelatedReadList(News news)throws Exception;

	/**
	 * 点赞
	 * @param commentNo
	 * @return
	 * @throws Exception
	 */
	public int praiseComment(String commentNo)throws Exception;
	/**
	 * 
	 * @param news
	 * @return
	 * @throws Exception
	 */
	public int editWords(News news)throws Exception;
	
	public int addWords(List<SearchWord> words,String newsNo) throws Exception;
	/**
	 * 页面删除word
	 * @param word
	 * @throws Exception
	 */
	public void deleteWordByObjectAndWord(SearchWord word) throws Exception;
	public int deleteWordByObject(String newsNo) throws Exception;
	/**
	 * 添加Media
	 * @param trsMediaList
	 * @return
	 * @throws Exception
	 */
	public int addTrsMedia(List<TrsMedia> trsMediaList)throws Exception;
	/**
	 * 批量删除新闻
	 * @param newsNoArrs
	 * @return
	 * @throws Exception
	 */
	public int batchDeleteNews(String[] newsNoArrs)throws Exception;
	
}
