package com.xsbweb.interceptor;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.common.bean.DataBaseConstant;
import com.xsbweb.service.AppConfigService;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.util.GetIp;
import com.xsbweb.util.MultipleDataSource;
import com.xsbweb.vo.AppToJ2eeLog;

/**
 * 改拦截器主要用来记录app和java之间的调用日志
 * @author Administrator
 *
 */
public class AppToJ2eeInterceptor implements HandlerInterceptor{
	
	private static String RESULTCODE = "resultCode";
	private static String RESULTONJECT = "resultObject";
	
	private Logger log = Logger.getLogger(AppToJ2eeInterceptor.class);
	
	@Resource
	private AppConfigService appConfigService;
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView mav) throws Exception {
		
			Map<String,Object> map = (HashMap<String,Object>)request.getAttribute(RESULTONJECT);
			if(map==null){
				return;
			}
			//完整访问url
			String parm = request.getQueryString();
			String url = request.getRequestURL().toString()+"?"+parm;
			//返回的json格式
			JSONObject objJson = JSONObject.fromObject(map);
			String objJsonStr = "";
			if(objJson!=null){
				objJsonStr = objJson.toString();
				//入库最大字符串长度不超过4000
				if(objJsonStr.length()>4000){
					objJsonStr = objJsonStr.substring(0, 3999);
				}
			}
			//当前时间
			String now = CommonUtils.getInstanceDate();
			//获取客户端ip地址
			String ip = GetIp.getIpAddr(request);
			//返回状态
			Integer resultCode = (Integer)map.get(RESULTCODE);
			
			String tmpToken = "";
			//请求用户(手机号码)
			String phone = "";
			//请求终端类型
			String loginMethod = "";
			Map<String, String[]> params = request.getParameterMap();  
			for (String key : params.keySet()) {
				 	if("tmpToken".equals(key)){
				 		tmpToken = params.get(key)[0];
				 	}
				 	if("customerPhoneNo".equals(key)){
				 		phone = params.get(key)[0];
				 	}
				 	if("loginMethod".equals(key)){
				 		loginMethod = params.get(key)[0];
				 	}
			}
			try {
				AppToJ2eeLog ajVO = new AppToJ2eeLog();
				ajVO.setReqId(phone);
				ajVO.setReqIp(ip);
				ajVO.setReqMethod(loginMethod);
				ajVO.setRequest(url);
				ajVO.setResponse(objJsonStr);
				ajVO.setResStatus(resultCode==null?"":String.valueOf(resultCode));
				ajVO.setReqDate(now);
				//插入日志
				MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
				int flag = appConfigService.insertAppToJ2eeLog(ajVO);
				if(flag==0){
					log.info("############时间："+now+",手机号为"+phone+"的用户请求："+url+" ，插入调用日志失败");
				}
		} catch (Exception e) {
			log.info("############时间："+now+",手机号为"+phone+"的用户请求："+url+" ，插入调用日志失败");
			e.printStackTrace();
			return;
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		
		
		
		return true;
	}

}
