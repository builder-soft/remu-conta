<%@page import="cl.buildersoft.business.beans.Period"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
String periodName = (String) request.getAttribute("PeriodName");
String statusName = (String) request.getAttribute("StatusName");
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<h1 class="cTitle">Modificaci�n de datos del per�odo</h1>

<span class="cLabel">El per�odo "<%=periodName%>" no puede ser cambiado por que est� en estado "<%=statusName%>"</span>
<br>
<br>
<a class="cCancel"
	href="${pageContext.request.contextPath}/servlet/admin/period/PeriodManager">Volver</a>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

