<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.xsb.com/xsbweb/tag/pager" prefix="page"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ResourceBundle bundle = ResourceBundle.getBundle("config");  
String	hostName = bundle.getString("ftp_url_pro");  //IP地址
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>sqlSelect</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>index</title>
    <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .mt10{margin-top:10px;}
    </style>
</head>
<body>
${error }
<div class="container-full">
    <div class="row">
        <div class="col-md-12 mt10">
                <div class="input-group">
                	<span class="input-group-addon" >数据源</span>
                	<select name="dbType" style="height:30px">
                		<option value="oltp">oltp</option>
                		<option value="olap">olap</option>
                		<option value="meta">meta</option>
                	</select>
                	<button class="btn btn-warning" onclick="perform();">查询</button>
                </div>
                </br>
                <div class="input-group">
                	<span class="input-group-addon">sql语句</span>
                	<textarea rows="4" cols="100" name="sql"></textarea>
                </div>
                <input type="hidden" name="pageNo" value="${sqlVo.pageNo}" /> 
                <input type="hidden" name="pageSize" value="${sqlVo.pageSize}" /> 
        </div>
    </div>
    <div class="row" id="dataArea">
        <div class="col-md-12 mt10" >
        	<%-- <aa:zone name="confListZone"> --%>
            <table class="table table-bordered table-hover table-responsive" >
                <thead>
                </thead>
                <tbody>
                </tbody>
            </table>
            <%-- </aa:zone>
            <page:pager pageSize="${sqlVo.pageSize eq '0' ?1:sqlVo.pageSize}" pageNo="${sqlVo.pageNo}" url="/admin/selectBySql"  recordCount="${sqlVo.totalRecord}"/> --%>
        </div>
    </div>
</div>
<!--</form>-->
</body>
 <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
 <script type="text/javascript" language="javascript">
 function perform(){debugger;
 	//var data = $("#confForm").serialize();
 	var sql =$("textarea[name='sql']").val();
 	var dbType =$("select[name='dbType']").val();
 	var pageNo=$("input[name='pageNo']").val()==""?1:$("input[name='pageNo']").val();
 	var pageSize=$("input[name='pageSize']").val()==""?10:$("input[name='pageSize']").val();
	$.ajax({
		 url :"admin/sql/selectBySql",
		 type :"POST",
		 //async: false,
		 //data:data,
		 data:{
			 "sql":sql,
			 "dbType" : dbType,
			 "pageNo" :pageNo,
			 "pageSize" :pageSize
			 
		 },
		 dataType:"json",
		 //contentType : 'application/json',
		 success : function(data){
			 console.log(data);
			 if(data){
				$("#dataArea thead").empty();
				$("#dataArea tbody").empty();
				var thd ="";
				for (var i=0;i<data.columnName.length;i++){
					 thd +="<td>"+data.columnName[i]+"</td>";
				}
				$("#dataArea thead").append("<tr>"+thd+"</tr>");
				var tbd ="";
				$.each( data.resultList, function(m, n){
					tbd +="<tr>";
						for (var i=0;i<data.columnName.length;i++){   
							$.each(n,function(mt,nt){
							 if(mt==data.columnName[i]){
								 tbd +="<td>"+nt+"</td>";
							 }
						});
						}
					tbd +="</tr>";
					}); 
				$("#dataArea tbody").append(tbd);
				console.log(data.sqlVo.pageSize);
				console.log(data.sqlVo.pageNo);
				console.log(data.sqlVo.totalRecord);
			 }
		 },
		 error:function(){
			 alert("操作失败");
		 }
	});
 }
	
 </script>
</html>