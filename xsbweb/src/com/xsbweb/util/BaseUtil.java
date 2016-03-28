package com.xsbweb.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xsbweb.service.StaffService;
import com.xsbweb.vo.Staff;

public class BaseUtil {
	
/*	private StaffService staffService;

	public Staff getStaffInfo(HttpServletRequest request){
		if(request==null){
			return null;
		}
		Object obj = request.getSession().getAttribute("staffId");
		String staffId = "";
		if(obj!=null){
			staffId = obj.toString();
		}
		Staff staff = staffService.getRoleByStaffId(staffId);
	}*/
	
	/**
	 * 添加Cookie
	 * @param cookieName
	 */
	public static void addCookie(String cookieName, String cookieValue,HttpServletResponse response) {
		
		Cookie cookie = new Cookie(cookieName,cookieValue);
		cookie.setPath("/");//该项目都可以调用这Cookie
		cookie.setMaxAge(31536000);//1年  
		response.addCookie(cookie);
	}
	/**
	 * 判读是否登录
	 * @param src
	 * @return
	 */
	public static boolean isLogin(HttpServletRequest request) {  
		//		customerId
		//		tmpToken
		//		customerPhoneNo
		//		headPicPath
		try {
			Cookie[] cookies = request.getCookies();
			if(cookies!=null){
				for (Cookie c :cookies) {
					if("customerId".equals(c.getName())
							&&URLDecoder.decode(c.getValue(),"UTF-8")!=null){
						
						return true;
					}
					
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		if(request.getSession().getAttribute("customerId")==null||//（用户id）
				request.getSession().getAttribute("customerPhoneNo")==null||//
					request.getSession().getAttribute("tmpToken")==null){//
			return false;
		}
		
		return true;
	}
	
	public static String getCookieValue(String key,HttpServletRequest request){
		
		try {
			Cookie[] cookies = request.getCookies();
			if(cookies!=null){
				for (Cookie c :cookies) {
					if(key.equals(c.getName())){
						
						return URLDecoder.decode(c.getValue(),"UTF-8");
					}
					
				}
			}
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		return null;
	}
}
