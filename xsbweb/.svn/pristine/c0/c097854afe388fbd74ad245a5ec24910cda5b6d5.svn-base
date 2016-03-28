package com.xsbweb.util;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.xsbweb.service.impl.LoginRegisterServiceImpl;
import com.xsbweb.vo.Menu;

/**
 * 工具类
 * @author Administrator
 *
 */
public class CommonUtils {

	/**
	 * 字符串非空判断
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		if(str==null || str.length() <= 0){
			return true;
		}
		return false;
	}
	/**
	 * 字符串非空判断
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str){
		if(str==null || str.length() <= 0){
			return false;
		}
		return true;
	}
	
	/**
	 * 获取系统当前时间
	 * @return 字符串 "yyyy-MM-dd HH:mm:ss"
	 */
	public static String getInstanceDate()
	 {
		  Date date=new Date();
		  DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String time=format.format(date);
		  return time;
	 }
	
	/**
	 * 获取系统当前时间
	 * @return 字符串 "yyyyMMddHHmmssSSS"
	 */
	public static String getNowDateStringOf17()
	 {
		  Date date=new Date();
		  DateFormat format=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		  String time=format.format(date);
		  return time;
	 }
	/**
	 * 获取系统当前时间
	 * @return 字符串 "yyyyMMddHHmmssSS"
	 */
	public static String getNowDateStringOf16()
	 {
		  Date date=new Date();
		  DateFormat format=new SimpleDateFormat("yyyyMMddHHmmssSS");
		  String time=format.format(date);
		  return time;
	 }
	/**
	 * 获取系统当前时间
	 * @return 字符串 "yyyyMMddHHmmss"
	 */
	public static String getNowDateStringOf14()
	 {
		  Date date=new Date();
		  DateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		  String time=format.format(date);
		  return time;
	 }
	/**
	 * 获取系统当前时间
	 * @return 字符串 "yyyyMMdd"
	 */
	public static String getNowDateStringOf8()
	 {
		  Date date=new Date();
		  DateFormat format=new SimpleDateFormat("yyyyMMdd");
		  String time=format.format(date);
		  return time;
	 }
	/**
	 * 获取系统当前时间
	 * @return Long类型
	 */
	public static Long getInstanceTime()
	 {
		  Date date=new Date();
		  //DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 // String time=format.format(date);
		  return date.getTime();
	 }
	/**
	 * 根据时间字符串获取Long类型时间
	 * @return Long类型
	 * @throws ParseException 
	 */
	public static Long getInstanceTime(String dateStr) throws ParseException
	 {
		if(CommonUtils.isBlank(dateStr)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		Date date = sdf.parse(dateStr);
		return date.getTime();
	 }
	
	/**
	 * 字符串转date
	 * @return Long类型
	 * @throws ParseException 
	 */
	public static Date strToDate(String dateStr) throws ParseException
	 {
		if(CommonUtils.isBlank(dateStr)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Date date = sdf.parse(dateStr);
		return date;
	 }
	/**
	 * URL编码 ，转换成UTF-8
	 * @param src
	 * @return 
	 */
	public static String urlEncode(String src) {  
	    try {  
	        return URLEncoder.encode(src, "UTF-8");  
	    } catch (Exception ex) {  
	        return src;  
	    }  
	}  
	
	/**
	 * 获取2个日期直接相差的天数 oDate-fDate
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int daysOfTwo(Date smdate, Date bdate)throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
       return Integer.parseInt(String.valueOf(between_days));   
    }
	
	/**
	 * 获取2个日期直接相差的小时 oDate-fDate
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static Long hoursOfTwo(Date fDate, Date oDate) {
		Long time = oDate.getTime() - fDate.getTime();
		Long hours = time/(1000*60*60);
		return hours;
    }
	
	/**
	 * 获取2个日期直接相差的分钟 oDate-fDate
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static Long minuteOfTwo(Date fDate, Date oDate) {
		Long time = oDate.getTime() - fDate.getTime();
		Long minute = time/(1000*60);
		return minute;
    }
	
	/**
	 * 获取2个日期直接的时间差，xx天后，xx小时后，xx分钟后
	 * @param fDate
	 * @param oDate
	 * @return
	 * @throws ParseException 
	 */
	public static String dateOfTwo(Date fDate, Date oDate) throws ParseException {
		String result = "";
		//计算2个日期直接相差的天数
		int dayof = daysOfTwo(fDate, oDate);
		if(dayof>0){
			result = dayof+"天后";
		}else if(dayof==0){
			//天数相减等于0，算小时
			Long hours = hoursOfTwo(fDate, oDate);
			if(hours<0){
				result = "预告";
			}else if(hours==0){
				//小时相减等于0，算分钟
				Long minute = minuteOfTwo(fDate, oDate);
				if(minute>0){
					result = minute+"分钟后";
				}else{
					result = "预告";
				}
			}else{
				result = hours+"小时后";
			}
		}else{
			result = "预告";
		}
		return result;
    }
	
	/**
	 * 正则表达式验证
	 * @param regex
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static boolean regexString(String regex, String str){
		if(isBlank(str) || isBlank(regex)){
			return false;
		}
		Pattern pat = Pattern.compile(regex);
		Matcher matcher = pat.matcher(str);
		boolean flag = matcher.matches();
		return flag;
    }
}
