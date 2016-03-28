package com.xsbweb.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


/**
 * 全局异常处理器
 * @author Administrator
 * dao、service、controller抛出的异常，最终会进入这个类进行处理
 */
public class ApplicationExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception excption) {
		//解析出异常类型
		//如果该 异常类型是系统 自定义的异常，直接取出异常信息，在错误页面展示
		ApplicationException appException = null;
		if(excption instanceof ApplicationException){
			appException = (ApplicationException)excption;
		}else{
			appException = new ApplicationException("未知错误，请联系系统管理员！");
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error");
		mav.addObject("error", appException.getMessage());
		return mav;
	}

}