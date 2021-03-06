<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    
    <title>编辑新闻</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/kindeditor/plugins/code/prettify.css" />
	<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<%=path%>/assets/css/headline_base.css" >
    <link rel="stylesheet" type="text/css" href="<%=path%>/assets/css/headline_detail.css" >
	<script type="text/javascript" src="<%=path%>/assets/js/jquery-1.8.3.min.js"></script>
	<style type="text/css">
	.tip2 {
    	text-align: left;
    }
	</style>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js" ></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/dtpicker/js/laydate.js" ></script>
	<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/plugins/code/prettify.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/layer/layer.js" ></script>
 	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="newsContent"]', {
				cssPath : 'kindeditor/plugins/code/prettify.css',
				uploadJson : '<%=basePath%>/admin/news/uploadJson.do',
				fileManagerJson : '<%=basePath%>/admin/news/fileManagerJson.do',
				allowFileManager : true,
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
			/*$("span").each(function(i){
			   if(this.title == "批量图片上传"){
			   		$(this).css("display","none");
			   }
			 });*/
			//prettyPrint();
		});
		var index =0;
		function addWord(){
			
			var html="<input type=\"text\"  class=\"form-control\"  style=\"margin-bottom:5px;\" name=\"searchWord["+index+"].word\" value=\"\" >";
			$("#add").append(html);
			index++;
		}
	</script>
	<script type="text/javascript">
		

		window.onload = function(){
			/* var newsRole = $("[name='newsRole']").val();
			if(newsRole!=null && newsRole!=""){
				var objs =newsRole.replace(/(.)(?=[^$])/g,"$1,").split(","); 
				if(objs!=null){
					for (var i = 0; i < objs.length; i++) {
						if(objs[i]==1){
							$("#newsType_"+i).attr("checked","checked");
						}
					}
				}
			} */
			$("input[name='newsTypeEnum']").each(function(){
				if(${checkedCode}.indexOf(parseInt($(this).val()))>-1){
					$(this).attr("checked","checked");
				}				
			});
			if(${news.isShow=='0'}){
				$("#nshow").attr("checked","checked");
			}
			if(${news.isShow=='1'}){
				$("#yshow").attr("checked","checked");
			}
			var url="<%=hostName%>";
			if(${news.newsPicPath!=''&&news.newsPicPath!=null}){
				$("#newsPicPath").attr("src","http://"+url+":8080/dyly${news.newsPicPath}");	
			} 
			/* if(${news.sharePicPath!=''&&news.sharePicPath!=null}){
				$("#sharePicPath").attr("src","http://"+url+":8080/dyly${news.sharePicPath}");	
			} 
			if(${news.bigPicParh!=''&&news.bigPicParh!=null}){
				$("#bigPicParh").attr("src","http://"+url+":8080/dyly${news.bigPicParh}");	
			} */
		};
	
		function checkNewsType(){
			var newsRole = 0;			
			/* $("input[name='newsTypeEnum']:checked").each(function(){
					newsRole += parseInt($(this).val());	
			});
			$("[name='newsRole']").val(newsRole); */
			
			$("input[name='newsTypeEnum']:checked").each(function(){
				newsRole += $(this).val()+",";
			});
			newsRole=newsRole.toString().substring(1, newsRole.toString().length-1);
			$("[name='newsRole']").val(newsRole);
		}
		
		!function(){
			laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
			laydate({elem: '#demo'});//绑定元素
		}();
		function deleteWord(newsNo,word){
			if(!confirm("确定要删除该数据吗？")){
				return;
			}
			newsNo=newsNo.replace(/\s+/g,"");
			word=word.replace(/\s+/g,"");
			$.ajax({
				url :"admin/news/deleteWord?newsNo="+newsNo+"&word="+word,
				contentType : 'application/json',
				async: false,
				dataType:"json",
				success:function(data){
					if(data.resultCode==1){
						window.location.reload();
					}else if (data.resultCode==0){
						alert("操作失败");
					}
				},
				error:function(){
					alert("操作失败");
				}
			});
		}
		function toUpload(mediaType){
			var url = "admin/news/uploadLayer.do?mediaNo="+mediaType+"&dir=image";
			layer.open({
			    type: 2,
			    area: ['800px', '530px'],
			    fix: false, //不固定
			    maxmin: true,
			    content: url
			});
		}
		function toChear(k,f){
			$("#"+k+"").attr("src","");
			$("[name="+f+"]").val('');
		}
		function uploadIsOk(mediaType,mediaNo,url,returnPath,newFileName,format,size){
			if(mediaType=="newsMediaNo"){
				$("[name=newsMediaNo]").val(mediaNo);
				$("#newsPicPath").attr("src",url);
				$("[name=shareMediaNo]").val(mediaNo);
				$("[name=bigMediaNo]").val(mediaNo);
			}
			/* if(mediaType=="newsShareMediaNo"){
				$("[name=shareMediaNo]").val(mediaNo);
				$("#sharePicPath").attr("src",url);
			}
			if(mediaType=="newsBigMediaNo"){
				$("[name=bigMediaNo]").val(mediaNo);
				$("#bigPicParh").attr("src",url);
			} */
		}
		function newsPreview(){
			$("#newsEdit").hide();
			$("#preview_title").html($("input[name='newsTitle']").val());
			$("#preview_newsSource").html($("input[name='newsSource']").val()+"<span class=\"ml20\">"+$("input[name='newsDate']").val()+"</span>");
			$("#preview_realCounts").html("<img style=\"height:10px;margin-right:4px;margin-bottom:3px;\" src=\"<%=request.getContextPath()%>/assets/images/headEye.png\">"+$("input[name='fakeCounts']").val()+"浏览");
			$("#preview_newsPicPath").attr("src",$("img[id='newsPicPath']")[0].src);
			$("#preview_newsContent").html($("textarea[name='newsContent']").val());
			$("#newsPreview").show();	
		}
		function ok(){
			$("#newsEdit").show();
			$("#newsPreview").hide();	
		}
	</script>
  </head>
  
  <body>
	<!-- 显示错误信息 -->
	<c:if test="${allErrors!=null }">
		<c:forEach items="${allErrors }" var="error">
			${ error.defaultMessage}<br/>
		</c:forEach>
	</c:if>


	<div class="container-full" id="newsEdit" >
		<div class="row">
			<div class="col-md-12 mt10">
				<form:form action="admin/news/editNews.do"  name="newsForm" method="post" class="form-horizontal">
					<input type="hidden" name="newsNo" value="${news.newsNo }"/>
					<div class="form-group">
						<label class="col-md-1 control-label">新闻原标题</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="newsTitle" style=" width:900px;"value="${news.newsTitle }" placeholder="请输入新闻原标题"/><form:errors path="newsTitle"></form:errors>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">新闻短标题</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="newsExTitle" style=" width:900px;"value="${news.newsExTitle }" placeholder="请输入新闻短标题"/><form:errors path="newsTitle"></form:errors>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">新闻类型</label>
						<div class="col-md-2" style=" width:60%">
							<c:forEach items="${news.newsTypeEnumList }" var="newTypeEnum" varStatus="index">
								<input  type="checkbox" name="newsTypeEnum"  onclick="checkNewsType();" id="newsType_${index.index}" value="${newTypeEnum.enumCode }"/>
								<span style=" display:inline-block; width:10%">${newTypeEnum.enumDescCn }</span>
								<c:if test="${(index.index+1)%6==0}">
								</br></c:if>
							</c:forEach>
							<input type="hidden" name="newsRole" value="${news.newsRole }"/>
							<input type="hidden" name="projectNo" value="${news.projectNo }"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">创建时间</label>
						<div class="col-md-2">
							<input placeholder="请输入日期" class="laydate-icon" name="newsDate" style="height:30px" 
							value="${news.newsDate }" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm'})">
						</div>
						<%-- <label class="col-md-1 control-label">新闻时间</label>
						<div class="col-md-2">
							<input placeholder="请输入日期" class="laydate-icon" name="newsYear" value="${news.newsYear }" onClick="laydate({istime: true, format: 'YYYY'})">
						</div> --%>
				</div>
					<div class="form-group">
						<label class="col-md-1 control-label">来源</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="newsSource" value="${news.newsSource }" placeholder="请输入来源"/><form:errors path="newsDate"></form:errors>
						</div>
						<label class="col-md-1 control-label">是否前台展示</label>
							<div class="col-md-2">
								是：<input type="radio" id="yshow"  name="isShow" value="1"/>
                     			否：<input type="radio" id="nshow" name="isShow" value="0"/>
							</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">维护浏览量</label>
						<div class="col-md-2">
							<input type="text" class="form-control" name="fakeCounts" value="${news.fakeCounts- (news.realCounts eq ''?0:news.realCounts)}" /><form:errors path="newsDate"></form:errors>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">新闻词条</label>
						<c:if test="${news.searchWord !=null &&!news.searchWord.isEmpty()}">
							<c:forEach items="${news.searchWord }" var="words" varStatus="status">
								<div class="form-group" style="margin-bottom:5px;">
								<c:if test="${status.index>0}">
									<label class="col-md-1 control-label" style="margin-right:12px;"></label>
								</c:if>
									<div class="col-md-2"> 
										<input type="hidden" value=' ${words.word }' name="oldWordListword"  />
		                				<input type="text" value=' ${words.word}' name="Listword" class="form-control"/>
		                				
			                      	</div>
		                      		<input type="button" onclick="deleteWord('${news.newsNo}','${words.word}')" value="删除" class="btn btn-success btn-sm"/>
			                     </div>
							</c:forEach>
						</c:if>
						<div class="form-group" style="margin-bottom:5px;">
							<label class="col-md-1 control-label" style="margin-right:12px;"></label>
							<div class="col-md-2" id="add">
							</div>
							<input type="button" onclick="addWord();" value="新增词条" class="btn btn-success btn-sm"/>
						</div>
					</div>
					
					<div class="form-group">
							<label class="col-md-1 control-label">新闻图片</label>
							<div class="col-md-3">
								<input type="hidden" class="form-control" name="newsMediaNo" value="${news.newsMediaNo }" >
								<input type="hidden" class="form-control" name="shareMediaNo" value="${news.shareMediaNo }" >
								<input type="hidden" class="form-control" name="bigMediaNo" value="${news.bigMediaNo }" >
                     			<img id="newsPicPath" src="" height="100" width="100">
                     			<input type="button" onclick="toUpload('newsMediaNo');" value="上传" class="btn btn-success"/>
                     			<input type="button" onclick="toChear('newsPicPath','newsMediaNo');" value="清空" class="btn"/>
                     			<label style="color:red">*文件大小不能超过10M</label>
							</div>
					</div>
					
					<%-- <div class="form-group">
							<label class="col-md-1 control-label">分享图片</label>
							<div class="col-md-3">
								<input type="hidden" class="form-control" name="shareMediaNo" value="${news.shareMediaNo }" >
                     			<img id="sharePicPath" src="" height="100" width="100">
                     			<input type="button" onclick="toUpload('newsShareMediaNo');" value="上传" class="btn btn-success"/>
                     			<input type="button" onclick="toChear('sharePicPath','shareMediaNo');" value="清空" class="btn"/>
                     			<label style="color:red">*文件大小不能超过10M</label>
							</div>
					</div>
					
					<div class="form-group">
							<label class="col-md-1 control-label">广告图片</label>
							<div class="col-md-3">
								<input type="hidden" class="form-control" name="bigMediaNo" value="${news.bigMediaNo }" >
                     			<img id="bigPicParh" src="" height="100" width="100">
                     			<input type="button" onclick="toUpload('newsBigMediaNo');" value="上传" class="btn btn-success"/>
                     			<input type="button" onclick="toChear('bigPicParh','bigMediaNo');" value="清空" class="btn"/>
                     			<label style="color:red">*文件大小不能超过10M</label>
							</div>
					</div> --%>
					
					
					<div class="form-group">
						<label class="col-md-1 control-label">新闻内容</label>
						<div class="col-md-9">
							<textarea name="newsContent" style="width: 80% !important;height: 500px !important;" cols="100" rows="8">${news.newsContent}</textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-md-9 mt10 text-center">
							<button type="submit" class="btn btn-success">提交</button>
							<button type="button" class="btn btn-success" onclick="newsPreview();">预览</button>
							<button type="button" class="btn btn-danger" onclick="history.go(-1)">返回</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	
	
