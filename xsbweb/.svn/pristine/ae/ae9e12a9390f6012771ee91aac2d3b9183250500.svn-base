<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://ajaxanywhere.sourceforge.net/" prefix="ajax"%>
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
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .mt10{margin-top:10px;}
    </style>
</head>
<body>
<!--<form action="meet/toMeetRoomList.do" name="confForm" method="post">-->
${error }
<div class="container-full">
    <div class="row">
        <div class="col-md-12 mt10">
            <form class="form-inline" action="meet/toMeetRoomList.do" name="confForm">
            	<span onclick="batchDeleteMeetRoom();"class="btn btn-danger" >删除</span>
            	<input type="hidden" name="method" value="post"/>
                <input type="text" class="form-control" id="search" value="${meetRoomVO.confLabel}"name="confLabel" placeholder="请输入查询内容">
                <input type="hidden" name="pageSize" id="pageSize" value="${meetRoomVO.pageSize}" /> 
                <button type="submit" class="btn btn-warning">查询</button>
                <a href="meet/toAddForeMeetRoom.do" role="button" class="btn btn-success">新建</a>
            </form>
        </div>
    </div>                             
    <div class="row">
        <div class="col-md-12 mt10">
        	<aa:zone name="confListZone">
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                    	<th><input type="checkbox" name="allCheckBox"  id="allCheckBox" onclick="checkMeetRoomAll(this);" >全选</th>
                    	<th>编号</th>
                        <th>名称</th> 
                        <th>会议直播ID</th>
                        <th>voiptoconfid</th>
                        <th>主持人</th>
                        <th>上线时间</th>
                        <th>直播时间</th>
                        <th>状态</th>
                        <th>是否展示</th>                 
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${meetRoomVOList!=null }">
	                	<c:forEach items="${meetRoomVOList}" var="mtroom" varStatus="index">
	                		<tr>
	                			<td><input type="checkbox" name="meetNoCheckBox" value="${index.index}"></td>
	                			<td id="meetNo_${index.index}">${mtroom.meetNo}</td>
		                        <td>${mtroom.confName}</td>
	                    	    <td>${mtroom.confid}</td>
		                        <td>${mtroom.voiptoconfid}</td>
		                        <td>${mtroom.compereName}</td>
		                        <td>${mtroom.onlineDate}</td>
	                         	<td>${mtroom.showDate}</td>
		                        <td>
		                        	<c:if test="${(mtroom.meetRole==''?0:mtroom.meetRole)>=1024&&(mtroom.meetRole==''?0:mtroom.meetRole)<2048}">
		                        		直播预告
		                        	</c:if>
		                        	<c:if test="${(mtroom.meetRole==''?0:mtroom.meetRole)>=2048&&(mtroom.meetRole==''?0:mtroom.meetRole)<4096}">
		                        		直播中
		                        	</c:if>
		                        	<c:if test="${(mtroom.meetRole==''?0:mtroom.meetRole)>=4096}">
		                        		直播结束
		                        	</c:if>
		                        </td>
		                        <td>
		                        	<c:if test="${mtroom.isShow == 1}">
		                        		展示
		                        	</c:if>
		                        	<c:if test="${mtroom.isShow != 1}">
		                        		不展示
		                        	</c:if>
		                        </td>
		                        <td>
		                        	<c:if test="${!empty mtroom.meetNo}">
				                       <a href="meet/lastEditDate.do?meetNo=${mtroom.meetNo}">置顶</a> | 
			                        </c:if>
		                        	<c:if test="${mtroom.isShow !=1}">
			                        	<a href="javascript:void(0);" onclick="updateMeetIsShowByMeetNo('${mtroom.meetNo}',1)">展示</a>
			                        </c:if> 
			                        <c:if test="${mtroom.isShow ==1}">
			                        	<a href="javascript:void(0);" onclick="updateMeetIsShowByMeetNo('${mtroom.meetNo}',0)">不展示</a>
			                        </c:if> | 
			                        <c:if test="${ !empty mtroom.confid &&((mtroom.meetRole==''?0:mtroom.meetRole)>=2048&&(mtroom.meetRole==''?0:mtroom.meetRole)<4096)}">
				                        <a href="meet/toMeetRoomInfo.do?confid=${mtroom.confid}">进入</a> | 
				                        <a href="javascript:void(0);" onclick="deletConfByConfid('${mtroom.confid}')">结束语音会议</a> |
			                        </c:if>
			                         <c:if test="${empty mtroom.confid}">
				                        <a href="meet/toAddMeetRoom.do?meetNo=${mtroom.meetNo}">新建语音会议</a> | 
				                        <%-- <a href="meet/toEditMeetByMeetNo.do?meetNo=${ mtroom.meetNo}" >编辑</a> |  --%>
			                        </c:if>
			                        <c:if test="${!empty mtroom.meetNo}">
			                        	<a href="meet/toEditMeetByMeetNo.do?meetNo=${ mtroom.meetNo}" >编辑</a> |
				                       <a href="javascript:void(0);" onclick="getitems('${mtroom.meetNo}')">查看item</a>
				                       <%--  | <a href="javascript:void(0);" onclick="deleteForeMeetRoom('${mtroom.meetNo}')">删除</a> --%>
			                        </c:if>
		                        </td>
		                    </tr>
	                	</c:forEach>
                	</c:if>
                </tbody>
            </table>
            </aa:zone>
            <page:pager pageSize="${meetRoomVO.pageSize eq '0' ?1:meetRoomVO.pageSize}" pageNo="${meetRoomVO.pageNo}" url="meet/toMeetRoomList.do" recordCount="${meetRoomVO.totalRecord}"/>
        </div>
    </div>
