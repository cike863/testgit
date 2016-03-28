<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>文件上传</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/js/util/jquery.form.js" type="text/javascript"></script>
  </head>
  	 <div id="str">
        
    </div>
	<div id="msg"  hidden="hidden" >
        
    </div>
<body>
<script type="text/javascript">
 $(document).ready(function() {
            createHtml($("#str"));
 });
 var index = parent.layer.getFrameIndex(window.name); 
function createHtml(obj) {
    var htmstr = [];
    htmstr.push(  "<form id='_fileForm' enctype='multipart/form-data' ");
    htmstr.push(  "<table cellspacing=\"0\" cellpadding=\"3\" style=\"margin:0 auto; margin-top:20px;\">");
    htmstr.push(  "<tr>");
    htmstr.push(  "<td class=\"tdt tdl\">请选择文件：</td>");
    htmstr.push(  "<td ><input type=\"hidden\" value=\"\" name=\"dir\" id=\"dir\"</td>");
    htmstr.push(  "<td class=\"tdt tdl\"><input id=\"loadcontrol\" type=\"file\" name=\"filepath\" id=\"filepath\" /></td>");
    htmstr.push(  "<td class=\"tdt tdl tdr\"><input type=\"button\" onclick=\"fileloadon()\" value=\"上传\"/></td>");
    htmstr.push(  "</tr>");
    htmstr.push(  "</table>");
    htmstr.push(  "</form>");
    obj.html(htmstr.join(""));
}

function fileloadon() {
    $("#msg").html(""); 
    $("#dir").val('${dir}'); 
    var options = {
    	
        url: "admin/news/uploadJson.do?dir="+'${dir}'+"&mediaNo="+'${mediaNo}',
        dataType: 'JSON',
        success: function (msg) {
        	 $("#msg").html(msg); 
        	 var data=$("#msg pre").html(); //jQuery.parseJSON(jsonstr),可以将json字符串转换成json对象  
        	 if(data){
        		 var jsonObj=JSON.parse(data); //可以将json字符串转换成json对象 
        		 if(jsonObj.error=='0'){
        			 alert("文件上传成功");
        		 }else{
        			 alert("文件上传失败");
        		 }
        		 parent.uploadIsOk(jsonObj.mediaType,jsonObj.mediaNo,jsonObj.url,jsonObj.returnPath,jsonObj.newFileName,jsonObj.format,jsonObj.size);
        		 parent.layer.close(index);
        	 }else{
        		 /*var jsonObj=JSON.parse( $("#msg").html());*/
        		alert("文件上传失败"); 
        	 }
        }
    };
    $("#_fileForm").ajaxSubmit(options);
       
}

</script>
</body>
</html>
