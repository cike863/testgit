package com.xsbweb.controller.zt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.xsbweb.common.bean.ResultCode;
import com.xsbweb.common.bean.XsbBusinessConstant;
import com.xsbweb.common.bean.ZtConstant;
import com.xsbweb.controller.CustomerController;
import com.xsbweb.duiba.rest.CreditConsumeParams;
import com.xsbweb.duiba.rest.CreditConsumeResult;
import com.xsbweb.duiba.rest.CreditNotifyParams;
import com.xsbweb.duiba.rest.CreditTool;
import com.xsbweb.service.UploadFilesService;
import com.xsbweb.service.XSBBaseService;
import com.xsbweb.service.ZtService;
import com.xsbweb.util.CommonUtils;
import com.xsbweb.util.ConfigUtil;
import com.xsbweb.util.IdlSDK;
import com.xsbweb.util.RedisUtil;
import com.xsbweb.util.UploadUtil;
import com.xsbweb.vo.TrsMedia;
import com.xsbweb.vo.extend.CustomerVO;
import com.xsbweb.vo.zt.FemaleLeader;
import com.xsbweb.vo.zt.SubsConsume;
import com.xsbweb.vo.zt.TrsVote;
import com.xsbweb.vo.zt.WxSubscriber;

@Controller
@RequestMapping(value="/zt")
public class ZtWomenDayController {

	private Logger log = Logger.getLogger(ZtWomenDayController.class);
	
	@Autowired
	private ZtService ztService;
	
	@Resource
    private	RedisUtil redisUtil;
	
	@Autowired
	private XSBBaseService xsbBaseService;
	
	@Autowired 
	private UploadFilesService uploadFilesService;
	
