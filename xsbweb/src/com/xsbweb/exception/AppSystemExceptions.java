package com.xsbweb.exception;

/**
 * 系统自定义异常类
 * 针对可预期的异常，需在程序中抛出此类的异常
 * @author Administrator
 *
 */
public class AppSystemExceptions extends Exception{

	private String message;
	
	public AppSystemExceptions(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}