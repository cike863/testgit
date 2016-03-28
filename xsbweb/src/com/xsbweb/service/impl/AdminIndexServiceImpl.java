package com.xsbweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xsbweb.common.bean.DataBaseConstant;
import com.xsbweb.mapper.MenuMapper;
import com.xsbweb.service.AdminIndexService;
import com.xsbweb.util.MultipleDataSource;
import com.xsbweb.vo.Menu;
import com.xsbweb.vo.extend.*;

public class AdminIndexServiceImpl implements AdminIndexService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public List<Menu> getMenuListByMenuIds(MenuVO menuVO) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return menuMapper.getMenuListByMenuIds(menuVO);
	}

	@Override
	public Menu getMenuByMenuId(String menuID) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return menuMapper.getMenuByMenuId(menuID);
	}

	@Override
	public int updateMenuByMenuId(Menu menu) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return menuMapper.updateMenuByMenuId(menu);
	}

	@Override
	public int deleteMenuByMenuId(String menuID) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return menuMapper.deleteMenuByMenuId(menuID);
	}

	@Override
	public int addMenu(Menu menu) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return menuMapper.addMenu(menu);
	}

	@Override
	public List<Menu> getAllMenuList() throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return menuMapper.getAllMenuList();
	}

	@Override
	public List<Menu> getMenuByMenuName(String itemText) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return menuMapper.getMenuByMenuName(itemText);
	}

	@Override
	public List<Menu> getSubMenuByParentId(String itemParent) throws Exception {
		MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
		return menuMapper.getSubMenuByParentId(itemParent);
	}

}
