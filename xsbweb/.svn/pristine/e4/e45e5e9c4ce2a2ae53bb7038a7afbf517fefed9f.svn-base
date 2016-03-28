package com.xsbweb.mapper;

import com.xsbweb.vo.TrsExeLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TrsExeLogMapper {


    int deleteByPrimaryKey(TrsExeLog record);

    int insert(TrsExeLog record);

    int insertSelective(TrsExeLog record);

    TrsExeLog selectByPrimaryKey(String taskId);

    int updateByPrimaryKeySelective(TrsExeLog record);

    int updateByPrimaryKey(TrsExeLog record);
    
    public List<TrsExeLog> getTrsExeLogList(TrsExeLog trsExeLogVO);
}