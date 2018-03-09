<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
				<a href="/login"><span>Sign in</span></a> <span>or</span> <span
					style="font-weight: bold">Sign up</span>
			</div>
			<hr />
			<div style="font-size: 17pt; margin-top: 50px;">
				JOIN US<br /> <span style="font-size: 11pt;">회원가입시 모든 요소는
					필수기입 항목입니다.</span>
			</div>
			<div>
				<form action="<%=request.getContextPath() %>/result"
					method="post" style="width: 330px; text-align: left;"
					autocomplete="off">
					<div style="margin-top: 20px;">
						<b>ID(*)</b> <small id="checkrst"></small><br /> <input
							type="text" name="id" id="id" pattern="[a-zA-Z]+">
					</div>
					<div style="margin-top: 20px;">
						<b>PASS(*)</b><br /> <input type="password" name="pass">
					</div>
					<div style="margin-top: 20px;">
						<b>EMAIL(*)</b><br /> <input type="email" name="email">
					</div>
					<div style="margin-top: 20px;">
						<button id="sbt" type="submit" style="width: 100%; height: 30px;">가
							입 신 청</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>