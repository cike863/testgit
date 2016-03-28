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
    
    <title>新增栏目</title>
    
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

	<!-- 显示错误信息 -->
	<c:if test="${allErrors!=null }">
		<c:forEach items="${allErrors }" var="error">
			${ error.defaultMessage}<br/>
		</c:forEach>
	</c:if>
	<div class="container-full">
		<div class="row">
			<div class="col-md-12 mt10">
				<form:form action="admin/menu/addMenu.do"  name="menuForm" method="post" class="form-horizontal">
					<div class="form-group">
						<label class="col-md-1 control-label">栏目ID</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="itemId" value="${itemId }" placeholder="请输入栏目ID" required="required" /><form:errors path="newsTitle"></form:errors>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">栏目名称</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="itemText" value="${itemText }" placeholder="请输入栏目名称" required="required" /><form:errors path="itemText"></form:errors>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">link</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="targetLink" value="${targetLink }" placeholder="请输入link"/><form:errors path="targetLink"></form:errors>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">父栏目ID</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="itemParent" value="${itemParent }" placeholder="无父级栏目则默认为空"/><form:errors path="itemParentId"></form:errors>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">顺序</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="itemOrder" value="${itemOrder }" /><form:errors path="itemOrder"></form:errors>
						</div>
					</div>
					<div class="row">
						<div class="col-md-9 mt10 text-center">
							<input type="submit" class="btn btn-success" value="提交"/>
							<button type="button" class="btn btn-danger">返回</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>

  </body>
</html>
