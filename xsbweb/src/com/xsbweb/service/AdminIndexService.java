package com.xsbweb.service;

import java.util.List;

import com.xsbweb.vo.Menu;
import com.xsbweb.vo.extend.*;

public interface AdminIndexService {

	public List<Menu> getMenuListByMenuIds(MenuVO menuVO)throws Exception;
	
	public Menu getMenuByMenuId(String menuID)throws Exception;
	
	public List<Menu> getAllMenuList()throws Exception;
	
	public int updateMenuByMenuId(Menu menu)throws Exception;
	
	public int deleteMenuByMenuId(String menuID)throws Exception;
	
	public int addMenu(Menu menu)throws Exception;
	
	public List<Menu> getMenuByMenuName(String itemText)throws Exception;
	/**
	 * 根据栏目ID获取子栏目
	 * @param itemParent
	 * @return
	 * @throws Exception
	 */
	public List<Menu> getSubMenuByParentId(String itemParent)throws Exception;
}
