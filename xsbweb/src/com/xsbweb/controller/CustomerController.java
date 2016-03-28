package com.xsbweb.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.common.bean.ResultCode;
import com.xsbweb.controller.app.AppCustomerController;
import com.xsbweb.service.LoginRegisterService;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.util.GetIp;
import com.xsbweb.util.MD5;
import com.xsbweb.util.RedisUtil;
import com.xsbweb.vo.extend.*;

@Controller
public class CustomerController {

	private Logger log = Logger.getLogger(CustomerController.class);
	@Autowired
	private LoginRegisterService loginRegisterService;
	
	@Resource
    private RedisUtil redisUtil;
	 
	/**
	 * 跳转到密码修改页面
	 * @param customerId
	 * @return
	 */
	@RequestMapping(value="/cust/toUpdatePwd.do")
	public ModelAndView toUpdatePwd(@RequestParam("customerId") String customerId){
		ModelAndView mav = new ModelAndView();
		CustomerVO customerVO = new CustomerVO();
		customerVO.setCustomerId(customerId);
		mav.addObject("customerVO",customerVO);
		mav.setViewName("customer/updateCustomerPwd");
		return mav;
	}
	
	/**
	 * 修改密码
	 * @param customerVO
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/cust/updatePwd.do")
	public @ResponseBody Map<String,Object> updatePwd(HttpServletRequest request,CustomerVO customerVO) throws Exception{
		//获取客户端ip地址
		Map<String,Object> map = new HashMap<String,Object>();
		String ip = GetIp.getIpAddr(request);
		log.info("############"+customerVO.getCustomerId()+"  ip地址："+ip);
		if(CommonUtils.isBlank(customerVO.getCustomerId())){
			map.put("resultCode", ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		customerVO.setIp(ip);
		customerVO.setLoginMethod(customerVO.getLoginMethod());
		customerVO.setCustomerUname(customerVO.getCustomerId());
		//MD5加密
		customerVO.setCustomerPassword(MD5.get(customerVO.getCustomerPassword()));
		//判断用户是否存在
		CustomerVO cuVO = loginRegisterService.validateLogin(customerVO);
		if(cuVO==null || CommonUtils.isBlank(cuVO.getCustomerId())){
			map.put("resultCode", ResultCode.ERROR_PWD_ERROR);
		}else{
			customerVO.setCustomerPassword(MD5.get(customerVO.getNewPassword()));
			//修改用户信息
			Integer flag = loginRegisterService.updatePassword(customerVO);
			//修改成功
			if(flag==1){
				map.put("resultCode", ResultCode.RESPONSE_SUCCESS);
			}else{
				map.put("resultCode", ResultCode.RESPONSE_FAIL);
			}
		}
		return map;
	}
}
