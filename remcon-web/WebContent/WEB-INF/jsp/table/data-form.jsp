<%@page import="cl.buildersoft.framework.dataType.BSLong"%>
<%@page import="cl.buildersoft.framework.dataType.BSTimestamp"%>
<%@page import="cl.buildersoft.framework.dataType.BSString"%>
<%@page import="cl.buildersoft.framework.dataType.BSBoolean"%>
<%@page import="cl.buildersoft.framework.dataType.BSDate"%>
<%@page import="cl.buildersoft.framework.dataType.BSInteger"%>
<%@page import="cl.buildersoft.framework.dataType.BSDouble"%>
<%@page import="cl.buildersoft.framework.dataType.BSDataType"%>
<%@page import="cl.buildersoft.web.servlet.common.crud.LoadTable"%>
<%@page import="cl.buildersoft.framework.util.BSDateTimeUtil"%>
<%@page import="cl.buildersoft.framework.beans.BSCss"%>
<%@page import="cl.buildersoft.framework.beans.BSScript"%>
<%@page import="cl.buildersoft.framework.beans.BSHeadConfig"%>
<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.framework.util.crud.BSField"%>
<%@page import="cl.buildersoft.framework.util.crud.BSTableConfig"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	BSTableConfig table = (BSTableConfig) session.getAttribute("BSTable");
//Connection conn = (Connection) request.getAttribute("Conn");

BSField[] fields = table.getFields();
%>
<%@ include file="/WEB-INF/jsp/common/header2.jsp"%>
<%
	BSHeadConfig head = (BSHeadConfig) session.getAttribute("BSHead");
	if (head != null) {
		BSScript script = head.getScript();
		BSCss css = head.getCss();
		for (String oneScript : script.getListScriptNames()) {
			out.print("<script src='" + request.getContextPath() + script.getPath() + oneScript + ".js'></script>");
		}
/**
		for (String oneCss : css.getListCssNames()) {
	out.print("<LINK rel='stylesheet' type='text/css' src='" + request.getContextPath() + css.getPath() + oneCss
	+ ".css'/>");
		}
*/
	}
%>
<script type="text/javascript">
<!--
	function sendForm() {
		var msg = null;
<%String fieldName=null;
	String html = "";
	BSDataType type=null;
	for (BSField field : fields) {
		type = field.getType();
		fieldName = field.getName();
		
		if(type  instanceof BSDouble || type instanceof BSInteger || type instanceof BSDate){		
			if(type instanceof BSDouble){
				html = "var " + fieldName + " = formated2double(document.getElementById('"+fieldName+"').value);\n";
			}
			if(type  instanceof BSInteger){
				html = "var " + fieldName + " = formated2integer(document.getElementById('"+fieldName+"').value);\n";
			}
			if(type  instanceof BSDate){
				html = "var " + fieldName + " = isDate(document.getElementById('"+fieldName+"').value);\n";
				html += fieldName + " = " + fieldName +"?"+fieldName +":null;\n";
			}

			html += "if (" + fieldName + " == null) {\n";
			html += "   msg = 'El campo "+ field.getLabel() + " no es valido';\n";
			html += "}\n";
}%>
	
<%=html%>
	
<%}%>
	if (msg != null) {
			alert(msg);
		} else {
<%html = "";
for (BSField field : fields) {
		type = field.getType();
		fieldName = field.getName();
		if(type  instanceof BSDouble || type instanceof BSInteger){
			html += "document.getElementById('"+fieldName+"').value = "+fieldName+";\n";
		}%>
	
<%}%>
	
<%=html%>
	document.getElementById("editForm").submit();
		}
	}
</script>
<%@ include file="/WEB-INF/jsp/common/menu2.jsp"%>
<h1 class="cTitle">Detalle de información</h1>
<%
	String nextServlet = (String) request.getAttribute("Action");
	if ("insert".equalsIgnoreCase(nextServlet) || table.usingView()) {
		nextServlet = "InsertRecord";
	} else {
		nextServlet = "UpdateRecord";
	}
%>

