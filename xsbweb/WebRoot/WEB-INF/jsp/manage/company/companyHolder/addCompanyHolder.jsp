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
    <title>新增十大股东</title>
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
            <form class="form-inline" action="admin/company/addCompanyHolder.do" method="post">
            <input type="hidden" name="_method" value="post" />
            <table class="table table-bordered table-hover table-responsive">
                <tbody>
                <tr>
                    <td class="col-md-2">股东名称</td>
                    <td class="col-md-10">
                    	<input type="text" class="form-control" name="holderName" value="${companyHolder.holderName}" class="laydate-icon" >
                    </td>
                </tr>
                <tr>
                    <td class="col-md-2">数据日期</td>
                    <td>
                    	<input type="text" class="laydate-icon form-control"   id="t1"  name="snapshotDay" style="width:130px;height:30px" 
                    	value="${companyFinance.snapshotDay}" onClick="laydate({elem: '#t1',istime: true, format: 'YYYY-MM-DD'})">
                    	<input type="hidden" name="stockId" value="${companyHolder.stockId}" />
                    </td>
                </tr>
                <tr>
                    <td class="col-md-2">持股性质</td>
                    <td class="col-md-10">
                    	<input type="text" class="form-control" name="holdType" value="${companyHolder.holdType}" class="laydate-icon" >
                    </td>
                </tr>
                
                <tr>
                    <td>持股数(万股)</td>
                    <td><input type="text" class="form-control"  name="holdAmount" value="${companyHolder.holdAmount}" ></td>
                </tr>
                <tr>
                    <td>持股比例（%)</td>
                    <td><input type="text" class="form-control"   name="holdPercent" value="${companyHolder.holdPercent}" ></td>
                </tr>
                <tr>
                    <td>持股数变化(万股)</td>
                    <td><input type="text" class="form-control"   name="chngHoldAmount" value="${companyHolder.chngHoldAmount}" ></td>
                </tr>
                
                <tr>
                    <td>持股比例变化（%)</td>
                    <td><input type="text" class="form-control"   name="chngHoldPercent" value="${companyHolder.chngHoldPercent}" ></td>
                </tr>
                <tr>
                    <td>排位</td>
                    <td><input type="text" class="form-control"   name="loc" value="${companyHolder.loc}" ></td>
                </tr>
							<tr>
								<td colspan="2" class="text-center">
									<!--  -->
									<button type="button" class="btn btn-success"
										onclick="submit();">提交</button>
										<button type="button" class="btn btn-danger" onclick="javascript:window.location.href='admin/company/getCompanyDetailListrByStockId.do?stockId=${companyHolder.stockId}&type=holder'">返回</button>
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