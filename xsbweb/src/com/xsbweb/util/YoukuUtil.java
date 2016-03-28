package com.xsbweb.util;

import java.util.HashMap;
import java.util.Map;

public class YoukuUtil {

	private static String clientId = "f0ea182d52819e7f";
	
	private static String login_url="https://openapi.youku.com/v2/oauth2/authorize";
	
	public static Map<String,Object> login(){
		Map<String,Object> map = new HashMap<String,Object>();
		StringBuffer param = new StringBuffer();
		param.append("client_id=").append(clientId)
		.append("&response_type=code").append("&redirect_uri=");
		try {
			map = IdlSDK.getURLByGet(login_url, param.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
