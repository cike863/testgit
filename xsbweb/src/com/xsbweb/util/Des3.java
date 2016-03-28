package com.xsbweb.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 3DES加密工具类
 * 
 * @author xxq 
 */
public class Des3 {
	// 密钥
	private static String secretKey = "012345670123456701234567";
	// 向量
	private final static String iv = "01234567";
	// 加解密统一使用的编码方式
	private final static String encoding = "utf-8";
	
	static Des3 des3;

	private Des3(){}
	
	public static Des3 getDes3() {
		if(null == des3){
			des3 = new Des3();
		}
		return des3;
	}

	public void setDecryptKey(String key) {
		this.secretKey = key;
	}

	/**
	 * 3DES加密
	 * 
	 * @param plainText 普通文本
	 * @return
	 * @throws Exception 
	 */
	public static String encode(String plainText) {
		Key deskey = null;
		DESedeKeySpec spec = null;
		SecretKeyFactory keyfactory = null;
		Cipher cipher = null;
		byte[] encryptData = null;
		try {
			spec = new DESedeKeySpec(secretKey.getBytes());
			keyfactory = SecretKeyFactory.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			encryptData = cipher.doFinal(plainText.getBytes(encoding));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return Base64.encode(encryptData);
	}

	/**
	 * 3DES解密
	 * 
	 * @param encryptText 加密文本
	 * @return
	 */
	public static String decode(String encryptText) throws Exception{
		Key deskey = null;
		DESedeKeySpec spec = null;
		SecretKeyFactory keyfactory;
		Cipher cipher = null;
		byte[] decryptData = null;
		spec = new DESedeKeySpec(secretKey.getBytes());
		keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
		decryptData = cipher.doFinal(Base64.decode(encryptText));
		return new String(decryptData, encoding);
	}
}