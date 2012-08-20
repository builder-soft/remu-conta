<%@page import="cl.buildersoft.framework.util.BSDateTimeUtil"%>
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

<form
	action="${pageContext.request.contextPath}/servlet/remuneration/process/salary/CalculateSalary"
	method="post">
	<table>
		<tr>
			<td class='cLabel'>Período:</td>
			<td class='cData'><%=BSDateTimeUtil.month2Word(date)%> de <%=BSDateTimeUtil.getYear(date)%></td>
		</tr>
		<!-- 
<tr>
<td class='cLabel'>Empresa:</td>
<td class='cData'><select/></td>
</tr>
-->
	</table>
	<input type="submit" value="Calcular">
</form>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

