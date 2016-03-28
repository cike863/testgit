package com.xsbweb.controller.app;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xsbweb.common.bean.ResultCode;
import com.xsbweb.common.bean.XsbBusinessConstant;
import com.xsbweb.service.UploadFilesService;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.util.ConfigUtil;
import com.xsbweb.util.UploadUtil;
import com.xsbweb.vo.TrsMedia;
import com.xsbweb.vo.extend.CustomerVO;

@Controller
public class AppUploadFileController {

	private Logger log = Logger.getLogger(AppCustomerController.class);
	
	@Autowired
	private UploadFilesService uploadFilesService;
	
	/**
	 * 语音文件上传接口
	 * @param mediaName
	 * @param mediaLocation
	 * @param mediaType
	 * @param mediaSize
	 * @param tmpToken
	 * @param loginMethod
	 * @param customerPhoneNo
	 * @return
	 */
	@RequestMapping(value="/app/upload/voice",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> upload(
			HttpServletRequest request,
			@RequestParam(value="mediaName")String mediaName,
			@RequestParam(value="mediaType")String mediaType,
			@RequestParam(value="tmpToken",required=false)String tmpToken,
			@RequestParam(value="loginMethod",required=false)String loginMethod,
			@RequestParam(value="customerPhoneNo",required=false)String customerPhoneNo
			) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(CommonUtils.isBlank(mediaName)||CommonUtils.isBlank(mediaType)
				//||CommonUtils.isBlank(loginMethod)||
				//CommonUtils.isBlank(tmpToken)||CommonUtils.isBlank(customerPhoneNo)
				){
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			log.info("############"+customerPhoneNo+"  请求参数有误");
			return map;
		}
		String newFileName=null;
	    String returnPath=null;
	    //定义允许上传的文件扩展名
	    Map<String, String> extMap = new HashMap<String, String>();
	    extMap.put("image", "gif,jpg,jpeg,png,bmp");
	    extMap.put("flash", "swf,flv");
	    extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
	    extMap.put("voice", "aac,3gp,amr,swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
	    extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
	    //最大文件大小
	    long maxSize =Long.valueOf(ConfigUtil.configMap.get("file_max"))*1024*1024;
	    try {    
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
			Map<String, MultipartFile>  multMap =  multipartRequest.getFileMap();
			if(multMap == null){
				map.put(ResultCode.RESULT_CODE, ResultCode.UPLOAD_ERROE_NULL);
				return map;
			}
			MultipartFile multFile = multMap.get("file");
			//文件名
			String fileName = multFile.getOriginalFilename();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			//大小
			Long mediaSize = multFile.getSize();
			//超过最大文件大小
			if(mediaSize>maxSize){
				map.put(ResultCode.RESULT_CODE, ResultCode.UPLOAD_ERROE_MAX);
				return map;
			}
	        //扩展名
	        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	        if(!Arrays.<String>asList(extMap.get(mediaType).split(",")).contains(fileExt)){
	        	//文件扩展名有不符合
	        	map.put(ResultCode.RESULT_CODE, ResultCode.UPLOAD_ERROE_FILEEXT);
	        	return map;
			}
	        //新文件名
			newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "."+fileExt;
			//图片上传到ftp服务器
			returnPath=UploadUtil.FTPupload(multFile.getInputStream(),newFileName,"voice",false);
			if(CommonUtils.isNotBlank(returnPath)){
				map.put("returnPath", XsbBusinessConstant.DYLY_URL+returnPath);
			}else{
				map.put("returnPath", "");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
			return map;
		}finally{
			//用来记录log日志
			//request.setAttribute(ResultCode.RESULT_OBJECT, map);
		}
		return map;
	}
}
