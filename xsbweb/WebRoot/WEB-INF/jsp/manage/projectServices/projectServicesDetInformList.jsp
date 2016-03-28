<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.xsb.com/xsbweb/tag/pager" prefix="page"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>
        对话框
    </title>
    <meta name="author" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <link rel="apple-touch-icon-precomposed" href="###">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/projectServices.css" >
    <script type="text/javascript" language="javascript"  src="<%=path%>/js/util/jquery-1.11.3.js"></script>
	<script  type="text/javascript" src="<%=path%>/common/dtpicker/js/laydate.js" ></script>

</head>
<body>
<div class="wrapper">
    <section>
        <h2 class="title" id="title">标题</h2>
        <div class="show" id="show">
            <c:if test="${commentlist!=null}">
	                	<c:forEach items="${commentlist}" var="comment" varStatus="index">
	                		<tr>
		                        <c:choose>
		                        	<c:when test="${comment.commenter eq customerId}">
		                        		<div class="show-msg-l clear-fix"><!--头像在左边的对话框-->
                								<div class="l-txt">
                    								<!-- <div class="l-name">别人发过来的</div> -->
                    								<div class="l-msg">${comment.commentContent}</div>
                								</div>
                							<div class="l-img"><img src="<%=path%>/images/projectServices/head1.jpg" /></div>
            						</div>
		                        	</c:when>      	
		                        	<c:otherwise>
		                        		<div class="show-msg-r clear-fix"><!--头像在右边的对话框-->
               		 						<div class="r-txt">
                    							<!-- <div class="r-name">自己发出去的</div> -->
                    							<div class="r-msg">${comment.commentContent}</div>
                							</div>
                							<div class="r-img"><img src="<%=path%>/images/projectServices/head2.jpg" /></div>
            							</div>
            							
		                        	</c:otherwise>
		                        </c:choose>
		                    </tr>
	                	</c:forEach>
                	</c:if>
        </div>
        <a href="#ST" target="_blank"></a>
        <a name="ST"></a>
        <div class="input" id="input" name ="input">
            <textarea rows="5" class="input-area" placeholder="请输入内容..." id="sendMsg"></textarea>
        </div>
        <div class="send" id="send">
            <input type="button" value="关闭" id="closeBtn"/>
            <input type="button" value="发送" id="sendBtn"/>
        </div>
    </section>
</div>
<script>
    window.onload = function () {
    	var projName=parent.getProjName();
		$("#title").html(projName+"留言详情");
        var oTitle = document.getElementById("title");
        var oShow = document.getElementById("show");
        var oInput = document.getElementById("input");
        var oSend = document.getElementById("send");
        var wHeight = parseInt(document.documentElement.clientHeight || document.body.offsetHeight);  //获取显示区域高度
        var tHeight = parseInt(oTitle.offsetHeight);
        var iHeight = parseInt(oInput.offsetHeight);
        var sHeight = parseInt(oSend.offsetHeight);
        oShow.style.height = wHeight - tHeight - iHeight - sHeight - 42 + "px";   //展示消息的区域自适应屏幕高度
    	oShow.scrollTop = oShow.scrollHeight;

        var oSendBtn = document.getElementById("sendBtn");
        var oCloseBtn = document.getElementById("closeBtn");
        var oSendMsg = document.getElementById("sendMsg");
        oSendBtn.onclick = function(){      //点击发送按钮响应事件
            if(oSendMsg.value == ""){
                alert("发送的内容不能为空！");
            } else {               
                $.ajax({
                	url:"<%=path%>/admin/comment",
                	type:"POST",
                	 async: false,
                	data:{
                		'loginMethod':'j2ee',
                		'commentTarget':'${customerId}',
                		'commentAddress':'${projectNo}',
                		'commentContent':oSendMsg.value,
                		'commentPlatform':'项目留言'
                	},
                	success:function(data){
                		if(data.resultCode=='1'){
                			document.location.reload();
                			
                		}else{
                			 alert("发送失败！");
                			 return;
                		}
                	}
                });
                createMyDiv(oShow,oSendMsg.value);
                window.location.hash="ST" ;
            }
            oSendMsg.value = "";
        };
        oCloseBtn.onclick = function(){      //点击发送按钮响应事件
        	var index = parent.layer.getFrameIndex(window.name); 
        	parent.layer.close(index);
        };
    };
    function createMyDiv(obj,content){   //自己发送的内容填充到信息展示区
        var oDiv = document.createElement("div");
        oDiv.className = "show-msg-r clear-fix";
        oDiv.innerHTML = '<div class="r-txt">' +
                            '<div class="r-name">自己发出去的</div>' +
                            '<div class="r-msg">' + content + '</div>' +
                         '</div>' +
                         '<div class="r-img"><img src="<%=path%>/images/projectServices/head2.jpg" /></div>';
        obj.appendChild(oDiv);
        obj.scrollTop = obj.scrollHeight;
    }
    function createOtherDiv(obj,content){      //别人发送的内容填充到信息展示区
        var oDiv = document.createElement("div");
        oDiv.className = "show-msg-l clear-fix";
        oDiv.innerHTML = '<div class="l-txt">' +
                '<div class="l-name">别人发过来的</div>' +
                '<div class="l-msg">' + content + '</div>' +
                '</div>' +
                '<div class="l-img"><img src="<%=path%>/images/projectServices/head1.jpg"  /></div>';
        obj.appendChild(oDiv);
        obj.scrollTop = obj.scrollHeight;
    }
    
</script>


</body>

</html>
