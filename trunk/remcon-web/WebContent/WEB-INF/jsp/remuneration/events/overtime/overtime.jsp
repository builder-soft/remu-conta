<%@page import="cl.buildersoft.business.service.impl.PeriodServiceImpl"%>
<%@page import="cl.buildersoft.business.service.PeriodService"%>
<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.business.beans.Overtime"%>
<%@page import="cl.buildersoft.framework.util.BSConfig"%>
<%@page import="cl.buildersoft.business.beans.Employee"%>
<%@page import="cl.buildersoft.business.beans.Period"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Period period = (Period) request.getAttribute("Period");
	Employee employee = (Employee) request.getAttribute("Employee");
	List<Overtime> overtimes = (List<Overtime>) request.getAttribute("Overtimes");
	
	Integer lastDayMonth = lastDayMonth(period);
	
%>
<%!
private Integer lastDayMonth(Period period){
	PeriodService ps = new PeriodServiceImpl();
	return ps.lastDayMonth(period);
}
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<script
	src="${pageContext.request.contextPath}/js/remuneration/events/overtime/overtime.js?<%=Math.random()%>"></script>

<h1 class="cTitle">Horas Extras</h1>

<span class="cLabel">Período:&nbsp;&nbsp;</span>
<span class="cData"><%=BSWeb.month2Word(period.getDate())%></span>
<br>
<%@ include file="/WEB-INF/jsp/config/employee/employee-information.jsp"%>
<br>
 
<table class="cList" cellpadding="0" cellspacing="0" border="1"
	id="overtimeTable">
	<tr>
		<td class='cHeadTD'>Sel.</td>
		<td class='cHeadTD'>Fecha</td>
		<td class='cHeadTD'>Porcentaje</td>
		<td class='cHeadTD'>Cantidad</td>
	</tr>

<%
Integer index = 0;
for(Overtime overtime : overtimes){
	String color = index++ % 2 == 0 ? "cDataTD": "cDataTD_odd";
%>
	<tr>
		<td class='<%=color%>'><input name="overtimeId" type="radio" value="<%=overtime.getId() %>" <%=index==1?"checked":""%> 
										onclick="javascript:selectRadio(<%=index%>)"></td>
		<td class='<%=color%>'><%=BSWeb.date2String(request, overtime.getDate()) %></td>
		<td class='<%=color%>'><%=overtime.getPercent() %></td>
		<td class='<%=color%>'><%=overtime.getAmount() %></td>
	</tr>

<%	
}
%>

</table>
<br>
<div id="defaultButtons">
	<input type="Button" value="Agregar" onclick="javascript:add(<%=lastDayMonth%>);"> 
	
	<input type="Button" value="Modificar" id="modify" onclick="javascript:editOvertime(<%=lastDayMonth%>);">
	 
	<input type="Button" value="Eliminar" id="erase"> 
	
	<a href="${pageContext.request.contextPath}/servlet/remuneration/events/overtime/OvertimeServlet">Volver</a>
</div>
<div id="commitButtons" style="float: left; display: none;">
	<input type="Button" value="Aceptar" onclick="javascript:commitAdd();">
	<input type="Button" value="Cancelar" onclick="javascript:cancel();">
</div>

<form id="frm" method="post">
	<input type="hidden" name="cId" value="<%=employee.getId()%>">
	<input type="hidden" name="cPeriod" value="<%=period.getId()%>">

	<input type="hidden" name="cDate" id="cDate"> <input
		type="hidden" name="cPercent" id="cPercent"> <input
		type="hidden" name="cAmount" id="cAmount">
</form>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

