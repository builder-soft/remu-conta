<%@page import="cl.buildersoft.business.beans.EnterpriseConfig"%>
<%@page import="cl.buildersoft.business.beans.Health"%>
<%@page import="cl.buildersoft.business.beans.ExBoxSystem"%>
<%@page import="cl.buildersoft.business.beans.PFM"%>
<%@page import="cl.buildersoft.business.beans.RagreementAPV"%>
<%@page import="cl.buildersoft.business.beans.Currency"%>
<%@page import="cl.buildersoft.business.beans.APV"%>
<%@page import="cl.buildersoft.business.beans.Employee"%>
<%@page import="cl.buildersoft.business.beans.Agreement"%>
<%@page import="cl.buildersoft.framework.beans.BSCss"%>
<%@page import="cl.buildersoft.framework.beans.BSScript"%>
<%@page import="cl.buildersoft.framework.beans.BSHeadConfig"%>
<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.framework.type.BSFieldType"%>
<%@page import="cl.buildersoft.framework.beans.BSField"%>
<%@page import="cl.buildersoft.framework.beans.BSTableConfig"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	BSTableConfig table = (BSTableConfig) session.getAttribute("BSTable");
	BSField[] fields = table.getFields();

	EnterpriseConfig enterpriseConfig = (EnterpriseConfig) request.getAttribute("EnterpriseConfig");
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>

<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<h1 class="cTitle">Configuración de la empresa</h1>
<form action="${pageContext.request.contextPath}/servlet/config/employee/SaveEnterpriseConfig" method="post" id="editForm">
	<input type="hidden" name="cId" value="<%=request.getParameter("cId")%>">
	<input type="hidden" name="enterprise" value="<%=enterpriseConfig.getEnterprise()%>">
	<table border="0">
			<tr>
				<td  class="cLabel">Mostrar Fecha, UF y UTM</td>
				<td valign='top'><%=paintCheck("showDateUfUtm", enterpriseConfig.getShowDateUfUtm())%></td>				
			</tr>
			<tr>
				<td  class="cLabel">Mostrar Cargo</td>
				<td valign='top'><%=paintCheck("showProfile", enterpriseConfig.getShowProfile())%></td>				
			</tr>
			<tr>
				<td class="cLabel">Mostrar Centro de Costos</td>
				<td valign='top'><%=paintCheck("showCostCenter", enterpriseConfig.getShowCostCenter())%></td>				
			</tr>	
			<tr>
				<td class="cLabel">Mostrar Datos del Contrato</td>
				<td valign='top'><%=paintCheck("showDataAgreement", enterpriseConfig.getShowDataAgreement())%></td>				
			</tr>							
			<tr>
				<td class="cLabel">Mostrar Renta Base</td>
				<td valign='top'><%=paintCheck("showSalary", enterpriseConfig.getShowSalaryRoot())%></td>				
			</tr>
			<tr>
				<td class="cLabel">Mostrar Aportes del Empleador</td>
				<td valign='top'><%=paintCheck("showEmployerBonus", enterpriseConfig.getShowEmployerBonus())%></td>				
			</tr>
			<tr>
				<td class="cLabel">Mostrar Días Trabajados</td>
				<td valign='top'><%=paintCheck("showWorkDay", enterpriseConfig.getShowWorkDay())%></td>				
			</tr>
			<tr>
				<td class="cLabel">Mostrar Alcance Líquido</td>
				<td valign='top'><%=paintCheck("showNetPaymentScope", enterpriseConfig.getShowNetPaymentScope())%></td>				
			</tr>
			<tr>
				<td colspan="2" class="cLabel">Texto al Pie de la Liquidación<br>
				<textarea name="textFootSalary" id="textFootSalary" cols="80" rows="4"><%=enterpriseConfig.getTextFootSalary()%></textarea></td>				
			</tr>	
			<tr>
				<td colspan="2" class="cLabel">Avisar todos los dias lunes al correo <input id="email" name="email" value="<%=enterpriseConfig.getMailNotice()%>"><br>los contratos que vencen dentro de los proximos 15 días.</td>			
			</tr>		
			<tr>
				<td colspan="2" class="cLabel">Se pueden consultar las últimas <select name="cViewLastSettlements">
				<%
					Integer[] items = { 1, 3, 6, 12, 24 };
					for (Integer item : items) {
						out.write("<option value='" + item + "' "
								+ (enterpriseConfig.getViewLastSettlements().equals(item) ? "selected" : "") + ">" + item + "</option>");
					}
				%>
				</select>liquidaciones por la web.</td>
			</tr>		
	</table>
	
<input type="button" value="Aceptar" onclick="javascript:$('#editForm').submit();">	
<a href="${pageContext.request.contextPath}/servlet/table/LoadTable">Cancelar</a>&nbsp;&nbsp;&nbsp;
</form>
<br>


<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

<%!private String paintCheck(String id, boolean check) {
		String isChecked = check ? "checked" : "";
		return "<input type='checkbox' id='" + id + "' name='" + id + "' " + isChecked + ">";
	}%>
