<%@page import="cl.buildersoft.web.servlet.common.parentChild.HttpServletParentChild"%>
<%@page import="cl.buildersoft.framework.beans.parentChild.BSParentChild"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	BSParentChild parentChild = (BSParentChild) session
			.getAttribute(HttpServletParentChild.SESSION_ATTRIBUTE_NAME_PARENT_CHILD);
%>

<%@ include file="/WEB-INF/jsp/common/header2.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu2.jsp"%>


<div class="row-fluid">
	<div class="span12">
		<h1><%=parentChild.getTitle()%></h1>
	</div>
</div>

<div class="row-fluid">
	<div class="text-center span1 btn">son <%=parentChild.getParentFields().length %></div>
	<div class="text-center span2 btn">Hola</div>
	<div class="span1 btn"></div>
	<div class="text-center span8 btn">Mundo</div>
</div>


<table class="table table-bordered table-striped">
	<thead>
		<tr>
			<td align="center"><input type="CHECKBOX" onclick="javascript:swapAllCheck(this);" id="mainCheck"></td>
			<td align="left">Llave</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center" class=""><input type="checkbox" onclick="javascript:swapCheck(this);" value="1" name="cId"></td>
			<td align="left">Dato</td>
		</tr>
	</tbody>
</table>

<%@ include file="/WEB-INF/jsp/common/footer2.jsp"%>
