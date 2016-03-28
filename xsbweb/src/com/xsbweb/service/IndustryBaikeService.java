package com.xsbweb.service;

import java.util.List;

import com.xsbweb.vo.IndustryBaike;

public interface IndustryBaikeService {
	/***********总条数**************/
	public int getIndustryBaikeCounts(IndustryBaike bk)throws Exception;
	/************总列表*************/
	public List<IndustryBaike> getAllIndustryBaikeList(IndustryBaike bk)throws Exception;
	/**********删除***********/
	public int delIndustryBaike(String id)throws Exception;
	
	/********通过id获取列表**********/
	public IndustryBaike getIndustryBaikeById(String id)throws Exception;
	
	/*******修改**********/
	public int updateBaike(IndustryBaike bk)throws Exception;
	
	/******添加*******/
	public int insertBaike(IndustryBaike bk)throws Exception;
	
	/******获取要加的id*****/
	public int getMaxId()throws Exception;
	/**
	 * 批量删除百科
	 * @param baikeIdArrs
	 * @return
	 * @throws Exception
	 */
	public int batchDeleteIndustryBaike(String[] baikeIdArrs)throws Exception;
}
