<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://ajaxanywhere.sourceforge.net/" prefix="aa"%>
<%@ taglib uri="http://java.xsb.com/xsbweb/tag/pager" prefix="page"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>项目报名列表</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .mt10{margin-top:10px;}
    </style>
</head>
<script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
 <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/aa.js"></script>
 <script  type="text/javascript" src="<%=request.getContextPath()%>/common/layer/layer.js" ></script>
 <script type="text/javascript" language="javascript">	
	ajaxAnywhere.formName="confForm";
	ajaxAnywhere.getZonesToReload = function(){
		return "confListZone";
	}
	function queryApplyList(){
		$.ajax({
			url:"admin/project/queryapplylist.do",
			type:"GET"
		});
	}
	function toUpload(mediaType,dir){
		mediaType=mediaType.replace(/\s+/g,"");
		dir=dir.replace(/\s+/g,"");
		var url = "admin/news/uploadLayer.do?mediaNo="+mediaType+"&dir="+dir;
		layer.open({
		    type: 2,
		    area: ['800px', '530px'],
		    fix: false, //不固定
		    maxmin: true,
		    content: url
		});
	}
	function uploadIsOk(mediaType,mediaNo,url,returnPath,newFileName,format,size){
		$("#theFilePath").val(url);
		excelImport();
		/* $("#meetMediaPic").attr("src",url); */
	}
	function excelImport(){
		var path = $("#theFilePath").val();
		var projectNo = $("#projectNo").val();
		var fileType=path.substring(path.lastIndexOf('.')+1);
		var array="xls,xlsx";
		if(path.length==0){
			alert("请选择excel文件");
			return;
		}
		if(array.indexOf(fileType)==-1){
			alert("请选择excel文件");
			return;
		}
		 $.ajax({
			url:"admin/uploadExcel.do?theFilePath="+path+"&projectNo="+projectNo,
			contentType : 'application/json',
  			type:'GET',
  			async: false,
			success:function(data){
				//console.log("读取返回成功");
				if(data.resultCode=='1'){
					window.location.reload();
				}
				if(data.resultCode=='0'){
					alert("数据导入失败");
				}
				if(data.resultCode=='60001'){
					alert("excel表没有数据");
				}
				if(data.resultCode=='60003'){
					alert("数据超过"+data.excelCount+"条，请重新选择条件");
				}
			},
			error:function(){
				alert("数据导入失败");
			}
		}); 
	}
	function excelOutport(){
		var projectNo=$("[name='projectNo']").val();
		if(projectNo==''|| projectNo ==null){
			alert("请选择项目");
			return;
		}
		var isCheck=false;
		$.ajax({
			url:"admin/toCheckExcel.do?projectNo="+projectNo,
			contentType : 'application/json',
  			type:"GET",
  			async: false,  
			success:function(data){
				if(data.resultCode=='1'){
					isCheck=true;
				}else if(data.resultCode=='60002'){
					alert("所选项目没有报名数据");
				}
			},
			error:function(){
				alert("数据导出失败");
			} 
		});
		if(isCheck){
				document.confForm.action="admin/toExcel.do";
		}
	}
	//全选
	function checkApplyCustomerAll(k){
  		if($(k).is(':checked')){
  			$("input[name='customerIdCheckBox']").each(function(){	
				$(this).prop("checked",true);
			});
  		}else{
  			$("input[name='customerIdCheckBox']").each(function(){	
  				$(this).prop("checked",false);
			});
  		}	
  	}
	function batchDeleteApplyCustomer(){
		if(!confirm("确定要删除该数据吗？")){
			return;
		}
  		var customerIdArrs = "";
  		var projectNo=$("[name='projectNo']").val();
  		$("input[name='customerIdCheckBox']:checked").each(function(){ 
  			customerIdArrs += $("#customerId_"+$(this).val()).html()+",";
  		});
  		customerIdArrs=customerIdArrs.toString().substring(0, customerIdArrs.toString().length-1);
  		$.ajax({
  			url : "admin/project/"+projectNo+"/"+customerIdArrs+"/del",
  			//contentType : 'application/json',JSON.stringify(
			type : 'POST',
			data : {
				'_method' : 'delete'
			},
			async: false,
			success : function(data) {
				if (data.resultCode == '1') {
					window.location.reload();
				} else {
					alert("删除失败");
				};
			},
			error : function() {
				alert("删除失败");
			}
		});
	}
 </script>
<body>
<!--<form action="meet/toMeetRoomList.do" name="confForm" method="post">-->
${error }
<div class="container-full">
    	<div class="col-md-12 mt10">
            <form class="form-inline" method="post"  name="confForm">
            <span onclick="batchDeleteApplyCustomer();"class="btn btn-danger">删除</span>
                <div class="input-group">
                	<span class="input-group-addon">项目编号</span>
                	<input type="text" class="form-control" id="projectNo" readonly="readonly"  name="projectNo" value="${(customer.objectNo == '')?'':customer.objectNo}" >
                </div>
                <input type="hidden" name="_method" value="get" /> 
                <!-- <input type="text"  name="projectName"  ><input type="button" onclick="toChooseProject()" value="项目选择"/> -->
                <input type="button" onclick="toUpload('','file');" value="选择excel文件" class="btn btn-success"/>
				<input type="hidden" id="theFilePath" name="theFilePath" value="">
                <!-- <button onclick="excelImport()"  class="btn btn-success" >excel导入</button> -->
            	<button onclick="excelOutport()" class="btn btn-success">excel导出</button>
            </form>
        </div>
    <div class="row">
        <div class="col-md-12 mt10">
        	<aa:zone name="confListZone">
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                    	<th><input type="checkbox" name="allCheckBox"  id="allCheckBox" onclick="checkApplyCustomerAll(this);" >全选</th>
                    	<th style="display:none" >用户id</th>
                    	<th>活动</th>
                    	<th>用户名称</th>
                    	<th>用户手机号</th>
                    	<th>邮箱</th>
                    	<th>微信号</th>
                    	<th>公司名称</th>
                    	<th>职称</th>
                    	<th>投资领域</th>
                    	<th>基金规模</th>
                    	<th>成功案例</th>
                        <th>报名时间</th>
                        <th>类型</th>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${custList!=null}">
	                	<c:forEach items="${custList}" var="cl" varStatus="index">
	                		<tr>
	                			<td ><input type="checkbox" name="customerIdCheckBox" value="${index.index}"></td>
	                			<td  style="display:none" id="customerId_${index.index}">${cl.customerId}</td>
	                			<td>${cl.authentStatus}</td>
	                			<td>${cl.customerName}</td>
	                			<td>${cl.customerPhoneNo}</td>
	                			<td>${cl.customerEmail}</td>
	                			<td>${cl.customerWechat}</td>
	                			<td>${cl.customerCompany}</td>
	                			<td>${cl.customerPosition}</td>
	                			<td>${cl.customerInstttCode}</td>
	                			<td>${cl.capitalScale}</td>
	                			<td>${cl.customerCases}</td>
	                			<td><fmt:formatDate value="${cl.createDate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
	                			<td>${cl.roleStatus}</td>
		                    </tr>
	                	</c:forEach>
                	</c:if>
                </tbody>
            </table>
            </aa:zone>
             <page:pager pageSize="${customer.pageSize}" pageNo="${customer.pageNo}" url="admin/queryApplyList.do" recordCount="${customer.totalRecord}"/> 
        </div>
    </div>
</div>
<!--</form>-->
</body>
 
</html>