	/**
	 * 生成免登录url
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/womenday/login/{wxId}",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> login(HttpServletRequest request,
			@PathVariable("wxId")String wxId
			){
		Map<String,Object> map = new HashMap<String,Object>();
		//参数校验
		if(CommonUtils.isBlank(wxId)){
			//请求参数有误
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		//用户id 
		String uid = wxId;
		//查询改id是否存在
		long credits = 0l;
		WxSubscriber wx2;
		try {
			wx2 = ztService.getWxSubscriber(uid);
			String score = "";
			if(wx2==null){
				score = "0";
			}else{
				score = String.valueOf(wx2.getVoteScore());
			}
			//用户积分余额
			//接口appKey，应用的唯一标识
			String appKey = ZtConstant.duiba_app_key;
			String appSecret = ZtConstant.duiba_app_secret;
			//1970-01-01开始的时间戳，毫秒为单位。
			long timestamp = new Date().getTime();
			//登录成功后的重定向地址
			String redirect = "";
			//MD5签名
			String sign = "";
			CreditTool tool=new CreditTool(appKey,appSecret);
			Map params=new HashMap();
			params.put("uid",uid);
			params.put("credits",score);
			if(redirect!=null){
			    //redirect是目标页面地址，默认积分商城首页是：http://www.duiba.com.cn/chome/index
			    //此处请设置成一个外部传进来的参数，方便运营灵活配置
			    params.put("redirect",redirect);
			}
			//此url即为免登录url
			String url=tool.buildUrlWithSign("http://www.duiba.com.cn/autoLogin/autologin?",params);
			log.info("#######生成免登录url:"+url);
			map.put("url", url);
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		}
		return map;
	}
	/**
	 * 用户积分扣除回调接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/womenday/deduct")
	public @ResponseBody String deduct(HttpServletRequest request){
		CreditTool tool=new CreditTool(ZtConstant.duiba_app_key, ZtConstant.duiba_app_secret);
		String resultStr = "";
		try {
		    CreditConsumeParams params= tool.parseCreditConsume(request);//利用tool来解析这个请求
		    String uid=params.getUid();//用户id
		    Long credits=params.getCredits();
		    String type=params.getType();//获取兑换类型
		    String alipay=params.getAlipay();//获取支付宝账号
		    String orderNum = params.getOrderNum();
		    //其他参数参见 params的属性字段
		    
		    WxSubscriber wxSubscriber = ztService.getWxSubscriber(uid);
		    String errorMessage = "";
		    Long yvs = -1l; 
		    String bizId = "";
		    if(wxSubscriber==null){
		    	errorMessage = "改用户已经不存在！";
		    }else{
		    	int voteScore = wxSubscriber.getVoteScore();
		    	int vs = Integer.valueOf(String.valueOf(credits));
		    	int yuVoteScore = voteScore-vs;
		    	if(yuVoteScore<0){
		    		errorMessage = "积分余额不够！";
		    	}else{
		    		WxSubscriber wx2 = new WxSubscriber();
		    		wx2.setWxSubsId(uid);
		    		wx2.setVoteScore(yuVoteScore);
		    		//修改用户积分余额
		    		ztService.editWxSubscriber(wx2);
		    		//记录到订单表，系统中的业务订单id
				    SubsConsume subsConsume = new SubsConsume();
				    subsConsume.setCnsumStatus("1");
				    subsConsume.setVoteScore(-vs);
				    subsConsume.setWxSubsId(uid);
				    subsConsume.setExCnsumNo(orderNum);
				    int cnsumNo = ztService.addSubsConsume(subsConsume);
				    bizId = cnsumNo+"";
		    		//返回结果里更新积分余额信息		
		    		yvs = Long.valueOf(String.valueOf(yuVoteScore));
		    	}
		    }
		    CreditConsumeResult result;
		    if(CommonUtils.isNotBlank(errorMessage)){
		    	result = new CreditConsumeResult(false);
		    	result.setErrorMessage(errorMessage);
		    }else{
		    	result = new CreditConsumeResult(true);
		    	result.setBizId(bizId);
		    	result.setCredits(yvs);
		    }
		    resultStr = result.toString();
		    log.info("#######用户积分扣除回调接口:"+resultStr);
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		 return resultStr;
	}
	
	/**
	 * 用户积分扣除回调接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/womenday/isSuccess")
	public @ResponseBody String isSuccess(HttpServletRequest request){
		CreditTool tool=new CreditTool(ZtConstant.duiba_app_key, ZtConstant.duiba_app_secret);
		log.info("#######测试isSuccess");
		try {
		    CreditNotifyParams params= tool.parseCreditNotify(request);//利用tool来解析这个请求
		    String orderNum=params.getOrderNum();
		    String cnsumNo = params.getBizId();
		    if(params.isSuccess()){
		        //兑换成功
		    	log.info("#######兑换成功！！！！！！");
		    }else{
		        //兑换失败，根据orderNum，对用户的金币进行返还，回滚操作
		    	SubsConsume subsConsume = ztService.getSubsConsumeByCnsumNo(cnsumNo);
		    	if(subsConsume==null){
		    		log.info("#######订单号："+cnsumNo+",在数据库中不存在");
		    	}else{
		    		SubsConsume sub2 = new SubsConsume();
		    		//失败的记录
		    		sub2.setCnsumStatus("0");
		    		sub2.setExCnsumNo(orderNum);
		    		sub2.setVoteScore(Math.abs(subsConsume.getVoteScore()));
		    		sub2.setWxSubsId(subsConsume.getWxSubsId());
		    		ztService.addSubsConsume(sub2);
		    		
		    		//查询账户积分信息
		    		WxSubscriber wxSubscriber = ztService.getWxSubscriber(subsConsume.getWxSubsId());
		    		if(wxSubscriber!=null){
		    			int voteScore = wxSubscriber.getVoteScore();
		    			WxSubscriber wx2 = new WxSubscriber();
		    			wx2.setWxSubsId(subsConsume.getWxSubsId());
		    			wx2.setVoteScore(voteScore+Math.abs(subsConsume.getVoteScore()));
		    			//修改用户积分余额
		    			ztService.editWxSubscriber(wx2);
		    			log.info("#######账号："+subsConsume.getWxSubsId()+",订单号："+orderNum+"，"+Math.abs(subsConsume.getVoteScore())+"积分返回成功");
		    		}else{
		    			log.info("#######账号："+subsConsume.getWxSubsId()+",在数据库中不存在");
		    		}
		    	}
		    }
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		 return "ok";
	}
	
	/**
	 * 获取首页数据
	 * @return
	 */
	@RequestMapping(value="/womenday/index",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getIndexData(
			HttpServletRequest request,
			@RequestParam(value="loginMethod",required=false) String loginMethod
			){
		Map<String,Object> map = new HashMap<String,Object>();
		//orderType,1:正常排序，2：按投票排序
		FemaleLeader femaleLeader = new FemaleLeader();
		femaleLeader.setOrderType("1");
		//类型为开放投票的
		femaleLeader.setVotedStatus("1");
		try {
			//获取普通用户投票
			List<FemaleLeader> femaleLeaders = ztService.getFemaleLeaderList(femaleLeader);
			//获取投票总数
			int allVoteCount = ztService.getAllVoteCounts();
			List<FemaleLeader> specialLeaders = ztService.getSpecialFemaleLeaderList();
			
			map.put("femaleLeaders", femaleLeaders);
			map.put("specialLeaders", specialLeaders);
			map.put("allVoteCount", allVoteCount);
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		    map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		}finally{
			request.setAttribute(ResultCode.RESULT_OBJECT, map);
		}
		 return map;
	}
	
	
	/**
	 * 获取开发投票了的候选人列表
	 * @param request
	 * @param orderType
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/womenday/femaleLeaders",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getFemaleLeaderList(
			HttpServletRequest request,
			@RequestParam(value="leaderName",required=false) String leaderName,
			@RequestParam(value="orderType",required=false) String orderType,
			@RequestParam(value="pageNo",required=false) Integer pageNo,
			@RequestParam(value="pageSize",required=false) Integer pageSize
			){
		Map<String,Object> map = new HashMap<String,Object>();
		//orderType,1:正常排序，2：按投票排序
		FemaleLeader femaleLeader = new FemaleLeader();
		femaleLeader.setPageNo(pageNo==null?1:pageNo);
		femaleLeader.setPageSize(pageSize==null?1:pageSize);
		femaleLeader.setOrderType(orderType==null?"1":orderType);
		//类型为开放投票的
		femaleLeader.setVotedStatus("1");
		try {
			//搜索stockId来搜候选人
			String regex = "[+-]?[0-9]*";
			//验证是否为数字
			if(CommonUtils.regexString(regex,leaderName)){
				femaleLeader.setStockId(leaderName);;
			}else{
				femaleLeader.setLeaderName(leaderName);
			}
			List<FemaleLeader> femaleLeaders = ztService.getFemaleLeaderList(femaleLeader);
			map.put("femaleLeaders", femaleLeaders);
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		    map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		}
		 return map;
	}  
	
	
	/**
	 * 获取单个候选人信息
	 * @param request
	 * @param leaderName
	 * @param companyName
	 * @return
	 */
	@RequestMapping(value="/womenday/femaleLeader",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getFemaleLeader(
			HttpServletRequest request,
			@RequestParam(value="leaderName",required=false) String leaderName,
			@RequestParam(value="companyName",required=false) String companyName
			){
		Map<String,Object> map = new HashMap<String,Object>();
		//参数校验
		if(CommonUtils.isBlank(companyName) || CommonUtils.isBlank(leaderName)){
			//请求参数有误
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		FemaleLeader femaleLeader = new FemaleLeader();
		femaleLeader.setLeaderName(leaderName);
		femaleLeader.setCompanyName(companyName);
		femaleLeader.setPageNo(1);
		femaleLeader.setPageSize(10);
		//类型为开放投票的
		femaleLeader.setVotedStatus("1");
		try {
			FemaleLeader fledaer = ztService.getFemaleLeader(femaleLeader);
			map.put("femaleLeader", fledaer);
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		    map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		}
		 return map;
	}  
	
	/**
	 * 投票
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/womenday/vote",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> vote(
			HttpServletRequest request,
			@RequestParam(value="companyName",required=false) String companyName,
			@RequestParam(value="leaderName",required=false) String leaderName,
			@RequestParam(value="wxSubsId",required=false) String wxSubsId
			){
		Map<String,Object> map = new HashMap<String,Object>();
		//参数校验
		if(CommonUtils.isBlank(companyName) || CommonUtils.isBlank(leaderName) || CommonUtils.isBlank(wxSubsId)){
			//请求参数有误
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		TrsVote trsVote = new TrsVote();
		trsVote.setCompanyName(companyName);
		trsVote.setLeaderName(leaderName);
		trsVote.setWxSubsId(wxSubsId);
		try {
			int result = ztService.addTrsVote(trsVote);
			//0 投票失败,1 成功,2 该微信账号已经投了该候选人,3 给微信账号今天已经投了三次了
			map.put(ResultCode.RESULT_CODE, result);
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		    map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		}
		 return map;
	}
	
	/**
	 * 新用户关注微信号，新微信用户记录
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/womenday/wxSubscriber/{wxId}",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addWxSubscriber(
			HttpServletRequest request,
			@PathVariable("wxId")String wxId
			){
		Map<String,Object> map = new HashMap<String,Object>();
		//参数校验
		if(CommonUtils.isBlank(wxId)){
			//请求参数有误
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		WxSubscriber wxSubscriber = new WxSubscriber();
		wxSubscriber.setWxSubsId(wxId);
		wxSubscriber.setVoteScore(0);
		try {
			int result = 0;
			//如果该wxId存在，则不进行插入操作
			WxSubscriber wx2 = ztService.getWxSubscriber(wxSubscriber.getWxSubsId());
			if(wx2!=null){
				result = 1;
			}else{
				result = ztService.addWxSubscriber(wxSubscriber);
			}
			//0失败,1 成功
			map.put(ResultCode.RESULT_CODE, result);
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		    map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		}
		 return map;
	}
	
	/**
	 * 获取开发投票了的候选人列表
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *//*
	@RequestMapping(value="/womenday/femaleLeader",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addFemaleLeader(
			HttpServletRequest request,
			FemaleLeader femaleLeader
			){
		Map<String,Object> map = new HashMap<String,Object>();
		//参数校验
		if(femaleLeader==null 
				|| CommonUtils.isBlank(femaleLeader.getCompanyName()) 
				|| CommonUtils.isBlank(femaleLeader.getLeaderName()) 
				|| CommonUtils.isBlank(femaleLeader.getLeaderMobile()) 
				|| CommonUtils.isBlank(femaleLeader.getCandidateSlogan()) 
				|| CommonUtils.isBlank(femaleLeader.getCompanyInfo())){
			//请求参数有误
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		femaleLeader.setVotedStatus("0");
		//femaleLeader.setVotedCnt(0);
		try {
			int result = ztService.addFemaleLeader(femaleLeader);
			map.put(ResultCode.RESULT_CODE, result);
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		    map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		}
		 return map;
	}*/
	
	/**
	 * 报名
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/womenday/apply",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> apply(
			HttpServletRequest request,
			@RequestParam(value="leaderName",required=false) String leaderName,
			@RequestParam(value="companyName",required=false) String companyName,
			@RequestParam(value="leaderMobile",required=false) String leaderMobile,
			@RequestParam(value="candidateSlogan",required=false) String candidateSlogan,
			@RequestParam(value="companyInfo",required=false) String companyInfo,
			@RequestParam(value="phoneCode",required=false) String phoneCode,
			@RequestParam(value="leaderPicPath",required=false) String leaderPicPath,
			@RequestParam(value="companyPicPath",required=false) String companyPicPath
			){
		Map<String,Object> map = new HashMap<String,Object>();
		//参数校验
		if(CommonUtils.isBlank(leaderName) || CommonUtils.isBlank(phoneCode)
				|| CommonUtils.isBlank(companyName) 
				|| CommonUtils.isBlank(leaderMobile) 
				|| CommonUtils.isBlank(candidateSlogan) 
				|| CommonUtils.isBlank(companyInfo)){
			//请求参数有误
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_PARAMETER_ERROR);
			return map;
		}
		log.info("###########################################传过来的code:"+phoneCode);
        String code = redisUtil.getString("tmp:"+leaderMobile+":code");
        log.info("###########################################tmp:"+leaderMobile+":code="+code);
        if(CommonUtils.isBlank(code)){
        	map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_CODE_ERROR);
        	return map;
        }
        //验证码是否正确
        if(!code.equals(phoneCode)){  
        	map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_CODE_ERROR);
        	return map;
        } 
        try {
        	FemaleLeader femaleLeader = new FemaleLeader();
	        femaleLeader.setCandidateSlogan(candidateSlogan);
	        femaleLeader.setCompanyInfo(companyInfo);
	        femaleLeader.setCompanyName(companyName);
	        femaleLeader.setCompanyPicPath(companyPicPath);
	        femaleLeader.setLeaderMobile(leaderMobile);
	        femaleLeader.setLeaderName(leaderName);
	        femaleLeader.setLeaderPicPath(leaderPicPath);
	        //初始状态为待审核
	        femaleLeader.setVotedStatus("0");
	        //来源默认为1
	        femaleLeader.setLeaderSource("1");
	        FemaleLeader fl = ztService.getFemaleLeader(femaleLeader);
	        if(fl!=null){
	        	map.put(ResultCode.RESULT_CODE, "-1");
	        }else{
	        	int reault = ztService.addFemaleLeader(femaleLeader);
	        	map.put(ResultCode.RESULT_CODE, reault);
	        }
		} catch (Exception e) {
			e.printStackTrace();
			map.put(ResultCode.RESULT_CODE, ResultCode.ERROR_SYSTEM);
		}
 		return map;
	}
	
	
	/**
	 * 资源上传数据插入接口
	 * @return
	 */
	@RequestMapping(value="/womenday/upload",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> upload(
			HttpServletRequest request
			) {
		Map<String,Object> map = new HashMap<String,Object>();
		String newFileName=null;
	    String returnPath=null;
	    //定义允许上传的文件扩展名
	    String mediaType = "image";
	    Map<String, String> extMap = new HashMap<String, String>();
	    extMap.put("image", "gif,jpg,jpeg,png,bmp");
	    extMap.put("flash", "swf,flv");
	    extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
	    extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
	    //最大文件大小 1M
	    long maxSize =1*1024*1024;
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
			returnPath=UploadUtil.FTPupload(multFile.getInputStream(),newFileName,mediaType,false);
			//修改customer_media_no字段信息
			map.put("status", "success");
			map.put("src", XsbBusinessConstant.DYLY_URL+returnPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("status", "fail");
			return map;
		}finally{
			request.setAttribute(ResultCode.RESULT_OBJECT, map);
		}
		return map;
	}
	
	/**
	 * 初始化已关注的微信账号
	 * @return
	 */
	@RequestMapping(value="/womenday/admin/initwx")
	public @ResponseBody String initWxSubscriber(){
		String url = "http://120.27.194.22:80/XSBmobile/getCilentList.do";
		Map<String,Object> map = IdlSDK.httpGetToJson(url);
		if(map!=null){
			Map<String,Object> map2 = (Map<String,Object>)map.get("data");
			if(map2!=null){
				List<String> wxidList = (List<String>)map2.get("openid");
				if(wxidList!=null && !wxidList.isEmpty()){
					List<WxSubscriber> wxlist = new ArrayList<WxSubscriber>();
					WxSubscriber wxSubscriber = null;
					/*for (int i = 0; i < wxidList.size(); i++) {
						if(i<=10){
							wxSubscriber = new WxSubscriber();
							wxSubscriber.setVoteScore(0);
							wxSubscriber.setWxSubsId(wxidList.get(i));
							wxlist.add(wxSubscriber);
						}
					}*/
					for (String wxid : wxidList) {
						wxSubscriber = new WxSubscriber();
						wxSubscriber.setVoteScore(0);
						wxSubscriber.setWxSubsId(wxid);
						wxlist.add(wxSubscriber);
					}
					try {
						int result = ztService.batchInsertWx(wxlist);
						System.out.println(result);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return "1";
	}
	
//-----------------------------------------后台代码块--------------------------------------------------------------------------
	/**
	 * 列表显示
	 * @param femaleLeader
	 * @return
	 */
	@RequestMapping(value="/womenday/admin/femaleLeaders",method= RequestMethod.GET)
	public ModelAndView getFemaleLeaderList(FemaleLeader femaleLeader){
		ModelAndView mav = new ModelAndView();
		try {
			int count = ztService.getFemaleLeaderListCount(femaleLeader);
			femaleLeader.setTotalRecord(count);
			List<FemaleLeader> femaleLeaders = ztService.getFemaleLeaderList(femaleLeader);
			mav.addObject("femaleLeaders",femaleLeaders);
			mav.addObject("femaleLeader",femaleLeader);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("manage/zt/womenday/queryFemaleLeaderList");
		return mav;
	} 
	/**
	 * 新增页面跳转
	 * @param femaleLeader
	 * @return
	 */
	@RequestMapping(value="/womenday/admin/add",method= RequestMethod.GET)
	public ModelAndView toAddFemaleLeader(FemaleLeader femaleLeader){
		ModelAndView mav = new ModelAndView("manage/zt/womenday/addFemaleLeader");
		mav.addObject("femaleLeader", femaleLeader);
		return mav;
	}
	/**
	 * 新增
	 * @param femaleLeader
	 * @return
	 */
	@RequestMapping(value="/womenday/admin/femaleLeaders",method= RequestMethod.POST)
	public ModelAndView addFemaleLeader(FemaleLeader femaleLeader){
		femaleLeader.setLeaderSource("2");
		ModelAndView mav = new ModelAndView();
		try {
			ztService.addFemaleLeader(femaleLeader);
			mav.setViewName("redirect:/zt/womenday/admin/femaleLeaders");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("femaleLeader", femaleLeader);
			mav.setViewName("manage/zt/womenday/addFemaleLeader");
		}
		return mav;
	}
	/**
	 * 通过公司名领导名获取信息
	 * @param companyName
	 * @param leaderName
	 * @return
	 */
		@RequestMapping(value="/womenday/admin/getfemaleLeaders",method= RequestMethod.GET)
		public ModelAndView getFemaleLeaderByCompanyAndLeaderName(
				@RequestParam(value="type",required=false) String type,
				@RequestParam("companyName") String companyName,
				@RequestParam("leaderName") String leaderName){
		ModelAndView mav = new ModelAndView();
		FemaleLeader femaleLeader = new FemaleLeader();
		femaleLeader.setCompanyName(companyName);
		femaleLeader.setLeaderName(leaderName);
		try {
			femaleLeader = ztService.getFemaleLeader(femaleLeader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("femaleLeader", femaleLeader);
		if("1".equals(type)){
			mav.setViewName("manage/zt/womenday/editFemaleLeader");
		}else{
			mav.setViewName("manage/zt/womenday/certifyFemaleLeader");
		}
		return mav;
	}
	/**
	 * 审核通过
	 * @param companyName
	 * @param leaderName
	 * @return
	 */
	@RequestMapping(value="/womenday/admin/femaleLeaders/certify",method= RequestMethod.GET)
	public ModelAndView certifyFemaleLeaderByCompanyAndLeaderName(
			@RequestParam("companyName") String companyName,
			@RequestParam("leaderName") String leaderName){
		ModelAndView mav = new ModelAndView();
		FemaleLeader femaleLeader = new FemaleLeader();
		femaleLeader.setCompanyName(companyName);
		femaleLeader.setLeaderName(leaderName);
		femaleLeader.setVotedStatus("1");
		try {
			int result = ztService.editFemaleLeader(femaleLeader);
			if (result > 0) {
				mav.setViewName("redirect:/zt/womenday/admin/femaleLeaders");
			} else {
				mav.setViewName("redirect:/zt/womenday/admin/femaleLeaders?companyName="+companyName+"&leaderName="+leaderName);
			}

		} catch (Exception e) {
			mav.setViewName("redirect:/zt/womenday/admin/femaleLeaders?companyName="+companyName+"&leaderName="+leaderName);
		}
		return mav;
	}
	/**
	 * 更新
	 * @param companyName
	 * @param leaderName
	 * @return
	 */
	@RequestMapping(value="/womenday/admin/femaleLeaders/update",method= RequestMethod.POST)
	public ModelAndView updateFemaleLeaderByCompanyAndLeaderName(
			@RequestParam("companyName") String companyName,
			@RequestParam("leaderName") String leaderName,
			FemaleLeader femaleLeader){
		ModelAndView mav = new ModelAndView();
		try {
			int result = ztService.editFemaleLeader(femaleLeader);
			if (result > 0) {
				mav.setViewName("redirect:/zt/womenday/admin/femaleLeaders");
			} else {
				mav.addObject("femaleLeader", femaleLeader);
				mav.setViewName("manage/zt/womenday/editFemaleLeader");
			}
		} catch (Exception e) {
			mav.addObject("femaleLeader", femaleLeader);
			mav.setViewName("manage/zt/womenday/editFemaleLeader");
		}
		return mav;
	}
	/**
	 * 通过公司名领导名删除信息
	 * @param companyName
	 * @param leaderName
	 * @return
	 */
		@RequestMapping(value="/womenday/admin/delfemaleLeaders",method= RequestMethod.GET)
		@ResponseBody
		public Map<String,Object> delFemaleLeaderByCompanyAndLeaderName(
				@RequestParam("companyName") String companyName,
				@RequestParam("leaderName") String leaderName){
		Map<String,Object> map = new HashMap<String,Object>();
		FemaleLeader femaleLeader = new FemaleLeader();
		femaleLeader.setCompanyName(companyName);
		femaleLeader.setLeaderName(leaderName);
		try {
			int result = ztService.delFemaleLeader(femaleLeader);
			if(result>0){
				map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
			}else{
				map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
		}
		return map;
	}
	/**
	 * 订单列表
	 * @param subsConsume
	 * @return
	 */
	@RequestMapping(value="/womenday/admin/subsConsumes",method=RequestMethod.GET)
	public ModelAndView getSubsConsumeList(SubsConsume subsConsume){
		ModelAndView mav = new ModelAndView("manage/zt/womenday/querySubsConsumeList");
		try{
			List<SubsConsume> subsConsumeList = ztService.getSubsConsumeList(subsConsume);
			int count = ztService.getSubsConsumeCount(subsConsume);
			subsConsume.setTotalRecord(count);
			mav.addObject("subsConsumeList", subsConsumeList);
			mav.addObject("subsConsume", subsConsume);
		}catch(Exception e){
			e.printStackTrace();
		}
		return mav;
	}
	
}
