package com.xsbweb.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.xsbweb.common.bean.ResultCode;
import com.xsbweb.common.bean.XsbBusinessConstant;

public class QiniuUploadUtil {
	
	  //密钥配置
	  private static Auth auth = Auth.create(XsbBusinessConstant.QINIU_APP_KEY, XsbBusinessConstant.QINIU_APP_SECRET);
	  //创建上传对象
	  private static UploadManager uploadManager = new UploadManager();

	  //简单上传，使用默认策略，只需要设置上传的空间名就可以了
	  private static String getUpToken(String bucketname){
	      return auth.uploadToken(bucketname);
	  }
	  
	  public static Map<String,Object> upload(String filePath,String bucketname) throws IOException{
		Map<String,Object> map = new HashMap<String, Object>();
	    try {
	      //调用put方法上传
	      Response res = uploadManager.put(filePath, null, getUpToken(bucketname));
	      map= res.jsonToMap().map();
	      map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_SUCCESS);
	      System.out.println(res.bodyString()); 
	      } catch (QiniuException e) {
	    	  map.put(ResultCode.RESULT_CODE, ResultCode.RESPONSE_FAIL);
	          Response r = e.response;
	          // 请求失败时打印的异常的信息
	          System.out.println(r.toString());
	          try {
	              //响应的文本信息
	            System.out.println(r.bodyString());
	          } catch (QiniuException e1) {
	              //ignore
	          }
	      }
	    return map;
	  }
	  
}
