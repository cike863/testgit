 <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://ajaxanywhere.sourceforge.net/" prefix="aa"%>
<%@ taglib uri="http://java.xsb.com/xsbweb/tag/pager" prefix="page"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/shade.css">
    <style>
        .mt10{margin-top:10px;}
    </style>
    <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
 	<script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/aa.js"></script>
 	<script  type="text/javascript" src="<%=request.getContextPath()%>/common/dtpicker/js/laydate.js" ></script>
</head>
<body>
<!--<form action="meet/toMeetRoomList.do" name="confForm" method="post">-->
${error }
<div class="container-full">
        <div class="row">
			<div class="col-md-12 mt10">
				<form class="form-inline" action="zt/womenday/admin/subsConsumes" name="womendayForm" method="post">
					<div class="input-group">
						<span class="input-group-addon">消费状态</span>
						&nbsp;&nbsp;<input type="radio" name="cnsumStatus" value="0" id="ncheck" >失败
						&nbsp;&nbsp;<input type="radio" name="cnsumStatus" value="1" id="ycheck">成功
					</div>
					<div class="input-group">
						<span class="input-group-addon">创建时间</span> 
						<input placeholder="请输入开始日期" class="laydate-icon"  style="width:130px;height:30px"name="sartQueryDate" id="t1" 
						value="${subsConsume.sartQueryDate}" 
						onClick="laydate({elem: '#t1',istime: true, format: 'YYYYMMDD'})">~
						<input placeholder="请输入结束日期" class="laydate-icon" style="width:130px;height:30px" name="endQueryDate" id="t2" 
						value="${subsConsume.endQueryDate}"
						 onClick="laydate({elem: '#t2',istime: true, format: 'YYYYMMDD'})">
					</div>
					<input type="hidden" name="_method" value="get" />
					<input type="hidden" name="pageSize" value="${subsConsume.pageSize}" /> 
					<button type="submit" class="btn btn-warning" >查询</button>
				</form>
			</div>
		</div>
    </div>
    <div class="row">
        <div class="col-md-12 mt10">
        	<aa:zone name="confListZone">
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                    	<th>编号</th>
                    	<th>微信账户</th>
                        <th>消费积分</th>
                        <th>消费状态</th>
                        <th>三方平台订单号</th>
                        <th>创建时间</th>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${subsConsumeList!=null }">
	                	<c:forEach items="${subsConsumeList}" var="subsConsume" varStatus="index">
	                		<tr>
	                			<td>${subsConsume.cnsumNo}</td>
		                        <td>${subsConsume.wxSubsId}</td>
		                        <td>${subsConsume.voteScore}</td>
	                         	<td>
	                         		<c:choose>
		                        	<c:when test="${subsConsume.cnsumStatus eq '0'}">
		                        		失败
		                        	</c:when>
		                        	<c:when test="${subsConsume.cnsumStatus eq '1'}">
		                        		成功
		                        	</c:when>
		                        </c:choose>
	                         	</td>
	                         	<td>${subsConsume.exCnsumNo}</td>
	                         	<td>${subsConsume.createdDate}</td>
		                    </tr>
	                	</c:forEach>
                	</c:if>
                </tbody>
            </table>
            </aa:zone>
            <page:pager pageSize="${subsConsume.pageSize}" pageNo="${subsConsume.pageNo}" url="zt/womenday/admin/subsConsumes" recordCount="${subsConsume .totalRecord}"/>
        </div>
    </div>
<!--</form>-->
</body>
 
 <script type="text/javascript" language="javascript">
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
	window.onload = function (){
		if(${subsConsume.cnsumStatus == '0'}){
			$("#ncheck").attr("checked","checked");
		}
		if(${subsConsume.cnsumStatus == '1'}){
			$("#ycheck").attr("checked","checked");
		}
	}
 </script>
</html>