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
    <title>项目行业列表</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>项目数据字典页面</title>
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
    <div class="row">
    	<div class="col-md-12 mt10">
    		<form class="form-inline" action="enum/searchEnumManage.do" name="confForm" method="get">
      			<span onclick="batchDeleteProjectEnum();"class="btn btn-danger">删除</span>
        		<input type="hidden" name="_method" value="get"/>
            	<a href="enum/toAddData.do?enumFullName=industry" role="button" class="btn btn-success">新建</a>
	  		</form>
    	</div>
    </div>
    <div class="row">
        <div class="col-md-12 mt10"> 
        	<form action="enum/updateProjectEnum.do"  name="confForm" method="post">
        	<!-- <input type="hidden" name="_method" value="post"/> -->
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                    	<th><input type="checkbox" name="allCheckBox"  id="allCheckBox" onclick="checkProjectEnumAll(this);" >全选</th>
                    	<!-- <th>序号</th> -->
                    	<!-- <th>字段完整名称</th> -->
                        <!-- <th>枚举键</th> -->
                        <th>行业</th>
                        <!-- <th>隶属父级枚举键</th> -->
                        <th>修改顺序</th>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${enumList !=null }">
                		<c:forEach items="${enumList }" var="emList" varStatus="index">
                			<tr>
                				<td><input type="checkbox" name="projectEnumDescCheckBox" value="${index.index}"></td>
                				<%-- <td>${index.index+1 }</td> --%>
                				<%-- <td>${emList.enumFullName }</td> --%>
                				<%-- <td>${emList.enumCode }</td> --%>
                				<td id="enumDesc_${index.index}">${emList.enumDesc }</td>
                				<%-- <td>${emList.enumGroupCode }</td> --%>
                			 	<td> 
			                        <input type="text" name="enumList[${index.index }].enumDescCn" value='${emList.enumDescCn }'>
		                        	<input type="hidden" name="enumList[${index.index }].enumCode" value='${emList.enumCode }'>
		                        </td> 
                			</tr>
                		</c:forEach>
                	</c:if>
                	<tr>
                    <td colspan="5" class="text-center">
                        <button type="submit" class="btn btn-success">提交</button>
                        <!-- <button type="button" class="btn btn-danger" onclick="history.go(-1)">返回</button> -->
                    </td>
                </tr>
                </tbody>
            </table>
           </form>
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
	};
	function upprojectEnum(index){
		//itemValue=$("#itemValue"+index).html();
		var enumDesc1=$("#enumDesc_"+index).html();//更新前的enumDesc
		//console.log("enumDesc1:"+enumDesc1);
		var order = $("#industry_"+index).val();		
		//console.log("order:"+order);
		var enumDesc2 = $("#enumDesc_"+order).html();//更新后的enumDesc
		//console.log("enumDesc1:"+enumDesc1+",order:"+order+",enumDesc2:"+enumDesc2+",index:"+index);
			$.ajax({
				/* type : 'post', */
				/* url : "enum/updateProjectEnum.do", */
				url : "enum/updateProjectEnum.do?enumDesc1="+enumDesc1+"&code2="+index+"&enumDesc2="+enumDesc2+"&code1="+order,
				contentType : 'application/json',
				/* data : JSON.stringify({
					'enumDesc1' : enumDesc1,
					'enumDesc2' : enumDesc2,
					'code1' : index,
					'code2' : order
				}), */
				cache : false,
				dataType : 'json',
				async : false,
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
		}
	//全选
	function checkProjectEnumAll(k){
  		if($(k).is(':checked')){
  			$("input[name='projectEnumDescCheckBox']").each(function(){	
				$(this).prop("checked",true);
			});
  		}else{
  			$("input[name='projectEnumDescCheckBox']").each(function(){	
  				$(this).prop("checked",false);
			});
  		}	
  	}
	function batchDeleteProjectEnum(){
		if(!confirm("确定要删除该数据吗？")){
			return;
		}
  		var enumDescArrs = "";
  		$("input[name='projectEnumDescCheckBox']:checked").each(function(){ 
  			enumDescArrs += $("#enumDesc_"+$(this).val()).html()+",";
  		});
  		enumDescArrs=enumDescArrs.toString().substring(0, enumDescArrs.toString().length-1);
  		//console.log(enumDescArrs);
  		$.ajax({
  			url : "enum/projectEnumDesc/"+enumDescArrs+"/del",
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