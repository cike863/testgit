package com.xsbweb.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xsbweb.common.bean.BasePo;
import com.xsbweb.vo.extend.EnumVO;

public class TrsProject extends BasePo{
	
    private String projectNo;

    private String projOnlineDate="";

    private Integer projPv;

    private String projShowStatus="";

    private String projName="";

    private String projDynQuotes="";

    private String projCoachOrg="";

    private String projAssignmentMay="";

    private Integer projApplyCounts;

    private Double projPercentage;

    private String projCpCode="";
   /* //关联股票代码
    private String projectCpCode;*/
    //行业
    private String projectDesc="";

    private String projCpFullname="";

    private String projRegisterAdress="";

    private String projLegalPerson="";

    private String projChSecretary="";

    private Integer projRegisteredAssets;

    private String projIndustryType="";

    private String projShingleDate="";

    private String projCpUrl="";

    private String projZbTrader="";

    private String projZsTrader="";

    private String projCpLightspot="";

    private String projCpIntro="";

    private String projMainBusiness="";

    private String projIndustryAnalysis="";

    private String projIndustryProspect="";

    private String projCompetitiveEdge="";

    private String projStrategicPlan="";

    private String projExpectedEarnings="";

    private String projectType="";

    private String submitter="";

    private String projectStatus="";

    private String handler="";

    private String createDate="";

    private String lastUpdateDate="";
    //列表用图片
  	private String projMediaNo="";
  	//分享用图片
  	private String shareMediaNo="";
  	
  	private String projMediaPath="";
  	
  	private String shareMediaPath="";
  	
  	private String bigMediaPath="";
  	
  	private String projectTitle="";
  	private String projectExTitle="";
  	private int maxProjectPersonAmt;
  	private String isShow="";
  	
  	//分享链接
  	private String shareUrl="";
  	//路演状态，1：路演中，2：已完成
  	private String roadStatus="";
  	
    private String[] projectNoArrs;
    
    private Map<String,String> staffInfo = new HashMap<String,String>();
    
    private List<TrsMedia> trsMediaList = new ArrayList<TrsMedia>();
    
    private List<ProjectItem> itemList = new ArrayList<ProjectItem>();
    //词条
    private List<SearchWord> searchWord = new ArrayList<SearchWord>();
    
    private List<SearchWord> oldWordList = new ArrayList<SearchWord>();
    
    private List<EnumVO> enumList = new ArrayList<EnumVO>();
    
    private String projRole;
	
	private Integer prcFlag;
	//广告图
	private String bigPicNo="";
	//优酷视频id 
	private String youkuCode="";
	
	private String lastEditDate="";
	//集体敲钟编号
	private String prmtParentNo="";
	//维护浏览量
	private Integer fakeCount;
	
	public String getLastEditDate() {
		return lastEditDate;
	}

	public void setLastEditDate(String lastEditDate) {
		this.lastEditDate = lastEditDate;
	}

	public String getRoadStatus() {
		return roadStatus;
	}

