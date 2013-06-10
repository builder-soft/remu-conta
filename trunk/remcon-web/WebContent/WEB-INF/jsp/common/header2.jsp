<%@page import="cl.buildersoft.framework.beans.User"%>
<%@page import="cl.buildersoft.framework.beans.Domain"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Buildersoft &radic;</title>
<!-- Mobile viewport optimized -->
<meta name="viewport" content="width=device-width">
<!-- CSS -->
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css?<%=Math.random() %>" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css?<%=Math.random() %>" rel="stylesheet"
	type="text/css" />
<script>
	var contextPath = "${pageContext.request.contextPath}";
	var speed = "fast";// "slow";
</script>
</head>
<body>
	<div class="container-fluid">

		<div class="row-fluid">
			<div class="span5">
				<img src="${pageContext.request.contextPath}/img/logo.jpg" />
			</div>

			<div class="span3">
				Dominio:<strong><%=getDomainName(session)%></strong>
			</div>
			<div class="span1">|</div>
			<div class="span3">
				Usuario:<strong><%=getUserName(session)%> - <%=getUserMail(session)%></strong>
			</div>
		</div>
	</div>

	<%!private String getDomainName(HttpSession session) {
		return ((Domain) session.getAttribute("Domain")).getAlias();
	}

	private String getUserName(HttpSession session) {
		return ((User) session.getAttribute("User")).getName();
	}

	private String getUserMail(HttpSession session) {
		return ((User) session.getAttribute("User")).getMail();
	}%>