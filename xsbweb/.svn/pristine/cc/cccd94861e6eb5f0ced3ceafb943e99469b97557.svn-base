package com.xsbweb.vo.extend;

import java.util.HashSet;
import java.util.Set;

import com.xsbweb.vo.Customer;

public class CustomerVO extends Customer {

	//用户名（可以是手机号码、id、邮箱）
	private String customerUname;
	//手机验证码
	private String phoneCode;
	//获取客户端ip地址
	private String ip;
	
	private Integer prcFlag;
	//记录是哪一个访问，j2ee、android、ios
	private String loginMethod;
	//登录失败次数
	private Integer failedCounts;
	
	private String newPassword;
	
	//号码集合
	private String[] customerPhoneNos;
	//用户id集合
	private Set<String> customerIds=new HashSet<String>();
	
	public String[] getCustomerPhoneNos() {
		return customerPhoneNos;
	}

	public void setCustomerPhoneNos(String[] customerPhoneNos) {
		this.customerPhoneNos = customerPhoneNos;
	}
	
	public Set<String> getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(Set<String> customerIds) {
		this.customerIds = customerIds;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Integer getFailedCounts() {
		return failedCounts;
	}

	public void setFailedCounts(Integer failedCounts) {
		this.failedCounts = failedCounts;
	}

	public String getLoginMethod() {
		return loginMethod;
	}

	public void setLoginMethod(String loginMethod) {
		this.loginMethod = loginMethod;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCustomerUname() {
		return customerUname;
	}

	public void setCustomerUname(String customerUname) {
		this.customerUname = customerUname;
	}
	
	public Integer getPrcFlag() {
		return prcFlag;
	}

	public void setPrcFlag(Integer prcFlag) {
		this.prcFlag = prcFlag;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}
}
