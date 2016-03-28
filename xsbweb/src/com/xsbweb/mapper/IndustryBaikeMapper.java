package com.xsbweb.mapper;

import java.util.List;

import com.xsbweb.vo.IndustryBaike;

public interface IndustryBaikeMapper {
	/******获取所有列表数据*****/
    public List<IndustryBaike> getAllIndustryBaikeList(IndustryBaike bk)throws Exception;
    /******获取总数*****/
	public int getIndustryBaikeCounts(IndustryBaike bk)throws Exception;
	/******删除*****/
	public int delIndustryBaike(String id)throws Exception;
	
	/******通过id获取列表*****/
	public IndustryBaike getIndustryBaikeById(String id)throws Exception;
	
	/******更新*****/
	public int updateBaike(IndustryBaike bk)throws Exception;
	
	/******添加*****/
	public int insertBaike(IndustryBaike bk)throws Exception;
	
	/******获取要加的id*****/
	public int getMaxId()throws Exception;
	/**
	 * 批量删除
	 * @param baikeIdArrs
	 * @return
	 * @throws Exception
	 */
	public int batchDeleteIndustryBaike(String[] baikeIdArrs)throws Exception;
}
