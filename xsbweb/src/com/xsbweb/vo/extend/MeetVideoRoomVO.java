package com.xsbweb.vo.extend;

import java.util.ArrayList;
import java.util.List;

import com.xsbweb.common.bean.BasePo;
import com.xsbweb.vo.ProjectItem;
import com.xsbweb.vo.TrsMedia;
import com.xsbweb.vo.TrsProject;

public class MeetVideoRoomVO extends BasePo{

	//对应项目
	private TrsProject trsProject = new TrsProject();
	//往期回顾集合
	private List<TrsMedia> oldMediaList = new ArrayList<TrsMedia>();
	
	private List<ProjectItem> videoItemList = new ArrayList<ProjectItem>();
	
	private String meetNo;
	private String confid;
	private String meetMediaNo="";
	private String status="";
	private String shareMediaNo="";
	private String bigPicNo="";
	private String onlineDate="";
	private String confCreateDate="";
	private String showDate="";
	private String pv="";
	private String confName="";
	private String activityProfile="";
	private String activityBackground="";
	private String guest="";
	private String activitySchedule="";
	private String createDate="";
	private String isShow="";
	
	private String meetRole="";
	
	private Integer prcFlag;
	
	private String baseUrl="";
	//列表用图片
	private String meetMediaPath="";
	//分享用图片
	private String shareMediaPath="";
	//广告图片
	private String bigMediaPath="";
	//对应项目编号
	private String meetProjectNo="";
	//分享主标题
	private String meetTitle="";
	//分享副标题
	private String meetExTitle="";
	//活动参与人数
	private int maxMeetPersonAmt;
	//配文
	private String meetDesc="";
		
	//对应的项目名称
	private String meetProjectName="";
	
	private String lastEditDate="";
	//分享url
	private String sharUrl="";
	
	private String handler;
	
	//提醒时间
	private String startMinute="";
	
