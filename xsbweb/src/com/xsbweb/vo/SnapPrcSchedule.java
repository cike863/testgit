package com.xsbweb.vo;

import java.util.Date;

import com.xsbweb.common.bean.BasePo;

public class SnapPrcSchedule extends BasePo {
    private String taskPrc;

    private Integer taskPeriod;

    private Date effectiveDate;

    private Date extireDate;

    private Date lastExecTime;

    private Date nextExecTime;

    private String execStatus;

    private Date createDate;

    private Date lastUpdateDate;

    public String getTaskPrc() {
        return taskPrc;
    }

    public void setTaskPrc(String taskPrc) {
        this.taskPrc = taskPrc == null ? null : taskPrc.trim();
    }

    public Integer getTaskPeriod() {
        return taskPeriod;
    }

    public void setTaskPeriod(Integer taskPeriod) {
        this.taskPeriod = taskPeriod;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExtireDate() {
        return extireDate;
    }

    public void setExtireDate(Date extireDate) {
        this.extireDate = extireDate;
    }

    public Date getLastExecTime() {
        return lastExecTime;
    }

    public void setLastExecTime(Date lastExecTime) {
        this.lastExecTime = lastExecTime;
    }

    public Date getNextExecTime() {
        return nextExecTime;
    }

    public void setNextExecTime(Date nextExecTime) {
        this.nextExecTime = nextExecTime;
    }

    public String getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(String execStatus) {
        this.execStatus = execStatus == null ? null : execStatus.trim();
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