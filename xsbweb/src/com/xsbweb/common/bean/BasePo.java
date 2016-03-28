package com.xsbweb.common.bean;

public class BasePo {

	public static int DEFAULT_PAGESIZE = 10;  
	
	private int pageNo=1;          //当前页码  
	
	private int pageSize=10;        //每页行数  
	
	private int totalRecord;      //总记录数  
	
	private int totalPage;        //总页数  
	
	private int currentRecord; //当前数
	
	public int getCurrentRecord() {
		if(currentRecord==0){
			currentRecord = (this.pageNo-1)*this.pageSize;
		}
		return currentRecord;
	}
	public void setCurrentRecord(int currentRecord) {
		this.currentRecord = currentRecord;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo==0?1:pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
	 
}
