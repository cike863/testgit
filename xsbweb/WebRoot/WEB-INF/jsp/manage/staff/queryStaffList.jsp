<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.xsb.com/xsbweb/tag/pager" prefix="page"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
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
    <script  type="text/javascript" src="<%=request.getContextPath()%>/common/dtpicker/js/laydate.js" ></script>.
    <script  type="text/javascript" src="<%=request.getContextPath()%>/common/layer/layer.js" ></script>
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
				<form class="form-inline" name="staffForm"
					action="${pageContext.request.contextPath }/admin/staff.do">
					<div class="input-group">
						<span class="input-group-addon">手机号码</span> <input type="text"
							class="form-control" id="staffPhoneNo" name="staffPhoneNo"
							placeholder="手机号码"
							value="${(staff.staffPhoneNo == '')?'':staff.staffPhoneNo}">
					</div>
					<div class="input-group">
						<span class="input-group-addon">电子邮箱</span> <input type="text"
							class="form-control" id="staffEmail" name="staffEmail"
							placeholder="电子邮箱"
							value="${(staff.staffEmail == '')?'':staff.staffEmail}">
					</div>
					<input type="hidden" name="_method" value="get" />
					<button type="submit" class="btn btn-warning">查询</button>
					<button type="button" class="btn btn-danger"
						onclick="toAddStaff();">新建</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 mt10">
				<table class="table table-bordered table-hover table-responsive">
					<thead>
						<tr>
							<th>编号</th>
							<th>姓名</th>
							<th>手机</th>
							<th>邮箱</th>
							<th>添加时间</th>
							<th>opreation</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${staffList}" var="nVO">
							<tr>
								<td>${nVO.staffId}</td>
								<td>${nVO.staffName}</td>
								<td>${nVO.staffPhoneNo}</td>
								<td>${nVO.staffEmail}</td>
								<td>${nVO.createDate}</td>
								<td><a href="javascript:void(0);"
									onclick="toChangeRole('${nVO.staffId}');">权限设置</a><br /> <a
									href="${pageContext.request.contextPath }/admin/staff/toEditStaff.do?id=${nVO.staffId}">编辑</a> | 
									<a href="javaScript:void(0);" onclick="deleteStaff('${nVO.staffId}')">删除</a>

								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		</form>
		<page:pager pageSize="${staff.pageSize}" pageNo="${staff.pageNo}"
			url="admin/staff.do" recordCount="${staff.totalRecord}" />
	</div>
</body>
  <script type="text/javascript">
	  	/* function toEditEtlSchedule(taskTable){
			//提交form
			document.etlForm.action="admin/etlschedule/toEditEtlSchedule.do";
			document.etlForm.submit();
		} */
  		function queryCustomerList(){
  			//提交form
  			document.staffForm.action="admin/staff/toAdminStaff.do";
  			document.staffForm.submit();
  		}
  		function toAddStaff(){
  			//提交form
  			document.staffForm.action="admin/staff/toAddStaff.do";
  			document.staffForm.submit();
  		}
  		
  		function toChangeRole(staffId){
			var url = "admin/role?staffId="+staffId;
			layer.open({
			    type: 2,
			    area: ['850px', '550px'],
			    fix: false, //不固定
			    maxmin: true,
			    content: url
			});
  		}
  		function deleteStaff(staffId){
  			if(!confirm("确定要删除该数据吗？")){
  				return;
  			}
  			window.location="admin/staff/deleteStaff.do?id="+staffId;		  
  		}
  </script>
</html>
