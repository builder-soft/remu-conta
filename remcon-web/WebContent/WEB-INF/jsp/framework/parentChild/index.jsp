<%@page import="cl.buildersoft.framework.dataType.BSDataTypeUtil"%>
<%@page import="cl.buildersoft.framework.dataType.BSDataType"%>
<%@page import="cl.buildersoft.framework.util.crud.BSAction"%>
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
	String[] childFieldNames = parentChild.getChildFields();
	BSAction[] parentActions = parentChild.getParentActions();
%>

<%@ include file="/WEB-INF/jsp/common/header2.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu2.jsp"%>

<%@ include file="/WEB-INF/jsp/framework/functions.jsp"%>


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
	<div class="text-left span4">
		<label><%=field.getLabel()%>&nbsp;: <%=getFieldInput(field, request)%></label>
	</div>
	<%
		}
			}
	%>
</div>
<%
	}
%>



<div class="row-fluid">
	<%
		int spanLen = (int) Math.ceil(6 / parentActions.length);
		for (BSAction action : parentActions) {
	%>
	<div class="span<%=spanLen%>">
		<button type="button" class="btn btn-block" <%if (action.getFunction() != null) {%>
			onclick="javascript:try{<%=action.getFunction()%>();}catch(e){alert(e);}" <%}%>>
			<%=action.getLabel()%></button>
	</div>
	<%
		}
	%>
</div>
<hr>
<!-- CHILD TABLE FROM HERE -->


<div class="row-fluid">
	<div class="span12">
		<h2><%=parentChild.getTitleChild()%></h2>
	</div>
</div>


<div class="row-fluid">
	<div class="span12">
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<td align="center"><input type="CHECKBOX"></td>
					<%
						for (i = 0; i < childFieldNames.length; i++) {
					%>
					<td align="left"><%=parentChild.getChildFieldsMap().get(childFieldNames[i]).getLabel()%></td>
					<%
						}
					%>
				</tr>
			</thead>
			<!-- 
	<tbody>
		<tr>
			<td align="center" class=""><input type="checkbox" onclick="javascript:swapCheck(this);" value="1" name="cId"></td>
			<td align="left">Dato</td>
		</tr>
	</tbody>
	 -->
		</table>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/common/footer2.jsp"%>

<script src="${pageContext.request.contextPath}/js/framework/parent-child/index.js?<%=Math.random() %>"></script>


<%!private String getFieldInput(BSField field, HttpServletRequest request) {
		//BSDataType type = field.getType();
		String out = "";
		if (field.getFKInfo() == null) {
			out = writeHTMLField(field, request);
			
			/**
			out = "<input " + getNameInput(field) + " value='";
			Object value = field.toString();
			out += value == null ? "" : value.toString();
			out += "'>";
			*/
		} else {
			out += getFKSelect(field, true);
/**			
			out += "<select " + getNameInput(field) + ">";
			List<Object[]> dataList = field.getFKData();
			out += "<option vaue=''>- Seleccione opción -</option>";
			for (Object[] dataRow : dataList) {
				out += "<option vaue='" + dataRow[0] + "'>" + dataRow[1] + "</option>";
			}
			out += "</select>";
			*/
		}
		return out;
	}

	private String getNameInput(BSField field) {
		return " id='" + field.getName() + "' name='" + field.getName() + "' ";
	}%>