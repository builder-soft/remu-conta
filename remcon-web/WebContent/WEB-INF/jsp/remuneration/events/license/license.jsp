<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.business.beans.Period"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Period period = (Period) request.getAttribute("Period");
	Employee employee = (Employee) request.getAttribute("Employee");

	String periodName = BSWeb.month2Word(period.getDate()) + " de "
			+ BSWeb.getYear(period.getDate());
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<h1 class="cTitle">Licencias de empleado</h1>

<span class="cLabel">Período:&nbsp;&nbsp;</span>
<span class="cData"><%=periodName%></span>
<br>
<%@ include file="/WEB-INF/jsp/config/employee/employee-information.jsp"%>

<!-- 
<span class="cData"><hr> Pintar una tabla con los campos de
	la tabla tLicense, y que el detalle sea considarnado la carga de un
	archivo para los que requieren un certificado... </span>
-->

<table class="cList" cellpadding="0" cellspacing="0" border="1">
	<tr>
		<td class="cHeadTD">Desde</td>
		<td class="cHeadTD">Hasta</td>
		<td class="cHeadTD">Días</td>
		<td class="cHeadTD">Causa</td>
	</tr>
</table>

<!-- 
<table class="cList" cellpadding="0" cellspacing="0">
	<tr>
		<td class='cHeadTD'>encabezado</td>
	</tr>
	<tr>
		<td class='cDataTD'>dato</td>
	</tr>
	<tr>
		<td class='cDataTD_odd'>dato</td>
	</tr>
</table>

<a href="${pageContext.request.contextPath}/servlet/...">Volver</a>
-->
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
