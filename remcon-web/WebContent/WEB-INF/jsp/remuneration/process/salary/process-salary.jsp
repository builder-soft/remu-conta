<%@page import="java.util.Date,cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.business.beans.Period"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Period period = (Period) request.getAttribute("Period");
	Date date = period.getDate();

	//String month = date.
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<h1 class="cTitle">Cálculo de Remuneraciones</h1>

<form action="/servlet/">
<table>
	<tr>
		<td class='cLabel'>Período:</td>
		<td class='cData'><%=BSWeb.month2Word(date)%> de <%=date.getYear()%></td>
	</tr>
	<!-- 
<tr>
<td class='cLabel'>Empresa:</td>
<td class='cData'><select/></td>
</tr>
-->
</table>
</form>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

