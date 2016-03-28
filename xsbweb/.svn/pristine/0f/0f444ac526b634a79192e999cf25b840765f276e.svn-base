package com.xsbweb.controller.manage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.common.bean.DataBaseConstant;
import com.xsbweb.common.bean.ResultCode;
import com.xsbweb.common.bean.UploadFilePo;
import com.xsbweb.common.bean.XsbBusinessConstant;
import com.xsbweb.common.bean.ZtConstant;
import com.xsbweb.controller.app.AppLoginRegistController;
import com.xsbweb.service.ConfigService;
import com.xsbweb.service.NewsService;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.util.MultipleDataSource;
import com.xsbweb.util.QiniuUploadUtil;
import com.xsbweb.util.RedisUtil;
import com.xsbweb.util.UploadUtil;
import com.xsbweb.vo.TrsMedia;

@Controller
public class UploadFilesController {

	private Logger log = Logger.getLogger(AppLoginRegistController.class);
    @Autowired
	private ConfigService configService;  
    @Autowired
    private NewsService newsService;
    
    private RedisUtil redisUtil = new RedisUtil();
    
    @RequestMapping(value="/admin/news/uploadLayer.do")
    /*public String toLogin(){
    	return "uploadfile/uploadLayer";
    }*/
    public ModelAndView toLogin(HttpServletRequest request){
    	String mediaNo = request.getParameter("mediaNo");
    	String dir = request.getParameter("dir");
    	ModelAndView  mav = new ModelAndView("uploadfile/uploadLayer");
    	mav.addObject("mediaNo", mediaNo);
    	mav.addObject("dir", dir);
    	return mav;
    }
    
