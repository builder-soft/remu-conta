<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/header2.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu2.jsp"%>

<%
	try {
		HttpSession s = request.getSession();
		HttpSessionContext sc = s.getSessionContext();
		Enumeration ids = sc.getIds();
		while (ids.hasMoreElements()) {
			String id = (String) ids.nextElement();
			HttpSession theSession = sc.getSession(id);
			theSession.invalidate();
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
%>

<h1 class="cTitle">Contenido...</h1>

<form action="${pageContext.request.contextPath}/servlet/ShowParameters">
	<input type="radio" name="sex" value="male" /> Male<br /> <input type="radio" name="sex" value="female" /> Female<br /> <input
		type="submit">
</form>

<%@ include file="/WEB-INF/jsp/common/footer2.jsp"%>

