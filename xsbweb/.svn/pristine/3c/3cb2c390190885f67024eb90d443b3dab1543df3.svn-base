package com.xsbweb.controller.manage;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.common.bean.ResultCode;
import com.xsbweb.service.CompanyService;
import com.xsbweb.vo.Company;
import com.xsbweb.vo.CompanyDrctr;
import com.xsbweb.vo.CompanyEquity;
import com.xsbweb.vo.CompanyFinance;
import com.xsbweb.vo.CompanyHolder;

@Controller
@RequestMapping("/admin/company")
public class CompanyManagerController {
	private static Logger log = Logger.getLogger(CompanyManagerController.class);
	@Autowired
	private CompanyService companyService;
	/**
	 * 公司列表
	 * @param company
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getCompanyList(Company company) throws Exception{
		log.info("获取公司列表开始");
		ModelAndView mav = new ModelAndView();
		int count = companyService.getCompanyCount(company);
		company.setTotalRecord(count);
		List<Company> companyList = companyService.getCompanyList(company);
		mav.setViewName("manage/company/queryCompanyList");
		mav.addObject("companyList", companyList);
		mav.addObject("company", company);
		log.info("获取公司列表结束");
		return mav;
	}
	/**
	 * 新增公司跳转
	 * @param company
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toAddCompany.do",method=RequestMethod.GET)
	public ModelAndView toAddCompany(Company company) throws Exception{
		log.info("新增公司跳转");
		ModelAndView mav = new ModelAndView();
		mav.addObject("company", company);
		mav.setViewName("manage/company/addForeCompany");
		return mav;
	}
	/**
	 * 新增公司提交
	 * @param company
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView addCompany(Company company) throws Exception{
		log.info("新增公司信息提交");
		ModelAndView mav = new ModelAndView();
		int result = companyService.addCompany(company);
		if(result>0){
			mav.setViewName("redirect:/admin/company");
		}else{
			mav.addObject("company", company);
			mav.setViewName("manage/company/addForeCompany");
		}
		return mav;
	}
	/**
	 * 依据stockId获取公司信息，编辑页面跳转
	 * @param stockId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/{stockId}",method=RequestMethod.GET)
	public ModelAndView getCompanyByStockId(@PathVariable("stockId") String stockId) throws Exception{
		log.info("获取公司信息");
		ModelAndView mav = new ModelAndView();
		Company company = companyService.getCompanyByStockId(stockId);
		company.setStockId(stockId);
		mav.addObject("company", company);
		mav.setViewName("manage/company/editForeCompany");
		return mav;
	}
	/**
	 * 编辑公司信息提交
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{stockId}/upd",method=RequestMethod.PUT)
	public ModelAndView editCompanyByStockId(Company company) throws Exception{
		log.info("编辑公司信息提交");
		ModelAndView mav = new ModelAndView();
		int result = companyService.updateCompanyByStockId(company);
		if(result>0){
			mav.setViewName("redirect:/admin/company");
		}else{
			mav.addObject("company", company);
			mav.setViewName("manage/company/editForeCompany");
		}		
		return mav;
	}
	/**
	 * 批量删除公司信息
	 * @param company
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{stockIds}/del",method=RequestMethod.DELETE)
	@ResponseBody
	public Map<String,Object> deleteCompanyByStockIds(@PathVariable("stockIds") String [] stockIds) throws Exception{
		log.info("批量删除公司信息");
		Map<String,Object> map = new HashMap<String,Object>();
		int result = companyService.deleteCompanyByStockIds(stockIds);
		if(result>0){
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
		}else{
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		}		
		return map;
	}
	
	/**
	 * 公司详情列表
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getCompanyDetailListrByStockId.do",method=RequestMethod.GET)
	public ModelAndView getCompanyDetailListrByStockId(
			@RequestParam("stockId") String stockId,
			@RequestParam(value="type",required=false) String type) throws Exception{
		log.info("获取公司详情列表--默认加载公司高管人员");
		ModelAndView mav = new ModelAndView();
		//List<CompanyDrctr> companyDrctrList = companyService.getCompanyDrctrByStockId(stockId);
		//mav.addObject("companyDrctrList", companyDrctrList);
		mav.addObject("stockId",stockId);
		if("equity".equals(type)){
			mav.addObject("type","equity");
		}else if("holder".equals(type)){
			mav.addObject("type","holder");
		}else if("finance".equals(type)){
			mav.addObject("type","finance");
		}else{
			mav.addObject("type","drctr");
		}
		mav.setViewName("manage/company/queryCompanyDetailList");
		return mav;
	}
	
	/* 公司高管人员开始 */
	/**
	 * 依据stockId获取公司高管人员
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getCompanyDrctrByStockId.do",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getCompanyDrctrByStockId(@RequestParam("stockId") String stockId) throws Exception{
		log.info("获取公司高管人员");
		//ModelAndView mav = new ModelAndView();
		Map<String,Object> map = new HashMap<String,Object>();
		List<CompanyDrctr> companyDrctrList = companyService.getCompanyDrctrByStockId(stockId);
		//mav.addObject("companyDrctrList", companyDrctrList);
		//mav.addObject("stockId",stockId);
		map.put("companyDrctrList", companyDrctrList);
		//mav.setViewName("manage/company/queryCompanyDetailList");
		return map;
	}
	/**
	 * 新增公司高管
	 * @param companyDrctr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toAddCompanyDrctr.do",method=RequestMethod.GET)
	public ModelAndView toAddCompanyDrctr (CompanyDrctr companyDrctr)throws Exception{
		log.info("新增公司高管人员列表");
		ModelAndView mav = new ModelAndView();
		mav.addObject("companyDrctr", companyDrctr);
		mav.setViewName("manage/company/companyDrctr/addCompanyDrctrs");
		//mav.setViewName("manage/company/companyDrctr/editCompanyDrctr");
		return mav;
	}
	/**
	 * 新增公司高管提交
	 * @param companyDrctr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addCompanyDrctr.do",method=RequestMethod.POST)
	public ModelAndView addCompanyDrctr (CompanyDrctr companyDrctr)throws Exception{
		log.info("新增公司高管人员列表");
		ModelAndView mav = new ModelAndView();
		int flag = companyService.addCompanyDrctr(companyDrctr);
		if(flag>0){
			mav.addObject("stockId",companyDrctr.getStockId());
			mav.addObject("type","drctr");
			mav.setViewName("manage/company/queryCompanyDetailList");
		}else{
			mav.addObject("companyDrctr", companyDrctr);
			mav.setViewName("manage/company/companyDrctr/addCompanyDrctrs");
		}
		//mav.setViewName("manage/company/companyDrctr/editCompanyDrctr");
		return mav;
	}
	/**
	 * 编辑CompanyDrctr跳转
	 * @param companyDrctr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toEditCompanyDrctrByDsmName.do",method=RequestMethod.GET)
	public ModelAndView toEditCompanyDrctrByDsmName (
			@RequestParam("stockId") String stockId,
			@RequestParam("dsmName") String dsmName)throws Exception{
		log.info("编辑CompanyDrctr跳转");
		ModelAndView mav = new ModelAndView();
		CompanyDrctr companyDrctr = companyService.getCompanyDrctrByDsmName(stockId,dsmName);
		//companyDrctr.setStockId(stockId);
		mav.addObject("companyDrctr", companyDrctr);
		mav.setViewName("manage/company/companyDrctr/editCompanyDrctr");
		return mav;
	}
	/**
	 * 编辑CompanyDrctr提交
	 * @param companyDrctr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/editCompanyDrctrByDsmName.do",method=RequestMethod.POST)
	public ModelAndView editCompanyDrctrByDsmName (CompanyDrctr companyDrctr
			)throws Exception{
		log.info("编辑CompanyDrctr提交");
		ModelAndView mav = new ModelAndView();
		int flag = companyService.editCompanyDrctrByDsmName(companyDrctr);
		if(flag>0){
			mav.addObject("stockId",companyDrctr.getStockId());
			mav.addObject("type","drctr");
			mav.setViewName("manage/company/queryCompanyDetailList");
		}else{
			mav.addObject("companyDrctr", companyDrctr);
			mav.setViewName("manage/company/CompanyDrctr/editCompanyDrctr");
		}
		return mav;
	}
	/**
	 * 批量删除高管
	 * @param companyDrctr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/batchDeleteDrctr",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> batchDeleteDrctr (CompanyDrctr companyDrctr
			)throws Exception{
		log.info("批量删除CompanyDrct");
		Map<String,Object>map = new HashMap<String,Object>();
		int flag = companyService.batchDeleteDrctr(companyDrctr);
		if(flag>0){
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
		}else{
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		}
		return map;
	}
	/* 公司高管人员结束 */
	
	
	/* 公司股本状况开始*/
	/**
	 * 依据stockId获取股本状况
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getCompanyEquityByStockId.do",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getCompanyEquityByStockId(@RequestParam("stockId") String stockId,Model model) throws Exception{
		log.info("获取公司股本状况");
		//ModelAndView mav = new ModelAndView();
		Map<String,Object> map = new HashMap<String,Object>();
		List<CompanyEquity> companyEquityList = companyService.getCompanyEquityByStockId(stockId);
		//mav.addObject("companyEquityList", companyEquityList);
		//mav.addObject("stockId",stockId);
		//mav.setViewName("manage/company/queryCompanyDetailList");
		//model.addAttribute("companyEquityList", companyEquityList);
		map.put("companyEquityList", companyEquityList);
		return map;
	}
	/**
	 * 新增股本状况跳转
	 * @param companyDrctr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toAddCompanyEquity.do",method=RequestMethod.GET)
	public ModelAndView toAddCompanyEquity (CompanyEquity companyEquity)throws Exception{
		log.info("新增股本状况");
		ModelAndView mav = new ModelAndView();
		mav.addObject("companyEquity", companyEquity);
		mav.setViewName("manage/company/companyEquity/addCompanyEquity");
		//mav.setViewName("manage/company/companyDrctr/editCompanyDrctr");
		return mav;
	}
	/**
	 * 新增股本状况提交
	 * @param companyDrctr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addCompanyEquity.do",method=RequestMethod.POST)
	public ModelAndView addCompanyEquity (CompanyEquity companyEquity)throws Exception{
		log.info("新增股本状况提交");
		ModelAndView mav = new ModelAndView();
		int flag = companyService.addCompanyEquity(companyEquity);
		if(flag>0){
			mav.addObject("stockId",companyEquity.getStockId());
			mav.addObject("type","equity");
			mav.setViewName("manage/company/queryCompanyDetailList");
		}else{
			mav.addObject("companyEquity", companyEquity);
			mav.setViewName("manage/company/companyEquity/addCompanyEquity");
		}
		return mav;
	}
	/**
	 * 编辑CompanyEquity跳转
	 * @param companyDrctr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toEditCompanyEquity.do",method=RequestMethod.GET)
	public ModelAndView toEditCompanyEquity (
			@RequestParam("stockId") String stockId,
			@RequestParam("reportDate") String reportDate)throws Exception{
		log.info("编辑CompanyEquity跳转");
		ModelAndView mav = new ModelAndView();
		CompanyEquity companyEquity = companyService.getCompanyEquity(stockId,reportDate);
		//companyDrctr.setStockId(stockId);
		mav.addObject("companyEquity", companyEquity);
		mav.setViewName("manage/company/companyEquity/editCompanyEquity");
		return mav;
	}
	/**
	 * 编辑CompanyEquity提交
	 * @param companyEquity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/editCompanyEquity.do",method=RequestMethod.POST)
	public ModelAndView editCompanyEquity (CompanyEquity companyEquity
			)throws Exception{
		log.info("编辑CompanyEquity提交");
		ModelAndView mav = new ModelAndView();
		int flag = companyService.editCompanyEquity(companyEquity);
		if(flag>0){
			mav.addObject("stockId",companyEquity.getStockId());
			mav.addObject("type","equity");
			mav.setViewName("manage/company/queryCompanyDetailList");
		}else{
			mav.addObject("companyEquity", companyEquity);
			mav.setViewName("manage/company/CompanyEquity/editCompanyEquity");
		}
		return mav;
	}
	/**
	 * 批量删除CompanyEquity
	 * @param companyEquity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/batchDeleteEquity",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> batchDeleteEquity (CompanyEquity companyEquity
			)throws Exception{
		log.info("批量删除CompanyEquity");
		Map<String,Object>map = new HashMap<String,Object>();
		int flag = companyService.batchDeleteEquity(companyEquity);
		if(flag>0){
			map.put("type","equity");
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
		}else{
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		}
		return map;
	}
	/* 公司股本状况结束 */
	
