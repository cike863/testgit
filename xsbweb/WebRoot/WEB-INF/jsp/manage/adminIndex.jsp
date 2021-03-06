<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
// String customerName=(String)request.getSession().getAttribute("customerName");
String customerName="";
Cookie[] cookies = request.getCookies();
if(cookies!=null){
	for (Cookie c :cookies) {
		if("customerName".equals(c.getName())){
			customerName = URLDecoder.decode(c.getValue(),"UTF-8");
		}
		
	}
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	  <meta charset="utf-8">
	  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	  <meta name="description" content="">
	  <meta name="author" content="">
	  <link rel="icon" href="http://v3.bootcss.com/favicon.ico">
	  <title>后台管理</title>
	  <!-- Bootstrap core CSS -->
	  <link href="<%=request.getContextPath()%>/js_css/bootstrap.css" rel="stylesheet">
	  <!-- Custom styles for this template -->
	  <link href="<%=request.getContextPath()%>/js_css/dashboard.css" rel="stylesheet">
	  <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
	  <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
	  <script src="<%=request.getContextPath()%>/js_css/ie-emulation-modes-warning.js"></script>
	  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	  <!--[if lt IE 9]>
	  <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	  <![endif]-->
	<script  type="text/javascript" src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js" ></script>
  </head>
  <body onload="startTime()">
<!--顶部标题开始-->
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="javascript:void(0);">新三板资本圈后台管理系统</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav navbar-right">
        <li style="color:#fff;height:50px;padding:15px 15px;font-size:18px;line-height:20px}"><p >当前用户:&nbsp;<%=customerName %></p></li>
        <li style="color:#fff;height:50px;padding:15px 15px;font-size:18px;line-height:20px}"><div ><!-- 当前时间: --><span id="time"></span></div></li>
        <li ><a href="<%=request.getContextPath()%>/staffLogout.do" style="color:#fff;height:50px;padding:15px 15px;font-size:18px;line-height:20px">退出</a></li>
        
      </ul>
      <!-- <form class="navbar-form navbar-right">
        <input class="form-control" placeholder="搜索框" type="text">
      </form> -->
    </div>
  </div>
</nav>
<!--顶部标题结束-->

<div class="container-fluid">
  <div class="row">
    <!--左侧菜单栏开始-->
    <div class="col-sm-3 col-md-2 sidebar">
    <input type="hidden" name="customerId" value="${customerId }"/>
    	<c:forEach items="${menuList }" var="menu">
    		<ul class="nav nav-sidebar">
    		<c:if test="${empty menu.targetLink}">
	    		<li id="li${menu.itemId}">
	    			<a href="javascript:void(0);" onclick="getSubMenuList('${menu.itemId}')" id="${menu.itemId}">${menu.itemText}</a>
    			</li>
    		</c:if>
    		<c:if test="${not empty menu.targetLink}">
    			<li id="li${menu.itemId}">
    				<a href="${menu.targetLink}" onclick="changeFatherColor(this);" target="contentFrame" >${menu.itemText}</a>
    			</li>
    		</c:if>
    		</ul>
    	</c:forEach>
    </div>
    <!--左侧菜单栏结束-->
    <!--右侧主要内容开始-->
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
      <h1 class="page-header"></h1><!--这里面写内容页的内容代码-->
      <iframe name="contentFrame" frameborder="0" height="100%" width="100%" ></iframe>
    </div>
    <!--右侧主要内容结束-->
  </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=request.getContextPath()%>/js_css/bootstrap.js"></script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<script src="<%=request.getContextPath()%>/js_css/holder.html"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<%=request.getContextPath()%>/js_css/ie10-viewport-bug-workaround.js"></script>


</body>
  
  <script type="text/javascript">
  	function getSubMenuList(itemId){
  		var uls = $("#li"+itemId).find("ul");
  		if(uls==null || uls.length==0){
  			var customerId = $("[name='customerId']").val();
  	  		if(itemId==null){
  	  			return;
  	  		}
  	  		$.ajax({
  	  			url:"admin/menu/getSubmenuList.do",
  				contentType : 'application/json',
  				type:'GET',
  				data:{
  					'itemId' : itemId,
  					'customerId' : customerId
  				},
  				success:function(data){
  					if(data!=null){
  						var htmlstr = "<ul id=\"ul"+itemId+"\" style=\"display:block;\" class=\"nav nav-sidebar\">";
  						for (var i = 0; i < data.length; i++) {
  							var submenu = data[i];
  							htmlstr += "<li id=\"li"+submenu.itemId+"\">";
  							if(submenu.targetLink==null || submenu.targetLink==""){
  								htmlstr += "<a class=\"subitems\" href=\"javascript:void(0);\" onclick=\"getSubMenuList(\'"+submenu.itemId+"\')\" id=\""+submenu.itemId+"\">"+submenu.itemText+"</a>";
  							}else{
  								htmlstr += "<a class=\"subitems\" href=\""+submenu.targetLink+"\"onclick=\"changeColor(this);\"  target=\"contentFrame\">"+submenu.itemText+"</a>";
  							}
  							htmlstr += "</li>";
  						}
  						htmlstr += "</ul>";
  						$("#"+itemId).after(htmlstr);
  					}
  				}
  	  		});
  		}
  		//单击一级菜单背景颜色修改
  		$("#"+itemId).css('background-color','#FFB6C1'); 
		$($("#"+itemId).parent("li").parent("ul").siblings()).each(function(){
  			$(this).find("li").find("a").css('background-color','#D7FFEE'); 
  		 	$($(this).find("li").find("ul")).each(function(){
  		 		$(this).find("li").find("ul").find("a").css('background-color','#D7FFEE');
  		 	});
  		});
		
		
  		var ulshow = $("#ul"+itemId).css("display");
  		if(ulshow=="none"){
  			$("#ul"+itemId).css("display","block");
  		}else{
  			$("#ul"+itemId).css("display","none");
  		}
  	}
  		function startTime(){ 
  			var today=new Date(); 
  			var strDate=(" "+today.getFullYear()+"年"+(today.getMonth()+1)+"月"+today.getDate()+"日"); 
  			var n_day=today.getDay(); 
  			switch(n_day){ 
  			case 0: 
  			{strDate=strDate+" 星期日 "}break; 
  			case 1: 
  			{strDate=strDate+" 星期一 "}break; 
  			case 2: 
  			{strDate=strDate+" 星期二 "}break; 
  			case 3: 
  			{strDate=strDate+" 星期三 "}break; 
  			case 4: 
  			{strDate=strDate+" 星期四 "}break; 
  			case 5: 
  			{strDate=strDate+" 星期五 "}break; 
  			case 6: 
  			{strDate=strDate+" 星期六 "}break; 
  			case 7: 
  			{strDate=strDate+" 星期日 "}break; 
  			} 
  			//增加时分秒 
  			// add a zero in front of numbers<10 
  			var h=today.getHours(); 
  			var m=today.getMinutes(); 
  			var s=today.getSeconds();
  			m=checkTime(m); 
  			s=checkTime(s); 
  			strDate=strDate+" "+h+":"+m+":"+s; 
  			document.getElementById('time').innerHTML=strDate; 
  			t=setTimeout('startTime()',500) 
  			} 

  			function checkTime(i){ 
  			if (i<10) {i="0" + i}
  			return i 
  			} 
  	//单击二级菜单背景颜色修改
  	function changeColor(k){
  		$(k).parent("li").siblings().children("a").css('background-color','#D7FFEE'); 
  		$($(k).parent("li").parent("ul").parent("li").parent("ul").siblings()).each(function(){
  			//$(k).css('background-color','#D7FFEE'); 
  			//$(this).find("li").find("ul").find("a").css('background-color','#D7FFEE');
  			//console.log("111");
  			$(this).find("li").find("a").css('background-color','#D7FFEE'); 
  		 	$($(this).find("li").find("ul")).each(function(){
  		 		$(this).find("li").find("ul").find("a").css('background-color','#D7FFEE');

  		 	});
  		});
  		$(k).css('background-color','#FFE4B5');
  		$(k).parent("li").parent("ul").parent("li").find("a").first().css('background-color','#FFB6C1'); 
  	}
  	function changeFatherColor(k){
  	//单击一级菜单背景颜色修改
  		//console.log($(k).css('background-color'));
  		$(k).css('background-color','#FFB6C1'); 
  		//console.log($(k).css('background-color'));
		$($(k).parent("li").parent("ul").siblings()).each(function(){
  			$(this).find("li").find("a").css('background-color','#D7FFEE'); 
  		 	$($(this).find("li").find("ul")).each(function(){
  		 		$(this).find("li").find("ul").find("a").css('background-color','#D7FFEE');
  		 	});
  		});
  	}
  </script>
</html>
