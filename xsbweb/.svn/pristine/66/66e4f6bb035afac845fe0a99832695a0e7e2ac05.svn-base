<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.xsb.com/xsbweb/tag/pager" prefix="page"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
    <script  type="text/javascript" src="<%=path%>/common/dtpicker/js/laydate.js" ></script>
    <script  type="text/javascript" src="<%=path%>/common/layer/layer.js" ></script>
	<style>
		.mt10{margin-top:10px;}
		a{text-decoration:underline;}
		a:after{content:"";margin-right:5px;}
	</style>
  </head>
 
  <body>
  ${error}
	  <div class="container-full">
		  <form class="form-inline" name="customerForm" action="${pageContext.request.contextPath }/admin/comment" >
		  <div class="row">
			  <div class="col-md-12 mt10">					  
					  <div class="input-group">
                		<span class="input-group-addon">手机号码</span>
                		<input type="text" class="form-control" id="customerPhoneNo" name="customerPhoneNo" placeholder="请输入查询内容" value="${(customer.customerPhoneNo == '')?'':customer.customerPhoneNo}" >
                	</div>
               		<div class="input-group">
                		<span class="input-group-addon">客户姓名</span>
                		<input type="text" class="form-control" id="customerName" name="customerName" placeholder="请输入查询内容" value="${(customer.customerName == '')?'':customer.customerName}" >
                	</div>
                	<div class="input-group">
                		<span class="input-group-addon">公司名称</span>
                		<input type="text" class="form-control" id="customerCompany" name="customerCompany" placeholder="请输入查询内容" value="${(customer.customerCompany == '')?'':customer.customerCompany}" >
                	</div>
                	<div class="input-group">
                		<span class="input-group-addon">身份类型</span>
                		<input type="text" class="form-control" id="customerType" name="customerType" placeholder="请输入查询内容" value="${(customer.customerType == '')?'':customer.customerType}" >
                	</div>
                	<input type="hidden" name="_method" value="get" /> 
                	<input type="hidden" name="projectNo" value="${projectNo }" />
                	<input type="hidden" id ="projName" name="projName" value="${projName }" />
					<button type="submit" class="btn btn-warning"  >查询</button>
			  </div>
		  </div>
		  <div class="row">
			  <div class="col-md-12 mt10">
				  <table class="table table-bordered table-hover table-responsive">
					  <thead>
					  <tr>
					      <th>编号</th>
						  <th>姓名</th>
						  <th>手机</th>
						  <th>邮箱</th>
						  <th>公司</th>
						  <th>职位</th>
						  <th>类型</th>
						  <th>注册时间</th>
						  <th>opreation</th>
					  </tr>
					  </thead>
					  <tbody>
						<c:forEach items="${customerList}" var="nVO">
							<tr>
							<td>${nVO.customerId}</td>
							<td>${nVO.customerName}</td>
							<td>${nVO.customerPhoneNo}</td>
							<td>${nVO.customerEmail}</td>
							<td>${nVO.customerCompany}</td>
							<td>${nVO.customerPosition}</td>
							<td>${nVO.customerType}</td>
							<td>${nVO.createDate}</td>
							<td>
							<%-- <a href="javascript:void(0);" onclick="toEditEtlSchedule('${eVO.taskTable}');">edit</a><br/> --%>
							<input type="button" onclick="toViewDetInformMes('${nVO.customerId}','${projectNo}');" value="查看详细留言信息"/>
							<%-- <a href="<%=path%>/admin/comment/getViewDetInformMes?customerId=&?projectNo="></a> --%>
							</td>
							</tr>
						</c:forEach>
					  </tbody>
				  </table>
			  </div>
		  </div>
	   	</form>
	   	<page:pager pageSize="${customer.pageSize}" pageNo="${customer.pageNo}" url="/admin/comment?projectNo=${projectNo }" recordCount="${customer.totalRecord}"/>
	  </div>
  </body>
  <script type="text/javascript">
  		function toViewDetInformMes(customerId,projectNo){
			var url = "<%=path%>/admin/comment/getViewDetInformMes?customerId="+customerId+"&projectNo="+projectNo;
			layer.open({
			    type: 2,
			    area: ['800px', '530px'],
			    fix: false, //不固定
			    maxmin: true,
			    content: url
			});
		}
  		function getProjName(){
  			return $("#projName").val();
  		}
  </script>
</html>
