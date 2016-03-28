<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>忘记密码</title>
    
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
	  	//提交密码修改
	  	function submitUpdatePwd(){
	  		if(!validate()){
	  			return;
	  		}
	  		var customerPhoneNo = $("[name='customerPhoneNo']").val();
	  		var phoneCode = $("[name='phoneCode']").val();
	  		var customerPassword = $("[name='customerPassword']").val();
	  		$.ajax({
	  			url:"login/forgetPwd.do?r="+Math.random(),
	  			contentType : 'application/json',
	  			type:'GET',
	  			data:{
	  				'customerPhoneNo' : customerPhoneNo,
	  				'phoneCode' : phoneCode,
	  				'customerPassword' : customerPassword
	  			},
				success:function(data){
					if(data.resultCode==90001){
						alert("验证码超时！");
					}else if(data.resultCode==90002){
						alert("验证码错误！");
					}else if(data.resultCode==1){
						alert("修改成功！");
					}else if(data.resultCode==0){
						alert("修改失败！");
					}else if(data.resultCode==90000){
						alert("不存在该手机号！");
					}
				}
	  		});
	  	}
	  	
	  	function cancelValidateTips(arg){
	 		$("#"+arg).css("display","none");
			$("#"+arg).html("");
	  	}
	  	//校验前后2次输入密码时候一致
	  	function validatePassword(){
	  		var pwd2 = $("#pwd2").val();
	  		var pwd1 = $("[name='customerPassword']").val();
	  		if(pwd2!=pwd1){
	  			$("#pwdValidate").css("display","block");
	  			$("#pwdValidate").html("前后2次密码不一致");
	  		}
	  	} 
	  	//获取验证码
	  	function sendCode(){
	  		var customerPhoneNo = $("[name='customerPhoneNo']").val();
	  		if(!checkedPhoneNo(customerPhoneNo)){
	  			return;
	  		}
	  		$.ajax({
	  			url:"register/sendCode.do?r="+Math.random(),
	  			contentType : 'application/json',
	  			type:'get',
	  			data:{
	  				'customerPhoneNo' : customerPhoneNo
	  			},
	  			success:function(data){
	  				if(data=="0"){
	  					alert("手机号码不能为空!");
	  					return;
	  				}else if(data=="fail"){
	  					alert("发送失败，请稍后再试!");
	  					return;
	  				}else if(data=="success"){
	  					alert("发送成功，请注意查收!");
	  					return;
	  				}
	  			}
	  		});
	  		 // 1分钟内禁止点击  
	         for (var i = 1; i <= 60; i++) {  
	              // 1秒后显示  
	             window.setTimeout("updateTime(" + (60 - i) + ")", i * 1000);  
	         }  
	  	}
	  	
	  	function updateTime(i){  
	        // setTimeout传多个参数到function有点麻烦，只能重新获取对象  
	        var obj = document.getElementById("validationCode");  
	        if(i > 0){  
	            obj.value  = "距离下次获取还有" + i + "秒";  
	            obj.disabled = true;  
	        }else{  
	            obj.value = "获取验证码";  
	            obj.disabled = false;  
	        }  
	    }  
	    
	    function checkTime(){  
	        var sendCodeTime = <%=(Long)session.getAttribute("SEND_CODE_TIME")%>;  
	        if(sendCodeTime){  
	            var nowTime = new Date().getTime();  
	            var flag = Math.floor((nowTime - sendCodeTime)/1000);  
	            if(flag < 60){  
	                var end = 60 - flag;  
	                // 进页面马上开始，选i为0  
	                for (var i = 0; i <= end; i++) {  
	                    window.setTimeout("updateTime(" + (end - i) + ")", i * 1000);  
	                }  
	            }  
	        }  
	    } 
	    
	    function checkedPhoneNo(mobile){
	    	if(mobile==null || mobile==""){
	    		alert("请先输入正确的手机号码！");
	            return false;
	    	}
	    	var myreg = /(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;
	        if(!myreg.test(mobile))
	        {
	            alert("请输入有效的手机号码！");
	            return false;
	        }
	        return true;
	    }
	    
	    function validate(){
	    	var flag = true;
    		var customerPhoneNo = $("[name='customerPhoneNo']").val();
	  		var phoneCode = $("[name='phoneCode']").val();
	  		var customerPassword = $("[name='customerPassword']").val();
	  		if(customerPhoneNo==null|| customerPhoneNo==""){
	  			$("#phoneNoTips").css("display","block");
	  			$("#phoneNoTips").html("手机号码不能为空");
	  			flag = false;
	  		}
	  		if(customerPassword==null|| customerPassword==""){
	  			$("#pwdValidate").css("display","block");
	  			$("#pwdValidate").html("密码不能为空");
	  			flag = false;
	  		}
	  		if(phoneCode==null|| phoneCode==""){
	  			alert("验证码不能为空！");
	  			flag = false;
	  		}
	  		return flag;
	    }
  </script>
  </head>
  
  <body onload="checkTime();">
  	<form action="login/forgetPwd.do" method="post">
	    <h1>忘记密码？</h1>
	    手机号码：<input type="text" name="customerPhoneNo" value="${customerVO.customerPhoneNo}" onfocus="cancelValidateTips('phoneNoTips');" onblur="validateInfo('phoneNoTips');"/><label style="display: none;color: red;font-size: 12px;" id="phoneNoTips"></label><br/>
	    	<input type="text" name="phoneCode" value="${customerVO.phoneCode}" /><input id="validationCode" type="button" onclick="sendCode(this);" value="获取验证码"/>${error}<br/>
	    新密码：<input type="password" name="customerPassword" /><br/>
	    确认密码：<input type="password" id="pwd2" onblur="validatePassword();" onfocus="cancelValidateTips('pwdValidate');"/><label id="pwdValidate" style="display: none;font-size: 10px;color: red;"></label><br/>
		<input type="button" value="提交" onclick="submitUpdatePwd();"/>
	</form>
  </body>
</html>
