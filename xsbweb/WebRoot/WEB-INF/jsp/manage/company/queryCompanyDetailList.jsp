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
    <title>公司高管</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>index</title>
    <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .mt10{margin-top:10px;}
    </style>
    <script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
 	<script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/aa.js"></script>
</head>
<body>
<script type="text/javascript">
	var type='${type}';
	window.onload= function(){
		//console.log(${type eq 'drctr' });
		//console.log("type"+type);
		var browserName=navigator.userAgent.toLowerCase();
		console.log(browserName);
		if(type == 'drctr' ){
			drctr("#drctrs");	
		}
		if(type == 'equity' ){
			equity("#equitys");
		}
		if(type =='holder' ){
			holder("#holders");			
		}
		if(type == 'finance' ){
			finance("#finances");
		}
	}; 
	//高管人员开始
	function drctr(k){
		$("#drctr").show();
		$("#equity").hide();
		$("#holder").hide();
		$("#finance").hide();
		$(k).parent("div").parent("div").find("button").css('background-color','#FFFFFF');
		$(k).css('background-color','#FFB6C1'); 
		$.ajax({
			url: "admin/company/getCompanyDrctrByStockId.do?stockId="+${stockId},
			async: false,
			success:function(data){
				//console.log(data.companyDrctrList);
				if(data.companyDrctrList){
						$("#drctr tbody").empty();  //这是body  里面的一个table
						$.each(data.companyDrctrList,function(i,n){
							//console.log(n.stockId);//测试 这是我得到的一个数组 是可以得到的
						var tTr="<tr>"
						+"	<td><input type=\"checkbox\" name=\"dsmNameCheckBox\" value="+i+"></td>"
						+"<td id=\"drctr_"+i+"\">"+n.dsmName+"</td>"
						+"<td>"+(n.dsmTitle == null ?'无数据':n.dsmTitle )+"</td>"
						+"<td>"+(n.dsmType =='d'?'董事':( n.dsmType =='s'?'监事':'高管'))+"</td>"
						+"<td>"+(n.loc == null ?'无数据':n.loc )+"</td>"
						+"<td>"+(n.holdAmount == null ?'无数据':n.holdAmount )+"</td>"
						+"<td><a href=\"admin/company/toEditCompanyDrctrByDsmName.do?stockId="+${stockId}+"&dsmName="+n.dsmName+"\">编辑</a></td>"  	
						+"</tr>";
						//console.log(tTr);
						$("#drctr tbody").append(tTr);
						 });
					}
			}
		});
	}
	//批量删除
	function batchDeleteDrctr(){
		//勾选的栏目对应的code值相加得出role值
  		//var dsmNames = "[";
		if(!confirm("确定要删除该数据吗？")){
			return;
		}
  		var dsmNames = "";
  		$("input[name='dsmNameCheckBox']:checked").each(function(){ 
  			//console.log($("#drctr_"+$(this).val()).html());
  			dsmNames += $("#drctr_"+$(this).val()).html()+",";
  		});
  		//console.log(dsmNames);
  		dsmNames=dsmNames.toString().substring(0, dsmNames.toString().length-1);
  		//dsmNames+="]";
  		$.ajax({
  			url : "admin/company/batchDeleteDrctr",
  			//contentType : 'application/json',JSON.stringify(
			type : 'POST',
			data : {
				'stockId' : '${stockId}',	
				'dsmNames' : dsmNames
			},
			async: false,
			success : function(data) {
				if (data.resultCode == '1') {
					drctr("#drctrs");	
				} else {
					alert("删除失败");
				};
			},
			error : function() {
				alert("删除失败");
			}
		});
	}
	//全选
	function checkDrctrAll(k){
  		if($(k).is(':checked')){
  			$("input[name='dsmNameCheckBox']").each(function(){	
				$(this).prop("checked",true);
			});
  		}else{
  			$("input[name='dsmNameCheckBox']").each(function(){	
  				$(this).prop("checked",false);
			});
  		}	
  	}
	//高管人员结束
	
	//股本状况开始
	function equity(k){
		$("#drctr").hide();
		$("#equity").show();
		$("#holder").hide();
		$("#finance").hide();
		$(k).parent("div").parent("div").find("button").css('background-color','#FFFFFF');
		$(k).css('background-color','#FFB6C1'); 
		$.ajax({
			url: "admin/company/getCompanyEquityByStockId.do?stockId="+${stockId},
			async: false,
			success:function(data){
				//console.log(data.companyEquityList);
				if(data.companyEquityList){
						$("#equity tbody").empty();  //这是body  里面的一个table
						$.each(data.companyEquityList,function(i,n){
							//console.log(n.stockId);//测试 这是我得到的一个数组 是可以得到的
						var tTr="<tr>"
						+"	<td><input type=\"checkbox\" name=\"reportDateCheckBox\" value="+i+"></td>"
						+"<td id=\"equity_"+i+"\">"+n.reportDate+"</td>"
						+"<td>"+(n.totalEquity == null ?'无数据':n.totalEquity )+"</td>"
						+"<td>"+(n.exchngATotalEquity == null ?'无数据':n.exchngATotalEquity )+"</td>"
						+"<td>"+(n.exchngACircuEquity == null ?'无数据':n.exchngACircuEquity )+"</td>"
						+"<td>"+(n.exchngALimitEquity == null ?'无数据':n.exchngALimitEquity )+"</td>"
						+"<td>"+(n.equityChangeReason == null ?'无数据':n.equityChangeReason )+"</td>"
						+"<td><a href=\"admin/company/toEditCompanyEquity.do?stockId="+${stockId}+"&reportDate="+n.reportDate+"\">编辑</a></td>"  	
						+"</tr>";
						//console.log(tTr);
						$("#equity tbody").append(tTr);
						 });
					}
			}
		});
	}
	//批量删除
	function batchDeleteEquity(){
		if(!confirm("确定要删除该数据吗？")){
			return;
		}
  		var reportDates = "";
  		$("input[name='reportDateCheckBox']:checked").each(function(){ 
  			//console.log($("#equity_"+$(this).val()).html());
  			reportDates += $("#equity_"+$(this).val()).html()+",";
  		});
  		reportDates=reportDates.toString().substring(0, reportDates.toString().length-1);
  		$.ajax({
  			url : "admin/company/batchDeleteEquity",
  			//contentType : 'application/json',JSON.stringify(
			type : 'POST',
			data : {
				'stockId' : '${stockId}',	
				'reportDates' : reportDates
			},
			async: false,
			success : function(data) {
				if (data.resultCode == '1') {
					equity("#equitys");
				} else {
					alert("删除失败");
				};
			},
			error : function() {
				alert("删除失败");
			}
		});
	}
	//全选
	function checkEquityAll(k){
  		if($(k).is(':checked')){
  			$("input[name='reportDateCheckBox']").each(function(){	
				$(this).prop("checked",true);
			});
  		}else{
  			$("input[name='reportDateCheckBox']").each(function(){	
  				$(this).prop("checked",false);
			});
  		}	
  	}
	//公司股本结束
	
	//十大股东开始
	function holder(k){
		$("#drctr").hide();
		$("#equity").hide();
		$("#holder").show();
		$("#finance").hide();
		$(k).parent("div").parent("div").find("button").css('background-color','#FFFFFF');
		$(k).css('background-color','#FFB6C1'); 
		$.ajax({
			url: "admin/company/getCompanyHolderByStockId.do?stockId="+${stockId},
			async: false,
			success:function(data){
				if(data.companyHolderList){
						$("#holder tbody").empty();  //这是body  里面的一个table
						$.each(data.companyHolderList,function(i,n){
							//console.log(n.stockId);//测试 这是我得到的一个数组 是可以得到的
						var tTr="<tr>"
						+"	<td><input type=\"checkbox\" name=\"holderNameCheckBox\" value="+i+"></td>"
						+"<td id=\"holder_"+i+"\">"+n.holderName+"</td>"
						+"<td>"+(n.holdType == null ?'无数据':n.holdType )+"</td>"
						+"<td>"+(n.holdAmount == null ?'无数据':n.holdAmount )+"</td>"
						+"<td>"+(+(n.holdPercent == null ?'0':n.holdPercent )).toFixed(3)+"%"+"</td>"
						+"<td>"+(n.chngHoldAmount == null ?'无数据':n.chngHoldAmount )+"</td>"
						+"<td>"+(n.chngHoldPercent == null ?'无数据':n.chngHoldPercent) +"</td>"
						+"<td><a href=\"admin/company/toEditCompanyHolder.do?stockId="+${stockId}+"&holderName="+n.holderName+"\">编辑</a></td>" 
						+"</tr>";
						$("#holder tbody").append(tTr);
						 });
					}
			}
		});
	}
	//批量删除
	function batchDeleteHolder(){
		if(!confirm("确定要删除该数据吗？")){
			return;
		}
  		var holderNames = "";
  		$("input[name='holderNameCheckBox']:checked").each(function(){ 
  			//console.log($("#equity_"+$(this).val()).html());
  			holderNames += $("#holder_"+$(this).val()).html()+",";
  		});
  		holderNames=holderNames.toString().substring(0, holderNames.toString().length-1);
  		$.ajax({
  			url : "admin/company/batchDeleteHolder",
  			//contentType : 'application/json',JSON.stringify(
			type : 'POST',
			data : {
				'stockId' : '${stockId}',	
				'holderNames' : holderNames
			},
			async: false,
			success : function(data) {
				if (data.resultCode == '1') {
					holder("#holders");
				} else {
					alert("删除失败");
				};
			},
			error : function() {
				alert("删除失败");
			}
		});
	}
	//全选
	function checkHolderAll(k){
  		if($(k).is(':checked')){
  			$("input[name='holderNameCheckBox']").each(function(){	
				$(this).prop("checked",true);
			});
  		}else{
  			$("input[name='holderNameCheckBox']").each(function(){	
  				$(this).prop("checked",false);
			});
  		}	
  	}
	//十大股东结束
	//财务分析开始
	function finance(k){
		type='finance';
		$("#drctr").hide();
		$("#equity").hide();
		$("#holder").hide();
		$("#finance").show();
		$(k).parent("div").parent("div").find("button").css('background-color','#FFFFFF');
		$(k).css('background-color','#FFB6C1'); 
		$.ajax({
			url: "admin/company/getCompanyFinanceByStockId.do?stockId="+${stockId},
			async: false,
			success:function(data){
				//console.log(data.companyFinanceList);
				if(data.companyFinanceList){
						$("#finance tbody").empty();  //这是body  里面的一个table
						$.each(data.companyFinanceList,function(i,n){
							//console.log(n.stockId);//测试 这是我得到的一个数组 是可以得到的
						var tTr="<tr>"
						+"	<td><input type=\"checkbox\" name=\"attrNameCheckBox\" value="+i+"></td>"
						+"<td id=\"finance_"+i+"\">"+n.attrName+"</td>"
						+"<td>"+(n.attrValue == null ?'无数据':n.attrValue )+"</td>"
						+"<td>"+(n.reportDate == null ?'无数据':n.reportDate )+"</td>"
						+"<td><a href=\"admin/company/toEditCompanyFinance.do?stockId="+${stockId}+"&attrName="+n.attrName+"\">编辑</a></td>" 
						+"</tr>";
						//console.log(tTr);
						$("#finance tbody").append(tTr);
						 });
					}
			}
		});
	}
	//批量删除
	function batchDeleteFinance(){
		if(!confirm("确定要删除该数据吗？")){
			return;
		}
  		var attrNames = "";
  		$("input[name='attrNameCheckBox']:checked").each(function(){ 
  			//console.log($("#equity_"+$(this).val()).html());
  			attrNames += $("#finance_"+$(this).val()).html()+",";
  		});
  		attrNames=attrNames.toString().substring(0, attrNames.toString().length-1);
  		$.ajax({
  			url : "admin/company/batchDeleteFinance",
  			//contentType : 'application/json',JSON.stringify(
			type : 'POST',
			async: false,
			data : {
				'stockId' : '${stockId}',	
				'attrNames' : attrNames
			},
			success : function(data) {
				if (data.resultCode == '1') {
					finance("#finances");
				} else {
					alert("删除失败");
				};
			},
			error : function() {
				alert("删除失败");
			}
		});
	}
	//全选
	function checkFinanceAll(k){
  		if($(k).is(':checked')){
  			$("input[name='attrNameCheckBox']").each(function(){	
				$(this).prop("checked",true);
			});
  		}else{
  			$("input[name='attrNameCheckBox']").each(function(){	
  				$(this).prop("checked",false);
			});
  		}	
  	}

