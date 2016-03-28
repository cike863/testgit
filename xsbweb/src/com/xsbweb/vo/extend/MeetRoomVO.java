package com.xsbweb.vo.extend;

import java.util.ArrayList;
import java.util.List;

import com.xsbweb.common.bean.BasePo;
import com.xsbweb.vo.ProjectItem;
import com.xsbweb.vo.TrsMedia;
import com.xsbweb.vo.TrsProject;

public class MeetRoomVO extends BasePo{

	private List<MeetCallVO> meetCallVOList = new ArrayList<MeetCallVO>();
	
	private List<ProjectItem> voiceItemList =new ArrayList<ProjectItem>();
	
	private List<ProjectItem> oldVoiceItemList =new ArrayList<ProjectItem>();
	
	private List<TrsMedia> trsMedias=new ArrayList<TrsMedia>();
	
	private TrsProject trProject = new TrsProject();
	
	//private PageVO pageVO = new PageVO();
	//语音会议开始时长
	private int startMinute; 
	
	private String confid="";//会议Id
	private String voiptoconfid="";//直呼加入的会议Id，用于VoIP直呼加入会议时呼叫的Id。
	private String action="";		    //会议创建通知的回调url地址。自定义的相对地址，默认值为空
	private String maxmember=""; 	    //		最大会议人数，不能大于300。默认值为3
	private String passwd=""; 		//			加入会议密码。默认为空，暂时设置无法使用
	private String passwderrorurl=""; //	密码输入三次错误的回调url地址。自定义的相对地址，默认为空，暂时设置无法使
	private String dtmfreporturl="";  //	会议中DTMF上报通知的回调url地址。自定义的相对地址，默认值为空
	private String delreporturl=""; 	//	会议被删除通知的回调url地址。自定义的相对地址，默认值为空
	private String confduration=""; 	//	此次会议时长单位是秒，小于等于0时则不限时，到时后会议自动结束。第一个成员
	private String autohangup="";	    //		会议自动结束后是否自动挂断用户电话，默认值为false 
	private String confendprompt="";  //	会议自动结束前的提示音，为空则不播放。默认值为空 
	private String autorecord=""; 	//		是否自动录音，true为录音，false为不录音。默认值为false 
	private String quiturl=""; 		//		退出会议通知的回调url地址。自定义的相对地址，默认值为空
	private String mediaopturl=""; 	//	会议媒体控制结果通知的回调url地址。自定义的相对地址，默认值为空 
	private String autojoin=""; 		//		是否自动加入会议。通过IVR响应命令调用时有效。默认为false 
	private String joinurl="";		//		加入会议通知的回调url地址。自定义的相对地址，默认值为空 
	private String compere=""; //主持人
	private String compereName=""; //主持人用户名
	private String comperePhoneNo="";
	private String confName="";//会议名称
	//主持人头像
	private String picPath="";
	private String isShow="";
	//状态 0:失效，1：直播预告，2：直播中，3：直播结束
	private String status="";
	
	private String createdDate=""; //创建时间
	//查询条件
	private String confLabel="";
	//录音下载地址
	private String recordurl="";
	//录音时长
	private String recordduration="";
	
	//录音下载地址1
	private String recordurl1="";
	//录音下载地址2
	private String recordurl2="";
	//录音下载地址3
	private String recordurl3="";
	//录音下载地址4
	private String recordurl4="";
	//录音下载地址5
	private String recordurl5="";
	//录音状态 1：未开始录音，2：录音中，3：录音完成
	private String recording="";  
	
	//对应的项目no
	private String projectNo="";
	
	//对应的项目名称
	private String projName="";
	//上线时间
	private String onlineDate="";
	//直播开始时间
	private String showDate="";
	//浏览量
	private Integer pv;
	//会议创建时间
	private String confCreateDate="";
	//列表用图片
	private String meetMediaNo="";
	//分享用图片
	private String shareMediaNo="";
	//分享主标题
	private String meetTitle="";
	//分享副标题
	private String meetExTitle="";
	//活动参与人数
	private int maxMeetPersonAmt;
	private String meetNo="";
	private String meetRole="";
	private String bigPicNo="";
	
	private String pptMediaNo="";
	
	private String meetMediaPath="";
	
	private String shareMediaPath="";
	
	private String bigMediaPath="";
	
	private String pptMediaPath="";
	
	private Integer prcFlag;
	
	private String lastEditDate;
	
