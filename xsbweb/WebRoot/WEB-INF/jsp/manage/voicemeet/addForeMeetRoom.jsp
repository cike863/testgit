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
    <title>add</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .mt50{margin-top:50px;}
        .mt20{margin-top:20px;}
    </style>
    
    
    <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/dtpicker/js/laydate.js" ></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/layer/layer.js" ></script>
	<script type="text/javascript">
	/* function chooseProject(){
		var url = "meet/toChooseProject.do";
		var DialogLocation = initShowModalDialog(500, 260);
		window.open(url,window,DialogLocation);
	}
	
	function initShowModalDialog(width,height){
		var iWidth = width;
	    var iHeight = height;
	    var iTop = (window.screen.availHeight - 20 - iHeight) / 2;
	    var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
	    return 'width=' + iWidth + 'px;height:' + iHeight + 'px;top: ' + iTop + 'px; left: ' + iLeft + 'px;center:yes;scroll:no;status:no;resizable:0;location:no';
	} */
	function chooseProject(){
		//$("[name='_method']").val("get");
		//var url = "admin/project";
	 	var url = "admin/chooseRelevanceProject";
		layer.open({
			type:2,
			area:['1000px','620px'],
			fix:false,
			maxmin:true,
			content:url
		});
	}
	//设置选择函数，将数据存入到
	function chooseOk(arg1,arg2,arg3){
		//console.log(arg1+","+arg2);
		$("[name='projectNo']").val(arg1);
		$("[name='projName']").val(arg2);
	}
		!function(){
			laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
			laydate({elem: '#demo'});//绑定元素
		}();
		
		window.onload = function(){
			$("#show").attr("checked","checked");
		};
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
			if(mediaType=="voiceListMediaNo"){
				$("[name=meetMediaNo]").val(mediaNo);
				$("#meetMediaPic").attr("src",url);
			}
			if(mediaType=="voiceShareMediaNo"){
				$("[name=shareMediaNo]").val(mediaNo);
				$("#shareMediaPic").attr("src",url);
			}
			if(mediaType=="voicebigPicNo"){
				$("[name=bigPicNo]").val(mediaNo);
				$("#bigPic").attr("src",url);
			}
			if(mediaType=="pptMediaNo"){
				$("[name=pptMediaNo]").val(mediaNo);
				$("#pptMediaUrl").val(url); 
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
			/* if(argValue.length>2000){
				alert("文本过长");
				return;
			} */
		
			var itemTablehtml = $("#itemTable").html();
				var trHtml ="";
					trHtml += "<tr><td hidden=\"hidden\"><input readOnly name=\"voiceItemList["+size+"].itemKey\" value=\""+arg1+"\"/></td>"
					+"<td hidden=\"hidden\" ><input  name=\"voiceItemList["+size+"].itemValue\" value=\'"+argValue+"\'/></td>"
					+"<td hidden=\"hidden\">"+arg2+"</td>"
					+"<td >"+arg1+"</td>"
					+"<td><a href=\"javascript:void(0);\" onclick=\"deleteItem(this)\">delete</a></td></tr>";
				$("#itemTable").html(itemTablehtml+trHtml);
		} 
		function deleteItem(k){
			 $(k).parent().parent().remove();     
		}
		/* function chooseProjectOK(projectNo,projName){
			$("[name='projectNo']").val(projectNo);
			$("[name='projName']").val(projName);
		} */
		function toChear(k,f){
			if(k!="pptMediaNo"){
				$("#"+k+"").attr("src","");
			}
			$("[name="+f+"]").val('');
		}
	</script>
