
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>路演详情-${roaddetailcon.projName}</title>
<meta name="author" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="apple-touch-icon-precomposed" href="###">
<!-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/swiper.3.1.2.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/hall_base.css" >
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/hall_detail.css" >-->

<link rel="stylesheet" type="text/css"
	href="<%=path %>/assets/css/swiper.3.1.2.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=path %>/assets/css/hall_base.css">
<link rel="stylesheet" type="text/css"
	href="<%=path %>/assets/css/hall_detail.css">

</head>
<body>

	<!--遮罩层开始-->
	<div class="shade" id="shade">
		<div class="prompt">
			<div class="share-div">
				<div class="share-detail" id="wx">
					<img
						src="<%=request.getContextPath()%>/assets/images/hall/fx_wx.png" /><span>微信</span>
				</div>
				<div class="share-detail" id="pyq">
					<img
						src="<%=request.getContextPath()%>/assets/images/hall/fx_pyq.png" /><span>微信朋友圈</span>
				</div>
				<div class="share-detail" id="qq">
					<img
						src="<%=request.getContextPath()%>/assets/images/hall/fx_qq.png" /><span>手机QQ</span>
				</div>
				<div class="share-detail" id="wb">
					<img
						src="<%=request.getContextPath()%>/assets/images/hall/fx_wb.png" /><span>新浪微博</span>
				</div>
				<div style="clear:both"></div>
			</div>
			<div class="share-cancel" id="shareCancel">取消</div>
		</div>
	</div>
	<!--遮罩层结束-->
	<!-- header：start -->
	<div class="xsb-header-bar">
		<span class="arrow-left" id="back"></span>
		<h1>${roaddetailcon.projName}</h1>
		<img src="<%=request.getContextPath()%>/assets/images/hall/fx.png"
			class="img-right" id="share" />
	</div>
	<!-- header：end -->
	<!-- main:start -->
	<div class="mod-main">
		<!--视频信息介绍开始-->
		<div class="card">
			<div class="time_browse">
				<span class="time">上线时间：${roaddetailcon.projOnlineDate}</span> <span
					class="browse">浏览：${roaddetailcon.projPv}</span>
			</div>
			<div class="div_img">
				<img
					src="<%=request.getContextPath()%>/assets/images/upload/img_main_1.jpg"
					class="card_img" /> <img
					src="<%=request.getContextPath()%>/assets/images/hall/play.png"
					class="play_img" />
			</div>
			<div class="scroll_img">
				<!--滚动的视频star-->
				<!-- Swiper -->
				<div class="swiper-container">
					<div class="swiper-wrapper">
						<div class="swiper-slide">
							<div class="main-img-t">图片内容简介文字文字1</div>
							<img
								src="<%=request.getContextPath()%>/assets/images/upload/img_main_1.jpg"
								class="card_img" /> <img
								src="<%=request.getContextPath()%>/assets/images/hall/play.png"
								class="sm-play-img" />
						</div>
						<div class="swiper-slide">
							<div class="main-img-t">图片内容简介文字文字1</div>
							<img
								src="<%=request.getContextPath()%>/assets/images/upload/img_main_2.jpg"
								class="card_img" /> <img
								src="<%=request.getContextPath()%>/assets/images/hall/play.png"
								class="sm-play-img" />
						</div>
						<div class="swiper-slide">
							<div class="main-img-t">图片内容简介文字文字1</div>
							<img
								src="<%=request.getContextPath()%>/assets/images/upload/img_main_3.jpg"
								class="card_img" /> <img
								src="<%=request.getContextPath()%>/assets/images/hall/play.png"
								class="sm-play-img" />
						</div>
						<div class="swiper-slide">
							<div class="main-img-t">图片内容简介文字文字1</div>
							<img
								src="<%=request.getContextPath()%>/assets/images/upload/img_main_1.jpg"
								class="card_img" /> <img
								src="<%=request.getContextPath()%>/assets/images/hall/play.png"
								class="sm-play-img" />
						</div>
						<div class="swiper-slide">
							<div class="main-img-t">图片内容简介文字文字1</div>
							<img
								src="<%=request.getContextPath()%>/assets/images/upload/img_main_2.jpg"
								class="card_img" /> <img
								src="<%=request.getContextPath()%>/assets/images/hall/play.png"
								class="sm-play-img" />
						</div>
						<div class="swiper-slide">
							<div class="main-img-t">图片内容简介文字文字1</div>
							<img
								src="<%=request.getContextPath()%>/assets/images/upload/img_main_3.jpg"
								class="card_img" /> <img
								src="<%=request.getContextPath()%>/assets/images/hall/play.png"
								class="sm-play-img" />
						</div>
					</div>
					<!-- Add Navigation -->
					<!--              <div class="swiper-button-prev"></div>
                  <div class="swiper-button-next"></div>-->
				</div>
			</div>
			<!--滚动的视频end-->
			<div class="info_general">
				<ul>
					<li><span class="label">${roaddetailcon.projShowStatus=="1"?"路演中":"路演完成"}</span><span
						class="company_name">${roaddetailcon.projName}</span></li>
					<li><img
						src="<%=request.getContextPath()%>/assets/images/hall/sshq.png" /><span>实时行情：</span><span
						class="color_primary">${roaddetailcon.projDynQuotes}</span></li>
				</ul>
			</div>
			<div class="info_detail">
				<div style="float:left;">
					<canvas>${roaddetailcon.projPercentage}%</canvas>
					<!--环形进度条-->
				</div>
				<div>
					<span>辅导机构：</span><span class="color_primary">${roaddetailcon.projCoachOrg}</span>
					<br /> <span>转让方式：</span><span class="color_primary">${roaddetailcon.projAssignmentMay}</span>
					<span class="color_primary pull-right mr16">${roaddetailcon.projApplyCounts}人</span><span
						class="pull-right">报名人数：</span>
				</div>
				<div style="clear:both;"></div>
			</div>
		</div>
		<!--视频信息介绍结束-->
		<div class="detail-nav">
			<div class="swiper-container swiper-tabs">
				<div class="swiper-wrapper">
					<div class="swiper-slide swiper-tab active">公司简介</div>
					<div class="swiper-slide swiper-tab">商业计划书</div>
					<div class="swiper-slide swiper-tab">F10信息</div>
					<div class="swiper-slide swiper-tab">财务指标</div>
					<div class="swiper-slide swiper-tab">公司新闻</div>
					<div class="swiper-slide swiper-tab">公司公告</div>
					<div class="swiper-slide swiper-tab">股本状况</div>
					<div class="swiper-slide swiper-tab">十大股东</div>
					<div class="swiper-slide swiper-tab">高管人员</div>
					<div class="swiper-slide swiper-tab">历史沿革</div>
					<div class="swiper-slide swiper-tab">个股研报</div>
					<div class="swiper-slide swiper-tab">申报价格</div>
					<div class="swiper-slide swiper-tab">当日走势</div>
					<div class="swiper-slide swiper-tab">历史走势</div>
					<div class="swiper-slide swiper-tab">交易记录</div>
				</div>
			</div>
		</div>

		<!--<div class="detail-nav">
            <ul>
                <li><a href="###" class="active">公司简介</a></li>
                <li><a href="###">商业计划书</a></li>
                <li><a href="###">F10信息</a></li>
                <li><a href="###">财务指标</a></li>
            </ul>
        </div>-->
		<div class="detail-list">
			<div class="detail-item">
				<div class="item-btn">
					路演信息<span class="d-arrow-down"></span>
				</div>
				<ul class="detail-info">
					<li>简称代码：<span>${itemList.projName }(${itemList.projectNo}})</span></li>
					<li>公司全称：<span>${itemList.projCoachOrg }</span></li>
					<li>注册地址：<span>${itemList.projRegisterAdress }</span></li>
					<li>法人代表：<span>${itemList.projLegalPerson }</span></li>
					<li>公司董秘：<span>${itemList.projChSecretary }</span></li>
					<li>注册资本(万元)：<span>${itemList.projPercentage }</span></li>
					<li>行业分类：<span>${itemList.projIndustryType }</span></li>
					<li>挂牌日期：<span>${itemList.projShingleDate }</span></li>
					<li>公司网址：<span>${itemList.projCpUrl }</span></li>
					<li>转让方式：<span>${itemList.projAssignmentMay }</span></li>
					<li>主办券商：<span>${itemList.projZbTrader }</span></li>
					<li>做市商：<span>${itemList.projZsTrader }</span></li>
				</ul>
			</div>
			<div class="detail-item">
				<div class="item-btn">
					企业亮点<span class="d-arrow-down"></span>
				</div>
				<ul class="detail-info">
					<li>${itemList.projCpLightspot }</li>
				</ul>
			</div>
			<div class="detail-item">
				<div class="item-btn">
					公司简介<span class="d-arrow-down"></span>
				</div>
				<ul class="detail-info">
					<li>${itemList.projCpIntro }</li>
				</ul>
			</div>
			<div class="detail-item">
				<div class="item-btn">
					主营业务<span class="d-arrow-down"></span>
				</div>
				<ul class="detail-info">
					<li>${itemList.projMainBusiness }</li>
				</ul>
			</div>
			<div class="detail-item">
				<div class="item-btn">
					行业分析<span class="d-arrow-down"></span>
				</div>
				<ul class="detail-info">
					<li>${itemList.projIndustryAnalysis }</li>
				</ul>
			</div>
			<div class="detail-item">
				<div class="item-btn">
					行业前景<span class="d-arrow-down"></span>
				</div>
				<ul class="detail-info">
					<li>${itemList.projIndustryProspect }</li>
				</ul>
			</div>
			<div class="detail-item">
				<div class="item-btn">
					竞争优势<span class="d-arrow-down"></span>
				</div>
				<ul class="detail-info">
					<li></li>
					<!-- 竞争优势 -->
				</ul>
			</div>
			<div class="detail-item">
				<div class="item-btn">
					战略规划<span class="d-arrow-down"></span>
				</div>
				<ul class="detail-info">
					<li>${itemList.projectType }</li>
				</ul>
			</div>
			<div class="detail-item">
				<div class="item-btn">
					盈利预期<span class="d-arrow-down"></span>
				</div>
				<ul class="detail-info">
					<li>${itemList.projExpectedEarnings }</li>
				</ul>
			</div>
		</div>
		<div class="detail-apply">
			<!--秒杀报名star-->
			<div class="t-apply">
				秒杀报名：46人<span class="d-arrow-right"></span>
			</div>
			<ul>
				<c:if test="${customerList!=null}">
					<c:forEach items="${customerList}" var="enumList" varStatus="index">
						<li><img
							src="<%=request.getContextPath()%>/assets/images/upload/head5.jpg" />
							<span>${enumList.customerName }</span> <span>${enumList.customerPhoneNo }</span>
						</li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
		<!--秒杀报名end-->
	</div>
	<!-- main:end -->
	<div class="detail-bottom">
		<!--底部按钮star-->
		<a href="meet.html"><span>约谈<i
				class="icon-yuetan detail-icon"></i></span></a> <a href="apply.html"><span>秒杀报名</span></a>
	</div>
	<!--底部按钮end-->
	<!-- footer:start -->
	<!-- footer:end -->

	<!-- Swiper JS -->
	<script
		src="<%=request.getContextPath()%>/assets/js/swiper.3.1.2.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/detail.js"></script>

	<script>
    <!-- Initialize Swiper -->
    var swiper = new Swiper('.scroll_img .swiper-container', {
        pagination: '.swiper-pagination',
        slidesPerView: 3,
//        slidesPerGroup : 3,
        paginationClickable: true,
//        autoplay:5000,
        spaceBetween: 10,
//        nextButton: '.swiper-button-next',
//        prevButton: '.swiper-button-prev',
        loop: true
    });
    var swiper1 = new Swiper('.detail-nav .swiper-container', {
//        pagination: '.swiper-pagination1',
        slidesPerView: 4,
        slidesPerGroup : 4,
        paginationClickable: true,
        spaceBetween: 0
    });


  window.onload = function(){
    //获取所有的canvas标签，并根据标签内的数值进行画环操作
    var aCanvas = document.getElementsByTagName("canvas");
    for(var i=0;i<aCanvas.length;i++){
      if(isNaN(parseFloat(aCanvas[i].innerHTML))){
        aCanvas[i].innerHTML = 0; //如果标签内的数值无法转换成浮点型，则重置为0
      }
      drawCircle(aCanvas[i],parseFloat(aCanvas[i].innerHTML));
//      alert(parseFloat(aCanvas[i].innerHTML));
    }
        //为列表添加点击展开事件
      var aArrow = document.getElementsByClassName("item-btn");
      for(var i=0;i<aArrow.length;i++){
          aArrow[i].onclick = function(){
              if(this.getElementsByTagName("span")[0].className == "d-arrow-down"){
                  this.getElementsByTagName("span")[0].className = "d-arrow-up";
                  this.parentNode.getElementsByTagName("ul")[0].style.display = "block";
              }else{
                  this.getElementsByTagName("span")[0].className = "d-arrow-down";
                  this.parentNode.getElementsByTagName("ul")[0].style.display = "none";
              }
          }
      }
      //为头部返回和分享按钮添加点击事件
      var aBack = document.getElementById("back");
      var aShare = document.getElementById("share");
      var aShade = document.getElementById("shade");
      var aShadeCancel = document.getElementById("shareCancel");
      aBack.onclick = function(){
          window.history.go(-1);
      }
      aShare.onclick = function(){
          aShade.style.display = "block";
      }
      aShadeCancel.onclick = function(){
          aShade.style.display = "none";
      }
  }
</script>


</body>

</html>
