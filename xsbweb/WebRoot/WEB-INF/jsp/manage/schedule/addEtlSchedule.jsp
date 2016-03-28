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
    
    <title>My JSP 'addEtlSchedule.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/dtpicker/js/laydate.js" ></script>
	<script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
	<style>
		input[type="text"]{
			margin-top:5px; 
			width:90%;"
		}
	</style>
  </head>
  	
  <body>
  		<!-- 显示错误信息 -->
		<c:if test="${allErrors!=null }">
			<c:forEach items="${allErrors }" var="error">
				${ error.defaultMessage}<br/>
			</c:forEach>
		</c:if>
    	<form:form action="admin/etlschedule/addEtlSchedule.do" modelAttribute="etlScheduleMode" name="etlForm" method="post">
    		<table style="width: 100%">
    			<tr>
    				<td style=" width:14%;">目标表</td>
    				<td style=" width:35%;"><input type="text" placeholder="必填项" name="taskTable" value="${etlScheduleVO.taskTable }" required  class="input-xlarge"/><form:errors path="taskTable"></form:errors></td>
    				<td style=" width:14%;">taskD任务描述esc</td>
    				<td style=" width:35%;"><input type="text" name="taskDesc" value="${etlScheduleVO.taskDesc }" /><form:errors path="taskDesc"></form:errors></td>
    			</tr>
    			<tr>
    				<td>任务周期。单位，分钟</td>
    				<td><input type="text" name="taskPeriod" value="${etlScheduleVO.taskPeriod }"/><form:errors path="taskPeriod"></form:errors></td>
    				<td>taskDependency</td>
    				<td><input type="text" name="taskDependency" value="${etlScheduleVO.taskDependency }"/></td>
    			</tr>
    			<tr>
    				<td>调度依赖</td>
    				<td><input type="text" name="effectiveDate" value="${etlScheduleVO.effectiveDate }"/><form:errors path="effectiveDate"></form:errors></td>
    				<td>任务失效时间</td>
    				<td>
    					<input placeholder="请输入日期" class="laydate-icon" name="extireDate" style="height:30px" 
    					id="extireDate" value="${etlScheduleVO.extireDate}"  
    					onClick="laydate({elem: '#extireDate',istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
    				</td>
    			</tr>
    			<tr>
    				<td>最后一次执行时间</td>
    				<td>
    					<input placeholder="请输入日期" class="laydate-icon" name="lastExecTime" style="height:30px" id="lastExecTime" 
    					value="${etlScheduleVO.lastExecTime}"  onClick="laydate({elem: '#lastExecTime',istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
    				</td>
    				<td>任务下次执行时间</td>
    				<td>
    					<input placeholder="请输入日期" class="laydate-icon" name="nextExecTime" id="nextExecTime" style="height:30px" 
    					value="${etlScheduleVO.nextExecTime}"  
    					onClick="laydate({elem: '#nextExecTime',istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
    				</td>
    			</tr>
    			<tr>
    				<td>etl数据源记录格式</td>
    				<td><input type="text" name="intrfcRecordType" value="${etlScheduleVO.intrfcRecordType }"/></td>
    				<td>etl数据源方法</td>
    				<td><input type="text" name="intrfcMethod" value="${etlScheduleVO.intrfcMethod }"/></td>
    			</tr>
    			<tr>
    				<td>etl数据源接口类型</td>
    				<td><input type="text" name="intrfcType" value="${etlScheduleVO.intrfcType }"/></td>
    				<td>执行状态</td>
    				<td><input type="text" name="execStatus" value="${etlScheduleVO.execStatus }"/></td>
    			</tr>
    			<tr>
    				<td>数据源url part1</td>
    				<td><input type="text" name="urlPart1" value="${etlScheduleVO.urlPart1 }"/></td>
    				<td>数据源url part1的逻辑</td>
    				<td><input type="text" name="urlPart1Logic" value="${etlScheduleVO.urlPart1Logic }"/></td>
    			</tr>
    			<tr>
    				<td>数据源url part2</td>
    				<td><input type="text" name="urlPart2" value="${etlScheduleVO.urlPart2 }"/></td>
    				<td>数据源url part2的逻辑</td>
    				<td><input type="text" name="urlPart2Logic" value="${etlScheduleVO.urlPart2Logic }"/></td>
    			</tr>
    			<tr>
    				<td>u数据源url part3</td>
    				<td><input type="text" name="urlPart3" value="${etlScheduleVO.urlPart3 }"/></td>
    				<td>数据源url part3的逻辑</td>
    				<td><input type="text" name="urlPart3Logic" value="${etlScheduleVO.urlPart3Logic }"/></td>
    			</tr>
    			<tr>
    				<td>数据源url part4</td>
    				<td><input type="text" name="urlPart4" value="${etlScheduleVO.urlPart4 }"/></td>
    				<td>数据源url part4的逻辑</td>
    				<td><input type="text" name="urlPart4Logic" value="${etlScheduleVO.urlPart4Logic }"/></td>
    			</tr>
    			<tr>
    				<td>数据源url part5</td>
    				<td><input type="text" name="urlPart5" value="${etlScheduleVO.urlPart5 }"/></td>
    				<td>数据源url part5的逻辑</td>
    				<td><input type="text" name="urlPart5Logic" value="${etlScheduleVO.urlPart5Logic }"/></td>
    			</tr>
    			<tr>
    				<td>正则表达式</td>
    				<td><input type="text" name="regex" value="${etlScheduleVO.regex }"/></td>
    				<td>任务执行标的事件</td>
    				<td><input type="text" name="scheduleDate" value="${etlScheduleVO.scheduleDate }"/></td>
    			</tr>
    			<tr>
    				<td>目标字段1</td>
    				<td><input type="text" name="targetColumn01" value="${etlScheduleVO.targetColumn01 }"/></td>
    				<td>目标字段2</td>
    				<td><input type="text" name="targetColumn02" value="${etlScheduleVO.targetColumn02 }"/></td>
    			</tr>
    			<tr>
    				<td>目标字段3</td>
    				<td><input type="text" name="targetColumn03" value="${etlScheduleVO.targetColumn03 }"/></td>
    				<td>目标字段4</td>
    				<td><input type="text" name="targetColumn04" value="${etlScheduleVO.targetColumn04 }"/></td>
    			</tr>
    			<tr>
    				<td>目标字段5</td>
    				<td><input type="text" name="targetColumn05" value="${etlScheduleVO.targetColumn05 }"/></td>
    				<td>目标字段6</td>
    				<td><input type="text" name="targetColumn06" value="${etlScheduleVO.targetColumn06 }"/></td>
    			</tr>
    			<tr>
    				<td>目标字段7</td>
    				<td><input type="text" name="targetColumn07" value="${etlScheduleVO.targetColumn07 }"/></td>
    				<td>目标字段8</td>
    				<td><input type="text" name="targetColumn08" value="${etlScheduleVO.targetColumn08 }"/></td>
    			</tr>
    			<tr>
    				<td>目标字段9</td>
    				<td><input type="text" name="targetColumn09" value="${etlScheduleVO.targetColumn09 }"/></td>
    				<td>目标字段10</td>
    				<td><input type="text" name="targetColumn10" value="${etlScheduleVO.targetColumn10 }"/></td>
    			</tr>
    			<tr>
    				<td>该记录创建日期</td>
    				<td>
    					<input placeholder="请输入日期" class="laydate-icon" name="createDate" id="createDate" style="height:30px" 
    					value="${etlScheduleVO.createDate}"  onClick="laydate({elem: '#createDate',istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
    				</td>
    				<td>该记录最后更新日期</td>
    				<td>
    					<input placeholder="请输入日期" class="laydate-icon" name="lastUpdateDate" id="lastUpdateDate" style="height:30px" 
    					value="${etlScheduleVO.lastUpdateDate}" onClick="laydate({elem: '#lastUpdateDate',istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
    				</td>
    			</tr>
    			<tr>
    				<td>线程Host</td>
    				<td><input type="text" name="threadHost" value="${etlScheduleVO.threadHost }"/></td>
    				<td>线程端口</td>
    				<td><input type="text" name="threadPort" value="${etlScheduleVO.threadPort }"/></td>
    			</tr>
    			<tr>
    				<td>线程名称</td>
    				<td><input type="text" name="threadName" value="${etlScheduleVO.threadName }"/></td>
    			</tr>
    			<tr>
					<td colspan="4" class="text-center">
						<button type="button" class="btn btn-success"  style="margin-top:15px; "
						onclick="submitData();">提交</button>
						<button type="button" class="btn btn-danger" onclick="history.go(-1)"  style="margin-top:15px; ">返回</button>
					</td>
				</tr>
    		</table>
    	</form:form>
  </body>
  <script type="text/javascript">
  function checkForm() {
	  var taskTable=$("input[name='taskTable']").val();
	  console.log("taskTable:"+taskTable);
	  if(taskTable.length==0){
		  alert("taskTable不能为空");
		  return false;
	  }else{
		  return true;
	  }
  }
  	 function submitData(){
  		var result = checkForm();
  		if(result){
  			document.etlForm.action="admin/etlschedule/addEtlSchedule.do";
			document.etlForm.submit();
  		}
  	}
  </script>
</html>
