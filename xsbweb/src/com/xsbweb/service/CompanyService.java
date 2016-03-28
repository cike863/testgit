package com.xsbweb.service;

import java.util.List;

import com.xsbweb.vo.Company;
import com.xsbweb.vo.CompanyDrctr;
import com.xsbweb.vo.CompanyEquity;
import com.xsbweb.vo.CompanyFinance;
import com.xsbweb.vo.CompanyHolder;
import com.xsbweb.vo.CompanyInfoHistory;
import com.xsbweb.vo.extend.SbhqSencodVO;

public interface CompanyService {
	/**
	 * 路演大厅中，获取项目详情中的路演信息（公司信息）
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	public Company getProjCompanyByPrc(String stockId)throws Exception;
	
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
	 * @param Company
	 * @return
	 * @throws Exception
	 */
	public List<Company>getCompanyList(Company Company)throws Exception;
	/**
	 * 公司数量
	 * @param Company
	 * @return
	 * @throws Exception
	 */
	public int getCompanyCount(Company Company)throws Exception;
	/**
	 * 获取公司信息
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	public Company getCompanyByStockId(String stockId)throws Exception;
	/**
	 * 更新公司信息
	 * @param company
	 * @return
	 * @throws Exception
	 */
	public int updateCompanyByStockId(Company company)throws Exception;
	/**
	 * 批量删除公司
	 * @param company
	 * @return
	 * @throws Exception
	 */
	public int deleteCompanyByStockIds(String [] stockIds)throws Exception;
	/**
	 * 新增公司
	 * @param company
	 * @return
	 * @throws Exception
	 */
	public int addCompany(Company company)throws Exception;
	/**
	 * 通过stockid以及dsmName获取高管信息
	 * @param stockId
	 * @param dsmName
	 * @return
	 * @throws Exception
	 */
	public CompanyDrctr getCompanyDrctrByDsmName(String stockId,
			String dsmName)throws Exception;
	/**
	 * 通过stockid以及dsmName获取更新高管信息
	 * @param stockId
	 * @param dsmName
	 * @return
	 * @throws Exception
	 */
	public int editCompanyDrctrByDsmName(CompanyDrctr companyDrctr)throws Exception;
	/**
	 * 新增高管信息
	 * @param companyDrctr
	 * @return
	 * @throws Exception
	 */
	public int addCompanyDrctr(CompanyDrctr companyDrctr)throws Exception;
	/**
	 * 批量删除高管信息
	 * @param companyDrctr
	 * @return
	 * @throws Exception
	 */
	public int batchDeleteDrctr(CompanyDrctr companyDrctr)throws Exception;
	/**
	 * 新增股本信息
	 * @param companyEquity
	 * @return
	 * @throws Exception
	 */
	public int addCompanyEquity(CompanyEquity companyEquity)throws Exception;
	/**
	 * 通过stockId以及reportDate获取股本信息
	 * @param stockId
	 * @param reportDate
	 * @return
	 * @throws Exception
	 */
	public CompanyEquity getCompanyEquity(String stockId, String reportDate)throws Exception;
	/**
	 * 通过stockid以及reportDate更新股本信息
	 * @param companyEquity
	 * @return
	 * @throws Exception
	 */
	public int editCompanyEquity(CompanyEquity companyEquity)throws Exception;
	/**
	 * 批量删除股本信息
	 * @param companyEquity
	 * @return
	 */
	public int batchDeleteEquity(CompanyEquity companyEquity)throws Exception;
	/**
	 * 新增CompanyHolder
	 * @param companyHolder
	 * @return
	 * @throws Exception
	 */
	public int addCompanyHolder(CompanyHolder companyHolder)throws Exception;
	/**
	 * 获取CompanyHolder，通过stockId,holderName
	 * @param stockId
	 * @param holderName
	 * @return
	 * @throws Exception
	 */
	public CompanyHolder getCompanyHolder(String stockId, String holderName)throws Exception;
	/**
	 * 更新CompanyHolder
	 * @param companyHolder
	 * @return
	 * @throws Exception
	 */
	public int editCompanyHolder(CompanyHolder companyHolder)throws Exception;
	/**
	 * 批量删除CompanyHolder
	 * @param companyHolder
	 * @return
	 * @throws Exception
	 */
	public int batchDeleteHolder(CompanyHolder companyHolder)throws Exception;
	/**
	 * 新增CompanyFinance
	 * @param companyFinance
	 * @return
	 * @throws Exception
	 */
	public int addCompanyFinance(CompanyFinance companyFinance)throws Exception;
	/**
	 * 获取CompanyFinance，通过stockId,attrName
	 * @param stockId
	 * @param holderName
	 * @return
	 * @throws Exception
	 */
	public CompanyFinance getCompanyFinance(String stockId, String attrName)throws Exception;
	/**
	 * 更新companyFinance
	 * @param companyFinance
	 * @return
	 * @throws Exception
	 */
	public int editCompanyFinance(CompanyFinance companyFinance)throws Exception;
	/**
	 * 批量删除companyFinance
	 * @param companyFinance
	 * @return
	 * @throws Exception
	 */
	public int batchDeleteFinance(CompanyFinance companyFinance)throws Exception;

	
}
