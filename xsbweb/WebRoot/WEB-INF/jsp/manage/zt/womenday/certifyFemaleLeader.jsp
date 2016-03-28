<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>新增项目</title>
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
			if(${femaleLeader.votedStatus=='0'}){
				$("#show0").attr("checked","checked");
			}else if(${femaleLeader.votedStatus=='1'}){
				$("#show1").attr("checked","checked");
			}else if(${femaleLeader.votedStatus=='2'}){
				$("#show2").attr("checked","checked");
			}else{
				$("#show3").attr("checked","checked");
			}
			<%-- var url="<%=hostName%>";
			 if(${femaleLeader.leaderPicPath !=''&&femaleLeader.leaderPicPath !=null}){
				$("#leaderPicPath").attr("src","http://"+url+":8080/dyly${femaleLeader.leaderPicPath}");	
			} 
			if(${femaleLeader.companyPicPath !=''&&femaleLeader.companyPicPath !=null}){
				$("#companyPicPath").attr("src","http://"+url+":8080/dyly${femaleLeader.companyPicPath}");	
			}   --%>
		}
	</script>
</head>
<body>
<div class="container">

    <div class="row">
        <div class="col-md-12 mt20">
            <form class="form-inline" action="/womenday/admin/femaleLeaders" method="post">
            <input type="hidden" name="_method" value="post" />
            <table class="table table-bordered table-hover table-responsive">
                <tbody>
                 <tr>
                    <td class="col-md-2">公司名称</td>
                    <td class="col-md-10"><input type="text" class="form-control" style=" width:900px;" name="companyName" value="${femaleLeader.companyName}"  readonly="readonly"></td>
                </tr>
                
                <tr>
                    <td>候选人姓名</td>
                    <td><input type="text" class="form-control"  name="leaderName" value="${femaleLeader.leaderName}" readonly="readonly"></td>
                </tr>
                <tr>
                    <td>得票数</td>
                    <td><input type="text" class="form-control"  style=" width:900px;"  name="votedCnt" value="${femaleLeader.votedCnt}" readonly="readonly"></td>
                </tr>
                <tr>
                    <td>候选人状态</td>
                     <td>
                     	<input type="radio" id="show0"  name="votedStatus" value="0" disabled>&nbsp;&nbsp;候选人审核 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     	<input type="radio" id="show1"  name="votedStatus" value="1" disabled/>&nbsp;&nbsp;开放投票 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     	<input type="radio" id="show2" name="votedStatus" value="2" disabled/>&nbsp;&nbsp;报名截止 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     	<input type="radio" id="show3" name="votedStatus" value="3" disabled/>&nbsp;&nbsp;取消报名资格
                     </td>
                </tr>
                <tr>
                    <td>候选口号</td>
                    <td><textarea rows="6" cols="100%" name="candidateSlogan" readonly="readonly">${femaleLeader.candidateSlogan}</textarea></td>
                </tr>
                <tr>
                    <td>公司简介</td>
                    <td><textarea rows="6" cols="100%"name="companyInfo" readonly="readonly">${femaleLeader.companyInfo}</textarea></td>
                </tr>
                 <tr>
                    <td>任务头像存储路径</td>
                     	<td>
                     		<img id="leaderPicPath" src="${femaleLeader.leaderPicPath}" height="100" width="100" >
                     	</td>
                </tr>
                <tr>
                    <td>公司图标存储路径</td>
                     	<td>
                     		<img id="companyPicPath" src="${femaleLeader.companyPicPath}" height="100" width="100" >
                     	</td>
                </tr>
                <tr>
                    <td>排序方式</td>
                    <td><input type="text" class="form-control"  style=" width:900px;"  name="orderType" value="${femaleLeader.orderType}" readonly="readonly"></td>
                </tr>
							<tr>
								<td colspan="4" class="text-center">
									<!--  -->
									<button type="button" class="btn btn-success" onclick="javascript:window.location.href='zt/womenday/admin/femaleLeaders/certify?companyName=${femaleLeader.companyName}&leaderName=${femaleLeader.leaderName}'"  >认证通过</button>
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