    @RequestMapping(value="/admin/news/uploadJson.do")
    public @ResponseBody Map<String,Object> uploadJson(
    		HttpServletResponse response,
    		HttpServletRequest request)throws Exception{
    	Map<String,Object> map = new HashMap<String,Object>();
    String newFileName=null;
    String returnPath=null;
    //com.jspsmart.upload.SmartUpload su = new   com.jspsmart.upload.SmartUpload();
    /*su.initialize(pageContext);
    su.service(request, response);
    su.setTotalMaxFileSize(100000000);
    su.setAllowedFilesList("zip,rar");
    su.setDeniedFilesList("exe,bat,jsp,htm,html,,");
    su.upload();*/
   // 
    //String dirName = request.getParameter("dir");

    //定义允许上传的文件扩展名
    Map<String, String> extMap = new HashMap<String, String>();

    extMap.put("image", "gif,jpg,jpeg,png,bmp");
    extMap.put("flash", "swf,flv");
    extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
    extMap.put("file", "doc,docx,xls,xlsx,ppt,pptx,htm,html,txt,zip,rar,gz,bz2,pdf");

    //最大文件大小
    long maxSize =15*1024*1024;

    response.setContentType("text/html; charset=UTF-8");

    if(!ServletFileUpload.isMultipartContent(request)){
    	
    	return getError("请选择文件。");
    }
    String dirName=request.getParameter("dir");//dir取不到值改为dirName
    String mediaType=request.getParameter("mediaNo");
    if (dirName == null||"".equals(dirName)) {
    	dirName = "image";
    }

    /*FileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);
    upload.setHeaderEncoding("UTF-8");
    List items = upload.parseRequest(request);
    log.info("########items"+items+"################");
    Iterator itr = items.iterator();
    log.info("########itr"+itr+"################");
    String fileExt ="";
    log.info("########itr.hasNext()"+itr.hasNext()+"################");
    while (itr.hasNext()) {
    	FileItem item = (FileItem) itr.next();
    	String fileName = item.getName();
    	if (!item.isFormField()) {
    		//检查文件大小
    		if(item.getSize() > maxSize){
    			return getError("上传文件大小超过限制。");
    		}else{
    			maxSize=item.getSize();
    		}
    		//检查扩展名
    		fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    		if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
    			return getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
    		}

    		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    		newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
    		try{
    			returnPath=UploadUtil.FTPupload(item.getInputStream(),newFileName,dirName);//item.write(uploadedFile);
    		}catch(Exception e){
    			return getError("上传文件失败。");
    		}
    		}
    	}*/
    //long maxSize =Long.valueOf(ConfigUtil.configMap.get("file_max"))*1024*1024;
    String fileExt ;
    ResourceBundle bundle = ResourceBundle.getBundle("config");  
	String	hostName = bundle.getString("ftp_url_pro");  //IP地址
	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
	Map<String, MultipartFile>  multMap =  multipartRequest.getFileMap();
	Long mediaSize=(long) 0;
    try {    
		if(multMap == null){
			map.put(ResultCode.RESULT_CODE, ResultCode.UPLOAD_ERROE_NULL);
			return map;
		}
		MultipartFile multFile = multMap.get("filepath");
		//文件名
		//新闻图片上传时，是imgFile
		if(multFile==null){
			multFile = multMap.get("imgFile");
		}
		String fileName = multFile.getOriginalFilename();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		//大小
		mediaSize = multFile.getSize();
		//超过最大文件大小
		if(mediaSize>maxSize){
			map.put(ResultCode.RESULT_CODE, ResultCode.UPLOAD_ERROE_MAX);
			return map;
		}
        //扩展名
        fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
        	//文件扩展名有不符合
        	map.put(ResultCode.RESULT_CODE, ResultCode.UPLOAD_ERROE_FILEEXT);
        	return map;
		}
        //新文件名
		newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "."+fileExt;
		//图片上传到ftp服务器
		if(!ZtConstant.LEADERPROJECT_LEADERPIC.equals(mediaType)&&!ZtConstant.LEADERPROJECT_COMPANYPIC.equals(mediaType)){
			returnPath=UploadUtil.FTPupload(multFile.getInputStream(),newFileName,dirName,false);
			log.info("#################文件上传ftp成功#################");
			if("image".equals(dirName)){
				InputStream inputStream=UploadUtil.toSmallImage(multFile.getInputStream(),"."+fileExt,mediaSize);
				UploadUtil.FTPupload(inputStream,newFileName,dirName,true);
				log.info("#################手机小图上传ftp成功#################");
			}
		}
		log.info("#################################returnPath:"+returnPath);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		return map;
	}
    	
    	
    	if(mediaType!=null){
    		mediaType=mediaType.replaceAll(" ", "");
    	}
    	TrsMedia trsMedia = new TrsMedia();
    	//专题中，图片不存入media表
		if(!ZtConstant.LEADERPROJECT_LEADERPIC.equals(mediaType)&&!ZtConstant.LEADERPROJECT_COMPANYPIC.equals(mediaType)){
			trsMedia.setMediaSize(Double.parseDouble(Long.toString(maxSize/1024/1024)));//将字节转换为m
			trsMedia.setMediaFormat(fileExt);
			trsMedia.setMediaType(dirName);
			trsMedia.setMediaName(newFileName);
			trsMedia.setMediaLocation(returnPath);
			trsMedia.setMediaNo(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			MultipleDataSource.setDataSourceKey(DataBaseConstant.OLTP);
			String mediaNo = configService.addTrsMedia(trsMedia);
			if(CommonUtils.isBlank(mediaNo)){
				map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
				return map;
			}else{
				
			}
		}else{
		    File f = File.createTempFile("tmp","."+fileExt);
		    OutputStream os = new FileOutputStream(f);
		    InputStream is = multMap.get("filepath").getInputStream();
			log.info("File path: "+f.getAbsolutePath());
			byte [] by = new byte[1024];
			while( is.read(by)>-1){
				os.write(by);
				os.flush();
			}
			os.close();
			String realDir = f.getPath(); 
			Map<String,Object> qiniuMap = QiniuUploadUtil.upload(realDir, XsbBusinessConstant.QINIU_BUCKETNAME);
			int a = (int)qiniuMap.get(ResultCode.RESULT_CODE);
			String b = (String)qiniuMap.get("key");
			if(a==1){
				returnPath = XsbBusinessConstant.QINIU_URL+b;
			}
		}
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url", "http://"+hostName+":8080/dyly"+returnPath);
			obj.put("returnPath", returnPath);
			obj.put("newFileName",newFileName);
			obj.put("size", maxSize+"字节");
			obj.put("type",dirName);
			obj.put("format",fileExt);
			obj.put("mediaType", mediaType);
			obj.put("mediaNo", trsMedia.getMediaNo());
			return obj;
		//将对象放在session中
		/*if(mediaType!=null){
			request.getSession().setAttribute(mediaType, trsMedia);
		}	*/	
    }
    
