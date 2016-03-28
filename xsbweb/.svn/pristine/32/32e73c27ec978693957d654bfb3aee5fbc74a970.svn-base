<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script  type="text/javascript" src="<%=request.getContextPath()%>/js/utils/jquery-1.11.3.js" ></script>
	<script type="text/javascript">
	</script>
  </head>
  
  <body>
   		 <c:if test="${empty customerVO.customerName }">
 			<a href="${pageContext.request.contextPath }/toLogin.do">登录</a>
 		</c:if>
 		<c:if test="${not empty customerVO.customerName }">
 			当前用户：${customerVO.customerName }，
 			<a  href="cust/toUpdatePwd.do?customerId=${customerVO.customerId }" >修改密码</a>
 			<a href="${pageContext.request.contextPath }/logout.do">退出</a>
 		</c:if>
  	<form action="">
  		<input type="hidden" name="customerId" value="${customerVO.customerId}"/>
  	</form>
  </body>
</html>
