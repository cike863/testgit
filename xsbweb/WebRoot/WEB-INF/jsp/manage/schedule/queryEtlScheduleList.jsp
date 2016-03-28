<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://ajaxanywhere.sourceforge.net/" prefix="aa"%>
<%@ taglib uri="http://java.xsb.com/xsbweb/tag/pager" prefix="page"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <title>etl任务管理</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/shade.css">
    
    <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
 	<script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/aa.js"></script>
 	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/layer/layer.js" ></script>
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
      		<form class="form-inline" name="etlForm" action="${pageContext.request.contextPath }/admin/etlschedule/getEtlScheduleList.do" method="get">
        		<div class="input-group">
                	<span class="input-group-addon">目标表</span>
                	<input type="text" class="form-control" id="taskTable" name="taskTable" placeholder="请输入查询内容" value="${snapEtlSchedule.taskTable}" >
            	</div>
            	<div class="input-group">
                	<span class="input-group-addon">任务描述</span>
                	<input type="text" class="form-control" id="taskDesc" name="taskDesc" placeholder="请输入查询内容" value="${snapEtlSchedule.taskDesc}" >
            	</div>
            	<input type="hidden" name="_method" value="get"/>
            	 <input type="hidden" name="pageSize" value="${snapEtlSchedule.pageSize}" /> 
            	<input type="button" class="btn btn-danger" value="查询" onclick="queryEtlScheduleList();"/>
				<input type="button" class="btn btn-danger" value="新增" onclick="toAddScheduleList();"/>
	  		</form>
	  	</div>
    </div>
    <div class="row">
        <div class="col-md-12 mt10"> 
        	<aa:zone name="confListZone">
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                    	<th>目标表</th>
                        <th>执行状态</th>
                        <th>任务执行表的时间</th>
                        <th>执行服务器</th>
                        <th>线程名称</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${etlScheduleList !=null }">
                		<c:forEach items="${etlScheduleList }" var="eVO" varStatus="index">
                			<tr>
                				<td>${eVO.taskTable }</td>
                				<td>${eVO.execStatus}</td>
                				<td>${eVO.nextExecTime }</td>
                				<td>
                				<c:choose>
                					<c:when test="${(empty  eVO.threadHost || eVO.threadHost eq '' ) && (empty  eVO.threadPort || eVO.threadPort eq '' )} ">
                					</c:when>
                					<c:otherwise>
                						${eVO.threadHost} : ${eVO.threadPort}
                					</c:otherwise>
                				</c:choose>
                				<td>${eVO.threadName }</td>
                				<td>
                					<a href="${pageContext.request.contextPath }/admin/etlschedule/toEditEtlSchedule.do?taskTable=${eVO.taskTable}">编辑</a> |
                					<a href="javascript:void(0);" onclick="chooseRelevanceEtl('${eVO.taskTable}');" >关联线程</a> |        					
									<a href="javascript:void(0);" onclick="deleteEtlSchedule('${eVO.taskTable}')">删除</a>
                				</td>
                			</tr>
                		</c:forEach>
                	</c:if>
                </tbody>
            </table>
            </aa:zone>
             <page:pager pageSize="${snapEtlSchedule.pageSize}" pageNo="${snapEtlSchedule.pageNo}" url="admin/etlschedule/getEtlScheduleList.do"  recordCount="${snapEtlSchedule.totalRecord}"/>
        </div>
    </div>
</div>
<!--</form>-->
</body>
 <script type="text/javascript">
	  	/* function toEditEtlSchedule(taskTable){
			//提交form
			document.etlForm.action="admin/etlschedule/toEditEtlSchedule.do";
			document.etlForm.submit();
		} */
  		function queryEtlScheduleList(){
  			//提交form
  			document.etlForm.action="admin/etlschedule/getEtlScheduleList.do";
  			document.etlForm.submit();
  		}
  		function toAddScheduleList(){
  			//提交form
  			document.etlForm.action="admin/etlschedule/toAddEtlSchedule.do";
  			document.etlForm.submit();
  		}
  		function deleteEtlSchedule(taskTable){
			if(!confirm("确定要删除该数据吗？")){
				return;
			}
			window.location="admin/etlschedule/deleteEtlSchedule.do?taskTable="+taskTable;
		}
  		function chooseRelevanceEtl(taskTable){
  			url = "admin/etl?taskTable="+taskTable;
  			layer.open({
  				type:2,
  				area:['1000px','620px'],
  				fix:false,
  				maxmin:true,
  				offset: '100px',
  				content:url
  			});
  		}
  		//设置选择函数，将数据更新
  		function chooseOk(taskTable,threadHost,threadPort,threadName){
			//console.log("taskTable："+taskTable+"threadHost："+threadHost+"threadPort："+threadPort+"threadName："+threadName);
			$.ajax({
				url : "admin/etlschedule/updateRelevanceEtlSchedule.do",
				type : "POST",
				async: false,
				data :{
					"taskTable" : taskTable,
					"threadHost" : threadHost,
					"threadPort" : threadPort,
					"threadName" : threadName
				},
				success : function(data){
					if(data.resultCode=='1'){
						window.location.reload();
					}else{
						alert("关联线程失败");
					}
				},
				error : function(){
					alert("关联线程失败");
				}
			});
  		}
  </script>
</html>