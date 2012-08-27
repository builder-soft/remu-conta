<%@page import="cl.buildersoft.framework.util.BSDateTimeUtil"%>
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
					<option value='<%=period.getId()%>'><%=BSDateTimeUtil.month2Word(date)%>
						de <%=BSDateTimeUtil.getYear(date)%></option>
					<%
						}
					%>
			</select></td>
		</tr>

	</table>
	<button type="submit">Descargar</button>
</form>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

