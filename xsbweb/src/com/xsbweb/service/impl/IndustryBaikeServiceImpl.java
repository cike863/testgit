package com.xsbweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xsbweb.common.bean.DataBaseConstant;
import com.xsbweb.mapper.IndustryBaikeMapper;
import com.xsbweb.service.IndustryBaikeService;
import com.xsbweb.util.MultipleDataSource;
import com.xsbweb.vo.IndustryBaike;

public class IndustryBaikeServiceImpl implements IndustryBaikeService {
	
	
	@Autowired
	private IndustryBaikeMapper bkMapper;
	
	/**
	 * 获取用户总数
	 * @param news
	 * @return
	 * @throws Exception
	 */
	public int getIndustryBaikeCounts(IndustryBaike bk) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return bkMapper.getIndustryBaikeCounts(bk);
	}

	
	@Override
	public List<IndustryBaike> getAllIndustryBaikeList(IndustryBaike bk) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return bkMapper.getAllIndustryBaikeList(bk);
	}
	
	
	
	@Override
	public IndustryBaike getIndustryBaikeById(String id) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return bkMapper.getIndustryBaikeById(id);
	}
	
	@Override
	public int delIndustryBaike(String id){
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		int flag;
		try {
			int a = bkMapper.delIndustryBaike(id);
			//int b = customerMapper.deleteNewsIdx(newsNo);
			flag = a;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = -1;
		}
		return flag;
	}
	
	@Override
	public int insertBaike(IndustryBaike bk){
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		int flag;
		try {
			int a = bkMapper.insertBaike(bk);
			//int b = customerMapper.deleteNewsIdx(newsNo);
			flag = a;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = -1;
		}
		return flag;
	}
	
	
	@Override
	public int updateBaike(IndustryBaike bk){
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		int flag;
		try {
			int a = bkMapper.updateBaike(bk);
			//int b = customerMapper.deleteNewsIdx(newsNo);
			flag = a;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = -1;
		}
		return flag;
	}
	
	
	public int getMaxId() throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return bkMapper.getMaxId();
	}


	@Override
	public int batchDeleteIndustryBaike(String[] baikeIdArrs) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		int flag;
		try {
			int a = bkMapper.batchDeleteIndustryBaike(baikeIdArrs);
			//int b = customerMapper.deleteNewsIdx(newsNo);
			flag = a;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = -1;
		}
		return flag;
	}

}