	public String getStartMinute() {
		return startMinute;
	}
	public void setStartMinute(String startMinute) {
		this.startMinute = startMinute;
	}
	public String getSharUrl() {
		return sharUrl;
	}
	public void setSharUrl(String sharUrl) {
		this.sharUrl = sharUrl;
	}
	public List<ProjectItem> getVideoItemList() {
		return videoItemList;
	}
	public void setVideoItemList(List<ProjectItem> videoItemList) {
		this.videoItemList = videoItemList;
	}
	public List<TrsMedia> getOldMediaList() {
		return oldMediaList;
	}
	public void setOldMediaList(List<TrsMedia> oldMediaList) {
		this.oldMediaList = oldMediaList;
	}
	public TrsProject getTrsProject() {
		return trsProject;
	}
	public void setTrsProject(TrsProject trsProject) {
		this.trsProject = trsProject;
	}
	public String getMeetMediaPath() {
		return meetMediaPath;
	}
	public void setMeetMediaPath(String meetMediaPath) {
		this.meetMediaPath = meetMediaPath == null ? null : meetMediaPath.trim();
	}
	public String getShareMediaPath() {
		return shareMediaPath;
	}
	public void setShareMediaPath(String shareMediaPath) {
		this.shareMediaPath = shareMediaPath == null ? null : shareMediaPath.trim();
	}
	public String getMeetProjectNo() {
		return meetProjectNo;
	}
	public void setMeetProjectNo(String meetProjectNo) {
		this.meetProjectNo = meetProjectNo == null ? null : meetProjectNo.trim();
	}
	public String getMeetTitle() {
		return meetTitle;
	}
	public void setMeetTitle(String meetTitle) {
		this.meetTitle = meetTitle == null ? null : meetTitle.trim().replaceAll("　","");;
	}
	public String getMeetExTitle() {
		return meetExTitle;
	}
	public void setMeetExTitle(String meetExTitle) {
		this.meetExTitle = meetExTitle == null ? null : meetExTitle.trim().replaceAll("　","");;
	}
	public int getMaxMeetPersonAmt() {
		return maxMeetPersonAmt;
	}
	public void setMaxMeetPersonAmt(int maxMeetPersonAmt) {
		this.maxMeetPersonAmt = maxMeetPersonAmt;
	}
	public String getMeetDesc() {
		return meetDesc;
	}
	public void setMeetDesc(String meetDesc) {
		this.meetDesc = meetDesc == null ? null : meetDesc.trim().replaceAll("　","");;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl == null ? null : baseUrl.trim().replaceAll("　","");;
	}
	public Integer getPrcFlag() {
		return prcFlag;
	}
	public void setPrcFlag(Integer prcFlag) {
		this.prcFlag = prcFlag;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow == null ? null : isShow.trim();
	}
	public String getMeetRole() {
		return meetRole;
	}
	public void setMeetRole(String meetRole) {
		this.meetRole = meetRole == null ? null : meetRole.trim();
	}
	public String getMeetNo() {
		return meetNo;
	}
	public void setMeetNo(String meetNo) {
		this.meetNo = meetNo == null ? null : meetNo.trim();
	}
	public String getConfid() {
		return confid;
	}
	public void setConfid(String confid) {
		this.confid = confid == null ? null : confid.trim();
	}
	public String getMeetMediaNo() {
		return meetMediaNo;
	}
	public void setMeetMediaNo(String meetMediaNo) {
		this.meetMediaNo = meetMediaNo == null ? null : meetMediaNo.trim();
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}
	public String getShareMediaNo() {
		return shareMediaNo;
	}
	public void setShareMediaNo(String shareMediaNo) {
		this.shareMediaNo = shareMediaNo == null ? null : shareMediaNo.trim();
	}
	public String getOnlineDate() {
		return onlineDate;
	}
	public void setOnlineDate(String onlineDate) {
		this.onlineDate = onlineDate == null ? null : onlineDate.trim();
	}
	public String getConfCreateDate() {
		return confCreateDate;
	}
	public void setConfCreateDate(String confCreateDate) {
		this.confCreateDate = confCreateDate == null ? null : confCreateDate.trim();
	}
	public String getShowDate() {
		return showDate;
	}
	public void setShowDate(String showDate) {
		this.showDate = showDate == null ? null : showDate.trim();
	}
	public String getPv() {
		return pv;
	}
	public void setPv(String pv) {
		this.pv = pv == null ? null : pv.trim();
	}
	public String getConfName() {
		return confName;
	}
	public void setConfName(String confName) {
		this.confName = confName == null ? null : confName.trim().replaceAll("　","");;
	}
	public String getActivityProfile() {
		return activityProfile;
	}
	public void setActivityProfile(String activityProfile) {
		this.activityProfile = activityProfile == null ? null : activityProfile.trim();
	}
	public String getActivityBackground() {
		return activityBackground;
	}
	public void setActivityBackground(String activityBackground) {
		this.activityBackground = activityBackground == null ? null : activityBackground.trim();
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest == null ? null : guest.trim();
	}
	public String getActivitySchedule() {
		return activitySchedule;
	}
	public void setActivitySchedule(String activitySchedule) {
		this.activitySchedule = activitySchedule == null ? null : activitySchedule.trim();
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate == null ? null : createDate.trim();
	}
	public String getBigPicNo() {
		return bigPicNo;
	}
	public void setBigPicNo(String bigPicNo) {
		this.bigPicNo = bigPicNo == null ? null : bigPicNo.trim();
	}
	public String getBigMediaPath() {
		return bigMediaPath;
	}
	public void setBigMediaPath(String bigMediaPath) {
		this.bigMediaPath = bigMediaPath == null ? null : bigMediaPath.trim();
	}
	
	public String getMeetProjectName() {
		return meetProjectName;
	}
	public void setMeetProjectName(String meetProjectName) {
		this.meetProjectName = meetProjectName == null ? null : meetProjectName.trim();
	}
	public String getLastEditDate() {
		return lastEditDate;
	}
	public void setLastEditDate(String lastEditDate) {
		this.lastEditDate = lastEditDate == null ? null : lastEditDate.trim();
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler == null ? null : handler.trim();
	}
}
