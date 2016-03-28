<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'selectSubAccountList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
 	<script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/aa.js"></script>
  </head>
  
  <body>
  	<form action="meet/inviteJonConf.do" name="selectSubAccountForm" method="post">
  		<div class="row">
	        <div class="col-md-8 col-md-offset-2 mt20">
	        	<aa:zone name="callListZone">
	            <table class="table table-bordered table-hover table-responsive">
	                <thead>
	                    <tr>
	                        <th></th>
	                        <th>用户名</th>
	                        <th>云账号</th>
	                        <th>手机号码</th>
	                    </tr>
	                </thead>
	                <tbody>
	                	<c:if test="${meetRoomVO.meetCallVOList!=null }">
	                		<c:forEach items="${meetRoomVO.meetCallVOList}" var="callVO" varStatus="index">
	                			<tr>
			                        <td>${callVO.customerName}</td>
			                        <td>${callVO.jointime}</td>
			                        <td>${callVO.isHandsUp}</td>
			                        <td>
			                        	<input type="hidden" name="customerId" value="${callVO.customerId}"/>
			                        	<c:if test="${callVO.isBanVoice == '0' }">
				                            <button type="button" class="btn btn-info" onclick="operation(1,'${callVO.callSid}')">
				                                <span class="glyphicon glyphicon-volume-off" aria-hidden="true"></span> 静音
				                            </button>
			                        	</c:if>
			                        	<c:if test="${callVO.isBanVoice == '1' }">
			                        		<button type="button" class="btn btn-success" onclick="operation(2,'${callVO.callSid}')">
				                                <span class="glyphicon glyphicon-volume-up" aria-hidden="true"></span> 取消静音
				                            </button>
			                        	</c:if>
			                            <button type="button" class="btn btn-danger" onclick="operation(3,'${callVO.callSid}')">
			                                <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> 强制退出
			                            </button>
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
    This is my JSP page. <br>
  </body>
</html>
