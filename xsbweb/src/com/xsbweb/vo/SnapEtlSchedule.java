package com.xsbweb.vo;

import com.xsbweb.common.bean.BasePo;

public class SnapEtlSchedule extends BasePo {
	
    private String taskTable;

    private String regex;
    
    private String taskDesc;

    private Integer taskPeriod;

    private String taskDependency;

    private String effectiveDate;

    private String extireDate;

    private String lastExecTime;

    private String nextExecTime;

    private String intrfcRecordType;

    private String intrfcMethod;

    private String intrfcType;

    private String execStatus;

    private String urlPart1;

    private String urlPart1Logic;

    private String urlPart2;

    private String urlPart2Logic;

    private String urlPart3;

    private String urlPart3Logic;

    private String urlPart4;

    private String urlPart4Logic;

    private String urlPart5;

    private String urlPart5Logic;

    private String targetColumn01;

    private String targetColumn02;

    private String targetColumn03;

    private String targetColumn04;

    private String targetColumn05;

    private String targetColumn06;

    private String targetColumn07;

    private String targetColumn08;

    private String targetColumn09;

    private String targetColumn10;

    private String createDate;

    private String lastUpdateDate;

    private String scheduleDate;
    
    private String threadHost;
    
    private String threadPort;

    private String threadName;
    
    public String getTaskTable() {
        return taskTable;
    }

    public void setTaskTable(String taskTable) {
        this.taskTable = taskTable == null ? null : taskTable.trim();
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc == null ? null : taskDesc.trim();
    }

    public Integer getTaskPeriod() {
        return taskPeriod;
    }

    public void setTaskPeriod(Integer taskPeriod) {
        this.taskPeriod = taskPeriod;
    }

    public String getTaskDependency() {
        return taskDependency;
    }

    public void setTaskDependency(String taskDependency) {
        this.taskDependency = taskDependency == null ? null : taskDependency.trim();
    }

  

    public String getIntrfcRecordType() {
        return intrfcRecordType;
    }

    public void setIntrfcRecordType(String intrfcRecordType) {
        this.intrfcRecordType = intrfcRecordType == null ? null : intrfcRecordType.trim();
    }

    public String getIntrfcMethod() {
        return intrfcMethod;
    }

    public void setIntrfcMethod(String intrfcMethod) {
        this.intrfcMethod = intrfcMethod == null ? null : intrfcMethod.trim();
    }

    public String getIntrfcType() {
        return intrfcType;
    }

    public void setIntrfcType(String intrfcType) {
        this.intrfcType = intrfcType == null ? null : intrfcType.trim();
    }

    public String getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(String execStatus) {
        this.execStatus = execStatus == null ? null : execStatus.trim();
    }

    public String getUrlPart1() {
        return urlPart1;
    }

    public void setUrlPart1(String urlPart1) {
        this.urlPart1 = urlPart1 == null ? null : urlPart1.trim();
    }

    public String getUrlPart1Logic() {
        return urlPart1Logic;
    }

    public void setUrlPart1Logic(String urlPart1Logic) {
        this.urlPart1Logic = urlPart1Logic == null ? null : urlPart1Logic.trim();
    }

    public String getUrlPart2() {
        return urlPart2;
    }

    public void setUrlPart2(String urlPart2) {
        this.urlPart2 = urlPart2 == null ? null : urlPart2.trim();
    }

    public String getUrlPart2Logic() {
        return urlPart2Logic;
    }

    public void setUrlPart2Logic(String urlPart2Logic) {
        this.urlPart2Logic = urlPart2Logic == null ? null : urlPart2Logic.trim();
    }

    public String getUrlPart3() {
        return urlPart3;
    }

    public void setUrlPart3(String urlPart3) {
        this.urlPart3 = urlPart3 == null ? null : urlPart3.trim();
    }

    public String getUrlPart3Logic() {
        return urlPart3Logic;
    }

    public void setUrlPart3Logic(String urlPart3Logic) {
        this.urlPart3Logic = urlPart3Logic == null ? null : urlPart3Logic.trim();
    }

