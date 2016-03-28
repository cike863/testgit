package com.xsbweb.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsbweb.common.bean.ResultCode;
import com.xsbweb.service.CompanyService;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.vo.*;
import com.xsbweb.vo.extend.SbhqSencodVO;

@Controller
public class CompanyController {

	private Logger log = Logger.getLogger(CompanyController.class);
	
	@Autowired
	private CompanyService companyService;
	
	/**
	 * 获取高管人员
	 * @param stockId
	 * @param loginMethod
	 * @return
	 */
	@RequestMapping(value="/app/company/getCompanyDrctrByStockId.do",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getCompanyDrctrByStockId(
			HttpServletRequest request,
			@RequestParam("stockId") String stockId,
			@RequestParam("loginMethod") String loginMethod
			){
		Map<String,Object> map = new HashMap<String, Object>();
		//请求参数有误
		if(CommonUtils.isBlank(stockId)||CommonUtils.isBlank(loginMethod)){
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		List<CompanyDrctr> companyDrctrList = null;
		try {
			companyDrctrList = companyService.getCompanyDrctrByStockId(stockId);
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
			map.put("companyDrctrList", companyDrctrList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_SYSTEM);
			e.printStackTrace();
		}finally{
			request.setAttribute(ResultCode.RESULT_OBJECT, map);
		}
		return map;
	}
	
	/**
	 * 获取股本状况
	 * @param stockId
	 * @param loginMethod
	 * @return
	 */
	@RequestMapping(value="/app/company/getCompanyEquityByStockId.do",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getCompanyEquityByStockId(
			HttpServletRequest request,
			@RequestParam("stockId") String stockId,
			@RequestParam("loginMethod") String loginMethod
			){
		Map<String,Object> map = new HashMap<String, Object>();
		//请求参数有误
		if(CommonUtils.isBlank(stockId)||CommonUtils.isBlank(loginMethod)){
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		List<CompanyEquity> companyEquityList = null;
		try {
			companyEquityList = companyService.getCompanyEquityByStockId(stockId);
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
			map.put("companyEquityList",companyEquityList );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_SYSTEM);
			e.printStackTrace();
		}finally{
			request.setAttribute(ResultCode.RESULT_OBJECT, map);
		}
		return map;
	}
	
	/**
	 * 获取财务指标
	 * @param stockId
	 * @param loginMethod
	 * @return
	 */
	@RequestMapping(value="/app/company/getCompanyFinanceByStockId.do",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getCompanyFinanceByStockId(
			HttpServletRequest request,
			@RequestParam("stockId") String stockId,
			@RequestParam("loginMethod") String loginMethod
			){
		Map<String,Object> map = new HashMap<String, Object>();
		//请求参数有误
		if(CommonUtils.isBlank(stockId)||CommonUtils.isBlank(loginMethod)){
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		List<CompanyFinance> companyFinanceList = null;
		try {
			companyFinanceList = companyService.getCompanyFinanceByStockId(stockId);
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
			map.put("companyFinanceList", companyFinanceList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_SYSTEM);
			e.printStackTrace();
		}finally{
			request.setAttribute(ResultCode.RESULT_OBJECT, map);
		}
		return map;
	}
	
	/**
	 * 获取十大股东
	 * @param stockId
	 * @param loginMethod
	 * @return
	 */
	@RequestMapping(value="/app/company/getCompanyHolderByStockId.do",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getCompanyHolderByStockId(
			HttpServletRequest request,
			@RequestParam("stockId") String stockId,
			@RequestParam("loginMethod") String loginMethod
			){
		Map<String,Object> map = new HashMap<String, Object>();
		//请求参数有误
		if(CommonUtils.isBlank(stockId)||CommonUtils.isBlank(loginMethod)){
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		List<CompanyHolder> companyHolderList = null;
		try {
			companyHolderList = companyService.getCompanyHolderByStockId(stockId);
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
			map.put("companyHolderList", companyHolderList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_SYSTEM);
			e.printStackTrace();
		}finally{
			request.setAttribute(ResultCode.RESULT_OBJECT, map);
		}
		return map;
	}
	
	/**
	 * 获取历史沿革
	 * @param stockId
	 * @param loginMethod
	 * @return
	 */
	@RequestMapping(value="/app/company/getCompanyHistoryByStockId.do",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getCompanyHistoryByStockId(
			HttpServletRequest request,
			@RequestParam("stockId") String stockId,
			@RequestParam("loginMethod") String loginMethod
			){
		Map<String,Object> map = new HashMap<String, Object>();
		//请求参数有误
		if(CommonUtils.isBlank(stockId)||CommonUtils.isBlank(loginMethod)){
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		List<CompanyInfoHistory> companyHistoryList = null;
		try {
			companyHistoryList = companyService.getCompanyHistoryByStockId(stockId);
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
			if(companyHistoryList!=null && !companyHistoryList.isEmpty()){
				map.put("companyHistoryList", companyHistoryList.get(0));
			}else{
				map.put("companyHistoryList", null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_SYSTEM);
			e.printStackTrace();
		}finally{
			request.setAttribute(ResultCode.RESULT_OBJECT, map);
		}
		return map;
	}
	
	/**
	 * 获取公司概况
	 * @param request
	 * @param stockId
	 * @param loginMethod
	 * @return
	 */
	@RequestMapping(value="/app/company/situation",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getCompanySituationByStockId(
			HttpServletRequest request,
			@RequestParam("stockId") String stockId,
			@RequestParam("loginMethod") String loginMethod
			){
		Map<String,Object> map = new HashMap<String, Object>();
		//请求参数有误
		if(CommonUtils.isBlank(stockId)||CommonUtils.isBlank(loginMethod)){
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		SbhqSencodVO companySituation = null;
		try {
			companySituation = companyService.getCompanySituationByStockId(stockId);
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
			map.put("companySituation",companySituation==null?"":companySituation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_SYSTEM);
			e.printStackTrace();
		}finally{
			request.setAttribute(ResultCode.RESULT_OBJECT, map);
		}
		return map;
	}
}