</div>
<!--</form>-->
</body>
 <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
 <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/aa.js"></script>
 <script type="text/javascript" language="javascript">
 	ajaxAnywhere.formName="confForm";
	ajaxAnywhere.getZonesToReload = function(){
		return "confListZone";
	} 
	function deletConfByConfid(confid){
		if(!confirm("确定要结束语音会议？")){
			return;
		}
		document.forms[0].action="meet/deleteConfByList.do?confid="+confid;
		ajaxAnywhere.submitAJAX();
	}
	function deleteForeMeetRoom(meetNo){
							    //http://localhost:8080/XSBmobile/test1.do
		if(!confirm("确定要删除该数据吗？")){
			return;
		}
		document.forms[0].action="meet/deleteMeetRoomByMeetNo.do?meetNo="+meetNo;
		ajaxAnywhere.submitAJAX();
	}
	
	function updateForeMeetRoom(meetNo){
		document.forms[0].action="meet/toEditMeetByMeetNo.do?meetNo="+meetNo;
		//console.log("meet/toEditMeetByMeetNo.do?meetNo="+meetNo);
		//alert("该功能暂未提供！");
		document.forms[0].submit();
	}

	function updateMeetIsShowByMeetNo(meetNo,isShow){
		//document.forms[0].action="meet/deleteMeetRoomByMeetNo.do?meetNo="+meetNo;
		//document.forms[0].action="meet/toUpdateMeetIsShowByMeetNo.do?meetNo="+meetNo+"&isShow="+isShow;
		//ajaxAnywhere.submitAJAX();
		$.ajax({
			cache:true,
			type : 'POST',
			url : "meet/toUpdateMeetIsShowByMeetNo.do?meetNo="+meetNo+"&isShow="+isShow,
			//data : $("#updateMeetToShowStatus").serialize(),
			async : false,
			error : function (){
				alert("操作失败");
			},
			success :function(data){
				if(data.resultCode=='1'){
					document.location.reload();
				}
				if(data.resultCode=='0'){
					alert("操作失败");
				}
			}
			
		});
	}
	function getitems(projectNo){
		window.location="admin/item?projectNo="+projectNo;		  
	}
	//全选
	function checkMeetRoomAll(k){
  		if($(k).is(':checked')){
  			$("input[name='meetNoCheckBox']").each(function(){	
				$(this).prop("checked",true);
			});
  		}else{
  			$("input[name='meetNoCheckBox']").each(function(){	
  				$(this).prop("checked",false);
			});
  		}	
  	}
	function batchDeleteMeetRoom(){
		if(!confirm("确定要删除该数据吗？")){
			return;
		}
  		var meetNoArrs = "";
  		$("input[name='meetNoCheckBox']:checked").each(function(){ 
  			meetNoArrs += $("#meetNo_"+$(this).val()).html()+",";
  		});
  		meetNoArrs=meetNoArrs.toString().substring(0, meetNoArrs.toString().length-1);
  		//console.log(meetNoArrs);
  		$.ajax({
  			url : "meet/meetRoom/"+meetNoArrs+"/del",
  			//contentType : 'application/json',JSON.stringify(
			type : 'POST',
			data : {
				'_method' : 'delete'
			},
			async: false,
			success : function(data) {
				if (data.resultCode == '1') {
					window.location.reload();
				} else {
					alert("删除失败");
				};
			},
			error : function() {
				alert("删除失败");
			}
		});
	}
 </script>
</html>