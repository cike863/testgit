package com.xsbweb.vo.zt;

import com.xsbweb.util.CommonUtils;

public class TrsVote {

	private String companyName;
	
	private String leaderName;
	
	private String wxSubsId;
	
	private Integer prcFlag;

	private String createdDate;
	
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

	public String getWxSubsId() {
		return wxSubsId;
	}

	public void setWxSubsId(String wxSubsId) {
		this.wxSubsId = wxSubsId;
	}

	public Integer getPrcFlag() {
		return prcFlag;
	}

	public void setPrcFlag(Integer prcFlag) {
		this.prcFlag = prcFlag;
	}
	
}
