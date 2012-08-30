<%@page import="cl.buildersoft.business.beans.Period"%>
<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Period period = (Period) request.getAttribute("Period");
	String periodName = (String) request.getAttribute("PeriodName");
	String statusName = (String) request.getAttribute("StatusName");
	Boolean canEdit = true; //period.getPeriodStatus() == 2L;
	final String DISABLED = canEdit ? "" : "disabled";
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/config/period/period.js?<%=Math.random()%>">
	
</script>
<h1 class="cTitle">Duplicando período</h1>

			<!-- 
				action="${pageContext.request.contextPath}/servlet/admin/period/SavePeriod"
				action="${pageContext.request.contextPath}/servlet/ShowParameters"
				 -->
			<form
				action="${pageContext.request.contextPath}/servlet/admin/period/SavePeriod"
				method="post" id="form">
				
				<table>
					<tr>
						<td class="cLabel">Id:</td>
						<td class="cData"><%=period.getId()%></td>
					</tr>
					<tr>
						<td class="cLabel">Período origen <%=periodName%>:</td>
						<td><%@ include file="/WEB-INF/jsp/config/period/period-select.jsp"%></td>
					</tr>
					<tr>
						<td class="cLabel">Estado:</td>
						<td class="cData"><%=statusName%></td>
					</tr>
					<tr>
						<td class="cLabel">U.F.:</td>
						<td><input name="cUF" id="cUF" <%=DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getUf())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel">Factor de horas extras:</td>
						<td><input name="cOvertimeFactor" id="cOvertimeFactor"
							<%=DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getOvertimeFactor())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel">Sueldo mínimo:</td>
						<td><input name="cMinSalary" id="cMinSalary"
							<%=DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getMinSalary())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel">Tope de gratificación:</td>
						<td><input name="cLimitGratification"
							id="cLimitGratification" <%=DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getLimitGratification())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel">Factor de gratificación:</td>
						<td><input name="cGratificationFactor"
							id="cGratificationFactor" <%=DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getGratificationFactor())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel">Tope IPS:</td>
						<td><input name="cLimitIPS" id="cLimitIPS"
							<%=DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getLimitIPS())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel">Tope Seguro cesantía:</td>
						<td><input name="cLimitInsurance" id="cLimitInsurance"
							<%=DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getLimitInsurance())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel">Tope Sistema de Salud:</td>
						<td><input name="cLimitHealth" id="cLimitHealth"
							<%=DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getLimitHealth())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel">Valor UTM:</td>
						<td><input name="cUTM" id="cUTM" <%=DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getUtm())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel" nowrap>Días de vacaciones por año:</td>
						<td><input name="cDaysForYear" id="cDaysForYear"
							<%=DISABLED%>
							value="<%=BSWeb.formatInteger(request, period.getDaysForYear())%>"
							onfocus="javascript:integerFocus(this);"
							onblur="javascript:integerBlur(this);"></td>
					</tr>
				</table>
			</form>


<button onclick="javascript:submitPeriod_()">Aceptar</button>
&nbsp;&nbsp;&nbsp;
<a class="cCancel"
	href="${pageContext.request.contextPath}/servlet/admin/period/PeriodManager">Cancelar</a>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