	public void setRoadStatus(String roadStatus) {
		this.roadStatus = roadStatus;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public Map<String, String> getStaffInfo() {
		return staffInfo;
	}

	public void setStaffInfo(Map<String, String> staffInfo) {
		this.staffInfo = staffInfo;
	}

	public String getYoukuCode() {
		return youkuCode;
	}

	public void setYoukuCode(String youkuCode) {
		this.youkuCode = youkuCode;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getBigPicNo() {
		return bigPicNo;
	}

	public void setBigPicNo(String bigPicNo) {
		this.bigPicNo = bigPicNo == null ? null : bigPicNo.trim();
	}

	//商业计划书路径
	private String tradePlanMediaPath="";
	//商业计划书类型
	private String tradePlanType="";
	
	public String getTradePlanType() {
		return tradePlanType;
	}

	public void setTradePlanType(String tradePlanType) {
		this.tradePlanType = tradePlanType == null ? null : tradePlanType.trim();
	}

	public String getTradePlanMediaPath() {
		return tradePlanMediaPath;
	}

	public void setTradePlanMediaPath(String tradePlanMediaPath) {
		this.tradePlanMediaPath = tradePlanMediaPath == null ? null : tradePlanMediaPath.trim();
	}

	public Integer getPrcFlag() {
		return prcFlag;
	}

	public void setPrcFlag(Integer prcFlag) {
		this.prcFlag = prcFlag;
	}

	public String getProjRole() {
		return projRole;
	}

	public void setProjRole(String projRole) {
		this.projRole = projRole == null ? null : projRole.trim();
	}

	public String getProjMediaNo() {
		return projMediaNo;
	}

	public void setProjMediaNo(String projMediaNo) {
		this.projMediaNo = projMediaNo == null ? null : projMediaNo.trim();
	}

	public String getProjMediaPath() {
		return projMediaPath;
	}

	public void setProjMediaPath(String projMediaPath) {
		this.projMediaPath = projMediaPath == null ? null : projMediaPath.trim();
	}

	public String getShareMediaPath() {
		return shareMediaPath;
	}

	public void setShareMediaPath(String shareMediaPath) {
		this.shareMediaPath = shareMediaPath == null ? null : shareMediaPath.trim();
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle == null ? null : projectTitle.trim().replaceAll("　","");;
	}

	public String getProjectExTitle() {
		return projectExTitle;
	}

	public void setProjectExTitle(String projectExTitle) {
		this.projectExTitle = projectExTitle == null ? null : projectExTitle.trim().replaceAll("　","");;
	}

	public int getMaxProjectPersonAmt() {
		return maxProjectPersonAmt;
	}

	public void setMaxProjectPersonAmt(int maxProjectPersonAmt) {
		this.maxProjectPersonAmt = maxProjectPersonAmt;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow == null ? null : isShow.trim();
	}

	public String getShareMediaNo() {
		return shareMediaNo;
	}

	public void setShareMediaNo(String shareMediaNo) {
		this.shareMediaNo = shareMediaNo == null ? null : shareMediaNo.trim();
	}

	public String[] getProjectNoArrs() {
		return projectNoArrs;
	}

	public void setProjectNoArrs(String[] projectNoArrs) {
		this.projectNoArrs = projectNoArrs;
	}

	public List<ProjectItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<ProjectItem> itemList) {
		this.itemList = itemList;
	}
	public List<TrsMedia> getTrsMediaList() {
		return trsMediaList;
	}

	public void setTrsMediaList(List<TrsMedia> trsMediaList) {
		this.trsMediaList = trsMediaList;
	}

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public Integer getProjPv() {
        return projPv;
    }

    public void setProjPv(Integer projPv) {
        this.projPv = projPv;
    }

    public String getProjShowStatus() {
        return projShowStatus;
    }

    public void setProjShowStatus(String projShowStatus) {
        this.projShowStatus = projShowStatus == null ? null : projShowStatus.trim();
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName == null ? null : projName.trim().replaceAll("　","");
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
        this.projCoachOrg = projCoachOrg == null ? null : projCoachOrg.trim().replaceAll("　","");;
    }

    public String getProjAssignmentMay() {
        return projAssignmentMay;
    }

    public void setProjAssignmentMay(String projAssignmentMay) {
        this.projAssignmentMay = projAssignmentMay == null ? null : projAssignmentMay.trim();
    }

    public Integer getProjApplyCounts() {
        return projApplyCounts;
    }

    public void setProjApplyCounts(Integer projApplyCounts) {
        this.projApplyCounts = projApplyCounts;
    }

    public Double getProjPercentage() {
        return projPercentage;
    }

    public void setProjPercentage(Double projPercentage) {
        this.projPercentage = projPercentage;
    }

    public String getProjCpCode() {
        return projCpCode;
    }

    public void setProjCpCode(String projCpCode) {
        this.projCpCode = projCpCode == null ? null : projCpCode.trim();
    }

    
    
   /* public String getProjectCpCode() {
		return projectCpCode;
	}

	public void setProjectCpCode(String projectCpCode) {
		this.projectCpCode = projectCpCode == null ? null : projectCpCode.trim();
	}*/

	public String getProjCpFullname() {
        return projCpFullname;
    }

    public void setProjCpFullname(String projCpFullname) {
        this.projCpFullname = projCpFullname == null ? null : projCpFullname.trim().replaceAll("　","");;
    }

    public String getProjRegisterAdress() {
        return projRegisterAdress;
    }

    public void setProjRegisterAdress(String projRegisterAdress) {
        this.projRegisterAdress = projRegisterAdress == null ? null : projRegisterAdress.trim();
    }

    public String getProjLegalPerson() {
        return projLegalPerson;
    }

    public void setProjLegalPerson(String projLegalPerson) {
        this.projLegalPerson = projLegalPerson == null ? null : projLegalPerson.trim();
    }

    public String getProjChSecretary() {
        return projChSecretary;
    }

    public void setProjChSecretary(String projChSecretary) {
        this.projChSecretary = projChSecretary == null ? null : projChSecretary.trim();
    }

    public Integer getProjRegisteredAssets() {
        return projRegisteredAssets;
    }

    public void setProjRegisteredAssets(Integer projRegisteredAssets) {
        this.projRegisteredAssets = projRegisteredAssets;
    }

    public String getProjIndustryType() {
        return projIndustryType;
    }

    public void setProjIndustryType(String projIndustryType) {
        this.projIndustryType = projIndustryType == null ? null : projIndustryType.trim();
    }

    public String getProjCpUrl() {
        return projCpUrl;
    }

    public void setProjCpUrl(String projCpUrl) {
        this.projCpUrl = projCpUrl == null ? null : projCpUrl.trim();
    }

    public String getProjZbTrader() {
        return projZbTrader;
    }

    public void setProjZbTrader(String projZbTrader) {
        this.projZbTrader = projZbTrader == null ? null : projZbTrader.trim();
    }

    public String getProjZsTrader() {
        return projZsTrader;
    }

    public void setProjZsTrader(String projZsTrader) {
        this.projZsTrader = projZsTrader == null ? null : projZsTrader.trim();
    }

    public String getProjCpLightspot() {
        return projCpLightspot;
    }

    public void setProjCpLightspot(String projCpLightspot) {
        this.projCpLightspot = projCpLightspot == null ? null : projCpLightspot.trim();
    }

    public String getProjCpIntro() {
        return projCpIntro;
    }

    public void setProjCpIntro(String projCpIntro) {
        this.projCpIntro = projCpIntro == null ? null : projCpIntro.trim();
    }

    public String getProjMainBusiness() {
        return projMainBusiness;
    }

    public void setProjMainBusiness(String projMainBusiness) {
        this.projMainBusiness = projMainBusiness == null ? null : projMainBusiness.trim();
    }

    public String getProjIndustryAnalysis() {
        return projIndustryAnalysis;
    }

    public void setProjIndustryAnalysis(String projIndustryAnalysis) {
        this.projIndustryAnalysis = projIndustryAnalysis == null ? null : projIndustryAnalysis.trim();
    }

    public String getProjIndustryProspect() {
        return projIndustryProspect;
    }

    public void setProjIndustryProspect(String projIndustryProspect) {
        this.projIndustryProspect = projIndustryProspect == null ? null : projIndustryProspect.trim();
    }

    public String getProjCompetitiveEdge() {
        return projCompetitiveEdge;
    }

    public void setProjCompetitiveEdge(String projCompetitiveEdge) {
        this.projCompetitiveEdge = projCompetitiveEdge == null ? null : projCompetitiveEdge.trim();
    }

    public String getProjStrategicPlan() {
        return projStrategicPlan;
    }

    public void setProjStrategicPlan(String projStrategicPlan) {
        this.projStrategicPlan = projStrategicPlan == null ? null : projStrategicPlan.trim();
    }

    public String getProjExpectedEarnings() {
        return projExpectedEarnings;
    }

    public void setProjExpectedEarnings(String projExpectedEarnings) {
        this.projExpectedEarnings = projExpectedEarnings == null ? null : projExpectedEarnings.trim();
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType == null ? null : projectType.trim();
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter == null ? null : submitter.trim();
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus == null ? null : projectStatus.trim();
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler == null ? null : handler.trim();
    }

	public String getProjOnlineDate() {
		return projOnlineDate;
	}

	public void setProjOnlineDate(String projOnlineDate) {
		this.projOnlineDate = projOnlineDate == null ? null : projOnlineDate.trim();
	}

	public String getProjShingleDate() {
		return projShingleDate;
	}

	public void setProjShingleDate(String projShingleDate) {
		this.projShingleDate = projShingleDate == null ? null : projShingleDate.trim();
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate == null ? null : createDate.trim();
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate == null ? null : lastUpdateDate.trim();
	}

	public String getBigMediaPath() {
		return bigMediaPath;
	}

	public void setBigMediaPath(String bigMediaPath) {
		this.bigMediaPath = bigMediaPath == null ? null : bigMediaPath.trim();
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

	public List<EnumVO> getEnumList() {
		return enumList;
	}

	public void setEnumList(List<EnumVO> enumList) {
		this.enumList = enumList;
	}

	public String getPrmtParentNo() {
		return prmtParentNo;
	}

	public void setPrmtParentNo(String prmtParentNo) {
		this.prmtParentNo = prmtParentNo == null ? null : prmtParentNo.trim();
	}

	public Integer getFakeCount() {
		return fakeCount;
	}

	public void setFakeCount(Integer fakeCount) {
		this.fakeCount = fakeCount;
	}
}