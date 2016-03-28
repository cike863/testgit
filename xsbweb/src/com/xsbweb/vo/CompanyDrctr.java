package com.xsbweb.vo;

import com.xsbweb.common.bean.BasePo;

public class CompanyDrctr extends BasePo{
	
    private String stockId;

    private String dsmName;//董监高名称
    
    private String [] dsmNames;//董监高名称集合

    private String dsmTitle;//董监高职位

    private String holdAmount;//持股数
    
    private String loc;//排位
    
    private String dsmType;//董监高类型，d:董事，s:监事，m:高管
    
    private String snapshotDay;

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId == null ? null : stockId.trim();
	}

	public String getDsmName() {
		return dsmName;
	}

	public void setDsmName(String dsmName) {
		this.dsmName = dsmName == null ? null : dsmName.trim();
	}

	public String getDsmTitle() {
		return dsmTitle;
	}

	public void setDsmTitle(String dsmTitle) {
		this.dsmTitle = dsmTitle == null ? null : dsmTitle.trim();
	}

	public String getHoldAmount() {
		return holdAmount;
	}

	public void setHoldAmount(String holdAmount) {
		this.holdAmount = holdAmount == null ? null : holdAmount.trim();
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc == null ? null : loc.trim();
	}

	public String getDsmType() {
		return dsmType;
	}

	public void setDsmType(String dsmType) {
		this.dsmType = dsmType == null ? null : dsmType.trim();
	}

	public String getSnapshotDay() {
		return snapshotDay;
	}

	public void setSnapshotDay(String snapshotDay) {
		this.snapshotDay = snapshotDay == null ? null : snapshotDay.trim();
	}

	public String[] getDsmNames() {
		return dsmNames;
	}

	public void setDsmNames(String[] dsmNames) {
		this.dsmNames = dsmNames;
	}
	
}