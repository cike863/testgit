package com.xsbweb.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.util.BaseUtil;
import com.xsbweb.util.CommonUtils;

/**
 * 登录验证拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor{

	private  String[] noturls = {"/staffLogin.do","/staffRegister.do","/staffToRegister.do","/staffToForgetPwd.do",
			"/staffRegister/sendCode.do","/staffRegister/validate.do","/login/staffForgetPwd.do","/toLogin.do",
			"/toIndex.do","/toRegister.do","/toForgetPwd.do","/login.do","/logout.do","/register.do",
			"/register/validate.do","/register/sendCode.do","/login/forgetPwd.do",
			"/admin/customer/","/meet/createconfsucc.do","/meet/joinConfsucc.do","/meet/joinConfsucc.do",
			"/meet/operation.do","/meet/delMeetRoom.do","/meet/inviteResult.do","/meet/MeetRoom/{meetNoArrs}/del",
			"/meet/videoMeetRoom/{meetNoArrs}/del","wap/meet/toWapMeetRoomIndex.do","/meet/quitConfsucc.do",
			"/zt/womenday/","/admin/sql/","/ueditor"
	};
		
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		
		String url = request.getRequestURL().toString();
		//如果是app通过url访问，则不拦截
		//if(url.indexOf("/app/")>0 || url.indexOf("/meet/")>0 || url.indexOf("/process/")>0){
		if(url.indexOf("/app/")>0 || url.indexOf("/process/")>0){
			return true;
		}
		for (String urldo : noturls) {
			if(url.indexOf(urldo)>0){
				return true;
			}
		}
		/*//判断session中是否存在用户信息
		HttpSession session = request.getSession();
		String customerId = (String)session.getAttribute("customerId");
		Long loginDate = (Long)session.getAttribute("loginTime");
		Long nowDate = CommonUtils.getInstanceTime();
		if(loginDate!=null){
			//超过半小时
			if(loginDate.longValue()-nowDate>900000){
				request.getRequestDispatcher("/staffLogin.do").forward(request, response);
				return false;
			}
		}
		//若果customerName为空，说明没有进行登录
		if(CommonUtils.isBlank(customerId) || loginDate==null){
			request.getRequestDispatcher("/staffLogin.do").forward(request, response);
			return false;
		}
		//如果每次访问没有被拦截，则更新session中的login_date_time
		session.setAttribute("loginTime", nowDate);*/
		
		//判断cookie里面是否有用户信息
		String customerId = BaseUtil.getCookieValue("customerId", request);
		if(CommonUtils.isBlank(customerId)){
			request.getRequestDispatcher("/staffLogin.do").forward(request, response);
			return false;
		}
		return true;
	}
}
