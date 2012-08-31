<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="cl.buildersoft.business.beans.Period"%>
<%@page import="cl.buildersoft.framework.util.BSDateTimeUtil"%>

<%=write_period_selector(request)%>

<%!private String write_period_selector(HttpServletRequest request) {
		Period period = (Period) request.getAttribute("Period");
		Integer increment = (request.getAttribute("Increment") == null ? 1 : (Integer) request.getAttribute("Increment"));

		Date date = period.getDate();
		Calendar calendar = BSDateTimeUtil.date2Calendar(date);
		incrementMonth(calendar, increment);

		Integer currentMonth = calendar.get(Calendar.MONTH);

		Integer currentYear = getYear(date);
		String out = "<select name='cMonth'>\n";
		out += getMonthOptions(currentMonth);
		out += "</select>\n";

		out += "<input type='number' name='cYear' min='" + (currentYear - 2) + "' max='" + (currentYear + 3) + "' ";
		out += "value='" + currentYear + "'>";

		return out;
	}

	private Integer getYear(Date date) {
		return Integer.parseInt(BSDateTimeUtil.getYear(date));
	}

	private String getMonthOptions(Integer currentMonth) {
		String out = "";

		for (int i = 0; i <= 11; i++) {
			out += "<option value='" + i + "' " + (i == currentMonth ? "selected" : "") + ">" + BSDateTimeUtil.month2Word(i)
					+ "</option>\n";
		}
		return out;
	}

	private void incrementMonth(Calendar calendar, Integer increment) {
		calendar.add(Calendar.MONTH, increment);
		//		return out;
	}%>
