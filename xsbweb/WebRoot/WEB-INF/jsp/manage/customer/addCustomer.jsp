<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/kindeditor/plugins/code/prettify.css" />
	<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
	<script  type="text/javascript" src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js" ></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/js/util/dtpicker.js" ></script>
	<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/plugins/code/prettify.js"></script>
  </head>
  
  <body>

	<!-- 显示错误信息 -->
	<c:if test="${allErrors!=null }">
		<c:forEach items="${allErrors }" var="error">
			${ error.defaultMessage}<br/>
		</c:forEach>
	</c:if>
	<div class="container-full">
		<div class="row">
			<div class="col-md-12 mt10">
				<form:form action="admin/customer/addCustomer.do"  name="customerForm" method="post" class="form-horizontal">

					
					<div class="form-group">
						<label class="col-md-1 control-label">姓名</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="customerName" value="${customer.customerName }" placeholder="请输入用户名"/><form:errors path="customerName"></form:errors>
						</div>
						<div class="form-group">
						<label class="col-md-1 control-label">手机号码</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="customerPhoneNo" value="${customer.customerPhoneNo }" placeholder="请输入用户手机号码"/><form:errors path="customerPhoneNo"></form:errors>
						</div>
						</div>
					</div>
	                <div class="form-group">
						<label class="col-md-1 control-label">密码</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="customerPassword" value="${customer.customerPassword }" placeholder="请设置密码"/><form:errors path="customerPassword"></form:errors>
						</div>
						<div class="form-group">
						<label class="col-md-1 control-label">类型</label>
						<div class="col-md-2">
					    <input type="text" class="form-control" id="customerType" name="customerType" value="${customer.customerType }" />
						</div>
					</div>
				    </div>
					
					<div class="form-group">
						<label class="col-md-1 control-label">公司名称</label>
						<div class="col-md-5">
					        <input type="text" class="form-control" name="customerCompany" value="${customer.customerCompany }" /><form:errors path="customerCompany"></form:errors>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-1 control-label">QQ</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="customerQq" value="${customer.customerQq }" placeholder="请输入QQ"/><form:errors path="customerQq"></form:errors>
						</div>
						<div class="form-group">
							<label class="col-md-1 control-label">微信</label>
							<div class="col-md-2">
								<input type="text" class="form-control" name="customerWechat" value="${customer.customerWechat }" /><form:errors path="customerWechat"></form:errors>
							</div>
						</div>
					</div>
						<div class="form-group">
						<label class="col-md-1 control-label">电子邮箱</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="customerEmail" value="${customer.customerEmail }" placeholder="请输入电子邮箱"/><form:errors path="customerEmail"></form:errors>
						</div>
						<div class="form-group">
							<label class="col-md-1 control-label">职位</label>
							<div class="col-md-2">
		                        <input type="text" class="form-control" name="customerPosition" value="${customer.customerPosition }" placeholder="请输入职位"/><form:errors path="customerPosition"></form:errors>
							</div>
						</div>
					
					
						<div class="form-group">
						<label class="col-md-1 control-label">昵称</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="customerNickname" value="${customer.customerNickname }" placeholder="请输入昵称"/><form:errors path="customerNickname"></form:errors>
						</div>
						<div class="form-group">
							<label class="col-md-1 control-label">性别</label>
							<div class="col-md-2">
							
							    <input  type="text" class="form-control" name="customerSex" value="${customer.customerSex }" placeholder="请输入性别"/><form:errors path="customerSex"></form:errors>
		                      
							</div>
						</div>
					</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">基金规模</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="capitalScale" value="${customer.capitalScale }" /><form:errors path="capitalScale"></form:errors>
						</div>
						<div class="form-group">
							<label class="col-md-1 control-label">关注行业</label>
							<div class="col-md-2">
								<input type="text" class="form-control" name="gzIndustry" value="${customer.gzIndustry }" /><form:errors path="gzIndustry"></form:errors>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-7 mt10 text-center">
							<button type="submit" class="btn btn-success">提交</button>
							<button type="button" class="btn btn-danger" onclick="javascript:window.location.href='admin/customer/toAdminCustomer.do'">返回</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>

  </body>
</html>
