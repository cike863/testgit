package com.xsbweb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.xsbweb.cloopen.rest.InitRestContants;
import com.xsbweb.service.MeetRoomService;
import com.xsbweb.vo.ImSubAccount;

@Controller
public class SDKSubAccountController {
	
	@Resource
	private MeetRoomService meetRoomService;
	
	private static Logger log = Logger.getLogger(SDKSubAccountController.class);

	/**
	 * 创建容联子账户
	 * @return
	 */
	@RequestMapping(value="/meet/createImSubAccount.do")
	public ModelAndView createImSubAccount(){
		//创建100账户
		for (int i = 100; i < 1000; i++) {
			String accountName = "xsbaccount"+i;
			createImSubAccountUtil(accountName);
		}
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("success");
		return mav;
	}
	
	public void createImSubAccountUtil(String accountName){
		HashMap<String, Object> result = null;
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init(InitRestContants.ServerIP, InitRestContants.ServerPort);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(InitRestContants.AccountSid, InitRestContants.AccountToken);// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(InitRestContants.AppId);// 初始化应用ID
		result = restAPI.createSubAccount(accountName);

		log.info("SDKSubAccountUtil.createSubAccount("+accountName+") result=" + result);
		
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			if(data != null){
				List<HashMap<String,Object>> saccount = (ArrayList<HashMap<String,Object>> )data.get("SubAccount");
				if(saccount!=null && !saccount.isEmpty()){
					HashMap<String,Object> accountMap = saccount.get(0);
					ImSubAccount subAccount = new ImSubAccount();
					subAccount.setImAccountId(accountName);
					subAccount.setSubAccountSid((String)accountMap.get("subAccountSid"));
					subAccount.setSubToken((String)accountMap.get("subToken"));
					subAccount.setDateCreated((String)accountMap.get("dateCreated"));
					subAccount.setVoipAccount((String)accountMap.get("voipAccount"));
					subAccount.setVoipPwd((String)accountMap.get("voipPwd"));
					try {
						//插入账户信息进入数据库
						meetRoomService.insertImSubAccount(subAccount);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}else{
			//异常返回输出错误码和错误信息
			log.info("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
	}
}
