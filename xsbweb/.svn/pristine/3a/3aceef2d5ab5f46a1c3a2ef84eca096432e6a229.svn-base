package com.xsbweb.mapper;

import java.util.List;
import java.util.Map;

import com.xsbweb.vo.Comment;
import com.xsbweb.vo.Customer;
import com.xsbweb.vo.CustomerFavor;
import com.xsbweb.vo.IndustryBaike;
import com.xsbweb.vo.ProjectItem;
import com.xsbweb.vo.RObject;
import com.xsbweb.vo.extend.EnumVO;

public interface CommonMapper {
	
	/**
	 * 获取知识库集合
	 * @param industryBaike
	 * @return
	 * @throws Exception
	 */
	public List<IndustryBaike> getIndustryBaikeList(IndustryBaike industryBaike)throws Exception;
	/**
	 * 根据id获取知识库信息
	 * @param baikeId
	 * @return
	 * @throws Exception
	 */
	public IndustryBaike getIndustryBaikeById(String baikeId)throws Exception;
	
	/**
	 * 新增评论
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	public int addComment(Comment comment)throws Exception;
	
	/**
	 * 获取评论
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	public List<Comment> getCommentByAddress(Comment comment)throws Exception;
	
	/**
	 * 获取约谈
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	public List<Comment> getCommentByTalk(Comment comment)throws Exception;;
	
	/**
	 * 新增收藏
	 * @param customerFavor
	 * @return
	 * @throws Exception
	 */
	public int addCustomerFavor(CustomerFavor customerFavor)throws Exception;
	
	//public List<CustomerFavor> get(CustomerFavor customerFavor)throws Exception;
	/**
	 * 批量新增items
	 * @param items
	 * @return
	 * @throws Exception
	 */
	public int addItemsBatch(List<ProjectItem> itemList)throws Exception;
	
	/**
	 * 获取约谈列表
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	public List<RObject> getCustomerTalkList(String customerId)throws Exception;
	
	public List<String> getObjectListByWord(Map<String,Object> param)throws Exception;
}
