package com.xsbweb.vo;

public class CompanyHolder {

    private String stockId;

    private String holderName;

    private String holdType;

    private String holdAmount;

    private String holdPercent;

    private String chngHoldAmount;

    private String chngHoldPercent;
    
    private String loc;
    
    private String snapshotDay;
    
    private String[] holderNames;
    
    public String getStockId() {
        return stockId;
    }
    
    public void setStockId(String stockId) {
        this.stockId = stockId == null ? null : stockId.trim();
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName == null ? null : holderName.trim();
    }

	public String getHoldType() {
		return holdType;
	}

	public void setHoldType(String holdType) {
		this.holdType = holdType == null ? null : holdType.trim();
	}

	public String getHoldAmount() {
		return holdAmount;
	}

	public void setHoldAmount(String holdAmount) {
		this.holdAmount = holdAmount == null ? null : holdAmount.trim();
	}

	public String getHoldPercent() {
		return holdPercent;
	}

	public void setHoldPercent(String holdPercent) {
		this.holdPercent = holdPercent == null ? null : holdPercent.trim();
	}

	public String getChngHoldAmount() {
		return chngHoldAmount;
	}

	public void setChngHoldAmount(String chngHoldAmount) {
		this.chngHoldAmount = chngHoldAmount == null ? null : chngHoldAmount.trim();
	}

	public String getChngHoldPercent() {
		return chngHoldPercent;
	}

	public void setChngHoldPercent(String chngHoldPercent) {
		this.chngHoldPercent = chngHoldPercent == null ? null : chngHoldPercent.trim();
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc == null ? null : loc.trim();
	}

	public String getSnapshotDay() {
		return snapshotDay;
	}

	public void setSnapshotDay(String snapshotDay) {
		this.snapshotDay = snapshotDay == null ? null : snapshotDay.trim();
	}

	public String[] getHolderNames() {
		return holderNames;
	}

	public void setHolderNames(String[] holderNames) {
		this.holderNames = holderNames;
	}
	
}