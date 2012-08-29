<%@page import="cl.buildersoft.framework.util.BSDateTimeUtil"%>
<%@page import="cl.buildersoft.framework.util.BSBeanUtilsSP"%>
<%@page
	import="cl.buildersoft.business.service.impl.AgreementServiceImpl"%>
<%@page import="cl.buildersoft.business.service.AgreementService"%>
<%@page import="java.sql.Connection"%>
<%@page import="cl.buildersoft.framework.database.BSmySQL"%>
<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.business.beans.Agreement"%>
<%@page import="cl.buildersoft.business.beans.Horary"%>
<%@page import="cl.buildersoft.business.beans.GratificationType"%>
<%@page import="cl.buildersoft.business.beans.ContractType"%>
<%@page import="cl.buildersoft.business.beans.Profile"%>
<%@page import="cl.buildersoft.business.beans.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<%
	Employee employee = (Employee) request.getAttribute("Employee");
	Agreement agreement = (Agreement) request.getAttribute("Agreement");
	if (agreement == null) {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		AgreementService agreementService = new AgreementServiceImpl();
		agreement = agreementService.getDefaultAgreement(conn, employee.getId());
	}

	List<Profile> profiles = (List<Profile>) request.getAttribute("Profiles");
	List<ContractType> contractTypes = (List<ContractType>) request.getAttribute("ContractTypes");
	List<GratificationType> gratificationTypes = (List<GratificationType>) request.getAttribute("GratificationType");
	List<Horary> horaries = (List<Horary>) request.getAttribute("Horary");

	String dateFormat = (String) request.getAttribute("DateFormat");
%>

<h1 class="cTitle">Información Contractual</h1>
<%@ include file="/WEB-INF/jsp/config/employee/employee-information.jsp"%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/config/employee/contractual-info.js">
	
</script>
<br>
<form id="frmContractualInfo"
	action="${pageContext.request.contextPath}/servlet/config/employee/SaveContractualInfo">
	<input type="hidden" name="cId" value="<%=employee.getId()%>">

	<table border="0">
		<tr>
			<td class="cLabel">Cargo:</td>
			<td><select name="cProfile">
					<%
						for (Profile profile : profiles) {
					%>
					<option value="<%=profile.getId()%>"
						<%=profile.getId().equals(agreement.getProfile()) ? "selected" : ""%>><%=profile.getName()%></option>
					<%
						}
					%>
			</select></td>
			<td class="cLabel">Contrato:</td>
			<td><select name="cContractType">
					<%
						for (ContractType contractType : contractTypes) {
					%>
					<option value="<%=contractType.getId()%>"
						<%=contractType.getId().equals(agreement.getContractType()) ? "selected" : ""%>><%=contractType.getName()%></option>
					<%
						}
					%>
			</select></td>
		</tr>
		<tr>
			<td class="cLabel">Inicio Contrato:</td>
			<td><input type="text" name="cStartContract" id="cStartContract"
				onblur="javascript:dateBlur(this);"
				value="<%=BSDateTimeUtil.date2String(request, agreement.getStartContract())%>"><span
				class="cLabel">(<%=dateFormat%>)
			</span></td>
			<td class="cLabel">Término Contrato:</td>
			<td><input type="text" name="cEndContract"
				value="<%=BSDateTimeUtil.date2String(request, agreement.getEndContract())%>"><span
				class="cLabel">(<%=dateFormat%>)
			</span></td>
		</tr>
		<tr>
			<td class="cLabel">Gratificación:</td>
			<td><select name="cGratificationType">
					<%
						for (GratificationType gratificationType : gratificationTypes) {
					%>
					<option value="<%=gratificationType.getId()%>"
						<%=gratificationType.getId().equals(agreement.getGratificationType()) ? "selected" : ""%>><%=gratificationType.getName()%></option>
					<%
						}
					%>
			</select></td>

			<td class="cLabel">Horario:</td>
			<td><select name="cHorary">
					<%
						for (Horary horary : horaries) {
					%>
					<option value="<%=horary.getId()%>"
						<%=horary.getId().equals(agreement.getHorary()) ? "selected" : ""%>><%=horary.getName()%></option>
					<%
						}
					%>
			</select></td>
		</tr>
		<tr>
			<td class="cLabel">Colación:</td>
			<td><input type="text" name="cFeeding" id="cFeeding"
				onfocus="javascript:doubleFocus(this);"
				onblur="javascript:doubleBlur(this);"
				value="<%=BSWeb.formatDouble(request, agreement.getFeeding())%>"></td>
			<td class="cLabel">Movilización:</td>
			<td><input type="text" name="cMobilization" id="cMobilization"
				onfocus="javascript:doubleFocus(this);"
				onblur="javascript:doubleBlur(this);"
				value="<%=BSWeb.formatDouble(request, agreement.getMobilization())%>"></td>
		</tr>
		<tr>
			<td class="cLabel">Sueldo Base:</td>
			<td colspan="3"><input type="text" name="cSalaryRoot"
				id="cSalaryRoot" onfocus="javascript:integerFocus(this);"
				onblur="javascript:integerBlur(this);"
				value="<%=BSWeb.formatInteger(request, agreement.getSalaryRoot().intValue())%>"></td>
		</tr>

	</table>
	<br>
	<button type="button" onclick="javascript:validateAndSubmit()">Aceptar</button>
	&nbsp;&nbsp;<a class="cCancel"
		href="${pageContext.request.contextPath}/servlet/config/employee/EmployeeManager">Cancelar</a>
</form>
<span id="ErrorMessage" class="cError"></span>
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