	/* 十大股东开始 */
	/**
	 * 依据stockId获取十大股东
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getCompanyHolderByStockId.do",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getCompanyHolderByStockId(@RequestParam("stockId") String stockId) throws Exception{
		log.info("获取公司十大股东");
		//ModelAndView mav = new ModelAndView();
		Map<String,Object> map = new HashMap<String,Object>();
		List<CompanyHolder> companyHolderList = companyService.getCompanyHolderByStockId(stockId);
		//mav.addObject("companyHolderList", companyHolderList);
		//mav.addObject("stockId",stockId);
		//mav.setViewName("manage/company/queryCompanyDetailList");
		map.put("companyHolderList", companyHolderList);
		return map;
	}
	/**
	 * 新增十大股东跳转
	 * @param CompanyHolder
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toAddCompanyHolder.do",method=RequestMethod.GET)
	public ModelAndView toAddCompanyHolder (CompanyHolder companyHolder)throws Exception{
		log.info("新增十大股东");
		ModelAndView mav = new ModelAndView();
		mav.addObject("companyHolder", companyHolder);
		mav.setViewName("manage/company/companyHolder/addCompanyHolder");
		//mav.setViewName("manage/company/companyDrctr/editCompanyDrctr");
		return mav;
	}
	/**
	 * 新增十大股东提交
	 * @param CompanyHolder
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addCompanyHolder.do",method=RequestMethod.POST)
	public ModelAndView addCompanyHolder (CompanyHolder companyHolder)throws Exception{
		log.info("新增十大股东提交");
		ModelAndView mav = new ModelAndView();
		int flag = companyService.addCompanyHolder(companyHolder);
		if(flag>0){
			mav.addObject("stockId",companyHolder.getStockId());
			mav.addObject("type","holder");
			mav.setViewName("manage/company/queryCompanyDetailList");
		}else{
			mav.addObject("companyHolder", companyHolder);
			mav.setViewName("manage/company/companyHolder/addCompanyHolder");
		}
		return mav;
	}
	/**
	 * 编辑CompanyHolder跳转
	 * @param CompanyHolder
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toEditCompanyHolder.do",method=RequestMethod.GET)
	public ModelAndView toEditCompanyHolder (
			@RequestParam("stockId") String stockId,
			@RequestParam("holderName") String holderName)throws Exception{
		log.info("编辑CompanyHolder跳转");
		ModelAndView mav = new ModelAndView();
		CompanyHolder companyHolder = companyService.getCompanyHolder(stockId,holderName);
		//companyDrctr.setStockId(stockId);
		mav.addObject("companyHolder", companyHolder);
		mav.setViewName("manage/company/companyHolder/editCompanyHolder");
		return mav;
	}
	/**
	 * 编辑CompanyHolder提交
	 * @param CompanyHolder
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/editCompanyHolder.do",method=RequestMethod.POST)
	public ModelAndView editCompanyHolder (CompanyHolder companyHolder
			)throws Exception{
		log.info("编辑CompanyHolder提交");
		ModelAndView mav = new ModelAndView();
		int flag = companyService.editCompanyHolder(companyHolder);
		if(flag>0){
			mav.addObject("stockId",companyHolder.getStockId());
			mav.addObject("type","holder");
			mav.setViewName("manage/company/queryCompanyDetailList");
		}else{
			mav.addObject("companyEquity", companyHolder);
			mav.setViewName("manage/company/CompanyHolder/editCompanyHolder");
		}
		return mav;
	}
	/**
	 * 批量删除CompanyEquity
	 * @param companyEquity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/batchDeleteHolder",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> batchDeleteHolder (CompanyHolder companyHolder
			)throws Exception{
		log.info("批量删除CompanyHolder");
		Map<String,Object>map = new HashMap<String,Object>();
		int flag = companyService.batchDeleteHolder(companyHolder);
		if(flag>0){
			map.put("type","holder");
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
		}else{
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		}
		return map;
	}
	/* 十大股东结束 */
	
	
	/*财务指标开始*/
	/**
	 * 依据stockId获取财务指标
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getCompanyFinanceByStockId.do",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getCompanyFinanceByStockId(@RequestParam("stockId") String stockId) throws Exception{
		log.info("获取公司财务指标");
		//ModelAndView mav = new ModelAndView();
		Map<String,Object> map = new HashMap<String,Object>();
		List<CompanyFinance> companyFinanceList = companyService.getCompanyFinanceByStockId(stockId);
		//mav.addObject("companyFinanceList", companyFinanceList);
		//mav.addObject("stockId",stockId);
		map.put("companyFinanceList", companyFinanceList);
		//mav.setViewName("manage/company/queryCompanyDetailList");
		return map;
	}
	/**
	 * 新增财务分析跳转
	 * @param CompanyFinance
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toAddCompanyFinance.do",method=RequestMethod.GET)
	public ModelAndView toAddCompanyFinance (CompanyFinance companyFinance)throws Exception{
		log.info("财务分析");
		ModelAndView mav = new ModelAndView();
		mav.addObject("companyFinance", companyFinance);
		mav.setViewName("manage/company/companyFinance/addCompanyFinance");
		//mav.setViewName("manage/company/companyDrctr/editCompanyDrctr");
		return mav;
	}
	/**
	 * 新增财务分析提交
	 * @param CompanyFinance
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addCompanyFinance.do",method=RequestMethod.POST)
	public ModelAndView addCompanyFinance (CompanyFinance companyFinance)throws Exception{
		log.info("新增财务分析提交");
		ModelAndView mav = new ModelAndView();
		int flag = companyService.addCompanyFinance(companyFinance);
		if(flag>0){
			mav.addObject("stockId",companyFinance.getStockId());
			mav.addObject("type","finance");
			mav.setViewName("manage/company/queryCompanyDetailList");
		}else{
			mav.addObject("companyFinance", companyFinance);
			mav.setViewName("manage/company/companyFinance/addCompanyFinance");
		}
		return mav;
	}
	/**
	 * 编辑CompanyFinance跳转
	 * @param CompanyFinance
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toEditCompanyFinance.do",method=RequestMethod.GET)
	public ModelAndView toEditCompanyFinance (
			@RequestParam("stockId") String stockId,
			@RequestParam("attrName") String attrName)throws Exception{
		log.info("编辑CompanyFinance跳转");
		ModelAndView mav = new ModelAndView();
		CompanyFinance companyFinance = companyService.getCompanyFinance(stockId,attrName);
		//companyDrctr.setStockId(stockId);
		mav.addObject("companyFinance", companyFinance);
		mav.setViewName("manage/company/companyFinance/editCompanyFinance");
		return mav;
	}
	/**
	 * 编辑CompanyFinance提交
	 * @param CompanyFinance
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/editCompanyFinance.do",method=RequestMethod.POST)
	public ModelAndView editCompanyFinance (CompanyFinance companyFinance
			)throws Exception{
		log.info("编辑CompanyFinance提交");
		ModelAndView mav = new ModelAndView();
		int flag = companyService.editCompanyFinance(companyFinance);
		if(flag>0){
			mav.addObject("stockId",companyFinance.getStockId());
			mav.addObject("type","finance");
			mav.setViewName("manage/company/queryCompanyDetailList");
		}else{
			mav.addObject("companyFinance", companyFinance);
			mav.setViewName("manage/company/CompanyFinance/editCompanyFinance");
		}
		return mav;
	}
	/**
	 * 批量删除CompanyFinance
	 * @param CompanyFinance
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/batchDeleteFinance",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> batchDeleteFinance (CompanyFinance companyFinance
			)throws Exception{
		log.info("批量删除CompanyFinance");
		Map<String,Object>map = new HashMap<String,Object>();
		int flag = companyService.batchDeleteFinance(companyFinance);
		if(flag>0){
			map.put("type","holder");
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
		}else{
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		}
		return map;
	}
	/* 财务结束 */
}
