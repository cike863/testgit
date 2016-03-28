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
    <title>index</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .mt10{margin-top:10px;}
    </style>
</head>
<body>
<!--<form action="meet/toMeetRoomList.do" name="confForm" method="post">-->
<div class="container-full">
    <form class="form-inline" action="meet/toForeMeetRoomList.do" name="confForm" method="post">
    <div class="row">
        <div class="col-md-12 mt10">
                <input type="text" class="form-control" id="search" name="confName" value="${meetRoomVO.confName }" placeholder="请输入查询内容">
                <button type="submit" class="btn btn-warning">查询</button>
                <a href="meet/toAddForeMeetRoom.do" role="button" class="btn btn-danger">新建</a>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 mt10">
        	<aa:zone name="confListZone">
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                        <th>名称</th>
                        <th>上线时间</th>
                        <th>直播开始时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${meetRoomVOList!=null }">
	                	<c:forEach items="${meetRoomVOList}" var="mtroom" varStatus="index">
	                		<tr>
		                        <td>${mtroom.confName}</td>
		                        <td>${mtroom.onlineDate}</td>
		                        <td>${mtroom.showDate}</td>
		                        <td>
		                      	  <a href="javascript:void(0);" onclick="deleteForeMeetRoom('${mtroom.meetId}')">删除</a>
		                        </td>
		                    </tr>
	                	</c:forEach>
                	</c:if>
                </tbody>
            </table>
            </aa:zone>
        </div>
    </div>
    </form>
</div>
<!--</form>-->
</body>
 <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
 <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/aa.js"></script>
 <script type="text/javascript" language="javascript">
 	ajaxAnywhere.formName="confForm";
	ajaxAnywhere.getZonesToReload = function(){
		return "confListZone";
	} 
	function deleteForeMeetRoom(meetId){
		alert(meetId)
		document.forms[0].action="meet/deleteMeetRoomByMeetId.do?meetId="+meetId;
		ajaxAnywhere.submitAJAX();
	}
	</script>
</html>