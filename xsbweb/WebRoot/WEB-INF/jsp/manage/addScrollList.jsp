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
        .row-div{width:100%;padding-left:5%;}
        .row-div-div{width:30%;display:inline-block;}
    </style>
</head>
 	<script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/dtpicker/js/laydate.js" ></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/layer/layer.js" ></script>
 <script type="text/javascript" language="javascript">
 		var index = parent.layer.getFrameIndex(window.name); 
		window.onload = function() {
			$("#closeIframe").click(function(){
				 parent.layer.close(index);
			});
		}
		function checkNewType(enumDesc) {
			var objectId = $("#objectId").val();
			//console.log(objectId);
			if (objectId.length==0) {
				alert("请输入id");
				return;
			}
			$.ajax({
				url : "enum/editScrollList?objectId=" + objectId + "&enumDesc="
						+ enumDesc,
				async: false,
				success : function(data) {
					if (data.resultCode == '0') {
						alert("修改失败");
					}
				},
				error : function() {
					alert("修改失败");
				}
			});
		}
	</script>
<body>
<!--<form action="meet/toMeetRoomList.do" name="confForm" method="post">-->
${error }
<div class="container-full">
      <%--<div class="row">
        <div class="col-md-12 mt10">
           <form class="form-inline" action="enum/getScrollList" method="post"  name="confForm"><!--action="admin/project/serach?serachInformation="+information, name="confForm" method="post"-->
                
                <div class="input-group">
                	<span class="input-group-addon">编号</span>
                	<input type="text" class="form-control" id="enumGroupCode" name="enumGroupCode" placeholder="请输入查询内容" value="${enumVO.enumGroupCode}" >
                </div>
                <div class="input-group">
                	<span class="input-group-addon">名称</span>
                	<input type="text" class="form-control" id="enumFullName" name="enumFullName" placeholder="请输入查询内容" value="${enumVO.enumFullName}" >
                </div>
                <input type="hidden" name="_method" value="get" /> 
                <button class="btn btn-warning" type="submit">查询</button><!-- type="submit"  onclick="serach();-->
              <!--   <a href="javascript:void(0);" onclick="toAddItem();" class="btn btn-success">新建</a> -->
            </form> 
        </div>
    </div>--%>
    <!-- <div class="row">
    	<span class="input-group-addon">对应id</span>
        <input type="text" class="form-control" id="objectId" name="objectId" >
    </div>
    <div class="row">
       <input type="checkbox" class="col-md-4" name=""  value="obj_scroll_1"  onclick="checkNewType('obj_scroll_1')">
       <input type="checkbox" name="enumDesc"  value="obj_scroll_1"  onclick="checkNewType('obj_scroll_1')"><span>路演大厅_轮播1</span></input>
       <input type="checkbox" name="enumDesc"  value="obj_scroll_2"  onclick="checkNewType('obj_scroll_2')"><span>路演大厅_轮播2</span></input>
       <input type="checkbox" name="enumDesc"  value="obj_scroll_3"  onclick="checkNewType('obj_scroll_3')" ><span>路演大厅_轮播3</span></input></br>
       <input type="checkbox" name=""  value="obj_scroll_1"  onclick="checkNewType('obj_scroll_1')">
       <input type="checkbox" name="enumDesc"  value="obj_scroll_4"  onclick="checkNewType('obj_scroll_4')"><span>路演大厅_轮播4</span></input>
       <input type="checkbox" name="enumDesc"  value="obj_scroll_5"  onclick="checkNewType('obj_scroll_5')"><span>路演大厅_轮播5</span></input>
       <input type="checkbox" name="enumDesc"  value="news_scroll_1"  onclick="checkNewType('news_scroll_1')"><span>三版头条_轮播1</span></input></br>
       <input type="checkbox" name=""  value="obj_scroll_1"  onclick="checkNewType('obj_scroll_1')">
       <input type="checkbox" name="enumDesc"  value="news_scroll_2"  onclick="checkNewType('news_scroll_2')"><span>三版头条_轮播2</span></input>
       <input type="checkbox" name="enumDesc"  value="news_scroll_3"  onclick="checkNewType('news_scroll_3')"><span>三版头条_轮播3</span></input>
       <input type="checkbox" name="enumDesc"  value="news_scroll_4"  onclick="checkNewType('news_scroll_4')"><span>三版头条_轮播4</span></input></br>
     	<input type="checkbox" name=""  value="obj_scroll_1"  onclick="checkNewType('obj_scroll_1')">
       <input type="checkbox" name="enumDesc"  value="news_scroll_5"  onclick="checkNewType('news_scroll_5')"><span>三版头条_轮播5</span></input>
    </div>
    <div class="row">
		<div class="col-md-9 mt10 text-center">
			<button type="button" class="btn btn-success" onclick="ok();">确定</button>
			<button class="btn btn-success" id="closeIframe">关闭</button>
		</div>
	</div>
</div> -->

<div class="container">
	<div class="row">
		<div class="input-group">
		  	<span class="input-group-addon">对应id</span>
		  	<input type="text" class="form-control" id="objectId" name="objectId">
		</div>
    </div>
    <div class="row">
		<div class="row-div mt10">
			<div class="row-div-div"><input type="checkbox" name="enumDesc"  value="obj_scroll_1"  onclick="checkNewType('obj_scroll_1')"><span>广告_1<!-- 路演大厅_轮播1 --></span></div>
			<div class="row-div-div"><input type="checkbox" name="enumDesc"  value="obj_scroll_2"  onclick="checkNewType('obj_scroll_2')"><span><!-- 路演大厅_轮播2 --></span></div>
			<div class="row-div-div"><input type="checkbox" name="enumDesc"  value="obj_scroll_3"  onclick="checkNewType('obj_scroll_3')" ><span><!-- 路演大厅_轮播3 --></span></div>
		</div>
		<div class="row-div mt10">
			<div class="row-div-div"><input type="checkbox" name="enumDesc"  value="obj_scroll_4"  onclick="checkNewType('obj_scroll_4')"><span><!-- 路演大厅_轮播4 --></span></div>
			<div class="row-div-div"><input type="checkbox" name="enumDesc"  value="obj_scroll_5"  onclick="checkNewType('obj_scroll_5')"><span>路演大厅_轮播5</span></div>
			<div class="row-div-div"><input type="checkbox" name="enumDesc"  value="news_scroll_1"  onclick="checkNewType('news_scroll_1')"><span>三版头条_轮播1</span></div>
		</div>
		<div class="row-div mt10">
			<div class="row-div-div"><input type="checkbox" name="enumDesc"  value="news_scroll_2"  onclick="checkNewType('obj_scroll_2')"><span>三版头条_轮播2</span></div>
			<div class="row-div-div"><input type="checkbox" name="enumDesc"  value="news_scroll_3"  onclick="checkNewType('obj_scroll_3')"><span>三版头条_轮播3</span></div>
			<div class="row-div-div"><input type="checkbox" name="enumDesc"  value="news_scroll_4"  onclick="checkNewType('obj_scroll_4')" ><span>三版头条_轮播4</span></div>
		</div>
		<div class="row-div mt10">
			<div class="row-div-div"><input type="checkbox" name="enumDesc"  value="news_scroll_5"  onclick="checkNewType('obj_scroll_5')"><span>三版头条_轮播5</span></div>
		</div>
	</div>
	<div class="row mt10">
		<div class="col-md-9 mt10 text-center">
			<!-- <button type="button" class="btn btn-success" onclick="ok();">确定</button> -->
			<button class="btn btn-success" id="closeIframe">关闭</button>
		</div>
	</div>
</div>
<!--</form>-->
</body>

</html>