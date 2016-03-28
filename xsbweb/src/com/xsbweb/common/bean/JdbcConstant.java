package com.xsbweb.common.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * sql操作常量类
 * @author Administrator
 *
 */
public class JdbcConstant {
	//数据库驱动
	public  static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	//数据库用户名
	public static final String DB_USER_NAME="admin";
	//数据库密码
	public static final String DB_USER_PWD="admin";
	//oltp url
	public static final String DB_OLTP="jdbc:mysql://192.168.1.179:3306/oltp?useUnicode=true&characterEncoding=UTF8&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull";
	//olap url
	public static final String DB_OLAP="jdbc:mysql://192.168.1.179:3306/olap?useUnicode=true&characterEncoding=UTF8&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull";
	//meta url
	public static final String DB_META="jdbc:mysql://192.168.1.179:3306/meta?useUnicode=true&characterEncoding=UTF8&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull";
	
}
