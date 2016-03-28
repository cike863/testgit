package com.xsbweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xsbweb.service.SqlService;
import com.xsbweb.util.JdbcUtil;
import com.xsbweb.vo.SqlVO;

public class SqlServiceImpl implements SqlService {

	@Override
	public List<Map<String, Object>> findResult(SqlVO sqlVo) throws Exception {
		if(!sqlVo.getSql().toLowerCase().contains("limit".toLowerCase())){
			if(sqlVo.getSql().endsWith(";")){
				sqlVo.setSql(sqlVo.getSql().replace(";", " limit "+sqlVo.getCurrentRecord()+","+sqlVo.getPageSize())+";");
			}else{
				sqlVo.setSql(sqlVo.getSql()+ " limit "+sqlVo.getCurrentRecord()+","+sqlVo.getPageSize()+";");
			}
		}
		//String sql ="SELECT * FROM oltp.v_stock_info WHERE 1 = 1  AND industry_name = ? LIMIT 1 , 10; ";
		List<String> prams = new ArrayList<String>();
		//prams.add("软件和信息技术服务业");
		JdbcUtil jdbcUtil = new JdbcUtil(sqlVo.getDbType());
		List<Map<String, Object>> list=jdbcUtil.findResult(sqlVo.getSql(),prams);
		//System.out.println(list);
		return list;
	}

	@Override
	public int findResultCount(SqlVO sqlVo) throws Exception {
		JdbcUtil jdbcUtil = new JdbcUtil(sqlVo.getDbType());
		List<Map<String, Object>> list=jdbcUtil.findResult(sqlVo.getSql(),null);
		int count = Integer.valueOf(list.get(0).get("count").toString());
		return count;
	}
	
}
