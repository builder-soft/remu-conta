<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.Format"%>
<%@page import="cl.buildersoft.framework.util.BSType"%>
<%@page import="cl.buildersoft.framework.beans.BSField"%>
<%@page import="cl.buildersoft.framework.beans.BSTableConfig"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	ResultSet rs = (ResultSet) request.getAttribute("Data");
	BSTableConfig table = (BSTableConfig) session
			.getAttribute("BSTable");
%>

<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>

<script
	src="${pageContext.request.contextPath}/js/table/table.js?r=<%=Math.random()%>"></script>
<h1 class="cTitle">
	<%=table.getTitle()%>
</h1>
<form method="post" action="${pageContext.request.contextPath}/admin/DeleteRecords" id='frm'>
	<table class="cList" cellpadding="0" cellspacing="0">
		<%
			BSField[] fields = table.getFields();
			String name = null;
			String pkName = null;

			Boolean canEdit = table.isCanEdit();
			Boolean canDelete = table.isCanDelete();
			int rowCount = 0;
			Object[] values = null;
			out.println("<tr>");

			if (canDelete) {
				out.print("<td  align='center' class='cHeadTD'><input id='mainCheck' type='CHECKBOX' onclick='javascript:swapCheck(this);'></td>");
			}
			for (BSField field : fields) {

				if (field.isVisible()) {
					out.println("<td class='cHeadTD'>" + field.getLabel()
							+ "</td>");
				}
				if (field.isPk()) {
					pkName = field.getName();
				}
			}
			out.println("</tr>");

			while (rs.next()) {
				values = values2Array(rs, pkName, fields);

				out.print(writeValues(values, fields, rowCount, canEdit,
						ctxPath, request, canDelete));
				rowCount++;
			}

			rs.close();
		%>
	</table>
</form>
<%
	out.print("<br>");
	if (table.isCanInsert()) {
		out.print("<input type='button' value='Nuevo...' onclick=\"javascript:window.location.href='"
				+ ctxPath + "/admin/NewRecord'\">");
	}
	if (table.isCanDelete()) {
		out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id='deleteButton' disabled type='button' value='Borrar' onclick='javascript:deleteRecords();'>");
	}
%>


<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

<%!private String writeValues(Object[] values, BSField[] fields, int rowCount,
			boolean canEdit, String ctxPath, HttpServletRequest request,
			Boolean canDelete) {
		String out = "<tr>";
		Object value = null;
		int i = 1;

		String color = rowCount % 2 != 0 ? "cDataTD" : "cDataTD_odd";

		if (canDelete) {
			out += "<td align='center' class='"
					+ color
					+ "'><input type='CHECKBOX' onclick='javascript:verifyDeleteButton();' name='cId' value='"
					+ values[0] + "'></td>";
		}

		for (BSField field : fields) {
			if (field.isVisible()) {
				value = field.isPk() ? values[0] : values[i++];

				out += "<td class='" + color + "'>";

				if (canEdit) {
					out += "<a href='" + ctxPath + "/admin/SearchRecord?cId="
							+ values[0] + "'>";
				}
				if (field.getType().equals(BSType.Boolean)) {
					Boolean b = (Boolean) value;
					if (b.booleanValue() == Boolean.TRUE) {
						out += "Si";
					} else {
						out += "No";
					}
				} else if (field.getType().equals(BSType.Date)) {
					String format = BSWeb.getFormatDate(request);
					Format formatter = new SimpleDateFormat(format);
					out += formatter.format(value);
				} else if (field.getType().equals(BSType.Datetime)) {
					String format = BSWeb.getFormatDatetime(request);
					Format formatter = new SimpleDateFormat(format);
					out += formatter.format(value);
				} else if (field.getType().equals(BSType.Double)) {
					String format = BSWeb.getFormatNumber(request);
					Format formatter = new DecimalFormat(format);
					out += formatter.format(value);

				} else {
					out += value;
				}
				if (canEdit) {
					out += "</a>";
				}
				out += "</td>";
			}
		}

		out += "</tr>";

		return out;
	}

	private Object[] values2Array(ResultSet rs, String pkName, BSField[] fields)
			throws Exception {
		String name = null;
		Object value = null;
		int i = 1;
		Object[] out = new Object[fields.length];

		for (BSField field : fields) {
			name = field.getName();
			value = rs.getObject(name);

			if (field.getName().equals(pkName)) {
				out[0] = value;
			} else {
				out[i++] = value;
			}

			//			i++;
		}
		return out;
	}%>
