
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu2.jsp"%>

<h1 class="cTitle">Lista de parámetros</h1>

<%
	Map<Long, Map<String, Object>> data = (Map<Long, Map<String, Object>>) request
			.getAttribute("Data");

	//out.print(rs.isClosed());
%>
<a class="clabel">
<%

Iterator it = data.entrySet().iterator();

while (it.hasNext()) {
	Object r = it.next();
	%>
	<%=r%>
<%
}
%>
 </a>
<hr>
<table class="cList" cellpadding="0" cellspacing="0">
	<tr>
		<td class='cHeadTD'>encabezado</td>
	</tr>
	<tr>
		<td class='cDataTD'>dato</td>
	</tr>
	<tr>
		<td class='cDataTD_odd'>dato</td>
	</tr>
</table>


<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

