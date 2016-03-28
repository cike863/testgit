<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://ajaxanywhere.sourceforge.net/" prefix="aa"%>
<%@ taglib uri="http://java.xsb.com/xsbweb/tag/pager" prefix="page"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ResourceBundle bundle = ResourceBundle.getBundle("config");  
String	hostName = bundle.getString("ftp_url_pro");  //IP地址
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>项目列表</title>
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
                <span onclick="batchDeleteProject();"class="btn btn-danger">删除</span>
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
                <input type="hidden" name="pageSize" value="${trsProject.pageSize}" /> 
                <input type="hidden"name="projShowStatus" value="0">
                <button class="btn btn-warning" type="submit">查询</button><!-- type="submit"  onclick="serach();-->
                <a href="admin/project/toAddForieProject.do" role="button" class="btn btn-success">新建</a>
                <span onclick="resetOrder();"class="btn btn-danger">一键还原排序</span>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 mt10">
        	<aa:zone name="confListZone">
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                    	<th><input type="checkbox" name="allCheckBox"  id="allCheckBox" onclick="checkProjectAll(this);" >全选</th>
                    	<th>项目编号</th>
                    	<th>关联公司代码</th>
                        <th>项目名称</th>
                        <th>上线时间</th>
                        <th>最后编辑时间</th>
                        <th>路演状态</th>
                        <th>前台显示</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${trsProjectVOList!=null}">
	                	<c:forEach items="${trsProjectVOList}" var="project" varStatus="index">
	                		<tr>
	                			<td><input type="checkbox" name="projectNoCheckBox" value="${index.index}"></td>
	                			<td id="project_${index.index}">${project.projectNo}</td>
	                			<td>
	                				<c:choose>
		                        			<c:when test="${project.projRole ge 32 && project.projRole lt 64}">
		                        				<a href="http://<%=hostName%>:8080/roaddetail.do?path=${project.projMediaPath}&stockQuote=--&stockId=${project.projCpCode}&projectNos=${project.projectNo}&projectType=1" target="_blank">${project.projCpCode}</a>
		                        			</c:when>
		                        			<c:when test="${project.projRole ge 64 && project.projRole lt 128}">
		                        				<a href="http://<%=hostName%>:8080/roaddetail.do?path=${project.projMediaPath}&stockQuote=--&stockId=${project.projCpCode}&projectNos=${project.projectNo}&projectType=2" target="_blank">${project.projCpCode}</a>
		                        			</c:when>
		                        		</c:choose>
	                			</td>
		                        <td>${project.projName}</td>
		                        <td>${project.projOnlineDate}</td>
		                        <td>${project.lastEditDate}</td>
		                        <td>
		                        <c:choose>
		                        	<c:when test="${project.projRole ge 32 && project.projRole lt 64}">
		                        		路演中
		                        	</c:when>
		                        	<c:when test="${project.projRole ge 64 && project.projRole lt 128}">
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
		                        	<c:when test="${project.isShow eq '2'}">
		                        		隐藏
		                        	</c:when>
		                        	<c:otherwise>
		                        		无数据
		                        	</c:otherwise>
		                        </c:choose>
		                        </td>
		                        <td>	
		                        	<c:if test="${project.projectNo ne '' }">
		                        		<a href="admin/project/lastEditDate.do?projectNo=${project.projectNo}">置顶</a> |
		                        		<a href="<%=path%>/admin/project/${project.projectNo}" <%-- onclick="updateForeProject('${project.projectNo}')" --%>>编辑</a> | 
			                        	<a href="javascript:void(0);" onclick="queryApplyList('${project.projectNo}')">查看报名列表</a> |
			                        	<a href="javascript:void(0);" onclick="getitems('${project.projectNo}')">查看item</a> |
			                        	<a href="javascript:void(0);" onclick="getwords('${project.projectNo}')">查看词条</a> |
			                        	<a href="javascript:void(0);" onclick="addNews('${project.projectNo}','${project.projCpCode}')">公司新闻</a>
			                        	<%-- <c:choose>
		                        			<c:when test="${project.projRole ge 32 && project.projRole lt 64}">
		                        				<a href="http://<%=hostName%>:8080/roaddetail.do?path=${project.projMediaPath}&stockQuote=--&stockId=${project.projCpCode}&projectNos=${project.projectNo}&projectType=1" target="_blank">预览</a>
		                        			</c:when>
		                        			<c:when test="${project.projRole ge 64 && project.projRole lt 128}">
		                        				<a href="http://<%=hostName%>:8080/roaddetail.do?path=${project.projMediaPath}&stockQuote=--&stockId=${project.projCpCode}&projectNos=${project.projectNo}&projectType=2" target="_blank">预览</a>
		                        			</c:when>
		                        		</c:choose> --%>
			                        	<%--  | <a href="javascript:void(0);" onclick="deleteForeProject('${project.projectNo}')">删除</a> --%>
		                        	</c:if>
		                        	
		                    </tr>
	                	</c:forEach>
                	</c:if>
                </tbody>
            </table>
            </aa:zone>
            <%-- <page:pager pageSize="${trsProject.pageSize}" pageNo="${trsProject.pageNo}" url="${serachInformation == ''?\"admin/project\":\"admin/project/serach\"}"
            recordCount="${trsProject.totalRecord}"/> --%>
            <page:pager pageSize="${trsProject.pageSize eq '0' ?1:trsProject.pageSize}" pageNo="${trsProject.pageNo}" url="admin/project"  recordCount="${trsProject.totalRecord}"/>
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
	function deleteForeProject(projectNo){
		if(!confirm("确定要删除该数据吗？")){
			return;
		}
		$.ajax({
			url:"admin/project/"+projectNo+"/delete",
			type:"POST",
			contentType : 'application/json',
			async: false,
			dataType:"json",
			success:function(data){
				if(data.resultCode=='0'){
					alert("操作失败");
				}else if(data.resultCode=='1'){
					alert("操作成功");
					document.location.reload();
				}else{
					alert("操作失败");
				}
			},
			error:function(){
				alert("操作失败");
			}
		});
	}
	
	function updateForeProject(projectNo){
		$.ajax({
			url:"admin/project/"+projectNo,
			type:"GET"
		});
	}
	
	function queryApplyList(projectNo){
		window.location="admin/queryApplyList.do?objectNo="+projectNo;
	}
	function getitems(projectNo){
		window.location="admin/item?projectNo="+projectNo;
	}
	function getwords(projectNo){
		window.location="admin/project/words?projectNo="+projectNo;	
	}
	function addNews(projectNo,projCpCode){
		//console.log("admin/news/toAddNews.do?projectNo="+projectNo+"&projCpCode="+projCpCode);
		window.location="admin/news/toAdminNews.do?projectNo="+projectNo+"&projCpCode="+projCpCode;	
	}
	/**function serach(){
		//var information = $("#serach").value;
		var information=document.getElementById("search").value;
		//console.log("information:"+information);
		console.log("information:"+information);
		if(information  == null || information  == undefined || information  == ''){//判断搜索输入框是否有值
			alert("请输入搜索内容");
		return;
		}
		$.ajax({
			url : "admin/project/serach?serachInformation="+information,
			//date:"serachInformation="+information,
			type: "post",
			async:false,
			success:function(){
				//window.location.reload; 
				document.location.reload();
			}
		});
	}*/
	//全选
	function checkProjectAll(k){
  		if($(k).is(':checked')){
  			$("input[name='projectNoCheckBox']").each(function(){	
				$(this).prop("checked",true);
			});
  		}else{
  			$("input[name='projectNoCheckBox']").each(function(){	
  				$(this).prop("checked",false);
			});
  		}	
  	}
	function batchDeleteProject(){
		if(!confirm("确定要删除该数据吗？")){
			return;
		}
  		var projectNoArrs = "";
  		$("input[name='projectNoCheckBox']:checked").each(function(){ 
  			projectNoArrs += $("#project_"+$(this).val()).html()+",";
  		});
  		projectNoArrs=projectNoArrs.toString().substring(0, projectNoArrs.toString().length-1);
  		//console.log(projectNoArrs);
  		$.ajax({
  			url : "admin/project/"+projectNoArrs+"/del",
  			//contentType : 'application/json',JSON.stringify(
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
	function resetOrder(){
		if(!confirm("确定要一键还原排序（默认按照上线时间）？")){
			return;
		}
		$.ajax({
  			url : "admin/project/resetOrder.do",
			async: false,
			success : function(data) {
				if (data.resultCode == '1') {
					window.location.reload();
				} else {
					alert("一键还原排序失败");
				};
			},
			error : function() {
				alert("一键还原排序失败");
			}
		});
	}
 </script>
</html>