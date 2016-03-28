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
    
    <title>编辑三板百科</title>
    
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
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/dtpicker/js/laydate.js" ></script>
	<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/plugins/code/prettify.js"></script>
 	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="baikeContent"]', {
				cssPath : 'kindeditor/plugins/code/prettify.css',
				uploadJson : 'admin/news/uploadJson.do',
				fileManagerJson : 'admin/news/fileManagerJson.do',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['bkForm'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['bkForm'].submit();
					});
				}
			});
			$("span").each(function(i){
			   if(this.title == "批量图片上传"){
			   		$(this).css("display","none");
			   }
			 });
			//prettyPrint();
		});
	</script>
	<script type="text/javascript">
	
		
		
		!function(){
			laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
			laydate({elem: '#demo'});//绑定元素
		}();
		window.onload = function(){
			if(${bk.baikeType=='tz'}){
				$("#tz").attr("checked","checked");
			}
			if(${bk.baikeType=='rm'}){
				$("#rm").attr("checked","checked");
			}
			if(${bk.baikeType=='gp'}){
				$("#gp").attr("checked","checked");
			}
		}
		
		
	</script>
  </head>
  
  <body>
	<!-- 显示错误信息 
	<c:if test="${allErrors!=null }">
		<c:forEach items="${allErrors }" var="error">
			${ error.defaultMessage}<br/>
		</c:forEach>
	</c:if>
-->

	<div class="container-full">
		<div class="row">
			<div class="col-md-12 mt10">
				<form:form action="admin/encyclopedias/editBaike.do"  name="bkForm" method="post" class="form-horizontal">
					<input type="hidden" name="baikeId" value="${bk.baikeId }"/>
					<div class="form-group">
						<label class="col-md-1 control-label">百科标题</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="baikeTitle" value="${bk.baikeTitle }" placeholder="请输入百科标题"/><form:errors path="baikeTitle"></form:errors>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">发布日期</label>
						<div class="col-md-2"><!--  hh:mm:ss -->
							<input placeholder="请输入日期" class="laydate-icon" name="createdDate" style="width:130px;height:30px" 
							value="${bk.createdDate }" onClick="laydate({istime: true, format: 'YYYY-MM-DD'})">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-1 control-label">排序</label>
						<div class="col-md-2">
							<input placeholder="请输入排序" type="text" class="form-control" name="baikeOrder" value="${bk.baikeOrder }" ">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">百科类型</label>
						<div class="col-md-9">
							<input type="radio"  name="baikeType" value="tz" id="tz">投资&nbsp;&nbsp;&nbsp;
							<input type="radio"  name="baikeType" value="gp"  id="gp">挂牌&nbsp;&nbsp;&nbsp;
							<input type="radio"  name="baikeType" value="rm"  id="rm">入门
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">内容</label>
						<div class="col-md-9">
							<textarea name="baikeContent" style="width: 80% !important;height: 500px !important;" cols="100" rows="8">${bk.baikeContent}</textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-md-9 mt10 text-center">
							<button type="submit" class="btn btn-success">提交</button>
							<button type="button" class="btn btn-danger" onclick="history.go(-1)">返回</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>

  </body>
</html>
