package com.xsbweb.exception;

/**
 * 系统自定义异常类
 * 针对可预期的异常，需在程序中抛出此类的异常
 * @author Administrator
 *
 */
public class ApplicationException extends Exception{

	private String message;
	
	public ApplicationException(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
