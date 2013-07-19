<%@page import="cl.buildersoft.framework.dataType.BSLong"%>
<%@page import="cl.buildersoft.framework.dataType.BSInteger"%>
<%@page import="cl.buildersoft.framework.dataType.BSDecimal"%>
<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.framework.dataType.BSDouble"%>
<%@page import="cl.buildersoft.framework.dataType.BSTimestamp"%>
<%@page import="cl.buildersoft.framework.util.BSDateTimeUtil"%>
<%@page import="cl.buildersoft.framework.dataType.BSDate"%>
<%@page import="cl.buildersoft.framework.dataType.BSString"%>
<%@page import="cl.buildersoft.framework.dataType.BSBoolean"%>
<%@page import="cl.buildersoft.framework.dataType.BSDataType"%>
<%@page import="java.util.List"%>
<%@page import="cl.buildersoft.framework.util.crud.BSField"%>
<%!private static String NEW = "[Nuevo]";

	private String getFKSelect(BSField field, Boolean addEmptyOpction) {
		String name = field.getName();
		Object value = field.getValue();

		String out = "<select name='";
		out += name + "'>";
		List<Object[]> data = field.getFKData();
		if (addEmptyOpction) {
			out += "<option vaue=''>- Seleccione alternativa -</option>";
		}
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

	private String writeHTMLField(BSField field, HttpServletRequest request) {
		String out = "";
		BSDataType type = field.getType();
		Object value = field.getValue();
		Integer maxlength = 0;
		String name = field.getName();
		String format = "";
		Integer size = 0;
		String afterInput = "";
		String htmlType = "text";
		Boolean isPk = field.isPK();

		Boolean isReadOnly = isPk ? Boolean.TRUE : field.isReadonly();
		String validationOnBlur = field.getValidationOnBlur() != null ? field.getValidationOnBlur() : "";

		if (type instanceof BSBoolean) {
			/**
			out += "<SELECT name='" + name + "' ";
			out += isReadOnly ? " DISABLED " : "";
			out += ">";

			out += writeOptionHTML("true", "Si", value);
			out += writeOptionHTML("false", "No", value);

			out += "</SELECT>";
			 */
			htmlType = "checkbox";
			value = value == null ? "" : value;
		} else {
			if (type instanceof BSString) {
				value = value == null ? "" : value;
				maxlength = field.getLength();
				size = maxlength;
				if (size > 75) {
					size = 75;
				}
			} else if (type instanceof BSDate) {
				maxlength = 8;
				format = BSDateTimeUtil.getFormatDate(request);
				value = BSDateTimeUtil.date2String(value, format);
				size = maxlength;
				afterInput = "(" + format + ")";
				htmlType = "date";

			} else if (type instanceof BSTimestamp) {
				maxlength = 16;
				format = BSDateTimeUtil.getFormatDatetime(request);
				value = BSDateTimeUtil.date2String(value, format);
				size = maxlength;
				afterInput = "(" + format + ")";
				htmlType = "datetime";
				
			} else if (type instanceof BSDouble || type instanceof BSDecimal) {
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
		}
		out += drawInputText(htmlType, name, maxlength, isReadOnly, value, size, afterInput, validationOnBlur, isPk, type);
		return out;
	}

	private String drawInputText(String type, String name, Integer maxlength, Boolean isReadonly, Object value, Integer size,
			String afterInput, String validationOnBlur, Boolean isPk, BSDataType dataType) {
		String out = "";

		if (isPk) {
			out += "" + value + "";
			type = "hidden";
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
	}%>