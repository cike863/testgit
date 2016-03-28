package com.xsbweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xsbweb.common.bean.DataBaseConstant;
import com.xsbweb.mapper.CompanyMapper;
import com.xsbweb.service.CompanyService;
import com.xsbweb.util.MultipleDataSource;
import com.xsbweb.vo.Company;
import com.xsbweb.vo.CompanyDrctr;
import com.xsbweb.vo.CompanyEquity;
import com.xsbweb.vo.CompanyFinance;
import com.xsbweb.vo.CompanyHolder;
import com.xsbweb.vo.CompanyInfoHistory;
import com.xsbweb.vo.extend.SbhqSencodVO;

public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyMapper companyMapper;
	
	@Override
	public Company getProjCompanyByPrc(String stockId) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("stockId", stockId);
		param.put("prcFlag", null);
		Company comPany = companyMapper.getProjCompanyByPrc(param);
		return comPany;
	}

	@Override
	public List<CompanyDrctr> getCompanyDrctrByStockId(String stockId)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		return companyMapper.getCompanyDrctrByStockId(stockId);
	}

	@Override
	public List<CompanyEquity> getCompanyEquityByStockId(String stockId)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		return companyMapper.getCompanyEquityByStockId(stockId);
	}

	@Override
	public List<CompanyFinance> getCompanyFinanceByStockId(String stockId)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		return companyMapper.getCompanyFinanceByStockId(stockId);
	}

	@Override
	public List<CompanyHolder> getCompanyHolderByStockId(String stockId)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		return companyMapper.getCompanyHolderByStockId(stockId);
	}

	@Override
	public List<CompanyInfoHistory> getCompanyHistoryByStockId(String stockId)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return companyMapper.getCompanyHistoryByStockId(stockId);
	}

	/**
	 * 公司概况
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	@Override
	public SbhqSencodVO getCompanySituationByStockId(String stockId)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return companyMapper.getCompanySituationByStockId(stockId);
	}

	@Override
	public List<Company> getCompanyList(Company Company) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		try{
			return companyMapper.getCompanyList(Company);
		}catch(Exception e ){
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public int getCompanyCount(Company Company) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		try{			
			return companyMapper.getCompanyCount(Company);
		}catch(Exception e ){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public CompanyDrctr getCompanyDrctrByDsmName(String stockId,
			String dsmName) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("stockId", stockId);
		param.put("dsmName", dsmName);
		return companyMapper.getCompanyDrctrByDsmName(param);
	}

	@Override
	public int editCompanyDrctrByDsmName(CompanyDrctr companyDrctr)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		int falg=0;
		try{
			falg = companyMapper.updateCompanyDrctrByDsmName(companyDrctr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return falg;
	}

	@Override
	public int addCompanyDrctr(CompanyDrctr companyDrctr) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		int falg=0;
		try{
			companyMapper.addCompanyDrctr(companyDrctr);
			falg= 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return falg;
	}

	@Override
	public int batchDeleteDrctr(CompanyDrctr companyDrctr) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		int falg=0;
		try{
			companyMapper.batchDeleteDrctr(companyDrctr);
			falg= 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return falg;
	}

	@Override
	public int addCompanyEquity(CompanyEquity companyEquity) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		int falg=0;
		try{
			companyMapper.addCompanyEquity(companyEquity);
			falg= 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return falg;
	}

	@Override
	public CompanyEquity getCompanyEquity(String stockId, String reportDate)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("stockId", stockId);
		param.put("reportDate", reportDate);
		return companyMapper.getCompanyEquity(param);
	}

	@Override
	public int editCompanyEquity(CompanyEquity companyEquity) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		int falg=0;
		try{
			falg = companyMapper.updateCompanyEquity(companyEquity);
		}catch(Exception e){
			e.printStackTrace();
		}
		return falg;
	}

	@Override
	public int batchDeleteEquity(CompanyEquity companyEquity) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		int falg=0;
		try{
			companyMapper.batchDeleteEquity(companyEquity);
			falg= 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return falg;
	}

	@Override
	public int addCompanyHolder(CompanyHolder companyHolder) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		int falg=0;
		try{
			companyMapper.addCompanyHolder(companyHolder);
			falg= 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return falg;
	}

	@Override
	public CompanyHolder getCompanyHolder(String stockId, String holderName)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("stockId", stockId);
		param.put("holderName", holderName);
		return companyMapper.getCompanyHolder(param);
	}

	@Override
	public int editCompanyHolder(CompanyHolder companyHolder) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		int falg=0;
		try{
			falg = companyMapper.updateCompanyHolder(companyHolder);
		}catch(Exception e){
			e.printStackTrace();
		}
		return falg;
	}

	@Override
	public int batchDeleteHolder(CompanyHolder companyHolder) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		int falg=0;
		try{
			companyMapper.batchDeleteHolder(companyHolder);
			falg= 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return falg;
	}

	@Override
	public int addCompanyFinance(CompanyFinance companyFinance)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		int falg=0;
		try{
			companyMapper.addCompanyFinance(companyFinance);
			falg= 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return falg;
	}

	@Override
	public CompanyFinance getCompanyFinance(String stockId, String attrName)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("stockId", stockId);
		param.put("attrName", attrName);
		return companyMapper.getCompanyFinance(param);
	}

	@Override
	public int editCompanyFinance(CompanyFinance companyFinance)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		int falg=0;
		try{
			falg = companyMapper.updateCompanyFinance(companyFinance);
		}catch(Exception e){
			e.printStackTrace();
		}
		return falg;
	}

	@Override
	public int batchDeleteFinance(CompanyFinance companyFinance)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLAP);
		int falg=0;
		try{
			companyMapper.batchDeleteFinance(companyFinance);
			falg= 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return falg;
	}

	@Override
	public Company getCompanyByStockId(String stockId) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return companyMapper.getCompanyByStockId(stockId);
	}

	@Override
	public int updateCompanyByStockId(Company company) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		int flag=0;
		try{
			flag = companyMapper.updateCompanyBySockId(company);
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int deleteCompanyByStockIds(String [] stockIds) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		int flag=0;
		try{
			flag = companyMapper.batchDeleteCompanyBySockIds(stockIds);
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int addCompany(Company company) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		int flag=0;
		try{
			companyMapper.addCompany(company);
			flag = 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
}
