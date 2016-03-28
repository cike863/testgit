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
    <title>新增公司高管</title>
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
		window.onload = function(){
			//console.log('${companyDrctr.dsmType}');
			if(${companyDrctr.dsmType eq 'm'}){
				$("#m").attr("checked","checked");
			}
			if(${companyDrctr.dsmType eq 'd'}){
				$("#d").attr("checked","checked");
			}
			if(${companyDrctr.dsmType eq 's'}){
				$("#s").attr("checked","checked");
			}
		}
	</script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12 mt20">
            <form class="form-inline" action="admin/company/editCompanyDrctrByDsmName.do" method="post">
            <input type="hidden" name="_method" value="post" />
            <table class="table table-bordered table-hover table-responsive">
                <tbody>
                 <tr>
                    <td class="col-md-2">姓名</td>
                    <td class="col-md-10">
                    	<input type="text" class="form-control" name="dsmName" value="${companyDrctr.dsmName}"  readonly="readonly">
                    	<input type="hidden" name="stockId" value="${companyDrctr.stockId}" />
                    </td>
                </tr>
                
                <tr>
                    <td>职位</td>
                    <td><input type="text" class="form-control"  name="dsmTitle" value="${companyDrctr.dsmTitle}" ></td>
                </tr>
                <tr>
                    <td>持股数</td>
                    <td><input type="text" class="form-control"   name="holdAmount" value="${companyDrctr.holdAmount}" ></td>
                </tr>
                <tr>
                    <td>类型</td>
                     <td>董事：<input type="radio" value="d"  name="dsmType" id="d" />&nbsp;&nbsp;&nbsp;&nbsp;
							监事：<input type="radio" value="s"   name="dsmType"  id="s" />&nbsp;&nbsp;&nbsp;&nbsp;
							高管：<input type="radio" value="m"  name="dsmType"  id="m" /></td>
                </tr>
							<tr>
								<td colspan="2" class="text-center">
									<button type="button" class="btn btn-success"
										onclick="submit();">提交</button>
										<button type="button" class="btn btn-danger" onclick="history.go(-1)">返回</button>
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