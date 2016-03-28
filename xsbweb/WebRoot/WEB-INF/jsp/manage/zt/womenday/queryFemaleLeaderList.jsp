 <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://ajaxanywhere.sourceforge.net/" prefix="aa"%>
<%@ taglib uri="http://java.xsb.com/xsbweb/tag/pager" prefix="page"%>
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
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/shade.css">
    <style>
        .mt10{margin-top:10px;}
    </style>
    <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
 	<script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/aa.js"></script>
</head>
<body>
<!--<form action="meet/toMeetRoomList.do" name="confForm" method="post">-->
${error }
<div class="container-full">
        <div class="row">
			<div class="col-md-12 mt10">
				<form class="form-inline" action="zt/womenday/admin/femaleLeaders" name="womendayForm" method="post">
					<div class="input-group">
						<span class="input-group-addon">候选人姓名</span>
						 <input type="text" class="form-control" name="leaderName" placeholder="候选人名称"  value="${femaleLeader.leaderName}"/>
					</div>
					<div class="input-group">
						<span class="input-group-addon">候选人公司</span> 
						<input type="text"class="form-control" name="companyName" placeholder="候选人公司" value="${femaleLeader.companyName}"/>
					</div>
					<div class="input-group">
						<span class="input-group-addon">候选人手机号</span> 
						<input type="text"class="form-control" name="leaderMobile" placeholder="候选人手机号" value="${femaleLeader.leaderMobile}"/>
					</div>
					<input type="hidden" name="_method" value="get" />
					<input type="hidden" name="pageSize" value="${femaleLeader.pageSize}" /> 
					<button type="submit" class="btn btn-warning" >查询</button>
            		<a href="zt/womenday/admin/add" role="button" class="btn btn-success">新建</a>
				</form>
			</div>
		</div>
    </div>
    <div class="row">
        <div class="col-md-12 mt10">
        	<aa:zone name="confListZone">
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                    	<!-- <th><input type="checkbox" name="allCheckBox"  id="allCheckBox" onclick="checkFemaleLeaderAll(this);" >全选</th> -->
                    	<th>候选人姓名</th>
                        <th>候选人公司</th>
                        <th>候选人手机号</th>
                        <th>所得投票数</th>
                        <th>候选人状态</th>
                        <th>排序</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${femaleLeaders!=null }">
	                	<c:forEach items="${femaleLeaders}" var="leader" varStatus="index">
	                		<tr>
	                			<%-- <td><input type="checkbox" id="femaleLeader_${index.index}"  value="${leader.leaderName}"></td> --%>
	                			<td>${leader.leaderName}</td>
		                        <td>${leader.companyName}</td>
		                        <td>${leader.leaderMobile}</td>
		                        <td>${leader.votedCnt}</td>
	                         	<td>
	                         		<c:choose>
		                        	<c:when test="${leader.votedStatus eq '0'}">
		                        		候选人审核
		                        	</c:when>
		                        	<c:when test="${leader.votedStatus eq '1'}">
		                        		开放投票
		                        	</c:when>
		                        	<c:when test="${leader.votedStatus eq '2'}">
		                        		报名截止
		                        	</c:when>
		                        	<c:otherwise>
		                        		取消报名资格
		                        	</c:otherwise>
		                        </c:choose>
	                         	</td>
	                         	<td>${leader.orderNoEx}</td>
	                         	<td>
	                         		<c:if  test="${leader.votedStatus eq '0'}">
	                         			<a href="<%=path%>/zt/womenday/admin/getfemaleLeaders?companyName=${leader.companyName}&leaderName=${leader.leaderName}">审核</a> | 
	                         		</c:if>
	                         		<a href="<%=path%>/zt/womenday/admin/getfemaleLeaders?type=1&companyName=${leader.companyName}&leaderName=${leader.leaderName}">编辑</a> | 
	                         		<a href="javascript:void(0);" onclick="delfemaleLeaders('${leader.companyName}','${leader.leaderName}')">删除</a>
	                         		 
		                        </td>
		                    </tr>
	                	</c:forEach>
                	</c:if>
                </tbody>
            </table>
            </aa:zone>
            <page:pager pageSize="${femaleLeader.pageSize}" pageNo="${femaleLeader.pageNo}" url="zt/womenday/admin/femaleLeaders" recordCount="${femaleLeader.totalRecord}"/>
        </div>
    </div>
<!--</form>-->
</body>

 <script type="text/javascript">
 function delfemaleLeaders(companyName,leaderName){
	 if(!confirm("确定要删除该数据吗？")){
			return;
		}
	 $.ajax({
			url : "<%=path%>/zt/womenday/admin/delfemaleLeaders",
			type :"GET",
			async: false,
			data : {
				"companyName" : companyName,
				"leaderName" : leaderName
			},
			success : function (data){
				if(data.resultCode == '1'){
					window.location.reload();
				}else{
					alert("删除失败");
				}
			},
			errror : function(){
				alert("删除失败");
			}
		});
 }
 
 </script>
</html>