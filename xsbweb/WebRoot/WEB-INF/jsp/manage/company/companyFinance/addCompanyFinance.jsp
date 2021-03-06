<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>新增财务分析</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .mt50{margin-top:50px;}
        .mt20{margin-top:20px;}
    </style>
    <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/dtpicker/js/laydate.js" ></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/layer/layer.js" ></script>
	<script type="text/javascript">
		!function(){
			laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
			laydate({elem: '#demo'});//绑定元素
		}();
	</script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12 mt20">
            <form class="form-inline" action="admin/company/addCompanyFinance.do" method="post">
            <input type="hidden" name="_method" value="post" />
            <table class="table table-bordered table-hover table-responsive">
                <tbody>
                 <tr>
                    <td class="col-md-2">项目</td>
                    <td class="col-md-10">
                    	<input type="text" class="form-control" name="attrName" value="${companyFinance.attrName}" >
                    	<input type="hidden" name="stockId" value="${companyFinance.stockId}" />
                    </td>
                </tr>
                <tr>
                    <td>数据</td>
                    <td><input type="text" class="form-control"  name="attrValue" value="${companyFinance.attrValue}" ></td>
                </tr>
                <tr>
                    <td>时间</td>
                    <td><input type="text" class="laydate-icon form-control"   id="t1"  name="reportDate" style="width:130px;height:30px" 
                    value="${companyFinance.reportDate}" onClick="laydate({elem: '#t1',istime: true, format: 'YYYY-MM-DD'})"></td>
                </tr>
							<tr>
								<td colspan="2" class="text-center">
									<!--  -->
									<button type="button" class="btn btn-success"
										onclick="submit();">提交</button>
										<button type="button" class="btn btn-danger" onclick="javascript:window.location.href='admin/company/getCompanyDetailListrByStockId.do?stockId=${companyFinance.stockId}&type=finance'">返回</button>
								</td>
							</tr>
						</tbody>
            </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>