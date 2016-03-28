package com.xsbweb.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.json.JsonObject;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import test.HttpClientSendPost;
import test.HttpsClient;
import test.HttpsUtil;
import test.MyX509TrustManager;
import test.test22;

import com.xsbweb.cloopen.rest.RestSDK;
import com.xsbweb.common.bean.ResultCode;
import com.xsbweb.common.bean.XsbBusinessConstant;
import com.xsbweb.controller.app.AppCustomerController;
import com.xsbweb.exception.ApplicationException;
import com.xsbweb.service.LoginRegisterService;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.util.ConfigUtil;
import com.xsbweb.util.GetIp;
import com.xsbweb.util.MD5;
import com.xsbweb.util.RedisUtil;
import com.xsbweb.util.SmsUtil;
import com.xsbweb.vo.Customer;
import com.xsbweb.vo.Staff;
import com.xsbweb.vo.extend.*;

/**
 * 用于登录、注册控制
 * @author Administrator
 *
 */
@Controller
public class LoginRegisterController {
	
	private Logger log = Logger.getLogger(AppCustomerController.class);
	
	private static final String VALIDATE_PHONE_CODE = "VALIDATE_PHONE_CODE";  
    private static final String VALIDATE_PHONE = "VALIDATE_PHONE";  
    private static final String SEND_CODE_TIME = "SEND_CODE_TIME"; 
    private static final String LOGIN_TIME = "loginTime";
    private static final String CUSTOMERID = "customerId";
    private static final String RESULTCODE = "resultCode";

    @Resource
    private RedisUtil redisUtil;
    
    @Autowired
	private LoginRegisterService loginRegisterService;  
    
    @RequestMapping(value="/toLogin.do")
    public String toLogin(){
    	redisUtil.setString("aa", "11111111111111111", 6000, null);
    	String ttt = redisUtil.getString("aa");
    	log.info("######################aa: "+ttt);
    	return "login";
    }
    
    
    
    @RequestMapping(value="/toIndex.do")
    public String toIndex(){
    	return "index";
    }
    
    @RequestMapping(value="/toRegister.do")
    public String toRegister(){
    	return "register";
    }
    
    
    
    @RequestMapping(value="/toForgetPwd.do")
    public String toForgetPwd(){
    	return "forgetPwd";
    }
    
    
	/**
	 * 用户登录判断
	 * @param customerVO
	 * @return
	 */
	@RequestMapping(value="/login.do",params="method=login",method=RequestMethod.POST)
	public String login(ModelMap mode,HttpServletRequest request,CustomerVO customerVO)throws Exception{
		//获取客户端ip地址
		String ip = GetIp.getIpAddr(request);
		customerVO.setIp(ip);
		customerVO.setLoginMethod("j2ee");
		//MD5加密
		customerVO.setCustomerPassword(MD5.get(customerVO.getCustomerPassword()));
		//判断用户是否存在
		CustomerVO cuVO = loginRegisterService.validateLogin(customerVO);
		log.info("########cuVO:"+cuVO+"################");
		log.info("########customerId:"+cuVO.getCustomerId()+"################prcFlag:"+cuVO.getPrcFlag());
		if(cuVO!=null && cuVO.getPrcFlag()==3){
			//提示用户名或密码错误
			mode.put("error", "错误次数太多，请稍后再尝试");
			return "login";
		}
		if(cuVO.getPrcFlag()!=null && cuVO.getPrcFlag()==4){
			//用户进入黑名单
			mode.put("error", "该用户已进入黑名单，永久被锁");
			return "login";
		}
		if(cuVO!=null && CommonUtils.isNotBlank(cuVO.getCustomerId())){
			//根据customerId查询用户信息、权限
			CustomerVO customer = loginRegisterService.getCustomerById(cuVO.getCustomerId());
			
			//获取权限信息----------------------
		    /*List<String> roleEnumList = loginRegisterService.getRoleEnumByCustomerId(cuVO.getCustomerId());
		    //权限机制：用户登录的时候查询出该用户的权限存入redis中，修改用户权限时进行替换
		    redisUtil.addListToJedis("role:"+cuVO.getCustomerId(), roleEnumList, -1, null);*/
			//------------------
			
			//验证通过，在session中存入用户信息
			HttpSession session = request.getSession();
			session.setAttribute(CUSTOMERID, customer.getCustomerId());
			session.setAttribute(LOGIN_TIME, new Date().getTime());
			log.info("##########customerId:"+customer.getCustomerId()+"############loginTime:"+new Date().getTime()+"####################");
			mode.put("customerVO", customer);
			if(1==1){
				mode.addAttribute("customerName",cuVO.getCustomerName());
				//request.setAttribute("customerName",cuVO.getCustomerName());
				return "redirect:/manage/toAdminIndex.do?customerId="+cuVO.getCustomerId();
			}else{
				return "index";
			}
			
			//-----判断用户权限，根据权限跳转到后台管理页面或者前台页面-------
			
			
		}else{
			//提示用户名或密码错误
			mode.put("error", "账户名和密码错误");
			return "login";
		}
	}
	
	
	
