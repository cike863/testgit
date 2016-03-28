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
			if(${trsProject.isShow=='1'}){
				$("#show").attr("checked","checked");
			}else{
				$("#nshow").attr("checked","checked");
			}
		}
		
		function toAddItem(){
			var url = " item/toAddItems";
			layer.open({
			    type: 2,
			    area: ['800px', '530px'],
			    fix: false, //不固定
			    maxmin: true,
			    content: url
			});
		} 
		var itemSize = 0;
		function isOK(arg1,arg2,size){
			var argValue="";
			argValue=arg2.replace(/"/g,'\\\"').replace(/'/g,"\\\'");
		/* 	if(argValue.length>2000){
				alert("文本过长");
				return;
			} */
			var itemTablehtml = $("#itemTable").html();
				var trHtml ="";
					trHtml += "<tr><td hidden=\"hidden\" ><input readOnly name=\"itemKey\" value=\""+arg1+"\"/></td>"
					+"<td hidden=\"hidden\" ><input  name=\"itemValue\" value=\'"+argValue+"\'/></td>"
					+"<td hidden=\"hidden\" >"+arg2+"</td>"
					+"<td >"+arg1+"</td>"
					+"<td><a href=\"javascript:void(0);\" onclick=\"deleteItem(this)\">delete</a></td></tr>";
				$("#itemTable").html(itemTablehtml+trHtml);
		} 
/* 			
			
			var itemTablehtml = $("#itemTable").html();
			var trHtml = "<tr><td><input readOnly name=\"itemKey\" value=\""+arg1+"\"/></td><td><input readOnly name=\"itemValue\" value=\""+arg2+"\"/></td><td><a href=\"javascript:void(0);\" onclick=\"deleteItem(this)\">delete</a></td></tr>";
			$("#itemTable").html(itemTablehtml+trHtml);
		} */
		function deleteItem(k){
				 $(k).parent().parent().remove();     
		}
		function submit(){
			itemSize = 0;
			document.forms[0].submit();
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
		function uploadIsOk(mediaType,mediaNo,url,returnPath,newFileName,format,size){
			if(mediaType=="projListMediaNo"){
				$("[name=projMediaNo]").val(mediaNo);
				$("#projMediaPic").attr("src",url);
			}
			if(mediaType=="projShareMediaNo"){
				$("[name=shareMediaNo]").val(mediaNo);
				$("#shareMediaPic").attr("src",url);
			}
			if(mediaType=="projbigPicNo"){
				$("[name=bigPicNo]").val(mediaNo);
				$("#bigPic").attr("src",url);
			}
		}
		function toChear(k,f){
			$("#"+k+"").attr("src","");
			$("[name="+f+"]").val('');
		}
		var index =0;
		function addWord(){
			var html="<div class=\"col-md-12\" style=\"padding:0;\"><input type=\"text\"  class=\"form-control\"  style=\"margin-bottom:5px;\"placeholder=\"请输入词条\" name=\"searchWord["+(index+1)+"].word\" value=\"\" >";
			html+="<input type=\"text\"  class=\"form-control\"  style=\"margin-bottom:5px;margin-left:4px;\" placeholder=\"请输入词条顺序\"name=\"searchWord["+(index+1)+"].labOrder\" value=\"\" ></div>";
			$("#add").append(html);
			index++;
		}
	</script>
</head>
<body>
<div class="container">

    <div class="row">
        <div class="col-md-12 mt20">
            <form class="form-inline" action="admin/project/addProject" method="post">
            <input type="hidden" name="_method" value="post" />
            <table class="table table-bordered table-hover table-responsive">
                <tbody>
                 <tr>
                    <td class="col-md-2">项目名称</td>
                    <td class="col-md-10"><input type="text" class="form-control" style=" width:900px;" name="projName" value="${trsProject.projName}" ></td>
                </tr>
                
                <tr>
                    <td>关联公司代码</td>
                    <td><input type="text" class="form-control"  name="projCpCode" value="${trsProject.projCpCode}" ><span style=" color:red;">没有可不填，有是以43或83为前缀</span></td>
                </tr>
                <tr>
                    <td>广告语</td>
                    <td><input type="text" class="form-control"  style=" width:900px;"  name="projCpFullname" value="${trsProject.projCpFullname}" ></td>
                </tr>
                <tr>
                    <td>是否前台展示</td>
                     <td>
                     	是：<input type="radio" id="show"  name="isShow" value="1"/>
                     	否：<input type="radio" id="nshow" name="isShow" value="2" checked="checked"/>
                     </td>
                </tr>
                 <tr>
                	<td>转让方式</td>
                	<td><input type="radio" value="协议"  name="projAssignmentMay"  checked/>协议&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="做市" name="projAssignmentMay" />做市</td>
                </tr>
                 <tr>
                    <td>辅导机构</td>
                    <td><input type="text" class="form-control"  style=" width:900px;"  name="projCoachOrg" value="${trsProject.projCoachOrg}" ></td>
                </tr>
                <!-- <tr>
                    <td>项目词条</td>
                    <td>
                    	<div id="add" class="col-md-8" style="padding:0;">
	                    	<div class="col-md-12" style="padding:0;">
								<input type="text"  class="form-control"  style="margin-bottom:5px;" placeholder="请输入词条" name="searchWord[0].word" value="" >
								<input type="text"   class="form-control"  style="margin-bottom:5px;" placeholder="请输入词条顺序" name="searchWord[0].labOrder" value="" >
							</div>
						</div>
						<div class="col-md-4">
							<input type="button" onclick="addWord();" value="新增词条" class="btn btn-success"/>
						</div>
					</td>
                </tr> -->
                <tr>
                	<td>上线时间</td>
                	<td>
                	<input placeholder="请输入日期" class="laydate-icon" name="projOnlineDate" id="t1" style="height:30px" 
                	value="${trsProject.projOnlineDate}"  onClick="laydate({elem: '#t1',istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
                	</td>
                </tr>
				
                <tr>
                    <td>定版图</td>
                     	<td>
                     		<input type="hidden" class="form-control" name="projMediaNo" value="${trsProject.projMediaNo}" >
                     		<img id="projMediaPic" src="" height="100" width="100" name="projMediaPath" >
                     		<input type="button" onclick="toUpload('projListMediaNo');" value="上传" class="btn btn-success"/>
                     		<input type="button" onclick="toChear('projMediaPic','projMediaNo');" value="清空" class="btn"/>
                     		<label style="color:red">*文件大小不能超过10M</label>
                     	</td>
                </tr>
                <tr>
                    <td>分享图片</td>
                     	<td>
                     		<input type="hidden" class="form-control" name="shareMediaNo" value="${trsProject.shareMediaNo}" >
                     		<img id="shareMediaPic" src="" height="100" width="100" name="shareMediaPath" >
                     		<input type="button" onclick="toUpload('projShareMediaNo');" value="上传" class="btn btn-success"/>
                     		<input type="button" onclick="toChear('shareMediaPic','shareMediaNo');" value="清空" class="btn"/>
                     		<label style="color:red">*文件大小不能超过10M</label>
                     	</td>
                </tr>
                <tr>
                    <td>广告图片</td>
                     	<td>
                     		<input type="hidden" class="form-control" name="bigPicNo" value="${trsProject.bigPicNo}" >
                     		<img id="bigPic" src="" height="100" width="100" name="bigMediaPath" > 
                     		<input type="button" onclick="toUpload('projbigPicNo');" value="上传" class="btn btn-success"/>
                     		<input type="button" onclick="toChear('bigPic','bigPicNo');" value="清空" class="btn"/>
                     		<label style="color:red">*文件大小不能超过10M</label>
                     	</td>
                </tr>
                 <tr>
                    <td>分享标题</td>
                     <td><input type="text" class="form-control" style=" width:900px;" name="projectTitle" value="${trsProject.projectTitle}" ></td>
                </tr>
                 <tr>
                    <td>分享描述</td>
                     <td><input type="text" class="form-control" style=" width:900px;" name="projectExTitle" value="${trsProject.projectExTitle}" ></td>
                </tr>
                <tr>
                    <td>路演状态</td>
                	<td>路演中：<input type="radio" value="32"  name="projRole" checked  />&nbsp;&nbsp;&nbsp;&nbsp;
							成功案例：<input type="radio" value=" 64" name="projRole" /></td>
                </tr> 
               
                <!--  <tr>
                    <td>实时行情</td>
                    <td><input type="text" class="form-control"  name="projDynQuotes"></td>
                </tr>-->
                
                <tr>
                    <td>总报名人数</td>
                    <td><input type="text" class="form-control" value="40"  name="maxProjectPersonAmt" value="${trsProject.maxProjectPersonAmt}" ></td>
                </tr>
                <tr>
                    <td>浏览/PV</td>
                    <td><input type="text" class="form-control" name="fakeCount" value="${trsProject.fakeCount}" ></td>
                </tr>
							<!--  <tr>
                	<td colspan="2">	
                		<table id="itemTable" style="width: 95% !improtant;" class="table table-bordered table-hover table-responsive">
                			<tr>
                				<td>itemKey</td>
                				<td>itemValue</td>
                				<td>operation <a href="javascript:void(0);" onclick="toAddItem();">+</a></td>
                			</tr>               			
                		</table>                	
                	</td>
                </tr> -->
							<tr>
								<td>项目负责人</td>
								<td><select id="handler" name="handler"  style="width:200px;height:30px">
										<option value=""></option>
										<c:if test="${staffList!=null}">
											<c:forEach items="${staffList}" var="staff" varStatus="index">
												<option value="${staff.staffId }">${staff.staffName }</option>
											</c:forEach>
										</c:if>
								</select></td>
							</tr>

							<tr>
								<td colspan="4" class="text-center">
									<!--  -->
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