package com.xsbweb.vo;

public class EtlThread {

	private String threadHost;
	
	private String threadPort;
	
	private String threadName;
	
	private String lastStartTime;
	
	private String lastStopTime;
	
	private String threadStatus;
	
	private String lastUpdateDate;
	
	private String createdDate;
	
	private String lastUpdateBy;
	
	private String createdBy;
	
	private String description;
	
	 private String taskTable;

	public String getTaskTable() {
		return taskTable;
	}

	public void setTaskTable(String taskTable) {
		this.taskTable = taskTable == null ? null : taskTable.trim();
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

	public String getLastStartTime() {
		return lastStartTime;
	}

	public void setLastStartTime(String lastStartTime) {
		this.lastStartTime = lastStartTime == null ? null : lastStartTime.trim();
	}

	public String getLastStopTime() {
		return lastStopTime;
	}

	public void setLastStopTime(String lastStopTime) {
		this.lastStopTime = lastStopTime == null ? null : lastStopTime.trim();
	}

	public String getThreadStatus() {
		return threadStatus;
	}

	public void setThreadStatus(String threadStatus) {
		this.threadStatus = threadStatus == null ? null : threadStatus.trim();
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate == null ? null : lastUpdateDate.trim();
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate == null ? null : createdDate.trim();
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy == null ? null : lastUpdateBy.trim();
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy == null ? null : createdBy.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}
}
