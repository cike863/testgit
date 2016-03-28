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
    
    <title>新增资源</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
	<script  type="text/javascript" src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js" ></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/layer/layer.js" ></script>
  </head>
  <script type="text/javascript">
  	function toUpload(mediaType){
  		var dir = $("#toUploadMediaType").val();
  		//console.log(dir);
		var url = "admin/news/uploadLayer.do?mediaNo="+mediaType+"&dir="+dir;
		//console.log(url);
		layer.open({
		    type: 2,
		    area: ['800px', '530px'],
		    fix: false, //不固定
		    maxmin: true,
		    content: url
		});
	}
  	function uploadIsOk(mediaType,mediaNo,url,returnPath,newFileName,format,size){
  		$("[name=mediaName]").val(newFileName);
  		$("[name=mediaFormat]").val(format);
  		$("[name=mediaNo]").val(mediaNo);
  		$("[name=mediaLocation]").val(returnPath);
  		$("[name=mediaSize]").val(parseInt(size)/1024);
  		$("[name=mediaType]").val(newFileName.substring(newFileName.lastIndexOf(".") + 1).toLowerCase());
	}
  	function chooseProject(type){
		//$("[name='_method']").val("get");
		//var url = "admin/project";
		var url = "";
		if(type=='project'){//选择关联项目
			url = "admin/chooseRelevanceProject";
		}
		if(type=='meet'){//选择关联直播
			url = "meet/chooseRelevanceMeet";
		}
		layer.open({
			type:2,
			area:['1000px','620px'],
			fix:false,
			maxmin:true,
			content:url
		});
	}
	//设置选择函数，将数据存入到
	function chooseOk(arg1,arg2,arg3){
		//console.log(arg3);
		var mediaAuthor=$("#mediaAuthor").val();
		//$("[name='objectId']").val(arg1);
		 if(mediaAuthor=='annc'){
			 if(arg1.indexOf("meetv")>-1){
				 $("[name='objectId']").val(arg1);
			 }else{
				 $("[name='objectId']").val(arg3);
			 }
		}else{
			$("[name='objectId']").val(arg1);
		}	
	}
  </script>
  <body>

	<!-- 显示错误信息 -->
	<c:if test="${allErrors!=null }">
		<c:forEach items="${allErrors }" var="error">
			${ error.defaultMessage}<br/>
		</c:forEach>
	</c:if>
	<div class="container-full">
	 <div class="row">
        <div class="col-md-12 mt20">
            <form class="form-inline" action="admin/media" method="post">
            <input type="hidden" name="_method" value="post" />
            <table class="table table-bordered table-hover table-responsive">
                <tbody>
                 <tr>
                    <td>资源名称/优酷code</td>
                    <td>
                    	<input type="text" class="form-control"  name="mediaName" value="${trsMedia.mediaName}">
                    	<select id="toUploadMediaType" > 
								<option value="image">图片</option>
								<option value="media">视频</option>
								<option value="file">文件</option>
							</select>
                    	<input type="button" onclick="toUpload('media');" value="非优酷资源  文件上传" class="btn btn-success"/>
                    </td>
                </tr>
                <tr>
                    <td>资源格式</td>
                    <td><input type="text" class="form-control"  name="mediaType" value="${trsMedia.mediaFormat}">
                    		<input type="hidden" class="form-control"  name="mediaNo" value="${trsMedia.mediaNo}">
                    	</td>
                </tr>
                <tr>
                    <td>资源路径</td>
                     <td><input type="text" class="form-control"  name="mediaLocation" value="${trsMedia.mediaLocation}"></td>
                </tr>
                 <tr>
                	<td>资源大小</td>
                	<td><input type="text" class="form-control"  name="mediaSize" value="${trsMedia.mediaSize}"></td>
                </tr>
                <tr>
                	<td>资源类型</td>
                	<td><input type="text" class="form-control"  name="mediaFormat" value="youku"  readonly="readonly"></td>
                </tr>
                <tr>
                	<td>作者</td>
					<td><select id="mediaAuthor" name="mediaAuthor"> 
								<option value="annc" >公司公告</option>
								<option value="drct ">定向增发</option>
								<option value="prlst">拟挂牌</option>
								<option value="video_uku">优酷视频</option>
								<option value="anlz">个股研报</option>
							</select></td>
						<%-- <td><input type="text" class="form-control"  name="mediaAuthor" value="${trsMedia.mediaAuthor}"></td> --%>
                </tr>
                <tr>
                	<td>资源管理objectId</td>
                	<td>
                		<input type="text" class="form-control"  name="objectId" value="${trsMedia.objectId}"  required="required">
                		<button type="button" onclick="chooseProject('project')" class="btn btn-success">选择项目</button>
                		&nbsp;&nbsp;&nbsp;&nbsp;
                		<button type="button" onclick="chooseProject('meet')" class="btn btn-success">选择路演</button>
                	</td>
                </tr>
                <tr>
                	<td>资源描述</td>
                	<td><textarea type="text" class="form-control"  name="mediaDesc" rows="5">${trsMedia.mediaDesc}</textarea></td>
                </tr>
                <tr>
                    <td colspan="2" class="text-center">
                        <button type="button" class="btn btn-success" onclick="submit();">提交</button>
                    </td>
                </tr>
                </tbody>
            </table>
            </form>
        </div>
    </div>
	</div>
  </body>
  
</html>
