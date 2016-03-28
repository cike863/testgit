package com.xsbweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xsbweb.common.bean.DataBaseConstant;
import com.xsbweb.mapper.RoadShowMapper;
import com.xsbweb.util.MultipleDataSource;
import com.xsbweb.vo.TrsProject;
import com.xsbweb.vo.extend.RoadShowVO;



public class RoadShowServiceImp {
	/**
	 * 获取所有数据
	 */
	@Autowired
	private RoadShowMapper roadShowMapper;
	public List<RoadShowVO> queryRoadShowInfo(RoadShowVO roadShowVO){
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		List<RoadShowVO> roadShowsList = roadShowMapper.queryRoadShowInfo(roadShowVO); 
		return roadShowsList;
	}
	
	/**
	 * 获取总数
	 * @param roadShowVO
	 * @return
	 */
	public int TotalRecordRoadShowCount(RoadShowVO roadShowVO){
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		int rsCount = roadShowMapper.TotalRecordRoadShowCount(roadShowVO);
		return rsCount;
	}
	

}