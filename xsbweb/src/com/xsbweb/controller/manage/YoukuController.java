package com.xsbweb.controller.manage;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.util.YoukuUtil;
import com.xsbweb.vo.TrsProject;

@Controller
@RequestMapping(value="/admin/youku")
public class YoukuController {

	/**
	 * 跳转到优酷视频上传页面
	 * @param trsProject
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/upload",method=RequestMethod.GET)
	public ModelAndView toYoukuUpload(TrsProject trsProject) throws Exception{
		ModelAndView mav = new ModelAndView("manage/youku/upload");
		Map<String,Object> map = YoukuUtil.login();
		return mav;
	}
}
