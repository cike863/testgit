<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>
        挂牌仪式
    </title>
    <meta name="author" content="dyly">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <link rel="apple-touch-icon-precomposed" href="###">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/indexC.css" >

</head>
<body>
<div class="wrapper clear-fix">
    <div class="left">
        <div class="left-intro">
        	<c:if test="${itemList != null}">
            <c:forEach items="${itemList}" var="i" varStatus="index">
	            <div class="tw-msg">
	                <div>
	                <h3 class="tw-title">
	                	<c:out value="${i.itemKey }"/>
					</h3>
					</div>
	                <div class="tw-time">
	               		<c:out value="${i.createDate}"/>
	                </div>
	                <div class="clear-fix"></div>
	                <div class="intro-p">
	                	${fn:replace(i.itemValue, '\\\"', '"')}
	                </div>
	            </div>
            </c:forEach>
            </c:if>
        </div>
    </div>
</div>
<script>
    window.onload = function(){
        var oIntro = document.getElementById("intro");
        var oFinance = document.getElementById("finance");
        var oCompany = document.getElementById("company");
        oIntro.onclick = function(){
            this.className = "intro-h2 intro-active";
            oFinance.className = "intro-h2";
            oCompany.className = "intro-h2";
        }
        oFinance.onclick = function(){
            this.className = "intro-h2 intro-active";
            oIntro.className = "intro-h2";
            oCompany.className = "intro-h2";
        }
        oCompany.onclick = function(){
            this.className = "intro-h2 intro-active";
            oIntro.className = "intro-h2";
            oFinance.className = "intro-h2";
        }
    }
</script>


</body>

</html>
