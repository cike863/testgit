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
                        <th>上一次启动时间</th>
                        <th>上一次停止时间</th>
                       	<th> 最后更新时间</th>
                        <th>创建人</th>
                        <th>线程状态</th>
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
                				<td>${etlThread.lastStartTime }</td>
                				<td>${etlThread.lastStopTime}</td>
                				<td>${etlThread.lastUpdateDate}</td>
                				<td>${etlThread.createdBy}</td>
                				<td>
                					<c:if test="${etlThread.threadStatus eq 1}">
                						<a href="javascript:void(0);" onclick="changeTherdStatus('${etlThread.threadHost}','${etlThread.threadPort }','${etlThread.threadName }','1')">结束线程</a>
                					</c:if>
                					<c:if test="${etlThread.threadStatus eq 0}">
                						<a href="javascript:void(0);" onclick="changeTherdStatus('${etlThread.threadHost}','${etlThread.threadPort }','${etlThread.threadName }','0')">开始线程</a>
                					</c:if>
                				</td>
                			 	<td>
                			 		<c:if test="${etlThread.threadStatus eq 1}">
                			 			<a href="javascript:void(0);" onclick="gw('${etlThread.threadHost}','${etlThread.threadPort }','${etlThread.threadName }')">归位</a>
                			 		</c:if>
                			 		<a href="javaScript:void(0);" onclick="getThreadStatus('${etlThread.threadHost}','${etlThread.threadPort }','${etlThread.threadName }')">状态</a> | 
                			 		<a href="javaScript:void(0);" onclick="updateEtlThreadData('${etlThread.threadHost}','${etlThread.threadPort }','${etlThread.threadName }')">编辑</a> | 
                			 		<a href="javaScript:void(0);" onclick="deleteEtlThreadData('${etlThread.threadHost}','${etlThread.threadPort }','${etlThread.threadName }')">删除</a>
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
	ajaxAnywhere.formName="confForm";
	ajaxAnywhere.getZonesToReload = function(){
		return "confListZone";
	} 
	function deleteEtlThreadData(threadHost,threadPort,threadName){
		if(!confirm("确定要删除该数据吗？")){
			return;
		}
		$.ajax({
			url:"admin/etl/"+threadHost+"/"+threadPort+"/"+threadName+"/del",
			type : 'POST',
			data : {
				'_method' : 'delete'
			},
			async: false,
			success : function(data) {
				if (data.resultCode == '1') {
					window.location.reload();
				} else {
					alert("删除失败");
				};
			},
			error : function() {
				alert("删除失败");
			}
		});
	}
	function updateEtlThreadData(threadHost,threadPort,threadName){
		   window.location="admin/etl/"+threadHost+"/"+threadPort+"/"+threadName;
	}
	function changeTherdStatus(threadHost,threadPort,threadName,threadStatus){
		var url ="";
		if(threadStatus=='1'){
			url = "admin/etl/toStop";
		}else{
			url = "admin/etl/toStar";
		}
		$.ajax({
			url:url,
			type : 'GET',
			data : {
				'threadHost' : threadHost,
				'threadPort' : threadPort,
				'threadName' : threadName
			},
			async: false,
			success : function(result) {
				if (result == "1") {
					alert("操作成功！");
					window.location.reload();
				} else {
					if(threadStatus=="1"){
						if(result == "20002"){
							alert("线程已经停止!");
						}else{
							alert("结束线程失败");
						}
					}else{
						if(result == "20001"){
							alert("线程已经开始!");
						}else{
							alert("开始线程失败");
						}
					}
				};
			},
			error : function() {
				if(threadStatus=='1'){
					alert("结束线程失败");
				}else{
					alert("开始线程失败");
				}
			}
		});
	}
	
	function gw(threadHost,threadPort,threadName){
		if(!confirm("该操作只有在上次服务器停掉的情况下才能操作，该操作将会改变数据库记录状态，而不会实际改变线程状态！")){
			return false;
		}
		$.ajax({
			url : "admin/etl/"+threadHost+"/"+threadPort+"/"+threadName+"/gw",
			type : 'POST',
			data : {
				'threadHost' : threadHost,
				'threadPort' : threadPort,
				'threadName' : threadName
			},
			async: false,
			success : function(result) {
				if(result==1){
					window.location.reload();
				}else{
					alert("操作失败！");
				}
			},
		});
	}
	
	function getThreadStatus(threadHost,threadPort,threadName){
		$.ajax({
			url : "admin/etl/getStatus",
			type : 'GET',
			data : {
				'threadHost' : threadHost,
				'threadPort' : threadPort,
				'threadName' : threadName
			},
			async: false,
			success : function(result) {
				if(result==1){
					alert("提示：该线程为运行状态！");
				}else{
					alert("提示：该线程已停止或者不存在！");
				}
			},
		});
	}
 </script>
</html>