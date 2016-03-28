package com.xsbweb.service;

import java.util.List;

import com.xsbweb.common.bean.BasePo;
import com.xsbweb.vo.Customer;
import com.xsbweb.vo.RObject;
import com.xsbweb.vo.TrsComment;
import com.xsbweb.vo.extend.CustomerVO;
import com.xsbweb.vo.extend.EnumVO;

public interface CustomerService {
    public CustomerVO getCustomerById(String customerId,List<EnumVO> gzIndustryEnumList,List<EnumVO> capitalScaleEnumList,List<EnumVO> customerTypeEnumList)throws Exception;
	
	public List<Customer> getAllCustomerList(Customer user)throws Exception;
	/**
	 * 
	 * 通过留言信息list以及用户信息（搜索时用）获取用户信息
	 * @param comments
	 * @param customerVO
	 * @return
	 * @throws Exception
	 */
	public List<Customer> getCustomerListByIds(List<TrsComment> comments,CustomerVO customerVO)throws Exception;
	/**
	 * 通过留言信息list以及用户信息（搜索时用）获取用户信息数量
	 * @param comments
	 * @param customerVO
	 * @return
	 * @throws Exception
	 */
	public int  getCustomerCountByIds(List<TrsComment> comments,CustomerVO customerVO)throws Exception;
	
	public int addCustomer(CustomerVO user)throws Exception;
	
	public int editCustomer(CustomerVO customer)throws Exception;
	
	public int deleteCustomer(Customer user)throws Exception;
	
	
	public int delCustomer(String customerId)throws Exception;
	
	/**
	 * 获取用户总数
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public int getCustomerCounts(Customer user)throws Exception;
	/**
	 * excel导入项目报名用户信息
	 * @param customerList
	 * @return
	 * @throws Exception
	 */
	public int projectRegistrationByExcel(List<CustomerVO> customerList,String objectNo)throws Exception;
	/**
	 * 根据项目编号获取报名用户信息
	 * @param projectNo
	 * @return
	 * @throws Exception 
	 */
	public List<Customer> getCustomerByProjectNo(String projectNo) throws Exception;
}
