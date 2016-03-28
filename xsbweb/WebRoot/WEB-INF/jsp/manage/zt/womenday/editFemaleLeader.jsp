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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/kindeditor/plugins/code/prettify.css" />
	<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
	<script  type="text/javascript" src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js" ></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/dtpicker/js/laydate.js" ></script>
	<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/plugins/code/prettify.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/layer/layer.js" ></script>
	<script type="text/javascript">
	KindEditor.ready(function(K) {
		var editor1 = K.create('textarea[name="candidateSlogan"]', {
			cssPath : 'kindeditor/plugins/code/prettify.css',
			uploadJson : '<%=basePath%>/admin/news/uploadJson.do',
			fileManagerJson : '<%=basePath%>/admin/news/fileManagerJson.do',
			allowFileManager : true,
			urlType:'absolute',
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['newsForm'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['newsForm'].submit();
				});
			}
		});
		var editor2 = K.create('textarea[name="companyInfo"]', {
			cssPath : 'kindeditor/plugins/code/prettify.css',
			uploadJson : '<%=basePath%>/admin/news/uploadJson.do',
			fileManagerJson : '<%=basePath%>/admin/news/fileManagerJson.do',
			allowFileManager : true,
			urlType:'absolute',
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['newsForm'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['newsForm'].submit();
				});
			}
		});
//			alert($("span").length);
		/*$("span").each(function(i){
		   if(this.title == "批量图片上传"){
		   		$(this).css("display","none");
		   }
		 });*/
		prettyPrint();
	});
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
		};
		function toUpload(mediaType){
			var url = "admin/news/uploadLayer.do?mediaNo="+mediaType+"&dir=image";
			layer.open({
			    type: 2,
			    area: ['800px', '530px'],
			    fix: false, //不固定
			    maxmin: true,
			    offset: '100px',
			    content: url
			});
		}
		function uploadIsOk(mediaType,mediaNo,url,returnPath,newFileName,format,size){
			if(mediaType=="leaderProject_leaderPic"){
				$("[name=leaderPicPath]").val(returnPath);
				$("#leaderPicPath").attr("src",returnPath);
			}
			if(mediaType=="leaderProject_companyPic"){
				$("[name=companyPicPath]").val(returnPath);
				$("#companyPicPath").attr("src",returnPath);
			}
		}
		function toChear(k,f){
			$("#"+k+"").attr("src","");
			$("[name="+f+"]").val('');
		}
	</script>
</head>
<body>
<div class="container">

    <div class="row">
        <div class="col-md-12 mt20">
            <form class="form-inline" action="zt/womenday/admin/femaleLeaders/update" method="post">
            <input type="hidden" name="_method" value="post" />
            <table class="table table-bordered table-hover table-responsive">
                <tbody>
                 <tr>
                    <td class="col-md-2">公司名称</td>
                    <td class="col-md-10">
                    	<input type="hidden" class="form-control" style=" width:90%" name="companyName" value="${femaleLeader.companyName}" >
                   		<input type="text" class="form-control" style=" width:90%" name="newCompanyName" value="${femaleLeader.companyName}"  >
                   	</td>
                </tr>
                 <tr>
                    <td class="col-md-2">股票代码</td>
                    <td class="col-md-10"><input type="text" class="form-control" style=" width:900px;" name="stockId" value="${femaleLeader.stockId}" ></td>
                </tr>
                <tr>
                    <td>候选人姓名</td>
                    <td>
                    	<input type="hidden" class="form-control"  name="leaderName" value="${femaleLeader.leaderName}"  >
                    	<input type="text" class="form-control"  name="newLeaderName" value="${femaleLeader.leaderName}"  >
                    	</td>
                </tr>
                <tr>
                    <td>得票数</td>
                    <td><input type="text" class="form-control"  style=" width:90%"  name="votedCnt" value="${femaleLeader.votedCnt}" ></td>
                </tr>
                <tr>
                    <td>候选人手机号</td>
                    <td><input type="text" class="form-control"  style=" width:90%"  name="leaderMobile" value="${femaleLeader.leaderMobile}" ></td>
                </tr>
                <tr>
                    <td>候选人状态</td>
                     <td>
                     	<input type="radio" id="show0"  name="votedStatus" value="0" >&nbsp;&nbsp;候选人审核 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     	<input type="radio" id="show1"  name="votedStatus" value="1" />&nbsp;&nbsp;开放投票 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     	<input type="radio" id="show2" name="votedStatus" value="2" />&nbsp;&nbsp;报名截止 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     	<input type="radio" id="show3" name="votedStatus" value="3" />&nbsp;&nbsp;取消报名资格
                     </td>
                </tr>
                <tr>
                    <td>候选口号</td>
                    <td>
                    <textarea name="candidateSlogan" style="width: 80% !important;height: 150px !important;" cols="100" rows="4">${femaleLeader.candidateSlogan}
                    </textarea></td>
                    
                   <%--  <textarea rows="6" cols="100%" name="candidateSlogan" >${femaleLeader.candidateSlogan}</textarea></td> --%>
                </tr>
                <tr>
                    <td>公司简介</td>
                    <td>
                    	<textarea name="companyInfo" style="width: 80% !important;height: 150px !important;" cols="100" rows="4">${femaleLeader.companyInfo}</textarea>
                    </td>
                    <%-- <textarea rows="6" cols="100%"name="companyInfo" >${femaleLeader.companyInfo}</textarea></td> --%>
                </tr>
                 <tr>
                    <td>任务头像存储路径</td>
                     	<td>
                     		<input type="hidden" class="form-control" name="leaderPicPath" value="${femaleLeader.leaderPicPath}" >
                     		<img id="leaderPicPath" src="${femaleLeader.leaderPicPath}" height="100" width="100" >
                     		<input type="button" onclick="toUpload('leaderProject_leaderPic');" value="上传" class="btn btn-success"/>
                     		<input type="button" onclick="toChear('leaderPicPath','leaderPicPath');" value="清空" class="btn"/>
                     		<label style="color:red">*文件大小不能超过10M</label>
                     	</td>
                </tr>
                <tr>
                    <td>公司图标存储路径</td>
                     	<td>
                     		<input type="hidden" class="form-control" name="companyPicPath" value="${femaleLeader.companyPicPath}" >
                     		<img id="companyPicPath" src="${femaleLeader.companyPicPath}" height="100" width="100" >
                     		<input type="button" onclick="toUpload('leaderProject_companyPic');" value="上传" class="btn btn-success"/>
                     		<input type="button" onclick="toChear('companyPicPath','companyPicPath');" value="清空" class="btn"/>
                     		<label style="color:red">*文件大小不能超过10M</label>
                     	</td>
                </tr>
                <tr>
                    <td>排序</td>
                    <td><input type="text" class="form-control"  style=" width:90%"  name="orderNoEx" value="${femaleLeader.orderNoEx}" ></td>
                </tr>
							<tr>
								<td colspan="4" class="text-center">
									<button type="submit" class="btn btn-success">提交</button>
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