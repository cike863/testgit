package com.xsbweb.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xsbweb.common.bean.BasePo;
import com.xsbweb.vo.extend.EnumVO;

public class News extends BasePo {

	private String newsNo="";
	
	private String newsDate="";
	
	private String lastUpdateBy="";
	
	private String commentCounts="";

	private String newsType="";
	
	private String newsSource="";
	
	private String newsTitle="";
	
	private String newsExTitle="";
	
	private String newsRole="";
	
	private String newsContent="";
	
	private String fakeCounts="";
	
	private String realCounts="";
	
	private String lastUpdateDate="";
	
	private String newsMediaNo="";
	
	private String shareMediaNo="";
	
	private String bigMediaNo="";
	
	private String newsPicPath="";
	
	private String sharePicPath="";
	
	private String bigPicParh="";
	/**
	 * 是否置顶，1：是；0：否
	 */
	private String isTop="";
	
	private String isShow="";
	
	private String createBy="";
	
	private String word;
	
	private List<EnumVO> newsTypeEnumList;
	
	private List<SearchWord> searchWord = new ArrayList<SearchWord>();
	private List<SearchWord> oldWordList = new ArrayList<SearchWord>();
	
	private List<TrsMedia> trsMediaList = new ArrayList<TrsMedia>();
	
	private String projectNo;
	
	private String projCpCode;
	
	private Integer prcFlag;
	
	private String pv;
	
	private Date sartQueryDate;
	
	private Date endQueryDate;
	
	public String getPv() {
		return pv;
	}

	public void setPv(String pv) {
		this.pv = pv== null ? null : pv.trim();
	}

	public String getCommentCounts() {
		return commentCounts;
	}

	public void setCommentCounts(String commentCounts) {
		this.commentCounts = commentCounts == null ? null : commentCounts.trim();
	}

	public String getNewsMediaNo() {
		return newsMediaNo;
	}

	public void setNewsMediaNo(String newsMediaNo) {
		this.newsMediaNo = newsMediaNo == null ? null : newsMediaNo.trim();
	}

	public String getShareMediaNo() {
		return shareMediaNo;
	}

	public void setShareMediaNo(String shareMediaNo) {
		this.shareMediaNo = shareMediaNo == null ? null : shareMediaNo.trim();
	}

	
	
	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow == null ? null : isShow.trim();
	}

	public String getNewsPicPath() {
		return newsPicPath;
	}

	public void setNewsPicPath(String newsPicPath) {
		this.newsPicPath = newsPicPath == null ? null : newsPicPath.trim();
	}

	public String getSharePicPath() {
		return sharePicPath;
	}

	public void setSharePicPath(String sharePicPath) {
		this.sharePicPath = sharePicPath == null ? null : sharePicPath.trim();
	}

	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop == null ? null : isTop.trim();
	}

	public List<EnumVO> getNewsTypeEnumList() {
		return newsTypeEnumList;
	}

	public void setNewsTypeEnumList(List<EnumVO> newsTypeEnumList) {
		this.newsTypeEnumList = newsTypeEnumList;
	}

	public String getFakeCounts() {
		return fakeCounts;
	}

	public void setFakeCounts(String fakeCounts) {
		this.fakeCounts = fakeCounts == null ? null : fakeCounts.trim();
	}

	public String getRealCounts() {
		return realCounts;
	}

	public void setRealCounts(String realCounts) {
		this.realCounts = realCounts == null ? null : realCounts.trim();
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate == null ? null : lastUpdateDate.trim();
	}

	public String getNewsNo() {
		return newsNo;
	}

	public void setNewsNo(String newsNo) {
		this.newsNo = newsNo == null ? null : newsNo.trim();
	}

	public String getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(String newsDate) {
		this.newsDate = newsDate == null ? null : newsDate.trim();
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType == null ? null : newsType.trim();
	}

	public String getNewsSource() {
		return newsSource;
	}

	public void setNewsSource(String newsSource) {
		this.newsSource = newsSource == null ? null : newsSource.trim().replaceAll("　", "");
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle == null ? null : newsTitle.trim().replaceAll("　", "");
	}

	public String getNewsExTitle() {
		return newsExTitle;
	}

	public void setNewsExTitle(String newsExTitle) {
		this.newsExTitle = newsExTitle == null ? null : newsExTitle.trim().replaceAll("　", "");
	}

	

	public String getNewsRole() {
		return newsRole;
	}

	public void setNewsRole(String newsRole) {
		this.newsRole = newsRole == null ? null : newsRole.trim().replaceAll("　", "");
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent == null ? null : newsContent.trim();
	}
	public List<SearchWord> getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(List<SearchWord> searchWord) {
		this.searchWord = searchWord;
	}

	public List<SearchWord> getOldWordList() {
		return oldWordList;
	}

	public void setOldWordList(List<SearchWord> oldWordList) {
		this.oldWordList = oldWordList;
	}

	

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo == null ? null : projectNo.trim();
	}

	public String getProjCpCode() {
		return projCpCode;
	}

	public void setProjCpCode(String projCpCode) {
		this.projCpCode = projCpCode== null ? null : projCpCode.trim();
	}

	public String getBigMediaNo() {
		return bigMediaNo;
	}

	public void setBigMediaNo(String bigMediaNo) {
		this.bigMediaNo = bigMediaNo == null ? null : bigMediaNo.trim();
	}

	public String getBigPicParh() {
		return bigPicParh;
	}

	public void setBigPicParh(String bigPicParh) {
		this.bigPicParh = bigPicParh == null ? null : bigPicParh.trim();
	}

	public List<TrsMedia> getTrsMediaList() {
		return trsMediaList;
	}

	public void setTrsMediaList(List<TrsMedia> trsMediaList) {
		this.trsMediaList = trsMediaList;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy == null ? null : lastUpdateBy.trim();
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word == null ? null : word.trim();
	}

	public Integer getPrcFlag() {
		return prcFlag;
	}

	public void setPrcFlag(Integer prcFlag) {
		this.prcFlag = prcFlag;
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
