<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    
    <title>注册页面</title>
    
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
  	//实时校验账户名是否已注册
  	function validateInfo(tips){
  		var customerPhone = "";
  		var customerMail = "";
  		if(tips=="phoneNoTips"){
  			customerPhone = $("[name='customerPhoneNo']").val();
  		}else if(tips=="emailTips"){
  			customerMail = $("[name='customerEmail']").val();
  		}
  		$.ajax({
  			url:"register/validate.do?r="+Math.random(),
  			contentType : 'application/json',
  			type:'GET',
  			data:{
  				'customerEmail' : customerMail,
  				'customerPhoneNo' : customerPhone
  			},
			success:function(data){
				//如果该用户已存在，则展现出提示
				if(data=="false"){
					if(tips=="phoneNoTips"){
						$("#phoneNoTips").css("display","block");
						$("#phoneNoTips").html("号码已存在");
					}
					if(tips=="emailTips"){
						$("#emailTips").css("display","block");
						$("#emailTips").html("邮箱已存在");
					}
					return;
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
        //单选按钮的选中
    	$("[name='customerTypeR']").each(function(){
    		var customerType = $("[name='customerType']").val();
    		if(customerType!=null){
    			if(this.value == customerType){
	    			this.checked = "checked";
	    		}
    		}
    	});
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
    
    function checkCustomerType(){
    	$("[name='customerTypeR']").each(function(){
    		if(this.checked){
    			$("[name='customerType']").val(this.value);
    		}
    	});
    }
  </script>

  </head>
  
  <body onload="checkTime();">
    <form action="register.do" method="post">
    <%-- 	用户类型：<input type="radio" name="customerTypeR" onclick="checkCustomerType();" value="1" >机构投资者
	    	<input type="radio" name="customerTypeR" onclick="checkCustomerType();" value="2" >个人投资者
	    	<input type="radio" name="customerTypeR" onclick="checkCustomerType();" value="3" >企业
	    	<input type="radio" name="customerTypeR" onclick="checkCustomerType();" value="4" >券商
	    	<input type="radio" name="customerTypeR" onclick="checkCustomerType();" value="5" >律师事务所
	    	<input type="radio" name="customerTypeR" onclick="checkCustomerType();" value="6" >会计事务所
	    	<input type="radio" name="customerTypeR" onclick="checkCustomerType();" value="7" >媒体 <br>
	    	<input type="hidden" name="customerType" value="${customerVO.customerType}"/> --%>
	<%--     真实姓名：<input type="text" name="customerName" value="${customerVO.customerName}"/><br> --%>
	    手机号码：<input type="text" name="customerPhoneNo" value="${customerVO.customerPhoneNo}" onfocus="cancelValidateTips('phoneNoTips');" onblur="validateInfo('phoneNoTips');"/><label style="display: none;color: red;font-size: 12px;" id="phoneNoTips"></label><br/>
	    	<input type="text" name="phoneCode" value="${customerVO.phoneCode}" /><input id="validationCode" type="button" onclick="sendCode(this);" value="获取验证码"/>${error}<br/>
	   <%--  常用邮箱：<input type="text" name="customerEmail" value="${customerVO.customerEmail}" onfocus="cancelValidateTips('emailTips');" onblur="validateInfo('emailTips');"/><label style="display: none;color: red;font-size: 12px;" id="emailTips"></label><br/> --%>
	    创建密码：<input type="password" name="customerPassword" value="${customerVO.customerPassword}"/><br/>
	    确认密码：<input type="password" id="pwd2" onblur="validatePassword();"/><label id="pwdValidate" style="font-size: 10px;color: red;"></label><br/>
	    <%-- 公司名称：<input type="text" name="customerCompany" value="${customerVO.customerCompany}"/><br/> --%>
		<input type="submit" value="注册"/>
		<input type="hidden" name="method" id="tt" value="register"/>
    </form>
  </body>
</html>
