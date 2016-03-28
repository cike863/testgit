<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://ajaxanywhere.sourceforge.net/" prefix="aa"%>
<%@ taglib uri="http://java.xsb.com/xsbweb/tag/pager" prefix="page"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>词条编辑</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .mt10{margin-top:10px;}
        .row-div{width:100%;padding-left:5%;}
        .row-div-div{width:30%;display:inline-block;}
    </style>
</head>
 	<script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/dtpicker/js/laydate.js" ></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/layer/layer.js" ></script>
 <script type="text/javascript" language="javascript">		
 		//获取窗口索引	
 		var index = parent.layer.getFrameIndex(window.name); 
		//关闭iframe
		window.onload = function(){
			$("#closeIframe").click(function(){
				 parent.layer.close(index);
			});
			/* $("form").submit(function(e){
				console.log("1234");
				
				$("form").submit();
			}); */
		};
		function ok(){
			var word=$("#word").val();
			var oldWord=$("#oldWord").val();
			var object=$("#object").val();
			var labOrder=$("#labOrder").val();
			parent.editIsOK(word,oldWord,object,labOrder);
			parent.layer.close(index);
			/* 
			$.ajax({
				type : 'post',
				url : "admin/project/editword",
				contentType : 'application/json',
				data : JSON.stringify({
					'word' : $("#word").val(),
					'oldWord' : $("#oldWord").val(),
					'object' : $("#object").val(),
					'labOrder' : $("#labOrder").val()
				}),
				cache : false,
				dataType : 'json',
				async : false,
				success : function(data) {
					if (data.resultCode == '1') {
						console.log("123456");
						//window.location.href ="admin/project/words?projectNo="+$("#object").val();
						parent.layer.close(index);
						demo();
						//
					} else {
						alert("修改失败");
					}
					;
					
					
				},
				error : function() {
					alert("添加失败");
				}
			}); */
		}
		function demo(){  
		    $.ajax({
		    type : "GET",  
		     url :"admin/project/words",  
		     data:{"projectNo":$("#object").val()},  
		     async:false,  
		     cache :false,  
		     dataType : "json"
		     }); 
		}
	</script>
<body>
<!--<form action="meet/toMeetRoomList.do" name="confForm" method="post">-->
${error }
<div class="container-full">
		<div class="container">
				<form class="form-inline"action="admin/project/editword" method="post" >
					<input type="hidden" name="_method" value="get" />
					<table class="table table-bordered table-hover table-responsive">
						<tr>
							<td>词条名称</td>
							<td hidden="hidden">词条名称</td>
							<td>词条顺序</td>
						</tr>
						<tr>
							<td><select id="word" name="word">
									<option value="${word.word }" selected='selected'>${word.word }</option>
									<c:if test="${projectWords!=null}">
										<c:forEach items="${projectWords}" var="words"
											varStatus="index">
											<option value="${words.enumDesc }">${words.enumDesc }</option>
										</c:forEach>
									</c:if>
							</select></td>
							<td hidden="hidden"><input type="hidden" id="oldWord" 
								value=' ${word.word }' name="oldWord" class="form-control" />
								<input type="hidden" id="object" 
								value=' ${projectNo }' name="object" class="form-control" />
							</td>
							<td><input type="text" value=' ${word.labOrder}'
								id="labOrder" name="labOrder" style="margin-bottom:5px;" class="form-control" /></td>
						</tr>
						<tr>
							<td colspan="3" class="text-center">
								<button  class="btn btn-success" onclick="ok();">提交</button>
								<button class="btn btn-danger" id="closeIframe">关闭</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>