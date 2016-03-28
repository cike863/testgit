<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>/css/login.css" rel="stylesheet" type="text/css" />
	<script  type="text/javascript" src="<%=request.getContextPath()%>/js/utils/jquery-1.11.3.js" ></script>
	<script type="text/javascript">
		function toRegister(){
			window.location="toRegister.do";
		}
		function toStaff(){
			window.location="staffLogin.do";
		}
	</script>
  </head>
  
  <body>
  	<form id="login" action="login.do" method="post">
  		<h1>登录</h1>
        <fieldset id="inputs">
            <input id="username" placeholder="Username" name="customerUname" autofocus="" required="" type="text"><br/><label style="font-size: 10px;color: red;">${error }</label>
            <input id="password" placeholder="Password" name="customerPassword" required="" type="password">
        </fieldset>
        <fieldset id="actions">
            <input id="submit" value="登录" type="submit" >
            <input id="submit" value="内部用户" type="button" onclick="toStaff();">
            <input type="hidden" name="method" value="login" />
            <a href="toForgetPwd.do">忘记密码?</a><a href="javascript:void(0);" onclick="toRegister();">注册</a>
        </fieldset>
	</form>
  </body>
</html>
