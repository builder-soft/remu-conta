<%@page import="cl.buildersoft.framework.util.BSBeanUtilsSP"%>
<%@page	import="cl.buildersoft.business.service.impl.AgreementServiceImpl"%>
<%@page	import="cl.buildersoft.business.service.AgreementService"%>
<%@page import="java.sql.Connection"%>
<%@page import="cl.buildersoft.framework.database.BSmySQL"%>
<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.framework.beans.Agreement"%>
<%@page import="cl.buildersoft.framework.beans.Horary"%>
<%@page import="cl.buildersoft.framework.beans.GratificationType"%>
<%@page import="cl.buildersoft.framework.beans.ContractType"%>
<%@page import="cl.buildersoft.framework.beans.Profile"%>
<%@page import="cl.buildersoft.framework.beans.Employee"%>
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
		agreement = agreementService.getDefaultAgreement(conn,
				employee.getId());
	}


	List<Profile> profiles = (List<Profile>) request
			.getAttribute("Profiles");
	List<ContractType> contractTypes = (List<ContractType>) request
			.getAttribute("ContractTypes");
	List<GratificationType> gratificationTypes = (List<GratificationType>) request
			.getAttribute("GratificationType");
	List<Horary> horaries = (List<Horary>) request
			.getAttribute("Horary");

	String dateFormat = (String) request.getAttribute("DateFormat");
%>

<h1 class="cTitle">Infrmación Contractual</h1>

<table>
	<tr>
		<td class="cLabel">RUT:</td>
		<td class="cData"><%=employee.getRut()%></td>
	</tr>
	<tr>
		<td class="cLabel">Empleado:</td>
		<td class="cData"><%=employee.getName() + " " + employee.getLastName1() + " "
					+ employee.getLastName2()%></td>
	</tr>
</table>
<br>
<form
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
						<%=profile.getId().equals(agreement.getProfile()) ? "selected"
						: ""%>><%=profile.getName()%></option>
					<%
						}
					%>
			</select></td>
			<td class="cLabel">Tipo Contrato:</td>
			<td><select name="cContractType">
					<%
						for (ContractType contractType : contractTypes) {
					%>
					<option value="<%=contractType.getId()%>"
						<%=contractType.getId().equals(
						agreement.getContractType()) ? "selected" : ""%>><%=contractType.getName()%></option>
					<%
						}
					%>
			</select></td>
		</tr>
		<tr>
			<td class="cLabel">Inicio Contrato:</td>
			<td class="cData"><input type="text" name="cStartContract"
				value="<%=BSWeb.date2String(request, agreement.getStartContract())%>">(<%=dateFormat%>)</td>
			<td class="cLabel">Término Contrato:</td>
			<td class="cData"><input type="text" name="cEndContract"
				value="<%=BSWeb.date2String(request, agreement.getEndContract())%>">(<%=dateFormat%>)</td>
		</tr>
		<tr>
			<td class="cLabel">Gratificación:</td>
			<td><select name="cGratificationType">
					<%
						for (GratificationType gratificationType : gratificationTypes) {
					%>
					<option value="<%=gratificationType.getId()%>"
						<%=gratificationType.getId().equals(
						agreement.getGratificationType()) ? "selected" : ""%>><%=gratificationType.getName()%></option>
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
						<%=horary.getId().equals(agreement.getHorary()) ? "selected"
						: ""%>><%=horary.getName()%></option>
					<%
						}
					%>
			</select></td>
		</tr>
		<tr>
			<td class="cLabel">Colación:</td>
			<td><input type="text" name="cFeeding"
				value="<%=agreement.getFeeding()%>"></td>
			<td class="cLabel">Movilización:</td>
			<td><input type="text" name="cMobilization"
				value="<%=agreement.getMobilization()%>"></td>
		</tr>
		<tr>
			<td class="cLabel">Sueldo Base:</td>
			<td colspan="3"><input type="text" name="cSalaryRoot"
				value="<%=agreement.getSalaryRoot()%>"></td>
		</tr>

	</table>
	<br> <input type="submit" value="Aceptar">&nbsp;&nbsp;<a
		href="${pageContext.request.contextPath}/servlet/remu/EmployeeManager">Cancelar</a>
</form>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
