package com.xsbweb.controller.manage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.common.bean.XsbBusinessConstant;
import com.xsbweb.common.serialize.ListTranscoder;
import com.xsbweb.service.AdminIndexService;
import com.xsbweb.service.EnumService;
import com.xsbweb.service.LoginRegisterService;
import com.xsbweb.service.StaffService;
import com.xsbweb.service.TrsProjectService;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.util.RedisUtil;
import com.xsbweb.vo.extend.*;
import com.xsbweb.vo.Menu;
import com.xsbweb.vo.ProjectItem;

@Controller
public class AdminIndexController {

	@Autowired
	private AdminIndexService adminIndexService;
	
	@Autowired 
	private LoginRegisterService loginRegisterService;
	
	@Autowired 
	private EnumService enumService;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private TrsProjectService trsProjectService;
	
	private static Logger log = Logger.getLogger(CommonUtils.class);

    private static RedisUtil redisUtil = new RedisUtil();
	  
	/**
	 * 栏目管理机制:每次根据权限查出根目录
	 * @param mode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/manage/toAdminIndex.do")
	public String toAdminIndex(String customerId,String customerName,ModelMap mode) throws Exception{
		//request.setCharacterEncoding("UTF-8");
		//String customerName = (String) mode.get("customerName");
		//String customerName=(String) request.getAttribute("customerName");
		
		//获取栏目
		MenuVO menuVO = new MenuVO();
		if(CommonUtils.isBlank(customerId)){
			return null;
		}
		
		//List<Menu> menuList = redisUtil.getToListJedis("staff:"+customerId+":role");
		List<Menu> menuList=null;
		//如果redis中没该数据，则从数据库中查询出来，然后再存入redis中
		if(menuList==null || menuList.isEmpty()){
			//roleEnumList = loginRegisterService.getRoleEnumByCustomerId(customerId);
			//获取后台用户的权限
			List<String> roleEnumList = staffService.getRoleByStaffId(customerId);
			
			if(roleEnumList == null || roleEnumList.isEmpty()){
				mode.put("error", "用户没有配置权限，请联系管理员配置权限！User without permission, please contact your administrator configure permissions!");
				return "error";
			}
			String[] menuIds = roleEnumList.toArray(new String[roleEnumList.size()]);
			menuVO.setMenuIds(menuIds);
			menuList = adminIndexService.getMenuListByMenuIds(menuVO);
			//menuList = adminIndexService.getAllMenuList();
			//根据用户拥有的栏目信息存入redis
			//redisUtil.addToListJedis("staff:"+customerId+":role", menuList);
		}
		
		//考虑后台使用人数不多，先不采用redis服务器
		//List<String> roleEnumList = staffService.getRoleByStaffId(customerId);
		
		//测试专用-----------------------------
		//List<Menu> menuList = adminIndexService.getAllMenuList();
		//-----------------------------------
		
		//根据用户拥有的栏目信息存入redis
		//redisUtil.addToListJedis("staff:"+customerId+":role", menuList);
		//-------------------------------------
		//第一次进入栏目只取一级栏目，异步加载子栏目
		List<Menu> listOne = new ArrayList<Menu>();
		if(menuList!=null){
			menuVO.setMenuList(menuList);
			for (Menu menu : menuList) {
				//id长度只有2说明是根目录
				if(menu.getItemId().length()==2){
					listOne.add(menu);
				}
			}
		}
		mode.put("menuList", listOne);
		mode.put("customerId", customerId);
		mode.put("customerName", customerName);
		return "manage/adminIndex";
	}
	
	/**
	 * 获取子栏目
	 * @param itemId
	 * @param customerId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/admin/menu/getSubmenuList.do",method=RequestMethod.GET)
	public @ResponseBody List<Menu> getSubmenuList(
			@RequestParam("itemId") String itemId,
			@RequestParam("customerId") String customerId
			) throws Exception{
		if(CommonUtils.isBlank(itemId) || CommonUtils.isBlank(customerId)){
			return null;
		}
		//获取redis中该用户的栏目集合
		//List<Menu> menuList = redisUtil.getToListJedis("staff:"+customerId+":role");
		//获取后台用户的权限
		List<String> roleEnumList = staffService.getRoleByStaffId(customerId);
		
		String[] menuIds = roleEnumList.toArray(new String[roleEnumList.size()]);
		MenuVO menuVO = new MenuVO();
		menuVO.setMenuIds(menuIds);
		List<Menu> menuList = adminIndexService.getMenuListByMenuIds(menuVO);
		if(menuList==null){
			//这个地方可以再查一次数据库，确认（以防止redis中数据丢失）
			//-----------------------------
			log.info("############staff:"+customerId+":role拥有栏目数量：null");
		}else{
			log.info("############staff:"+customerId+":role拥有栏目数量："+menuList.size());
		}
		if(menuList==null || CommonUtils.isBlank(itemId)){
			return null;
		}
		List<Menu> subMenuList = new ArrayList<Menu>();
		for (Menu menu : menuList) {
			if(itemId.equals(menu.getItemParent())){
				subMenuList.add(menu);
			}
		}
		return subMenuList;
	}
	
	
	@RequestMapping(value="/item/toAddItems")
	/*public ModelAndView deleteVideoMeetRoomByMeetNo(String itemKey,String itemLocationOrder)throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.addObject("itemKey", itemKey);
		mav.addObject("itemLocationOrder", itemLocationOrder);
		mav.setViewName("manage/itemEditor");
		return mav;
	}*/
	public ModelAndView deleteVideoMeetRoomByMeetNo(ProjectItem item)throws Exception{
		ModelAndView mav = new ModelAndView();
		//新建判断ItemValue 为null或者""
		if(item!=null&&!"".equals(item.getObjectNo())&&item.getItemValue()!=null&&!"".equals(item.getItemValue())){
			List<ProjectItem> items = trsProjectService.getProjectItemListByNo(item);
			if(items!=null&&!items.isEmpty()){
				item=items.get(0);
				item.setOldItemKey(item.getItemKey());
				log.info("item.getItemValue() 替换前"+item.getItemValue());
				if(item.getItemValue()!=null && item.getItemValue().contains("/dyly/")){
					item.setItemValue(item.getItemValue().replace("/dyly/", XsbBusinessConstant.DYLY_URL+"/"));
				}
				if(item.getItemValue()!=null && item.getItemValue().contains("/Files/")){
					item.setItemValue(item.getItemValue().replace("/Files/", XsbBusinessConstant.DYLY_URL+"/Files/"));
				}
				log.info("item.getItemValue() 替换后"+item.getItemValue());
			}
		}else{
			item.setOldItemKey(null);
		}
		/*mav.addObject("itemKey", itemKey);
		mav.addObject("itemLocationOrder", itemLocationOrder);*/
		mav.addObject("item", item);
		mav.setViewName("manage/itemEditor");
		return mav;
	}
}
