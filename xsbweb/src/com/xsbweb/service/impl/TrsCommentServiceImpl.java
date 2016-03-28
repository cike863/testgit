package com.xsbweb.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.xsbweb.common.bean.DataBaseConstant;
import com.xsbweb.mapper.TrsCommentMapper;
import com.xsbweb.service.TrsCommentService;
import com.xsbweb.util.MultipleDataSource;
import com.xsbweb.vo.Customer;
import com.xsbweb.vo.TrsComment;

public class TrsCommentServiceImpl implements TrsCommentService {

	@Autowired
	private TrsCommentMapper trsCommentMapper;
	
	@Override
	public int insertTrsComment(TrsComment trsComment) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		return trsCommentMapper.insertTrsComment(trsComment);
	}

	@Override
	public int deleteTrsComment(TrsComment trsComment) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TrsComment> getTrsCommentByObjectId(String objectId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 通过Address获取留言信息，拿到用户id
	 */
	@Override
	public List<TrsComment> getTrsCommentByAddress(TrsComment trsComment)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return  trsCommentMapper.getCommentList(trsComment);

	}

	@Override
	public int getCommentListCount(TrsComment trsComment) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return trsCommentMapper.getCommentListCount(trsComment);
	}

	@Override
	public int bathDeleteProject(String[] trsCommentNoArrs) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		int flag=0;
		try{
			trsCommentMapper.bathDeleteProject(trsCommentNoArrs);
			flag=1;
		}catch(Exception e){
			e.printStackTrace();
			flag=0;
		}
		return flag;
	}
	
}
