package com.xsbweb.mapper;

import java.util.List;
import java.util.Map;

import com.xsbweb.vo.News;
import com.xsbweb.vo.SearchWord;

public interface NewsMapper {

	public News getNewsContentByNewsNo(String newsNo)throws Exception;
	
	public List<News> getNewsIndexListByPrc(Map<String, Object> param)throws Exception;
	
	public List<News> getAllNewsList(News news)throws Exception;
	
	public int insertNewsDtl(News news)throws Exception;
	
	public int updateNewsDtl(News news)throws Exception;

	public int insertNewsIdx(News news)throws Exception;
	
	public int updateNewsIdx(News news)throws Exception;
	
	public int deleteNewsIdx(String newsNo)throws Exception;
	
	public int deleteNewsDtl(String newsNo)throws Exception;
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
	 * 批量插入关键词
	 * @param words
	 * @return
	 * @throws Exception
	 */
	public int batchAddWord (List<SearchWord> words)throws Exception;
	
	/**
	 * 通过newsNo获取相关关键词
	 * @param newsNo
	 * @return
	 * @throws Exception
	 */
	public List<SearchWord>getWordListByNewsNo(String newsNo) throws Exception;
	/**
	 * 批量更新关键词
	 * @param words
	 * @return
	 * @throws Exception
	 */
	public int batchUpdateWord(List<SearchWord>  word)throws Exception;
	/**
	 * 通过object以及word删除关键词
	 * @param word
	 * @return
	 * @throws Exception
	 */
	public void deleteWordByObjectAndWord(SearchWord word)throws Exception;
	/**
	 * 通过newsNo批量删除关键词
	 * @param newsNo
	 * @return
	 * @throws Exception
	 */
	public int deleteWordByObject(String newsNo) throws Exception;
	/**
	 * 通过newsNoArrs批量删除词条
	 * @param newsNoArrs
	 * @throws Exception
	 */
	public void deleteWordByObjects(String[] newsNoArrs) throws Exception;
	/**
	 * 批量删除新闻
	 * @param newsNoArrs
	 * @throws Exception
	 */
	public void deleteNewsDtlByNewsNos(String[] newsNoArrs) throws Exception;
	/**
	 * 批量删除新闻
	 * @param newsNoArrs
	 * @throws Exception
	 */
	public void deleteNewsIdxByNewsNos(String[] newsNoArrs) throws Exception;
	/**
	 * 新增新闻（采用存储过程）
	 * @param news
	 * @throws Exception
	 */
	public void insertNews(News news)throws Exception;;
}
