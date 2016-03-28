<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

<title>新增item</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/kindeditor/themes/default/default.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/kindeditor/plugins/code/prettify.css" />

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/dtpicker/js/laydate.js"></script>
<script charset="utf-8"
	src="<%=request.getContextPath()%>/kindeditor/kindeditor.js"></script>
<script charset="utf-8"
	src="<%=request.getContextPath()%>/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8"
	src="<%=request.getContextPath()%>/kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/layer/layer.js"></script>

<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/ueditor1_4_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/ueditor1_4_3/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/ueditor1_4_3/lang/zh-cn/zh-cn.js"></script>


<script>
		<%-- KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="itemValue"]', {
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
// 			alert($("span").length);
			/*$("span").each(function(i){
			   if(this.title == "批量图片上传"){
			   		$(this).css("display","none");
			   }
			 });*/
			prettyPrint();
		}); --%>
		
		
		
	</script>
<script type="text/javascript">
	
		//获取窗口索引
		//var index = parent.layer.getFrameIndex(window.name);
		<%-- UEDITOR_CONFIG.UEDITOR_HOME_URL = <%=basePath%>/admin/news/uploadJson.do'； --%>
		//UE.getEditor('itemValue'); 
		var ueditor = UE.getEditor('itemValue',{
			imageUrl: "<%=path%>/servlet/UploadServlet", //图片上传提交后台对应的地址
		    imagePath: "<%=path%>/ueditor/",  //图片在服务器上的存储目录
		    imageFieldName: "upload"  //后台对应接收image的参数名
		});
		/* uobj.ue.ready(function(){
			ue.setContent(value);
			uodj.ue.addListener("contentChage",function(){
				ST();
			});
			}
		) */
		UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
		UE.Editor.prototype.getActionUrl = function(action) {
    	 if (action != 'config') {
         	return '<%=basePath%>/ueditorUpload.do?action='+action;
     	} else {
        	 	return this._bkGetActionUrl.call(this, action);
     	}
		}
		
		function ok(){
			//var itemKey = $("[name='itemKey']").val();
			//var itemLocationOrder = $("[name='itemLocationOrder']").val();
			//var itemValue = $("textarea[name='itemValue']").val();
			//这个破东西搞了我好久---KindEditor会改变html代码---XXXXXXOOOOOO 
			//var itemValue=$(document.getElementsByTagName('iframe')[0].contentWindow.document.body).html();
			//var size = parent.itemSize;
			//parent.isOK(itemKey,itemValue,itemLocationOrder);
			//parent.itemSize = size+1;
			//parent.layer.close(index);
		}
		
		//关闭iframe
		window.onload = function(){
			var url="<%=hostName%>";
			if(${item.itemMediaPath!=''&&item.itemMediaPath!=null}){
				$("#itemMediaPic").attr("src","http://"+url+":8080/dyly${item.itemMediaPath}");	
			}
			if(${error!=null&&error !=""}){
				alert("${error}");
			}
		}
		function toUpload(mediaType,dir){
			mediaType=mediaType.replace(/\s+/g,"");
			dir=dir.replace(/\s+/g,"");
			var url = "admin/news/uploadLayer.do?mediaNo="+mediaType+"&dir="+dir;
			layer.open({
			    type: 2,
			    area: ['800px', '530px'],
			    fix: false, //不固定
			    maxmin: true,
			    content: url
			});
		}
		function uploadIsOk(mediaType,mediaNo,url,returnPath,newFileName,format,size){
			mediaType=mediaType.replace(/\s+/g,"");
			if(mediaType=="itemMediaNo"){
				$("[name=itemMediaNo]").val(mediaNo);
				$("#itemMediaPic").attr("src",url);
			}
		}
		function toChear(k,f){
			if(k!="pptMediaNo"){
				$("#"+k+"").attr("src","");
			}
			$("[name="+f+"]").val('');
		}
	</script>
</head>

<body>
	<%-- <div class="container-full">
		<div class="row">
			<div class="col-md-12 mt10">
				<div class="form-group">
					<label class="col-md-1 control-label">名称</label>
					<div class="col-md-9">
						<input type="text" name="itemKey" value="${item.itemKey}" style="width: 100% !important;"  />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-1 control-label">位置顺序</label>
					<div class="col-md-9">
						<input type="text" name="itemLocationOrder" value="${item.itemLocationOrder}" style="width: 100% !important;"  />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-1 control-label">内容</label>
					<div class="col-md-9">
						<textarea id="itemValue" name="itemValue" style="width: 100% !important;height: 300px !important;" cols="100" rows="8">${item.itemValue}</textarea>
					</div>
				</div>
				<div class="row">
					<div class="col-md-9 mt10 text-center">
						<button type="button" class="btn btn-success" onclick="ok();">确定</button>
						<button class="close" id="closeIframe">关闭</button>
					</div>
				</div>
			</div>
		</div>
	</div> --%>

	<div class="container-full">
		<div class="row">
			<div class="col-md-12 mt10">
				<form:form action="admin/item/add" name="newsForm" method="post"
					class="form-horizontal">
					<div class="form-group">
						<label class="col-md-1 control-label">名称</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="itemKey"
								value="${item.itemKey}" placeholder="名称" />
							<form:errors path="itemKey"></form:errors>
							<input type="hidden" name="objectNo" value="${item.objectNo}" />
							<c:if test="${item.oldItemKey ne null}">
								<input type="hidden" name="oldItemKey"
									value="${item.oldItemKey}" />
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">位置顺序</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="itemLocationOrder"
								value="${item.itemLocationOrder eq null ? 0 : item.itemLocationOrder}"
								placeholder="位置顺序" />
							<form:errors path="itemLocationOrder"></form:errors>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">资源</label>
						<div class="col-md-7">
							<input type="hidden" class="form-control" name="itemMediaNo"
								value="${item.itemMediaNo }" placeholder="位置顺序" />
							<form:errors path="itemMediaNo"></form:errors>
							<img id="itemMediaPic" src="" height="100" width="100"> <input
								type="button" onclick="toUpload('itemMediaNo','image');"
								value="上传" class="btn btn-success" /> <input type="button"
								onclick="toChear('itemMediaPic','itemMediaNo');" value="清空"
								class="btn" /> <label style="color:red">*文件大小不能超过10M</label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">内容</label>
						<div class="col-md-9">
							<textarea name="itemValue" id="itemValue"
								style="width: 1024px; height: 500px;">${item.itemValue}</textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-md-9 mt10 text-center">
							<button type="submit" class="btn btn-success">提交</button>
							<button type="button" class="btn btn-danger"
								onclick="history.go(-1)">返回</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>
