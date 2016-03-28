<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.xsb.com/xsbweb/tag/pager" prefix="page"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>三板百科列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
	<style>
		.mt10{margin-top:10px;}
		a{text-decoration:underline;}
		a:after{content:"";margin-right:5px;}
	</style>
  </head>
 
  <body>
  ${error}
	  <div class="container-full">
		  <form class="form-inline" name="bkForm" action="${pageContext.request.contextPath }/admin/encyclopedias.do " >
		  <div class="row">
			  <div class="col-md-12 mt10">
			  		  <span onclick="batchDeleteBaike();"class="btn btn-danger" >删除</span>
					  <div class="input-group">
						  <span class="input-group-addon">百科标题</span>
						  <input type="text" class="form-control" id="search" name="baikeTitle" placeholder="请输入查询内容" value="${bk.baikeTitle}" >
					  </div>
					  <div class="input-group">
						  <span class="input-group-addon">百科标题</span>&nbsp;&nbsp;&nbsp;
							<input type="radio"  name="baikeType" value="tz" id="tz">投资&nbsp;&nbsp;&nbsp;
							<input type="radio"  name="baikeType" value="gp"  id="gp">挂牌&nbsp;&nbsp;&nbsp;
							<input type="radio"  name="baikeType" value="rm"  id="rm">入门
					  </div>
					  <input type="hidden" name="pageSize" id="pageSize" value="${bk.pageSize}" /> 
					  <button class="btn btn-warning" type="submit">查询</button>
					  <button type="button" class="btn btn-success" onclick="toAddBk();">新建</button>
			  </div>
		  </div>
		  <div class="row">
			  <div class="col-md-12 mt10">
				  <table class="table table-bordered table-hover table-responsive">
					  <thead>
					  <tr>
					  		<th><input type="checkbox" name="allCheckBox"  id="allCheckBox" onclick="checkBaikeAll(this);" >全选</th>
						  	<th>编码</th>
						  	<th>百科标题</th>
						  	<th>排序</th>
						  	<th>类型</th>
						  	<th>创建时间</th>
						  	<th>opreation</th>
					  </tr>
					  </thead>
					  <tbody>
						<c:forEach items="${bkList}" var="nVO" varStatus="index">
							<tr>
								<td><input type="checkbox" name="baikeIdCheckBox" value="${index.index}"></td>
	                			<td id="baikeId_${index.index}">${nVO.baikeId}</td>
								<td>${nVO.baikeTitle}</td>
								<td>${nVO.baikeOrder}</td>
							<td>
								<c:if test="${nVO.baikeType eq 'tz'}">
		                        		投资
		                        </c:if>
		                        <c:if test="${nVO.baikeType eq 'gp'}">
		                        		挂牌
		                        </c:if>
		                        <c:if test="${nVO.baikeType eq 'rm'}">
		                        		入门
		                        </c:if>
							</td>
							<td>${nVO.createdDate}</td>
							<td>
							<%-- <a href="javascript:void(0);" onclick="toEditEtlSchedule('${eVO.taskTable}');">edit</a><br/> --%>
							<a href="${pageContext.request.contextPath }/admin/encyclopedias/toEditBaike.do?id=${nVO.baikeId}">编辑</a>
							<%-- | <a href="javascript:void(0);" onclick="deleteBaike('${nVO.baikeId}')">删除</a> --%>
							<%-- | <a href="${pageContext.request.contextPath }/admin/encyclopedias/deleteBaike.do?id=${nVO.baikeId}">删除</a> --%>				
							</td>
							</tr>
						</c:forEach>
					  </tbody>
				  </table>
			  </div>
		  </div>
	   	</form>
		<page:pager pageSize="${bk.pageSize}" pageNo="${bk.pageNo}" url="admin/encyclopedias.do" recordCount="${bk.totalRecord}"/>
	  </div>
  </body>
  <script type="text/javascript">
	  	/* function toEditEtlSchedule(taskTable){
			//提交form
			document.etlForm.action="admin/etlschedule/toEditEtlSchedule.do";
			document.etlForm.submit();
		} */
  		
  		function toAddBk(){
  			//提交form
  			
  			document.bkForm.action="admin/encyclopedias/toAddBaike.do";
  			document.bkForm.submit();
  		}
		$(document).ready(function(){
			if(${bk.baikeType=='tz'}){
				$("#tz").attr("checked","checked");
			}
			if(${bk.baikeType=='rm'}){
				$("#rm").attr("checked","checked");
			}
			if(${bk.baikeType=='gp'}){
				$("#gp").attr("checked","checked");
			}
		})
		function deleteBaike(baikeId){
			if(!confirm("确定要删除该数据吗？")){
				return;
			}
			window.location="admin/encyclopedias/deleteBaike.do?id="+baikeId;
		}
		//全选
		function checkBaikeAll(k){
	  		if($(k).is(':checked')){
	  			$("input[name='baikeIdCheckBox']").each(function(){	
					$(this).prop("checked",true);
				});
	  		}else{
	  			$("input[name='baikeIdCheckBox']").each(function(){	
	  				$(this).prop("checked",false);
				});
	  		}	
	  	}
		function batchDeleteBaike(){
			if(!confirm("确定要删除该数据吗？")){
				return;
			}
	  		var baikeIdArrs = "";
	  		$("input[name='baikeIdCheckBox']:checked").each(function(){ 
	  			baikeIdArrs += $("#baikeId_"+$(this).val()).html()+",";
	  		});
	  		baikeIdArrs=baikeIdArrs.toString().substring(0, baikeIdArrs.toString().length-1);
	  		//console.log(projectNoArrs);
	  		$.ajax({
	  			url : "admin/encyclopedias/"+baikeIdArrs+"/del",
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
