package com.xsbweb.vo;

import java.io.Serializable;
import java.util.Date;

import com.xsbweb.common.bean.BasePo;

public class Menu extends BasePo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String itemId;

    private String itemText;

    private String pageLoc;

    private String targetLink;

    private Date createDate;

    private Date lastUpdateDate;
    
    private String itemParent;
    
    private Integer itemOrder;

    public String getItemParent() {
		return itemParent;
	}

	public void setItemParent(String itemParent) {
		this.itemParent = itemParent;
	}

	public Integer getItemOrder() {
		return itemOrder;
	}

	public void setItemOrder(Integer itemOrder) {
		this.itemOrder = itemOrder;
	}

	public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText == null ? null : itemText.trim();
    }

    public String getPageLoc() {
        return pageLoc;
    }

    public void setPageLoc(String pageLoc) {
        this.pageLoc = pageLoc == null ? null : pageLoc.trim();
    }

    public String getTargetLink() {
        return targetLink;
    }

    public void setTargetLink(String targetLink) {
        this.targetLink = targetLink == null ? null : targetLink.trim();
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
}