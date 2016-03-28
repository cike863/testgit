package com.xsbweb.mapper;

import com.xsbweb.vo.TrsMedia;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TrsMediaMapper {

    int deleteByPrimaryKey(TrsMedia record);

    int insert(TrsMedia record);
    
    int batchInsert(List<TrsMedia> trsMedias);

    int insertSelective(TrsMedia record);

    List<TrsMedia> selectByPrimaryKey(String mediaNo);

    int updateByPrimaryKeySelective(TrsMedia record);

    int updateByPrimaryKey(TrsMedia record);
    
    public List<TrsMedia> getTrsMediaList(TrsMedia record);
    
    public List<TrsMedia> getTrsMediaListByObjectId(String objectId);
    
    public List<TrsMedia> getVideoListByObjectId(String objectId);

	public int getTrsMediaCounts(TrsMedia trsMedia);

	int batchDeleteTrsMedia(String[] mediaNoArrs);
	
	public List<TrsMedia> getForeMeetVedioList(String objectId);
	
	public List<TrsMedia> getForeMeetVedioListByArrs(String[] objectIds);

}