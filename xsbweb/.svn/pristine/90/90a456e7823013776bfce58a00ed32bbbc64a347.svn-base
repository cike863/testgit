package com.xsbweb.controller.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.common.bean.ResultCode;
import com.xsbweb.service.ConfigService;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.vo.TrsMedia;

@Controller
@RequestMapping(value="/admin/media")
public class TrsMediaController {

	@Resource
	private ConfigService configService;
	
	/**
	 * 查询列表
	 * @param TrsMediaVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView  getTrsMediaList(TrsMedia trsMedia)throws Exception{
		ModelAndView mav= new ModelAndView("manage/media/queryTrsMediaList");
		int count = configService.getTrsMediaCounts(trsMedia);
		trsMedia.setTotalRecord(count);
		List<TrsMedia> mediaList = null;
		if(count>0){
			mediaList = configService.getTrsMediaList(trsMedia);
		}
		mav.addObject("mediaList",mediaList);
		mav.addObject("trsMedia",trsMedia);
		return mav;
	}
	
	/**
	 * 新增
	 * @param TrsMediaVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView addTrsMedia(TrsMedia trsMedia)throws Exception{
		ModelAndView mav= new ModelAndView();
		String mediaNo ="";
		if("youku".equals(trsMedia.getMediaFormat())){
			String no = CommonUtils.getNowDateStringOf14()+new Random().nextInt(99);
			trsMedia.setMediaNo(no);
			mediaNo = configService.addTrsMedia(trsMedia);
		}else{
			configService.updateTrsMedia(trsMedia);
			mediaNo=trsMedia.getMediaNo();
		}
		if(CommonUtils.isBlank(mediaNo)){
			mav.addObject("trsMedia", trsMedia);
			mav.setViewName("manage/media/addMedia");
			return mav;
		}else{
			mav.setViewName("redirect:/admin/media");
			/*mav.setViewName("manage/media/queryTrsMediaList");
			int count = configService.getTrsMediaCounts(new TrsMedia());
			List<TrsMedia> mediaList = null;
			if(count>0){
				mediaList = configService.getTrsMediaList(new TrsMedia());
			}
			mav.addObject("mediaList",mediaList);
			mav.addObject("trsMedia",trsMedia);*/
			return mav;
		}
	}
	
	/**
	 * 编辑
	 * @param TrsMediaVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{mediaNo}",method=RequestMethod.PUT)
	public ModelAndView editTrsMedia(
			@PathVariable(value="mediaNo") String mediaNo,
			TrsMedia trsMediaVO)throws Exception{
		ModelAndView mav= new ModelAndView();
		int flag = configService.updateTrsMedia(trsMediaVO);
		if(CommonUtils.isBlank(mediaNo)){
			mav.addObject("trsMedia", trsMediaVO);
			mav.setViewName("redirect:/admin/media/toEditMedia?mediaNo="+mediaNo );
			return mav;
		}else{
			mav.setViewName("redirect:/admin/media");
			/*mav.setViewName("manage/media/queryTrsMediaList");
			int count = configService.getTrsMediaCounts(new TrsMedia());
			List<TrsMedia> mediaList = null;
			if(count>0){
				mediaList = configService.getTrsMediaList(new TrsMedia());
			}
			mav.addObject("mediaList",mediaList);
			mav.addObject("trsMedia",new TrsMedia());*/
			return mav;
		}
	}
	
	/**
	 * 删除
	 * @param TrsMediaVO
	 * @param mediaNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delete/{mediaNo}",method=RequestMethod.POST)
	public @ResponseBody Integer deleteTrsMedia(TrsMedia trsMediaVO,@PathVariable("mediaNo") String mediaNo)throws Exception{
		TrsMedia pVO = new TrsMedia();
		pVO.setMediaNo(mediaNo);
		int flag = configService.deleteTrsMedia(pVO);
		//现在成功跳转到登录页面
		return flag;
	}
	
	/**
	 * 跳转到新增页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toAddMedia")
	public ModelAndView toAddTrsMedia() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.addObject("trsMediaVO",new TrsMedia());
		mav.setViewName("manage/media/addMedia");
		return mav;
	}
	
	/**
	 * 跳转到编辑页面
	 * @param taskTable
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toEditMedia")
	public ModelAndView toEditTrsMedia(@RequestParam("mediaNo") String mediaNo) throws Exception{
		ModelAndView mav = new ModelAndView();
		TrsMedia trsMedia = configService.getTrsMediaByMediaNo(mediaNo);
		mav.addObject("trsMedia",trsMedia);
		mav.setViewName("manage/media/editMedia");
		return mav;
	}
	/**
	 * 删除
	 * @param TrsMediaVO
	 * @param mediaNoArrs
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{mediaNoArrs}/del",method=RequestMethod.DELETE)
	public @ResponseBody Map<String,Object> batchDeleteTrsMedia(@PathVariable("mediaNoArrs") String[] mediaNoArrs)throws Exception{
		Map<String,Object>map = new HashMap<String,Object>();
		int flag = configService.batchDeleteTrsMedia(mediaNoArrs);
		if(flag>0){
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
		}else{
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		}
		return map;
	}
}
