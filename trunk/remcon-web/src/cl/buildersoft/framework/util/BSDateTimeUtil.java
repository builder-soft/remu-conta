package cl.buildersoft.framework.util;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSProgrammerException;

public class BSDateTimeUtil {
	private static String formatDate = null;

	public static String month2Word(Date date) {
		String[] out = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre",
				"Noviembre", "Diciembre" };
		return out[date.getMonth()];
	}

	public static String calendar2String(Calendar calendar, String format) {
		return date2String(calendar2Date(calendar), format);
	}

	public static Date calendar2Date(Calendar calendar) {
		Date date = new Date();
		date.setTime(calendar.getTimeInMillis());
		return date;
	}

	public static Calendar date2Calendar(java.util.Date date) {
		Calendar out = Calendar.getInstance();
		out.setTimeInMillis(date.getTime());
		return out;
	}

	public static Calendar string2Calendar(String dateString, String format) {
		DateFormat formatter = new SimpleDateFormat(format);
		Calendar out = null;
		try {
			java.util.Date date = (java.util.Date) formatter.parse(dateString);
			out = date2Calendar(date);
			date = null;
		} catch (ParseException e) {
			throw new BSProgrammerException(e);
		}
		// System.out.println(calendar2String(out));

		return out;
	}

	public static Boolean isValidDate(String date, String format) {
		Boolean out = Boolean.TRUE;
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		Date testDate = null;

		try {
			testDate = sdf.parse(date);
		} catch (ParseException e) {
			out = Boolean.FALSE;
		}

		if (!sdf.format(testDate).equals(date)) {
			out = Boolean.FALSE;
		}

		return out;
	}

	public static String getYear(Date date) {
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
		return simpleDateformat.format(date);
	}

	public static String getFormatDatetime(Connection conn) {
		BSConfig config = new BSConfig();
		return config.getString(conn, "FORMAT_DATETIME");
	}

	public static String getFormatDatetime(HttpServletRequest request) {
		BSmySQL mysql = new BSmySQL();

		Connection conn = mysql.getConnection(request);
		return getFormatDatetime(conn);
		/*
		 * String out = request.getServletContext().getInitParameter(
		 * "bsframework.datetimeFormat"); if (out == null) { out =
		 * "dd/MM/yyyy HH:mm:ss"; } return out;
		 */
	}

	public static String getFormatDate(Connection conn) {
		if (formatDate == null) {
			BSConfig config = new BSConfig();
			formatDate = config.getString(conn, "FORMAT_DATE");
		}
		return formatDate;
	}

	public static String getFormatDate(HttpServletRequest request) {
		if (formatDate == null) {
			BSmySQL mysql = new BSmySQL();
			Connection conn = mysql.getConnection(request);
			formatDate = getFormatDate(conn);
		}
		return formatDate;
	}

	public static String date2String(Object value, String format) {
		String out = "";
		if (value != null) {
			DateFormat formatter = new SimpleDateFormat(format);
			out = formatter.format((Date) value);
		}
		return out;
	}

	public static String date2String(HttpServletRequest request, Object value) {
		String out = "";
		if (value != null) {
			String format = getFormatDate(request);
			DateFormat formatter = new SimpleDateFormat(format);
			out = formatter.format((Date) value);
		}
		return out;
	}

	public static String dateTime2String(HttpServletRequest request, Object value) {
		String out = "";
		if (value != null) {
			String format = getFormatDatetime(request);
			DateFormat formatter = new SimpleDateFormat(format);
			out = formatter.format((Date) value);
		}
		return out;
	}

	public static Calendar string2Calendar(HttpServletRequest request, String date) {
		String format = getFormatDate(request);
		return string2Calendar(date, format);
	}
}
