<%@page import="cl.buildersoft.framework.util.BSDateTimeUtil"%>
<%@page import="cl.buildersoft.framework.beans.BSCss"%>
<%@page import="cl.buildersoft.framework.beans.BSScript"%>
<%@page import="cl.buildersoft.framework.beans.BSHeadConfig"%>
<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.framework.type.BSFieldType"%>
<%@page import="cl.buildersoft.framework.beans.BSField"%>
<%@page import="cl.buildersoft.framework.beans.BSTableConfig"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	BSTableConfig table = (BSTableConfig) session.getAttribute("BSTable");
	BSField[] fields = table.getFields();
%>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<%
	BSHeadConfig head = (BSHeadConfig) session.getAttribute("BSHead");
	if (head != null) {
		BSScript script = head.getScript();
		BSCss css = head.getCss();
		for (String oneScript : script.getListScriptNames()) {
			out.print("<script src='" + request.getContextPath() + script.getPath() + oneScript + ".js'></script>");
		}

		for (String oneCss : css.getListCssNames()) {
			out.print("<LINK rel='stylesheet' type='text/css' src='" + request.getContextPath() + css.getPath() + oneCss
					+ ".css'/>");
		}
	}
%>
<%@ include file="/WEB-INF/jsp/common/menu.jsp"%>
<h1 class="cTitle">Detalle de información</h1>
<%
	String nextServlet = (String) request.getAttribute("Action");
	if ("insert".equalsIgnoreCase(nextServlet) || table.usingView()) {
		nextServlet = "InsertRecord";
	} else {
		nextServlet = "UpdateRecord";
	}
%>

<form
	action="${pageContext.request.contextPath}/servlet/table/<%=nextServlet%>"
	method="post" id="editForm">
	<table>
		<%
			for (BSField field : fields) {
		%>
		<tr>
			<td class="cLabel" valign='top'><%=field.getLabel()%>:</td>
			<td class="cData"><%=writeHTMLField(field, request)%></td>
		</tr>
		<%
			}
		%>
	</table>
</form>
<button type="button" onclick="javascript:$('#editForm').submit();">Aceptar</button>
&nbsp;&nbsp;&nbsp;
<a class="cCancel" href="${pageContext.request.contextPath}/servlet/table/LoadTable">Cancelar</a>


<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>

<%!private static String NEW = "[Nuevo]";

	private String writeHTMLField(BSField field, HttpServletRequest request) {
		String out = "";
		BSFieldType type = field.getType();
		Object value = field.getValue();
		Integer maxlength = 0;
		String name = field.getName();
		String format = "";
		Integer size = 0;
		String afterInput = "";
		Boolean isPk = field.isPK();
		//Boolean isNew = Boolean.FALSE;
		Boolean isReadOnly = isPk ? Boolean.TRUE : field.isReadonly();
		String validationOnBlur = field.getValidationOnBlur() != null ? field.getValidationOnBlur() : "";

		if (isFK(field)) {
			out += getFKSelect(field);
		} else {
			if (type.equals(BSFieldType.Boolean)) {
				out += "<SELECT name='" + name + "' ";
				out += isReadOnly ? " DISABLED " : "";
				out += ">";

				out += writeOptionHTML("true", "Si", value);
				out += writeOptionHTML("false", "No", value);

				out += "</SELECT>";
			} else {
				if (type.equals(BSFieldType.String)) {
					value = value == null ? "" : value;
					maxlength = field.getLength();
					size = maxlength;
					if (size > 75) {
						size = 75;
					}

				} else if (type.equals(BSFieldType.Date)) {
					maxlength = 10;
					format = BSDateTimeUtil.getFormatDate(request);
					value = BSDateTimeUtil.date2String(value, format);
					size = maxlength;
					afterInput = "(formato: " + format + ")";

				} else if (type.equals(BSFieldType.Timestamp)) {
					maxlength = 16;
					format = BSDateTimeUtil.getFormatDatetime(request);
					value = BSDateTimeUtil.date2String(value, format);
					size = maxlength;
					afterInput = "(formato: " + format + ")";
				} else if (type.equals(BSFieldType.Double)) {
					maxlength = 15;
					format = BSWeb.getFormatDecimal(request);
					value = BSWeb.number2String(value, format);
					size = maxlength;
				} else if (type.equals(BSFieldType.Integer)) {
					maxlength = 8;
					format = BSWeb.getFormatInteger(request);
					value = BSWeb.number2String(value, format);
					size = maxlength;
				} else if (type.equals(BSFieldType.Long)) {
					maxlength = 10;
					format = BSWeb.getFormatInteger(request);
					if (isPk && value == null) {
						value = NEW;
						//isNew = Boolean.TRUE;
					} else {
						value = value == null ? "" : BSWeb.number2String(value, format);
					}
					size = maxlength;
				}

				out += drawInputText("text", name, maxlength, isReadOnly, value, size, afterInput, validationOnBlur, isPk);
			}
		}
		return out;
	}

	private String writeOptionHTML(String option, String display, Object value) {
		String out = "<OPTION value='" + option + "'";
		out += (value != null && value.toString().equals(option) ? " selected" : "");
		out += ">" + display + "</OPTION>";
		return out;
	}

	private Boolean isFK(BSField field) {
		Boolean out = Boolean.FALSE;
		List<Object[]> data = field.getFKData();
		out = data != null;
		return out;
	}

	private String drawInputText(String type, String name, Integer maxlength, Boolean isReadonly, Object value, Integer size,
			String afterInput, String validationOnBlur, Boolean isPk) {
		String out = "";

		if (isPk) {
			out += "<span class='cData'>" + value + "</span>";
			type = isPk ? "hidden" : type;
			//			value = value.equals(NEW) ? "" : value;
		}

		// isPk && value == null

		out += "<input type='" + type + "' name='";
		out += name;
		out += "' ";
		out += "maxlength='" + maxlength + "' ";
		out += isReadonly ? "READONLY " : "";
		out += "value='" + (value.equals(NEW) ? "0" : value) + "' ";
		out += "size='" + size + "px' ";
		if (!"".equals(validationOnBlur)) {
			out += "onBlur='javascript:" + validationOnBlur + "(this)'";
		}
		out += ">&nbsp;" + afterInput;

		return out;
	}

	private String getFKSelect(BSField field) {
		String name = field.getName();
		Object value = field.getValue();

		String out = "<select name='";
		out += name + "'>";
		List<Object[]> data = field.getFKData();
		for (Object[] row : data) {
			out += "<option value='" + row[0] + "' ";
			if (value != null) {
				out += value.equals(row[0]) ? " selected " : "";
			}
			out += ">" + row[1] + "</option>";
		}
		out += "</select>";
		return out;
	}%>
