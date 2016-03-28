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
    <title>etl线程list</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/shade.css">
    <style>
        .mt10{margin-top:10px;}
    </style>
</head>
<body>
<!--<form action="meet/toMeetRoomList.do" name="confForm" method="post">-->
${error }
<div class="container-full">
    <div class="row">
    	<div class="col-md-12 mt10">
      		<form class="form-inline" action="admin/etl" name="confForm" method="get">
        		<div class="input-group">
                	<span class="input-group-addon">服务器ip</span>
                	<input type="text" class="form-control" id="threadHost" name="threadHost" placeholder="请输入查询内容" value="${etlThread.threadHost}" >
            	</div>
            	<div class="input-group">
                	<span class="input-group-addon">端口号</span>
                	<input type="text" class="form-control" id="threadPort" name="threadPort" placeholder="请输入查询内容" value="${etlThread.threadPort}" >
            	</div>
            	<div class="input-group">
                	<span class="input-group-addon">线程名称</span>
                	<input type="text" class="form-control" id="threadName" name="threadName" placeholder="请输入查询内容" value="${etlThread.threadName}" >
            	</div>
            	<input type="hidden" name="_method" value="get"/>
            	<button type="submit" class="btn btn-warning">查询</button>
            	<a href="admin/etl/toAddData.do" role="button" class="btn btn-danger">新建</a>
	  		</form>
	  	</div>
    </div>
    <div class="row">
        <div class="col-md-12 mt10"> 
        	<aa:zone name="confListZone">
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                    	<th>服务器ip</th>
                        <th>端口号</th>
                        <th>线程名称</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${etlThreadList !=null }">
                		<c:forEach items="${etlThreadList }" var="etlThread" varStatus="index">
                			<tr>
                				<td>${etlThread.threadHost }</td>
                				<td>${etlThread.threadPort }</td>
                				<td>${etlThread.threadName }</td>
                				<td>
                			 		<a href="javaScript:void(0);" onclick="chooseRelevanceEtlSchedule('${taskTable}','${etlThread.threadHost }','${etlThread.threadPort }','${etlThread.threadName }')">选择</a>
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
	function chooseRelevanceEtlSchedule(taskTable,threadHost,threadPort,threadName){
		var index = parent.layer.getFrameIndex(window.name); 
		parent.chooseOk(taskTable,threadHost,threadPort,threadName);
		parent.layer.close(index);
	}
 </script>
</html>