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
    <title>我要敲钟修改</title>
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
			if(${trsProject.isShow=='2'}){
				$("#nshow").attr("checked","checked");
			}
			if(${trsProject.isShow=='1'}){
				$("#yshow").attr("checked","checked");
			}
			
			if(${trsProject.projShowStatus=='1'}){
				$("#projShowStatus1").attr("checked","checked");
			}
			if(${trsProject.projShowStatus=='2'}){
				$("#projShowStatus2").attr("checked","checked");
			}
			if(${trsProject.projShowStatus=='3'}){
				$("#projShowStatus3").attr("checked","checked");
			}
			var url="<%=hostName%>";
				/* if(${trsProject.trsMediaList != null && !trsProject.trsMediaList.isEmpty()}){
					<c:forEach var="object" items="${trsProject.trsMediaList}">
					dd="${object.mediaNo}";
					if(${trsProject.projMediaNo != ''} && ${object.mediaNo==trsProject.projMediaNo}){
							$("#projMediaPic").attr("src","http://"+url+":8080/dyly${object.mediaLocation}");	
					}else if(${trsProject.shareMediaNo != ''} && ${object.mediaNo==trsProject.shareMediaNo}){
						$("#shareMediaPic").attr("src","http://"+url+":8080/dyly${object.mediaLocation}");		
					}else if(${trsProject.bigPicNo != '' } && ${object.mediaNo==trsProject.bigPicNo}){
						$("#bigPic").attr("src","http://"+url+":8080/dyly${object.mediaLocation}");
					} 
					</c:forEach>
				} */
				if(${trsProject.projMediaPath!=''}){
					$("#projMediaPic").attr("src","http://"+url+":8080/dyly${trsProject.projMediaPath}");	
				} 
				if(${trsProject.shareMediaPath!=''}){
					$("#shareMediaPic").attr("src","http://"+url+":8080/dyly${trsProject.shareMediaPath}");	
				} 
				if(${trsProject.bigMediaPath!=''}){
					$("#bigPic").attr("src","http://"+url+":8080/dyly${trsProject.bigMediaPath}");	
				}
		};
		var itemValue;
		function toAddItem(){
			var url = " item/toAddItems";
			itemValue="";
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
		function updateItem(k,itemKey,index){
			mark=k;
			/* itemValue=$(k).parents('tr').eq(0).find('td').eq(2).find('[name="itemValue"]').val(); */
			itemValue=$(k).parents('tr').eq(0).find('td').eq(3).html();
			itemValue=itemValue.replace(/\\\"/g,'"');
			
			isUpdateItem=false;
			var url = " item/toAddItems?itemKey="+itemKey+"&&itemValue=";
			layer.open({
			    type: 2,
			    area: ['800px', '530px'],
			    fix: false, //不固定
			    maxmin: true,
			    content: url
			});

		}
		var itemSize = 0;
		
		function getItemValue(){
			return itemValue;
		}
		function isOK(arg1,arg2,size){
			var argValue="";
			argValue=arg2.replace(/"/g,'\\\"').replace(/'/g,"\\\'");
			/* if(argValue.length>2000){
				alert("文本过长");
				return;
			} */
			if(isUpdateItem){
				var itemTablehtml = $("#itemTable").html();
				var trHtml ="";
					trHtml += "<tr><td hidden=\"hidden\" ><input readOnly name=\"itemKey\" value=\""+arg1+"\"/></td>"
					+"<td hidden=\"hidden\" ><input  name=\"itemValue\" value=\'"+argValue+"\'/></td>"
					+"<td  hidden=\"hidden\" >"+arg2+"</td>"
					+"<td  >"+arg1+"</td>"
					+"<td><a href=\"javascript:void(0);\" onclick=\"deleteItem(this)\">delete</a></td></tr>";
				$("#itemTable").html(itemTablehtml+trHtml);
			}
			if(!isUpdateItem){	
				k=mark;
				$(k).parents('tr').eq(0).find('td').eq(1).find('[name=".itemKey"]').val(arg1);
				$(k).parents('tr').eq(0).find('td').eq(2).find('[name="itemValue"]').val(argValue);
				$(k).parents('tr').eq(0).find('td').eq(3).html(arg2);
				$(k).parents('tr').eq(0).find('td').eq(4).html(arg1);
				isUpdateItem=true;
			}
			/* if(isUpdateItem){
				var itemTablehtml = $("#itemTable").html();
				var trHtml = "<tr><td><input readOnly name=\"itemKey\" value=\""+arg1+"\"/></td><td><input readOnly name=\"itemValue\" value=\""+arg2+"\"/></td><td><a href=\"javascript:void(0);\" onclick=\"deleteItem(this)\">delete</a></td></tr>";
				$("#itemTable").html(itemTablehtml+trHtml);
			}
			if(!isUpdateItem){	
				k=mark;
				$(k).parents('tr').eq(0).find('td').eq(1).find('[name=itemKey]').val(arg1);
				$(k).parents('tr').eq(0).find('td').eq(2).find('[name=itemValue]').val(arg2);
				isUpdateItem=true;
			} */
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
		
		function uptateData(objectNo){
				objectNo=objectNo.replace(/\s+/g,"");
				$.ajax({
					url :"admin/project/"+objectNo+"/upd",
					type:"PUT",
					async: false,
					success:function(data){
						if(data.resultCode==1){
							alert("操作成功。。。。。。。。。");
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
		function checkProjectCpCode(){
			/* var regex = "^(43|83)\d{4}$"; */
			var reg =/^\d{6}$/; 
			var projCpCode=$("[name='projCpCode']").val();
			if(reg.test(projCpCode)!=true){
				alert("请输入正确的股票代码");
			}
		}
	</script>
</head>
<body>
<div class="container">

    <div class="row">
        <div class="col-md-12 mt20">
            <form class="form-inline"  action="admin/project/${trsProject.projectNo}/upd" method="post"  >
           <input type="hidden" name="_method" value="put" /> 
            <table class="table table-bordered table-hover table-responsive">
                <tbody>
                  <tr>
                    <td >项目编号</td>
                    <td colspan="2" width="600px">${trsProject.projectNo}</td>
                </tr>
                <%-- <tr>
                	<td >关联公司代码</td>
                	<td colspan="2"><input type="text" value="${trsProject.projCpCode}" name ="projCpCode"  style="width:900px;"/></td>
                </tr> --%>
                <c:if test="${trsProject.prmtParentNo ne ''}">
                	<tr>
                    	<td>集体编号</td>
                    	<td><input type="text" class="form-control"  name="prmtParentNo" value="${trsProject.prmtParentNo}" readonly="readonly" ></td>
                	</tr>
                </c:if>
                <tr>
                	<td >关联股票代码</td>
                	<td colspan="2"><input type="text" value="${trsProject.projCpCode}" name ="projCpCode"  style="width:900px;"onchange="checkProjectCpCode()" /></td>
                </tr>
                 
                <tr>
                    <td>是否前台展示</td>
                     <td colspan="2">
                     	是：<input type="radio"  id="yshow" name="isShow" value="1"/>
                     	否：<input type="radio" id="nshow" name="isShow" value="2"/>
                     </td>
                </tr>
               <%--  <tr>
                	<td>转让方式</td>
                	<td  colspan="2">协议：<input type="radio" value="协议"  name="projAssignmentMay"  checked/>&nbsp;&nbsp;&nbsp;&nbsp;
							做市：<input type="radio" value="做市" name="projAssignmentMay" /></td>
                </tr>
                 <tr>
                	<td>辅导机构</td>
                	<td  colspan="2"><input type="text" value="${trsProject.projCoachOrg}" name ="projCoachOrg"  style="width:900px;"/></td>
                </tr> --%>
               
                <tr>
                	<td>上线时间</td>
                	<td colspan="2">
                	<input placeholder="请输入日期" class="laydate-icon" name="projOnlineDate" style="height:30px" 
                	value="${trsProject.projOnlineDate}" id="t1" onClick="laydate({elem: '#t1',istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
                	</td>
                </tr>
                <tr>
                    <td>定版图</td>
                     	<td colspan="2">
                     	<input type="hidden" class="form-control" name="projMediaNo" value="${trsProject.projMediaNo}" >
                     	<img id="projMediaPic" src="" height="100" width="100">
                     	<input type="button" onclick="toUpload('projListMediaNo');" value="上传" class="btn btn-success"/>
                     	<input type="button" onclick="toChear('projMediaPic','projMediaNo');" value="清空" class="btn"/>
                     	<label style="color:red">*文件大小不能超过10M</label>
                     </td>
                </tr>
                <tr>
                    <td>分享图片</td>
                     	<td colspan="2">
                     		<input type="hidden" class="form-control" name="shareMediaNo" value="${trsProject.shareMediaNo}">
                     		<img id="shareMediaPic" src="" height="100" width="100">
                     		<input type="button" onclick="toUpload('projShareMediaNo');" value="上传" class="btn btn-success"/>
                     		<input type="button" onclick="toChear('shareMediaPic','shareMediaNo');" value="清空" class="btn"/>
                     		<label style="color:red">*文件大小不能超过10M</label>
                     	</td>
                </tr>
                <tr>
                    <td>广告图片</td>
                     	<td colspan="2">
                     		<input type="hidden" class="form-control" name="bigPicNo" value="${trsProject.bigPicNo}">
                     		<img id="bigPic" src="" height="100" width="100">
                     		<input type="button" onclick="toUpload('projbigPicNo');" value="上传" class="btn btn-success"/>
                     		<input type="button" onclick="toChear('bigPic','bigPicNo');" value="清空" class="btn"/>
                     		<label style="color:red">*文件大小不能超过10M</label>
                     	</td>
                </tr>
                
                
                 <tr>
                    <td>分享标题</td>
                     <td colspan="2"><input type="text" class="form-control" style=" width:900px;" value="${trsProject.projectTitle}"  name="projectTitle"></td>
                </tr>
                 <tr>
                    <td>分享描述</td>
                     <td colspan="2"><input type="text" class="form-control" style=" width:900px;" value="${trsProject.projectExTitle}" name="projectExTitle"></td>
                </tr>
                
                <%-- <tr>
                    <td>总报名人数</td>
                    <td colspan="2"><input type="text" class="form-control" value="40" value="${trsProject.maxProjectPersonAmt}"   name="maxProjectPersonAmt"></td>
                </tr> --%>
                <tr>
                    <td>浏览/PV</td>
                    <td colspan="2"><input type="text" class="form-control" value="${trsProject.fakeCount}"  name="fakeCount">
                    <input type="hidden" value="${trsProject.projRole}" name="projRole" /></td>
                </tr>
               <tr>
                    <td>敲钟状态</td>
                	<td>直播：<input type="radio" value="1"  name="projShowStatus" id="projShowStatus1"  />&nbsp;&nbsp;&nbsp;&nbsp;
							预告：<input type="radio" value="2" name="projShowStatus"  id="projShowStatus2" />&nbsp;&nbsp;&nbsp;&nbsp;
							回放：<input type="radio" value="3" name="projShowStatus"  id="projShowStatus3" /></td>
							
                </tr>
		          <%-- <tr>
                	<td colspan="3">	
                		<table id="itemTable" style="width: 95% !improtant;" class="table table-bordered table-hover table-responsive">
                			<tr>
                				<td>itemKey</td>
                				<!-- <td>itemValue</td> -->
                				<td>operation <a href="javascript:void(0);" onclick="toAddItem();">+</a></td>
                			</tr>
                	<c:if test="${!objectList.isEmpty()}" >
	                	<c:forEach items="${objectList}" var="object" varStatus="index">
	                		<tr >
	                			<td  style="display: none"><input type="text" value='${object.itemKey }' name="oldItemKey"/></td>
	                			<td><input readonly="readonly" type="text" value='${object.itemKey}' name="itemKey"/></td>
	                			<td><input readonly="readonly" type="text" value='${object.itemValue}' name="itemValue" /></td>
	                			<td><a href="javascript:void(0);" onclick="deleteObjectItem(' ${trsProject.projectNo}','${object.itemKey}')">delete</a></td>
		                      	<td><a href="javascript:void(0);" onclick="updateItem(this,'${object.itemKey }','${object.itemValue}')">修改</a>|<a href="javascript:void(0);" onclick="deleteObjectItem('${object.objectNo}','${object.itemKey}')">删除</a></td>
		                      	
		                      	<td  style="display: none"><input type="text" value='${object.itemKey }' name="oldItemKey"/></td>
	                			<td hidden="hidden" > <input readonly="readonly"  type="text" name="itemKey" value='${object.itemKey}' /></td>
	                			<td hidden="hidden" ><input  type="text" name="itemValue"  value='${object.itemValue}' ></td>
								<td hidden="hidden" >${fn:replace(object.itemValue, '\\\"', '"')}</td >
								<td >${object.itemKey }</td >
		                      	<td><a href="javascript:void(0);" onclick="updateItem(this,'${object.itemKey }','${ status.index}')">修改</a>|<a href="javascript:void(0);" onclick="deleteObjectItem('${object.objectNo}','${object.itemKey}')">删除</a></td>
		                      </tr>
		                 </c:forEach>
		         	</c:if>
                			              			
                		</table>                	
                	</td>
                </tr> --%>
		          <tr>
                    <td colspan="3" class="text-center">
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