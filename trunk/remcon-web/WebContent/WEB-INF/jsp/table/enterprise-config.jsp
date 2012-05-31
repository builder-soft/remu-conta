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


<form action="${pageContext.request.contextPath}/servlet/config/employee/SavePrevitionalInfo" method="post" id="editForm">
	<input type="hidden" name="cId" value="<%=request.getParameter("cId")%>">
	<table border="0">
			<tr>
				<td  class="cData">Mostrar Fecha, UF y UTM</td>
				<td class="cLabel" valign='top'><%=paintCheck("1",enterConf.getShowDateUfUtm())%></td>				
			</tr>
			<tr>
				<td  class="cData">Mostrar Cargo</td>
				<td class="cLabel" valign='top'><%=paintCheck("2",enterConf.getShowProfile())%></td>				
			</tr>
			<tr>
				<td  class="cData">Mostrar Centro de Costos</td>
				<td class="cLabel" valign='top'><%=paintCheck("3",enterConf.getShowCostCenter())%></td>				
			</tr>	
			<tr>
				<td  class="cData">Mostrar Datos del Contrato</td>
				<td class="cLabel" valign='top'><%=paintCheck("4",enterConf.getShowDataAgreement())%></td>				
			</tr>							
			<tr>
				<td  class="cData">Mostrar Renta Base</td>
				<td class="cLabel" valign='top'><%=paintCheck("5",enterConf.getShowSalaryRoot())%></td>				
			</tr>
			<tr>
				<td  class="cData">Mostrar Aportes del Empleador</td>
				<td class="cLabel" valign='top'><%=paintCheck("6",enterConf.getShowEmployerBonus())%></td>				
			</tr>
			<tr>
				<td  class="cData">Mostrar Días Trabajados</td>
				<td class="cLabel" valign='top'><%=paintCheck("7",enterConf.getShowWorkDay())%></td>				
			</tr>
			<tr>
				<td  class="cData">Mostrar Alcance Líquido</td>
				<td class="cLabel" valign='top'><%=paintCheck("8",enterConf.getShowNetPaymentScope())%></td>				
			</tr>			
			
	</table>
</form>
<br>
<a href="${pageContext.request.contextPath}/servlet/table/LoadTable">Cancelar</a>&nbsp;&nbsp;&nbsp;

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

<%!
	private String paintCheck(String id,boolean check){	
	String isChecked = check ? "checked" : "";
	String sCheck = "<input type='checkbox' id='chb_"+id+"' name='chb_"+id+"' "+isChecked+">";
	return sCheck;
	}
%>
