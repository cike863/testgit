package com.xsbweb.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;




import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class IdlSDK {

	private static Logger log = Logger.getLogger(IdlSDK.class);
	/**
	 * 接口调用公共方法 get方法
	 * @param url 
	 * @param param
	 * @return
	 */
	public static Map<String,Object> httpGetToJson(String url){
		Map<String,Object> map = new HashMap<String, Object>();
		log.info("########接口get请求开始，url:"+url);
		//参数转换为json格式
		PrintWriter out = null;
        BufferedReader in = null;
		 try {
	            URL realUrl = new URL(url);
	            // 打开和URL之间的连接
	            URLConnection conn = (URLConnection)realUrl.openConnection();
	            // 设置通用的请求属性
	            conn.setRequestProperty("accept", "application/json");
	            conn.setRequestProperty("connection", "utf-8");
	            conn.setRequestProperty("content-type","application/json;charset=utf-8");
	            conn.connect();
	            BufferedReader bufIn = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
	    		String line = null;
	    		StringBuffer result = new StringBuffer();
	    		while((line = bufIn.readLine())!=null)
	    		{
	    			result.append(line);
	    		}	
	            map = parseJSON2Map(result.toString());
	        } catch (Exception e) {
	            System.out.println("发送请求出现异常！"+e);
	            e.printStackTrace();
	        }
	        //使用finally块来关闭输出流、输入流
	        finally{
	            try{
	                if(out!=null){
	                    out.close();
	                }
	                if(in!=null){
	                    in.close();
	                }
	            }
	            catch(IOException ex){
	                ex.printStackTrace();
	            }
	        }
		return map;
	}
	
	
	/**
	 * 接口调用公共方法 post方法
	 * @param url 
	 * @param param
	 * @return
	 */
	public static Map<String,Object> httpPostToJson(String getUrl,String param){
		StringBuffer data=new StringBuffer(param);
		StringBuffer sb = null;
		BufferedReader reader = null;
		OutputStreamWriter wr = null;
		URL url;
		try {
			url = new URL(getUrl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setConnectTimeout(1000 * 5);
			// 当存在post的值时，才打开OutputStreamWriter
			if (data != null && data.toString().trim().length() > 0) {
				wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
				wr.write(data.toString());
				wr.flush();
			}

			// Get the response
			reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			sb = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "/n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sb = new StringBuffer();
		} finally {
			try {
				if (wr != null) {
					wr.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return parseJSON2Map(sb.toString());
	}
	
	public static Map<String, Object> parseJSON2Map(String jsonStr){
	    Map<String, Object> map = new HashMap<String, Object>();
	    //最外层解析
	    JSONObject json = JSONObject.fromObject(jsonStr);
	    for(Object k : json.keySet()){
	      Object v = json.get(k); 
	      //如果内层还是数组的话，继续解析
	      if(v instanceof JSONArray){
	        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	        Iterator<JSONObject> it = ((JSONArray)v).iterator();
	        while(it.hasNext()){
	          JSONObject json2 = it.next();
	          list.add(parseJSON2Map(json2.toString()));
	        }
	        map.put(k.toString(), list);
	      } else {
	        map.put(k.toString(), v);
	      }
	    }
	    return map;
	  }
	
	public static void main(String[] args) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("loginMethod", "j2ee");
		param.put("newsNo", "572");
		//String urlget = "http://192.168.1.179:8080/xsbweb/app/project/getProjectByNo.do?loginMethod=ios&projectNo=proj000743";
		String urlPost = "http://www.neeq.cc/ajax/Kmin";
		//Map<String,Object> r = httpGetToJson(urlget);
		Map<String,Object> r2 = httpPostToJson(urlPost, "zqdm=830925");
		System.out.println(r2);
	}
	/*
	 * get方式请求http服务
	 * 
	 * @param urlStr http://192.168.1.179:8080/xsbweb/app/news/getNewsDetail2.do
	 * 
	 * @param params  name=yxd&age=25
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public static Map getURLByGet(String http_url_pro,String Param) throws Exception {
		/** 网络的url地址 */
		URL url = null;
		/** http连接 */
		HttpURLConnection httpConn = null;
		/**//** 输入流 */
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();
		try {

			url = new URL(http_url_pro+"?"+ Param);
			in = new BufferedReader(new InputStreamReader(url.openStream(),
					"UTF-8"));
			String str = null;
			while ((str = in.readLine()) != null) {
				sb.append(str);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		String result = sb.toString();
		return toMap(result);
	}

	/*
	 * post方式请求http服务
	 * 
	 * @param urlStr http://192.168.1.179:8080/xsbweb/app/news/getNewsDetail2.do
	 * 
	 * @param params name=yxd&age=25
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public static Map getURLByPost(String tourl,String Param) {
		StringBuffer data=new StringBuffer(Param);
		StringBuffer sb = null;
		BufferedReader reader = null;
		OutputStreamWriter wr = null;
		URL url;
		try {
			url = new URL(tourl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setConnectTimeout(1000 * 5);
			// 当存在post的值时，才打开OutputStreamWriter
			if (data != null && data.toString().trim().length() > 0) {
				wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
				wr.write(data.toString());
				wr.flush();
			}

			reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			sb = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "/n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (wr != null) {
					wr.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return toMap(sb.toString());
	}
	/*
	 * json字符串转换成MAP
	 */
	public static Map toMap(String jsonResult){
		
		ObjectMapper objectMapper = new ObjectMapper();
		// 将json字符串转成map结合解析出来，并打印(这里以解析成map为例)
		Map<String, Map<String, Object>> maps=null;
		try {
			maps = objectMapper.readValue(jsonResult,
					Map.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return maps;
	}
}
