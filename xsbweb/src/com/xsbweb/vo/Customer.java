package com.xsbweb.vo;

import java.util.Date;

import com.xsbweb.common.bean.BasePo;

public class Customer extends BasePo {

	private String customerId;

	private String customerName="";

	private String customerType="";

	private Date effectiveDate;

	private Date extireDate;

	private Date createDate;
	
	private Date sartQueryDate;
	
	private Date endQueryDate;

	private Date lastUpdateDate;

	private String customerPassword;

	private String customerPhoneNo="";

	private String customerQq="";
	private String customerWechat="";
	private String customerSex="";
	private String customerPosition="";
	private String customerNickname="";

	private Long customerPrivilege1;

	private Long customerPrivilege2;

	private Long customerPrivilege3;

	private Long customerPrivilege4;

	private String customerEmail="";

	private String custLoginStatus="";

	private Date lastLoginDate;

	private Byte curAuthFailed;

	private String customerCompany="";

	
    //关注行业
    private Long gzIndustry;
    
    private Long[] gzIndustryArr;
    //资金规模
    private String capitalScale="";
    //活动Id
    private String objectNo="";
    
    //活动报名状态
    private String signStatus="";
    //头像
    private String headPicPath="";
    
    private String customerMediaNo="";
    
    private String customerCases="";
    
    //认证状态
    private String authentStatus="";
    //认证图片No1
    private String authentMediaNo1="";
    //认证图片No2
    private String authentMediaNo2="";
    //认证图片路径1
    private String authentMediaPath1="";
    //认证图片路径2
    private String authentMediaPath2="";
    //用户地址
    private String customerAddress="";
    //用户身份证号
    private String customerCardId="";
    //组装机构代码
    private String customerInstttCode="";
    //用户报名中项目/视频状态
    private String roleStatus;
    
	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}

	public String getAuthentMediaNo1() {
		return authentMediaNo1;
	}

	public void setAuthentMediaNo1(String authentMediaNo1) {
		this.authentMediaNo1 = authentMediaNo1;
	}

	public String getAuthentMediaNo2() {
		return authentMediaNo2;
	}

	public void setAuthentMediaNo2(String authentMediaNo2) {
		this.authentMediaNo2 = authentMediaNo2;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerCardId() {
		return customerCardId;
	}

	public void setCustomerCardId(String customerCardId) {
		this.customerCardId = customerCardId;
	}

	public String getCustomerInstttCode() {
		return customerInstttCode;
	}

	public void setCustomerInstttCode(String customerInstttCode) {
		this.customerInstttCode = customerInstttCode;
	}

	public String getAuthentMediaPath1() {
		return authentMediaPath1;
	}

	public void setAuthentMediaPath1(String authentMediaPath1) {
		this.authentMediaPath1 = authentMediaPath1;
	}

	public String getAuthentMediaPath2() {
		return authentMediaPath2;
	}

	public void setAuthentMediaPath2(String authentMediaPath2) {
		this.authentMediaPath2 = authentMediaPath2;
	}

	public Long[] getGzIndustryArr() {
		return gzIndustryArr;
	}

	public void setGzIndustryArr(Long[] gzIndustryArr) {
		this.gzIndustryArr = gzIndustryArr;
	}

	public String getCustomerMediaNo() {
		return customerMediaNo;
	}

	public void setCustomerMediaNo(String customerMediaNo) {
		this.customerMediaNo = customerMediaNo;
	}

	public String getHeadPicPath() {
		return headPicPath;
	}

	public void setHeadPicPath(String headPicPath) {
		this.headPicPath = headPicPath;
	}

	public Long getGzIndustry() {
		return gzIndustry;
	}

	public void setGzIndustry(Long gzIndustry) {
		this.gzIndustry = gzIndustry;
	}

	public String getCapitalScale() {
		return capitalScale;
	}

	public void setCapitalScale(String capitalScale) {
		this.capitalScale = capitalScale;
	}

	public String getObjectNo() {
		return objectNo;
	}

	public void setObjectNo(String objectNo) {
		this.objectNo = objectNo;
	}

	public String getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}

	public String getCustomerQq() {
		return customerQq;
	}

	public void setCustomerQq(String customerQq) {
		this.customerQq = customerQq;
	}

	public String getCustomerWechat() {
		return customerWechat;
	}

	public void setCustomerWechat(String customerWechat) {
		this.customerWechat = customerWechat;
	}

	public String getCustomerSex() {
		return customerSex;
	}

	public void setCustomerSex(String customerSex) {
		this.customerSex = customerSex;
	}

	public String getCustomerPosition() {
		return customerPosition;
	}

	public void setCustomerPosition(String customerPosition) {
		this.customerPosition = customerPosition;
	}

	public String getCustomerNickname() {
		return customerNickname;
	}

	public void setCustomerNickname(String customerNickname) {
		this.customerNickname = customerNickname;
	}

	public String getCustomerCompany() {
		return customerCompany;
	}

	public void setCustomerCompany(String customerCompany) {
		this.customerCompany = customerCompany;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName == null ? null : customerName.trim();
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType == null ? null : customerType.trim();
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExtireDate() {
		return extireDate;
	}

	public void setExtireDate(Date extireDate) {
		this.extireDate = extireDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword == null ? null
				: customerPassword.trim();
	}

	public String getCustomerPhoneNo() {
		return customerPhoneNo;
	}

	public void setCustomerPhoneNo(String customerPhoneNo) {
		this.customerPhoneNo = customerPhoneNo == null ? null : customerPhoneNo
				.trim();
	}

	public Long getCustomerPrivilege1() {
		return customerPrivilege1;
	}

	public void setCustomerPrivilege1(Long customerPrivilege1) {
		this.customerPrivilege1 = customerPrivilege1;
	}

	public Long getCustomerPrivilege2() {
		return customerPrivilege2;
	}

	public void setCustomerPrivilege2(Long customerPrivilege2) {
		this.customerPrivilege2 = customerPrivilege2;
	}

	public Long getCustomerPrivilege3() {
		return customerPrivilege3;
	}

	public void setCustomerPrivilege3(Long customerPrivilege3) {
		this.customerPrivilege3 = customerPrivilege3;
	}

	public Long getCustomerPrivilege4() {
		return customerPrivilege4;
	}

	public void setCustomerPrivilege4(Long customerPrivilege4) {
		this.customerPrivilege4 = customerPrivilege4;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail == null ? null : customerEmail
				.trim();
	}

	public String getCustLoginStatus() {
		return custLoginStatus;
	}

	public void setCustLoginStatus(String custLoginStatus) {
		this.custLoginStatus = custLoginStatus == null ? null : custLoginStatus
				.trim();
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Byte getCurAuthFailed() {
		return curAuthFailed;
	}

	public void setCurAuthFailed(Byte curAuthFailed) {
		this.curAuthFailed = curAuthFailed;
	}

	public String getCustomerCases() {
		return customerCases;
	}

	public void setCustomerCases(String customerCases) {
		this.customerCases = customerCases == null ? null : customerCases
				.trim();
	}

	public String getAuthentStatus() {
		return authentStatus;
	}

	public void setAuthentStatus(String authentStatus) {
		this.authentStatus = authentStatus == null ? null : authentStatus
				.trim();
	}

	public Date getSartQueryDate() {
		return sartQueryDate;
	}

	public void setSartQueryDate(Date sartQueryDate) {
		this.sartQueryDate = sartQueryDate;
	}

	public Date getEndQueryDate() {
		return endQueryDate;
	}

	public void setEndQueryDate(Date endQueryDate) {
		this.endQueryDate = endQueryDate;
	}
	
	
}