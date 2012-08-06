<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.business.beans.Period"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Period period = (Period) request.getAttribute("Period");
	Employee employee = (Employee) request.getAttribute("Employee");

	String periodName = BSWeb.month2Word(period.getDate()) + " de " + BSWeb.getYear(period.getDate());
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<h1 class="cTitle">Vacaciones de empleado</h1>

<span class="cLabel">Período:&nbsp;&nbsp;</span>
<span class="cData"><%=periodName%></span>
<br>
<%@ include file="/WEB-INF/jsp/config/employee/employee-information.jsp"%>
<hr>

<table class="cList" cellpadding="0" cellspacing="0">
	<tr>
		<td class='cHeadTD'>Período</td>
		<td class='cHeadTD'>Normales</td>
		<td class='cHeadTD'>Progresivas</td>
		<td class='cHeadTD'>Total</td>
		<td class='cHeadTD'>Tomadas Normales</td>
		<td class='cHeadTD'>Tomadas Progresivas</td>
		<td class='cHeadTD'>Saldo</td>

	</tr>

	<tr>
		<td class='cDataTD' colspan="7">dato</td>
	</tr>
	<tr>
		<td class='cDataTD_odd' colspan="7">dato</td>
	</tr>
</table>

<a href="${pageContext.request.contextPath}/servlet/remuneration/events/EventsEmployeeServlet">Volver</a>

<div id="divShowDetail" style="display: none">
	<h2 class="cTitle2">Detalle de valores</h2>

	<div class="contentScroll">
		<table class="cList" cellpadding="0" cellspacing="0" id="movesTable">
			<tr>
				<td class="cHeadTD">Detalle</td>
				<td class="cHeadTD">Comentario</td>
				<td class="cHeadTD">Tipo</td>
				<td class="cHeadTD">Monto</td>
			</tr>
		</table>
	</div>
	<br /> <input type="button" value="Cancelar"
		onclick="javascript:closeTooltip()" />

</div>


<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