</script>
${error }
<div class="container-full">
	
    <div class="row">
        <div class="col-md-12 mt10">
        	<div class="form-inline">
        		<div class="input-group">
                	<button class="input-group-addon"  style="border:1px solid  #ccc;" onclick="drctr(this);" id="drctrs">高管人员</button>
                </div>
                <div class="input-group">
                	<button class="input-group-addon" style="border:1px solid  #ccc;" onclick="equity(this);" id="equitys">股本状况</button>
                </div>
                <div class="input-group">
                	<button class="input-group-addon" style="border:1px solid  #ccc;" onclick="holder(this);" id="holders">十大股东</button>
                </div>
                <div class="input-group">
                	<button class="input-group-addon" style="border:1px solid  #ccc;" onclick="finance(this);" id="finances">财务分析</button>
                </div>
        	</div>
        </div>
    </div>
		<div class="row" id="drctr" style="display:true;">
			<!-- 公司高管开始 -->
			<div class="row">
				<div class="col-md-12 mt10">
						<a href="admin/company/toAddCompanyDrctr.do?stockId=${stockId }"
							role="button" class="btn btn-success"  style="margin-left:30px;">新建</a>
							<button onclick="batchDeleteDrctr();"class="btn btn-danger">删除</button>
				</div>
			</div>
			<div class="col-md-12 mt10">
				<aa:zone name="confListZone1">
					<table class="table table-bordered table-hover table-responsive">
						<thead>
							<tr>
								<th><input type="checkbox" name="allCheckBox"  id="allCheckBox" onclick="checkDrctrAll(this);" >全选</th>
								<th>姓名</th>
								<th>职务</th>
								<th>类型</th>
								<th>排位</th>
								<th>所持股份</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="drctr_body">
							<%-- <c:if test="${companyDrctrList!=null}">
								<c:forEach items="${companyDrctrList}" var="companyDrctr"
									varStatus="index">
									<tr>
										<td>${companyDrctr.dsmName}</td>
										<td>${companyDrctr.dsmTitle}</td>
										<td>${companyDrctr.dsmType}</td>
										<td>${companyDrctr.loc}</td>
										<td>${companyDrctr.holdAmount}</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody> --%>
					</table>
				</aa:zone>
				<%-- 
            <page:pager pageSize="${companyDrctr.pageSize eq '0' ?1:companyDrctr.pageSize}" pageNo="${companyDrctr.pageNo}" url="admin/company"  recordCount="${companyDrctr.totalRecord}"/>--%>
			</div>
		</div>
		<!-- 公司高管结束 -->
    <div class="row" id="equity"  style="display:none;"> 
    	<!-- 股本概括开始 -->
    	<div class="row">
				<div class="col-md-12 mt10">
						<a href="admin/company/toAddCompanyEquity.do?stockId=${stockId }"
							role="button" class="btn btn-success" style="margin-left:30px;">新建</a>
							<button onclick="batchDeleteEquity();"class="btn btn-danger">删除</button>
				</div>
			</div>
        <div class="col-md-12 mt10">
        	<aa:zone name="confListZone">
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                   		<th><input type="checkbox" name="allCheckBox"  id="allCheckBox" onclick="checkEquityAll(this);" >全选</th>
                    	<th>数据日期</th>
                    	<th>总股本</th>
                    	<th>a股全部</th>
                    	<th>a股流通</th>
                    	<th>a股受限</th>
                        <th>变动原因</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody id="equity_body">
                	<%-- <c:if test="${companyEquityList!=null}">
	                	<c:forEach items="${companyEquityList}" var="companyEquity" varStatus="index">
	                		<tr>
	                			<td>${companyEquity.reportDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	                			<td>${companyEquity.totalEquity}</td>
		                        <td>${companyEquity.exchngATotalEquity}</td>
		                        <td>${companyEquity.exchngACircuEquity}</td>
	                			<td>${companyEquity.exchngALimitEquity}</td>
		                        <td>${companyEquity.equityChangeReason}</td>
		                    </tr>
	                	</c:forEach>
                	</c:if> --%>
                </tbody>
            </table>
            </aa:zone>
            <%-- 
            <page:pager pageSize="${company.pageSize eq '0' ?1:company.pageSize}" pageNo="${company.pageNo}" url="admin/company"  recordCount="${company.totalRecord}"/>
        --%></div>
    </div><!-- 股本概括结束 -->
    <div class="row" id="holder" style="display:none;"><!-- 十大股东开始 -->
    	<div class="row">
				<div class="col-md-12 mt10">
						<a href="admin/company/toAddCompanyHolder.do?stockId=${stockId }"
							role="button" class="btn btn-success" style="margin-left:30px;">新建</a>
							<button onclick="batchDeleteHolder();"class="btn btn-danger">删除</button>
				</div>
		</div>
        <div class="col-md-12 mt10">
        	<aa:zone name="confListZone">
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                    	<th><input type="checkbox" name="allCheckBox"  id="allCheckBox" onclick="checkHolderAll(this);" >全选</th>
                    	<th>股东名称</th>
                    	<th>持股性质</th>
                    	<th>持股数(万股)</th>
                    	<th>持股比例（%)</th>
                        <th>持股数变化(万股)</th>
                        <th>持股比例变化（%)</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody  id="holder_body">
                	<%-- <c:if test="${companyHolderList!=null}">
	                	<c:forEach items="${companyHolderList}" var="companyHolder" varStatus="index">
	                		<tr>
	                			<td>${companyHolder.holderName}</td>
	                			<td>${companyHolder.holdType}</td>
		                        <td>${companyHolder.holdAmount}</td>
		                        <td>${companyHolder.holdPercent}</td>
	                			<td>${companyHolder.chngHoldAmount}</td>
		                        <td>${companyHolder.chngHoldPercent}</td>
		                    </tr>
	                	</c:forEach>
                	</c:if> --%>
                </tbody>
            </table>
            </aa:zone>
            <%--  
            <page:pager pageSize="${company.pageSize eq '0' ?1:company.pageSize}" pageNo="${company.pageNo}" url="admin/company"  recordCount="${company.totalRecord}"/>
        --%></div>
    </div><!-- 十大股东结束 -->
    <div class="row" id="finance" style="display:none;"><!-- 财务分析开始 -->
    	<div class="row">
				<div class="col-md-12 mt10">
						<a href="admin/company/toAddCompanyFinance.do?stockId=${stockId }"
							role="button" class="btn btn-success" style="margin-left:30px;">新建</a>
							<button onclick="batchDeleteFinance();"class="btn btn-danger">删除</button>
				</div>
		</div>
        <div class="col-md-12 mt10">
        	<aa:zone name="confListZone">
            <table class="table table-bordered table-hover table-responsive">
                <thead>
                    <tr>
                    	<th><input type="checkbox" name="allCheckBox"  id="allCheckBox" onclick="checkFinanceAll(this);" >全选</th>
                    	<th>项目</th>
                    	<th>数据</th>
                    	<th>时间</th>
                    	<th>操作</th>
                    </tr>
                </thead >
                <tbody  id="finance_body">
                	<%-- <c:if test="${companyFinanceList!=null}">
	                	<c:forEach items="${companyFinanceList}" var="companyFinance" varStatus="index">
	                		<tr>
	                			<td>${companyFinance.attrName}</td>
	                			<td>${companyFinance.attrValue}</td>
		                        <td>${companyFinance.reportDate}</td>
		                    </tr>
	                	</c:forEach>
                	</c:if> --%>
                </tbody>
            </table>
            </aa:zone>
            <%-- 
            <page:pager pageSize="${company.pageSize eq '0' ?1:company.pageSize}" pageNo="${company.pageNo}" url="admin/company"  recordCount="${company.totalRecord}"/>
         --%></div>
    </div><!-- 财务分析结束 -->
    </div>
<!--</form>-->
</body>
</html>