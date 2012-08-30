<%@page import="cl.buildersoft.framework.util.BSDateTimeUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="cl.buildersoft.business.service.impl.PeriodServiceImpl"%>
<%@page import="cl.buildersoft.business.service.PeriodService"%>
<%@page import="cl.buildersoft.business.beans.LicenseCause"%>
<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.business.beans.Period"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Period period = (Period) request.getAttribute("Period");
	Employee employee = (Employee) request.getAttribute("Employee");
	List<LicenseCause> licenseCauses = (List<LicenseCause>) request.getAttribute("LicenseCauses");
	ResultSet licenses = (ResultSet) request.getAttribute("Licenses");

	String periodName = BSDateTimeUtil.month2Word(period.getDate()) + " de " + BSDateTimeUtil.getYear(period.getDate());
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<script
	src="${pageContext.request.contextPath}/js/remuneration/events/license/license.js?<%=Math.random()%>">
	
</script>

<h1 class="cTitle">Movimiento de personal</h1>

<span class="cLabel">Período:&nbsp;&nbsp;</span>
<span class="cData"><%=periodName%></span>
<br>
<%@ include file="/WEB-INF/jsp/config/employee/employee-information.jsp"%>

<!-- 
<span class="cData"><hr> Pintar una tabla con los campos de
	la tabla tLicense, y que el detalle sea considarnado la carga de un
	archivo para los que requieren un certificado... </span>
-->
<hr>
<table class="cList" cellpadding="0" cellspacing="0" border="1">
	<tr>
		<td class="cHeadTD">Sele.</td>
		<td class="cHeadTD">Desde</td>
		<td class="cHeadTD">Hasta</td>
		<td class="cHeadTD">Días</td>
		<td class="cHeadTD">Causa</td>
		<td class="cHeadTD">Archivo</td>
	</tr>
	<%
		String fileName = null;
		Integer index = 0;
		while (licenses.next()) {
			fileName = licenses.getString("cFileName");
			if (fileName == null) {
				fileName = "-";
			}
			index++;
	%>
	<tr>
		<td class="cDataTD"><input type="radio" name='LicenseRadio'
			value="<%=licenses.getLong("cId")%>"
			<%=index.equals(1) ? "checked" : ""%>></td>
		<td class="cDataTD"><%=licenses.getInt("cFrom") + " " + periodName%></td>
		<td class="cDataTD"><%=licenses.getInt("cTo") + " " + periodName%></td>
		<td class="cDataTD"><%=licenses.getInt("cDays")%></td>
		<td class="cDataTD"><%=licenses.getString("cLicenseCauseName")%></td>
		<td class="cDataTD"><%=fileName%></td>
	</tr>
	<%
		}
		Connection conn = (Connection) request.getAttribute("Conn");
		new BSmySQL().closeConnection(conn);
	%>

</table>
<br>
<div id="defaultButtons">
	<button type="Button" onclick="javascript:showForm();">Agregar</button>
	<button type="Button" id="erase" onclick="javascript:eraseLicense();">Eliminar</button>
	<a class="cCancel"
		href="${pageContext.request.contextPath}/servlet/remuneration/events/EventsEmployeeServlet">Volver</a>
</div>
<form id="EraseLicense"
	action="${pageContext.request.contextPath}/servlet/remuneration/events/license/EraseLicense"
	method="post">
	<input type="hidden" name="cLicense" id="cLicense"> <input
		type="hidden" value="<%=employee.getId()%>" name="cId">

</form>
<div id="divShowDetail" style="display: none">
	<h2 class="cTitle2">Detalle de Licencia</h2>

	<form
		action="${pageContext.request.contextPath}/servlet/remuneration/events/license/AddLicense"
		enctype="multipart/form-data" method="POST">


		<!--
		 	<form
		action="${pageContext.request.contextPath}/servlet/ShowParameters">
-->
		<input type="hidden" value="<%=employee.getId()%>" name="cId">
		<input type="hidden" value="<%=period.getId()%>" name="cPeriod">
		<table border="0">
			<tr>
				<td class="cLabel">Desde:</td>
				<td><%=write_date(period, "cFrom")%></td>
			</tr>
			<tr>
				<td class="cLabel">Hasta:</td>
				<td><%=write_date(period, "cTo")%></td>
			</tr>
			<tr>
				<td class="cLabel">Días:</td>
				<td id="days" class="cData"></td>
			</tr>
			<tr>
				<td class="cLabel">Causa:</td>
				<td>
					<!-- 
			< %=cause.getId().equals(agreement.getProfile()) ? "selected"
						: ""% >
		 --> <select name="cCause" id="cCause"
					onchange='javascript:changeCause();'>
						<%
							String fileCategorie = null;
							for (LicenseCause cause : licenseCauses) {
								fileCategorie = cause.getFileCategory() == null ? "" : "" + cause.getFileCategory();
						%>
						<option value="<%=cause.getId()%>#<%=fileCategorie%>"><%=cause.getName()%></option>
						<%
							}
						%>
				</select>
				</td>
			</tr>

			<tr id="RetrieveReason">
				<td class="cLabel">Razón:</td>
				<td><input name="cReason"></td>
			</tr>
			<tr id="RetrieveFile">
				<td class="cLabel">Certificado:</td>
				<td><input type="file" name="cFile"></td>
			</tr>
		</table>

		<br /> <input type="submit" value="Aceptar" />
		<button type="button" onclick="javascript:closeTooltip()">Cancelar</button>
	</form>
</div>
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
<%!private String write_date(Period period, String id) {
		String periodName = BSDateTimeUtil.month2Word(period.getDate()) + " de " + BSDateTimeUtil.getYear(period.getDate());
		String html = "<select name='" + id + "' id='" + id + "' onchange='javascript:changeDay();'>";
		for (Integer i = 1; i <= lastDayMonth(period); i++) {
			html += "<option value='" + i + "'>" + i + "</option>";
		}
		html += "</select><span class='cLabel'> de " + periodName + "</span>";
		return html;
	}

	private Integer lastDayMonth(Period period) {
		PeriodService ps = new PeriodServiceImpl();
		return ps.lastDayMonth(period);
	}%>