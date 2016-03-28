<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://ajaxanywhere.sourceforge.net/" prefix="aa"%>
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
    <title>选择项目</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .mt10{margin-top:10px;}
    </style>
</head>
<body>
<!--<form action="meet/toMeetRoomList.do" name="confForm" method="post">-->
<div class="container-full">
    <div class="row">
        <div class="col-md-12 mt10">
            <form class="form-inline" action="meet/toChooseProject.do" name="confForm" method="post">
                <input type="text" class="form-control" id="search" name="confLabel" placeholder="请输入查询内容">
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
                        <th>项目编号</th>
                        <th>项目名称</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${trsProjectList!=null }">
	                	<c:forEach items="${trsProjectList}" var="project" varStatus="index">
	                		<tr>
		                        <td>${project.projectNo}</td>
		                        <td>${project.projName}</td>
		                        <td>
		                        <a href="javascript:void(0);" onclick="chooseOK('${project.projectNo}','${project.projName}')">选择</a>
		                        </td>
		                    </tr>
	                	</c:forEach>
                	</c:if>
                </tbody>
            </table>
            </aa:zone>
        </div>
    </div>
</div>
<!--</form>-->
</body>
 <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
 <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/aa.js"></script>
 <script type="text/javascript" language="javascript">
 	function chooseOK(projectNo,projName){
 		window.opener.chooseProjectOK(projectNo,projName);
 		window.close();
 	}
 </script>
</html>	