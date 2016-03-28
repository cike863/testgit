package com.xsbweb.vo;

import java.util.Date;

public class Department {
    private String departmentId;

    private String departmentName;

    private String departmentLeader;

    private String fatherDepartmentId;

    private Date effectiveDate;

    private Date extireDate;

    private Date createDate;

    private Date lastUpdateDate;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public String getDepartmentLeader() {
        return departmentLeader;
    }

    public void setDepartmentLeader(String departmentLeader) {
        this.departmentLeader = departmentLeader == null ? null : departmentLeader.trim();
    }

    public String getFatherDepartmentId() {
        return fatherDepartmentId;
    }

    public void setFatherDepartmentId(String fatherDepartmentId) {
        this.fatherDepartmentId = fatherDepartmentId == null ? null : fatherDepartmentId.trim();
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