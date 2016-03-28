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
    <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
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
            <form class="form-inline" action="admin/project" method="post"  name="confForm"><!--action="admin/project/serach?serachInformation="+information, name="confForm" method="post"-->
                
                <div class="input-group">
                	<span class="input-group-addon">项目编号</span>
                	<input type="text" class="form-control" id="projectNo" name="projectNo" placeholder="请输入查询内容" value="${(trsProject.projectNo == '')?'':trsProject.projectNo}" >
                </div>
                <div class="input-group">
                	<span class="input-group-addon">项目名称</span>
                	<input type="text" class="form-control" id="projName" name="projName" placeholder="请输入查询内容" value="${(trsProject.projName == '')?'':trsProject.projName}" >
                </div>
                <div class="input-group">
                	<span class="input-group-addon">关联公司代码</span>
                	<input type="text" class="form-control" id="projCpCode" name="projCpCode" placeholder="请输入查询内容" value="${(trsProject.projCpCode == '')?'':trsProject.projCpCode}" >
                </div>
                <input type="hidden" name="_method" value="get" /> 
                <input type="hidden" name="projRole" value="32" /> 
                <input type="hidden" name="pageSize" id="pageSize" value="${trsProject.pageSize}" /> 
                <button class="btn btn-warning" type="submit">查询</button><!-- type="submit"  onclick="serach();-->
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 mt10">
        	<aa:zone name="confListZone">
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                    	<th>序号</th>
                    	<th>项目编号</th>
                    	<th>关联公司代码</th>
                        <th>项目名称</th>
                        <th>上线时间</th>
                        <th>路演状态</th>
                        <th>前台显示</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${trsProjectVOList!=null}">
	                	<c:forEach items="${trsProjectVOList}" var="project" varStatus="index">
	                		<tr>
	                			<td>${index.index+1}</td>
	                			<td>${project.projectNo}</td>
	                			<td>${project.projCpCode}</td>
		                        <td>${project.projName}</td>
		                        <td>${project.projOnlineDate}</td>                      	
		                        <td>
		                        <c:choose>
		                        	<c:when test="${project.projRole eq '32'}">
		                        		路演中
		                        	</c:when>
		                        	<c:when test="${project.projRole eq '64'}">
		                        		成功案例
		                        	</c:when>
		                        	<c:otherwise>
		                        		无数据
		                        	</c:otherwise>
		                        </c:choose>
		                        </td>
		                        <td>
		                        <c:choose>
		                        	<c:when test="${project.isShow eq '1'}">
		                        		显示
		                        	</c:when>
		                        	<c:when test="${project.isShow eq '0'}">
		                        		隐藏
		                        	</c:when>
		                        	<c:otherwise>
		                        		无数据
		                        	</c:otherwise>
		                        </c:choose>
		                        </td>
		                        <td>	
		                        	<c:if test="${project.projectNo ne '' }">
		                        		<a href="<%=path%>/admin/comment?projectNo=${project.projectNo}&projName=${project.projName}" >查看留言</a>		                        	
		                        	</c:if>
		                    </tr>
	                	</c:forEach>
                	</c:if>
                </tbody>
            </table>
            </aa:zone>
            <page:pager pageSize="${trsProject.pageSize}" pageNo="${trsProject.pageNo}" url="admin/project"  recordCount="${trsProject.totalRecord}"/>
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
 </script>
</html>