<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://ajaxanywhere.sourceforge.net/" prefix="aa"%>
<%@ taglib uri="http://java.xsb.com/xsbweb/tag/pager" prefix="page"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>词条列表</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>wordList</title>
    <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .mt10{margin-top:10px;}
    </style>
</head>
 	<script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/dtpicker/js/laydate.js" ></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/layer/layer.js" ></script>
 <script type="text/javascript" language="javascript">
	function toAddWord(projectNo){
		var url = "admin/project/editwordList?projectNo="+projectNo;
		itemValue="";
		layer.open({
		    type: 2,
		    area: ['800px', '530px'],
		    fix: false, //不固定
		    maxmin: true,
		    content: url
		});
	}
	function updateWord(projectNo,word,labOrder){
		var url = "admin/project/editwordList?projectNo="+projectNo+"&word="+word+"&labOrder="+labOrder;
		layer.open({
		    type: 2,
		    area: ['800px', '530px'],
		    fix: false, //不固定
		    maxmin: true,
		    content: url
		}); 
	}
	function deleteWord(newsNo,word){
		if(!confirm("确定要删除该数据吗？")){
			return;
		}
		newsNo=newsNo.replace(/\s+/g,"");
		word=word.replace(/\s+/g,"");
		$.ajax({
			url :"admin/news/deleteWord?newsNo="+newsNo+"&word="+word,
			contentType : 'application/json',
			async: false,
			dataType:"json",
			success:function(data){
				if(data.resultCode==1){
					window.location.reload();
				}else if (data.resultCode==0){
					alert("操作失败");
				}
			},
			error:function(){
				alert("操作失败");
			}
		});
	}
	function editIsOK(word,oldWord,object,labOrder){
		$.ajax({
			type : 'post',
			url : "admin/project/editword",
			contentType : 'application/json',
			data : JSON.stringify({
				'word' : word,
				'oldWord' : oldWord,
				'object' : object,
				'labOrder' : labOrder
			}),
			cache : false,
			dataType : 'json',
			async : false,
			success : function(data) {
				if (data.resultCode == '1') {
					//console.log("123456");
					window.location.reload();
				} else {
					alert("修改失败");
				}
				;
			},
			error : function() {
				alert("修改失败");
			}
		});
	}
	function addIsOK(word,object,labOrder){
		//console.log(word+","+object+","+labOrder);
		$.ajax({
			type : 'post',
			url : "admin/project/addword",
			contentType : 'application/json',
			data : JSON.stringify({
				'word' : word,
				'object' : object,
				'labOrder' : labOrder
			}),
			cache : false,
			dataType : 'json',
			async : false,
			 success : function(data) {
				if (data.resultCode == '1') {
					//console.log("123456");
					window.location.reload();
				} else {
					alert("添加失败");
				}
				;
			}, 
			error : function() {
				alert("添加失败");
			}
		});
	}
	</script>
<body>
<!--<form action="meet/toMeetRoomList.do" name="confForm" method="post">-->
${error }
<div class="container-full">
    <div class="row">
        <div class="col-md-12 mt10">
            <form class="form-inline" action="admin/item" method="post"  name="confForm">
                <a href="javascript:void(0);" onclick="toAddWord('${projectNo}');" class="btn btn-success">新建</a>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 mt10">
        	<aa:zone name="confListZone">
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                    	<th>词条名称</th>
                    	<!-- <th>词条顺序</th> -->
                    	<th>创建时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${words!=null}">
	                	<c:forEach items="${words}" var="word" varStatus="index">
	                		<tr>
	                			<td >${word.word}</td>
	                			<%-- <td >${word.labOrder}</td> --%>
	                			<td ><fmt:formatDate value="${word.createDate}" pattern="yyyy-MM-dd"/></td>		                        
		                        <td>	
		                        	<c:if test="${item.itemKey ne '' }">
		                        		<a href="javascript:void(0);" onclick="updateWord('${projectNo}','${word.word }','${word.labOrder }')">修改</a> |
		                        		<a href="javascript:void(0);" onclick="deleteWord('${projectNo}','${word.word}')">删除</a>
		                        	</c:if>
		                        </td>	
		                    </tr>
	                	</c:forEach>
                	</c:if>
                	 </tr>
		          <tr>
                    <td colspan="4" class="text-center">
                        <button type="button" class="btn btn-danger" onclick="history.go(-1)">返回</button>
                    </td>
                </tr>
                </tbody>
            </table>
            </aa:zone>
            <%-- <page:pager pageSize="${base.pageSize}" pageNo="${base.pageNo}" url="admin/project"  recordCount="${base.totalRecord}"/> --%>
        </div>
    </div>
</div>
<!--</form>-->
</body>

</html>