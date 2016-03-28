package com.xsbweb.vo;

import java.util.Date;

public class SearchWord {
    private String word;
    
    private String oldWord;
    
    private String object;

    private String link;
    
    private Integer labOrder;

    private Date createDate;

    private Date lastUpdateDate;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word == null ? null : word.trim().replaceAll("　", "");;
    }
    
    public String getOldWord() {
		return oldWord ;
	}

	public void setOldWord(String oldWord) {
		this.oldWord = oldWord == null ? null : oldWord.trim().replaceAll("　", "");;
	}

	public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object == null ? null : object.trim().replaceAll("　", "");;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim().replaceAll("　", "");;
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

	public Integer getLabOrder() {
		return labOrder;
	}

	public void setLabOrder(Integer labOrder) {
		this.labOrder = labOrder;
	}
    
}