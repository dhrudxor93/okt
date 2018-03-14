<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>친구찾기</title>
</head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
a {
	text-decoration: none;
}
</style>
<body>
	<div align="center">
		<div style="width: 980px;">
			<div>
				<h1>Spring Project</h1>
				<small>- ${ment } -</small>
			</div>
			<div class="alert alert-warning alert-dismissible" id="warn1" style="display: none">
				<a href="javascript:location.reload();" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>경고!</strong> 다른 윈도우 혹은 탭에서 로그인되었습니다. F5를 눌러주세요.
			</div>
			<div class="alert alert-warning alert-dismissible" id="warn2" style="display: none">
				<a href="javascript:location.reload();" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>경고!</strong> 다른 윈도우 혹은 탭에서 로그아웃되었습니다. F5를 눌러주세요.
			</div>
			<hr />
			<c:if test="${logon!=null }">
				<div align="center">
					<input type="text" name="friend" id=fiend/>
				</div>
				<div align="right" style="padding-right: 20px;">
					${logon } 님 어서오세요<br /> <a
						href="<%=request.getContextPath()%>/logout">로그아웃</a> / <b>친구목록</b>
				</div>
			</c:if>
			<c:if test="${empty logon }">
				<div align="right" style="padding-right: 20px;">
					<a href="<%=request.getContextPath()%>/log"><span>Sign
							in</span></a> <span>or</span> <a href="<%=request.getContextPath()%>/regist"><span>Sign
							up</span></a>
				</div>
			</c:if>
			<hr />
		</div>
		<div>
			<div class="alert alert-info">
				<b>현재 접속자 수 : <span id="cnt"></span></b>/ <strong>서버알림</strong><span
					id="info">.</span>
			</div>
		</div>
	</div>
	<div>
		${friend };
	</div>
	<script><%-- WebSocket을 하기 위해선 script처리가 필요하다.--%>
		
	<%-- 내 ip주소를 적고 / app-config(Spring 설정파일)에서 설정한 path경로를 적어두면 된다.--%>
		/* 	var ws = new WebSocket("ws://192.168.10.82/chap05/handle"); */
		var ws = new WebSocket(
				"ws://${pageContext.request.serverName }/chap05/handle");
		var ac = new WebSocket(
				"ws://${pageContext.request.serverName}/chap05/alert");
	<%-- ${pageContext.request.serverName}으로 설정해두면 어디의 ip주소이든 일단 접속가능하다.--%>
		// 연결이 됬을 때..
		ws.onopen = function() {
			console.log("opened");
			console.log(this);
		}
		ac.onopen = function() {
			console.log(this);
		}
		ac.onmessage = function(rst) {
			if(rst.data == "로그인"){
				$("#warn1").show();
			}
			if(rst.data == "로그아웃"){
				$("#warn2").show();
			}
			console.log(rst);
		}
		// 메세지가 들어올 때
		ws.onmessage = function(resp) {
			var obj = JSON.parse(resp.data);
			$("#cnt").html(obj.cnt);
			$("#info").html(obj.info);
		}
		// 연결이 끊길 때
		ws.onclose = function() {
			window.alert("연결이 해제되었습니다.")
		}
	</script>
</body>
</html>