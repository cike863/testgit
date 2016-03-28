package com.xsbweb.vo;

import java.util.Date;

import com.xsbweb.common.bean.BasePo;

public class Staff extends BasePo{
	//登錄名
		private String loginName;
		//员工id（可以是手机号码、id、邮箱）
		private String staffId;
		//手机验证码
		private String phoneCode;
		
		//员工姓名
		private String staffName;
		//员工部门id
		private String departmentId;
		//员工部门名称
		private String departmentName;
		//有效日期
		private Date effectiveDate;
		//失效日期
		private Date extireDate;
		// 创建时间
		private String createDate;
		// 最后更新时间
		private Date lastUpdateDate;
		// 员工手机号
		private String staffPhoneNo;
		// 员工email
		private String staffEmail;
		// 角色
		private String staffRole;
		// 密码
		private String staffPassword;
		
		private Integer prcFlag;
		
		private String staffQq;
		private String staffTel;
		
		private String staffWebchat;
		
		private String staffPicPath;
		
		private String staffPosition;
		//员工部门名称
		private String[] departmentNames;
		
		public String getStaffPosition() {
			return staffPosition;
		}
		public void setStaffPosition(String staffPosition) {
			this.staffPosition = staffPosition;
		}
		public String getStaffWebchat() {
			return staffWebchat;
		}
		public void setStaffWebchat(String staffWebchat) {
			this.staffWebchat = staffWebchat;
		}
		public String getStaffPicPath() {
			return staffPicPath;
		}
		public void setStaffPicPath(String staffPicPath) {
			this.staffPicPath = staffPicPath;
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
		public String getLoginName() {
			return loginName;
		}
		public void setLoginName(String loginName) {
			this.loginName = loginName;
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
		public String getCreateDate() {
			return createDate;
		}
		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}
		public Date getLastUpdateDate() {
			return lastUpdateDate;
		}
		public void setLastUpdateDate(Date lastUpdateDate) {
			this.lastUpdateDate = lastUpdateDate;
		}
		
		public String getStaffId() {
			return staffId;
		}
		public void setStaffId(String staffId) {
			this.staffId = staffId;
		}
		public String getStaffName() {
			return staffName;
		}
		public void setStaffName(String staffName) {
			this.staffName = staffName;
		}
		public String getDepartmentId() {
			return departmentId;
		}
		public void setDepartmentId(String departmentId) {
			this.departmentId = departmentId;
		}
		
		public String getStaffPhoneNo() {
			return staffPhoneNo;
		}
		public void setStaffPhoneNo(String staffPhoneNo) {
			this.staffPhoneNo = staffPhoneNo;
		}
		public String getStaffEmail() {
			return staffEmail;
		}
		public void setStaffEmail(String staffEmail) {
			this.staffEmail = staffEmail;
		}
		public String getStaffRole() {
			return staffRole;
		}
		public void setStaffRole(String staffRole) {
			this.staffRole = staffRole;
		}
		public String getStaffPassword() {
			return staffPassword;
		}
		public void setStaffPassword(String staffPassword) {
			this.staffPassword = staffPassword;
		}
		
		
		public String getStaffQq() {
			return staffQq;
		}
		public void setStaffQq(String staffQq) {
			this.staffQq = staffQq;
		}
		
		public String getStaffTel() {
			return staffTel;
		}
		public void setStaffTel(String staffTel) {
			this.staffTel = staffTel;
		}
		public String getDepartmentName() {
			return departmentName;
		}
		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}
		public String[] getDepartmentNames() {
			return departmentNames;
		}
		public void setDepartmentNames(String[] departmentNames) {
			this.departmentNames = departmentNames;
		}
		
}