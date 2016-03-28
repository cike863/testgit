package com.xsbweb.common.bean;

import java.util.HashMap;

public class ResultCode {
	
	public static String RESULT_CODE="resultCode";
	
	public static String RESULT_OBJECT="resultObject";
	/**
	 * 操作失败
	 */
	public static final int RESPONSE_FAIL = 0;
	
	/**
	 * 操作成功
	 */
	public static final int RESPONSE_SUCCESS = 1;
	
	/**
	 * 系统错误
	 */
	public static final int ERROR_SYSTEM = 10001;
	
	/**
	 * 暂停服务
	 */
	public static final int ERROR_OUT_OF_SERVICE = 10002;
	
	/**
	 * 处理超时
	 */
	public static final int ERROR_TIME_OUT = 10003;
	
	/**
	 * 非法请求
	 */
	public static final int ERROR_REQUEST = 10004;
	
	/**
	 * 用户授权不可为空
	 */
	public static final int ERROR_TOKEN_NOT_NULL = 10005;
	
	/**
	 * 用户授权过期
	 */
	public static final int ERROR_TOKEN_TIMEOUT = 10006;
	
	/**
	 * 请求参数错误
	 */
	public static final int ERROR_PARAMETER_ERROR = 10007;
	
	/**
	 * 业务异常
	 */
	public static final int ERROR_BISNUESS_EXCEPTION = 10008;
	
	/**
	 * 请求的客户端版本号为空
	 */
	public static final int ERROR_VERSION_NULL = 10009;
	
	/**
	 * 当前有新版本，需要更新才能使用
	 */
	public static final int ERROR_VERSION_UPDATE = 10010;
	
	/**
	 * 用户被锁
	 */
	public static final int ERROR_USER_LOCK = 10011;
	
	/**
	 * 进入黑名单
	 */
	public static final int ERROR_USER_BLACK = 10012;
	
	
	public static HashMap<Integer, String> CODEMESSAGE = null;
	
	static{
		CODEMESSAGE = new HashMap<Integer, String>();
		
		CODEMESSAGE.put(ERROR_SYSTEM, "系统错误");
		CODEMESSAGE.put(ERROR_OUT_OF_SERVICE, "暂停服务");
		CODEMESSAGE.put(ERROR_TIME_OUT, "处理超时");
		CODEMESSAGE.put(ERROR_REQUEST, "非法请求");
		CODEMESSAGE.put(ERROR_TOKEN_NOT_NULL, "用户授权不可为空");
		CODEMESSAGE.put(ERROR_TOKEN_TIMEOUT, "用户授权过期");
		CODEMESSAGE.put(ERROR_PARAMETER_ERROR, "请求参数错误");
		CODEMESSAGE.put(ERROR_BISNUESS_EXCEPTION, "业务异常");
		CODEMESSAGE.put(ERROR_VERSION_NULL, "版本号不能为空");
		CODEMESSAGE.put(ERROR_VERSION_UPDATE, "需版本更新");
	}
	
	/**
	 * 手机号码为空
	 */
	public static final int ERROR_PHONENO_NULL = 90000;
	/**
	 * 手机验证码超时
	 */
	public static final int ERROR_CODE_OUTTIME = 90001;
	/**
	 * 手机验证码错误
	 */
	public static final int ERROR_CODE_ERROR = 90002;
	/**
	 * 密码错误
	 */
	public static final int ERROR_PWD_ERROR = 80000;
	
	/**
	 * 容联子账号不足
	 */
	public static final int IM_SUBACCOUNT_NOTENOUGH = 70001;
	
	/**
	 * 会议不存在
	 */
	public static final int MEET_CONF_NULL = 70002;
	
	//手机号码已存在
	public static final int CUSTOMER_EXIST = 20001;
	
	//用户ID为空,用户不存在
	public static final int CUSTOMER_ID_NULL = 20002;
	
	//用户信息不完善
	public static final int CUSTOMER_INFO_COMPLETE = 20003;
	
	//用户已经报名
	public static final int PROJECT_IS_APPLY = 30003;
	
	//公司已申请我要路演
	public static final int COMPANY_SAME_ROAD_APPLY = 30004;
	
	//用户已经收藏
	public static final int FAVOR_IS_ED = 40001;
	
	//文件扩展名有不符合
	public static final int UPLOAD_ERROE_FILEEXT = 50001;
	
	//文件超过规定大小
	public static final int UPLOAD_ERROE_MAX = 50002;
	
	//后台没有获取到文件
	public static final int UPLOAD_ERROE_NULL = 50003;
	
	//excel表为空
	public static final int RESPONSE_EXCEL_FAIL = 60001;
	
	//导出的excel无数据
	public static final int GET_EXCEL_FAIL = 60002;
	
	//导出的excel数据超过5000条
	public static final int EXCEL_COUNT_TOMAX = 60003;
	
	//excel格式正确
	public static final int REARD_EXCEL_FAIL = 60003;
	//视频开始直播失败
	public static final int VIDEO_DOING_FAIL = 70001;
	//视频结束直播失败
	public static final int VIDEO_DONE_FAIL = 70002;
}
