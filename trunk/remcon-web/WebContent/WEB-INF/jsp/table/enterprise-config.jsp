<%@page import="cl.buildersoft.framework.beans.EnterpriseConfig"%>
<%@page import="cl.buildersoft.framework.beans.Health"%>
<%@page import="cl.buildersoft.framework.beans.ExBoxSystem"%>
<%@page import="cl.buildersoft.framework.beans.PFM"%>
<%@page import="cl.buildersoft.framework.beans.RagreementAPV"%>
<%@page import="cl.buildersoft.framework.beans.Currency"%>
<%@page import="cl.buildersoft.framework.beans.APV"%>
<%@page import="cl.buildersoft.framework.beans.Employee"%>
<%@page import="cl.buildersoft.framework.beans.Agreement"%>
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
	
	EnterpriseConfig enterConf = (EnterpriseConfig)request.getAttribute("enterpriseConfig");
	Employee employee = (Employee) request.getAttribute("Employee");

%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>

<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<h1 class="cTitle">Informacion de la configuracion de la empresa</h1>
<%
	String nextServlet = (String) request.getAttribute("Action");
	
	if ("insert".equalsIgnoreCase(nextServlet)) {
		nextServlet = "InsertRecord";
	} else {
		nextServlet = "UpdateRecord";
	}
%>
<!--config/employee/SavePrevitionalInfo-->


<form action="${pageContext.request.contextPath}/servlet/config/employee/SaveEnterpriseConfig" method="post" id="editForm">
	<input type="hidden" name="cId" value="<%=request.getParameter("cId")%>">
	<input type="hidden" name="enterprise" value="<%=enterConf.getEnterprise()%>">
	<table border="0">
			<tr>
				<td  class="cData">Mostrar Fecha, UF y UTM</td>
				<td class="cLabel" valign='top'><%=paintCheck("showDateUfUtm",enterConf.getShowDateUfUtm())%></td>				
			</tr>
			<tr>
				<td  class="cData">Mostrar Cargo</td>
				<td class="cLabel" valign='top'><%=paintCheck("showProfile",enterConf.getShowProfile())%></td>				
			</tr>
			<tr>
				<td  class="cData">Mostrar Centro de Costos</td>
				<td class="cLabel" valign='top'><%=paintCheck("showCostCenter",enterConf.getShowCostCenter())%></td>				
			</tr>	
			<tr>
				<td  class="cData">Mostrar Datos del Contrato</td>
				<td class="cLabel" valign='top'><%=paintCheck("showDataAgreement",enterConf.getShowDataAgreement())%></td>				
			</tr>							
			<tr>
				<td  class="cData">Mostrar Renta Base</td>
				<td class="cLabel" valign='top'><%=paintCheck("showSalary",enterConf.getShowSalaryRoot())%></td>				
			</tr>
			<tr>
				<td  class="cData">Mostrar Aportes del Empleador</td>
				<td class="cLabel" valign='top'><%=paintCheck("showEmployerBonus",enterConf.getShowEmployerBonus())%></td>				
			</tr>
			<tr>
				<td  class="cData">Mostrar Días Trabajados</td>
				<td class="cLabel" valign='top'><%=paintCheck("showWorkDay",enterConf.getShowWorkDay())%></td>				
			</tr>
			<tr>
				<td  class="cData">Mostrar Alcance Líquido</td>
				<td class="cLabel" valign='top'><%=paintCheck("showNetPaymentScope",enterConf.getShowNetPaymentScope())%></td>				
			</tr>
			<tr>
				<td class="cData" colspan="2">
				<td class="cData">Texto al Pié de la Liquidación<br><textarea name="textFootSalary" id="textFootSalary" cols="80" rows="4"><%=enterConf.getTextFootSalary()%></textarea></td>
				<td></td>
			</tr>	
			<tr>
				<td colspan="2"></td>
				<td class="cData">Avisar todos los dias lunes a este correo, los contratos que vencen dentro de los proximos 15 días.</td>			
			</tr>		
			<tr>
				<td colspan="2"></td>
				<td class="cData">Email: <input id="email" name="email" value="<%= enterConf.getEmail() !=null ? enterConf.getEmail():""%>"></td>
			</tr>
	</table>
	
<input type="button" value="Aceptar" onclick="javascript:$('#editForm').submit();">	
<a href="${pageContext.request.contextPath}/servlet/table/LoadTable">Cancelar</a>&nbsp;&nbsp;&nbsp;
</form>
<br>


<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

<%!
	private String paintCheck(String id,boolean check){	
	String isChecked = check ? "checked" : "";
	String sCheck = "<input type='checkbox' id='"+id+"' name='"+id+"' "+isChecked+">";
	return sCheck;
	}
%>
