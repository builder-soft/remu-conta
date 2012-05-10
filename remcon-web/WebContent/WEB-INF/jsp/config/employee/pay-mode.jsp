<%@page import="cl.buildersoft.framework.beans.Board"%>
<%@page import="cl.buildersoft.framework.beans.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Employee empl = (Employee) request.getAttribute("Employee");


	List<Board> banks = (List<Board>) request.getAttribute("Banks");
	List<Board> accountTypes = (List<Board>) request
			.getAttribute("AccountTypes");
	List<Board> paymentTypes = (List<Board>) request
			.getAttribute("PaymentTypes");
	/**
	request.setAttribute("Employee", getEmployee(conn, bu, employeeId));
	 */
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<h1 class="cTitle">Forma de Pago</h1>

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

<form>
	<table>
		<tr>
			<td class="cLabel">Modo de pago:</td>
			<td><select name="cPaymentTypes">
					<%
						for (Board paymentType : paymentTypes) {
					%>
					<option value="<%=paymentType.getId()%>"><%=paymentType.getValue()%></option>
					<%
						}
					%>
			</select></td>
			<td class="cLabel">Banco:</td>
			<td><select name="cBank">
					<%
						for (Board bank : banks) {
					%>
					<option value="<%=bank.getId()%>"><%=bank.getValue()%></option>
					<%
						}
					%>
			</select></td>
		</tr>

		<tr>
			<td class="cLabel">Número de Cuenta:</td>
			<td><input></td>
			<td class="cLabel">Tipo de cuenta:</td>
			<td><select name="cBank">
					<%
						for (Board accountType : accountTypes) {
					%>
					<option value="<%=accountType.getId()%>"><%=accountType.getValue()%></option>
					<%
						}
					%>
			</select></td>
		</tr>

		</form>
		<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>