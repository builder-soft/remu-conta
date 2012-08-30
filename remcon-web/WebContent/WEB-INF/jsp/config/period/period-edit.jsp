<%@page import="cl.buildersoft.business.beans.Period"%>
<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Period period = (Period) request.getAttribute("Period");
	String periodName = (String) request.getAttribute("PeriodName");
	String statusName = (String) request.getAttribute("StatusName");
	Boolean canEdit = period.getPeriodStatus() == 2L;
	final String DISABLED = "disabled";
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/config/period/period.js">
	
</script>
<h1 class="cTitle">Modificación de datos del período</h1>

<table>
	<tr>
		<td valign="top">
			<form action="/servlet/admin/period/SavePeriod" method="post"
				id="form">
				<table>
					<tr>
						<td class="cLabel">Id:</td>
						<td class="cData"><%=period.getId()%></td>
					</tr>
					<tr>
						<td class="cLabel">Período:</td>
						<td class="cData"><%=periodName%></td>
					</tr>
					<tr>
						<td class="cLabel">Estado:</td>
						<td class="cData"><%=statusName%></td>
					</tr>
					<tr>
						<td class="cLabel">U.F.:</td>
						<td><input name="cUF" id="cUF" <%=canEdit ? "" : DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getUf())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel">Factor de horas extras:</td>
						<td><input name="cOvertimeFactor" id="cOvertimeFactor"
							<%=canEdit ? "" : DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getOvertimeFactor())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel">Sueldo mínimo:</td>
						<td><input name="cMinSalary" id="cMinSalary"
							<%=canEdit ? "" : DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getMinSalary())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel">Tope de gratificación:</td>
						<td><input name="cLimitGratification"
							id="cLimitGratification" <%=canEdit ? "" : DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getLimitGratification())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel">Factor de gratificación:</td>
						<td><input name="cGratificationFactor"
							id="cGratificationFactor" <%=canEdit ? "" : DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getGratificationFactor())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel">Tope IPS:</td>
						<td><input name="cLimitIPS" id="cLimitIPS"
							<%=canEdit ? "" : DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getLimitIPS())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel">Tope Seguro cesantía:</td>
						<td><input name="cLimitInsurance" id="cLimitInsurance"
							<%=canEdit ? "" : DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getLimitInsurance())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel">Tope Sistema de Salud:</td>
						<td><input name="cLimitHealth" id="cLimitHealth"
							<%=canEdit ? "" : DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getLimitHealth())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel">Valor UTM:</td>
						<td><input name="cUTM" id="cUTM" <%=canEdit ? "" : DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getUtm())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel">Días de vacaciones por año:</td>
						<td><input name="cDaysForYear" id="cDaysForYear"
							<%=canEdit ? "" : DISABLED%>
							value="<%=BSWeb.formatInteger(request, period.getDaysForYear())%>"
							onfocus="javascript:integerFocus(this);"
							onblur="javascript:integerBlur(this);"></td>
					</tr>
				</table>
			</form>
		</td>
		<td valign="top" class="cLabel"><p>Considere que estos datos
				serán actualizados en los parametros del sistema. Por otro lado
				tendrá que ejecutar el proceso de calculo nuevamente para que estos
				valores tengan efecto en las remuneraciones.</p></td>
	</tr>
</table>


<button onclick="javascript:submitPeriod()">Aceptar</button>
&nbsp;&nbsp;&nbsp;
<a class="cCancel"
	href="${pageContext.request.contextPath}/servlet/admin/period/PeriodManager">Cancelar</a>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

