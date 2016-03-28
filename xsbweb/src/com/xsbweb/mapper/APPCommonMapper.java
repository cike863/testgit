package com.xsbweb.mapper;

import java.util.List;
import java.util.Map;

import com.xsbweb.vo.ApplyRoad;
import com.xsbweb.vo.CustomerFavor;
import com.xsbweb.vo.News;
import com.xsbweb.vo.RObject;
import com.xsbweb.vo.TrsProject;
import com.xsbweb.vo.extend.MeetVideoRoomVO;
import com.xsbweb.vo.extend.SbhqVO;
import com.xsbweb.vo.extend.XSBTotalVO;

public interface APPCommonMapper {

	public List<RObject> getRObjectListByGroup(Map<String, Object> param)throws Exception;
	
	/**
	 * 搜索返回结果集
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<RObject> getSearchListByWord(Map<String, Object> param)throws Exception;
	
	public XSBTotalVO getXSBTotalOfRoadIndex()throws Exception;
	
	/**
	 * 新增收藏
	 * @param customerFavor
	 * @return
	 * @throws Exception
	 */
	public int addCustomerFavor(CustomerFavor customerFavor)throws Exception;
	/**
	 * 获取新闻收藏列表
	 * @param customerFavor
	 * @return
	 * @throws Exception
	 */
	public List<News> getNewsListByFavor(CustomerFavor customerFavor)throws Exception;
	
	/**
	 * 是否已收藏
	 * @param customerFavor
	 * @return
	 * @throws Exception
	 */
	public int isFavored(CustomerFavor customerFavor)throws Exception;
	
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
	 * 获取sqlserver库的统计数据
	 * @return
	 * @throws Exception
	 */
	public XSBTotalVO getSqlServerDataTotal()throws Exception;
	
	/**
	 * 根据公司名称判断是否已报名路演
	 * @param companyName
	 * @return
	 * @throws Exception
	 */
	public int isApplyRoad(String companyName)throws Exception;
	
	/**
	 * 申请路演报名
	 * @param companyName
	 * @return
	 * @throws Exception
	 */
	public int applyRoad(ApplyRoad applyRoad)throws Exception;

}
