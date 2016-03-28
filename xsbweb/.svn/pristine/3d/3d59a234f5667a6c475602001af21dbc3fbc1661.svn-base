package com.xsbweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xsbweb.common.bean.DataBaseConstant;
import com.xsbweb.mapper.DepartmentMapper;
import com.xsbweb.mapper.StaffMapper;
import com.xsbweb.service.StaffService;
import com.xsbweb.util.MultipleDataSource;
import com.xsbweb.vo.Department;
import com.xsbweb.vo.Staff;
import com.xsbweb.vo.extend.EnumVO;

public class StaffServiceImpl implements StaffService {
	private Logger log = Logger.getLogger(StaffServiceImpl.class);
	@Autowired
	private StaffMapper staffMapper;
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Override
	public List<Staff> getAllStaffList(Staff staff) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return staffMapper.getAllStaffList(staff);
	}

	/**
	 * 获取用户总数
	 * @param news
	 * @return
	 * @throws Exception
	 */
	public int getStaffCounts(Staff staff) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return staffMapper.getStaffCounts(staff);
	}
	
	@Override
	public Staff getStaffListById(String id) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Staff staffList =  staffMapper.getStaffById(id);
		return staffList;
	}
	
	
	@Override
	public int delStaff(String id){
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		int flag;
		try {
			int a = staffMapper.delStaff(id);
			//int b = customerMapper.deleteNewsIdx(newsNo);
			flag = a;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = -1;
		}
		return flag;
	}
	
	/**
	 * 用来校验登录信息
	 * @return 用户ID
	 * @throws Exception
	 */
	@Override
	public Staff validateStaffLogin(Staff staff) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loginName", staff.getLoginName());
		param.put("staffPassword", staff.getStaffPassword());
		return staffMapper.validateLogin(param);

	}
	/**
	 * 判断用户是否存在
	 * @throws Exception 
	 */
	@Override
	public Staff checkStaff(Staff staff) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loginName", staff.getLoginName());
		return staffMapper.checkStaff(param);
	}

	@Override
	public Staff checkRegisteredByPhoneNo(String phoneNo) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return staffMapper.checkRegisteredByPhoneNo(phoneNo);
	}

	@Override
	public void updatePassWord(Staff staff) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		staffMapper.updatePassword(staff);
	}

	@Override
	public boolean selectStaffByPhoneNoOrEmail(Staff staff) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Staff staffs = staffMapper.selectStaffByPhoneNoOrEmail(staff);
		if(staffs==null){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public void addStaff(Staff staff) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		staffMapper.addStaff(staff);
	}

	@Override
	public int updateStaff(Staff staff){
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		int flag = 0;
		try {
			flag = staffMapper.editStaff(staff);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = -1;
		}
		return flag;
	}

	/**
	 * 获取后台用户权限
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<String> getRoleByStaffId(String staffId){
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		Map<String,Object> param = new HashMap<String,Object>(); 
		param.put("staffId", staffId);
		param.put("prcFlag", null);
		List<String> codeList = new ArrayList<String>();
		try {
			List<EnumVO> menuList = staffMapper.getStaffRoleByPrc(param);
			if(menuList!=null && !menuList.isEmpty()){
				for (EnumVO enumVO : menuList) {
					codeList.add(enumVO.getEnumDesc());
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			codeList = null;
		}
		return codeList;
	}

	@Override
	public List<Department> getDepartment() throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return departmentMapper.getDepartmentList();
	}

	@Override
	public List<Staff> getStaffByDeptName(Staff staff) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return staffMapper.getStaffByDeptName(staff);
	}
	
}