    public String getUrlPart4() {
        return urlPart4;
    }

    public void setUrlPart4(String urlPart4) {
        this.urlPart4 = urlPart4 == null ? null : urlPart4.trim();
    }

    public String getUrlPart4Logic() {
        return urlPart4Logic;
    }

    public void setUrlPart4Logic(String urlPart4Logic) {
        this.urlPart4Logic = urlPart4Logic == null ? null : urlPart4Logic.trim();
    }

    public String getUrlPart5() {
        return urlPart5;
    }

    public void setUrlPart5(String urlPart5) {
        this.urlPart5 = urlPart5 == null ? null : urlPart5.trim();
    }

    public String getUrlPart5Logic() {
        return urlPart5Logic;
    }

    public void setUrlPart5Logic(String urlPart5Logic) {
        this.urlPart5Logic = urlPart5Logic == null ? null : urlPart5Logic.trim();
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex == null ? null : regex.trim();
    }

    public String getTargetColumn01() {
        return targetColumn01;
    }

    public void setTargetColumn01(String targetColumn01) {
        this.targetColumn01 = targetColumn01 == null ? null : targetColumn01.trim();
    }

    public String getTargetColumn02() {
        return targetColumn02;
    }

    public void setTargetColumn02(String targetColumn02) {
        this.targetColumn02 = targetColumn02 == null ? null : targetColumn02.trim();
    }

    public String getTargetColumn03() {
        return targetColumn03;
    }

    public void setTargetColumn03(String targetColumn03) {
        this.targetColumn03 = targetColumn03 == null ? null : targetColumn03.trim();
    }

    public String getTargetColumn04() {
        return targetColumn04;
    }

    public void setTargetColumn04(String targetColumn04) {
        this.targetColumn04 = targetColumn04 == null ? null : targetColumn04.trim();
    }

    public String getTargetColumn05() {
        return targetColumn05;
    }

    public void setTargetColumn05(String targetColumn05) {
        this.targetColumn05 = targetColumn05 == null ? null : targetColumn05.trim();
    }

    public String getTargetColumn06() {
        return targetColumn06;
    }

    public void setTargetColumn06(String targetColumn06) {
        this.targetColumn06 = targetColumn06 == null ? null : targetColumn06.trim();
    }

    public String getTargetColumn07() {
        return targetColumn07;
    }

    public void setTargetColumn07(String targetColumn07) {
        this.targetColumn07 = targetColumn07 == null ? null : targetColumn07.trim();
    }

    public String getTargetColumn08() {
        return targetColumn08;
    }

    public void setTargetColumn08(String targetColumn08) {
        this.targetColumn08 = targetColumn08 == null ? null : targetColumn08.trim();
    }

    public String getTargetColumn09() {
        return targetColumn09;
    }

    public void setTargetColumn09(String targetColumn09) {
        this.targetColumn09 = targetColumn09 == null ? null : targetColumn09.trim();
    }

    public String getTargetColumn10() {
        return targetColumn10;
    }

    public void setTargetColumn10(String targetColumn10) {
        this.targetColumn10 = targetColumn10 == null ? null : targetColumn10.trim();
    }

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getExtireDate() {
		return extireDate;
	}

	public void setExtireDate(String extireDate) {
		this.extireDate = extireDate;
	}

	public String getLastExecTime() {
		return lastExecTime;
	}

	public void setLastExecTime(String lastExecTime) {
		this.lastExecTime = lastExecTime;
	}

	public String getNextExecTime() {
		return nextExecTime;
	}

	public void setNextExecTime(String nextExecTime) {
		this.nextExecTime = nextExecTime;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getThreadHost() {
		return threadHost;
	}

	public void setThreadHost(String threadHost) {
		this.threadHost = threadHost == null ? null : threadHost.trim();
	}

	public String getThreadPort() {
		return threadPort;
	}

	public void setThreadPort(String threadPort) {
		this.threadPort = threadPort == null ? null : threadPort.trim();
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName == null ? null : threadName.trim();
	}
    
}