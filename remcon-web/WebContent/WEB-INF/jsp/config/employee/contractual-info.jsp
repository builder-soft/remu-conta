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
	Employee empl = (Employee) request.getAttribute("Employee");
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
		<td class="cData"><%=empl.getRut()%></td>
	</tr>
	<tr>
		<td class="cLabel">Empleado:</td>
		<td class="cData"><%=empl.getName() + " " + empl.getLastName1() + " "
					+ empl.getLastName2()%></td>
	</tr>
</table>
<br>
<form action="">
	<input type="hidden" name="cId" value="<%=empl.getId()%>">

	<table border="1">
		<tr>
			<td class="cLabel">Cargo:</td>
			<td><select name="cProfile">
					<%
						for (Profile profile : profiles) {
					%>
					<option value="<%=profile.getId()%>"><%=profile.getName()%></option>
					<%
						}
					%>
			</select></td>
			<td class="cLabel">Tipo Contrato:</td>
			<td><select name="cContractType">
					<%
						for (ContractType contractType : contractTypes) {
					%>
					<option value="<%=contractType.getId()%>"><%=contractType.getName()%></option>
					<%
						}
					%>
			</select></td>
		</tr>
		<tr>
			<td class="cLabel">Inicio Contrato:</td>
			<td class="cData"><input type="text" name="cStartContract">(<%=dateFormat%>)</td>
			<td class="cLabel">Término Contrato:</td>
			<td class="cData"><input type="text" name="cEndContract">(<%=dateFormat%>)</td>
		</tr>
		<tr>
			<td class="cLabel">Gratificación:</td>
			<td><select name="cGratificationType">
					<%
						for (GratificationType gratificationType : gratificationTypes) {
					%>
					<option value="<%=gratificationType.getId()%>"><%=gratificationType.getName()%></option>
					<%
						}
					%>
			</select></td>

			<td class="cLabel">Horario:</td>
			<td><select name="cHorary">
					<%
						for (Horary horary : horaries) {
					%>
					<option value="<%=horary.getId()%>"><%=horary.getName()%></option>
					<%
						}
					%>
			</select></td>
		</tr>
		<tr>
			<td class="cLabel">Factor H.E.:</td>
			<td>&nbsp;</td>
			<td class="cLabel">Sueldo Base:</td>
			<td><input type="text" name="cSalaryRoot"></td>
		</tr>
		<tr>
			<td class="cLabel">Colación:</td>
			<td><input type="text" name="cFeeding"></td>
			<td class="cLabel">Movilización:</td>
			<td><input type="text" name="cMobilization"></td>
		</tr>
		<tr>
			<td class="cLabel">Meses cotizados:</td>
			<td colspan="3"><input type="text" name="XXXXXXXXX"></td>
		</tr>

	</table>
<br>
	<input type="submit" value="Aceptar" disabled>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/servlet/remu/EmployeeManager">Cancelar</a>
</form>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
