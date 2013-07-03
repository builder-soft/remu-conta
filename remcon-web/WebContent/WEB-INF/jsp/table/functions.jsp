
<%@page import="cl.buildersoft.framework.dataType.BSDataTypeUtil"%>
<%@page import="cl.buildersoft.framework.dataType.BSBoolean"%>
<%@page import="cl.buildersoft.framework.dataType.BSCalendar"%>
<%@page import="cl.buildersoft.framework.dataType.BSDataType"%>
<%@page import="cl.buildersoft.framework.util.crud.BSField"%>
<%@page import="java.util.List"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="cl.buildersoft.framework.beans.BSCss"%>
<%@page import="cl.buildersoft.framework.beans.BSScript"%>
<%@page import="cl.buildersoft.framework.beans.BSHeadConfig"%>
<%@page import="cl.buildersoft.framework.type.BSFieldType"%>
<%!private String getAlign(BSField field) {
		String out = " align='left' ";
		BSDataType type = field.getType();
		if (type instanceof BSCalendar || type instanceof BSBoolean || field.isFK()) {
			out = " align='center' ";
		} else if (BSDataTypeUtil.isNumber(type)) {
			out = " align='right' ";
		}
		return out;
	}

	private String capitalize(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}

	/**<code>
	private void configHead(HttpSession session, HttpServletRequest request, JspWriter out ){
		BSHeadConfig head = (BSHeadConfig) session.getAttribute("BSHead");
		BSScript script = head.getScript();
		BSCss css = head.getCss();
		for (String oneScript : script.getListScriptNames()) {
			out.print("<script src='" + request.getContextPath()
					+ script.getPath() + oneScript + ".js'></script>");
		}

		for (String oneCss : css.getListCssNames()) {
			out.print("<LINK rel='stylesheet' type='text/css' src='"
					+ request.getContextPath() + css.getPath() + oneCss
					+ ".css'/>");
		}

	}</code>*/%>