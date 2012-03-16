<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.framework.util.BSType"%>
<%@page import="cl.buildersoft.framework.beans.BSField"%>
<%@page import="cl.buildersoft.framework.beans.BSTableConfig"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	BSTableConfig table = (BSTableConfig) session
			.getAttribute("BSTable");
	BSField[] fields = table.getFields();
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<h1 class="cTitle">Creacion de información</h1>
<script>
	function onLoadPage() {

	}
</script>
<%
	String nextServlet = (String) request.getAttribute("Action");
	if ("insert".equalsIgnoreCase(nextServlet)) {
		nextServlet = "InsertRecord";
	} else {
		nextServlet = "UpdateRecord";
	}
%>

<form action="${pageContext.request.contextPath}/servlet/table/<%=nextServlet%>"
	method="post" id="editForm">
	<table>
		<%
			for (BSField field : fields) {
		%>
		<tr>
			<td class="cLabel"><%=field.getLabel()%>:</td>
			<td class="cData"><%=writeHTMLField(field, request)%></td>
		</tr>
		<%
			}
		%>
	</table>
</form>
<input type="button" value="Aceptar"
	onclick="javascript:$('#editForm').submit();">
&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/servlet/table/LoadTable">Cancelar</a>


<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

<%!private String writeHTMLField(BSField field, HttpServletRequest request) {
		String out = "";
		BSType type = field.getType();
		Object value = field.getValue(); // == null ? "" : field.getValue();  
		Integer maxlength = 0;
		String name = field.getName();
		String format = "";
		Integer size = 0;
		String afterInput = "";
		Boolean isPk = field.isPk();
		Boolean isReadOnly = isPk ? Boolean.TRUE : field.isReadonly();

		if (type.equals(BSType.Boolean)) {
			out += "<SELECT name='" + name + "' ";
			out += isReadOnly ? " DISABLED " : "";
			out += ">";

			out += writeOptionHTML("true", "Si", value);
			out += writeOptionHTML("false", "No", value);

			out += "</SELECT>";
		} else {
			if (type.equals(BSType.String)) {
				value = value == null ? "" : value;
				maxlength = field.getLength();
				size = maxlength;
				if (size > 75) {
					size = 75;
				}
			} else if (type.equals(BSType.Date)) {
				maxlength = 10;
				format = BSWeb.getFormatDate(request);
				value = BSWeb.date2String(value, format);
				size = maxlength;
				afterInput = "(formato: " + format + ")";
			} else if (type.equals(BSType.Datetime)) {
				maxlength = 16;
				format = BSWeb.getFormatDatetime(request);
				value = BSWeb.date2String(value, format);
				size = maxlength;
				afterInput = "(formato: " + format + ")";
			} else if (type.equals(BSType.Double)) {
				maxlength = 15;
				format = BSWeb.getFormatNumber(request);
				value = BSWeb.number2String(value, format);
				size = maxlength;
			} else if (type.equals(BSType.Integer)) {
				maxlength = 8;
				format = BSWeb.getFormatNumber(request);
				value = BSWeb.number2String(value, format);
				size = maxlength;
			} else if (type.equals(BSType.Long)) {
				maxlength = 10;
				format = BSWeb.getFormatNumber(request);
				if (isPk && value == null) {
					value = "[Nuevo]";
				} else {
					value = value == null ? "" : BSWeb.number2String(value,
							format);
				}
				//				value = !isPk ? : value;
				size = maxlength;
			}

			out += "<input type='text' name='";
			out += name;
			out += "' ";
			out += "maxlength='" + maxlength + "' ";
			out += isReadOnly ? "READONLY " : "";
			out += "value='" + value + "' ";
			out += "size='" + size + "px'";
			out += ">" + afterInput;
		}
		return out;
	}

	private String writeOptionHTML(String option, String display, Object value) {
		String out = "<OPTION value='" + option + "'";
		out += (value != null && value.toString().equals(option)
				? " selected"
				: "");
		out += ">" + display + "</OPTION>";
		return out;
	}%>
