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
    
    <title>新增后台用户</title>
    
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
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/dtpicker/js/laydate.js" ></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/layer/layer.js" ></script>
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
				<form:form action="admin/staff/addStaff.do"  name="staffForm" method="post" class="form-horizontal">
					<div class="form-group">
						<label class="col-md-1 control-label">姓名</label>
						<div class="col-md-5">
							<input type="text" class="form-control" name="staffName" value="${staff.staffName }" placeholder="请输入用户名"/><form:errors path="staffName"></form:errors>
						</div>
						
					</div>
	             
					<div class="form-group">
						<label class="col-md-1 control-label">手机号码</label>
						<div class="col-md-5">
							<input type="text" class="form-control" name="staffPhoneNo" value="${staff.staffPhoneNo }" placeholder="请输入用户手机号码"/><form:errors path="staffPhoneNo"></form:errors>
						</div>
						</div>
					
						<div class="form-group">
						<label class="col-md-1 control-label">电子邮箱</label>
						<div class="col-md-5">
							<input type="text" class="form-control" name="staffEmail" value="${staff.staffEmail }" placeholder="请输入电子邮箱"/><form:errors path="staffEmail"></form:errors>
						</div>
						</div>
						
						<div class="form-group">
						<label class="col-md-1 control-label">QQ</label>
						<div class="col-md-5">
							<input type="text" class="form-control" name="staffQq" value="${staff.staffQq }" placeholder="请输入QQ"/><form:errors path="staffQq"></form:errors>
						</div>
						</div>
						<div class="form-group">
						<label class="col-md-1 control-label">微信</label>
						<div class="col-md-5">
							<input type="text" class="form-control" name="staffWebchat" value="${staff.staffWebchat }" placeholder="请输入微信"/><form:errors path="staffWebchat"></form:errors>
						</div>
						</div>
						<div class="form-group">
						<label class="col-md-1 control-label">职位</label>
						<div class="col-md-5">
							<input type="text" class="form-control" name="staffPosition" value="${staff.staffPosition }" placeholder="请输入职位"/><form:errors path="staffPosition"></form:errors>
						</div>
						</div>
						<div class="form-group">
						<label class="col-md-1 control-label">头像</label>
						<div class="col-md-5">
							<input type="hidden" class="form-control" name="staffPicPath" value="${staff.staffPicPath}" >
                     		<img id="staffPic" src="${staff.staffPicPath}" height="100" width="100" name="shareMediaPath" >
                     		<input type="button" onclick="toUpload('staffPicPath');" value="上传" class="btn btn-success"/>
                     		<input type="button" onclick="toChear('staffPic','staffPicPath');" value="清空" class="btn"/>
                     		<label style="color:red">*文件大小不能超过10M</label>
						</div>
						</div>
						
						<div class="form-group">
						<label class="col-md-1 control-label">座机</label>
						<div class="col-md-5">
							<input type="text" class="form-control" name="staffTel" value="${staff.staffTel }" placeholder="请输入座机"/><form:errors path="staffTel"></form:errors>
						</div>
						</div>
						
						 <div class="form-group">
						<label class="col-md-1 control-label">密码</label>
						<div class="col-md-5">
							<input type="text" class="form-control" name="staffPassword" value="${staff.staffPassword }" placeholder="请设置密码"/><form:errors path="staffPassword"></form:errors>
						</div>
						</div>
						
						<div class="form-group">
						<label class="col-md-1 control-label">部门</label>
						<div class="col-md-5">
							<select id="departmentId" name="departmentId"  style="width:200px;height:30px">
										<option value=""></option>
										<c:if test="${departments!=null}">
											<c:forEach items="${departments}" var="department" varStatus="index">
												<option value="${department.departmentId }">${department.departmentName }</option>
											</c:forEach>
										</c:if>
								</select>
						</div>
				    </div>
					
					<div class="row">
						<div class="col-md-5 mt10 text-center">
							<button type="submit" class="btn btn-success">提交</button>
							<button type="button" class="btn btn-danger" onclick="javascript:window.location.href='admin/staff.do'">返回</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>

  </body>
  <script type="text/javascript">
  function toUpload(mediaType){
			var url = "admin/news/uploadLayer.do?mediaNo="+mediaType+"&dir=image";
			layer.open({
			    type: 2,
			    area: ['800px', '530px'],
			    fix: false, //不固定
			    maxmin: true,
			    offset: '100px',
			    content: url
			});
		}
		function uploadIsOk(mediaType,mediaNo,url,returnPath,newFileName,format,size){
				$("[name=staffPicPath]").val(url);
				$("#staffPic").attr("src",url);
		}
		function toChear(k,f){
			$("#"+k+"").attr("src","");
			$("[name="+f+"]").val('');
		}
		</script>
</html>
