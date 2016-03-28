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
    
    <title>Ȩ�޹���</title>
    
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
						  <span class="input-group-addon">��ѯ����</span>
						  <input type="text" class="form-control"  name="staffPhoneNo" value="${staff.staffPhoneNo}"  placeholder="�ֻ�����">
						  <input type="text" class="form-control"  name="staffEmail" value="${staff.staffEmail}"  placeholder="��������">
					  </div><!-- onclick="queryCustomerList();" -->
					  <button type="submit" class="btn btn-warning"  >��ѯ</button>
					  <button type="button" class="btn btn-danger" onclick="toAddstaff();">�½�</button> --%>
			  </div>
		  </div>
		  <div class="row">
			  <div class="col-md-12 mt10">
			  	  <input type="hidden" name="staffId" value="${staffId }"/>
				  <table class="table table-bordered table-hover table-responsive">
					  <thead>
					  <tr>
					  	  <th><input type="checkbox" name="allCheckBox"  id="allCheckBox" onclick="checkAll(this);">ȫѡ</th>
					      <th>���</th>
						  <th>�˵�����</th>
						  <th>codeֵ</th>
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
			  	<input type="button" onclick="isOk();" value="ȷ��"/>
			  </div>
		  </div>
	  </div>
  </body>
  <script type="text/javascript">
  
		//��ȡ��������
		var index = parent.layer.getFrameIndex(window.name); 
	
	  	function isOk(){
	  		//��ѡ����Ŀ��Ӧ��codeֵ��ӵó�roleֵ
	  		var role = 0;
	  		$("input[name='roleCheckBox']:checked").each(function(){ 
	  			role += $("#code_"+$(this).val()).html()+",";
	  		});
	  		role=role.toString().substring(1, role.toString().length-1);
	  		//console.log(role);
	  		//��Ȩ��
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
					//������û��Ѵ��ڣ���չ�ֳ���ʾ
					if(data=="1"){
						alert("Ȩ�޸���ɹ���");
						parent.layer.close(index);
					}else{
						alert("Ȩ�޸���ʧ�ܣ�");
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
