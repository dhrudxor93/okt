<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>Spring - managed by GIT</title>
</head>
<style>
a {
	text-decoration: none;
}
input {
	padding: 3px;
	width: 300px;
}
</style>
<body>
	<div align="center">
		<div style="width: 980px;">
			<div>
				<h1>Spring Project</h1>
				<small>- ${ment } -</small>
			</div>
			<hr />
			<div align="right" style="padding-right: 20px;">
				<span
					style="font-weight: bold">Sign in</span> <span>or</span> 
				<a href="<%=request.getContextPath() %>/regist"><span>Sign up</span></a>
			</div>
			<hr />
			<div style="font-size: 17pt; margin-top: 50px;">
				LOGIN<br /> <span style="font-size: 11pt;">로그인 페이지입니다.</span>
			</div>
			<div>
				<c:if test="${msg != null }">
					<span style="color:red">${msg }</span>
				</c:if>
				<form action="<%=request.getContextPath() %>/login"
					method="post" style="width: 330px; text-align: left;"
					autocomplete="off">
					<div style="margin-top: 20px;">
						<b>ID(*)</b> <small id="checkrst"></small><br /> <input
							type="text" name="id" id="id" pattern="[a-zA-Z]+">
					</div>
					<div style="margin-top: 20px;">
						<b>PASS(*)</b><br /> <input type="password" name="password">
					</div>
					<div style="margin-top: 20px;">
						<button id="sbt" type="submit" style="width: 100%; height: 30px;">로 그 인</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>