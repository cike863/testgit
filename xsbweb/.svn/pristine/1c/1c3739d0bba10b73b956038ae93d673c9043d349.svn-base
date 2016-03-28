package com.xsbweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.xsbweb.common.bean.DataBaseConstant;
import com.xsbweb.mapper.TrsMediaMapper;
import com.xsbweb.service.UploadFilesService;
import com.xsbweb.util.MultipleDataSource;
import com.xsbweb.vo.TrsMedia;

public class UploadFilesServiceImpl implements UploadFilesService {

	@Autowired
	private TrsMediaMapper trsMediaMapper;
	
	
	public String addMediaRecode(TrsMedia trsMedia) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		//插入media数据
		trsMediaMapper.insert(trsMedia);
		return  trsMedia.getMediaNo();
	}

}
