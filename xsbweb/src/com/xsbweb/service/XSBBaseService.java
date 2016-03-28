package com.xsbweb.service;

import java.util.List;
import java.util.Map;

import com.xsbweb.vo.ApplyRoad;
import com.xsbweb.vo.Comment;
import com.xsbweb.vo.CustomerFavor;
import com.xsbweb.vo.IndustryBaike;
import com.xsbweb.vo.News;
import com.xsbweb.vo.RObject;
import com.xsbweb.vo.TrsMedia;
import com.xsbweb.vo.TrsProject;
import com.xsbweb.vo.extend.EnumVO;
import com.xsbweb.vo.extend.MeetVideoRoomVO;
import com.xsbweb.vo.extend.XSBTotalVO;

public interface XSBBaseService {

	public List<EnumVO> getEnumVOList(EnumVO enumVO) throws Exception;
	
	public List<EnumVO> getEnumVOListByColumnFullName(String columnFullName) throws Exception;
	
	public List<EnumVO> getEnumVOListByNameAndCode(EnumVO enumVO) throws Exception;
	/**
	 * 根据位置获取列表集合
	 * @param group
	 * @return
	 * @throws Exception
	 */
	public List<RObject> getRObjectListByGroup(String group)throws Exception;
	
	/**
	 * 搜索返回结果集
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<RObject> getSearchListByWord(String word,int pageNo, int pageSize,String searchType)throws Exception;
	
	/**
	 * 获取注册人数和浏览量的统计信息
	 * @return
	 * @throws Exception
	 */
	public XSBTotalVO getXSBTotalOfRoadIndex()throws Exception;
	
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
	public int addComment(String objectId,String customerId,String commentContent,String type)throws Exception;
	
	
	/**
	 * 新增评论项目客户专区用
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
	public List<Comment> getCommentByTalk(Comment comment)throws Exception;
	
	/**
	 * 新增收藏
	 * @param customerFavor
	 * @return
	 * @throws Exception
	 */
	public int addCustomerFavor(CustomerFavor customerFavor)throws Exception;
	/**
	 * 获取收藏列表
	 * @param customerFavor
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getCustomerFavorList(CustomerFavor customerFavor)throws Exception;
	/**
	 * 获取新闻收藏列表
	 * @param customerFavor
	 * @return
	 * @throws Exception
	 */
	public List<News> getNewsListByFavor(CustomerFavor customerFavor)throws Exception;
	
	/**
	 * 获取项目收藏列表
	 * @param customerFavor
	 * @return
	 * @throws Exception
	 */
	public List<TrsProject> getProjectListByFavor(CustomerFavor customerFavor)throws Exception;
	
	/**
	 * 获取提醒列表
	 * @param customerFavor
	 * @return
	 * @throws Exception
	 */
	public List<MeetVideoRoomVO> getRemindListByFavor(CustomerFavor customerFavor)throws Exception;
	
	/**
	 * 删除收藏
	 * @param customerFavor
	 * @return
	 * @throws Exception
	 */
	public int deleteFavor(CustomerFavor customerFavor)throws Exception;
	
	/**
	 * 是否已收藏
	 * @param customerFavor
	 * @return
	 * @throws Exception
	 */
	public boolean isFavored(CustomerFavor customerFavor)throws Exception;
	
	/**
	 * 获取sqlserver库的统计数据
	 * @return
	 * @throws Exception
	 */
	public XSBTotalVO getSqlServerDataTotal()throws Exception;
	
	/**
	 * 申请路演
	 * @param applyRoad
	 * @return
	 * @throws Exception
	 */
	public int applyRoad(ApplyRoad applyRoad)throws Exception;
	
	/**
	 * 根据公司名称判断是否已报名路演
	 * @param companyName
	 * @return
	 * @throws Exception
	 */
	public int isApplyRoad(String companyName)throws Exception;

	/**
	 * 获取约谈列表
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	public List<RObject> getCustomerTalkList(String customerId)throws Exception;
	
	/**
	 * 批量更新资源
	 * @param trsMedias
	 * @return
	 * @throws Exception
	 */
	public int batchInsertMedias(List<TrsMedia> trsMedias)throws Exception;
}
