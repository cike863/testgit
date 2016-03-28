<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    
    <title>新闻列表</title>
    
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
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/dtpicker/js/laydate.js" ></script>
	<style>
		.mt10{margin-top:10px;}
		a{text-decoration:underline;}
		a:after{content:"";margin-right:5px;}
	</style>
  </head>
 
  <body>
  ${error}
	  <div class="container-full">
		  <form class="form-inline" name="newsForm" action="${pageContext.request.contextPath }/admin/news/toAdminNews.do " >
		  <div class="row">
			  <div class="col-md-12 mt10">
			  		<span onclick="batchDeleteNews();"class="btn btn-danger" >删除</span>
					 <div class="input-group">
						  <span class="input-group-addon">新闻标题</span>
						  <input type="text" class="form-control" id="search" name="newsTitle" placeholder="请输入查询内容" value="${news.newsTitle}" >
					  </div>
					  <div class="input-group">
						<span class="input-group-addon">编辑时间</span> 
						<input placeholder="请输入开始日期" class="laydate-icon" name="sartQueryDate" id="t1" style="width:130px;height:30px" 
						value="<fmt:formatDate value="${news.sartQueryDate}" pattern="yyyy-MM-dd" />" 
						onClick="laydate({elem: '#t1',istime: true, format: 'YYYY-MM-DD'})">~
						<input placeholder="请输入结束日期" class="laydate-icon" name="endQueryDate" id="t2" style="width:130px;height:30px" 
						value="<fmt:formatDate value="${news.endQueryDate}" pattern="yyyy-MM-dd" />"
						 onClick="laydate({elem: '#t2',istime: true, format: 'YYYY-MM-DD'})">
					</div>
					  <!--新闻标题<input type="text" class="form-control" id="search" name="search" placeholder="请输入查询内容">-->
					  <input type="hidden" name="projectNo" value="${news.projectNo}" /> 
					  <input type="hidden" name="projCpCode" value="${news.projCpCode}" /> 
					  
					  <input type="hidden" name="pageSize" id="pageSize" value="${news.pageSize}" /> 
					  <button class="btn btn-warning" type="submit">查询</button>
					  <button type="button" class="btn btn-success" onclick="toAddNews();">新建</button>
			  </div>
		  </div>
		  <div class="row">
			  <div class="col-md-12 mt10">
				  <table class="table table-bordered table-hover table-responsive">
					  <thead>
					  <tr>
					  		<th><input type="checkbox" name="allCheckBox"  id="allCheckBox" onclick="checkNewsAll(this);" >全选</th>
						  	<th>新闻编码</th>
						  	<th>新闻来源</th>
						  	<th>新闻标题</th>
						  	<th>实际浏览量</th>
						  	<th>维护浏览量</th>
						  	<th style="width:70px;">处理人</th>
						  	<th>新闻类型</th>
						  	<th>创建时间</th>
						  	<th>opreation</th>
					  </tr>
					  </thead>
					  <tbody>
						<c:forEach items="${newsList}" var="nVO" varStatus="index">
							<tr>
								<td><input type="checkbox" name="newsNoCheckBox" value="${index.index}"></td>
	                			<td id="news_${index.index}">${nVO.newsNo}</td>
								<td>${nVO.newsSource}</td>
								<td>${nVO.newsTitle}</td>
								<td>${nVO.realCounts}</td>
								<td>${nVO.fakeCounts}</td>
								<td>${nVO.lastUpdateBy}</td>
								<td>${nVO.newsType}</td>
								<td>${nVO.newsDate}</td>
								<td>
							<c:if test="${nVO.newsNo ne '' }">
								<a href="${pageContext.request.contextPath }/admin/news/newsToTop.do?newsNo=${nVO.newsNo}&projectNo=${news.projectNo}">置顶</a> | 
							</c:if>
							<%-- <a href="javascript:void(0);" onclick="toEditEtlSchedule('${eVO.taskTable}');">edit</a><br/> --%>
							<a href="${pageContext.request.contextPath }/admin/news/toEditNews.do?newsNo=${nVO.newsNo}&projectNo=${news.projectNo}">编辑</a>
							<%-- | <a href="javascript:void(0);" onclick="deleteNews('${nVO.newsNo}')">删除</a> --%>
							
							<%-- <c:if test="${nVO.isTop == '1'}">
								<a href="${pageContext.request.contextPath }/admin/news/newsCancelTop.do?newsNo=${nVO.newsNo}">取消置顶</a>				
							</c:if> --%>
							</td>
							</tr>
						</c:forEach>
					  </tbody>
				  </table>
			  </div>
		  </div>
	   	</form>
		<page:pager pageSize="${news.pageSize}" pageNo="${news.pageNo}" url="admin/news/toAdminNews.do" recordCount="${news.totalRecord}"/>
	  </div>
  </body>
  <script type="text/javascript">
	  	/* function toEditEtlSchedule(taskTable){
			//提交form
			document.etlForm.action="admin/etlschedule/toEditEtlSchedule.do";
			document.etlForm.submit();
		} */
  		
  		function toAddNews(){
  			//提交form
  			
  			document.newsForm.action="admin/news/toAddNews.do";
  			document.newsForm.submit();
  		}
		function deleteNews(newsNo){
			if(!confirm("确定要删除该数据吗？")){
				return;
			}
			window.location="admin/news/deleteNews.do?newsNo="+newsNo+"&projectNo="+'${news.projectNo}';
		}
		//全选
		function checkNewsAll(k){
	  		if($(k).is(':checked')){
	  			$("input[name='newsNoCheckBox']").each(function(){	
					$(this).prop("checked",true);
				});
	  		}else{
	  			$("input[name='newsNoCheckBox']").each(function(){	
	  				$(this).prop("checked",false);
				});
	  		}	
	  	}
		function batchDeleteNews(){
			if(!confirm("确定要删除该数据吗？")){
				return;
			}
	  		var newsNoArrs = "";
	  		$("input[name='newsNoCheckBox']:checked").each(function(){ 
	  			newsNoArrs += $("#news_"+$(this).val()).html()+",";
	  		});
	  		newsNoArrs=newsNoArrs.toString().substring(0, newsNoArrs.toString().length-1);
	  		//console.log(newsNoArrs);
	  		$.ajax({
	  			url : "admin/news/"+newsNoArrs+"/del",
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
