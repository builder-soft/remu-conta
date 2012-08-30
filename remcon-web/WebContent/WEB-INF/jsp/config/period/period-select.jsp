<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="cl.buildersoft.business.beans.Period"%>
<%@page import="cl.buildersoft.framework.util.BSDateTimeUtil"%>
<%!private String getYear(Period period) {
		Date date = period.getDate();
		date = incrementMonth(date);
		return BSDateTimeUtil.getYear(date);
	}

	private String getMonthOptions(Period period) {
		Date date = period.getDate();
		date = incrementMonth(date);

		String out = "";
		for (int i = 0; i <= 11; i++) {
			out += "<option value='" + i + "'>" + BSDateTimeUtil.month2Word(i) + "</option>\n";
		}
		return out;
	}

	private Date incrementMonth(Date date) {
		Calendar calendar = BSDateTimeUtil.date2Calendar(date);
		calendar.add(Calendar.MONTH, 1);
		Date out = BSDateTimeUtil.calendar2Date(calendar);
		return out;
	}%>
<select name="cMonth">
	<%=getMonthOptions(period)%>
</select>
<input type="number" name="cYear" min="2010" max="2015"
	value="<%=getYear(period)%>" />