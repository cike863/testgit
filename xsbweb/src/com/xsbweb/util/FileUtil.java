package com.xsbweb.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;

public class FileUtil {

	public static void readDBF(String path,String wpath) {
		Long btime = new Date().getTime();
		//System.out.println(btime);
		//String path = "C:/Users/Administrator/Desktop/NQHQ.DBF";
		 InputStream fis = null;  
		    try {  
		        // 读取文件的输入流  
		        fis = new FileInputStream(path);  
		        // 根据输入流初始化一个DBFReader实例，用来读取DBF文件信息  
		        DBFReader reader = new DBFReader(fis);  
		        reader.setCharactersetName("GBK");
		        // 调用DBFReader对实例方法得到path文件中字段的个数  
		        int fieldsCount = reader.getFieldCount();  
		        //定义DBF文件字段     
		        DBFField[] fields = new DBFField[fieldsCount]; 
		  
		        // 取出字段信息  
		        for (int i = 0; i < fieldsCount; i++) {  
		            fields[i] = reader.getField(i);
		        }  
		        List<Object[]> objlist = new ArrayList<Object[]>();
		        Object[] rowValues;  
		        // 一条条取出path文件中记录  
		        while ((rowValues = reader.nextRecord()) != null) {  
		        	objlist.add(rowValues);
		        	weiterToTxt(fields,rowValues,wpath+rowValues[0]+".txt");
		        }  
		    }  catch (FileNotFoundException e) {  
		        //log.error("解析dbf数据过程中，没有找到dbf文件");  
		        e.printStackTrace();  
		    } catch (DBFException e) {  
		       // log.error("解析dbf数据过程中，dbf文件读取异常");  
		        e.printStackTrace();  
		    }  finally {  
		        try {  
		            fis.close();  
		            Long etime = new Date().getTime();
		            //System.out.println(etime);
		            System.out.println("操作执行时间为："+(etime-btime)/1000+"秒！");
		        } catch (Exception e) {  
		        }  
		    }  
	}
	
	
	public static void weiterToTxt(DBFField[] fields, Object[] objs,String path) {
		 FileWriter writer = null;  
		    try {  
		    	String jsonStr = initToJsonStr(fields,objs);
		    	File file = new File(path);
		    	//文件是否存在
		    	if(file.isFile()){
		    		//存在，则追加
		    		 writer = new FileWriter(file, true);  
		    		 writer.write(jsonStr);      
		    	}else{
		    		//不存在，写入
		    		 writer = new FileWriter(file, false); 
		    		  writer.write("\n"+jsonStr);      
		    	}
		    }  catch (Exception e) {  
		        e.printStackTrace();  
		    }  finally {  
		        try {  
		        	writer.close();
		        } catch (Exception e) {  
		        }  
		    }  
	}
	
	/**
	 * 初始化数据为json字符串
	 * @param fields
	 * @param objs
	 * @return
	 */
	public static String initToJsonStr(DBFField[] fields, Object[] objs){
		StringBuffer sb = new StringBuffer();
		if(fields!=null && fields.length>0 && objs!=null && objs.length>0){
			sb.append("{");
			for (int i = 0; i < fields.length; i++) {
				sb.append("\"").append(fields[i].getName()).append("\"").append(":").append("\"").append(objs[i]).append("\"");
				if(i<(fields.length-1)){
					sb.append(",");
				}
			}
			sb.append("}");
		}
		return sb.toString();
	}
}
