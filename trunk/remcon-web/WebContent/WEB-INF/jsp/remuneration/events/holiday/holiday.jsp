<%@page import="java.sql.SQLException"%>
<%@page import="java.util.Date"%>
<%@page
	import="cl.buildersoft.business.service.impl.AgreementServiceImpl"%>
<%@page import="cl.buildersoft.business.service.AgreementService"%>
<%@page import="cl.buildersoft.business.beans.Agreement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.business.beans.Period"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Period period = (Period) request.getAttribute("Period");
	Employee employee = (Employee) request.getAttribute("Employee");
	ResultSet holidays = (ResultSet) request.getAttribute("Holidays");
	Agreement agreement = (Agreement) request.getAttribute("Agreement");
	ResultSet holidayInfo = (ResultSet) request.getAttribute("HolidayInfo");

	String totalDays = null;
	String proportionalToday = null;
	String usedDays = null;
	String balanceDays = null;
	
	if (holidayInfo.next()) {
		totalDays = getValueFormated(request, holidayInfo, "cTotalDays");
		proportionalToday = getValueFormated(request, holidayInfo, "cProportionalToday");
		usedDays = getValueFormated(request, holidayInfo, "cUsedDays");
		balanceDays = getValueFormated(request, holidayInfo, "cBalanceDays");
	}
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
		<td class="cData"><%=BSWeb.date2String(request, agreement.getStartContract())%></td>
		<!-- 
	</tr>
	<tr> -->
		<td class="cLabel">Fecha termino de contrato</td>
		<td class="cData"><%=getEndDateContract(request, agreement)%></td>
	</tr>
	<tr>
		<td class="cLabel">Días a la fecha (<%=BSWeb.date2String(request, new Date())%>)
		</td>
		<td class="cData"><%=proportionalToday%></td>
		<!-- 
	</tr>
	<tr> -->
		<td class="cLabel">Total días</td>
		<td class="cData"><%=totalDays%></td>
	</tr>
	<tr>
		<td class="cLabel">Días Tomados</td>
		<td class="cData"><%=usedDays%></td>
		<!-- 
	</tr>
	<tr> -->
		<td class="cLabel">Saldo a la fecha</td>
		<td class="cData"><%=balanceDays%></td>
	</tr>

</table>
<hr>
<table border="0" width="90%">
	<tr>
		<td valign="top">
			<table class="cList" cellpadding="0" cellspacing="0">
				<caption>Vacaciones tomadas</caption>
				<tr>
					<td class='cHeadTD'>Fecha Inicio</td>
					<td class='cHeadTD'>Fecha Termino</td>
					<td class='cHeadTD'>Días</td>
				</tr>
				<%
					Boolean haveHoliday = Boolean.FALSE;
					String color = null;
					Integer index = 0;
					while (holidays.next()) {
						index++;
						color = index % 2 == 0 ? "cDataTD_odd" : "cDataTD";
				%>
				<tr>
					<td class='<%=color%>'><%=BSWeb.date2String(request, holidays.getDate("cFrom"))%></td>
					<td class='<%=color%>'><%=BSWeb.date2String(request, holidays.getDate("cTo"))%></td>
					<td class='<%=color%>'><%=holidays.getInt("cDays")%></td>
				</tr>
				<%
					haveHoliday = Boolean.TRUE;
					}

					if (!haveHoliday) {
				%>
				<tr>
					<td colspan="3" class="cDataTD">No se han tomado vacaciones</td>
				</tr>
				<%
					}
				%>
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
<!-- 
				<tr>
					<td class='cDataTD' colspan="7">dato</td>
				</tr>
				<tr>
					<td class='cDataTD_odd' colspan="7">dato</td>
				</tr>
				 -->
			</table>

		</td>


	</tr>
</table>

<br>
<input type="Button" value="Solicitar vacaciones">
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

<%!private String getEndDateContract(HttpServletRequest request, Agreement agreement) {
		String out = "";
		if (agreement.getContractType().equals(1L)) {

			BSmySQL mysql = new BSmySQL();
			Connection conn = mysql.getConnection(request);

			AgreementService ageementService = new AgreementServiceImpl();
			out = ageementService.getContractTypeName(conn, agreement);

		} else {
			out = BSWeb.date2String(request, agreement.getEndContract());
		}
		return out;
	}

	private String getValueFormated(HttpServletRequest request, ResultSet rs, String fieldName) throws SQLException {
		Double valueAsDouble = 0D;
		String valueFormated = null;

		valueAsDouble = rs.getDouble(fieldName);

		valueFormated = BSWeb.number2String(valueAsDouble, BSWeb.getFormatDecimal(request));
		return valueFormated;
	}%>