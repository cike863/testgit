package com.xsbweb.mapper;

import com.xsbweb.vo.SnapEtlSchedule;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SnapEtlScheduleMapper {


    int deleteByPrimaryKey(SnapEtlSchedule etlScheduleVO);

    int insert(SnapEtlSchedule record);

    int insertSelective(SnapEtlSchedule record);


    SnapEtlSchedule selectByPrimaryKey(String taskTable);


    int updateByPrimaryKeySelective(SnapEtlSchedule record);

    int updateByPrimaryKey(SnapEtlSchedule record);
    
	public List<SnapEtlSchedule> getEtlScheduleList(SnapEtlSchedule etlScheduleVO);

	int getEtlScheduleCount(SnapEtlSchedule snapEtlSchedule);
}