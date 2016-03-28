<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://ajaxanywhere.sourceforge.net/" prefix="aa"%>
<%@ taglib uri="http://java.xsb.com/xsbweb/tag/pager" prefix="page"%>
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
    <title>index</title>
    <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .mt10{margin-top:10px;}
    </style>
</head>
 	<script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/dtpicker/js/laydate.js" ></script>
	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/layer/layer.js" ></script>
 <script type="text/javascript" language="javascript">
 var itemValue;
 var oldItemKey;
	/* function toAddItem(){
		var url = " item/toAddItems";
		itemValue="";
		var index = layer.open({
		    type: 2,
		    area: ['800px', '530px'],
		    fix: false, //不固定
		    maxmin: true,
		    content: url
		});
		layer.style(index, {
		    top: '10px'
		});
	} */
	function toAddItem(){
		window.location="item/toAddItems?objectNo="+'${projectNo}';
	}
	var isUpdateItem=true;
	
	/* function updateItem(itemKey,itemLocationOrder,index){ */
	function updateItem(itemKey){
		/* if(itemKey.indexOf('%')>-1){
			itemKey=itemKey.replace(/\%/g,"%25");
		}
		if(itemKey.indexOf('+')>-1){
			itemKey=itemKey.replace(/\+/g, "%2B");
		} */
		/* itemKey=itemKey.replace(/\%/g,"%25")
									.replace(/\+/g, "%2B")
									.replace(/\#/g,"%23"); */
		/*itemKey=itemKey.replace(/=/g,"%3D"); */
		//encodeURIComponent----url路径转码
		window.location = " item/toAddItems?objectNo="+'${projectNo}'+"&itemKey="+encodeURIComponent(itemKey)+"&itemValue=1";
	}
	
	/*function updateItem(itemKey,itemLocationOrder,index){
		debugger;
		console.log("1234");
		/* itemValue=$("#itemValue"+index).html(); 
		//itemValue=$("#itemValue"+index).html();
		oldItemKey=itemKey;
		/* argValue=itemValue.replace(/"/g,'\\\"')
		.replace(/'/g,"\\\'")
		.replace(/#/g,"%23")
		.replace(/&/g,"%26")
		/* .replace(/?/g,"%3F") 
		.replace(/%/g,"%25")
		.replace(/=/g,"%3D")
		.replace(/\\"/g,""); */
		/* itemValue=itemValue.replace(/\\\"/g,'"')
										.replace(/\\\'/g,"'")
										.replace(/%23/g,"#")
										.replace(/%26/g,"&")
										/* .replace(/%3F/g,"?") 
										.replace(/%25/g,"%")
										.replace(/%3D/g,"=")
										.replace(/\\\&quot;/g,"");
		console.log(itemValue); 
		isUpdateItem=false;
		var url = " item/toAddItems?objectNo="+'${projectNo}'+"&itemKey="+itemKey;
		layer.open({
		    type: 2,
		    area: ['800px', '530px'],
		    fix: false, //不固定
		    maxmin: true,
		    content: url
		}); 
	}
	function getItemValue(){
		return itemValue;
	}
	function isOK(arg1,arg2,itemLocationOrder){
		var argValue="";
		//转义处理 在“ ’前加斜杠，将#转义成%23，将&转义成%26
		/* argValue=arg2.replace(/"/g,'\\\"')
								.replace(/'/g,"\\\'")
								.replace(/#/g,"%23")
								.replace(/&/g,"%26")
								/* .replace(/?/g,"%3F") 
								.replace(/%/g,"%25")
								.replace(/=/g,"%3D"); 
		console.log(isUpdateItem);
		if (isUpdateItem) {debugger;
				$.ajax({
					type : 'post',
					/* url : "admin/item/add?objectNo=" +'${projectNo}'+"&itemKey="+arg1+"&itemValue="+ argValue+"&itemLocationOrder="+ itemLocationOrder, 
					url : "admin/item/add",
					contentType : 'application/json',
					data : JSON.stringify({
						'objectNo' : '${projectNo}',
						'itemKey' : arg1,
						'itemValue' : argValue,
						'itemLocationOrder' : itemLocationOrder
					}),
					cache : false,
					dataType : 'json',
					async : false,
					success : function(data) {
						if (data.resultCode == '1') {
							document.location.reload();
						} else {
							alert("添加失败");
						}
						;
					},
					error : function() {
						alert("添加失败");
					}
				});
			}
		console.log("123:"+oldItemKey);
		console.log("123321:"+isUpdateItem);
			if (!isUpdateItem) {debugger;
				$.ajax({
					url : "admin/item/update",
					/* url : "admin/item/update?objectNo=" +'${projectNo}'+"&oldItemKey="+oldItemKey+"&itemKey="+arg1+"&itemValue="+argValue+"&itemLocationOrder="+ itemLocationOrder, 
					contentType : 'application/json',
					type : 'POST',
					data : JSON.stringify({
						'objectNo' : '${projectNo}',
						'oldItemKey' : oldItemKey,
						'itemKey' : arg1,
						'itemValue' : arg2,
						'itemLocationOrder' : itemLocationOrder
					}),
					success : function(data) {
						if (data.resultCode == '1') {
							document.location.reload();
						} else {
							alert("修改失败");
						}
						;
					},
					error : function() {
						alert("修改失败");
					}
				});
				isUpdateItem = true;
			}
		}*/
		function deleteObjectItem(objectNo, itemKey) {
			if(!confirm("确定要删除该数据吗？")){
				return;
			}
			objectNo = objectNo.replace(/\s+/g, "");
			itemKey = itemKey.replace(/\s+/g, "");
			$.ajax({
				url : "admin/project/deleteItem?objectNo=" + objectNo
						+ "&itemKey=" + itemKey,
				contentType : 'application/json',
				async : false,
				success : function(data) {
					if (data.resultCode == 1) {
						window.location.reload();
					} else if (data.resultCode == 0) {
						alert("操作失败");
					}
				},
				error : function() {
					alert("操作失败");
				}
			});
		}
	</script>