	/**
	 * 用户登出判断
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout.do")
	public String logout(HttpSession session){
		//清除session
		session.invalidate();
		return "index";
	}
	/**
	 * 注册用户
	 * @param customerVO
	 * @return
	 */
	@RequestMapping(value="/register.do",params="method=register",method=RequestMethod.POST)
	public ModelAndView register(CustomerVO customerVO,HttpServletRequest request)throws Exception{
		ModelAndView mav= new ModelAndView();
		//验证用户是否存在
		//String ss = URLDecoder.decode(customerVO.getCustomerName(),"utf-8");
		Integer unameflag = loginRegisterService.validateRegister(customerVO);
		if(unameflag!=null && unameflag==0){
			 mav.setViewName("register");
        	 mav.addObject("error","邮箱或号码已存在！");
        	 mav.addObject("customerVO",customerVO);
        	 return mav;
		}
		customerVO.setCustomerEmail(customerVO.getCustomerPhoneNo());
		//首先验证手机验证码是否正确,是否超时
		HttpSession session = request.getSession();  
		String code = "";
		String phone = "";
		if(session.getAttribute(VALIDATE_PHONE_CODE)!=null){
			code = session.getAttribute(VALIDATE_PHONE_CODE).toString();  
		}
		if(session.getAttribute(VALIDATE_PHONE)!=null){
			phone = session.getAttribute(VALIDATE_PHONE).toString();  
		}
		Long codeDate = (Long)session.getAttribute(SEND_CODE_TIME);
        String phoneCode = customerVO.getPhoneCode();  
        String customerPhone = customerVO.getCustomerPhoneNo();  
        //验证码是否正确
        if(phone.equals(customerPhone) && code.equalsIgnoreCase(phoneCode)){  
        	//验证码正确后，验证验证码是否超时（存在时间5分钟）
        	if(codeDate!=null){
        		System.out.println(CommonUtils.getInstanceTime().longValue());
        		 if(CommonUtils.getInstanceTime().longValue()-codeDate.longValue()>300000){
        			 mav.setViewName("register");
    	        	 mav.addObject("error","验证码超时，请重新申请发送");
    	        	 mav.addObject("customerVO",customerVO);
    	        	 return mav;
        		 }
        	}
        }else{  
        	mav.setViewName("register");
        	mav.addObject("error","验证码错误");
        	mav.addObject("customerVO",customerVO);
        	return mav;
        }  
        //MD5加密
  		customerVO.setCustomerPassword(MD5.get(customerVO.getCustomerPassword()));
		//新增用户信息
		Integer flag = loginRegisterService.register(customerVO);
		//注册成功
		if(flag==1){
			mav.setViewName("index");
			mav.addObject("customerVO",customerVO);
		}else{
			throw new ApplicationException("注册失败，请稍后再试，如果多次出现该问题，请联系在线服务人员");
		}
		return mav;
	}
	
