package com.xsbweb.mapper;

import java.util.List;
import java.util.Map;

import com.xsbweb.vo.Company;
import com.xsbweb.vo.CompanyDrctr;
import com.xsbweb.vo.CompanyEquity;
import com.xsbweb.vo.CompanyFinance;
import com.xsbweb.vo.CompanyHolder;
import com.xsbweb.vo.CompanyInfoHistory;
import com.xsbweb.vo.extend.SbhqSencodVO;

public interface CompanyMapper {

	public Company getProjCompanyByPrc(Map<String, Object> param)throws Exception;
	
	/**
	 * 高管人员
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	public List<CompanyDrctr> getCompanyDrctrByStockId(String stockId)throws Exception;
	/**
	 * 股本状况
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	public List<CompanyEquity> getCompanyEquityByStockId(String stockId)throws Exception;
	/**
	 * 财务指标
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	public List<CompanyFinance> getCompanyFinanceByStockId(String stockId)throws Exception;
	/**
	 * 十大股东
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	public List<CompanyHolder> getCompanyHolderByStockId(String stockId)throws Exception;
	/**
	 * 历史沿革
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	public List<CompanyInfoHistory> getCompanyHistoryByStockId(String stockId)throws Exception;

	/**
	 * 公司概况
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	public SbhqSencodVO getCompanySituationByStockId(String stockId)throws Exception;
	/**
	 * 公司列表
	 * @param company
	 * @return
	 * @throws Exception
	 */
	public List<Company>getCompanyList(Company company)throws Exception;
	/**
	 * 公司数量
	 * @param company
	 * @return
	 * @throws Exception
	 */
	public int getCompanyCount(Company company)throws Exception;
	/**
	 * 通过id获取公司
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	public Company getCompanyByStockId(String stockId)throws Exception;
	/**
	 * 新增公司信息
	 * @param company
	 * @throws Exception
	 */
	public void addCompany(Company company)throws Exception;
	/**
	 * 通过stockId更新公司信息
	 * @param company
	 * @return
	 * @throws Exception
	 */
	public int updateCompanyBySockId(Company company)throws Exception;
	/**
	 * 通过stockId批量删除公司信息
	 * @param company
	 * @return
	 * @throws Exception
	 */
	public int batchDeleteCompanyBySockIds(String [] stockIds)throws Exception;
	/**
	 * 通过stockid以及dsmName获取高管信息
	 * @param stockId
	 * @param dsmName
	 * @return
	 * @throws Exception
	 */
	public CompanyDrctr getCompanyDrctrByDsmName(Map<String,Object> param )throws Exception;
	/**
	 * 通过stockid以及dsmName更新高管信息
	 * @param companyDrctr
	 * @return
	 * @throws Exception
	 */
	public int updateCompanyDrctrByDsmName(CompanyDrctr companyDrctr)throws Exception;
	/**
	 * 新增高管信息
	 * @param companyDrctr
	 * @throws Exception
	 */
	public void addCompanyDrctr(CompanyDrctr companyDrctr)throws Exception;
	/**
	 * 批量删除高管
	 * @param companyDrctr
	 * @throws Exception
	 */
	public void batchDeleteDrctr(CompanyDrctr companyDrctr)throws Exception;
	/**
	 * 获取股本信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public CompanyEquity getCompanyEquity(Map<String, Object> param)throws Exception;
	/**
	 * 新增股本信息
	 * @param companyEquity
	 * @throws Exception
	 */
	public void addCompanyEquity(CompanyEquity companyEquity)throws Exception;
	/**
	 * 批量股本信息
	 * @param companyEquity
	 * @throws Exception
	 */
	public int batchDeleteEquity(CompanyEquity companyEquity)throws Exception;
	/**
	 * 通过stockid以及reportDate更新股本信息
	 * @param companyEquity
	 * @return
	 * @throws Exception
	 */
	public int updateCompanyEquity(CompanyEquity companyEquity)throws Exception;
	/**
	 * 添加十大股东
	 * @param companyHolder
	 * @throws Exception
	 */
	public void addCompanyHolder(CompanyHolder companyHolder)throws Exception;
	/**
	 * 获取十大股东
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public CompanyHolder getCompanyHolder(Map<String, Object> param)throws Exception;
	/**
	 * 更新十大股东
	 * @param companyHolder
	 * @return
	 * @throws Exception
	 */
	public int updateCompanyHolder(CompanyHolder companyHolder)throws Exception;
	/**
	 * 批量删除十大股东
	 * @param companyHolder
	 * @return
	 * @throws Exception
	 */
	public int  batchDeleteHolder(CompanyHolder companyHolder)throws Exception;
	/**
	 * 新增CompanyFinance
	 * @param companyFinance
	 * @throws Exception
	 */
	public void addCompanyFinance(CompanyFinance companyFinance)throws Exception;
	/**
	 * 获取CompanyFinance
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public CompanyFinance getCompanyFinance(Map<String, Object> param)throws Exception;
	/**
	 * 更新CompanyFinance
	 * @param companyFinance
	 * @return
	 * @throws Exception
	 */
	public int updateCompanyFinance(CompanyFinance companyFinance)throws Exception;
	/**
	 * 批量删除CompanyFinance
	 * @param companyFinance
	 * @throws Exception
	 */
	public void batchDeleteFinance(CompanyFinance companyFinance)throws Exception;
}
