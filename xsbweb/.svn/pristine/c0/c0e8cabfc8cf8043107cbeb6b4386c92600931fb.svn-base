package com.xsbweb.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;



/**
 * 用于生成前台APP访问令牌
 * @author xxq
 */
public class TokenUtil {
	
	public static HashMap<String, String> TOKENCODE = new HashMap<String, String>();
	
	public static void setToken(String uid, String code) {
		TOKENCODE.put(uid, code);
	}
	
	public static boolean validateToken(String uid, String token) {
		boolean result = false;
		String tempToken = TOKENCODE.get(uid);
		if(CommonUtils.isNotBlank(tempToken)) {
			if(tempToken.equals(token)) {
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * 生成APP访问令牌，采用MD5
	 * @return	APP访问令牌
	 */
	synchronized public static String getToken() {
		//获取UUID
		String uuid = UUID.randomUUID().toString();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			if(CommonUtils.isNotBlank(uuid)) {
				md.update(uuid.getBytes());
			}
			return toHex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 把字节转换为16进制
	 * @param data	待处理数据
	 * @return
	 */
	public static String toHex(final byte[] data) {
		String result = "";
		if(data != null && data.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (byte b : data) {
			 	sb.append(String.format("%02X", b));
			}
			result = sb.toString();
		}
		return result;
	}
	
}
