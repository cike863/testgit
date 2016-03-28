<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
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
    
    <title>权限管理</title>
    
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
					 <%--  <div class="input-group">
						  <span class="input-group-addon">查询条件</span>
						  <input type="text" class="form-control"  name="staffPhoneNo" value="${staff.staffPhoneNo}"  placeholder="手机号码">
						  <input type="text" class="form-control"  name="staffEmail" value="${staff.staffEmail}"  placeholder="电子邮箱">
					  </div><!-- onclick="queryCustomerList();" -->
					  <button type="submit" class="btn btn-warning"  >查询</button>
					  <button type="button" class="btn btn-danger" onclick="toAddstaff();">新建</button> --%>
			  </div>
		  </div>
		  <div class="row">
			  <div class="col-md-12 mt10">
			  	  <input type="hidden" name="staffId" value="${staffId }"/>
				  <table class="table table-bordered table-hover table-responsive">
					  <thead>
					  <tr>
					  	  <th><input type="checkbox" name="allCheckBox"  id="allCheckBox" onclick="checkAll(this);">全选</th>
					      <th>编号</th>
						  <th>菜单名称</th>
						  <th>code值</th>
					  </tr>
					  </thead>
					  <tbody>
						<c:forEach items="${roleList}" var="role" varStatus="st">
							<tr>
								<td><input type="checkbox" name="roleCheckBox" value="${st.index }"/></td>
								<td>${role.enumDesc}</td>
								<td>${role.enumDescCn}</td>
								<td id="code_${st.index }">${role.enumCode}</td>
							</tr>
						</c:forEach>
					  </tbody>
				  </table>
			  </div>
			  <div align="center">
			  	<input type="button" onclick="isOk();" value="确定"/>
			  </div>
		  </div>
	  </div>
  </body>
  <script type="text/javascript">
  
		//获取窗口索引
		var index = parent.layer.getFrameIndex(window.name); 
	
	  	function isOk(){
	  		//勾选的栏目对应的code值相加得出role值
	  		var role = 0;
	  		$("input[name='roleCheckBox']:checked").each(function(){ 
	  			role += $("#code_"+$(this).val()).html()+",";
	  		});
	  		role=role.toString().substring(1, role.toString().length-1);
	  		//console.log(role);
	  		//赋权限
	  		var staffId = $("[name='staffId']").val();
	  		$.ajax({
	  			url:"admin/role/changeStaffRole?r="+Math.random(),
	  			contentType : 'application/json',
	  			type:'GET',
	  			data:{
	  				'staffId' : staffId,
	  				'staffRole' : role
	  			},
				success:function(data){
					//如果该用户已存在，则展现出提示
					if(data=="1"){
						alert("权限赋予成功！");
						parent.layer.close(index);
					}else{
						alert("权限赋予失败！");
					}
				}
	  		});
	  	}
	  	function checkAll(k){
	  		if($(k).is(':checked')){
	  			$("input[name='roleCheckBox']").each(function(){	
					$(this).prop("checked",true);
				});
	  		}else{
	  			$("input[name='roleCheckBox']").each(function(){	
	  				$(this).prop("checked",false);
				});
	  		}
	  		
	  	}
	  	window.onload = function(){
	  		$("input[name='roleCheckBox']").each(function(){
				if(${checkedCode}.indexOf(parseInt($(this).parent().next().next().next()[0].innerHTML))>-1){
					$(this).attr("checked","checked");
				}				
			});
	  	}
	  	
  </script>
</html>