	public String getPptMediaPath() {
		return pptMediaPath;
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

	public String getBigMediaPath() {
		return bigMediaPath;
	}

	public void setBigMediaPath(String bigMediaPath) {
		this.bigMediaPath = bigMediaPath == null ? null : bigMediaPath.trim();
	}

	public void setPptMediaPath(String pptMediaPath) {
		this.pptMediaPath = pptMediaPath == null ? null : pptMediaPath.trim();
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName == null ? null : projName.trim();
	}
	public String getPptMediaNo() {
		return pptMediaNo;
	}
	public void setPptMediaNo(String pptMediaNo) {
		this.pptMediaNo = pptMediaNo == null ? null : pptMediaNo.trim();
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath == null ? null : picPath.trim();
	}
	public String getMeetTitle() {
		return meetTitle;
	}
	public void setMeetTitle(String meetTitle) {
		this.meetTitle = meetTitle == null ? null : meetTitle.trim();
	}
	public String getMeetExTitle() {
		return meetExTitle;
	}
	public void setMeetExTitle(String meetExTitle) {
		this.meetExTitle = meetExTitle == null ? null : meetExTitle.trim();
	}
	public int getMaxMeetPersonAmt() {
		return maxMeetPersonAmt;
	}
	public void setMaxMeetPersonAmt(int maxMeetPersonAmt) {
		this.maxMeetPersonAmt = maxMeetPersonAmt;
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
	public String getMeetMediaNo() {
		return meetMediaNo;
	}
	public void setMeetMediaNo(String meetMediaNo) {
		this.meetMediaNo = meetMediaNo == null ? null : meetMediaNo.trim();
	}
	public String getShareMediaNo() {
		return shareMediaNo;
	}
	public void setShareMediaNo(String shareMediaNo) {
		this.shareMediaNo = shareMediaNo == null ? null : shareMediaNo.trim();
	}
	public String getConfCreateDate() {
		return confCreateDate;
	}
	public void setConfCreateDate(String confCreateDate) {
		this.confCreateDate = confCreateDate == null ? null : confCreateDate.trim();
	}
	public String getOnlineDate() {
		return onlineDate;
	}
	public void setOnlineDate(String onlineDate) {
		this.onlineDate = onlineDate == null ? null : onlineDate.trim();
	}
	public String getShowDate() {
		return showDate;
	}
	public void setShowDate(String showDate) {
		this.showDate = showDate == null ? null : showDate.trim();
	}
	public Integer getPv() {
		return pv;
	}
	public void setPv(Integer pv) {
		this.pv = pv;
	}
	public String getComperePhoneNo() {
		return comperePhoneNo;
	}
	public void setComperePhoneNo(String comperePhoneNo) {
		this.comperePhoneNo = comperePhoneNo == null ? null : comperePhoneNo.trim();
	}
	public int getStartMinute() {
		return startMinute;
	}
	public void setStartMinute(int startMinute) {
		this.startMinute = startMinute;
	}
	public TrsProject getTrProject() {
		return trProject;
	}
	public void setTrProject(TrsProject trProject) {
		this.trProject = trProject;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo == null ? null : projectNo.trim();
	}
	public String getRecordurl1() {
		return recordurl1;
	}
	public void setRecordurl1(String recordurl1) {
		this.recordurl1 = recordurl1 == null ? null : recordurl1.trim();
	}
	public String getRecordurl2() {
		return recordurl2;
	}
	public void setRecordurl2(String recordurl2) {
		this.recordurl2 = recordurl2 == null ? null : recordurl2.trim();
	}
	public String getRecordurl3() {
		return recordurl3;
	}
	public void setRecordurl3(String recordurl3) {
		this.recordurl3 = recordurl3 == null ? null : recordurl3.trim();
	}
	public String getRecordurl4() {
		return recordurl4;
	}
	public void setRecordurl4(String recordurl4) {
		this.recordurl4 = recordurl4 == null ? null : recordurl4.trim();
	}
	public String getRecordurl5() {
		return recordurl5;
	}
	public void setRecordurl5(String recordurl5) {
		this.recordurl5 = recordurl5 == null ? null : recordurl5.trim();
	}
	public String getRecording() {
		return recording;
	}
	public void setRecording(String recording) {
		this.recording = recording == null ? null : recording.trim();
	}
	public String getRecordurl() {
		return recordurl;
	}
	public void setRecordurl(String recordurl) {
		this.recordurl = recordurl == null ? null : recordurl.trim();
	}
	public String getRecordduration() {
		return recordduration;
	}
	public void setRecordduration(String recordduration) {
		this.recordduration = recordduration == null ? null : recordduration.trim();
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}
	public String getConfName() {
		return confName;
	}
	public void setConfName(String confName) {
		this.confName = confName == null ? null : confName.trim();
	}
	public List<MeetCallVO> getMeetCallVOList() {
		return meetCallVOList;
	}
	public void setMeetCallVOList(List<MeetCallVO> meetCallVOList) {
		this.meetCallVOList = meetCallVOList;
	}
	public String getCompereName() {
		return compereName;
	}
	public void setCompereName(String compereName) {
		this.compereName = compereName == null ? null : compereName.trim();
	}
	public String getConfLabel() {
		return confLabel;
	}
	public void setConfLabel(String confLabel) {
		this.confLabel = confLabel == null ? null : confLabel.trim();
	}
	public String getConfid() {
		return confid;
	}
	public void setConfid(String confid) {
		this.confid = confid == null ? null : confid.trim();
	}
	public String getVoiptoconfid() {
		return voiptoconfid;
	}
	public void setVoiptoconfid(String voiptoconfid) {
		this.voiptoconfid = voiptoconfid == null ? null : voiptoconfid.trim();
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action == null ? null : action.trim();
	}
	public String getMaxmember() {
		return maxmember;
	}
	public void setMaxmember(String maxmember) {
		this.maxmember = maxmember == null ? null : maxmember.trim();
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd == null ? null : passwd.trim();
	}
	public String getPasswderrorurl() {
		return passwderrorurl;
	}
	public void setPasswderrorurl(String passwderrorurl) {
		this.passwderrorurl = passwderrorurl == null ? null : passwderrorurl.trim();
	}
	public String getDtmfreporturl() {
		return dtmfreporturl;
	}
	public void setDtmfreporturl(String dtmfreporturl) {
		this.dtmfreporturl = dtmfreporturl == null ? null : dtmfreporturl.trim();
	}
	public String getDelreporturl() {
		return delreporturl;
	}
	public void setDelreporturl(String delreporturl) {
		this.delreporturl = delreporturl == null ? null : delreporturl.trim();
	}
	public String getConfduration() {
		return confduration;
	}
	public void setConfduration(String confduration) {
		this.confduration = confduration == null ? null : confduration.trim();
	}
	public String getAutohangup() {
		return autohangup;
	}
	public void setAutohangup(String autohangup) {
		this.autohangup = autohangup == null ? null : autohangup.trim();
	}
	public String getConfendprompt() {
		return confendprompt;
	}
	public void setConfendprompt(String confendprompt) {
		this.confendprompt = confendprompt == null ? null : confendprompt.trim();
	}
	public String getAutorecord() {
		return autorecord;
	}
	public void setAutorecord(String autorecord) {
		this.autorecord = autorecord == null ? null : autorecord.trim();
	}
	public String getQuiturl() {
		return quiturl;
	}
	public void setQuiturl(String quiturl) {
		this.quiturl = quiturl == null ? null : quiturl.trim();
	}
	public String getMediaopturl() {
		return mediaopturl;
	}
	public void setMediaopturl(String mediaopturl) {
		this.mediaopturl = mediaopturl == null ? null : mediaopturl.trim();
	}
	public String getAutojoin() {
		return autojoin;
	}
	public void setAutojoin(String autojoin) {
		this.autojoin = autojoin == null ? null : autojoin.trim();
	}
	public String getJoinurl() {
		return joinurl;
	}
	public void setJoinurl(String joinurl) {
		this.joinurl = joinurl == null ? null : joinurl.trim();
	}
	public String getCompere() {
		return compere;
	}
	public void setCompere(String compere) {
		this.compere = compere == null ? null : compere.trim();
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate == null ? null : createdDate.trim();
	}
	public List<ProjectItem> getVoiceItemList() {
		return voiceItemList;
	}
	public void setVoiceItemList(List<ProjectItem> voiceItemList) {
		this.voiceItemList = voiceItemList;
	}
	public List<TrsMedia> getTrsMedias() {
		return trsMedias;
	}
	public void setTrsMedias(List<TrsMedia> trsMedias) {
		this.trsMedias = trsMedias;
	}
	public String getBigPicNo() {
		return bigPicNo;
	}
	public void setBigPicNo(String bigPicNo) {
		this.bigPicNo = bigPicNo == null ? null : bigPicNo.trim();
	}
	public List<ProjectItem> getOldVoiceItemList() {
		return oldVoiceItemList;
	}
	public void setOldVoiceItemList(List<ProjectItem> oldVoiceItemList) {
		this.oldVoiceItemList = oldVoiceItemList;
	}

	public String getLastEditDate() {
		return lastEditDate;
	}

	public void setLastEditDate(String lastEditDate) {
		this.lastEditDate = lastEditDate == null ? null : lastEditDate.trim();
	}
	
}
