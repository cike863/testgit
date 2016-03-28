package com.xsbweb.mapper;

import com.xsbweb.vo.SnapPrcSchedule;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SnapPrcScheduleMapper {


    int deleteByPrimaryKey(SnapPrcSchedule record);

    int insert(SnapPrcSchedule record);

    int insertSelective(SnapPrcSchedule record);


    SnapPrcSchedule selectByPrimaryKey(String taskPrc);



    int updateByPrimaryKeySelective(SnapPrcSchedule record);

    int updateByPrimaryKey(SnapPrcSchedule record);
    
    public List<SnapPrcSchedule> getPrcScheduleList(SnapPrcSchedule prcScheduleVO);
}