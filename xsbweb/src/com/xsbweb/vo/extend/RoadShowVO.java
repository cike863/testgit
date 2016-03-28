package com.xsbweb.vo.extend;

import java.util.Date;

import com.xsbweb.common.bean.BasePo;


public class RoadShowVO extends BasePo {
	private String customerId;
	private String isVenture;
	private String financingType;
	private String turnoverScale;
	private Date creatDate;
	private Date lastUpdateDate;
	private String name;
	private String position;
	private String phoneNo;
	private String wx;
	private String email;
	private String company;
	private String step;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId == null ? null : customerId.trim();
	}
	public String getIsVenture() {
		return isVenture;
	}
	public void setIsVenture(String isVenture) {
		this.isVenture = isVenture == null ? null : isVenture.trim();
	}
	public String getFinancingType() {
		return financingType;
	}
	public void setFinancingType(String financingType) {
		this.financingType = financingType == null ? null : financingType.trim();
	}
	public String getTurnoverScale() {
		return turnoverScale;
	}
	public void setTurnoverScale(String turnoverScale) {
		this.turnoverScale = turnoverScale == null ? null : turnoverScale.trim();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position == null ? null : position.trim();
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo == null ? null : phoneNo.trim();
	}
	public String getWx() {
		return wx;
	}
	public void setWx(String wx) {
		this.wx = wx == null ? null : wx.trim();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company == null ? null : company.trim();
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step == null ? null : step.trim();
	}
	public Date getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	
}
