package com.xsbweb.service;

import java.util.List;
import java.util.Map;

import com.xsbweb.vo.Department;
import com.xsbweb.vo.Staff;

public interface StaffService {
		
		public List<Staff> getAllStaffList(Staff staff)throws Exception;
		
		/**
		 * 获取用户总数
		 * @param 
		 * @return
		 * @throws Exception
		 */
		public int getStaffCounts(Staff staff)throws Exception;
		
		/**
		 * 用来校验员工登录信息
		 * @return 用户ID
		 * @throws Exception
		 */
		public Staff validateStaffLogin(Staff staffVO) throws Exception;
		/**
		 * 判断员工是否存在
		 * @param staffVO
		 * @return
		 * @throws Exception 
		 */
		public Staff checkStaff(Staff staffVO) throws Exception;
		/**
		 * 判断手机号是否被注册
		 * @param phoneNo
		 * @return
		 * @throws Exception
		 */
		public Staff checkRegisteredByPhoneNo(String phoneNo) throws Exception;
		/**
		 * 密码修改
		 * @param staff
		 * @throws Exception
		 */
		public void updatePassWord(Staff staff)throws Exception;
		/**
		 * 判断手机号或者邮箱是否被注册
		 * @return
		 * @throws Exception
		 */
		public boolean selectStaffByPhoneNoOrEmail(Staff staff) throws Exception;
		/**
		 * 新增用户
		 * @param staff
		 * @throws Exception
		 */
		public void addStaff(Staff staff)throws Exception;
		
		/**
		 * 修改用户信息
		 * @param staff
		 * @return
		 * @throws Exception
		 */
		public int updateStaff(Staff staff)throws Exception;
		
		public Staff getStaffListById(String id)throws Exception;
		
		/**
		 * 删除用户信息
		 * @param staff
		 * @return
		 * @throws Exception
		 */
		public int delStaff(String id)throws Exception;
		
		/**
		 * 获取后台用户权限
		 * @param param
		 * @return
		 * @throws Exception
		 */
		public List<String> getRoleByStaffId(String staffId)throws Exception;
		/**
		 * 获取部门信息
		 * @return
		 * @throws Exception
		 */
		public List<Department> getDepartment()throws Exception;
		/**
		 * 通过部门名获取后台用户
		 * @param staff
		 * @return
		 * @throws Exception
		 */
		public List<Staff> getStaffByDeptName(Staff staff)throws Exception;
}
