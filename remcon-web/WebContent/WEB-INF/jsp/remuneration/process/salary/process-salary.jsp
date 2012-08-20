<%@page import="java.sql.ResultSet"%>
<%@page import="cl.buildersoft.framework.util.BSDateTimeUtil"%>
<%@page import="java.util.Date,cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.business.beans.Period"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Period period = (Period) request.getAttribute("Period");
	Date date = period.getDate();

	ResultSet book = (ResultSet) request.getAttribute("Book");

	String[] colNames = { "Fecha", "Inicio Contrato", "Fin Contrato", "Apellido", "UF",
			"UTM", "Días trabajados", "Pensionado", "cDaysForYear", "cContractType", "cMinSalary", "cSalaryRoot",
			"cSalaryReceived", "cGratificationType", "cLimitGratification", "cGratificationAmount",
			"cGratificationFactor", "cOvertime", "cOvertimeAmount", "cParticipation", "cB01", "cB02", "cB03", "cB04",
			"cB05", "cB06", "cB07", "cB08", "cB09", "cB10", "cExtraPay", "cTotalIncomeTaxable", "cLimitTaxableForecast",
			"cLimitTaxableDismissInsurance", "cLimitInsurance", "cLimitIPS", "cIncome", "cFamilyAssignmentStretch",
			"cFamilyAssignmentCount", "cFamilyAssignmentAmount", "cFamilyRetroactive", "cFeeding", "cMobilization",
			"cBounty", "cHorary", "cRewardAmount", "cMonthNotification", "cIAS", "cProportionalHoliday",
			"cTotalIncomeNotTaxable", "cVoluntaryIndenmization", "cDiscountsId", "cPFMHistory", "cExBoxSystem",
			"cObligatoryQuote", "cAPVAmount", "cAccount2", "cHealthHistory", "cHealthCurrency", "cHealthAmount",
			"cHealthCLP", "cAdditionalHealth", "cHealthLegalQuote", "cAdditionalPFMCurrency", "cAdditionalPFMAmount",
			"cAdditionalPFMCLP", "cLimitHealth", "cHealthQuote", "cInsuranceFactorEmployee",
			"cInsuranceFactorEnterprise", "cUnemploymentInsuranceAmount", "cUniqueTax", "cSubtotalLawfulDiscounts",
			"cAdvance", "cLoanEnterprise", "cD01", "cD02", "cD03", "cD04", "cD05", "cLoanCompensationFund",
			"cSavingCompensationFund", "cJudicialRetention", "cSubtotalOtherDiscounts", "cTotalDiscounts",
			"cNetPaymentScope", "cTotalIncome", "cToPayEmployee", "cInsuranceAmountEnterprise", "cMutualFactor",
			"cPayToMutulEnterprise", "cFamilyAssignmentEnterprise", "cCostEnterprise", "cSimpleLoads",
			"cDisabilityBurdens", "cMaternalLoads", "cSimpleAdjustment" };
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<h1 class="cTitle">Cálculo de Remuneraciones</h1>

<form
	action="${pageContext.request.contextPath}/servlet/remuneration/process/salary/CalculateSalary"
	method="post">
	<table>
		<tr>
			<td class='cLabel'>Período:</td>
			<td class='cData'><%=BSDateTimeUtil.month2Word(date)%> de <%=BSDateTimeUtil.getYear(date)%></td>
		</tr>
		<!-- 
<tr>
<td class='cLabel'>Empresa:</td>
<td class='cData'><select/></td>
</tr>
-->
	</table>
	<button type="submit" >Calcular</button>	
	<button type="button">Descargar como CSV</button>
	
</form>

<script type="text/javascript">
<!--
function onLoadPage(){
	document.getElementById("divScroll").style.height = (screen.availHeight-350) + "px";
	document.getElementById("divScroll").style.width = (screen.availWidth-50)+ "px";
	$("#divScroll").fadeIn(speed);
		
}
//-->
</script>

<div id="divScroll" style="display: none;overflow: auto;position: relative;" >
<%=BSWeb.showResultSet(book, colNames)%>
</div>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

