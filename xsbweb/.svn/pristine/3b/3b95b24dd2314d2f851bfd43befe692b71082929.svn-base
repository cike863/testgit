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
            <form class="form-inline" action="meet/chooseRelevanceMeet" method="post"  name="confForm"><!--action="admin/project/serach?serachInformation="+information, name="confForm" method="post"-->
                <div class="input-group">
                	<span class="input-group-addon">直播编号</span>
                	<input type="text" class="form-control" id="meetNo" name="meetNo" placeholder="请输入查询内容" value="${(meetVideoRoomVO.meetNo == '')?'':meetVideoRoomVO.meetNo}" >
                </div>
                <div class="input-group">
                	<span class="input-group-addon">直播名称</span>
                	<input type="text" class="form-control" id="confName" name="confName" placeholder="请输入查询内容" value="${(meetVideoRoomVO.confName == '')?'':meetVideoRoomVO.confName}" >
                </div>
                <input type="hidden" name="_method" value="get" />
                <input type="hidden" name="pageSize" id="pageSize" value="${meetVideoRoomVO.pageSize}" /> 
                <button class="btn btn-warning" type="submit">查询</button><!-- type="submit"  onclick="serach();-->
                <!-- <a href="admin/project/toAddForieProject.do" role="button" class="btn btn-danger">新建</a> -->
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 mt10">
        	<aa:zone name="confListZone">
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                    	<th>直播编号</th>
                    	<!-- <th>简称代码</th> -->
                        <th>直播名称</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody> 
                	<c:if test="${meetVideoRoomVOList!=null}">
	                	<c:forEach items="${meetVideoRoomVOList}" var="meetVideoRoom" varStatus="index">
	                		<tr>
	                			<%-- <td>${index.index+1}</td> --%>
	                			<td>${meetVideoRoom.meetNo}</td>
	                			<%-- <td>${project.projCpCode}</td> --%>
		                        <td>${meetVideoRoom.confName}</td>
		                        <td>	
		                        	<c:if test="${meetVideoRoom.meetNo ne '' }">
			                        	 <a onclick="chooseRelevanceUploadeMeet('${meetVideoRoom.meetNo}','${meetVideoRoom.confName}')">选择</a> 
		                        	</c:if>
		                    </tr>
	                	</c:forEach>
                	</c:if>
                </tbody>
            </table>
            </aa:zone>
            <%-- <page:pager pageSize="${trsProjectVOList.pageSize}" pageNo="${trsProjectVOList.pageNo}" url="${serachInformation == ''?\"admin/project\":\"admin/project/serach\"}"
            recordCount="${trsProjectVOList.totalRecord}"/> --%>
            <page:pager pageSize='${meetVideoRoomVO.pageSize}'  pageNo='${meetVideoRoomVO.pageNo}' url="meet/chooseRelevanceMeet" recordCount="${meetVideoRoomVO.totalRecord}"/>
        </div>
    </div>
</div>
<!--</form>-->
</body>
 <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
 <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/aa.js"></script>
 <script type="text/javascript" language="javascript">
 	/**
 	*选择相关上传的项目，获取编号，极其名称
 	*/
	function chooseRelevanceUploadeMeet(meetNo,confName){
		var index = parent.layer.getFrameIndex(window.name); 
		parent.chooseOk(meetNo,confName,'1');
		parent.layer.close(index);
	}
	//function optionUpload(projectNo,projectName){
		/* $.ajax({
			url:"admin/uploadData",
			type:"GET"
		}) */
		//alert(document.getElementById("projectId").innerHTML);
		//console.log("projectId:"+document.getElementById("projectId").innerText);
		//window.location.href = 'admin/project/OptionButton';\

		//console.log("projectNo:"+projectNo);
		//window.location.href = "admin/project/OptionButton?projectNo="+projectNo;//这里也获取到了值;
		//获取窗口索引
		/* var index = parent.layer.getFrameIndex(window.name); 
		parent.chooseOk(projectNo,projectName);
		parent.layer.close(index); */

/* 		console.log("projectId:"+projectNo);
		$.ajax({
			url:"admin/project/OptionButton?projectId="+projectNo,
			type:"GET",
			success:function(){
				console.log("projectId:");
				window.opener = window;
				window.close();
			}
		}) ; */
		
	//}	
	
 </script>
</html>