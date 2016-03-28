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
    
    <title>用户认证列表</title>
    
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
		  
		  <%-- <div class="row">
			  <div class="col-md-12 mt10">
					  <div class="input-group">
						  <span class="input-group-addon">查询条件</span>
						  <input type="text" class="form-control"  name="customerPhoneNo" value="${customer.customerPhoneNo}"  placeholder="手机号码">
						  <input type="text" class="form-control"  name="customerEmail" value="${customer.customerEmail}"  placeholder="电子邮箱">
						  <input type="text" class="form-control"  name="customerCompany" value="${customer.customerCompany}"  placeholder="公司名称">
						  <input type="text" class="form-control"  name="customerType" value="${customer.customerType}"  placeholder="身份类型">
						  <!--  <input placeholder="请输入开始日期" class="form-control" name="customer.createDate" value="${news.newsDate }" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
					      <input placeholder="请输入结束日期" class="form-control" name="customer.createDate" value="${news.newsDate }" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">-->
					  </div><!-- onclick="queryCustomerList();" -->
					  <button type="submit" class="btn btn-warning"  >查询</button>
					  <button type="button" class="btn btn-danger" onclick="toAddCustomer();">新建</button>
			  </div>
			  
		  </div> --%>
		  
		  <div class="row">
			<div class="col-md-12 mt10">
				<form class="form-inline" name="customerForm"
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
					<input type="radio"  name="authentStatus" value="1" id="toBeCertified">待认证
					<input type="radio"  name="authentStatus" value="2"  id="certified">已认证通过
					<input type="radio"  name="authentStatus" value="3"  id="failedCertified">未认证通过
					<input type="hidden" name="_method" value="get" /> 
					<button type="submit" class="btn btn-warning">查询</button>
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
							<td><fmt:formatDate value="${nVO.createDate}" pattern="yyyy-MM-dd"/></td>
							<td>
							<%-- <a href="javascript:void(0);" onclick="toEditEtlSchedule('${eVO.taskTable}');">edit</a><br/> --%>
							<c:if test="${customer.authentStatus=='1' }">
								<a href="${pageContext.request.contextPath }/admin/customer/toEditCustomer.do?userNo=${nVO.customerId}">审核</a>
							</c:if>
							
							<%-- | <a href="${pageContext.request.contextPath }/admin/customer/deleteCustomer.do?userNo=${nVO.customerId}">删除</a> --%>
							
							</td>
							</tr>
						</c:forEach>
					  </tbody>
				  </table>
			  </div>
		  </div>
	   	</form>
	   	<page:pager pageSize="${customer.pageSize}" pageNo="${customer.pageNo}" url="admin/customer/toAdminCustomer.do" recordCount="${customer.totalRecord}"/>
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
  		window.onload = function(){
			if(${customer.authentStatus=='1'}){
				$("#toBeCertified").attr("checked","checked");
			}
			if(${customer.authentStatus=='2'}){
				$("#certified").attr("checked","checked");
			}
			if(${customer.authentStatus=='3'}){
				$("#failedCertified").attr("checked","checked");
			}
  		}
  </script>
</html>
