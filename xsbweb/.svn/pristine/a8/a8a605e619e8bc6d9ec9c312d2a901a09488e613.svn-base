package com.xsbweb.service;

import java.util.List;
import java.util.Map;

import com.xsbweb.vo.Customer;
import com.xsbweb.vo.Staff;
import com.xsbweb.vo.extend.*;

public interface LoginRegisterService {

	/**
	 * 用来校验登录信息
	 * @return 用户ID
	 * @throws Exception
	 */
	public CustomerVO validateLogin(CustomerVO customerVO)throws Exception;
	
	/**
	 * 根据用户名ID获取用户信息
	 * @return
	 * @throws Exception
	 */
	public CustomerVO getCustomerById(String customerId)throws Exception;
	
	/**
	 * 用来校验注册信息
	 * @return 用户ID
	 * @throws Exception
	 */
	public Integer validateRegister(CustomerVO customerVO)throws Exception;
	
	/**
	 * 注册
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	public Integer register(CustomerVO customerVO)throws Exception;
	
	/**
	 * 修改密码
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	public Integer updatePassword(CustomerVO customerVO)throws Exception;
	
	/**
	 * 根据用户电话号码或邮箱获取用户ID
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String getCustomerIdByPhoneOrMail(String customerLabel)throws Exception;
	
	/**
	 * 根据用户信息获取权限集合
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<String> getRoleEnumByCustomerId(String customerId) throws Exception; 
	
	public List<CustomerVO> getCustomerListByIds(CustomerVO customerVO)throws Exception;

	Integer updateCustomerInfo(CustomerVO customerVO) throws Exception;
}
