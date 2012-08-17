<%@page import="cl.buildersoft.framework.util.BSConfig"%>
<%@page import="cl.buildersoft.business.beans.HolidayDevelop"%>
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
	List<HolidayDevelop> holidayDevelop = (List<HolidayDevelop>) request.getAttribute("HolidayDevelop");
	Boolean expiredContract = expiredContract(agreement);
	BSmySQL mysql = new BSmySQL();
	Connection conn = mysql.getConnection(request);

	String totalDays = "";
	String proportionalToday = "";
	String normalDays = "";
	String creepingDays = "";
	String balanceDays = "";

	if (holidayInfo.next()) {
		totalDays = getValueFormated(request, holidayInfo, "cTotalDays");
		proportionalToday = getValueFormated(request, holidayInfo, "cProportionalToday");
		normalDays = getValueFormated(request, holidayInfo, "cNormalDays");
		creepingDays = getValueFormated(request, holidayInfo, "cCreepingDays");
		balanceDays = getValueFormated(request, holidayInfo, "cBalanceDays");
	}
%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<h1 class="cTitle">Vacaciones de empleado</h1>

<br>
<%@ include file="/WEB-INF/jsp/config/employee/employee-information.jsp"%>
<hr>

<table border="0">
	<tr>
		<td class="cLabel">Inicio contrato</td>
		<td class="cData"><%=BSWeb.date2String(request, agreement.getStartContract())%></td>
		<td class="cLabel">Término contrato</td>
		<td class="cData"><%=getEndDateContract(request, agreement)%></td>
	</tr>
	<tr>
		<td class="cLabel">Días a la fecha (<%=BSWeb.date2String(request, new Date())%>)
		</td>
		<td class="cData"><%=proportionalToday%></td>
		<td class="cLabel">Total días</td>
		<td class="cData"><%=totalDays%></td>
	</tr>
	<tr>
		<td class="cLabel">Días progresivos tomados</td>
		<td class="cData"><%=creepingDays%></td>

		<td class="cLabel">Días normales tomados</td>
		<td class="cData"><%=normalDays%></td>
	</tr>

	<tr>
		<td class="cLabel">Saldo a la fecha</td>
		<td class="cData"><%=balanceDays%></td>
		<td colspan="2">&nbsp;</td>
	</tr>

</table>
<hr>
<table border="0" width="90%">
	<tr>
		<td valign="top">
			<table class="cList" cellpadding="0" cellspacing="0">
				<caption>Vacaciones tomadas</caption>
				<tr>
					<td class='cHeadTD' align="center">Fecha Inicio</td>
					<td class='cHeadTD' align="center">Días Normales</td>
					<td class='cHeadTD' align="center">Días Progresivos</td>
					<td class='cHeadTD' align="center">Fecha Termino</td>
					<td class='cHeadTD' align="center">Certificado</td>
				</tr>
				<%
					Boolean haveHoliday = Boolean.FALSE;
							String color = null;
							Integer index = 0;
							while (holidays.next()) {
								index++;
								haveHoliday = Boolean.TRUE;
								color = index % 2 == 0 ? "cDataTD_odd" : "cDataTD";
				%>
				<tr>
					<td class='<%=color%>' align="center"><%=BSWeb.date2String(request, holidays.getDate("cFrom"))%></td>
					<td class='<%=color%>' align="right"><%=holidays.getInt("cNormal")%></td>
					<td class='<%=color%>' align="right"><%=holidays.getInt("cCreeping")%></td>
					<td class='<%=color%>' align="center"><%=BSWeb.date2String(request, holidays.getDate("cTo"))%></td>
					<td class='<%=color%>' align="center"><a href="#">Ver</a></td>
				</tr>
				<%
					}

							if (!haveHoliday) {
				%>
				<tr>
					<td colspan="4" class="cDataTD">Vacaciones no registradas</td>
				</tr>
				<%
					}
				%>
			</table>
		</td>
		<td>&nbsp;&nbsp;</td>
		<td valign="top">
			<table class="cList" cellpadding="0" cellspacing="0">
				<caption>Desarrollo de vacaciones</caption>
				<tr>
					<td class='cHeadTD' rowspan="2">Año</td>
					<td class='cHeadTD' colspan="3">Normales</td>
					<td class='cHeadTD' colspan="3">Progresivas</td>
					<td class='cHeadTD' rowspan="2">Saldo<br>Total
					</td>
				<tr>
					<!-- 
					<td class='cHeadTD'></td>
 -->
					<td class='cHeadTD'>Proporcional</td>
					<td class='cHeadTD'>Tomadas</td>
					<td class='cHeadTD'>Saldo</td>

					<td class='cHeadTD'>Proporcional</td>
					<td class='cHeadTD'>Tomadas</td>
					<td class='cHeadTD'>Saldo</td>

				</tr>
				<%
					BSConfig _config = new BSConfig();
							String format = _config.getFormatDecimal(conn);
							for (HolidayDevelop  hd : holidayDevelop) {
				%>
				<tr>
					<td class="cDataTD" align="center"><%=hd.getYear()%>
					<td class="cDataTD" align="right"><%=BSWeb.number2String(hd.getNormalRatio(), format)%>
					<td class="cDataTD" align="right"><%=BSWeb.number2String(hd.getNormalTaken(), format)%>
					<td class="cDataTD" align="right"><%=BSWeb.number2String(hd.getNormalBalance(), format)%>
					<td class="cDataTD" align="right"><%=BSWeb.number2String(hd.getCreepingRatio(), format)%>
					<td class="cDataTD" align="right"><%=BSWeb.number2String(hd.getCreepingTaken(), format)%>
					<td class="cDataTD" align="right"><%=BSWeb.number2String(hd.getCreepingBalance(), format)%>
					<td class="cDataTD" align="right"><%=BSWeb.number2String(hd.getTotalBalance(), format)%>
				</tr>
				<%
					}
				%>
			</table>

		</td>

	</tr>
</table>

<br>

<input type="Button" value="Solicitar vacaciones"
	onclick="javascript:showTooltip('divRetrieveHoliday');"
	<%=expiredContract ? "disabled" : ""%>>

<a
	href="${pageContext.request.contextPath}/servlet/remuneration/events/EventsEmployeeServlet">Volver</a>
<%
	if (expiredContract) {
		out.println("<br><span class='cWarning' style='width:100%;'>&nbsp;&nbsp;&nbsp;El contrato ya expiró&nbsp;&nbsp;&nbsp;</span>");
	}
%>
<div id="divRetrieveHoliday" style="display: none">
	<h2 class="cTitle2">Solicitud de vacaciones</h2>


	<table>
		<tr>
			<td class="cLabel">Fecha Inicio:</td>
			<td><input></td>
		</tr>

		<tr>
			<td class="cLabel">Fecha Final:</td>
			<td><input></td>
		</tr>

		<tr>
			<td class="cLabel">Días normales:</td>
			<td><input></td>
		</tr>

		<tr>
			<td class="cLabel">Días progresivos:</td>
			<td><input></td>
		</tr>

	</table>

	<br>
	<button disabled>Aceptar y descargar comprobante</button>
	<button onclick="javascript:closeTooltip()">Cancelar</button>


	<!-- 
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
	
	<br /> <input type="button" value="Aceptar" disabled
		onclick="javascript:closeTooltip()" />
	-->

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
	}

	private Boolean expiredContract(Agreement agreement) {
		Boolean out = Boolean.FALSE;
		if (!agreement.getContractType().equals(1L)) {
			Date endContract = agreement.getEndContract();
			out = endContract.before(new Date());
		}
		return out;
	}%>