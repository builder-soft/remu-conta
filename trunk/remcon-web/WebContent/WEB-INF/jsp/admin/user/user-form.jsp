<%@page import="cl.buildersoft.framework.beans.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<%
	User me = (User) session.getAttribute("User");
%>
<h1 class="cTitle">Datos de usuario</h1>

 
<form action="${pageContext.request.contextPath}/servlet/admin/user/UserSave" method="post">

<!-- 
<form action="${pageContext.request.contextPath}/servlet/ShowParameters" method="post">
 -->
	<table>
		<tr>
			<td class="cLabel">Id:</td>
			<td class="cData">Nuevo</td>
		</tr>
		<tr>
			<td class="cLabel">Mail:</td>
			<td class="cData"><input type="text" name="cMail" size="50px"
				maxlength='50'></td>
		</tr>
		<tr>
			<td class="cLabel">Nombre:</td>
			<td class="cData"><input type="text" name="cName" size="100px"
				maxlength='100'></td>
		</tr>
		<%
			if (me.getAdmin()) {
		%>
		<tr>
			<td class="cLabel">Usuario administrador:</td>
			<td class="cData"><input type="checkbox" name="cAdmin"></td>
		</tr>
		<%
			}
		%>

	</table>
	<input type="submit" value="Aceptar">
</form>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

