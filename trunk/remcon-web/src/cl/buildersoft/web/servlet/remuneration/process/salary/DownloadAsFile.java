package cl.buildersoft.web.servlet.remuneration.process.salary;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Period;
import cl.buildersoft.business.service.PeriodService;
import cl.buildersoft.business.service.impl.PeriodServiceImpl;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.util.BSConfig;
import cl.buildersoft.framework.util.BSDateTimeUtil;
import cl.buildersoft.web.servlet.csv.CsvWriter;
import cl.buildersoft.web.servlet.table.AbstractServletUtil;

@WebServlet("/servlet/remuneration/process/salary/DownloadAsFile")
/**
 * Esta clase se puede mejorar para poder hacerla generica y que otras extiandan de ésta para exportar a csv cualquier resultado de un SP*/
public class DownloadAsFile extends AbstractServletUtil {
	private static final long serialVersionUID = -4993597280195709037L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		PeriodService ps = new PeriodServiceImpl();
		Period period = ps.getOpenedPeriod(conn);

		ResultSet rs = mysql.callSingleSP(conn, "pListBook", null);

		ServletOutputStream output = configHeaderAsCSV(response, "libro-remuneraciones-" + ps.periodAsShortString(period));
		CsvWriter csv = new CsvWriter(output, new BSConfig().getCSVSeparator(conn), Charset.defaultCharset());

		try {
			Integer index = 1;
			String type = null;
			String value = null;
			ResultSetMetaData metaData = rs.getMetaData();
			for (index = 1; index <= metaData.getColumnCount(); index++) {
				csv.write(metaData.getColumnLabel(index));
			}
			csv.endRecord();

			while (rs.next()) {
				for (index = 1; index <= metaData.getColumnCount(); index++) {
					type = metaData.getColumnTypeName(index);
					value = formatData(conn, rs.getString(index), type);
					csv.write(value);
				}
				csv.endRecord();
			}
			csv.flush();
			csv.close();

		} catch (SQLException e) {
			throw new BSDataBaseException(e);
		}

	}

	private String formatData(Connection conn, String data, String type) {
		String out = "";
		String format = null;

		if ("date".equalsIgnoreCase(type)) {
			Calendar cal = BSDateTimeUtil.string2Calendar(data, "yyyy-MM-dd");
			format = BSDateTimeUtil.getFormatDate(conn);
			out = BSDateTimeUtil.calendar2String(cal, format);
		} else if ("double".equalsIgnoreCase(type)) {
			format = "########0.00";
			Double dataDouble = Double.parseDouble(data);
			out = number2String(dataDouble, format);
		} else if ("int".equalsIgnoreCase(type)) {
			format = "########0";
			Integer dataInteger = Integer.parseInt(data);
			out = number2String(dataInteger, format);
		} else if ("bit".equalsIgnoreCase(type)) {
			if ("1".equals(data)) {
				out = "Si";
			} else {
				out = "No";
			}
		} else {
			out = data;
		}
		if (data == null) {
			out = "";
		}
		return out;
	}

	private String number2String(Object value, String format) {
		String out = "";
		if (value != null) {
			Format formatter = new DecimalFormat(format);
			out = formatter.format(value);
		}
		return out;
	}
}