<div class="wrapper" id="newsPreview"  style="display: none">
	<div class="shade" id="inputShade" style="z-index:99;"></div>
	<!--遮罩层开始-->
	<div class="shade" id="shade">
	    <div class="prompt">
	        <div class="share-div">
	            <div class="share-detail" id="wx"><img src="<%=path%>/assets/images/icon/fx_wx.png"/><span>微信</span></div>
	            <div class="share-detail" id="pyq"><img src="<%=path%>/assets/images/icon/fx_pyq.png"/><span>微信朋友圈</span></div>
	            <div class="share-detail" id="qq"><img src="<%=path%>/assets/images/icon/fx_qq.png"/><span>手机QQ</span></div>
	            <div class="share-detail" id="wb"><img src="<%=path%>/assets/images/icon/fx_wb.png"/><span>新浪微博</span></div>
	            <div style="clear:both"></div>
	        </div>
	        <div class="share-cancel" id="shareCancel">取消</div>
	    </div>
	</div>
	<!--遮罩层结束-->
	<!-- header：start -->
	<c:if test="${isWebview!=1}">
		<div class="xsb-header-bar">
			<span class="arrow-left" id="back"></span>
		    <h1></h1>
		    <img src="<%=path%>/assets/images/icon/fx.png" class="img-right" id="share"/>
<!-- 		    <i class="icon-collect icon-right" id="collect"></i> -->
		</div>
	</c:if>
	<!-- header：end -->
	<div class="content">
    <div class="title-t">
        <h1 id ="preview_title"></h1>
        <span class="title-pl" id="preview_newsSource" ></span>
        <span class="title-pr" id="preview_realCounts"></span>
    </div>
    <img id ="preview_newsPicPath" src="<%=path%>/assets/images/upload/img_main_1.jpg" class="con-img"/>
    <p class="con-txt" id="preview_newsContent">
    </p>
</div>
<div class="row">
						<div class="text-center">
							<button type="button" class="btn btn-success" onclick="ok();">确定</button>
						</div>
					</div>
</div>

  </body>
</html>
