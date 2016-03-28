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
    <title>公司列表</title>
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
<!--<form action="meet/toMeetRoomList.do" name="confForm" method="post">-->
${error }
<div class="container-full">
    <div class="row">
        <div class="col-md-12 mt10">
            <form class="form-inline" action="admin/company" method="post"  name="confForm"><!--action="admin/project/serach?serachInformation="+information, name="confForm" method="post"-->
                <span onclick="batchDeleteCompany();"class="btn btn-danger" >删除</span>
                <div class="input-group">
                	<span class="input-group-addon">公司名</span>
                	<input type="text" class="form-control" id="stockName" name="stockName" placeholder="请输入查询内容" value="${company.stockName}" >
                </div>
                <div class="input-group">
                	<span class="input-group-addon">交易方式</span>
                	<input type="text" class="form-control" id="assiMay" name="assiMay" placeholder="请输入查询内容" value="${company.assiMay}" >
                </div>
                <div class="input-group">
                	<span class="input-group-addon">所属行业</span>
                	<input type="text" class="form-control" id="industryType" name="industryType" placeholder="请输入查询内容" value="${company.industryType}" >
                </div>
                <input type="hidden" name="_method" value="get" /> 
                <input type="hidden" name="pageSize" id="pageSize" value="${company.pageSize}" /> 
                <button class="btn btn-warning" type="submit">查询</button><!-- type="submit"  onclick="serach();-->
                <a href="admin/company/toAddCompany.do" role="button" class="btn btn-success">新建</a>
                
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 mt10">
        	<aa:zone name="confListZone">
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                    	<th><input type="checkbox" name="allCheckBox"  id="allCheckBox" onclick="checkCompanyAll(this);" >全选</th>
                    	<th >股票代码</th>
                    	<th>公司名</th>
                    	<th>法人代表</th>
                    	<th>地区</th>
                        <th>交易方式</th>
                        <th>所属行业</th>
                        <th>创建时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${companyList!=null}">
	                	<c:forEach items="${companyList}" var="company" varStatus="index">
	                		<tr>
								<td><input type="checkbox" name="stockIdCheckBox" value="${index.index}"></td>
	                			<td id="company_${index.index}">${company.stockId}</td>
	                			<td>${company.stockName}</td>
		                        <td>${company.legalPerson}</td>
		                        <td>${company.area}</td>
	                			<td>${company.assiMay}</td>
		                        <td>${company.industryType}</td>
		                        <td><fmt:formatDate value="${company.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		                        <td>	
		                        	<c:if test="${company.stockId ne '' }">
		                        		<a href="admin/company/${company.stockId}">编辑</a> | 
		                        		<a href="admin/company/getCompanyDetailListrByStockId.do?stockId=${company.stockId}">查看详情</a>
		                        		
		                        	</c:if>
		                        </td>
		                    </tr>
	                	</c:forEach>
                	</c:if>
                </tbody>
            </table>
            </aa:zone>
            <%-- <page:pager pageSize="${trsProject.pageSize}" pageNo="${trsProject.pageNo}" url="${serachInformation == ''?\"admin/project\":\"admin/project/serach\"}"
            recordCount="${trsProject.totalRecord}"/> --%>
            <page:pager pageSize="${company.pageSize eq '0' ?1:company.pageSize}" pageNo="${company.pageNo}" url="admin/company"  recordCount="${company.totalRecord}"/>
        </div>
    </div>
</div>
<!--</form>-->
</body>
 <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
 <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/aa.js"></script>
 <script type="text/javascript" language="javascript">
 	ajaxAnywhere.formName="confForm";
	ajaxAnywhere.getZonesToReload = function(){
		return "confListZone";
	}
	//全选
	function checkCompanyAll(k){
  		if($(k).is(':checked')){
  			$("input[name='stockIdCheckBox']").each(function(){	
				$(this).prop("checked",true);
			});
  		}else{
  			$("input[name='stockIdCheckBox']").each(function(){	
  				$(this).prop("checked",false);
			});
  		}	
  	}
	function batchDeleteCompany(){
		if(!confirm("确定要删除该数据吗？")){
			return;
		}
  		var stockIds = "";
  		$("input[name='stockIdCheckBox']:checked").each(function(){ 
  			stockIds += $("#company_"+$(this).val()).html()+",";
  		});
  		//console.log(stockIds);
  		stockIds=stockIds.toString().substring(0, stockIds.toString().length-1);
  		$.ajax({
  			url : "admin/company/"+stockIds+"/del",
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