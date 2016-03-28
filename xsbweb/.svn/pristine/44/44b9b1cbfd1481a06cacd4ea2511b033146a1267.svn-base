 <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://ajaxanywhere.sourceforge.net/" prefix="aa"%>
<%@ taglib uri="http://java.xsb.com/xsbweb/tag/pager" prefix="page"%>
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
    <title>index</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/shade.css">
    <style>
        .mt10{margin-top:10px;}
    </style>
</head>
<body>
<!--<form action="meet/toMeetRoomList.do" name="confForm" method="post">-->
${error }
<div class="container-full">
    <%-- <div class="row">					
      
        <!--妈的，全角空格的惨痛教训 <input　type="hidden"　name="_method"　value="PUT"/> -->
        <input type="hidden" name="_method" value="PUT"/>
        <div class="col-md-12 mt10">
            <input type="text" class="form-control" id="search" value="${QueryCondition}"name="confName" placeholder="请输入查询内容">
        </div> --%>
        <div class="row">
			<div class="col-md-12 mt10">
				<form class="form-inline" action="meet/toVideoMeetRoomList.do" name="confForm" method="post">
					<span onclick="batchDeleteVideoMeetRoom();"class="btn btn-danger" >删除</span>
					<div class="input-group">
						<span class="input-group-addon">编号</span>
						 <input type="text" class="form-control" id="meetNo" name="meetNo"
							placeholder="请输入编号"
							value="${(meetVideoRoomVO.meetNo == '')?'':meetVideoRoomVO.meetNo}">
					</div>
					<div class="input-group">
						<span class="input-group-addon">名称</span> 
						<input type="text"class="form-control" id="confName" name="confName"
							placeholder="请输入名称"
							 value="${(meetVideoRoomVO.confName == '')?'':meetVideoRoomVO.confName}">
					</div>
					<input type="hidden" name="_method" value="get" />
					<input type="hidden" name="pageSize" id="pageSize" value="${meetVideoRoomVO.pageSize}" /> 
					<button type="submit" class="btn btn-warning" >查询</button>
            		<a href="meet/toAddForeVideoMeetRoom.do" role="button" class="btn btn-success">新建</a>
				</form>
			</div>
		</div>
        
        
        
        <div class="shade" id="shade" style="display:none;">
        	<!-- <form class="form-inline" action="admin/meet/updateMeetToShowStatus" name="confForm" method="post"> -->
        	<form class="form-inline" id="updateMeetToShowStatus" name="confForm" method="post">
        		<input type="hidden" name="_method" value="put"/>
		     	<div class="prompt" style="width:500px;margin-top:300px;">
		      	   	编号：<input type="text" class="input-i" name="meetNo" disabled="disabled"/><br/>
		                           <!-- 名称：<input type="text" class="input-i" name="confName" disabled="disabled"/><br/> -->
		                            名称：<input type="text" class="input-i" name="confName" /><br/>
	         	   	地址：<input type="text" placeHolder="请输入直播播放地址" name="baseUrl" class="input-i"/><br/>
		         	<input type="hidden" name="meetRole"/>
		         	<input type="hidden" name="meetNo"/>
		         	<input type="button"  value="确认" class="btn-s" onclick="addBaseUrl();"/>
		         	<!-- <input type="submit"  value="确认" class="btn-s"/> -->
		         	<input type="button"  value="取消" class="btn-c" onclick="closeShade();"/>
		     	</div>
		     </form>
		</div>
	  
    </div>
    <div class="row">
        <div class="col-md-12 mt10">
        	<aa:zone name="confListZone">
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                    	<th><input type="checkbox" name="allCheckBox"  id="allCheckBox" onclick="checkMeetVideoAll(this);" >全选</th>
                    	<th>编号</th>
                        <th>名称</th>
                        <th>上线时间</th>
                        <th>直播时间</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${meetVideoRoomVOList!=null }">
	                	<c:forEach items="${meetVideoRoomVOList}" var="mtroom" varStatus="index">
	                		<tr>
	                			<td><input type="checkbox" name="meetNoCheckBox" value="${index.index}"></td>
	                			<td id="meetNo_${index.index}">${mtroom.meetNo}</td>
		                        <td>
		                        	<c:choose>
		                        			<c:when test="${(mtroom.meetRole==''?0:mtroom.meetRole)>=128&&(mtroom.meetRole==''?0:mtroom.meetRole)<256}">
		                        				<a href="http://<%=hostName%>:8080/XSBmobile/meet/voiceMeeting.do?meetNo=${mtroom.meetNo}&type=1" target="_blank">${mtroom.confName}</a>
		                        			</c:when>
		                        			<c:when test="${(mtroom.meetRole==''?0:mtroom.meetRole)>=256&&(mtroom.meetRole==''?0:mtroom.meetRole)<512}">
		                        				${mtroom.confName}
		                        			</c:when>
		                        			<c:when test="${(mtroom.meetRole==''?0:mtroom.meetRole)>=512}">
		                        				<a href="http://<%=hostName%>:8080/XSBmobile/meet/voiceMeeting.do?meetNo=${mtroom.meetNo}&type=2" target="_blank">${mtroom.confName}</a>
		                        			</c:when>
		                        		</c:choose>
		                        </td>
		                        <td>${mtroom.onlineDate}</td>
	                         	<td>${mtroom.showDate}</td>
		                        <td>
		                        	<c:if test="${(mtroom.meetRole==''?0:mtroom.meetRole)>=128&&(mtroom.meetRole==''?0:mtroom.meetRole)<256}">
		                        		直播预告
		                        	</c:if>
		                        	<c:if test="${(mtroom.meetRole==''?0:mtroom.meetRole)>=256&&(mtroom.meetRole==''?0:mtroom.meetRole)<512}">
		                        		直播中
		                        	</c:if>
		                        	<c:if test="${(mtroom.meetRole==''?0:mtroom.meetRole)>=512}">
		                        		直播结束
		                        	</c:if>
		                        </td>
		                        <td>
		                        <c:if test="${!empty mtroom.meetNo}">
				                       <a href="admin/meet/lastEditDate.do?meetNo=${mtroom.meetNo}">置顶</a> | 
				                       <a href="javascript:void(0);" onclick="queryApplyList('${mtroom.meetNo}')">查看报名列表</a> |
			                        </c:if>
		                        <c:if test="${ (mtroom.meetRole==''?0:mtroom.meetRole)>=128&&(mtroom.meetRole==''?0:mtroom.meetRole)<256 }">
		                         	<a href="javascript:void(0);" onclick="toAddBaseUrl('${mtroom.meetNo}','${mtroom.confName}','${mtroom.meetRole}')">开始直播</a> |  
		                        </c:if>
		                         <c:if test="${(mtroom.meetRole==''?0:mtroom.meetRole)>=256&&(mtroom.meetRole==''?0:mtroom.meetRole)<512}">
		                         	<a href="javascript:void(0);" onclick="toAddBaseUrl('${mtroom.meetNo}','${mtroom.confName}','${mtroom.meetRole}')">结束直播</a> | 
		                        </c:if>
		                        <c:if test="${!empty mtroom.meetNo}">
		                         		<a href="admin/meet/toEditVideoMeetRoom.do?meetNo=${mtroom.meetNo}&&meetProjectNo=${mtroom.meetProjectNo}">编辑</a> | 
		                        		<%-- <a href="javascript:void(0);" onclick="deleteForeVideoMeetRoom('${mtroom.meetNo}')">删除</a> |  --%>
				                        <a href="javascript:void(0);" onclick="getitems('${mtroom.meetNo}')">查看item</a>
			                        </c:if>
		                    </tr>
	                	</c:forEach>
                	</c:if>
                </tbody>
            </table>
            </aa:zone>
            <page:pager pageSize="${meetVideoRoomVO.pageSize}" pageNo="${meetVideoRoomVO.pageNo}" url="meet/toVideoMeetRoomList.do" recordCount="${meetVideoRoomVO.totalRecord}"/>
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
	function queryApplyList(meetNo){
		window.location="admin/queryApplyList.do?objectNo="+meetNo;
	}
	function deleteForeVideoMeetRoom(meetNo){
		if(!confirm("确定要删除该数据吗？")){
			return;
		}
		//console.log("meetNo:"+meetNo);
		//document.forms[0].action="meet/deleteVideoMeetRoomByMeetNo.do?meetNo="+meetNo;
		//ajaxAnywhere.submitAJAX();
		window.location="meet/deleteVideoMeetRoomByMeetNo.do?meetNo="+meetNo;
	}
	
	function updateForeVideoMeetRoom(meetNo){
		alert("该功能暂未提供！");
	}
	//弹出baseUrl输入框
	function toAddBaseUrl(meetNo,confName,meetRole){
		$("#shade").css("display","block");
		$("[name='meetNo']").val(meetNo);
		$("[name='confName']").val(confName);
		$("[name='meetRole']").val(meetRole);
	}
	//新增baseUrl
	function addBaseUrl(){
		var baseUrl = $("[name='baseUrl']").val();
		if(baseUrl==null || $.trim(baseUrl)==""){
			alert("url地址不能为空");
		}
		var meetNo = $("[name='meetNo']").val();
		//document.forms[0].action="admin/meet/"+meetNo+"/updateMeetToShowStatus";
		//window.location="admin/meet/"+meetNo+"/updateMeetToShowStatus";
		//ajaxAnywhere.submitAJAX();
		$.ajax({
			cache:true,
			type : 'POST',
			url : "admin/meet/updateMeetToShowStatus",
			data : $("#updateMeetToShowStatus").serialize(),
			async : false,
			error : function (){
				alert("修改失败");
			},
			success :function(data){
				if(data.resultCode=='1'){
					closeShade();
					document.location.reload();
				}
				if(data.resultCode=='70001'){
					alert("开始直播操作失败");
					closeShade();
				}
				if(data.resultCode=='70001'){
					alert("结束直播操作失败");
					closeShade();
				}
			}
			
		});
	}
	//关闭baseUrl输入框
	function closeShade(){
		$("#shade").css("display","none");
		$("[name='meetNo']").val("");
		$("[name='confName']").val("");
		$("[name='baseUrl']").val("");
		$("[name='meetRole']").val("");
	}
	function getitems(projectNo){
		window.location="admin/item?projectNo="+projectNo;		  
	}
	//全选
	function checkMeetVideoAll(k){
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
	function batchDeleteVideoMeetRoom(){
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
  			url : "meet/videoMeetRoom/"+meetNoArrs+"/del",
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