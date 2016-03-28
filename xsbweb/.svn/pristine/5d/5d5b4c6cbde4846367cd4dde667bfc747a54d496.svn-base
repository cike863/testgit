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
    
    <title>编辑资源</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/kindeditor/plugins/code/prettify.css" />
	<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
	<script  type="text/javascript" src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js" ></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/js/util/dtpicker.js" ></script>
	<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/plugins/code/prettify.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/layer/layer.js" ></script>
	<script type="text/javascript">
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
	window.onload = function(){	
		 if(${trsMedia.mediaAuthor !=null && trsMedia.mediaAuthor !=''}){
			var select = $("#mediaAuthor option");				 
			for(var i=0; i<select.length; i++){  
				//console.log(select[i].value);
		        if(select[i].value == '${trsMedia.mediaAuthor}'){
		            select[i].selected = true;  
		            break;  
		        }  
		    }  
		}
	}
	</script>
  </head>
  
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
            <form class="form-inline" action="admin/media/${trsMedia.mediaNo}" method="post">
            <input type="hidden" name="_method" value="put" />
            <table class="table table-bordered table-hover table-responsive">
                <tbody>
                 <tr>
                    <td>资源编码</td>
                    <td><input type="text" class="form-control"  name="mediaNo" value="${trsMedia.mediaNo}" readonly="readonly"></td>
                 </tr>
                 <tr>
                    <td>资源名称/优酷code</td>
                    <td><input type="text" class="form-control"  name="mediaName" value="${trsMedia.mediaName}"></td>
                </tr>
                <tr>
                    <td>资源格式</td>
                    <td><input type="text" class="form-control"  name="mediaType" value="${trsMedia.mediaType}"></td>
                    
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
                	<td><input type="text" class="form-control"  name="mediaFormat" value="${trsMedia.mediaFormat}"></td>
                </tr>
                <tr>
                	<td>作者</td>
                	<td><select id="mediaAuthor" name="mediaAuthor"> 
                				<option value=""></option>
								<option value="annc">公司公告</option>
								<option value="drct ">定向增发</option>
								<option value="prlst">拟挂牌</option>
								<option value="video_uku">优酷视频</option>
								<option value="anlz">个股研报</option>
							</select></td>
                </tr>
                <tr>
                	<td>资源管理objectId</td>
                	<td><input type="text" class="form-control"  name="objectId" value="${trsMedia.objectId}" readonly="readonly">
                	<button type="button" onclick="chooseProject('project')" class="btn btn-success">选择项目</button>
                		&nbsp;&nbsp;&nbsp;&nbsp;
                	<button type="button" onclick="chooseProject('meet')" class="btn btn-success">选择路演</button>
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
