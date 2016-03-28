package com.xsbweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xsbweb.common.bean.DataBaseConstant;
import com.xsbweb.mapper.EnumVOMapper;
import com.xsbweb.service.EnumService;
import com.xsbweb.util.MultipleDataSource;
import com.xsbweb.vo.Menu;
import com.xsbweb.vo.News;
import com.xsbweb.vo.TrsMedia;
import com.xsbweb.vo.TrsProject;
import com.xsbweb.vo.extend.EnumVO;
import com.xsbweb.vo.extend.MeetVideoRoomVO;

public class EnumServiceImpl implements EnumService {
	@Autowired
	EnumVOMapper enuMapper;
	
	
	@Override
	public List<EnumVO> getEnumVOList(EnumVO enumVO) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.META);
		if("industry".equals(enumVO.getEnumFullName())){
			enumVO.setPageSize(20);
		}
		return enuMapper.getEnumVOList(enumVO);
	}
	/**
	 * 获取总数
	 */
	@Override
	public int getEnumCounts(EnumVO enumVO) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.META);
		return enuMapper.getEnumCounts(enumVO);
	}
	
	/**
	 * 删除数据
	 */
	@Override
	public int deleteEnum(EnumVO enumVO)throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.META);
		return enuMapper.deleteEnum(enumVO);
	}
	
	/**
	 * 更新数据
	 */
	@Override
	public int updateEnum(EnumVO enumVO) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.META);
		return enuMapper.updateEnum(enumVO);
	}
	
	/**
	 * 模糊查询
	 */
	@Override
	public List<EnumVO> fuzzySearch(int pageSize, int pageNo,
			String searchInformation)throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.META);
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("currentRecord",(pageNo==0?1:pageNo -1)*pageSize);
		param.put("pageSize",pageSize==0?10:pageSize);
		param.put("searchInformation",searchInformation);
		List<EnumVO> enumVOList = enuMapper.fuzzySearchEnum(param);
		return enumVOList;
	}
	
	/**
	 * 获取模糊查询的列表总数
	 */
	@Override
	public int getfuzzySearchEnumCount(String searchInformation)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.META);
		int totalRecordEnumCount=enuMapper.getfuzzySearchEnumCount(searchInformation);
		return totalRecordEnumCount;
	}
	/**
	 * 根据编码数组获取集合
	 * @param enumVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<EnumVO> getEnumVOListByArr(EnumVO enumVO) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return enuMapper.getEnumVOListByArr(enumVO);
	}
	@Override
	public EnumVO getEnumByfullNameEnumCode(String enumFullName, String enumCode)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.META);
		Map<String, Object> temp =new HashMap<String,Object>();
		temp.put("enumFullName", enumFullName);
		temp.put("enumCode", enumCode);
		return enuMapper.getEnumByfullNameEnumCode(temp);
	}
	@Override
	public int addEnum(EnumVO enumVO) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.META);
		if(enumVO!=null&&"industry".equals(enumVO.getEnumFullName())){
			String enumCode = enuMapper.selectEnumCode("industry");
			enumVO.setEnumCode(enumCode);
			return enuMapper.updateEnum(enumVO);
			//return enuMapper.addEnum(enumVO);
    	}else{
    		return enuMapper.addEnum(enumVO);
    	}
	}
	@Override
	public void addEnumByMenu(Menu menu) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.META);
		EnumVO enumVO = new EnumVO();
		enumVO.setEnumDesc(menu.getItemId());
		enumVO.setEnumDescCn(menu.getItemText());
		enumVO.setEnumGroupCode("system");		
		String enumCode = enuMapper.selectEnumCode("role");
		enumVO.setEnumCode(enumCode);
		enuMapper.updateEnumByMenu(enumVO);
	}
	@Override
	public void updateEnumByMenu(Menu menu) throws Exception {
		//MultipleDataSource.setDataSourceKey(DataBaseConstant.META);		
		EnumVO enumVO = new EnumVO();
		enumVO.setEnumDesc(menu.getItemId());
		enumVO.setEnumFullName("role");
		enumVO.setEnumGroupCode("system");
		List<EnumVO>enums =enuMapper.getEnumVOList(enumVO);
		enumVO.setEnumCode(enums.get(0).getEnumCode());
		enumVO.setEnumDescCn(menu.getItemText());
		enumVO.setCreateDate(enums.get(0).getCreateDate());
		enuMapper.updateEnumByMenu(enumVO);
	}
	@Override
	public List<EnumVO> getScrollList(EnumVO enumVO) throws Exception {
		return enuMapper.getScrollList(enumVO);
	}
	@Override
	public int getScrollListCount(EnumVO enumVO) throws Exception {
		/*MultipleDataSource.setDataSourceKey(DataBaseConstant.META);*/
		return enuMapper.getScrollListCount(enumVO);
	}
	@Override
	public List<EnumVO> getScrollListByObjectId(String objectId)
			throws Exception {		
		return enuMapper.getScrollListByObjectId(objectId);
	}
	@Override
	public int editScrollList(String objectId, String enumDesc)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Map<String,Object>map = new HashMap<String,Object>();
		map.put("objectId", objectId);
		map.put("enumDesc", enumDesc);
		map.put("prcFlag",0);
		enuMapper.editScrollList(map);
		return (int) map.get("prcFlag");
	}
	@Override
	public int bathUpdateEnum(List<EnumVO>enumVOList) throws Exception {
		for(int i=0;i<enumVOList.size();i++){
			enumVOList.get(i).setEnumFullName("industry");
		}
		int flag = 0;
		try{
			flag =enuMapper.bathUpdateEnum(enumVOList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public int batchDeleteProjectEnums(String[] enumDescArrs) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.META);
		int flag = 0;
		try{
			flag = enuMapper.batchDeleteProjectEnums(enumDescArrs);
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
}
