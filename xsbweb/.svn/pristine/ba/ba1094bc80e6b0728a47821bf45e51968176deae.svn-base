package com.xsbweb.controller.manage;

import java.net.URLDecoder;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.common.bean.DataBaseConstant;
import com.xsbweb.service.AdminIndexService;
import com.xsbweb.service.EnumService;
import com.xsbweb.service.XSBBaseService;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.util.MultipleDataSource;
import com.xsbweb.util.RedisUtil;
import com.xsbweb.vo.Menu;
import com.xsbweb.vo.ProjectItem;
import com.xsbweb.vo.TrsProject;
import com.xsbweb.vo.extend.EnumVO;
import com.xsbweb.vo.extend.MenuVO;

@Controller
public class SystemManageCotroller {

	private Logger log = Logger.getLogger(SystemManageCotroller.class);
    @Autowired
	private XSBBaseService baseService;  
    @Autowired
   	private AdminIndexService adminIndexService;                                                     
    @Autowired
	private EnumService enumService;
    
    private RedisUtil redisUtil = new RedisUtil();
    
    /**
     * 栏目页面
     * @param menuVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="admin/menu/toAdminMenu.do")
    public ModelAndView toAdminMenu(MenuVO menuVO)throws Exception{
    	ModelAndView mav = new ModelAndView();
    	List<Menu> menuList = adminIndexService.getAllMenuList();
    	mav.setViewName("manage/menu/queryMenuList");
    	menuVO.setMenuList(menuList);
    	mav.addObject("menuVO",menuVO);
    	return mav;
    }
    /**
     * 查询栏目
     * @param menuVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="admin/menu/queryMenuList.do")
    public ModelAndView queryMenuList(String itemText)throws Exception{
    	itemText = URLDecoder.decode(itemText,"utf-8");
    	ModelAndView mav = new ModelAndView();
    	MenuVO menuVO = new MenuVO();
    	List<Menu> menuList = adminIndexService.getMenuByMenuName(itemText);
    	mav.setViewName("manage/menu/queryMenuList");
    	menuVO.setMenuList(menuList);
    	mav.addObject("menuVO",menuVO);
    	return mav;
    }
    /**
     * 跳转到新增栏目页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value="admin/menu/toAddMenu.do")
    public String toAddMenu()throws Exception{
    	return "manage/menu/addMenu";
    }
    /**
     * 新增栏目
     * @param menu
     * @return
     * @throws Exception
     */
    @RequestMapping(value="admin/menu/addMenu.do")
    public String addMenu(Menu menu)throws Exception{
    	//获取当前时间，精确到毫秒
    	String nowDate = CommonUtils.getNowDateStringOf17();
    	if(menu==null || CommonUtils.isBlank(menu.getItemId())){
    		return null;
    	}
    	MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);	
    	adminIndexService.addMenu(menu);
    	MultipleDataSource.clearDataSourceType();
    	MultipleDataSource.setDataSourceKey(DataBaseConstant.META);	
    	enumService.addEnumByMenu(menu);
    	return "redirect:/admin/menu/toAdminMenu.do";
    }
    
    /**
     * 修改栏目
     * @param menuID
     * @return
     * @throws Exception
     */
    @RequestMapping(value="admin/menu/toEditMenu.do")
    public ModelAndView toEditMenu(String itemId)throws Exception{
    	ModelAndView mav = new ModelAndView();
    	Menu menu = adminIndexService.getMenuByMenuId(itemId);
    	mav.setViewName("manage/menu/editMenu");
    	mav.addObject("menu",menu);
    	return mav;
    }
    
    //编辑数据
    @RequestMapping(value="admin/menu/editMenu.do",method=RequestMethod.POST)
    public String editMenu(Menu menu)throws Exception{
      	adminIndexService.updateMenuByMenuId(menu);
      	MultipleDataSource.setDataSourceKey(DataBaseConstant.META);	
      	enumService.updateEnumByMenu(menu);
    	return "redirect:/admin/menu/toAdminMenu.do";
    }
    
    /**
     * 删除栏目
     * @param menu
     * @return
     * @throws Exception
     */
    @RequestMapping(value="admin/menu/deleteMenuByItemId.do")
    public ModelAndView deleteMenuByItemId(String itemId)throws Exception{
    	ModelAndView mav = new ModelAndView();
    	int flag = adminIndexService.deleteMenuByMenuId(itemId);
    	if(flag>0){
    		List<Menu> menuList = adminIndexService.getAllMenuList();
    		MenuVO menuVO = new MenuVO();
        	menuVO.setMenuList(menuList);
        	mav.addObject("menuVO",menuVO);
    	}else{
    		mav.addObject("error","删除失败");
    	}
    	mav.setViewName("manage/menu/queryMenuList");
    	return mav;
    }
    
    /**
     * 删除栏目
     * @param menu
     * @return
     * @throws Exception
     */
    @RequestMapping(value="admin/menu/isHaveSubMenu.do")
    public @ResponseBody boolean isHaveSubMenu(String itemId)throws Exception{
    	boolean flag = false;
    	List<Menu> menuList = adminIndexService.getSubMenuByParentId(itemId);
    	if(menuList!=null && menuList.size()>0){
    		flag = true;
    	}
    	return flag;
    }
    
    
    /**
     * 数据字典页面
     * @param menuVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="admin/enum/toAdminEnum.do")
    public ModelAndView toAdminEnum(EnumVO enumVO)throws Exception{
    	ModelAndView mav = new ModelAndView();
    		
    	//EnumVO enums = ;
    	return mav;
    }
    
    /**
     * 	//获取记录
	@RequestMapping(value="/project/{projectNo}",method=RequestMethod.GET)
	public ModelAndView getProjectListByProjectNo(@PathVariable("projectNo") String projectNo) throws Exception{
		ModelAndView mav = new ModelAndView("manage/project/editForeProject");
		TrsProject trsProject = trsProjectService.getProjectListByProjectNo(projectNo);
		mav.addObject("trsProject", trsProject);
		List<ProjectItem> objectList =trsProjectService.getObjectListByObjectNo(projectNo);
		mav.addObject("objectList", objectList);
		return mav;
	}
     */
    
    
    
    /**
     * 跳转到新增数据字典页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value="admin/enum/toAddEnum.do")
    public String toAddEnum()throws Exception{
    	return "manage/enum/addMenu";
    }
    /**
     * 新增数据字典
     * @param menu
     * @return
     * @throws Exception
     */
    @RequestMapping(value="admin/enum/addMenu.do")
    public String addMenu(EnumVO enumVO)throws Exception{
    	//获取当前时间，精确到毫秒
    	String nowDate = CommonUtils.getNowDateStringOf17();
    	if(enumVO==null){
    		return null;
    	}
    	
    	return "redirect:/admin/enum/toAdminEnum.do";
    }
    
    /**
     * 跳转到修改数据字典页面
     * @param menuID
     * @return
     * @throws Exception
     */
    @RequestMapping(value="admin/enum/toEditMenu.do")
    public ModelAndView toEditEnum(String menuID)throws Exception{
    	ModelAndView mav = new ModelAndView();
    	
    	//Menu menu = adminIndexService.getMenuByMenuId(menuID);
    	
    	mav.setViewName("manage/enum/editEnum");
    	
    	//mav.addObject("menu",menu);
    	return mav;
    }
    
    /**
     * 修改数据字典
     * @param enumVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="admin/enum/editMenu.do",method=RequestMethod.POST)
    public String editMenu(EnumVO enumVO)throws Exception{
    	
    	return "redirect:/admin/enum/toAdminEnum.do";
    }
    
    /**
     * 删除数据字典
     * @param menu
     * @return
     * @throws Exception
     */
    @RequestMapping(value="admin/enum/deleteMenuByItemId.do")
    public ModelAndView deleteEnum(String itemId)throws Exception{
    	ModelAndView mav = new ModelAndView();
    	
    	
    	mav.setViewName("manage/enum/queryEnumList");
    	return mav;
    }
}