</head>
<body>
<div class="container">

    <div class="row">
        <div class="col-md-12 mt20">
            <form class="form-inline" action="meet/addForeMeetRoom.do" method="post">
            <table class="table table-bordered table-hover table-responsive">
                <tbody>
                  <tr>
                    <td>语音直播预告名称</td>
                    <td><input type="text" class="form-control"  name="confName" style=" width:900px;" ></td>
                </tr>
                <tr>
                    <td>对应项目ID</td>
                    <td>
                    	<input type="hidden" class="form-control"  name="projectNo" />
	                    <input type="text" class="form-control"  name="projName"  />
	                    <button type="button" onclick="chooseProject()" class="btn btn-success">选择项目</button>
                    </td>
                </tr>
                <tr>
                    <td>上线时间</td>
                     <td>
                     	<input placeholder="请输入日期" class="laydate-icon" name="onlineDate" style="height:30px" 
                     	id="t1" onClick="laydate({elem: '#t1',istime: true, format: 'YYYY-MM-DD hh:mm'})">
                     </td>
                </tr>
                <tr>
                    <td>直播时间</td>
                     <td>
                     	<input placeholder="请输入日期" class="laydate-icon" name="showDate" style="height:30px" 
                     	id="t2" onClick="laydate({elem: '#t2',istime: true, format: 'YYYY-MM-DD hh:mm'})">
                     </td>
                </tr>
                <tr>
                    <td>是否前台展示</td>
                     <td>
                     	是：<input type="radio" id="show"  name="isShow" value="1"/>
                     	否：<input type="radio" id="nshow" name="isShow" value="0"/>
                     </td>
                </tr>
                <tr>
                    <td>定版图</td>
                    <td>
	                    <input type="hidden" class="form-control" name="meetMediaNo">
	                    <img id="meetMediaPic" src="" height="100" width="100">
	                    <input type="button" onclick="toUpload('voiceListMediaNo','image');" value="上传" class="btn btn-success"/>
	                    <input type="button" onclick="toChear('meetMediaPic','meetMediaNo');" value="清空" class="btn"/>
	                    <label style="color:red">*文件大小不能超过10M</label>
                    </td>
                </tr>
                <tr>
                    <td>分享用图片</td>
	                    <td><input type="hidden" class="form-control" name="shareMediaNo">
	                    <img id="shareMediaPic" src="" height="100" width="100">
	                    <input type="button" onclick="toUpload('voiceShareMediaNo','image');" value="上传" class="btn btn-success"/>
	                    <input type="button" onclick="toChear('shareMediaPic','shareMediaNo');" value="清空" class="btn"/>
                    <label style="color:red">*文件大小不能超过10M</label>
                    </td>
                </tr>
                <tr>
                    <td>广告图片</td>
                    <td>
	                    <input type="hidden" class="form-control" name="bigPicNo">
	                    <img id="bigPic" src="" height="100" width="100">
	                    <input type="button" onclick="toUpload('voicebigPicNo','image');" value="上传" class="btn btn-success"/>
	                    <input type="button" onclick="toChear('bigPic','bigPicNo');" value="清空" class="btn"/>
	                    <label style="color:red">*文件大小不能超过10M</label>
                    </td>
                </tr>
                
                <tr>
                    <td>PPT文件上传</td>
                    <td>
                    	<input type="hidden"   class="form-control" name="pptMediaNo" id="pptMediaNo">
                    	<input type="text" id="pptMediaUrl" placeholder="ppt文件路径" class="form-control" >
                    	<input type="button" onclick="toUpload('pptMediaNo','file');" value="上传" class="btn btn-success"/>
                    	<input type="button" onclick="toChear('pptMediaNo','pptMediaNo');" value="清空" class="btn"/>
                    	<label style="color:red">*文件大小不能超过10M</label>
                   	</td>
                </tr>
                 <tr>
                    <td>分享用主标题</td>
                     <td><input type="text" class="form-control" name="meetTitle" style=" width:900px;" ></td>
                </tr>
                 <tr>
                    <td>分享用副标题</td>
                     <td><input type="text" class="form-control" name="meetExTitle" style=" width:900px;" ></td>
                </tr>
                <!-- <tr>
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
                    <td colspan="2" class="text-center">
                        <button type="submit" class="btn btn-success">提交</button>
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