    @RequestMapping(value="admin/news/fileManagerJson.do")
    public @ResponseBody String fileManagerJson(HttpServletResponse response,HttpServletRequest request) throws SocketException, IOException{
    	UploadFilePo uploadFilePo=UploadUtil.uploadFilePo();
    	
    	FTPClient ftpClient=uploadFilePo.getFtpClient();
    	
    	String path = request.getParameter("path") != null ? request.getParameter("path") : "/";
    	
    	String remoteDir = path;
    	remoteDir = "upfile";
    	//根目录路径，可以指定绝对路径，比如 /var/www/attached/
    	String rootPath = "ftp://"+uploadFilePo.getHostName()+":"+uploadFilePo.getHostPort()+"/"+remoteDir;
    	//根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
    	String rootUrl  = rootPath;
    	
    	 //定义允许上传的文件扩展名
        Map<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
        
    	String dirName = request.getParameter("dir");
    	if (dirName == null) {
    	    dirName = "image";
    	}
    	String currentUrl = rootUrl ;
    	String currentDirPath = path;
    	String moveupDirPath = "";
    	if (!"".equals(path)) {
    		String str = currentDirPath.substring(0, currentDirPath.length() - 1);
    		moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
    	}

    	//排序形式，name or size or type
    	String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

    	//不允许使用..移动到上一级目录
    	if (path.indexOf("..") >= 0) {
    		return "Access is not allowed.";
    	}
    	//最后一个字符不是/
    	if (!"".equals(path) && !path.endsWith("/")) {
    		return "Parameter is not valid.";
    	}

    	FTPFile[] files = ftpClient.listFiles(remoteDir);
    	//遍历目录取的文件信息
    	List<Hashtable> _fileList = new ArrayList<Hashtable>();
    	for (FTPFile ftpFile : files) {
    		Hashtable<String, Object> hash = new Hashtable<String, Object>();
    		String fileName = ftpFile.getName();
    		String fileExt="";
    		boolean file=ftpClient.changeWorkingDirectory("/"+fileName);
    		if(file){
    			hash.put("is_dir", true);
    			hash.put("has_file", (ftpClient.listFiles("/"+fileName+"/")).length!=0);
    			hash.put("filesize", 0L);
    			hash.put("is_photo", false);
    			hash.put("filetype", "");
    		}else if(null!=fileName||!"".equals(fileName)&&!ftpClient.changeWorkingDirectory("/"+fileName)){
    			fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    			hash.put("is_dir", false);
    			hash.put("has_file", false);
    			hash.put("filesize", ftpFile.getSize());
    			hash.put("is_photo", Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt));
    			hash.put("filetype", fileExt);
    		}
    		hash.put("filename", fileName);
    		hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
    				((Calendar)ftpFile.getTimestamp()).getTime()));
    		if((!"".equals(fileExt)&&Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt))||file){
    			_fileList.add(hash);
    		}
    	}

    	if ("size".equals(order)) {
    		Collections.sort(_fileList, new SizeComparator());
    	} else if ("type".equals(order)) {
    		Collections.sort(_fileList, new TypeComparator());
    	} else {
    		Collections.sort(_fileList, new NameComparator());
    	}
    	JSONObject result = new JSONObject();
    	result.put("moveup_dir_path", moveupDirPath);
    	result.put("current_dir_path", currentDirPath);
    	result.put("current_url", currentUrl);
    	result.put("total_count", _fileList.size());
    	result.put("file_list", _fileList);

    	/* 设置格式为text/json */
    	response.setContentType("application/json"); 
    	/*设置字符集为'UTF-8'*/
    	response.setCharacterEncoding("UTF-8"); 
    	return result.toJSONString();
    	
    }
    @RequestMapping(value="/admin/news/uploadLeaderJson.do")
    public @ResponseBody Map<String,Object> uploadLeaderJson(@RequestParam("localfilepath")String filepath)throws Exception{
   	 Map<String,Object> map = QiniuUploadUtil.upload(filepath, XsbBusinessConstant.QINIU_BUCKETNAME);
   		int a = (int)map.get(ResultCode.RESULT_CODE);
   		String b = (String)map.get("key");
   		JSONObject obj = new JSONObject();
   		if(a==1){
   			String url = XsbBusinessConstant.QINIU_URL+b;
   			obj.put("error", 0);
   			obj.put("url", url);
   			obj.put("returnPath", url);
   			return obj;
   		}else{
   		}
		return obj;
    }
    
    @RequestMapping(value = "/ueditorUpload.do")    
    @ResponseBody    
    public Map<String, String> ueditorUpload(HttpServletRequest request,@RequestParam CommonsMultipartFile upfile) throws IOException {    
        Map<String, String> result =new HashMap<String, String>();  
        System.out.println(upfile.getFileItem().getFieldName());
        String action = request.getParameter("action");    
        String path = getFilePath(upfile,action);  
       /* File file = new File(path);  */
        System.out.println(path);  
        String state = "SUCCESS";  
        if("".equals(path)){
        	state = "FALSE";  
        }
        //返回类型    
       // String rootPath = request.getContextPath();  
        result.put("url",path);
        result.put("size", String.valueOf(upfile.getSize()));    
        result.put("type", upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf("."))); 
        result.put("state", state);    
        return result;    
    }  
    protected String getFilePath(CommonsMultipartFile uploadFile,String action){
    	String dirName=uploadFile.getName();//dir取不到值改为dirName
    	if("uploadimage".equals(action)){
    		dirName = "image";
    	}else if("uploadvideo".equals(action)){
    		dirName = "media";
    	}else if("uploadfile".equals(action)){
    		dirName = "file";
    	}
    	String  returnPath="";
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    	String fileName = uploadFile.getOriginalFilename();
    	String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    	String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "."+fileExt;
    	try {
			returnPath=UploadUtil.FTPupload(uploadFile.getInputStream(),newFileName,dirName,false);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			returnPath="";
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			returnPath="";
		}
        return returnPath;  
    }  
      
    protected File getFile(String path){  
        File file = new File(path);  
        return file;  
          
    }  

	/********************************************************************************************************************************/
   
    private JSONObject getError(String message) {
    	JSONObject obj = new JSONObject();
    	obj.put("error", 1);
    	obj.put("message", message);
    	return obj;
    }
    
}
 class NameComparator implements Comparator {
	public int compare(Object a, Object b) {
		Hashtable hashA = (Hashtable)a;
		Hashtable hashB = (Hashtable)b;
		if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
			return -1;
		} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
			return 1;
		} else {
			return ((String)hashA.get("filename")).compareTo((String)hashB.get("filename"));
		}
	}
}
 class SizeComparator implements Comparator {
	public int compare(Object a, Object b) {
		Hashtable hashA = (Hashtable)a;
		Hashtable hashB = (Hashtable)b;
		if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
			return -1;
		} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
			return 1;
		} else {
			if (((Long)hashA.get("filesize")) > ((Long)hashB.get("filesize"))) {
				return 1;
			} else if (((Long)hashA.get("filesize")) < ((Long)hashB.get("filesize"))) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}
 
 class TypeComparator implements Comparator {
	public int compare(Object a, Object b) {
		Hashtable hashA = (Hashtable)a;
		Hashtable hashB = (Hashtable)b;
		if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
			return -1;
		} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
			return 1;
		} else {
			return ((String)hashA.get("filetype")).compareTo((String)hashB.get("filetype"));
		}
	}
	
}