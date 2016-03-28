<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <title>add</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .mt50{margin-top:50px;}
        .mt20{margin-top:20px;}
    </style>
</head>
<body>
<div class="container">

    <div class="row">
        <div class="col-md-12 mt20">
            <form class="form-inline" action="meet/addMeetRoom.do" method="post">
            <table class="table table-bordered table-hover table-responsive">
                <tbody>
                  <tr>
                    <td>编号</td>
                    <td><input type="text" readonly="readonly" class="form-control"  name="meetNo" value="${meetRoomVO.meetNo }"></td>
                </tr>
                <tr>
                    <td>主持人号码</td>
                     <td><input type="text" class="form-control" name="compere">请输入正确的主持人号码</td>
                </tr>
                <tr>
                    <td>会议人数</td>
                    <td><input type="text" class="form-control"  name="maxmember">最高人数不能超过40(为空默认3人)</td>
                </tr>
                <tr>
                    <td>会议密码</td>
                     <td><input type="text" class="form-control" name="passwd">为空默认不设置密码</td>
                </tr>
                <tr>
                    <td colspan="2" class="text-center">
                        <button type="submit" class="btn btn-success">提交</button>
                    </td>
                </tr>
                </tbody>
            </table>
            </form>
        </div>
    </div>

</div>
</body>
<script type="text/javascript" language="javascript"  src="<%=request.getContextPath()%>/js/util/jquery-1.11.3.js"></script>
<script type="text/javascript">

	function chooseProject(){
		var url = "meet/toChooseProject.do";
		var DialogLocation = initShowModalDialog(500, 260);
		window.open(url,window,DialogLocation);
	}
	
	function initShowModalDialog(width,height){
		var iWidth = width;
	    var iHeight = height;
	    var iTop = (window.screen.availHeight - 20 - iHeight) / 2;
	    var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
	    return 'width=' + iWidth + 'px;height:' + iHeight + 'px;top: ' + iTop + 'px; left: ' + iLeft + 'px;center:yes;scroll:no;status:no;resizable:0;location:no';
	}
	
	function chooseProjectOK(projectNo,projName){
		$("[name='projectNo']").val(projectNo);
		$("[name='projName']").val(projName);
	}
</script>
</html>