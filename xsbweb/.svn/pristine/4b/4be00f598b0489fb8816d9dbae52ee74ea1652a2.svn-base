package com.xsbweb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.xsbweb.common.bean.JdbcConstant;

/**
 * jdbc工具类
 * @author Administrator
 *
 */
public class JdbcUtil {
	private Logger log = Logger.getLogger(JdbcUtil.class);
	private Connection conn=null;
	private String url =null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private ResultSetMetaData data=null;
	public JdbcUtil(String type){
		try {
			Class.forName(JdbcConstant.DB_DRIVER);
			conn = getConnection(type);
		} catch (ClassNotFoundException e) {
			log.info("加载mysql驱动失败");
			e.printStackTrace();
		}
	}
	public Connection getConnection(String type){
		url = JdbcConstant.DB_OLTP;
		if("olap".equals(type)){
			url = JdbcConstant.DB_OLAP;
		}else if("meta".equals(type)){
			url = JdbcConstant.DB_META;
		}
		try {
			conn = DriverManager.getConnection(url,JdbcConstant.DB_USER_NAME,JdbcConstant.DB_USER_PWD);
		} catch (SQLException e) {
			log.info("数据库连接失败");
			e.printStackTrace();
		}
		return conn;
	}
	public  List<Map<String, Object>> findResult(String sql,List<String> params) throws SQLException {
		List<Map<String, Object>> resultListMap = new ArrayList<Map<String, Object>>();
		ps= conn.prepareStatement(sql);
		if(params!=null &&!params.isEmpty()){
			for(int i=0;i<params.size();i++){
				ps.setObject(i+1,params.get(i));
			}
		}
		rs=ps.executeQuery();
		while (rs.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> columnNamemap = new HashMap<String, Object>();
			List<String> columnNameList = new ArrayList<String>();
			if (sql.toLowerCase().contains("count")) {
				int count = rs.getInt(1);
				map.put("count", count);
				columnNameList.add("count");
				resultListMap.add(map);
			}else{
				boolean iscolumnName = true;
				data = rs.getMetaData();
				for (int i = 1; i <= data.getColumnCount(); i++) {
					String columnName = data.getColumnName(i);
					if (iscolumnName) {
						columnNameList.add(columnName);
					}
					Object columnVlaue = rs.getObject(i);
					map.put(columnName, columnVlaue);
				}
				iscolumnName = false;
				resultListMap.add(map);
			}
			columnNamemap.put("columnName", columnNameList);
			resultListMap.add(columnNamemap);
		}
		return resultListMap;
	}
	public int findResultCount(String sql,List<String> params) throws SQLException {
		ps= conn.prepareStatement(sql);
		if(params!=null &&!params.isEmpty()){
			for(int i=0;i<params.size();i++){
				ps.setObject(i+1,params.get(i));
			}
		}
		rs=ps.executeQuery();
		data = rs.getMetaData();
		return data.getColumnCount();
	}
	
	
	public static void main(String[] args) throws SQLException {
		String sql ="SELECT count(1) as count from oltp.v_stock_info WHERE 1 = 1  AND industry_name = '软件和信息技术服务业' ";
		List<String> prams = new ArrayList<String>();
		//prams.add("软件和信息技术服务业");
		JdbcUtil jdbcUtil = new JdbcUtil("");
		List<Map<String, Object>> list=jdbcUtil.findResult(sql,prams);
		System.out.println(list);
		System.out.println(list.get(list.size()-1));
	}
}
