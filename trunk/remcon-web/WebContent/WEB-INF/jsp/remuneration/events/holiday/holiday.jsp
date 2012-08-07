<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.business.beans.Period"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Period period = (Period) request.getAttribute("Period");
	Employee employee = (Employee) request.getAttribute("Employee");
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<h1 class="cTitle">Vacaciones de empleado</h1>


<br>
<%@ include file="/WEB-INF/jsp/config/employee/employee-information.jsp"%>
<hr>

<table>
	<tr>
		<td class="cLabel">Fecha inicio contrato</td>
		<td class="cData">01/01/2012</td></tr>
	<tr>
		<td class="cLabel">Fecha termino de contrato</td>
		<td class="cData">31/12/2012</td>
	</tr>
	<tr>
		<td class="cLabel">Días a la fecha (06/08/2012)</td>
		<td class="cData">7</td>
	</tr>
	<tr>
		<td class="cLabel">Total días</td>
		<td class="cData">15</td>
	</tr>
	<tr>
		<td class="cLabel">Días Tomados</td>
		<td class="cData">3</td>
	</tr>
	<tr>
		<td class="cLabel">Saldo a la fecha</td>
		<td class="cData">4</td>
	</tr>


</table>
<hr>
<table border="0">
	<tr>
		<td valign="top">
			<table class="cList" cellpadding="0" cellspacing="0">
				<caption>Toma de vacaciones</caption>
				<tr>
					<td class='cHeadTD'>Fecha Inicio</td>
					<td class='cHeadTD'>Fecha Termino</td>
					<td class='cHeadTD'>Días</td>
				</tr>
			</table>
		</td>
		<td>&nbsp;&nbsp;</td>
		<td valign="top">
			<table class="cList" cellpadding="0" cellspacing="0">
				<caption>Saldo de vacaciones</caption>
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

		</td>


	</tr>
</table>


<a
	href="${pageContext.request.contextPath}/servlet/remuneration/events/EventsEmployeeServlet">Volver</a>

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

