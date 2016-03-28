var websocket;

//初始话WebSocket
function initWebSocket(url,getUser,linkType,userName) {
//	var path =window.location.pathname.substring(1, window.location.pathname.lastIndexOf("/"));
	//ws://120.27.194.22:8080/webSocket/
	var replaceUrl="ws://120.27.194.22:8080/webSocket/";
	if (window.WebSocket) {
		
		websocket = new WebSocket(encodeURI(replaceUrl+'echo?linkType='+linkType+'&user='+getUser+'&userName='+userName));
//		websocket = new ReconnectingWebSocket(encodeURI(replaceUrl+'echo?linkType='+linkType+'&user='+getUser+'&userName='+userName));
//		var title =  document.getElementById("title");
		websocket.onopen = function() {
			//连接成功做的操作
//			title.innerHTML=userName+"--已连接"
			alert("已连接");
		}
		websocket.onerror = function() {
			//连接失败做的操作
//			title.innerHTML=userName+"连接失败"
			alert("连接失败");
		}
		websocket.onclose = function() {
			//连接断开做的操作
//			title.innerHTML=userName+"连接断开"
			alert("连接断开");
		}
		//消息接收
		websocket.onmessage = function(message) {
			var message = JSON.parse(message.data);
//			alert(message.run);
			//接收用户发送的消息
			if (message.type == 'message') {
//				var divId = message.timestamp;
//				var obj =  document.getElementById("show");
//				if(message.user==getUser){
//					var oDiv = document.createElement("div");
//					oDiv.className = "show-msg-r clear-fix";
//					oDiv.innerHTML = '<div class="r-txt">' +
//					'<div class="r-name" >'+new Date(message.timestamp).Format("yyyy-MM-dd hh:mm:ss")+"&nbsp;&nbsp;"+message.userName+'</div>' +
//					'<div class="r-msg" id='+divId+'></div>' +
//					'</div>' +
//					'<div class="r-img"><img src="/'+path+'/images/projectServices/head2.jpg" /></div>';
//
//
//					obj.appendChild(oDiv);
//					obj.scrollTop = obj.scrollHeight;
//					$("#"+divId).text(message.content);
//				}else{
//					var oDiv = document.createElement("div");
//					oDiv.className = "show-msg-l clear-fix";
//					oDiv.innerHTML = '<div class="l-txt">' +
//					'<div class="r-name" style="margin-left:70px;margin-top:20px">'+message.userName+"&nbsp;&nbsp;"+new Date(message.timestamp).Format("yyyy-MM-dd hh:mm:ss")+'</div>' +
//					'<div class="l-msg" style="margin-top:3px" id='+divId+'></div>' +
//					'</div>' +
//					'<div class="l-img" style="padding-top:15px"><img src="/'+path+'/images/projectServices/head1.jpg" /></div>';
//					obj.appendChild(oDiv);
//					obj.scrollTop = obj.scrollHeight;
//					$("#"+divId).text(message.content);
//				}

			} else if (message.type == 'user_join'||message.type == 'user_leave') {
				//用户下线
				//用户上线
//				var num =  document.getElementById("num");
//				num.innerHTML="在线用户人数："+(message.number);
				clickDiv();

			}else if(message.type=="forced_off_line"){
				alert("账号在另一处登录，你已被强行退出");
				websocket=null;
			}
		}
	}
}


//强行下线
function qzxx(user) {
	var message = {};
	if (websocket != null) {
			message={
					userId : user,//被强行下线的user
			};

			websocket.send(JSON.stringify(message));
	} else {
		alert('提示:您已经掉线，无法操作，请刷新!');
	}
}
//语音操作
function qxyy(user,type) {
	var message = {};
	if (websocket != null) {
			message={
					userId : user,//被取消语音的user
					getMessage:type //A 用户禁止语音，B 取消用户禁止语音
			};

			websocket.send(JSON.stringify(message));
	} else {
		alert('提示:您已经掉线，无法操作，请刷新!');
	}
}
//发送消息
function send(obj,linkType,user,userName) {
	var message = {};
	if (websocket != null) {
		var content=document.getElementById(obj).value;
		if (content!="") {
			message={
					user : user,
					content : content,
					timestamp : new Date().getTime(),
					type : 'message',
					userName : userName,
					linkType:linkType
			};

			websocket.send(JSON.stringify(message));
		}
	} else {
		alert('提示:您已经掉线，无法发送消息!');
	}
}
