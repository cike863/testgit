
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://ajaxanywhere.sourceforge.net/" prefix="aa"%>
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

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>index</title>
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/shade.css">
<style>
.mt10 {
	margin-top: 10px;
}
</style>
<script type="text/javascript">

	window.onload = function() {
		if (${roadShowVO.isVenture=='0'}) {
			$("#notVenture").attr("checked", "checked");
		}
		if (${roadShowVO.isVenture=='2'}) {
			$("#isVenture").attr("checked", "checked");
		}
	}
</script>
</head>
<body>
	<!--<form action="meet/toMeetRoomList.do" name="confForm" method="post">-->
	${error }
	<div class="container-full">
	<div class="row">
			<div class="col-md-12 mt10">
				<form class="form-inline" name="customerForm"
					action="admin/road.do">
					<div class="input-group">
						<span class="input-group-addon">姓名</span> <input type="text"
							class="form-control" id="name" name="name"
							placeholder="姓名"
							value="${(roadShowVO.name == '')?'':roadShowVO.name}">
					</div>
					<%-- <div class="input-group">
						<span class="input-group-addon">公司名称</span> <input type="text"
							class="form-control" id="customerEmail" name="customerEmail"
							placeholder="电子邮箱"
							value="${(customer.customerEmail == '')?'':customer.customerEmail}">
					</div> --%>
					<div class="input-group">
						<span class="input-group-addon">挂牌进度</span> <input type="text"
							class="form-control" id="step" name="step"
							placeholder="挂牌进度"
							value="${(roadShowVO.step == '')?'':roadShowVO.step}">
					</div>
					<div class="input-group">
						<span class="input-group-addon">融资方式</span> <input type="text"
							class="form-control" id="financingType" name="financingType"
							placeholder="融资方式"
							value="${(roadShowVO.financingType == '')?'':roadShowVO.financingType}">
					</div>
					<div class="input-group">
						<span class="input-group-addon">营收规模</span> <input type="text"
							class="form-control" id="turnoverScale" name="turnoverScale"
							placeholder="营收规模"
							value="${(roadShowVO.turnoverScale == '')?'':roadShowVO.turnoverScale}">
					</div>
					<input type="radio"  name="isVenture" value="0" id="notVenture">无创投机构
					<input type="radio"  name="isVenture" value="1"  id="isVenture">有创投机构
					<input type="hidden" name="_method" value="get" /> <input
						type="hidden" name="projShowStatus" value="0">
					<button type="submit" class="btn btn-warning">查询</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 mt10">
				<aa:zone name="confListZone">
					<table class="table table-bordered table-hover table-responsive">
						<thead>
							<tr>
								<th>用户ID</th>
								<th>姓名</th>
								<th>职位</th>
								<th>手机号</th>
								<th>微信</th>
								<th>邮箱</th>
								<th>公司名称</th>
								<th>挂牌进度</th>
								<th>是否有创投机构</th>
								<th>融资方式</th>
								<th>营收方式</th>
								<th>创建时间</th>
								<th>该记录最后更新时间</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${roadShowVOList!=null }">
								<c:forEach items="${roadShowVOList}" var="rsv" varStatus="index">
									<tr>
										<td>${rsv.customerId}</td>
										<td>${rsv.name}</td>
										<td>${rsv.position}</td>
										<td>${rsv.phoneNo}</td>
										<td>${rsv.wx}</td>
										<td>${rsv.email}</td>
										<td>${rsv.company}</td>
										<td>${rsv.step}</td>
										<td>
											<c:if test="${rsv.isVenture eq 0}">
												否
											</c:if>
											<c:if test="${rsv.isVenture eq 1}">
		                        				是
		                        			</c:if>
		                        		</td>
										<td>${rsv.financingType}</td>
										<td>${rsv.turnoverScale}</td>
										<td><fmt:formatDate value="${rsv.creatDate}"
												pattern="yyyy-MM-dd" /></td>
										<td><fmt:formatDate value="${rsv.lastUpdateDate}"
												pattern="yyyy-MM-dd" /></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</aa:zone>
				<page:pager pageSize="${roadShowVO.pageSize}"
					pageNo="${roadShowVO.pageNo}" url="admin/road.do"
					recordCount="${roadShowVO.totalRecord}" />
			</div>
		</div>
	</div>
	<!--</form>-->
</body>
<script type="text/javascript" language="javascript"
	src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
<script type="text/javascript" language="javascript"
	src="<%=request.getContextPath()%>/js/aa.js"></script>
<script type="text/javascript" language="javascript">
 </script>
</html>