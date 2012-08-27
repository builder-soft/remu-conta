<%@page import="cl.buildersoft.business.beans.AccountType"%>
<%@page import="cl.buildersoft.business.beans.Bank"%>
<%@page import="cl.buildersoft.business.beans.PaymentType"%>
<%@page import="cl.buildersoft.business.beans.Agreement"%>
<%@page import="cl.buildersoft.business.beans.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Employee empl = (Employee) request.getAttribute("Employee");
	Agreement agreement = (Agreement) request.getAttribute("Agreement");

	List<Bank> banks = (List<Bank>) request.getAttribute("Banks");
	List<AccountType> accountTypes = (List<AccountType>) request
			.getAttribute("AccountTypes");
	List<PaymentType> paymentTypes = (List<PaymentType>) request
			.getAttribute("PaymentTypes");
	//	Account2 account = (Account2) request.getAttribute("Account");
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<h1 class="cTitle">Forma de Pago</h1>

<!-- 
<span class="cData"> <%=agreement.toString()%>
</span>
 -->

<form
	action="${pageContext.request.contextPath}/servlet/config/employee/SavePayMode">
	<!-- 
	action="${pageContext.request.contextPath}/servlet/ShowParameters">
	 -->

	<input type="hidden" name="cId" value="<%=empl.getId()%>">
<%@ include file="/WEB-INF/jsp/config/employee/employee-information.jsp"%>
<br>
	<table border=0>
		<tr>
			<td class="cLabel">Modo de pago:</td>
			<td><select name="cPaymentType">
					<%
						for (PaymentType paymentType : paymentTypes) {
					%>
					<option value="<%=paymentType.getId()%>"
						<%=paymentType.getId()
						.equals(agreement.getPaymentType()) ? "selected" : ""%>><%=paymentType.getName()%></option>
					<%
						}
					%>
			</select></td>
		</tr>
		<tr>
			<td class="cLabel">Banco:</td>
			<td><select name="cBank">
					<%
						for (Bank bank : banks) {
					%>
					<option value="<%=bank.getId()%>"
						<%=bank.getId().equals(agreement.getBank()) ? "selected"
						: ""%>><%=bank.getName()%></option>
					<%
						}
					%>
			</select></td>
		</tr>
		<tr>
			<td class="cLabel">Número de Cuenta:</td>
			<td class="cData"><input name="cNumber"
				value="<%=agreement.getAccountNumber()%>"></td>
		</tr>
		<tr>
			<td class="cLabel">Tipo de cuenta:</td>

			<td class="cData"><select name="cAccountType">
					<%
						for (AccountType accountType : accountTypes) {
					%>
					<option value="<%=accountType.getId()%>"
						<%=accountType.getId()
						.equals(agreement.getAccountType()) ? "selected" : ""%>><%=accountType.getName()%></option>
					<%
						}
					%>
			</select>(Pesos Chilenos)</td>
		</tr>
	</table>
	<br> <input type="submit" value="Aceptar">&nbsp;&nbsp; <a class="cCancel"
		href="${pageContext.request.contextPath}/servlet/config/employee/EmployeeManager">Cancelar</a>

</form>
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>