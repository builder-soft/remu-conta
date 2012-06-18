<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="java.util.Date"%>
<%@page import="cl.buildersoft.business.beans.Period"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	List<Period> periods = (List<Period>) request.getAttribute("Periods");
	Date date = null;
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<h1 class="cTitle">Archivos para Previred</h1>

<form
	action="${pageContext.request.contextPath}/servlet/remuneration/process/previred/DownloadPrevired"
	method="POST">
	<table>
		<tr>
			<td class='cLabel'>Período</td>
			<td class='cData'><select name="cPeriod">
					<%
						for (Period period : periods) {
							date = period.getDate();
					%>
					<option value='<%=period.getId()%>'><%=BSWeb.month2Word(date)%>
						de <%=BSWeb.getYear(date)%></option>
					<%
						}
					%>
			</select></td>
		</tr>

	</table>
	<input type="submit" value="Descargar">
</form>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

