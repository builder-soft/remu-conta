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
		<td>&nbsp;</td>
		<td class="cLabel">Término Contrato:</td>
		<td>&nbsp;</td>
	</tr>

	<tr>
		<td class="cLabel">Gratificación:</td>
		<td>&nbsp;</td>
		<td class="cLabel">Horario:</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td class="cLabel">Factor H.E.:</td>
		<td>&nbsp;</td>
		<td class="cLabel">Sueldo Base:</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td class="cLabel">Colación:</td>
		<td>&nbsp;</td>
		<td class="cLabel">Movilización:</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td class="cLabel">Meses cotizados:</td>
		<td colspan="3">&nbsp;</td>
	</tr>

</table>



<!-- 
<table class="cList" cellpadding="0" cellspacing="0">
	<tr>
		<td class='cHeadTD'>encabezado</td>
	</tr>
	<tr>
		<td class='cDataTD'>dato</td>
	</tr>
	<tr>
		<td class='cDataTD_odd'>dato</td>
	</tr>
</table>
 -->

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
