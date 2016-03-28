package com.xsbweb.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.xsbweb.common.bean.XsbBusinessConstant;

/**
 * 产生手机验证码
 * @author Administrator
 *
 */
public class SmsUtil {
	
	//4位随机数
	public static int createSmsCode(){
		double d = Math.random()*10000;
		int validateCode = (int)d;
		if(String.valueOf(validateCode).length()<4){
			validateCode = (int)createSmsCode();
		}
		return validateCode;
	}
	
	//6位随机数
	public static int randomFor6p(){
		double d = Math.random()*1000000;
		int validateCode = (int)d;
		if(String.valueOf(validateCode).length()<6){
				validateCode = (int)createSmsCode();
		}
		return validateCode;
	}
	
	public static String sendPhoneSms(String customerPhoneNo,String content)throws Exception{
		if(CommonUtils.isBlank(customerPhoneNo)||CommonUtils.isBlank(content)){
			return "";
		}
		//String u = "http://121.199.50.122:8888/smsGBK.aspx?action=send&userid=956&account=SZXGCM&password=12345678&mobile=";
		//StringBuffer urlsb = new StringBuffer(u);
		StringBuffer urlsb = new StringBuffer(XsbBusinessConstant.SMSURL);
		urlsb.append(customerPhoneNo).append("&content=").append(content);
		URL url = new URL(urlsb.toString());
		HttpURLConnection  conn = (HttpURLConnection )url.openConnection();
		conn.setRequestProperty("Accept-Charset", "utf-8");
		conn.setRequestProperty("contentType", "utf-8");
		//conn.setRequestProperty("Accept-Charset", "GBK");
		//conn.setRequestProperty("contentType", "GBK");
		conn.connect();
		//读取数据
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
		//BufferedReader bufIn = new BufferedReader(new InputStreamReader(conn.getInputStream(),"GBK"));
		String line = null;
		StringBuffer contentBuffer = new StringBuffer();
		while((line = bufIn.readLine())!=null)
		{
			contentBuffer.append(line);
		}	
		if(contentBuffer.length()>0 && contentBuffer.toString().indexOf("Success")>0){
			return "success";
		}else{
			return "fail";
		}
	}
}
