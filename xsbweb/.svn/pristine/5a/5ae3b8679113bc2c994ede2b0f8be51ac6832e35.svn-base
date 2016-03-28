package com.xsbweb.mapper;

import com.xsbweb.vo.Customer;
import com.xsbweb.vo.extend.*;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {

	/**
	 * 用户登录判断
	 * @return 用户ID
	 * @throws Exception
	 */
	public void validateLogin(Map<String, Object> param) throws Exception;
	
	/**
	 * 用来校验注册信息，手机号、邮箱是否已存在
	 * @return 用户ID
	 * @throws Exception
	 */
	public Integer validateRegister(Map<String, Object> param) throws Exception;

	/**
	 * 根据用户名ID获取用户信息
	 * @return
	 * @throws Exception
	 */
	public List<CustomerVO> getCustomerById(String customerId) throws Exception;
	
	/**
	 * 根据用户信息获取权限集合
	 */
	public List<String> getRoleEnumByCustomerId(Map<String, Object> param) throws Exception; 
	
	/**
	 * 新增注册用户信息
	 * @return
	 * @throws Exception
	 */
	public CustomerVO addCustomer(Map<String, Object> param)throws Exception;
	/**
	 * 新增注册用户信息
	 * @return
	 * @throws Exception
	 */
	public CustomerVO addCustomer(Customer customer)throws Exception;
	
	/**
	 * 修改用户信息
	 * @return
	 * @throws Exception
	 */
	public Integer editCustomer(Customer customer)throws Exception;
	
	/**
	 * 删除用户信息（软删除，status为0）
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	public Integer deleteCustomer(Customer customer)throws Exception;
	
	
	public Integer delCustomer(String customerId) throws Exception;
	//public List  getCustomerRoleById(customerId)throws Exception;
	
	/**
	 * 根据用户电话号码或邮箱获取用户ID
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String getCustomerIdByPhoneOrMail(Map<String, Object> param)throws Exception;
	
	public String getCustomerIdByPhoneOrMail(Customer customer)throws Exception;
	
	public int insertCustomer(CustomerVO customerVO)throws Exception;
	
	public List<CustomerVO> getCustomerListByIds(CustomerVO customerVO)throws Exception;
	
	public List<Customer> getCustomerListBycustomerIds(CustomerVO customerVO)throws Exception;
	
	public int getCustomerCountBycustomerIds(CustomerVO customerVO)throws Exception;
	
	public List<Customer> getAllCustomerList(Customer customer)throws Exception;
	
	public int getCustomerCounts(Customer customer)throws Exception;
	
	public int batchUpdateByCustomerPhones(List<Customer> customerList)throws Exception;
}