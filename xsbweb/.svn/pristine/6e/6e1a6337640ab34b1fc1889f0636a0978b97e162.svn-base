<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://ajaxanywhere.sourceforge.net/" prefix="aa"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>栏目列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		.mt10{margin-top:10px;}
		a{text-decoration:underline;}
		a:after{content:"";margin-right:5px;}
	</style>
  </head>
 
  <body>
  ${error}
	  <form class="form-inline" name="menuForm" action="${pageContext.request.contextPath }/admin/menu/toAdminMenu.do" >
	  <div class="container-full">
		  <div class="row">
			  <div class="col-md-12 mt10">
					  <div class="input-group">
						  <span class="input-group-addon">栏目名称</span>
						  <input type="text" class="form-control" id="search" name="menuVO.menu.itemText">
					  </div>
					  <!--新闻标题<input type="text" class="form-control" id="search" name="search" placeholder="请输入查询内容">-->
					  <button type="button" class="btn btn-warning" onclick="queryMenuList();">查询</button>
					  <button type="button" class="btn btn-danger" onclick="toAddMenu();">新建</button>
				  
			  </div>
		  </div>
		  <div class="row">
		  <aa:zone name="dataListZone">
			  <div class="col-md-12 mt10">
				  <table class="table table-bordered table-hover table-responsive">
					  <thead>
					  <tr>
						  <th>栏目ID</th>
						  <th>父栏目ID</th>
						  <th>栏目名称</th>
						  <th>link</th>
						  <th>顺序</th>
						  <th>opreation</th>
					  </tr>
					  </thead>
					  <tbody>
						<c:forEach items="${menuVO.menuList}" var="mVO">
							<tr>
							<td>${mVO.itemId}</td>
							<td>${mVO.itemParent}</td>
							<td>${mVO.itemText}</td>
							<td>${mVO.targetLink}</td>
							<td>${mVO.itemOrder}</td>
							<td>
							<%-- <a href="javascript:void(0);" onclick="toEditEtlSchedule('${eVO.taskTable}');">edit</a><br/> --%>
							<a href="${pageContext.request.contextPath }/admin/menu/toEditMenu.do?itemId=${mVO.itemId}">edit</a>
							<a href="javascript:void(0);" onclick="deleteMenuByItemId('${mVO.itemId}')">delete</a>
							</td>
							</tr>
						</c:forEach>
					  </tbody>
				  </table>
			  </div>
		  </aa:zone>
		  </div>
	  </div>
	  </form>
  </body>
  <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
   <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/aa.js"></script>
  <script type="text/javascript">
	  	ajaxAnywhere.formName="menuForm";
		ajaxAnywhere.getZonesToReload = function(){
			return "dataListZone";
		} 
		function deleteMenuByItemId(itemId){
			if(itemId==null){
				alert("删除失败，itemId参数为空！");
				return;
			}
			var flag = false;
			$.ajax({
				url:"admin/menu/isHaveSubMenu.do?",
	  			contentType : 'application/json',
	  			type:'GET',
	  			async: false,
	  			data:{
	  				'itemId' : itemId
	  			},
	  			success:function(data){
	  				if(data==true){
	  					alert("该栏目下存在子栏目，请先删除子栏目！");
	  					flag = true;
	  				}
	  			}
			});
  			if(!flag){
  				if(!confirm("确定要删除吗？删除后不可恢复")){
  					return;
  				} 
  				document.forms[0].action="admin/menu/deleteMenuByItemId.do?itemId="+itemId;
  				ajaxAnywhere.submitAJAX();
  			}
		}
  		function queryMenuList(){
  			var itemText = $("[name='menuVO.menu.itemText']").val();
  			var a = encodeURIComponent(itemText);
  			alert(a)
  			document.forms[0].action="admin/menu/queryMenuList.do?itemText="+itemText;
			ajaxAnywhere.submitAJAX();
  		}
  		function toAddMenu(){
  			//提交form
  			document.menuForm.action="admin/menu/toAddMenu.do";
  			document.menuForm.submit();
  		}
  		
  </script>
</html>
