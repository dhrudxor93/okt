<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h4>John's Blog</h4>
<ul class="nav nav-pills nav-stacked">  
	<li class=""><a href="<%=request.getContextPath()%>/">Home</a></li>
	<li class=""><a href="<%=request.getContextPath()%>/regist">SIGN UP</a></li>
	<li><a href="#section3">Family</a></li>
	<li><a href="#section3">Photos</a></li>
</ul>
<br>
<div class="input-group">
	<input type="text" class="form-control" placeholder="Search Blog..">
	<span class="input-group-btn">
		<button class="btn btn-default" type="button">
			<span class="glyphicon glyphicon-search"></span>
		</button>
	</span>
</div>