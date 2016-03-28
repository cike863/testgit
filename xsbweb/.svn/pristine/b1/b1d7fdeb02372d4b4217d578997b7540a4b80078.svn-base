package com.xsbweb.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.xsbweb.cloopen.rest.InitRestContants;
import com.xsbweb.cloopen.rest.NewSmsRest;

public class NewSmsUtil {

	public static String getBalance() throws UnsupportedEncodingException {

		String userName = InitRestContants.NEW_SMS_UNAME;
		String password = InitRestContants.NEW_SMS_PWD;
		NewSmsRest NewSmsRest = new NewSmsRest(userName, password);

		// 查询余额
		String result_balance = NewSmsRest.getBalance();

		if (result_balance.startsWith("-")) {
			System.out.print("发送失败！返回值为：" + result_balance + "请查看HTTP返回值对照表");
		}
		System.out.print("您的余额为 : " + result_balance);
		return result_balance;
	}

	public static long sendSms(String content,String mobile) throws UnsupportedEncodingException {
		String userName = InitRestContants.NEW_SMS_UNAME;
		String password = InitRestContants.NEW_SMS_PWD;
		NewSmsRest NewSmsRest = new NewSmsRest(userName, password);
		String result = NewSmsRest.mt(URLEncoder.encode(content, "UTF-8"),
				mobile, "", "", "", "");
		System.out.println(result);
		String strCode = result.split("\n")[0];
		long code = 0;
		code = Long.valueOf(strCode);
		String Info = null;
		if (code > 0) {// 成功提交
			Info = "发送成功";
		} else if (code == 0) {
			Info = "发送失败";
		} else if (code == -1) { // 用户名密码不正确
			Info = "用户名密码不正确";
		} else if (code == -2) { // 必填选项为空
			Info = "必填选项为空";
		} else if (code == -3) { // 短信内容0个字节
			Info = "短信内容0个字节";
		} else if (code == -4) { // 0个有效号码
			Info = "0个有效号码";
		} else if (code == -5) { // 余额不够
			Info = "余额不够";
		} else if (code == -10) { // 用户被禁用
			Info = "用户被禁用";
		} else if (code == -11) { // 短信内容过长
			Info = "短信内容过长";
		} else if (code == -12) { // 用户无扩展权限
			Info = "无扩展权限";
		} else if (code == -13) { // IP地址校验错
			Info = "IP校验错误";
		} else if (code == -14) { // 内容解析异常
			Info = "内容解析异常";
		} else {
			Info = "未知错误";
		}
		System.out.println("返回信息：" + Info + "--" + code + "--"
				+ NewSmsRest.getPwd());
		return code;
	}

	/**
	 * 个性短信(一个号码对应一条内容)
	 * 
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static long sendSmsGX(String[] mobiles,String[] contents) throws UnsupportedEncodingException {
		String userName = InitRestContants.NEW_SMS_UNAME;
		String password = InitRestContants.NEW_SMS_PWD;
		NewSmsRest NewSmsRest = new NewSmsRest(userName, password);

		String charset = "UTF-8";
		String mobile = "13888888888,13588888888";
		String content1 = "您的验证码是：11111【企业签名】";
		String content2 = "XX先生/女士，今天是您的生日，祝您生日快乐！【企业签名】";

		String content = URLEncoder.encode(content1, charset) + ","
				+ URLEncoder.encode(content2, charset);
		String ecodeContent = URLEncoder.encode(content, charset);
		String result = NewSmsRest.mtData(ecodeContent, mobile, "", "", "",
				charset);
		System.out.println(result);
		String strCode = result.split("\n")[0];
		long code = 0;
		code = Long.valueOf(strCode);
		String Info = null;
		if (code > 0) {// 成功提交
			Info = "发送成功";
		} else if (code == 0) {
			Info = "发送失败";
		} else if (code == -1) { // 用户名密码不正确
			Info = "用户名密码不正确";
		} else if (code == -2) { // 必填选项为空
			Info = "必填选项为空";
		} else if (code == -3) { // 短信内容0个字节
			Info = "短信内容0个字节";
		} else if (code == -4) { // 0个有效号码
			Info = "0个有效号码";
		} else if (code == -5) { // 余额不够
			Info = "余额不够";
		} else if (code == -10) { // 用户被禁用
			Info = "用户被禁用";
		} else if (code == -11) { // 短信内容过长
			Info = "短信内容过长";
		} else if (code == -12) { // 用户无扩展权限
			Info = "无扩展权限";
		} else if (code == -13) { // IP地址校验错
			Info = "IP校验错误";
		} else if (code == -14) { // 内容解析异常
			Info = "内容解析异常";
		} else {
			Info = "未知错误";
		}
		System.out.println("返回信息：" + Info + "--" + code + "--"
				+ NewSmsRest.getPwd());
		return code;
	}

	/**
	 * 修改密码
	 * 
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static String updatePwd(String newPassword) throws UnsupportedEncodingException {
		String userName = InitRestContants.NEW_SMS_UNAME;
		String password = InitRestContants.NEW_SMS_PWD;

		NewSmsRest newSmsRest = new NewSmsRest(userName, password);
		String result = newSmsRest.UpdatePassword(newPassword);
		System.out.println("返回结果：" + result);
		return result;
	}
	
	
	public static void main(String[] args) throws Exception {
		//String yue = getBalance();
		//long code = sendSms("您申请的手机验证码是：3659,请输入后进行验证，谢谢！【第一路演】", "18124198820");
		
	}
}
