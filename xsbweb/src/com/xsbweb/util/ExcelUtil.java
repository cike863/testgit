package com.xsbweb.util;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.xsbweb.vo.Customer;
import com.xsbweb.vo.extend.CustomerVO;
import com.xsbweb.vo.extend.EnumVO;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelUtil {
	/**
     * 查询指定目录中电子表格中所有的数据
     * @param file 文件完整路径
     * @return
     */
	private static Logger logger = Logger.getLogger(ExcelUtil.class);

    public  static List<CustomerVO> getAllByExcel(String file,
    		List<EnumVO> gzIndustryEnumList,
    		List<EnumVO> capitalScaleEnumList,
    		List<EnumVO> customerTypeEnumList ) {
        List<CustomerVO> list=new ArrayList<CustomerVO>();
        InputStream inputStream = null;
        try {
        	
        	URL url = new URL(file);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            connection.setConnectTimeout(3000);
            inputStream = connection.getInputStream();
            Workbook rwb=Workbook.getWorkbook(inputStream);
            Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
			//System.out.println(clos + " rows:" + rows);
			for (int i = 1; i < rows; i++) {
				// 第一个是列数，第二个是行数
				int j = 0;
				CustomerVO customer = new CustomerVO();
				// 默认最左边编号也算一列 所以这里得j++
				String customerEmail = rs.getCell(j++, i).getContents();// 邮箱
				String customerPhoneNo = rs.getCell(j++, i).getContents();// 手机
				String customerName = rs.getCell(j++, i).getContents();// 姓名
				String customerCompany = rs.getCell(j++, i).getContents();// 公司
				// String customerCompany=rs.getCell(j++,
				// i).getContents();//拟投金额 暂无字段
				//j++;
				String customerPosition = rs.getCell(j++, i).getContents();// 职位
				String gzIndustrys = rs.getCell(j++, i).getContents();// 投资领域、关注行业
				String capitalScale = rs.getCell(j++, i).getContents();// 基金规模
				String customerCases = rs.getCell(j++, i).getContents();// 投资案例
				//String gzIndustry=rs.getCell(j++, i).getContents();//来源 暂无字段
				//j++;
				String customerType = rs.getCell(j++, i).getContents();// 用户类型
				String createDate = rs.getCell(j++, i).getContents();// 报名时间
				if("".equals(customerName)||"".equals(customerPhoneNo)){
					continue;
				}
				customer.setCustomerEmail(customerEmail);
				customer.setCustomerPhoneNo(customerPhoneNo);
				customer.setCustomerName(customerName);
				customer.setCustomerPosition(customerPosition);
				customer.setGzIndustry((long) getGzIndustry(gzIndustrys,gzIndustryEnumList));
				customer.setCapitalScale(getCapitalScale(capitalScale,capitalScaleEnumList));
				//customer.setCustomerType(customerType);
				customer.setCustomerType(getCustomerType(customerType,customerTypeEnumList));
				customer.setCustomerCompany(customerCompany);
				customer.setCustomerCases(customerCases);
				if("".equals(createDate)){
					customer.setCreateDate(new Date());
				}else{
						try {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							customer.setCreateDate(sdf.parse(createDate));
						} catch (ParseException e) {
							customer.setCreateDate(new Date());
							logger.info("日期转换异常，用户createDate设置为当前系统时间");
							e.printStackTrace();
						}	
				}
				list.add(customer);
			}
        } catch (BiffException e) {
        	new RuntimeException(e);
        }  catch (IOException e) {
                // TODO Auto-generated catch block
        		logger.info("IOException");
                e.printStackTrace();               
        }finally{
        	if(inputStream!=null){
        		try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
        return list;
    }

	public static void  toExcel(List<Customer> list ,
			HttpServletResponse response,
			String tableName,
			List<EnumVO> gzIndustryEnumList,
			List<EnumVO> capitalScaleEnumList,
			List<EnumVO> customerTypeEnumList) throws IOException{
    	logger.info("getexcel start");  
    	WritableWorkbook wwb = null; 
    	OutputStream os = null; 
    	/*PrintWriter out = response.getWriter();*/
        try {            
        	os = response.getOutputStream();  
            wwb = Workbook.createWorkbook(os);  
            response.setContentType("application/dowload");  
            response.setHeader("Content-disposition", "attachment;filename=\""  
                    + new String((java.net.URLEncoder.encode(tableName+".xls",  "UTF-8")).getBytes("UTF-8"), "GB2312") + "\"");  
               // 创建工作表
               WritableSheet ws = wwb.createSheet(tableName, 0);  
               
            // 内容(居中)单元格样式
               WritableFont wf0 = new WritableFont(WritableFont.createFont("微软雅黑"), 12,WritableFont.NO_BOLD,false); // 定义格式 字体 下划线 斜体 粗体 颜色
               WritableCellFormat contentStyle = new WritableCellFormat(wf0); //
               contentStyle.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
               contentStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); // 设置垂直对齐方式
               contentStyle.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); // 设置边框
               // 一级标题单元格样式
               WritableFont wf1 = new WritableFont(WritableFont.createFont("微软雅黑"), 15,WritableFont.BOLD, false); // 定义格式 字体 下划线 斜体 粗体 颜色
               WritableCellFormat titleStyle1 = new WritableCellFormat(wf1); //
               titleStyle1.setBackground(jxl.format.Colour.GREEN); // 设置单元格的背景颜色
               titleStyle1.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
               titleStyle1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); // 设置边框
               // 二级标题单元格样式
               WritableFont wf2 = new WritableFont(WritableFont.createFont("微软雅黑"), 11,WritableFont.BOLD, false); // 定义格式 字体 下划线 斜体 粗体 颜色
               WritableCellFormat titleStyle2 = new WritableCellFormat(wf2); //
               titleStyle2.setBackground(jxl.format.Colour.GREY_25_PERCENT); // 设置单元格的背景颜色
               titleStyle2.setAlignment(jxl.format.Alignment.CENTRE); // 设置水平对齐方式
               titleStyle2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); // 设置垂直对齐方式
               titleStyle2.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); // 设置边框
               
               WritableCellFormat titleStyle3 = new WritableCellFormat(wf1); //
               titleStyle3.setBackground(jxl.format.Colour.BRIGHT_GREEN); // 设置单元格的背景颜色
               titleStyle3.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
               titleStyle3.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); // 设置边框
               
               
               //要插入到的Excel表格的行号，默认从0开始
               int i=0;
               ws.addCell(new Label(i++, 0, "序号",titleStyle1)); 
               ws.addCell(new Label(i++, 0, "邮箱",titleStyle1)); 
        	   ws.addCell(new Label(i++, 0, "手机",titleStyle1));
        	   ws.addCell(new Label(i++, 0, "姓名",titleStyle1));
        	   //i++;
        	   ws.addCell(new Label(i++, 0, "公司",titleStyle1));
        	   ws.addCell(new Label(i++, 0, "职位",titleStyle1));
        	   ws.addCell(new Label(i++, 0, "投资领域",titleStyle1));
        	   ws.addCell(new Label(i++, 0, "基金规模",titleStyle1));
        	   ws.addCell(new Label(i++, 0, "投资案例",titleStyle1));
        	   
        	   //i++;
        	   ws.addCell(new Label(i++, 0, "用户类型",titleStyle1));
        	   if("用户详情列表".equals(tableName)){
        		   ws.addCell(new Label(i++, 0, "注册时间",titleStyle1));
        	   }else {
        		   ws.addCell(new Label(i++, 0, "报名时间",titleStyle1));
        	   }
        	   i=0;
        	   ws.setColumnView(i++, (int)(10));
        	   ws.setColumnView(i++, (int)(40));
        	   ws.setColumnView(i++, (int)(20));
        	   ws.setColumnView(i++, (int)(15));
        	   
        	   ws.setColumnView(i++, (int)(60));
        	   ws.setColumnView(i++, (int)(20));
        	   ws.setColumnView(i++, (int)(40));
        	   ws.setColumnView(i++, (int)(15));
        	   ws.setColumnView(i++, (int)(40));
        	  	   
        	   ws.setColumnView(i++, (int)(20));
        	   ws.setColumnView(i++, (int)(30));
        	   
        	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               for (int j = 0; j < list.size(); j++) {
            	   i=0;
            	   ws.addCell(new Label(i++, j+1, j+1+"",contentStyle));
            	   ws.addCell(new Label(i++, j+1, list.get(j).getCustomerEmail(),contentStyle));
            	   ws.addCell(new Label(i++, j+1, list.get(j).getCustomerPhoneNo(),contentStyle));
            	   ws.addCell(new Label(i++, j+1, list.get(j).getCustomerName(),contentStyle));
            	   //i++;
            	   ws.addCell(new Label(i++, j+1, list.get(j).getCustomerCompany(),contentStyle));
            	   ws.addCell(new Label(i++, j+1, list.get(j).getCustomerPosition(),contentStyle));
            	   ws.addCell(new Label(i++, j+1, toGzIndustrys(list.get(j).getGzIndustry()+"",gzIndustryEnumList),contentStyle));
            	   ws.addCell(new Label(i++, j+1, toCapitalScale(list.get(j).getCapitalScale(),capitalScaleEnumList),contentStyle));  
            	   ws.addCell(new Label(i++, j+1, list.get(j).getCustomerCases(),contentStyle));
            	   
            	   //i++;
            	   //ws.addCell(new Label(i++, j+1, list.get(j).getCustomerType(),contentStyle));
            	   ws.addCell(new Label(i++, j+1, toCustomerType(list.get(j).getCustomerType(),customerTypeEnumList),contentStyle));  
            	   ws.addCell(new Label(i++, j+1, sdf.format(list.get(j).getCreateDate()),contentStyle));
               }
             
              //写进文档
               wwb.setProtected(false);  
               wwb.write();
               /*response.reset();*/
               /*String fileName ="项目报名列表.xls";
               File file = new File(fileName);
               response.reset();
               response.setHeader("Content-disposition", "attachment;filename=\""  
                       + new String((java.net.URLEncoder.encode("项目报名列表.xls",  "UTF-8")).getBytes("UTF-8"), "GB2312") + "\"");  
               response.setContentLength((int)file.length());
               FileInputStream fis = new FileInputStream(fileName);
               BufferedInputStream bis = new BufferedInputStream(fis);
               byte[] bt = new byte[1024];
               long l =0;
               ServletOutputStream out = response.getOutputStream();
               while(l<file.length()){
            	   int j = bis.read(bt,0,1024);
            	   l+=j;
            	   out.write(bt,0,j);
               }
               bis.close();
               out.close();*/
             /*  os.close();  */
              // 关闭Excel工作簿对象
        } catch (Exception e) {
        	logger.error("jftj/genexcel Exception", e);  
        }finally{
        	if(wwb!=null){
        		try {
					wwb.close();
				} catch (WriteException e) {
					logger.error("jftj/genexcel WriteException", e);  
				}
        	}
        	if (os != null) {
                os.close();  
            }      	
        	logger.info("getexcel end");  
        }
    }
   /* public static void main(String[] args){
    	//List<CustomerVO>  customers = getAllByExcel("C:/Users/Administrator/Downloads/20151216174436_829.xls");
    	//List<CustomerVO>  customers = getAllByExcel("http://192.168.1.179:8080//dyly//File//20151216//20151216174436_829.xls");
    	
    	//System.out.println(customers);
    }*/
    /**
     * gz行业转换
     * @param gzIndustrys
     * @param gzIndustryEnumList
     * @return
     */
    public static int getGzIndustry(String gzIndustrys,List<EnumVO> gzIndustryEnumList){
    	int gzIndustry=0;
    	for(EnumVO enumVO:gzIndustryEnumList){
    		if(gzIndustrys.contains(enumVO.getEnumDesc())){	
            	gzIndustry+=Integer.parseInt(enumVO.getEnumCode());
            }
    	}
    	return gzIndustry;
    }
    /**
     * 资金规模转换
     * @param capitalScale
     * @param capitalScaleEnumList
     * @return
     */
    public static String getCapitalScale(String capitalScale,List<EnumVO>capitalScaleEnumList){
    	String customerCapitalScale="";
    	boolean isMatch = false;
    	for(EnumVO enumVO:capitalScaleEnumList){
    		if(enumVO.getEnumDesc().equals(capitalScale)){
    			customerCapitalScale=enumVO.getEnumCode();
    			isMatch = true;
        		break;
        	}
    	}
    	if(!isMatch){
    		customerCapitalScale=capitalScale;
    	}
		return customerCapitalScale;
    }
    /**
     * 用户类型转换
     * @param customerType
     * @param customerTypeEnumList
     * @return
     */
    public static String getCustomerType(String customerType,
			List<EnumVO> customerTypeEnumList) {
    	String type="";
    	boolean isMatch = false;
    	for(EnumVO enumVO:customerTypeEnumList){
    		if(enumVO.getEnumDesc().equals(customerType)){
    			type=enumVO.getEnumCode();
    			isMatch = true;
        		break;
        	}
    	}
    	if(!isMatch){
    		type=customerType;
    	}
		return type;
	}
    /**
     * 用户类型回显
     * @param type
     * @param customerTypeEnumList
     * @return
     */
    public static String toCustomerType(String type,List<EnumVO>customerTypeEnumList){
    	String customerType="";
    	boolean isMatch = false;
    	for(EnumVO enumVO:customerTypeEnumList){
    		if(enumVO.getEnumCode().equals(type)){
    			customerType = enumVO.getEnumDesc();
    			isMatch = true;
        		break;
        	}
    	}
    	if(!isMatch){
    		customerType=type;
    	}
		return customerType;
    }
    /**
     * 资金规模回显
     * @param capitalScale
     * @param capitalScaleEnumList
     * @return
     */
    public static String toCapitalScale(String capitalScale,List<EnumVO>capitalScaleEnumList){
    	String customerCapitalScale="";
    	boolean isMatch = false;
    	for(EnumVO enumVO:capitalScaleEnumList){
    		if(enumVO.getEnumCode().equals(capitalScale)){
    			customerCapitalScale = enumVO.getEnumDesc();
    			isMatch = true;
        		break;
        	}
    	}
    	if(!isMatch){
    		customerCapitalScale=capitalScale;
    	}
		return customerCapitalScale;
    }
    /**
     * 关注行业回显
     * @param gzIndustry
     * @return
     */
    public static String toGzIndustrys(String gzIndustry,List<EnumVO> gzIndustryEnumList){
    	StringBuffer gzIndustrys = new StringBuffer();
    	gzIndustrys.append("");
    	if(gzIndustry==null||"".equals(gzIndustry)||"null".equals(gzIndustry)||"0".equals(gzIndustry)){
    		return "";
    	}
    	List<Long> arry = MathUtil.Fun(Long.parseLong(gzIndustry));
    	for(int i=0;i<arry.size();i++){
    		for(EnumVO enumVO:gzIndustryEnumList){
    			if(arry.get(i)==Integer.parseInt(enumVO.getEnumCode()) ){
    				gzIndustrys.append(enumVO.getEnumDesc()+",");
    			}
    		}
    	}
    	if("".equals(gzIndustrys)){
    		return gzIndustry;
    	}else{
    		return gzIndustrys.substring(0, gzIndustrys.length()-1).toString();
    	}   	
    }
    public static void main(String[] args) throws ParseException {
    	//String ss =getGzIndustrys("1041");
    	//System.out.println(ss);
    	String createDate = "205-2-1";
    	Date date1=null;
    	if("".equals(createDate)){
    		date1=new Date();
		}else{
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					date1=sdf.parse(createDate);
				} catch (ParseException e) {
					date1=new Date();
					e.printStackTrace();
				}	
		}
    	System.out.println(date1);
	}
}
