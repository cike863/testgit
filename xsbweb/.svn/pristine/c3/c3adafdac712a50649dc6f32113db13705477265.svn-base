package com.xsbweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xsbweb.common.bean.DataBaseConstant;
import com.xsbweb.controller.app.AppLoginRegistController;
import com.xsbweb.mapper.CustomerMapper;
import com.xsbweb.mapper.StaffMapper;
import com.xsbweb.service.LoginRegisterService;
import com.xsbweb.util.MultipleDataSource;
import com.xsbweb.util.XsbBusinessUtil;
import com.xsbweb.vo.Customer;
import com.xsbweb.vo.Staff;
import com.xsbweb.vo.extend.*;

public class LoginRegisterServiceImpl implements LoginRegisterService {

	private Logger log = Logger.getLogger(LoginRegisterServiceImpl.class);
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private StaffMapper staffMapper;
	/**
	 * 用来校验登录信息
	 * @return 用户ID
	 * @throws Exception
	 */
	@Override
	public CustomerVO validateLogin(CustomerVO customerVO) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("customerUname", customerVO.getCustomerUname());
		param.put("customerPassword", customerVO.getCustomerPassword());
		param.put("ip", customerVO.getIp());
		param.put("loginMethod", customerVO.getLoginMethod());
		param.put("prcFlag", null);
		//param.put("failedCounts", null);
		param.put("customerId", null);
		customerMapper.validateLogin(param);
		CustomerVO cVO = new CustomerVO();
		cVO.setCustomerId((String)param.get("customerId"));
		cVO.setPrcFlag((Integer)param.get("prcFlag"));
		return cVO;
	}
	
	
	/**
	 * 根据用户名ID获取用户信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public CustomerVO getCustomerById(String customerId) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		List<CustomerVO> customerVOList = customerMapper.getCustomerById(customerId);
		if(customerVOList == null || customerVOList.isEmpty()){
			return null;
		}
		return customerVOList.get(0);
	}

	/**
	 * 用来校验注册信息
	 * @return 用户ID
	 * @throws Exception
	 */
	@Override
	public Integer validateRegister(CustomerVO customerVO) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("customerPhoneNo", customerVO.getCustomerPhoneNo());
		param.put("customerEmail", customerVO.getCustomerEmail());
		param.put("prcFlag", null);
		customerMapper.validateRegister(param);
		return (Integer)param.get("prcFlag");
	}

	@Override
	public Integer register(CustomerVO customerVO) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Map<String, Object> param = XsbBusinessUtil.initCustomerParam(customerVO);
		customerMapper.addCustomer(param);
		return (Integer)param.get("prcFlag");
	}
	@Override
	public Integer updateCustomerInfo(CustomerVO customerVO) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Map<String, Object> param = XsbBusinessUtil.initCustomerParam(customerVO);
		customerMapper.addCustomer(param);
		return (Integer)param.get("prcFlag");
	}
	
	@Override
	public Integer updatePassword(CustomerVO customerVO) throws Exception {
		// TODO Auto-generated method stub
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Map<String, Object> param = XsbBusinessUtil.initCustomerParam(customerVO);
		customerMapper.addCustomer(param);
		return (Integer)param.get("prcFlag");
	}

	@Override
	public String getCustomerIdByPhoneOrMail(String customerLabel)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("customerLabel", customerLabel);
		param.put("customerId", null);
		customerMapper.getCustomerIdByPhoneOrMail(param);
		return (String)param.get("customerId");
	}

	@Override
	public List<String> getRoleEnumByCustomerId(String customerId)
			throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("customerId", customerId);
		param.put("prcFlag", null);
		List<String> list = customerMapper.getRoleEnumByCustomerId(param);
		return list;
	}

	@Override
	public List<CustomerVO> getCustomerListByIds(CustomerVO customerVO) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return customerMapper.getCustomerListByIds(customerVO);
	}


}
