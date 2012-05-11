<%@page import="cl.buildersoft.framework.beans.Account2"%>
<%@page import="cl.buildersoft.framework.beans.Agreement"%>
<%@page import="cl.buildersoft.framework.beans.Board"%>
<%@page import="cl.buildersoft.framework.beans.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Employee empl = (Employee) request.getAttribute("Employee");
	Agreement agreement = (Agreement) request.getAttribute("Agreement");

	List<Board> banks = (List<Board>) request.getAttribute("Banks");
	List<Board> accountTypes = (List<Board>) request
			.getAttribute("AccountTypes");
	List<Board> paymentTypes = (List<Board>) request
			.getAttribute("PaymentTypes");
	Account2 account = (Account2) request.getAttribute("Account");
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<h1 class="cTitle">Forma de Pago</h1>

<!-- 
<span class="cData"> <%=account.toString()%>
</span>
<hr>
<span class="cData"> <%=agreement.toString()%>
</span>
 -->
 
<form
	action="${pageContext.request.contextPath}/servlet/config/employee/SavePayMode">
	<!-- 
	action="${pageContext.request.contextPath}/servlet/ShowParameters">
	 -->

	<input type="hidden" name="cId" value="<%=empl.getId()%>">

	<table border=0>
		<tr>
			<td class="cLabel">RUT:</td>
			<td class="cData"><%=empl.getRut()%></td>
		</tr>
		<tr>
			<td class="cLabel">Empleado:</td>
			<td class="cData"><%=empl.getName() + " " + empl.getLastName1() + " "
					+ empl.getLastName2()%></td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td class="cLabel">Modo de pago:</td>
			<td><select name="cPaymentType">
					<%
						for (Board paymentType : paymentTypes) {
					%>
					<option value="<%=paymentType.getId()%>"
						<%=paymentType.getId()
						.equals(agreement.getPaymentType()) ? "selected" : ""%>><%=paymentType.getValue()%></option>
					<%
						}
					%>
			</select></td>
		</tr>
		<tr>
			<td class="cLabel">Banco:</td>
			<td><select name="cBank">
					<%
						for (Board bank : banks) {
					%>
					<option value="<%=bank.getId()%>"
						<%=bank.getId().equals(account.getInstitution()) ? "selected"
						: ""%>><%=bank.getValue()%></option>
					<%
						}
					%>
			</select></td>
		</tr>
		<tr>
			<td class="cLabel">Número de Cuenta:</td>
			<td class="cData"><input name="cNumber"
				value="<%=account.getNumber()%>"></td>
		</tr>
		<tr>
			<td class="cLabel">Tipo de cuenta:</td>

			<td class="cData"><select name="cAccountType">
					<%
						for (Board accountType : accountTypes) {
					%>
					<option value="<%=accountType.getId()%>" <%=accountType.getId()
						.equals(account.getAccountType()) ? "selected" : ""%>><%=accountType.getValue()%></option>
					<%
						}
					%>
			</select>(Pesos Chilenos)</td>
		</tr>
	</table>
	<br> <input type="submit" value="Aceptar">&nbsp;&nbsp; <a
		href="${pageContext.request.contextPath}/servlet/remu/EmployeeManager">Cancelar</a>

</form>
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>