	/**
	 * 用户电话号码实时校验
	 * @param customerVO
	 * @return
	 */
	@RequestMapping(value="register/validate.do",method=RequestMethod.GET)
	public @ResponseBody String validateCustomerName(@RequestParam("customerPhoneNo") String customerPhoneNo,
													@RequestParam("customerEmail") String customerEmail)throws Exception{
		CustomerVO customerVO = new CustomerVO();
		customerVO.setCustomerPhoneNo(customerPhoneNo);
		customerVO.setCustomerEmail(customerEmail);
		Integer flag = loginRegisterService.validateRegister(customerVO);
		//位0 就是存在该号码或者邮箱
		if(flag == 0){
			return "false";
		}
		return "true";
	}
	
	/**
	 * 发送手机验证码
	 * @return
	 */
	@RequestMapping(value="register/sendCode.do",method=RequestMethod.GET)
	public @ResponseBody String sendPhoneCode(
					@RequestParam("customerPhoneNo") String customerPhoneNo,
					HttpServletRequest request)throws Exception{
		if(CommonUtils.isBlank(customerPhoneNo)){
			return "0";
		}
		String u = "http://121.199.50.122:8888/smsGBK.aspx?action=send&userid=956&account=SZXGCM&password=12345678&mobile=";
		StringBuffer urlsb = new StringBuffer(u);
		//StringBuffer urlsb = new StringBuffer(XsbBusinessConstant.SMSURL);
		int code = SmsUtil.createSmsCode();
		log.info("#################################手机验证码："+code);
		String message = "您申请的手机验证码是："+code+",请输入后进行验证，谢谢！【第一路演】";
		urlsb.append(customerPhoneNo).append("&content=").append(message);
		URL url = new URL(urlsb.toString());
		HttpURLConnection  conn = (HttpURLConnection )url.openConnection();
		//conn.setRequestMethod("GET");
		//conn.setRequestProperty("Accept-Charset", "utf-8");
		//conn.setRequestProperty("contentType", "utf-8");
		conn.setRequestProperty("Accept-Charset", "GBK");
		conn.setRequestProperty("contentType", "GBK");
		conn.connect();
		//读取数据
		//BufferedReader bufIn = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(conn.getInputStream(),"GBK"));
		String line = null;
		StringBuffer contentBuffer = new StringBuffer();
		while((line = bufIn.readLine())!=null)
		{
			contentBuffer.append(line);
		}	
		if(contentBuffer.length()>0 && contentBuffer.toString().indexOf("Success")>0){
			//发送成功则在session中存入手机号码、验证码、发送时间
			HttpSession session = request.getSession();  
	        session.setAttribute(VALIDATE_PHONE, customerPhoneNo);  
	        session.setAttribute(VALIDATE_PHONE_CODE, code);  
	        session.setAttribute(SEND_CODE_TIME, new Date().getTime());  
			return "success";
		}else{
			return "fail";
		}
	}
	
