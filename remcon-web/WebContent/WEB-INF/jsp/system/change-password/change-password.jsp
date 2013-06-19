<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/header2.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu2.jsp"%>
<%
	String nextAction = "/servlet/system/changepassword/ChangePassword";
	String cancelAction = "/servlet/system/user/UserManager";
	Boolean passwordIsNull = (Boolean) request.getAttribute("PASS_IS_NULL");

	Object id = request.getParameter("cId");
	if (id == null) {
		id = (Long) request.getAttribute("cId");
		nextAction += "?GoHome";
		cancelAction = "/servlet/Home";
	}
%>

<div class="row-fluid">
	<div class="span12">
		<h1>Cambio de clave</h1>
	</div>
</div>


<form action="${pageContext.request.contextPath}<%=nextAction%>" method="post">
	<input type="hidden" name="cId" value="<%=id%>">

	<%
		if (!passwordIsNull) {
	%>
	<div class="row-fluid">
		<div class="span12">

			<label>Clave anterior: <input type="password" name="OldPassword">
			</label>
		</div>
	</div>
	<%
		}
	%>

	<div class="row-fluid">
		<div class="span12">
			<label>Nueva clave: <input type="password" name="NewPassword"></label>
		</div>
	</div>


	<div class="row-fluid">
		<div class="span12">
			<label>Confirme clave:
				<td><input type="password" name="CommitPassword">
			</label>
		</div>
	</div>

	<button type="submit" class="btn btn-primary">Confirmar</button>
	<a class="btn" href="${pageContext.request.contextPath}<%=cancelAction%>">Cancelar</a>
</form>

<%@ include file="/WEB-INF/jsp/common/footer2.jsp"%>

