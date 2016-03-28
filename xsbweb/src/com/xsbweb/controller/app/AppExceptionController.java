package com.xsbweb.controller.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsbweb.common.bean.ResultCode;

@Controller
public class AppExceptionController {

	@RequestMapping(value="/app/exception.do")
	public @ResponseBody Map<String,Object> appException(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(ResultCode.RESULT_CODE,ResultCode.ERROR_REQUEST);
		return map;
	}
}