	/**
	 * 忘记密码，根据手机找回密码
	 * @param customerPhoneNo
	 * @return
	 */
	@RequestMapping(value="login/forgetPwd.do",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> forgetPwdByPhone(CustomerVO customerVO,HttpServletRequest request)throws Exception{
		//首先验证手机验证码是否正确,是否超时
		Map<String,Object> map = new HashMap<String,Object>();
		HttpSession session = request.getSession();  
		String code = "";
		String phone = "";
		if(session.getAttribute(VALIDATE_PHONE_CODE)!=null){
			code = session.getAttribute(VALIDATE_PHONE_CODE).toString();  
		}
		if(session.getAttribute(VALIDATE_PHONE)!=null){
			phone = session.getAttribute(VALIDATE_PHONE).toString();  
		}
		Long codeDate = (Long)session.getAttribute(SEND_CODE_TIME);
        String phoneCode = customerVO.getPhoneCode();  
        String customerPhone = customerVO.getCustomerPhoneNo();  
        String customerId = loginRegisterService.getCustomerIdByPhoneOrMail(customerPhone);
        //如果customerId不存在，提示用户，手机号码没有注册
        if(CommonUtils.isBlank(customerId)){
        	map.put(RESULTCODE, ResultCode.ERROR_PHONENO_NULL);
        	return map;
        }
        customerVO.setCustomerId(customerId);
        //验证码是否正确
        if(phone.equals(customerPhone) && code.equalsIgnoreCase(phoneCode)){  
        	//验证码正确后，验证验证码是否超时（存在时间5分钟）
        	if(codeDate!=null){
        		System.out.println(CommonUtils.getInstanceTime().longValue());
        		 if(CommonUtils.getInstanceTime().longValue()-codeDate.longValue()>300000){
        			map.put(RESULTCODE, ResultCode.ERROR_CODE_OUTTIME);
        			return map;
        		 }
        	}
        }else{  
        	map.put(RESULTCODE, ResultCode.ERROR_CODE_ERROR);
        	return map;
        }  
        //MD5加密
  		customerVO.setCustomerPassword(MD5.get(customerVO.getCustomerPassword()));
		//修改用户信息
		Integer flag = loginRegisterService.updatePassword(customerVO);
		//注册成功
		if(flag==1){
			map.put(RESULTCODE, ResultCode.RESPONSE_SUCCESS);
			return map;
		}else{
			map.put(RESULTCODE, ResultCode.RESPONSE_FAIL);
			return map;
		}
	}
	
	
	/**
	 * 老版本PC端注册用户的同时注册新版
	 * @param customerVO
	 * @return
	 */
	/*	public @ResponseBody int register(HttpServletRequest request,
	
	)throws Exception{*/
	@RequestMapping(value="/oldpc/register.do",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> register(
			@RequestParam("customerPassword") String customerPassword,
			@RequestParam("loginMethod") String loginMethod,
			@RequestParam("customerPhoneNo") String customerPhoneNo,
			@RequestParam(value="customerType",required=false) String customerType,
			@RequestParam(value="customerName",required=false) String customerName,
			@RequestParam(value="customerEmail",required=false) String customerEmail,
			@RequestParam(value="customerCompany",required=false) String customerCompany,
			HttpServletRequest request)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//验证参数
		log.info("########################"+customerPhoneNo+"#########################");
		if(CommonUtils.isBlank(customerPassword)||CommonUtils.isBlank(loginMethod)||
				CommonUtils.isBlank(customerPhoneNo)){
			map.put(RESULTCODE, ResultCode.ERROR_PARAMETER_ERROR);
			log.info("##########################################参数有误");
			return map;
		}
		String cutId = loginRegisterService.getCustomerIdByPhoneOrMail(customerPhoneNo);
		if(CommonUtils.isNotBlank(cutId)){
			map.put(RESULTCODE, ResultCode.CUSTOMER_EXIST);
			return map;
		}
		//对可能存在中文的字符转码
		//customerName = URLDecoder.decode(customerName,"utf-8");
		//log.info("###########################################传过来的customerName decode:"+customerName);
        CustomerVO customerVO = new CustomerVO();
        customerVO.setCustomerPhoneNo(customerPhoneNo);
        customerVO.setCustomerPassword(customerPassword);
        //customerVO.setCustomerEmail(customerPhoneNo);
        customerVO.setLoginMethod(loginMethod);
        customerVO.setCustomerEmail(customerEmail);
        customerVO.setCustomerCompany(customerCompany);
        customerVO.setCustomerName(customerName);
        customerVO.setCustomerType(customerType);
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
}
