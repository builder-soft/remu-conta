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

	String periodName = BSWeb.month2Word(period.getDate()) + " de " + BSWeb.getYear(period.getDate());
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<script
	src="${pageContext.request.contextPath}/js/remuneration/events/license/license.js?<%=Math.random()%>">
	
</script>

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
<hr>
<table class="cList" cellpadding="0" cellspacing="0" border="1">
	<tr>
		<td class="cHeadTD">Desde</td>
		<td class="cHeadTD">Hasta</td>
		<td class="cHeadTD">Días</td>
		<td class="cHeadTD">Causa</td>
	</tr>


</table>
<br>
<div id="defaultButtons">
	<input type="Button" value="Agregar" onclick="javascript:showForm();">
	<input type="Button" value="Modificar" id="modify"
		onclick="javascript:editOvertime();"> <input type="Button"
		value="Eliminar" id="erase" onclick="javascript:eraseOvertime();">

	<a
		href="${pageContext.request.contextPath}/servlet/remuneration/events/EventsEmployeeServlet">Volver</a>
</div>

<div id="divShowDetail" style="display: none">
	<h2 class="cTitle2">Detalle de Licencia</h2>
	<!-- 
	<div class="contentScroll"></div>
	 -->
	<table>
		<tr>
			<td class="cLabel">Desde:</td>
			<td><%=write_date(period, "From")%></td>
		</tr>
		<tr>
			<td class="cLabel">Hasta:</td>
			<td><%=write_date(period, "To")%></td>
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
		 --> <select name="cCause" onchange='javascript:alert(this.value);'>
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
			<td><input></td>
		</tr>
		<tr id="RetrieveFile">
			<td class="cLabel">Archivo:</td>
			<td><input></td>
		</tr>
	</table>
	<br /> <input type="button" value="Aceptar"
		onclick="javascript:closeTooltip()" /> <input type="button"
		value="Cancelar" onclick="javascript:closeTooltip()" />

</div>
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
<%!private String write_date(Period period, String id) {
		String periodName = BSWeb.month2Word(period.getDate()) + " de " + BSWeb.getYear(period.getDate());
		String html = "<select id='" + id + "' onchange='javascript:changeDay();'>";
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