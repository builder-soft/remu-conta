<%@page import="cl.buildersoft.framework.beans.User"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<%
	ResultSet rs = (ResultSet) request.getAttribute("Data");
	User user = (User) session.getAttribute("User");
%>

<script
	src="${pageContext.request.contextPath}/js/table/table.js?<%=Math.random()%>"></script>
<h1 class="cTitle">Listado de Usuarios</h1>


<form id="frm"
	action="${pageContext.request.contextPath}/servlet/system/user/DeleteUser"
	method="post">
	<!-- 
<form id="frm" action="${pageContext.request.contextPath}/servlet/ShowParameters" method="post">
 -->
	<table class="cList" cellpadding="0" cellspacing="0">
		<tr>
			<td align='center' class='cHeadTD'><input id='mainCheck'
				type='CHECKBOX' onclick='javascript:swapAllCheck(this);'></td>
			<td class='cHeadTD'>Mail</td>
			<td class='cHeadTD'>Nombre</td>
			<%
				if (user.getAdmin()) {
			%>
			<td class='cHeadTD'>Administrador</td>
			<%
				}
			%>
		</tr>
		<%
			String color = null;
			Integer i = 0;
			while (rs.next()) {
				color = i % 2 == 0 ? "cDataTD_odd" : "cDataTD";
		%>
		<tr>
			<td align='center' class='<%=color%>'><input type='CHECKBOX'
				name='cId' value='<%=rs.getString("cId")%>'
				onclick='javascript:swapCheck(this);'></td>
			<td class='<%=color%>'><%=rs.getString("cMail")%></td>
			<td class='<%=color%>'><%=rs.getString("cName")%></td>
			<%
				if (user.getAdmin()) {
			%>
			<td class='<%=color%>' align="center"><%=rs.getBoolean("cAdmin") ? "Si" : "No"%></td>
			<%
				i++;
					}
			%>
		</tr>
		<%
			}
		%>
	</table>
</form>
<br>
<div style='float: left;'>
	<input type='button' value='Nuevo...'
		onclick="javascript:window.location.href='${pageContext.request.contextPath}/servlet/admin/user/UserNew'">
</div>
<div id='MultirecordActions' style='float: left; display: none;'>
	<input type='button' value='Borrar' id='oDelete'
		onclick='javascript:fDelete();'>
</div>

<div id='RecordActions' style='float: left; display: none;'>
	<input type='button' value='Modificar' id='oEdit'
		onclick='javascript:doAction("${pageContext.request.contextPath}/servlet/system/user/UserEdit", "EDIT");'>
		
	<input type='button' value='Cambiar Clave' id='oCh_pass'
		onclick='javascript:doAction("${pageContext.request.contextPath}/servlet/admin/SearchPassword", "CH_PASS");'>

	<input type='button' value='Roles de Usuario' id='oRoldef'
		onclick='javascript:doAction("${pageContext.request.contextPath}/servlet/admin/RoleDef", "ROL_DEF");'>
		
</div>



<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
