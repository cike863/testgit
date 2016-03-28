package com.xsbweb.mapper;

import java.util.List;
import java.util.Map;









import com.xsbweb.vo.Menu;
import com.xsbweb.vo.TrsMedia;
import com.xsbweb.vo.TrsProject;
import com.xsbweb.vo.extend.EnumVO;

public interface EnumVOMapper {

	/**
	 * 查询所有数据
	 * @param enumVO
	 * @return
	 * @throws Exception
	 */
	public List<EnumVO> getEnumVOList(EnumVO enumVO) throws Exception;
	/**
	 * 根据编码数组获取集合
	 * @param enumVO
	 * @return
	 * @throws Exception
	 */
	public List<EnumVO> getEnumVOListByArr(EnumVO enumVO)throws Exception;
	
	public List<EnumVO> getEnumVOListByColumnFullName(String columnFullName) throws Exception;
	/**
	 * getEnumVOListByColumnFullNameAndGroupCode
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<EnumVO>getEnumVOListByNameAndCode(Map<String,Object> param)throws Exception;
	/**
	 * 获取数据字典模块总数
	 * @param meetVideoRoomVO
	 * @return
	 * @throws Exception
	 */
	public int getEnumCounts(EnumVO enumVO) throws Exception;
	
	/**
	 * 根据enum_full_name,enum_code两个条件进行删除
	 * @param enumFullName
	 * @param enumCode
	 * @return
	 * @throws Exception
	 */
	public int deleteEnum(EnumVO enumVO)throws Exception;
			   
	/**
	 * 更新
	 * @param enumFullName
	 * @param enumCode
	 * @return
	 * @throws Exception
	 */		   
	public int updateEnum(EnumVO enumVO)throws Exception;
	
	/**
	 * 模糊查询,获取列表集合;
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<EnumVO> fuzzySearchEnum(Map<String, Object> param)throws Exception;
	
	/**
	 * 获取模糊查询,所有信息列表,集合总数;
	 * @param information
	 * @return
	 * @throws Exception
	 */
	public int getfuzzySearchEnumCount(String information)throws Exception;
	
	//查询更新条件
	public EnumVO getEnumByfullNameEnumCode(Map<String, Object>param)throws Exception;
	
	//添加
	public int addEnum(EnumVO enumVO)throws Exception;
	//Menu添加/修改数据时，添加enum
	public void updateEnumByMenu(EnumVO enumVO)throws Exception;
	
	/**
	 *获取未使用的role code值
	 * @return
	 * @throws Exception
	 */
	public String selectEnumCode(String enumFullName)throws Exception;
	
	public int getScrollListCount(EnumVO enumVO)throws Exception;
	
	public List<EnumVO> getScrollList(EnumVO enumVO)throws Exception;
	
	public List<EnumVO>getScrollListByObjectId(String objectId)throws Exception;
	
	public List<EnumVO>getScrollTypeList()throws Exception;
	
	public void editScrollList(Map<String,Object> param)throws Exception;
	
	public List<EnumVO> getProjectEnumVOList()throws Exception;
	
	public int bathUpdateEnum(List<EnumVO> enums)throws Exception;
	
	public List<String> getEnumDescListByRoleAndFullName(Map<String,Object> param)throws Exception;
	public int batchDeleteProjectEnums(String[] enumDescArrs)throws Exception;
}
