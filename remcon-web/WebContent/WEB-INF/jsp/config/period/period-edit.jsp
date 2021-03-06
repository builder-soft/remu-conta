<%@page import="cl.buildersoft.business.beans.Period"%>
<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Period period = (Period) request.getAttribute("Period");
	String periodName = (String) request.getAttribute("PeriodName");
	String statusName = (String) request.getAttribute("StatusName");
	Boolean canEdit = period.getPeriodStatus() == 2L;
	final String DISABLED = canEdit ? "" : "disabled";
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/config/period/period.js?<%=Math.random()%>">
	
</script>
<h1 class="cTitle">Modificaci�n de datos del per�odo</h1>

<table border="0">
	<tr>
		<td valign="top">
			<!-- 
				action="${pageContext.request.contextPath}/servlet/admin/period/SavePeriod"
				action="${pageContext.request.contextPath}/servlet/ShowParameters"
				 -->
			<form
				action="${pageContext.request.contextPath}/servlet/admin/period/SavePeriod"
				method="post" id="form">
				<input type="hidden" name="cId" value="<%=period.getId()%>">
				<table>
					<tr>
						<td class="cLabel">Id:</td>
						<td class="cData"><%=period.getId()%></td>
					</tr>
					<tr>
						<td class="cLabel">Per�odo:</td>
						<td class="cData"><%=periodName%></td>
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
						<td class="cLabel">Sueldo m�nimo:</td>
						<td><input name="cMinSalary" id="cMinSalary"
							<%=DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getMinSalary())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel">Tope de gratificaci�n:</td>
						<td><input name="cLimitGratification"
							id="cLimitGratification" <%=DISABLED%>
							value="<%=BSWeb.formatDouble(request, period.getLimitGratification())%>"
							onfocus="javascript:doubleFocus(this);"
							onblur="javascript:doubleBlur(this);"></td>
					</tr>
					<tr>
						<td class="cLabel">Factor de gratificaci�n:</td>
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
						<td class="cLabel">Tope Seguro cesant�a:</td>
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
						<td class="cLabel" nowrap>D�as de vacaciones por a�o:</td>
						<td><input name="cDaysForYear" id="cDaysForYear"
							<%=DISABLED%>
							value="<%=BSWeb.formatInteger(request, period.getDaysForYear())%>"
							onfocus="javascript:integerFocus(this);"
							onblur="javascript:integerBlur(this);"></td>
					</tr>
				</table>
			</form>
		</td>
		</tr><tr>
		
		<td valign="top" style="width:50%" class="cWarning"><p style="border:1px solid #000000;">Considere que estos datos
				ser�n actualizados en los parametros del sistema. Por otro lado
				tendr� que ejecutar el proceso de calculo nuevamente para que estos
				valores tengan efecto en las remuneraciones.</p></td>
	</tr>
</table>


<button onclick="javascript:submitPeriod()">Aceptar</button>
&nbsp;&nbsp;&nbsp;
<a class="cCancel"
	href="${pageContext.request.contextPath}/servlet/admin/period/PeriodManager">Cancelar</a>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

