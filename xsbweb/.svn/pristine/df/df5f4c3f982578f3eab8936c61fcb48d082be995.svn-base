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
    <title>数据字典页面</title>
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
      <form class="form-inline" action="enum/searchEnumManage.do" name="confForm" method="get">
        <input type="hidden" name="_method" value="get"/>
        <div class="col-md-12 mt10">
            <input type="text" class="form-control" id="search" name="searchInformation" placeholder="请输入查询内容" value="${(searchInformation == '')?'':searchInformation }">
            
            <button type="submit" class="btn btn-warning">查询</button>
            <a href="enum/toAddData.do" role="button" class="btn btn-danger">新建</a>
        </div>
        <div class="shade" id="shade" style="display:none;">
		     <div class="prompt" style="width:500px;margin-top:300px;">
		      	   编号：<input type="text" class="input-i" name="meetNo" disabled="disabled"/><br/>
		                           名称：<input type="text" class="input-i" name="confName" disabled="disabled"/><br/>
	         	   地址：<input type="text" placeHolder="请输入直播播放地址" name="baseUrl" class="input-i"/><br/>
		         <input type="hidden" name="meetRole"/>
		         <input type="button"  value="确认" class="btn-s" onclick="addBaseUrl();"/>
		         <input type="button"  value="取消" class="btn-c" onclick="closeShade();"/>
		     </div>
		</div>
	  </form>
    </div>
    <div class="row">
        <div class="col-md-12 mt10"> 
        	<aa:zone name="confListZone">
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                    	<th>字段完整名称</th>
                        <th>枚举键</th>
                        <th>枚举值</th>
                        <th>隶属父级枚举键</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${enumList !=null }">
                		<c:forEach items="${enumList }" var="emList" varStatus="index">
                			<tr>
                				<td>${emList.enumFullName }</td>
                				<td>${emList.enumCode }</td>
                				<td>${emList.enumDesc }</td>
                				<td>${emList.enumGroupCode }</td>
                			 	<td>           			 	
			                       	
			                       	<%-- <a href="enum/toEditEnum.do?enumFullName=${emList.enumFullName}&&enumCode=${emList.enumCode }">编辑</a> --%>
			                       	<%-- <a href="enum/toEnum.do?enumFullName=${enumList.enumFullName }">edit</a> --%>
			                        <a href="javaScript:void(0);" onclick="upEnumData('${emList.enumFullName}','${emList.enumCode }')">编辑</a>
			                        
			                        <a href="javaScript:void(0);" onclick="deleteEnumData('${emList.enumFullName}','${emList.enumCode }')">删除</a>
		                        </td> 
                			</tr>
                		</c:forEach>
                	</c:if>
                </tbody>
            </table>
            </aa:zone>
             <page:pager pageSize="${enumVO.pageSize}" pageNo="${enumVO.pageNo}" url="admin/enum/toEnumManage.do" recordCount="${enumVO.totalRecord}"/> 
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
	function deleteEnumData(enumFullName,enumCode){
		if(!confirm("确定要删除该数据吗？")){
			return;
		}
		   document.forms[0].action="enum/deleteEnumManage.do?enumFullName="+enumFullName+"&&enumCode="+enumCode;
		   ajaxAnywhere.submitAJAX();
	}
	function upEnumData(enumFullName,enumCode){
/* 		if(!confirm("确定要更改该数据吗？")){
			return;
		} */
		   window.location="enum/toEnum.do?enumFullName="+enumFullName+"&&enumCode="+enumCode;
		  
	}
	
	
/*  	ajaxAnywhere.formName="confForm";
	ajaxAnywhere.getZonesToReload = function(){
		return "confListZone";
	} 
	function deleteForeVideoMeetRoom(meetNo){
		if(!confirm("确定要删除该数据吗？")){
			return;
		}
		
		document.forms[0].action="meet/deleteVideoMeetRoomByMeetNo.do?meetNo="+meetNo;
		ajaxAnywhere.submitAJAX();
	}
	
	function updateForeVideoMeetRoom(meetNo){
		alert("该功能暂未提供！");
	}
	//弹出baseUrl输入框
	function toAddBaseUrl(meetNo,confName,meetRole){
		$("#shade").css("display","block");
		$("[name='meetNo']").val(meetNo);
		$("[name='confName']").val(confName);
		$("[name='meetRole']").val(meetRole);
	}
	//新增baseUrl
	function addBaseUrl(){
		var baseUrl = $("[name='baseUrl']").val();
		if(baseUrl==null || $.trim(baseUrl)==""){
			alert("url地址不能为空");
		}
		var meetNo = $("[name='meetNo']").val();
		document.forms[0].action="admin/meet/"+meetNo+"/updateMeetToShowStatus";
		ajaxAnywhere.submitAJAX();
		closeShade();
	}
	//关闭baseUrl输入框
	function closeShade(){
		$("#shade").css("display","none");
		$("[name='meetNo']").val("");
		$("[name='confName']").val("");
		$("[name='baseUrl']").val("");
		$("[name='meetRole']").val("");
	} */
 </script>
</html>