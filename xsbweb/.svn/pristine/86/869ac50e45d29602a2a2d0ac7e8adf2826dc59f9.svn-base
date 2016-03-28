package com.xsbweb.util;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class ConfigUtil {
	
	public static Map<String, String> configMap = null;
	
	static {
		if(configMap == null){
			init();
		}
	}

	public static void init(){
		try {
			configMap = new HashMap<String, String>();
			Properties prop = new Properties();
			prop.load(ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties"));
			String env = prop.getProperty("env");
			configMap.put("jedis_port", prop.getProperty("jedis_port"));
			configMap.put("ftp_port", prop.getProperty("ftp_port"));
			configMap.put("ftp_url_pro", prop.getProperty("ftp_url_pro"));
			configMap.put("jedis_timeout", prop.getProperty("jedis_timeout"));
			configMap.put("ftp_login_name", prop.getProperty("ftp_login_name"));
			configMap.put("ftp_login_pass", prop.getProperty("ftp_login_pass"));
			configMap.put("file_max", prop.getProperty("file_max"));
			configMap.put("email.protocol", prop.getProperty("email.protocol"));
			configMap.put("email.host", prop.getProperty("email.host"));
			configMap.put("email.prot", prop.getProperty("email.prot"));
			
			/*configMap.put("account_id", prop.getProperty("account_id"));
			configMap.put("token", prop.getProperty("token"));
			configMap.put("app_id", prop.getProperty("app_id"));
			configMap.put("sms_template", prop.getProperty("sms_template"));
			configMap.put("db_driver", prop.getProperty("db_driver"));
			configMap.put("db_name", prop.getProperty("db_name"));
			configMap.put("db_pwd", prop.getProperty("db_pwd"));
			configMap.put("db_url", prop.getProperty("db_url"));*/
			
			if("test".equals(env)){
				configMap.put("jedis_url", prop.getProperty("jedis_url_test"));
				configMap.put("jedis_password", prop.getProperty("jedis_password_test"));
			}else{
				configMap.put("jedis_url", prop.getProperty("jedis_url_pro"));
				configMap.put("jedis_password", prop.getProperty("jedis_password_pro"));
			}
			System.out.println("系统配置文件信息："+configMap.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
