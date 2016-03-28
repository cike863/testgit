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
            <form class="form-inline" action="admin/media" method="post"  name="mediaForm">
            	<span onclick="batchDeleteMedia();"class="btn btn-danger" >删除</span>
                <div class="input-group">
                	<span class="input-group-addon">资源编号</span>
                	<input type="text" class="form-control" id="mediaNo" name="mediaNo" placeholder="请输入查询内容" value="${(trsMedia.mediaNo == '')?'':trsMedia.mediaNo}" >
                </div>
                <div class="input-group">
                	<span class="input-group-addon">资源格式</span>
                	<input type="text" class="form-control" id="mediaFormat" name="mediaFormat" placeholder="请输入查询内容" value="${(trsMedia.mediaFormat == '')?'':trsMedia.mediaFormat}" >
                </div>
                <div class="input-group">
                	<span class="input-group-addon">资源描述</span>
                	<input type="text" class="form-control" id="mediaDesc" name="mediaDesc" placeholder="请输入查询内容" value="${(trsMedia.mediaDesc == '')?'':trsMedia.mediaDesc}" >
                </div>
               <input type="hidden" name="_method" value="get" /> 
               <input type="hidden" name="pageSize" id="pageSize" value="${trsMedia.pageSize}" /> 
                <button class="btn btn-warning" type="submit">查询</button><!-- type="submit"  onclick="serach();-->
                <a href="admin/media/toAddMedia" role="button" class="btn btn-success">新建</a>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 mt10">
        	<aa:zone name="mediaListZone">
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                    	<th><input type="checkbox" name="allCheckBox"  id="allCheckBox" onclick="checkMediaAll(this);" >全选</th>
                    	<th>项目编号</th>
                        <th>视频名称</th>
                        <th>视频地址</th>
                        <th>视频格式</th>
                        <th>视频描述</th>
                        <th>创建时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${mediaList!=null}">
	                	<c:forEach items="${mediaList}" var="media" varStatus="index">
	                		<tr>
	                			<td><input type="checkbox" name="mediaNoCheckBox" value="${index.index}"></td>
	                			<td id="media_${index.index}">${media.mediaNo}</td>
	                			<td>${media.mediaName}</td>
		                        <td>${media.mediaLocation}</td>
		                        <td>${media.mediaFormat}</td> 
		                        <td>${media.mediaDesc}</td>
		                        <td>${media.createDate}</td>                      	
		                        <td>
		                        	<c:if test="${project.projectNo ne '' }">
		                        		<a href="<%=path%>/admin/media/toEditMedia?mediaNo=${media.mediaNo}" >编辑</a>
			                        	<%--  | <a href="javascript:void(0);" onclick="deleteMedia('${media.mediaNo}')">删除</a>  --%>
		                        	</c:if>
	                        	</td>
		                    </tr>
	                	</c:forEach>
                	</c:if>
                </tbody>
            </table>
            </aa:zone>
            <%-- <page:pager pageSize="${trsProject.pageSize}" pageNo="${trsProject.pageNo}" url="${serachInformation == ''?\"admin/project\":\"admin/project/serach\"}"
            recordCount="${trsProject.totalRecord}"/> --%>
            <page:pager pageSize="${trsMedia.pageSize}" pageNo="${trsMedia.pageNo}" url="admin/media"  recordCount="${trsMedia.totalRecord}"/>
        </div>
    </div>
</div>
<!--</form>-->
</body>
 <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
 <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/aa.js"></script>
 <script type="text/javascript" language="javascript">
 	ajaxAnywhere.formName="mediaForm";
	ajaxAnywhere.getZonesToReload = function(){
		return "mediaListZone";
	}
	function deleteMedia(mediaNo){
		if(!confirm("确定要删除该数据吗？")){
			return;
		}
		$("[name='_method']").val("delete");
		$.ajax({
			url:"admin/media/delete/"+mediaNo,
			type:"POST",
			contentType : 'application/json',
			async: false,
			dataType:"json",
			success:function(data){
				if(data==0){
					alert("操作失败");
				}else if(data==1){
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
	//全选
	function checkMediaAll(k){
  		if($(k).is(':checked')){
  			$("input[name='mediaNoCheckBox']").each(function(){	
				$(this).prop("checked",true);
			});
  		}else{
  			$("input[name='mediaNoCheckBox']").each(function(){	
  				$(this).prop("checked",false);
			});
  		}	
  	}
	function batchDeleteMedia(){
		if(!confirm("确定要删除该数据吗？")){
			return;
		}
  		var mediaNoArrs = "";
  		$("input[name='mediaNoCheckBox']:checked").each(function(){ 
  			mediaNoArrs += $("#media_"+$(this).val()).html()+",";
  		});
  		mediaNoArrs=mediaNoArrs.toString().substring(0, mediaNoArrs.toString().length-1);
  		//console.log(mediaNoArrs);
  		$.ajax({
  			url : "admin/media/"+mediaNoArrs+"/del",
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
	
 </script>
</html>