<%@page import="cl.buildersoft.framework.beans.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<%
	Employee empl = (Employee) request.getAttribute("Employee");
%>

<h1 class="cTitle">Infrmación Contractual</h1>

<table>
	<tr>
		<td class="cLabel">RUT:</td>
		<td class="cData"><%=empl.getRut()%></td>
	</tr>
	<tr>
		<td class="cLabel">Empleado:</td>
		<td class="cData"><%=empl.getName() + " " + empl.getLastName1() + " "
					+ empl.getLastName2()%></td>
	</tr>
</table>

<table>
  <tr>
    <td class="cLabel">Cargo:</td><td>&nbsp;</td>
    <td class="cLabel">Tipo Contrato:</td><td>&nbsp;</td>
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
 -->

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
