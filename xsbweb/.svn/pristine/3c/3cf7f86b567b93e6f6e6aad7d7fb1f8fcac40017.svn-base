package com.xsbweb.service;

import java.util.List;

import com.xsbweb.vo.Menu;
import com.xsbweb.vo.TrsMedia;
import com.xsbweb.vo.TrsProject;
import com.xsbweb.vo.extend.EnumVO;
import com.xsbweb.vo.extend.MeetVideoRoomVO;

public interface EnumService {
	/**
	 * 实现枚举业务逻辑方法
	 * @param enumVO
	 * @return
	 * @throws Exception
	 */
	/**
	 * 数据初始化
	 * @param enumVO
	 * @return
	 * @throws Exception
	 */
	public List<EnumVO> getEnumVOList(EnumVO enumVO)throws Exception;
	
	/**
	 * 数据字典模块总数
	 * @param meetVideoRoomVO
	 * @return
	 * @throws Exception
	 */
	public int getEnumCounts(EnumVO enumVO) throws Exception;
	
	/**
	 * 删除数据
	 */
	public int deleteEnum(EnumVO enumVO)throws Exception;
	
	/**
	 * 更新数据
	 */
	public int updateEnum(EnumVO enumVO)throws Exception;

	/**
	 * 模糊查询
	 */
	public List<EnumVO> fuzzySearch(int pageSize,int pageNo, String searchInformation)throws Exception;

	 /**
	  * 获取模糊查询列表总数
	  */
	public int getfuzzySearchEnumCount(String searchInformation)throws Exception;
	
	/**
	 * 根据编码数组获取集合
	 * @param enumVO
	 * @return
	 * @throws Exception
	 */
	public List<EnumVO> getEnumVOListByArr(EnumVO enumVO)throws Exception;
	
	public EnumVO getEnumByfullNameEnumCode(String enumFullName,String enumCode)throws Exception;
	
	//添加数据
	public int addEnum(EnumVO enumVO)throws Exception;
	//添加栏目时，添加enum
	public void addEnumByMenu(Menu menu) throws Exception;
	//更新栏目时，更新enum
	public void updateEnumByMenu(Menu menu) throws Exception;
	/**
	 * 获取轮播集合
	 * @param enumVO
	 * @return
	 * @throws Exception
	 */
	public List<EnumVO> getScrollList(EnumVO enumVO)throws Exception;
	/**
	 * 获取轮播集合count
	 * @param enumVO
	 * @return
	 * @throws Exception
	 */
	public int getScrollListCount(EnumVO enumVO)throws Exception;
	/**
	 * 根据获取轮播集合
	 * @param objectId
	 * @return
	 * @throws Exception
	 */
	public List<EnumVO> getScrollListByObjectId(String objectId)throws Exception;
	/**
	 * 轮播修改
	 * @param objectId
	 * @param enumDesc
	 * @return
	 * @throws Exception
	 */
	public int editScrollList(String objectId, String enumDesc)throws Exception;
	/**
	 * 项目行业enum修改
	 * @param enumDesc1
	 * @param enumDesc2
	 * @param code1
	 * @param code2
	 * @throws Exception
	 */
	public int bathUpdateEnum(List<EnumVO>enumVOList)throws Exception;
	/**
	 * 批量删除ProjectEnums
	 * @param enumDescArrs
	 * @return
	 * @throws Exception
	 */
	public int batchDeleteProjectEnums(String[] enumDescArrs)throws Exception;
}
