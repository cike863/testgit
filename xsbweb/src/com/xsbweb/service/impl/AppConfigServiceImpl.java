package com.xsbweb.service.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.xsbweb.common.bean.DataBaseConstant;
import com.xsbweb.mapper.AppToJ2eeLogMapper;
import com.xsbweb.service.AppConfigService;
import com.xsbweb.util.MultipleDataSource;
import com.xsbweb.vo.AppToJ2eeLog;

public class AppConfigServiceImpl implements AppConfigService {

	@Autowired
	private AppToJ2eeLogMapper logMapper;
	
	@Override
	public int insertAppToJ2eeLog(AppToJ2eeLog appToJ2eeLog)
			throws Exception {
		//String url = request.get
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return logMapper.insert(appToJ2eeLog);
	}

}
