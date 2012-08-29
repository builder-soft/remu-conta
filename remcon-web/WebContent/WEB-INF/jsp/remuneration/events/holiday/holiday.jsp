<%@page import="cl.buildersoft.framework.util.BSDateTimeUtil"%>
<%@page import="java.util.Calendar"%>
<%@page import="cl.buildersoft.framework.util.BSUtils"%>
<%@page import="cl.buildersoft.framework.type.BSDouble"%>
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
//	Period period = (Period) request.getAttribute("Period");
	Employee employee = (Employee) request.getAttribute("Employee");
	ResultSet holidays = (ResultSet) request.getAttribute("Holidays");
	Agreement agreement = (Agreement) request.getAttribute("Agreement");
	ResultSet holidayInfo = (ResultSet) request.getAttribute("HolidayInfo");
	List<HolidayDevelop> holidayDevelop = (List<HolidayDevelop>) request.getAttribute("HolidayDevelop");
	Double normalBalance = (Double) request.getAttribute("NormalBalance");
	Double creepingBalance = (Double) request.getAttribute("CreepingBalance");
	String defaultHoliday = (String)request.getAttribute("DefaultHoliday");

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
<script
	src="${pageContext.request.contextPath}/js/remuneration/events/holiday/holiday.js?<%=Math.random()%>">
	
</script>
<h1 class="cTitle">Vacaciones de empleado</h1>

<br>
<%@ include file="/WEB-INF/jsp/config/employee/employee-information.jsp"%>
<hr>

<table border="0">
	<tr>
		<td class="cLabel">Inicio contrato</td>
		<td class="cData"><%=BSDateTimeUtil.date2String(request, agreement.getStartContract())%></td>
		<td class="cLabel">Término contrato</td>
		<td class="cData"><%=getEndDateContract(request, agreement)%></td>
	</tr>
	<tr>
		<td class="cLabel">Días a la fecha (<%=BSDateTimeUtil.date2String(request, new Date())%>)
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
					<td class='<%=color%>' align="center"><%=BSDateTimeUtil.date2String(request, holidays.getDate("cFrom"))%></td>
					<td class='<%=color%>' align="right"><%=holidays.getInt("cNormal")%></td>
					<td class='<%=color%>' align="right"><%=holidays.getInt("cCreeping")%></td>
					<td class='<%=color%>' align="center"><%=BSDateTimeUtil.date2String(request, holidays.getDate("cTo"))%></td>
					<td class='<%=color%>' align="center"><a href="#" onclick="javascript:showCertificate(<%=holidays.getLong("cId")%>);">Ver</a></td>
				</tr>
				<%
					}

					if (!haveHoliday) {
				%>
				<tr>
					<td colspan="5" class="cDataTD">Vacaciones no registradas</td>
				</tr>
				<%
					}
				%>
			</table> <br> <button type="Button" 
			onclick="javascript:showTooltip('divRetrieveHoliday');calculateEndDate();"
			<%=expiredContract ? "disabled" : ""%>>Solicitar vacaciones</button> <a class="cCancel"
			href="${pageContext.request.contextPath}/servlet/remuneration/events/EventsEmployeeServlet">Volver</a>
			<%
				if (expiredContract) {
					out.println("<br><span class='cWarning' style='width:100%;'>&nbsp;&nbsp;&nbsp;El contrato ya expiró&nbsp;&nbsp;&nbsp;</span>");
				}
			%>

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
					<td class='cHeadTD'>Proporcional</td>
					<td class='cHeadTD'>Tomadas</td>
					<td class='cHeadTD'>Saldo</td>

					<td class='cHeadTD'>Proporcional</td>
					<td class='cHeadTD'>Tomadas</td>
					<td class='cHeadTD'>Saldo</td>

				</tr>
				<%
				/*
					BSConfig _config = new BSConfig();
					String format = _config.getFormatDecimal(conn);
					*/
					index = 0;
					for (HolidayDevelop hd : holidayDevelop) {
						index++;
						color = index % 2 == 0 ? "cDataTD_odd" : "cDataTD";
				%>
				<tr>
					<td class="<%=color%>" align="center"><%=hd.getYear()%>
					<td class="<%=color%>" align="right"><%=BSWeb.formatDouble(request, hd.getNormalRatio())%>
					<td class="<%=color%>" align="right"><%=BSWeb.formatDouble(request, hd.getNormalTaken())%>
					<td class="<%=color%>" align="right"><%=BSWeb.formatDouble(request, hd.getNormalBalance())%>
					<td class="<%=color%>" align="right"><%=BSWeb.formatDouble(request, hd.getCreepingRatio())%>
					<td class="<%=color%>" align="right"><%=BSWeb.formatDouble(request, hd.getCreepingTaken())%>
					<td class="<%=color%>" align="right"><%=BSWeb.formatDouble(request, hd.getCreepingBalance())%>
					<td class="<%=color%>" align="right"><%=BSWeb.formatDouble(request, hd.getTotalBalance())%>
				</tr>
				<%
					}
				%>
			</table>

		</td>

	</tr>
</table>

<br>



<div id="divRetrieveHoliday" style="display: none">
	<h2 class="cTitle2">Solicitud de vacaciones</h2>

	<form method="post"
		action="${pageContext.request.contextPath}/servlet/remuneration/events/holiday/AddHoliday"
		id="frm">

		<!-- 	<form
		action="${pageContext.request.contextPath}/servlet/ShowParameters"
		id="frm">
		 -->
		<input type="hidden" Name="cId" value="<%=employee.getId()%>">

		<table>
			<tr>
				<td class="cLabel">Fecha Inicio:</td>
				<td><input size="10" name="cFrom" id="cFrom"
					value="<%=BSDateTimeUtil.date2String(request, new Date())%>"
					onblur="javascript:calculateEndDate();"> <span
					class="cLabel">(Use el formato <%=BSDateTimeUtil.getFormatDate(request)%>)
				</span> <span id="dateMessage" class="cLabel"> </span></td>
			</tr>

			<tr>
				<td class="cLabel">Días normales:</td>
				<td><input name="cNormal" id="cNormal" size="3" onblur="javascript:calculateEndDate();"
					value="<%=defaultHoliday%>"> <span class="cLabel">(máximo
						<%=Math.round(normalBalance)%>)
				</span></td>
			</tr>

			<tr>
				<td class="cLabel">Días progresivos:</td>
				<td><input name="cCreeping" id="cCreeping"
					<%=creepingBalance > 0 ? "" : "disabled"%> size="3" value="0"
					onblur="javascript:calculateEndDate();"><span
					class="cLabel">(máximo <%=Math.round(creepingBalance)%>)
				</span></td>
			</tr>
			<tr>
				<td class="cLabel">Fecha Final:</td>
				<td><input size="10" id="cTo" disabled></td>
			</tr>

		</table>
	</form>
	<br>
	<button onclick="javascript:acceptHoliday()">Aceptar</button>
	<button onclick="javascript:closeTooltip()">Cancelar</button>

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
			out = BSDateTimeUtil.date2String(request, agreement.getEndContract());
		}
		return out;
	}

	private String getValueFormated(HttpServletRequest request, ResultSet rs, String fieldName) throws SQLException {
		Double valueAsDouble = 0D;
		String valueFormated = null;

		valueAsDouble = rs.getDouble(fieldName);

//		valueFormated = BSWeb.number2String(valueAsDouble, BSWeb.getFormatDecimal(request));
		valueFormated = BSWeb.formatDouble(request, valueAsDouble); //, BSWeb.getFormatDecimal(request));
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