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
    
    <title>编辑枚举</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
	<script  type="text/javascript" src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js" ></script>
 	<script>
		
		
	</script>
  </head>
  
  <body>

	<!-- 获取提交过来的数据(1) -->
	<c:if test="${allErrors!=null }">
		<c:forEach items="${allErrors }" var="error">
			${ error.defaultMessage}<br/>
		</c:forEach>
	</c:if>
	<div class="container-full">
		<div class="row">
			<div class="col-md-12 mt10">
				<form:form action="enum/editEnum.do"  name="enumForm" method="post" class="form-horizontal">
					<input type="hidden" name="_method" value="put"/>
					<div class="form-group">
						<label class="col-md-1 control-label">字段完整名称</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="enumFullName" value="${enumVOList.enumFullName }" placeholder="请输入枚举全程名称"/><form:errors path="enumFullName"></form:errors>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">枚举健</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="enumCode" value="${enumVOList.enumCode }" placeholder="请输入枚举健"/><form:errors path="enumCode"></form:errors>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">枚举值</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="enumDesc" value="${enumVOList.enumDesc }" placeholder="请输入枚举值"/><form:errors path="enumDesc"></form:errors>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">隶属父级枚举健</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="enumGroupCode" value="${enumVOList.enumGroupCode }" placeholder="请输入隶属父级枚举健"/><form:errors path="enumGroupCode"></form:errors>
						</div>
					</div>
					<div class="row">
						<div class="col-md-9 mt10 text-center">
							<button type="submit" class="btn btn-success">提交</button>
							<button type="button" class="btn btn-danger">返回</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>

  </body>
</html>