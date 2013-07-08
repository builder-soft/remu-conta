<%@page import="cl.buildersoft.framework.util.BSFactory"%>
<%@page import="cl.buildersoft.framework.service.BSParentChildService"%>
<%@page import="cl.buildersoft.framework.util.crud.BSField"%>
<%@page import="cl.buildersoft.web.servlet.common.parentChild.HttpServletParentChild"%>
<%@page import="cl.buildersoft.framework.beans.parentChild.BSParentChild"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	BSParentChild parentChild = (BSParentChild) session
			.getAttribute(HttpServletParentChild.SESSION_ATTRIBUTE_NAME_PARENT_CHILD);
	BSParentChildService service = BSFactory.getParentChildService();

	String[] parentFieldNames = parentChild.getParentFields();
%>

<%@ include file="/WEB-INF/jsp/common/header2.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu2.jsp"%>


<div class="row-fluid">
	<div class="span12">
		<h1><%=parentChild.getTitle()%></h1>
	</div>
</div>


<%
	int n = (int) Math.ceil(parentFieldNames.length / 3.0);
	int i = 0;
	for (int row = 0; row < n; row++) {
%>
<div class="row-fluid">
	<%
		for (int col = 0; col < 3; col++) {
				String parentName = null;
				BSField field = null;
				if (i < parentFieldNames.length) {
					parentName = parentFieldNames[i++];
					field = service.getParentField(parentChild, parentName);
				}

				if (parentName != null) {
	%>
	<div class="text-left span4"><label><%=field.getLabel()%>
	 xxx</label></div>
	<%
		}
			}
	%>
</div>
<%
	}
%>

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

