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
    
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加词条</title>
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
		};
		function ok(){
			var word=$("#word").val();
			var object=$("#object").val();
			var labOrder=$("#labOrder").val();
			parent.addIsOK(word,object,labOrder);
			parent.layer.close(index);
		}
		var word="";
		function checkWords(){
			word="";
			/* $("input[name='newsTypeEnum']:checked").each(function(){
					newsRole += parseInt($(this).val());	
			});
			$("[name='newsRole']").val(newsRole); */
			//console.log($("[name='word']").val());
			$("input[name='enumDesc']:checked").each(function(){
				word += $(this).val()+",";
			});
		//console.log(word);
			word=word.toString().substring(0, word.toString().length-1);
			$("[name='word']").val(word);
			//console.log($("[name='word']").val());
		}
		function otherWordFunction(s){
			//console.log("change");
			var otherWord = $("[name='otherWord']").val();
			if(otherWord.length==0){
				$("[name='word']").val(word);
			}
			if(otherWord.length>0){
				$("[name='word']").val(word+","+otherWord);
			}
		}
	</script>
<body>
<!--<form action="meet/toMeetRoomList.do" name="confForm" method="post">-->
${error }
<div class="container-full">
		<div class="container">
				<form class="form-inline"action="admin/project/addword" method="post">
					<input type="hidden" name="_method" value="get" />
					<input type="hidden" value=' ${projectNo }'  id="object" name="object"  />
					<input type="hidden"  name="word"  value="" id="word" />
					<table class="table table-bordered table-hover table-responsive">
						<tr>
							<td>词条名称</td>
							<%-- <td><select id="word" name="word">
									<c:if test="${projectWords!=null}">
										<c:forEach items="${projectWords}" var="words"
											varStatus="index">
											<option value="${words.enumDesc }">${words.enumDesc }</option>
										</c:forEach>
									</c:if>
							</select></td> --%>
							<td>
								<c:forEach items="${projectWords }" var="words" varStatus="index">
								<input type="checkbox" name="enumDesc" onclick="checkWords()" value="${words.enumDesc }"><span style=" display:inline-block; width:20%">${words.enumDesc }</span></input>
								<c:if test="${(index.index+1)%4==0}">
								</br></c:if>
							</c:forEach>
							</td>
						</tr>
						<tr>
							<td>以上不存在</td>
							<td>
									<input type="text"  name="otherWord"  onchange="otherWordFunction(this.value)" value=""/>
							</td>
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