package com.xsbweb.controller.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsbweb.common.bean.ResultCode;
import com.xsbweb.common.bean.XsbBusinessConstant;
import com.xsbweb.service.LoginRegisterService;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.util.GetIp;
import com.xsbweb.util.MD5;
import com.xsbweb.util.MathUtil;
import com.xsbweb.util.RedisUtil;
import com.xsbweb.util.SmsUtil;
import com.xsbweb.vo.Customer;
import com.xsbweb.vo.extend.*;

@Controller
public class AppLoginRegistController {

	private static final String VALIDATE_PHONE_CODE = "VALIDATE_PHONE_CODE";  
    private static final String VALIDATE_PHONE = "VALIDATE_PHONE";  
    private static final String SEND_CODE_TIME = "SEND_CODE_TIME"; 
    private static final String LOGIN_TIME = "LOGIN_DATE_TIME";
    private static final String CUSTOMERID = "CUSTOMERID";
    private static final String OPERATION = "operation";
    private static final String RESULTCODE = "resultCode";
    
    private Logger log = Logger.getLogger(AppLoginRegistController.class);
    @Autowired
	private LoginRegisterService loginRegisterService;  
    
    @Resource
    private	RedisUtil redisUtil;
	/**
	 * 用户登录判断
	 * @param customerVO
	 * @return
	 */
	@RequestMapping(value="/app/login.do",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> login(HttpServletRequest request,
			@RequestParam("customerUname") String customerUname,
			@RequestParam("customerPassword") String customerPassword,
			@RequestParam("loginMethod") String loginMethod,
			@RequestParam("imei") String imei,
			@RequestParam(value="customerPhoneNo",required=false) String customerPhoneNo
			)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//参数校验
		if(CommonUtils.isBlank(customerUname) || CommonUtils.isBlank(customerPassword) || CommonUtils.isBlank(loginMethod) 
				 || CommonUtils.isBlank(imei)){
			//请求参数有误
			map.put(RESULTCODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		CustomerVO customerVO = new CustomerVO();
		customerVO.setCustomerUname(customerUname);
		customerVO.setCustomerPassword(customerPassword);
		customerVO.setLoginMethod(loginMethod);
		//获取客户端ip地址
		String ip = GetIp.getIpAddr(request);
		customerVO.setIp(ip);
		//判断用户是否存在
		CustomerVO cuVO = loginRegisterService.validateLogin(customerVO);
		if(cuVO==null){
			//提示用户名或密码错误
			map.put(RESULTCODE, ResultCode.RESPONSE_FAIL);
		}else{
			log.info("###############prcFlag:"+cuVO.getPrcFlag()+"###################");
			if(cuVO.getPrcFlag()!=null && cuVO.getPrcFlag()==3){
				//用户提交次数态度 被锁
				map.put(RESULTCODE, ResultCode.ERROR_USER_LOCK);
				return map;
			}
			if(cuVO.getPrcFlag()!=null && cuVO.getPrcFlag()==4){
				//用户进入黑名单
				map.put(RESULTCODE, ResultCode.ERROR_USER_BLACK);
				return map;
			}
			log.info("###############customerId:"+cuVO.getCustomerId()+"###################");
			if(CommonUtils.isNotBlank(cuVO.getCustomerId())){
				//根据customerId查询用户信息、权限
				CustomerVO customer = loginRegisterService.getCustomerById(cuVO.getCustomerId());
				
				//获取权限信息----------------------
				
				//------------------
				//用户登录成功，对用token存入缓存
				String token = "";
				if(CommonUtils.isNotBlank(customerPhoneNo)){
					token = MD5.get(imei+customerPhoneNo);
					redisUtil.setString("tmp:"+customerPhoneNo+":token", token, -1, null);
					log.info("###############tmp:"+customerPhoneNo+":token###################"+redisUtil.getString("tmp:"+customerPhoneNo+":token"));
				}else{
					token = MD5.get(imei+customerUname+CommonUtils.getNowDateStringOf8());
					redisUtil.setString("tmp:"+customerUname+":token", token, -1, null);
					log.info("###############tmp:"+customerUname+":token###################"+redisUtil.getString("tmp:"+customerUname+":token"));
				}
				//用户关注行业codes数组
				if(customer.getGzIndustry()!=null && customer.getGzIndustry()>0){
					List<Long> gzCodes = MathUtil.Fun(customer.getGzIndustry());
					if(gzCodes!=null && !gzCodes.isEmpty()){
						Long[] gzIndustryArr = new Long[gzCodes.size()];
						for (int i = 0; i < gzCodes.size(); i++) {
							gzIndustryArr[i] = gzCodes.get(i);
						}
						customer.setGzIndustryArr(gzIndustryArr);
					}else{
						customer.setGzIndustryArr(new Long[0]);
					}
				}else{
					customer.setGzIndustryArr(new Long[0]);
				}
				
				//永久存入redis
				map.put("customerVO", customer);
				map.put("tmpToken", token);
				map.put(RESULTCODE, ResultCode.RESPONSE_SUCCESS);
				map.put("customerPhoneNo", cuVO.getCustomerPhoneNo());
			}else{
				//提示用户名或密码错误
				map.put(RESULTCODE, ResultCode.RESPONSE_FAIL);
			}
		}
		request.setAttribute(ResultCode.RESULT_OBJECT, map);
		return map;
	}
	
	/**
	 * 用户登出判断
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/app/logout.do")
	public @ResponseBody Map<String,Object> logout(
			HttpServletRequest request,
			@RequestParam(value="tmpToken")String tmpToken,
			@RequestParam(value="customerPhoneNo")String customerPhoneNo
			){
		Map<String, Object> map = null;
		try {
			map = new HashMap<String,Object>();
			//参数校验
			if(CommonUtils.isBlank(tmpToken) || CommonUtils.isBlank(customerPhoneNo) ){
				//请求参数有误
				map.put(RESULTCODE, ResultCode.ERROR_PARAMETER_ERROR);
				return map;
			}
			//1秒后该token失效
			redisUtil.setString("tmp:"+customerPhoneNo+":token", tmpToken, 1, null);
			map.put(RESULTCODE, ResultCode.RESPONSE_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put(RESULTCODE, ResultCode.RESPONSE_FAIL);
		}
		request.setAttribute(ResultCode.RESULT_OBJECT, map);
		return map;
	}
	/**
	 * 注册用户
	 * @param customerVO
	 * @return
	 */
	/*	public @ResponseBody int register(HttpServletRequest request,
	
	)throws Exception{*/
	@RequestMapping(value="/app/register.do",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> register(
			@RequestParam("phoneCode") String phoneCode,
			@RequestParam("customerPassword") String customerPassword,
			@RequestParam("loginMethod") String loginMethod,
			@RequestParam("customerPhoneNo") String customerPhoneNo,
			HttpServletRequest request)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//验证参数
		log.info("########################"+customerPhoneNo+"#########################");
		if(CommonUtils.isBlank(phoneCode)||CommonUtils.isBlank(phoneCode)||
				CommonUtils.isBlank(customerPassword)||CommonUtils.isBlank(loginMethod)||
				CommonUtils.isBlank(customerPhoneNo)){
			map.put(RESULTCODE, ResultCode.ERROR_PARAMETER_ERROR);
			log.info("##########################################参数有误");
			return map;
		}
		//对可能存在中文的字符转码
		//customerName = URLDecoder.decode(customerName,"utf-8");
		//log.info("###########################################传过来的customerName decode:"+customerName);
		//首先验证手机验证码是否正确,是否超时
		log.info("###########################################传过来的code:"+phoneCode);
        String code = redisUtil.getString("tmp:"+customerPhoneNo+":code");
        log.info("###########################################tmp:"+customerPhoneNo+":code="+code);
        if(CommonUtils.isBlank(code)){
        	map.put(RESULTCODE, ResultCode.ERROR_CODE_ERROR);
        	return map;
        }
        //验证码是否正确
        if(!code.equalsIgnoreCase(phoneCode)){  
        	map.put(RESULTCODE, ResultCode.ERROR_CODE_ERROR);
        	return map;
        } 
        CustomerVO customerVO = new CustomerVO();
        customerVO.setCustomerPhoneNo(customerPhoneNo);
        customerVO.setCustomerPassword(customerPassword);
        //customerVO.setCustomerEmail(customerPhoneNo);
        customerVO.setLoginMethod(loginMethod);
		//新增用户信息
		Integer flag = loginRegisterService.register(customerVO);
		log.info("/app/register.do-------------"+flag);
		//注册成功
		if(flag==1){
			map.put(RESULTCODE, ResultCode.RESPONSE_SUCCESS);
		}else{
			map.put(RESULTCODE, ResultCode.RESPONSE_FAIL);
		}
		request.setAttribute(ResultCode.RESULT_OBJECT, map);
		return map;
	}
	
	/**
	 * 用户电话号码实时校验
	 * @param customerVO
	 * @return
	 */
	@RequestMapping(value="/app/register/validate.do",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> validateCustomerName(
			HttpServletRequest request,
			@RequestParam(value="customerPhoneNo",required=false) String customerPhoneNo,
			@RequestParam(value="customerEmail",required=false) String customerEmail){
		Map<String,Object> map = new HashMap<String,Object>();
		log.info("##############"+customerEmail+"#################");
		//参数校验
		if(CommonUtils.isBlank(customerPhoneNo) && CommonUtils.isBlank(customerEmail) ){
			//请求参数有误
			map.put(RESULTCODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		CustomerVO customerVO = new CustomerVO();
		customerVO.setCustomerPhoneNo(customerPhoneNo);
		customerVO.setCustomerEmail(customerEmail);
		Integer flag;
		try {
			flag = loginRegisterService.validateRegister(customerVO);
			if(flag == 0){
				map.put(RESULTCODE, ResultCode.RESPONSE_FAIL);
				return map;
			}
			map.put(RESULTCODE, ResultCode.RESPONSE_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put(RESULTCODE, ResultCode.ERROR_SYSTEM);
			e.printStackTrace();
		}
		//0 就是存在该号码或者邮箱
		request.setAttribute(ResultCode.RESULT_OBJECT, map);
		return map;
	}
	
	/**
	 * 发送手机验证码
	 * @return
	 */
	@RequestMapping(value="/app/register/sendCode.do",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> sendPhoneCode(
					@RequestParam("customerPhoneNo") String customerPhoneNo,
					@RequestParam("loginMethod") String loginMethod,
					HttpServletRequest request)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//手机号码为空
		if(CommonUtils.isBlank(customerPhoneNo)){
			map.put(RESULTCODE, ResultCode.ERROR_PHONENO_NULL);
			return map;
		}
		//String u = "http://121.199.50.122:8888/smsGBK.aspx?action=send&userid=956&account=SZXGCM&password=12345678&mobile=";
		//StringBuffer urlsb = new StringBuffer(u);
		StringBuffer urlsb = new StringBuffer(XsbBusinessConstant.SMSURL);
		int code = SmsUtil.createSmsCode();
		String message = "您申请的手机验证码是："+code+",请输入后进行验证，谢谢！【第一路演】";
		System.out.println(message);
		urlsb.append(customerPhoneNo).append("&content=").append(message);
		URL url = new URL(urlsb.toString());
		System.out.println(urlsb.toString());
		HttpURLConnection  conn = (HttpURLConnection )url.openConnection();
		//conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept-Charset", "utf-8");
		conn.setRequestProperty("contentType", "utf-8");
		//conn.setRequestProperty("Accept-Charset", "GBK");
		//conn.setRequestProperty("contentType", "GBK");
		conn.connect();
		//读取数据
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
		//BufferedReader bufIn = new BufferedReader(new InputStreamReader(conn.getInputStream(),"GBK"));
		String line = null;
		StringBuffer contentBuffer = new StringBuffer();
		while((line = bufIn.readLine())!=null)
		{
			contentBuffer.append(line);
		}	
		if(contentBuffer.length()>0 && contentBuffer.toString().indexOf("Success")>0){
			//发送成功则在redis中存入手机号码、验证码、发送时间
			redisUtil.setString("tmp:"+customerPhoneNo+":code", String.valueOf(code), 300, null);
			String s = redisUtil.getString("tmp:"+customerPhoneNo+":code");
			log.info("##########################"+s);
			log.info("####################tmp:"+customerPhoneNo+":code="+String.valueOf(code)+"#############");
	        map.put(RESULTCODE, ResultCode.RESPONSE_SUCCESS);
		}else{
			map.put(RESULTCODE, ResultCode.RESPONSE_FAIL);
		}
		request.setAttribute(ResultCode.RESULT_OBJECT, map);
		return map;
	}
	
	
	/**
	 * 忘记密码，根据手机找回密码
	 * @param customerPhoneNo
	 * @return
	 */
	@RequestMapping(value="/app/login/forgetPwd.do",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> forgetPwdByPhone(CustomerVO customerVO,
			@RequestParam(value="mobileIem",required=false)String mobileIem,
			HttpServletRequest request)throws Exception{
		//首先验证手机验证码是否正确,是否超时
		Map<String,Object> map = new HashMap<String,Object>();
		//验证参数
        String phoneCode = customerVO.getPhoneCode();  
        String customerPhone = customerVO.getCustomerPhoneNo();  
        String code = redisUtil.getString("tmp:"+customerPhone+":code");
        log.info("######tmp:"+customerPhone+":code："+code);
        log.info("######传过来的参数code："+code);
        if(CommonUtils.isBlank(code)){
        	map.put(RESULTCODE, ResultCode.ERROR_CODE_ERROR);
        	return map;
        }
        //验证码是否正确
        if(!code.equalsIgnoreCase(phoneCode)){  
        	map.put(RESULTCODE, ResultCode.ERROR_CODE_ERROR);
        	return map;
        } 
        String customerId = loginRegisterService.getCustomerIdByPhoneOrMail(customerPhone);
        //如果customerId不存在，提示用户，手机号码没有注册
        if(CommonUtils.isBlank(customerId)){
        	map.put(RESULTCODE, ResultCode.ERROR_PHONENO_NULL);
        	return map;
        }
        customerVO.setCustomerId(customerId);
        //MD5加密
  		//customerVO.setCustomerPassword(MD5.get(customerVO.getCustomerPassword()));
		//修改用户信息
		Integer flag = loginRegisterService.updatePassword(customerVO);
		//注册成功
		if(flag==1){
			map.put(RESULTCODE, ResultCode.RESPONSE_SUCCESS);
		}else{
			map.put(RESULTCODE, ResultCode.RESPONSE_FAIL);
		}
		request.setAttribute(ResultCode.RESULT_OBJECT, map);
		return map;
	}
	
	/**
	 * 验证手机号码或者邮箱是否在系统中存在
	 * @param customerLabel
	 * @return 90000：手机号码或者邮箱为空；1：成功；0：失败
	 * @throws Exception 
	 */
	@RequestMapping(value="/app/vpom.do",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> validatePhoneNoOrEmail(
			HttpServletRequest request,
			@RequestParam(value="customerLabel")String customerLabel
			) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		if(CommonUtils.isBlank(customerLabel)){
			map.put(RESULTCODE, ResultCode.ERROR_PHONENO_NULL);
			return map;
		}
		 String customerId = loginRegisterService.getCustomerIdByPhoneOrMail(customerLabel);
		 if(CommonUtils.isNotBlank(customerId)){
			 map.put(RESULTCODE, ResultCode.RESPONSE_SUCCESS);
		 }else{
			 map.put(RESULTCODE, ResultCode.RESPONSE_FAIL);
		 }
		 request.setAttribute(ResultCode.RESULT_OBJECT, map);
		 return map;
	}
	
	/**
	 * 个人中心，修改用户信息
	 * @param request
	 * @param customerId
	 * @param customerPhoneNo
	 * @param tmpToken
	 * @param loginMethod
	 * @param customerName
	 * @param customerEmail
	 * @param customerType
	 * @param customerCompany
	 * @param customerQq
	 * @param customerWechat
	 * @param customerSex
	 * @param customerPosition
	 * @param gzIndustry
	 * @param customerNickname
	 * @return
	 */
	@RequestMapping(value="/app/updateCustomerInfo.do",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateCustomerInfo(
			HttpServletRequest request,
			@RequestParam(value="customerId") String customerId,
			@RequestParam(value="customerPhoneNo") String customerPhoneNo,
			@RequestParam(value="tmpToken") String tmpToken,
			@RequestParam(value="loginMethod") String loginMethod,
			@RequestParam(value="customerName",required=false) String customerName,
			@RequestParam(value="customerEmail",required=false) String customerEmail,
			@RequestParam(value="customerType",required=false) String customerType,
			@RequestParam(value="customerCompany",required=false) String customerCompany,
			@RequestParam(value="customerQq",required=false) String customerQq,
			@RequestParam(value="customerWechat",required=false) String customerWechat,
			@RequestParam(value="customerSex",required=false) String customerSex,
			@RequestParam(value="customerPosition",required=false) String customerPosition,
			@RequestParam(value="capitalScale",required=false) String capitalScale,
			@RequestParam(value="gzIndustry",required=false) String gzIndustry,
			@RequestParam(value="customerNickname",required=false) String customerNickname
			){
		Map<String,Object> map = new HashMap<String,Object>();
		if(CommonUtils.isBlank(customerId)||CommonUtils.isBlank(customerPhoneNo)||
				CommonUtils.isBlank(loginMethod)||
				CommonUtils.isBlank(tmpToken)){
			map.put(RESULTCODE, ResultCode.ERROR_PARAMETER_ERROR);
			log.info("##########################################参数有误");
			return map;
		}
		CustomerVO customerVO = new CustomerVO();
		customerVO.setCustomerId(customerId);
		customerVO.setCustomerName(customerName);
		customerVO.setCustomerEmail(customerEmail);
		customerVO.setCustomerType(customerType);
		customerVO.setCustomerCompany(customerCompany);
		customerVO.setCustomerQq(customerQq);
		customerVO.setCustomerWechat(customerWechat);
		customerVO.setCustomerSex(customerSex);
		customerVO.setCustomerPosition(customerPosition);
		customerVO.setCustomerNickname(customerNickname);
		customerVO.setCapitalScale(capitalScale);
		if(CommonUtils.isNotBlank(gzIndustry)){
			customerVO.setGzIndustry(Long.valueOf(gzIndustry));
		}
		try {
			int flag = loginRegisterService.updateCustomerInfo(customerVO);
			if(flag>0){
				//修改成功后重新返回最新的用户信息
				Customer customer = loginRegisterService.getCustomerById(customerId);
				//用户关注行业codes数组
				if(customer.getGzIndustry()!=null && customer.getGzIndustry()>0){
					List<Long> gzCodes = MathUtil.Fun(customer.getGzIndustry());
					if(gzCodes!=null && !gzCodes.isEmpty()){
						Long[] gzIndustryArr = new Long[gzCodes.size()];
						for (int i = 0; i < gzCodes.size(); i++) {
							gzIndustryArr[i] = gzCodes.get(i);
						}
						customer.setGzIndustryArr(gzIndustryArr);
					}else{
						customer.setGzIndustryArr(new Long[0]);
					}
				}else{
					customer.setGzIndustryArr(new Long[0]);
				}
				map.put("customer", customer);
				map.put(RESULTCODE, ResultCode.RESPONSE_SUCCESS);
			}else{
				map.put(RESULTCODE, ResultCode.RESPONSE_FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put(RESULTCODE, ResultCode.RESPONSE_FAIL);
			return map;
		}finally{
			request.setAttribute(ResultCode.RESULT_OBJECT, map);
		}
		return map;
	}
}
