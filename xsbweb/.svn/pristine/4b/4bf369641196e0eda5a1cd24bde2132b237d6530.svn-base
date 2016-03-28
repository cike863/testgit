<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <title>add</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/dtpicker/js/laydate.js" ></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/layer/layer.js" ></script>
    <style>
        .mt50{margin-top:50px;}
        .mt20{margin-top:20px;}
    </style>
</head>
<script type="text/javascript">
$(function(){
	if(${meetRoomVO.isShow=='0'}){
		$("#nshow").attr("checked","checked");
	}
	if(${meetRoomVO.isShow=='1'}){
		$("#yshow").attr("checked","checked");
	}
	var url="<%=hostName%>";
	/* if(${meetRoomVO.trsMedias != null && !meetRoomVO.trsMedias.isEmpty()}){
		<c:forEach items="${meetRoomVO.trsMedias}" var="object" varStatus="status" >
			if(${meetRoomVO.meetMediaNo !='' }&& ${object.mediaNo==meetRoomVO.meetMediaNo}){
				$("#meetMediaPic").attr("src","http://"+url+":8080/dyly${object.mediaLocation}");	
			}else if(${meetRoomVO.shareMediaNo !='' } && ${object.mediaNo==meetRoomVO.shareMediaNo}){
				$("#shareMediaPic").attr("src","http://"+url+":8080/dyly${object.mediaLocation}");		
			}else if(${meetRoomVO.bigPicNo !='' }  && ${object.mediaNo==meetRoomVO.bigPicNo}){
				$("#bigPic").attr("src","http://"+url+":8080/dyly${object.mediaLocation}");
			}else if(${meetRoomVO.pptMediaNo !='' }  && ${object.mediaNo==meetRoomVO.pptMediaNo}){
				$("#pptMediaUrl").val("http://"+url+":8080/dyly${object.mediaLocation}");
			} 
			
		</c:forEach>	
	}	 */	
	if(${meetRoomVO.meetMediaPath!=''&&meetRoomVO.meetMediaPath!=null}){
		$("#meetMediaPic").attr("src","http://"+url+":8080/dyly${meetRoomVO.meetMediaPath}");	
	} 
	if(${meetRoomVO.shareMediaPath!=''&&meetRoomVO.shareMediaPath!=null}){
		$("#shareMediaPic").attr("src","http://"+url+":8080/dyly${meetRoomVO.shareMediaPath}");	
	} 
	if(${meetRoomVO.bigMediaPath!=''&&meetRoomVO.bigMediaPath!=null}){
		$("#bigPic").attr("src","http://"+url+":8080/dyly${meetRoomVO.bigMediaPath}");	
	}
	if(${meetRoomVO.pptMediaPath!=''&&meetRoomVO.pptMediaPath!=null}){
		$("#pptMediaUrl").attr("src","http://"+url+":8080/dyly${meetRoomVO.pptMediaPath}");	
	}
}); 
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
		itemValue="";
		var url = " item/toAddItems";
		layer.open({
		    type: 2,
		    area: ['800px', '530px'],
		    fix: false, //不固定
		    maxmin: true,
		    content: url
		});
	} 
	var isUpdateItem=true;
	var mark;
	var index;
	var itemValue;
	function getItemValue(){
		return itemValue;
	}
	function updateItem(k,itemKey,start){
		mark=k;
		index = start;
		/* itemValue=$(k).parents('tr').eq(0).find('td').eq(2).find('[name="oldVoiceItemList['+index+'].itemValue"]').val(); */
		itemValue=$(k).parents('tr').eq(0).find('td').eq(3).html();
		itemValue=itemValue.replace(/\\\"/g,'"').replace(/\\\'/g,"'");
		isUpdateItem=false;
		var url = " item/toAddItems?itemKey="+itemKey+"&itemValue="; 		
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
		argValue=arg2.replace(/"/g,'\\\"').replace(/'/g,"\\\'");;
		/* if(argValue.length>2000){
			alert("文本过长");
			return;
		} */
		if(isUpdateItem){
			var itemTablehtml = $("#itemTable").html();
			var trHtml ="";
				trHtml += "<tr><td hidden=\"hidden\"><input readOnly name=\"voiceItemList["+size+"].itemKey\" value=\""+arg1+"\"/></td>"
				+"<td hidden=\"hidden\" ><input  name=\"voiceItemList["+size+"].itemValue\" value=\'"+argValue+"\'/></td>"
				+"<td hidden=\"hidden\">"+arg2+"</td>"
				+"<td >"+arg1+"</td>"
				+"<td><a href=\"javascript:void(0);\" onclick=\"deleteItem(this)\">delete</a></td></tr>";
			$("#itemTable").html(itemTablehtml+trHtml);
		}
		if(!isUpdateItem){	
			k=mark;
			$(k).parents('tr').eq(0).find('td').eq(1).find('[name="oldVoiceItemList['+index+'].itemKey"]').val(arg1);
			$(k).parents('tr').eq(0).find('td').eq(2).find('[name="oldVoiceItemList['+index+'].itemValue"]').val(argValue);
			$(k).parents('tr').eq(0).find('td').eq(3).html(arg2);
			$(k).parents('tr').eq(0).find('td').eq(4).html(arg1);
			isUpdateItem=true;
		}
	} 
	function deleteItem(k){
		 $(k).parent().parent().remove();     
	}
	function deleteObjectItem(objectNo,itemKey){
		objectNo=objectNo.replace(/\s+/g,"");
		itemKey=itemKey.replace(/\s+/g,"");
		$.ajax({
			url :"admin/project/deleteItem?objectNo="+objectNo+"&itemKey="+itemKey,
			contentType : 'application/json',
			async: false,
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
	function chooseProjectOK(projectNo,projName){
		$("[name='projectNo']").val(projectNo);
		$("[name='projName']").val(projName);
	}
	function toChear(k,f){
		if(k!="pptMediaNo"){
			$("#"+k+"").attr("src","");
		}
		$("[name="+f+"]").val('');
	}
</script>
<body>
<div class="container">

    <div class="row">
        <div class="col-md-12 mt20">
            <form class="form-inline" action="meet/editMeetRoom.do" name="newsForm"  method="post">
            <input type="hidden" name="meetNo" value="${meetRoomVO.meetNo }">
            <table class="table table-bordered table-hover table-responsive">
                <tbody>
                  <tr>
                    <td>语音直播预告名称</td>
                    <td><input type="text" class="form-control"  name="confName" value="${meetRoomVO.confName }" style=" width:900px;" ></td>
                </tr>
                <tr>
                    <td>对应项目ID</td>
                    <td>
                    	<input type="hidden" class="form-control"  name="projectNo" value="${meetRoomVO.projectNo}" >
	                    <input type="text" class="form-control"  name="projName"  value="${meetRoomVO.projName }" >
	                    <button type="button" onclick="chooseProject()" class="btn btn-success">选择项目</button>
                    </td>
                </tr>
                <tr>
                    <td>上线时间</td>
                     <td>
                     	<input placeholder="请输入日期" class="laydate-icon" name="onlineDate" style="height:30px" 
                     	value="${meetRoomVO.onlineDate }" id="t1" onClick="laydate({elem: '#t1',istime: true, format: 'YYYY-MM-DD hh:mm'})">
                     </td>
                </tr>
                <tr>
                    <td>直播时间</td>
                     <td>
                     	<input placeholder="请输入日期" class="laydate-icon" name="showDate" style="height:30px" 
                     	 value="${meetRoomVO.showDate }"id="t2" onClick="laydate({elem: '#t2',istime: true, format: 'YYYY-MM-DD hh:mm'})">
                     </td>
                </tr>
                <tr>
                    <td>是否前台展示</td>
                     <td>
                     	是：<input type="radio" id="yshow"name="isShow" value="1"/>
                     	否：<input type="radio" id="nshow" name="isShow" value="0"/>
                     </td>
                </tr>
               <%-- <c:if test="${!meetRoomVO.trsMedias.isEmpty()}" >
	                	<c:forEach items="${meetRoomVO.trsMedias}" var="object" varStatus="index">
	                		<c:if test="${object.mediaNo==meetRoomVO.meetMediaNo}"> --%>
	                			<tr>
                    				<td>定版图</td>
                    				<td>
                    					<input type="hidden" class="form-control" name="meetMediaNo" value="${meetRoomVO.meetMediaNo}">
                    					<img id="meetMediaPic" src="" height="100" width="100">
                    					<input type="button" onclick="toUpload('voiceListMediaNo','image');" value="上传" class="btn btn-success"/>
                    					<input type="button" onclick="toChear('meetMediaPic','meetMediaNo');" value="清空" class="btn"/>
                    					<label style="color:red">*文件大小不能超过10M</label>
                    				</td>
                				</tr>
	                		<%-- </c:if>
	                		<c:if test="${object.mediaNo==meetRoomVO.shareMediaNo}">  --%>
	                			<tr>
                   	 				<td>分享用图片</td>
                     				<td>
                     					<input type="hidden" class="form-control" name="shareMediaNo" value="${meetRoomVO.shareMediaNo}">
                     					<img id="shareMediaPic" src="" height="100" width="100">
                     					<input type="button" onclick="toUpload('voiceShareMediaNo','image');" value="上传" class="btn btn-success"/>
                     					<input type="button" onclick="toChear('shareMediaPic','shareMediaNo');" value="清空" class="btn"/>
                     					<label style="color:red">*文件大小不能超过10M</label>
                     				</td>
                				</tr>	                		
	                		<%-- </c:if>
                			<c:if test="${object.mediaNo==meetRoomVO.bigPicNo}">  --%>
                				<tr>
                    				<td>广告图片</td>
                     				<td>
                     					<input type= "hidden" class="form-control" name="bigPicNo" value="${meetRoomVO.bigPicNo}">
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
                		<%-- 	</c:if>
               		</c:forEach>
		      </c:if> --%> 
                
                 <tr>
                    <td>分享用主标题</td>
                     <td><input type="text" class="form-control" name="meetTitle" value="${meetRoomVO.meetTitle }" style=" width:900px;" ></td>
                </tr>
                 <tr>
                    <td>分享用副标题</td>
                     <td><input type="text" class="form-control" name="meetExTitle" value="${meetRoomVO.meetExTitle }" style=" width:900px;" ></td>
                </tr>
                <%-- <tr>
                	<td colspan="2">	
                		<table id="itemTable" style="width: 95% !improtant;" class="table table-bordered table-hover table-responsive">
                			<tr>
                				<td>itemKey</td>
                				<!-- <td>itemValue</td> -->
                				<td>operation <a href="javascript:void(0);" onclick="toAddItem();">+</a></td>
                			</tr>
                			
                	<c:if test="${!meetRoomVO.voiceItemList.isEmpty()}" >
	                	<c:forEach items="${meetRoomVO.voiceItemList}" var="object" varStatus="status">
	                		<tr >
	                			<td  style="display: none"><input type="text" value='${object.itemKey }' name="oldVoiceItemList[${ status.index}].oldItemKey"/></td>
	                			<td hidden="hidden" > <input readonly="readonly" border:0px solid #c00 type="text" value='${object.itemKey}' name="oldVoiceItemList[${ status.index}].itemKey"/></td>
	                			<td hidden="hidden" ><input  type="text" value='${object.itemValue}' name="oldVoiceItemList[${ status.index}].itemValue" ></td>
								<td hidden="hidden" >${fn:replace(object.itemValue, '\\\"', '"')}</td >
								<td >${object.itemKey}</td >
		                      	<td><a href="javascript:void(0);" onclick="updateItem(this,'${object.itemKey }','${ status.index}')">修改</a>|<a href="javascript:void(0);" onclick="deleteObjectItem('${object.objectNo}','${object.itemKey}')">删除</a></td>
		                      </tr>
		                 </c:forEach>
		         	</c:if>
                			
                			
                		</table>              	
                	</td>
                </tr> --%>
                
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