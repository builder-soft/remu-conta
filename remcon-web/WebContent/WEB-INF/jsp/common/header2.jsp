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
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container-fluid">

		<div class="row-fluid"></div>
		<div class="span6">
			<img>
		</div>

		<div class="span3">
			Dominio:<strong><%=getDomainName(session)%></strong>
		</div>
		|
		<div class="span3">
			Usuario:<strong><%=getUserName(session)%> - <%=getUserMail(session)%></strong>
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