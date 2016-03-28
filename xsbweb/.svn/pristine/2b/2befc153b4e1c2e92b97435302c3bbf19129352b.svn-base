<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
	<script type="text/javascript">
	
		function validateInfo(tips){
			var tipsName = $("[name='"+tips+"']").val();
			if(tipsName==null || tipsName.trim()==""){
				$("#"+tips).css("display","block");
				$("#"+tips).html("此项不能为空");
			}
			if(tips=="newPassword" || tips=="newPassword2" ){
				validatePassword();
			}
		}
		
		function cancelValidateTips(arg){
	 		$("#"+arg).css("display","none");
			$("#"+arg).html("");
	  	}
	  	//校验前后2次输入密码时候一致
	  	function validatePassword(){
	  		var pwd1 = $("[name='newPassword']").val();
	  		var pwd2 = $("[name='newPassword2']").val();
	  		if(pwd1!=null && pwd1.trim()!="" && pwd2!=null && pwd2.trim()!=""){
		  		if(pwd2!=pwd1){
		  			$("#newPassword2").css("display","block");
		  			$("#newPassword2").html("前后2次密码不一致");
		  		}
	  		}
	  	} 
	    function validate(){
	    	var flag = true;
	  		var customerPassword = $("[name='customerPassword']").val();
	  		var newPassword = $("[name='newPassword']").val();
	  		var newPassword2 = $("[name='newPassword2']").val();
	  		if(customerPassword==null|| customerPassword.trim()==""){
	  			$("#customerPassword").css("display","block");
	  			$("#customerPassword").html("此项不能为空");
	  			flag = false;
	  		}
	  		if(newPassword==null|| newPassword.trim()==""){
	  			$("#newPassword").css("display","block");
	  			$("#newPassword").html("此项不能为空");
	  			flag = false;
	  		}
	  		if(newPassword2==null|| newPassword2.trim()==""){
	  			$("#newPassword2").css("display","block");
	  			$("#newPassword2").html("此项不能为空");
	  			flag = false;
	  		}
	  		return flag;
	    }
	    
	    function toSubmit(){
	    	if(!validate()){
	    		return;
	    	}
	    	$.ajax({
	  			url:"cust/updatePwd.do?r="+Math.random(),
	  			contentType : 'application/json',
	  			type:'GET',
	  			data:$("#updatePwdForm").serialize(),
				success:function(data){
					alert('2')
					if(data.resultCode==80000){
						alert("旧密码有误");
						$("#customerPassword").css("display","block");
						$("#customerPassword").html("旧密码有误");
					}else if(data.resultCode==1){
						alert("修改成功！");
					}else if(data.resultCode==0){
						alert("修改失败！");
					}
				}
	  		});
	    }
	</script>
  </head>
  
  <body>
    <form action="cust/updatePwd.do" id="updatePwdForm" method="post">
    	<input type="hidden" name="customerId" value="${customerVO.customerId }"/>
    	当前密码：<input type="password" name="customerPassword" onfocus="cancelValidateTips('customerPassword');" onblur="validateInfo('customerPassword');"/><label id="customerPassword" style="display:none;color: red;font-size: 10px;"></label><br/>
    	新密码：<input type="password" name="newPassword" onfocus="cancelValidateTips('newPassword');" onblur="validateInfo('newPassword');"/><label id="newPassword" style="display:none;color: red;font-size: 10px;"></label><br/>
    	确认新密码：<input type="password" name="newPassword2" onfocus="cancelValidateTips('newPassword2');" onblur="validateInfo('newPassword2');"/><label id="newPassword2" style="display:none;color: red;font-size: 10px;"></label><br/>
    	<input type="button" value="确认" onclick="toSubmit();"/>
    </form>
  </body>
</html>
