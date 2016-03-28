package com.xsbweb.vo.extend;

import com.xsbweb.util.ConfigUtil;
import com.xsbweb.util.SimpleMailSender;


public class TempTest {
	public static void main(String[] args){  
	     //这个类主要是设置邮件  
	     EmailVO mailInfo = new EmailVO();   
	     /*mailInfo.setMailServerHost("smtp.163.com");  */ 
	     mailInfo.setMailServerHost(ConfigUtil.configMap.get("smtp.163.com"));   
	     mailInfo.setMailServerPort("25");   
	     mailInfo.setValidate(true);   
	     mailInfo.setUserName("18124198895@163.com");   
	     mailInfo.setPassword("123456miao");//邮箱密码   
	     mailInfo.setFromAddress("18124198895");   
	     mailInfo.setToAddress("18124198895@163.com");    
	     mailInfo.setSubject("邮箱标题");   
	     mailInfo.setContent("邮箱内容");   
	        //这个类主要来发送邮件  
	     SimpleMailSender sms = new SimpleMailSender();  
	         sms.sendTextMail(mailInfo);//发送文体格式   
	         //sms.sendHtmlMail(mailInfo);//发送html格式  
	   }  
}