<body>
<!--<form action="meet/toMeetRoomList.do" name="confForm" method="post">-->
${error }
<div class="container-full">
    <div class="row">
        <div class="col-md-12 mt10">
            <form class="form-inline" action="admin/item" method="post"  name="confForm"><!--action="admin/project/serach?serachInformation="+information, name="confForm" method="post"-->
                
                <%-- <div class="input-group">
                	<span class="input-group-addon">itemKey</span>
                	<input type="text" class="form-control" id="itemKey" name="itemKey" placeholder="请输入查询内容" value="${(item.itemKey == '') ? '' : item.itemKey}" >
                </div>
                <div class="input-group">
                	<span class="input-group-addon">itemValue</span>
                	<input type="text" class="form-control" id="itemValue" name="itemValue" placeholder="请输入查询内容" value="${(item.itemValue == '') ? '' : item.itemValue}" >
                </div>
                <input type="hidden" name="_method" value="get" /> 
                <button class="btn btn-warning" type="submit">查询</button><!-- type="submit"  onclick="serach();--> --%>
                <a href="javascript:void(0);" onclick="toAddItem();" class="btn btn-success">新建</a>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 mt10">
        	<aa:zone name="confListZone">
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                    	<th>item序号</th>
                    	<th hidden="hidden">oldItemKey</th>
                    	<th>itemKey</th>
                    	<th>创建时间</th>
                        <th hidden="hidden">itemValue</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${itemList!=null}">
	                	<c:forEach items="${itemList}" var="item" varStatus="index">
	                		<tr>
	                			<%-- <td>${index.index+1}</td> --%>
	                			<td id="itemLocationOrder${index.index+1}">${item.itemLocationOrder}</td>
	                			<td hidden="hidden"id="oldItemKey${index.index+1}">${item.itemKey}</td>
	                			<td id="itemKey${index.index+1}">${item.itemKey}</td>
	                			<td >${item.createDate}</td>
		                        <td hidden="hidden" id="itemValue${index.index+1}">${item.itemValue}</td>
		                        
		                        <td>	
		                        	<%-- <c:if test="${item.itemKey ne '' }"> --%>
		                        		<%-- <a href="javascript:void(0);" onclick="updateItem('${item.itemKey }','${item.itemLocationOrder == '' ? 0 : item.itemLocationOrder}','${ index.index+1}')">修改</a>| --%>
		                        		<a href="javascript:void(0);" onclick="updateItem('${item.itemKey }')">修改</a>|
		                        		<a href="javascript:void(0);" onclick="deleteObjectItem('${projectNo}','${item.itemKey}')">删除</a>
		                        	<%-- </c:if> --%>
		                        </td>	
		                    </tr>
	                	</c:forEach>
                	</c:if>
                	 </tr>
		          <tr>
                    <td colspan="4" class="text-center">
                        <button type="button" class="btn btn-danger" onclick="history.go(-1)">返回</button>
                    </td>
                </tr>
                </tbody>
            </table>
            </aa:zone>
            <%-- <page:pager pageSize="${base.pageSize}" pageNo="${base.pageNo}" url="admin/project"  recordCount="${base.totalRecord}"/> --%>
        </div>
    </div>
</div>
<!--</form>-->
</body>

</html>