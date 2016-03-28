package com.xsbweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xsbweb.common.bean.DataBaseConstant;
import com.xsbweb.mapper.EtlConfigMapper;
import com.xsbweb.service.EtlConfigService;
import com.xsbweb.util.MultipleDataSource;
import com.xsbweb.vo.EtlThread;

public class EtlConfigServiceImpl implements EtlConfigService {
	@Autowired
	private EtlConfigMapper etlConfigMapper;
	@Override
	public List<EtlThread> getEtlThread(EtlThread etlThread) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return etlConfigMapper.getEtlThread(etlThread);
	}

	@Override
	public int addEtlThread(EtlThread etlThread) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		int flag=1;
		try{
			etlConfigMapper.addEtlThread(etlThread);
		}catch(Exception e){
			e.printStackTrace();
			 flag=-1;
		}
		return flag;
	}

	@Override
	public int editEtlThread(EtlThread etlThread) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return etlConfigMapper.editEtlThread(etlThread);
	}

	@Override
	public int deleteEtlThread(EtlThread etlThread) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return etlConfigMapper.deleteEtlThread(etlThread);
	}

}
