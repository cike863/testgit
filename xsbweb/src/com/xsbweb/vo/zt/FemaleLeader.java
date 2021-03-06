package com.xsbweb.vo.zt;

import com.xsbweb.common.bean.BasePo;
import com.xsbweb.util.CommonUtils;

public class FemaleLeader extends BasePo{
	//公司名称
	private String companyName;
	//修改后公司名称
	private String newCompanyName;
	//候选人姓名
	private String leaderName;
	//候选人姓名
	private String newLeaderName;
	//得票数
	private int votedCnt;
	//0:候选人审核，1:开放投票，2:报名截止，3:取消报名资格
	private String votedStatus;
	//候选人手机号
	private String leaderMobile;
	//候选口号
	private String candidateSlogan;
	//公司简介
	private String companyInfo;
	//任务头像存储路径
	private String leaderPicPath;
	//公司图标存储路径
	private String companyPicPath;
	
	private String createdDate;
	//排序方式
	private String orderType="1";
	
	private String leaderSource;
	
	private int orderNo;
	//股票代码
	private String stockId;
	//排序字段
	private int orderNoEx;
	
	public String getLeaderSource() {
		return leaderSource;
	}
	public void setLeaderSource(String leaderSource) {
		this.leaderSource = leaderSource;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getCreatedDate() {
		return CommonUtils.getNowDateStringOf8();
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public String getVotedStatus() {
		return votedStatus;
	}
	public void setVotedStatus(String votedStatus) {
		this.votedStatus = votedStatus;
	}
	public String getLeaderMobile() {
		return leaderMobile;
	}
	public void setLeaderMobile(String leaderMobile) {
		this.leaderMobile = leaderMobile;
	}
	public String getCandidateSlogan() {
		return candidateSlogan;
	}
	public void setCandidateSlogan(String candidateSlogan) {
		this.candidateSlogan = candidateSlogan;
	}
	public String getCompanyInfo() {
		return companyInfo;
	}
	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}
	public String getLeaderPicPath() {
		return leaderPicPath;
	}
	public void setLeaderPicPath(String leaderPicPath) {
		this.leaderPicPath = leaderPicPath;
	}
	public String getCompanyPicPath() {
		return companyPicPath;
	}
	public void setCompanyPicPath(String companyPicPath) {
		this.companyPicPath = companyPicPath;
	}
	public int getVotedCnt() {
		return votedCnt;
	}
	public void setVotedCnt(int votedCnt) {
		this.votedCnt = votedCnt;
	}
	public String getNewCompanyName() {
		return newCompanyName;
	}
	public void setNewCompanyName(String newCompanyName) {
		this.newCompanyName = newCompanyName;
	}
	public String getNewLeaderName() {
		return newLeaderName;
	}
	public void setNewLeaderName(String newLeaderName) {
		this.newLeaderName = newLeaderName;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public int getOrderNoEx() {
		return orderNoEx;
	}
	public void setOrderNoEx(int orderNoEx) {
		this.orderNoEx = orderNoEx;
	}
	
}
