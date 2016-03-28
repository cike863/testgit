package com.xsbweb.vo;

import java.util.Date;

import com.xsbweb.common.bean.BasePo;

public class Company extends BasePo {
	
	private String companyId;
	
    private String snapshotDay;
    //代码
    private String stockId;
    //公司简称
    private String companyName;
    //公司全称
    private String companyFullName;
    //
    private String companyEngName;
    //注册地址
    private String companyAddress;
    //法人代表
    private String legalPerson;
    //公司董秘
    private String chSecretary;
    //注册资本
    private String companyCapital;
    //行业分类
    private String industryType;
    //挂牌日期
    private String shingleDate;
    //公司网址
    private String url;
    //转让方式
    private String assiMay;
    //主办券商
    private String zbTrader;
    //做市商
    private String zsTrader;
    //
    private String industryId;
    //
    private String industryGroupId;
    //
    private String industryGrandGroupId;
   
    //股票名称
    private String stockName;
    //股票状态
    private String stockStatus;
    //所属地域
    private String area;
    //电　　话
    private String tel;
    //传　　真
    private String fax;
    //公司介绍
    private String historyShort;
    //邮      编
    private String zipCode;
    //该记录创建日期
    private Date createDate;
    //该记录最后更新日期
    private Date lastUpdateDate;
    //协议机构标记
    private String protocolInsttt;

    private Date listDate;
    //总股本
    private Double totalEquity;
    //协议机构
    private String protocolBroker;
    
    public String getAssiMay() {
		return assiMay;
	}

	public void setAssiMay(String assiMay) {
		this.assiMay = assiMay == null ? null : assiMay.trim();
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId == null ? null : companyId.trim();
	}

	public String getSnapshotDay() {
		return snapshotDay;
	}

	public void setSnapshotDay(String snapshotDay) {
		this.snapshotDay = snapshotDay == null ? null : snapshotDay.trim();
	}

	public String getChSecretary() {
		return chSecretary;
	}

	public void setChSecretary(String chSecretary) {
		this.chSecretary = chSecretary == null ? null : chSecretary.trim();
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType == null ? null : industryType.trim();
	}

	public String getShingleDate() {
		return shingleDate;
	}

	public void setShingleDate(String shingleDate) {
		this.shingleDate = shingleDate == null ? null : shingleDate.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getZbTrader() {
		return zbTrader;
	}

	public void setZbTrader(String zbTrader) {
		this.zbTrader = zbTrader == null ? null : zbTrader.trim();
	}

	public String getZsTrader() {
		return zsTrader;
	}

	public void setZsTrader(String zsTrader) {
		this.zsTrader = zsTrader == null ? null : zsTrader.trim();
	}

	public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId == null ? null : stockId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim().replaceAll("　","");;
    }

    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName == null ? null : companyFullName.trim().replaceAll("　","");;
    }

    public String getCompanyEngName() {
        return companyEngName;
    }

    public void setCompanyEngName(String companyEngName) {
        this.companyEngName = companyEngName == null ? null : companyEngName.trim().replaceAll("　","");;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim().replaceAll("　","");;
    }

    public String getCompanyCapital() {
        return companyCapital;
    }

    public void setCompanyCapital(String companyCapital) {
        this.companyCapital = companyCapital;
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId == null ? null : industryId.trim();
    }

    public String getIndustryGroupId() {
        return industryGroupId;
    }

    public void setIndustryGroupId(String industryGroupId) {
        this.industryGroupId = industryGroupId == null ? null : industryGroupId.trim();
    }

    public String getIndustryGrandGroupId() {
        return industryGrandGroupId;
    }

    public void setIndustryGrandGroupId(String industryGrandGroupId) {
        this.industryGrandGroupId = industryGrandGroupId == null ? null : industryGrandGroupId.trim();
    }
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getHistoryShort() {
        return historyShort;
    }

    public void setHistoryShort(String historyShort) {
        this.historyShort = historyShort == null ? null : historyShort.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
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

    public String getProtocolInsttt() {
        return protocolInsttt;
    }

    public void setProtocolInsttt(String protocolInsttt) {
        this.protocolInsttt = protocolInsttt == null ? null : protocolInsttt.trim();
    }

    public Date getListDate() {
        return listDate;
    }

    public void setListDate(Date listDate) {
        this.listDate = listDate;
    }

    public Double getTotalEquity() {
        return totalEquity;
    }

    public void setTotalEquity(Double totalEquity) {
        this.totalEquity = totalEquity;
    }
    public String getProtocolBroker() {
        return protocolBroker;
    }

    public void setProtocolBroker(String protocolBroker) {
        this.protocolBroker = protocolBroker == null ? null : protocolBroker.trim();
    }
    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus == null ? null : stockStatus.trim();
    }
    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName == null ? null : stockName.trim();
    }
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }
}