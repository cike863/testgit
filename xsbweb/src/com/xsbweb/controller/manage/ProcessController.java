package com.xsbweb.controller.manage;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.controller.app.AppLoginRegistController;
import com.xsbweb.service.TrsProjectService;
import com.xsbweb.vo.ProjectItem;

@Controller
public class ProcessController {

	private Logger log = Logger.getLogger(ProcessController.class);
	
	@Autowired
	private TrsProjectService trsProjectService;
	
	/**
	 * 提供item html页面给etl,用来加工item数据，方便终端使用
	 * @param projectNo
	 * @param itemKey
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/process/roadItems",method=RequestMethod.GET)
	 public ModelAndView roadItems(
			 @RequestParam("projectNo") String projectNo,
			 @RequestParam(value="itemKey",required=false) String itemKey
			 ) throws Exception {
		 	ModelAndView mav=new ModelAndView("process/roadItems");
		 	log.info("###########itemKey："+itemKey);
			try{
				//获取项目详情
				List<ProjectItem> projectItemList = null;
				ProjectItem projectItem = new ProjectItem();
				projectItem.setObjectNo(projectNo);
				projectItem.setItemKey(itemKey);
				projectItemList = trsProjectService.getProjectItemListByNo(projectItem);
				mav.addObject("itemList",projectItemList);
			}catch(Exception e){
				e.printStackTrace();
			}
			return mav;
	 }
}