<form action="${pageContext.request.contextPath}/servlet/common/crud/<%=nextServlet%>" method="post" id="editForm">

	<%
		for (BSField field : fields) {
	%>
	<div class='row-fluid'>
		<div class='span2'><%=field.getLabel()%>:
		</div>
		<div class="span10"><%=writeHTMLField(field, request)%></div>
	</div>
	<%
		}
	%>

</form>

<div class='row-fluid'>
	<div class='span12'>
		<button type="button" class="btn btn-primary" onclick="javascript:sendForm()">Aceptar</button>
		&nbsp;&nbsp;&nbsp; <a class="btn" href="${pageContext.request.contextPath}<%=LoadTable.URL%>">Cancelar</a>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/common/footer2.jsp"%>

<%!private static String NEW = "[Nuevo]";

	private String writeHTMLField(BSField field, HttpServletRequest request) {
		String out = "";
		BSDataType type = field.getType();
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
			if (type instanceof BSBoolean) {
				out += "<SELECT name='" + name + "' ";
				out += isReadOnly ? " DISABLED " : "";
				out += ">";

				out += writeOptionHTML("true", "Si", value);
				out += writeOptionHTML("false", "No", value);

				out += "</SELECT>";
			} else {
				if (type instanceof BSString) {
					value = value == null ? "" : value;
					maxlength = field.getLength();
					size = maxlength;
					if (size > 75) {
						size = 75;
					}
				} else if (type instanceof BSDate) {
					maxlength = 10;
					format = BSDateTimeUtil.getFormatDate(request);
					value = BSDateTimeUtil.date2String(value, format);
					size = maxlength;
					afterInput = "(formato: " + format + ")";

				} else if (type instanceof BSTimestamp) {
					maxlength = 16;
					format = BSDateTimeUtil.getFormatDatetime(request);
					value = BSDateTimeUtil.date2String(value, format);
					size = maxlength;
					afterInput = "(formato: " + format + ")";
				} else if (type instanceof BSDouble) {
					maxlength = 15;
					//					format = BSWeb.getFormatDecimal(request);
					value = BSWeb.formatDouble(request, (Double) value); // number2String(value, format);
					size = maxlength;
				} else if (type instanceof BSInteger) {
					maxlength = 8;
					//					format = BSWeb.getFormatInteger(request);
					//					value = BSWeb.number2String(value, format);
					value = BSWeb.formatInteger(request, (Integer) value);
					size = maxlength;
				} else if (type instanceof BSLong) {
					maxlength = 10;
					//					format = BSWeb.getFormatInteger(request);
					if (isPk && value == null) {
						value = NEW;
						//isNew = Boolean.TRUE;
					} else {
						value = value == null ? "" : BSWeb.formatLong(request, (Long) value); // BSWeb.number2String(value, format);
					}
					size = maxlength;
				}

				out += drawInputText("text", name, maxlength, isReadOnly, value, size, afterInput, validationOnBlur, isPk, type);
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
			String afterInput, String validationOnBlur, Boolean isPk, BSDataType dataType) {
		String out = "";

		if (isPk) {
			out += value;
			type = isPk ? "hidden" : type;
		}

		out += "<input type='" + type + "' name='";
		out += name;
		out += "' ";
		out += "id='" + name + "' ";
		out += "maxlength='" + maxlength + "' ";
		out += isReadonly ? "readonly " : "";
		out += "value='" + (value.equals(NEW) ? "0" : value) + "' ";
		out += "size='" + size + "px' ";

		out += addScript(dataType);

		if (!"".equals(validationOnBlur)) {
			out += "onBlur='javascript:" + validationOnBlur + "(this)'";
		}

		out += ">&nbsp;" + afterInput;

		return out;
	}

	private String addScript(BSDataType dataType) {
		String out = "";
		if (dataType instanceof BSDouble) {
			out = "onfocus='javascript:doubleFocus(this);' ";
			out += "onblur='javascript:doubleBlur(this);' ";
		} else if (dataType instanceof BSInteger) {
			out = "onfocus='javascript:integerFocus(this);' ";
			out += "onblur='javascript:integerBlur(this);' ";
		} else if (dataType instanceof BSDate) {
			out += "onblur='javascript:dateBlur(this);' ";
		}
		return out;
	}

	@Deprecated
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
	}

	%>
