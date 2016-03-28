package com.xsbweb.vo;

import com.xsbweb.common.bean.BasePo;

public class RObject extends BasePo{

	private String objectId="";
	
	private String objectPicNo="";
	
	private String sharePicNo="";
	
	private String bigPicNo="";
	
	private String bigPicPath="";
	
	private String objectPicPath="";
	
	private String sharePicPath="";
	
	private String textDesc="";
	//上线时间
	private String onlineDate="";
	//直播开始时间
	private String showDate="";
	//浏览量
	private Integer pv=0;
	//会议创建时间
	private String confCreateDate="";
	//列表用图片
	private String objName="";
	//新闻来源
	private String newsSource="";

	private String newsTitle="";
	//实时行情
	private String projDynQuotes="";
	//辅导机构
	private String projCoachOrg="";
	//转让方式
	private String projAssignmentMay="";
	//报名人数
	private String projApplyCounts="";
	//融资百分比
	private String projPercentage="";

	private String meetType="";
	//开始时长
	private String startMinute="";

	private String newsType="";
	
	//首页轮播的类型
	private String objectType="";
	
	private String confId="";
	
	//视频直播第三方连接url
	private String baseUrl="";
	
	private String role="";
	
	private String shareUrl="";

	private String stockId="";
	
	//标示轮播的跳转位置
	private String redirect="";
	//预告是否有视频
	private String isHaveVideo="0";
	
	public String getIsHaveVideo() {
		return isHaveVideo;
	}

	public void setIsHaveVideo(String isHaveVideo) {
		this.isHaveVideo = isHaveVideo;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public String getBigPicNo() {
		return bigPicNo;
	}

	public void setBigPicNo(String bigPicNo) {
		this.bigPicNo = bigPicNo;
	}

	public String getBigPicPath() {
		return bigPicPath;
	}

	public void setBigPicPath(String bigPicPath) {
		this.bigPicPath = bigPicPath;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getConfId() {
		return confId;
	}

	public void setConfId(String confId) {
		this.confId = confId;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectPicPath() {
		return objectPicPath;
	}

	public void setObjectPicPath(String objectPicPath) {
		this.objectPicPath = objectPicPath;
	}

	public String getSharePicPath() {
		return sharePicPath;
	}

	public void setSharePicPath(String sharePicPath) {
		this.sharePicPath = sharePicPath;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(String startMinute) {
		this.startMinute = startMinute;
	}

	public String getMeetType() {
		return meetType;
	}

	public void setMeetType(String meetType) {
		this.meetType = meetType;
	}

	public String getOnlineDate() {
		return onlineDate;
	}

	public void setOnlineDate(String onlineDate) {
		this.onlineDate = onlineDate;
	}

	public String getShowDate() {
		return showDate;
	}

	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}

	public Integer getPv() {
		return pv;
	}

	public void setPv(Integer pv) {
		this.pv = pv;
	}

	public String getConfCreateDate() {
		return confCreateDate;
	}

	public void setConfCreateDate(String confCreateDate) {
		this.confCreateDate = confCreateDate;
	}


	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public String getNewsSource() {
		return newsSource;
	}

	public void setNewsSource(String newsSource) {
		this.newsSource = newsSource;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getProjDynQuotes() {
		return projDynQuotes;
	}

	public void setProjDynQuotes(String projDynQuotes) {
		this.projDynQuotes = projDynQuotes;
	}

	public String getProjCoachOrg() {
		return projCoachOrg;
	}

	public void setProjCoachOrg(String projCoachOrg) {
		this.projCoachOrg = projCoachOrg;
	}

	public String getProjAssignmentMay() {
		return projAssignmentMay;
	}

	public void setProjAssignmentMay(String projAssignmentMay) {
		this.projAssignmentMay = projAssignmentMay;
	}

	public String getProjApplyCounts() {
		return projApplyCounts;
	}

	public void setProjApplyCounts(String projApplyCounts) {
		this.projApplyCounts = projApplyCounts;
	}

	public String getProjPercentage() {
		return projPercentage;
	}

	public void setProjPercentage(String projPercentage) {
		this.projPercentage = projPercentage;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getObjectPicNo() {
		return objectPicNo;
	}

	public void setObjectPicNo(String objectPicNo) {
		this.objectPicNo = objectPicNo;
	}

	public String getSharePicNo() {
		return sharePicNo;
	}

	public void setSharePicNo(String sharePicNo) {
		this.sharePicNo = sharePicNo;
	}

	public String getTextDesc() {
		return textDesc;
	}

	public void setTextDesc(String textDesc) {
		this.textDesc = textDesc;
	}
	
}
