<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    
    <title>用户列表</title>
    
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
    <script  type="text/javascript" src="<%=request.getContextPath()%>/common/dtpicker/js/laydate.js" ></script>
	<style>
		.mt10{margin-top:10px;}
		a{text-decoration:underline;}
		a:after{content:"";margin-right:5px;}
	</style>
  </head>
 
  <body>
	${error}
	<div class="container-full">
		<div class="row">
			<div class="col-md-12 mt10">
				<form class="form-inline" name="customerForm" id="customerForm"
					action="${pageContext.request.contextPath }/admin/customer/toAdminCustomer.do">
					<div class="input-group">
						<span class="input-group-addon">手机号码</span> <input type="text"
							class="form-control" id="customerPhoneNo" name="customerPhoneNo"
							placeholder="手机号码" 
							value="${(customer.customerPhoneNo == '')?'':customer.customerPhoneNo}">
					</div>
					<div class="input-group">
						<span class="input-group-addon">电子邮箱</span> <input type="text"
							class="form-control" id="customerEmail" name="customerEmail"
							placeholder="电子邮箱"
							value="${(customer.customerEmail == '')?'':customer.customerEmail}">
					</div>
					<div class="input-group">
						<span class="input-group-addon">公司名称</span> <input type="text"
							class="form-control" id="customerCompany" name="customerCompany"
							placeholder="公司名称"
							value="${(customer.customerCompany == '')?'':customer.customerCompany}">
					</div>
					<div class="input-group">
						<span class="input-group-addon">身份类型</span> <input type="text"
							class="form-control" id="customerType" name="customerType"
							placeholder="身份类型" 
							value="${(customer.customerType == '')?'':customer.customerType}">
					</div>
					<div class="input-group">
						<span class="input-group-addon">姓名</span> <input type="text"
							class="form-control" id="customerName" name="customerName"
							placeholder="姓名" 
							value="${(customer.customerName == '')?'':customer.customerName}">
					</div>
					<br/>
					<div class="input-group">
						<span class="input-group-addon">注册时间</span> 
						<input placeholder="请输入开始日期" class="laydate-icon" name="sartQueryDate" id="t1" style="width:130px;height:30px" 
						value="<fmt:formatDate value="${customer.sartQueryDate}" pattern="yyyy-MM-dd" />" 
						onClick="laydate({elem: '#t1',istime: true, format: 'YYYY-MM-DD'})">~
						<input placeholder="请输入结束日期" class="laydate-icon" name="endQueryDate" id="t2" style="width:130px;height:30px" 
						value="<fmt:formatDate value="${customer.endQueryDate}" pattern="yyyy-MM-dd" />"
						 onClick="laydate({elem: '#t2',istime: true, format: 'YYYY-MM-DD'})">
					</div>
					<input type="hidden" name="_method" value="get" /> <input
						type="hidden" name="projShowStatus" value="0">
						<input type="hidden" name="pageSize" id="pageSize" value="${customer.pageSize}" />
					<button onclick="excelOutport()" class="btn btn-success">excel导出</button> 
					<button type="submit" class="btn btn-warning"  >查询</button>
					<button type="button" class="btn btn-danger"
						onclick="toAddCustomer();">新建</button>
				</form>
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
								<td><fmt:formatDate value="${nVO.createDate}"
										pattern="yyyy-MM-dd" /></td>
								<td>
									<%-- <a href="javascript:void(0);" onclick="toEditEtlSchedule('${eVO.taskTable}');">edit</a><br/> --%>
									<a
									href="${pageContext.request.contextPath }/admin/customer/toEditCustomer.do?userNo=${nVO.customerId}">编辑</a>
									| <a
									href="${pageContext.request.contextPath }/admin/customer/deleteCustomer.do?userNo=${nVO.customerId}">删除</a>

								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<page:pager pageSize="${customer.pageSize}"
			pageNo="${customer.pageNo}" url="admin/customer/toAdminCustomer.do"
			recordCount="${customer.totalRecord}" />
	</div>
</body>
  <script type="text/javascript">
	  	/* function toEditEtlSchedule(taskTable){
			//提交form
			document.etlForm.action="admin/etlschedule/toEditEtlSchedule.do";
			document.etlForm.submit();
		} */
  		function queryCustomerList(){
  			//提交form
  			document.customerForm.action="admin/customer/toAdmincustomer.do";
  			document.customerForm.submit();
  		}
  		function toAddCustomer(){
  			//提交form
  			document.customerForm.action="admin/customer/toAddCustomer.do";
  			document.customerForm.submit();
  		}
  		function excelOutport(){
  			var isCheck=false;
  			$.ajax({
  				url:"admin/toCheckCustomerExcel.do",
  				//contentType : 'application/json',
  	  			type:"GET",
  	  			async: false,
  	  			data:$('#customerForm').serialize(),
  				success:function(data){
  					if(data.resultCode=='1'){
  						isCheck=true;
  					}else if(data.resultCode=='60003'){
  						alert("数据超过"+data.excelCount+"条，请重新选择条件");
  					}
  				},
  				error:function(){
  					alert("数据导出失败");
  				} 
  			});
  			if(isCheck){
  				var param=$('#customerForm').serialize();
  				parent.location.href="admin/toCustomerExcel.do?"+param;
  			}
  		}
  </script>
</html>
