package cl.buildersoft.framework.util;

import java.io.File;
import java.sql.Connection;

public class BSConfig extends BSDataUtils {
	public String getString(Connection conn, String key) {
		return getValue(conn, key);
	}

	public Integer getInteger(Connection conn, String key) {
		return Integer.parseInt(getValue(conn, key));
	}

	public Double getDouble(Connection conn, String key) {
		return Double.parseDouble(getValue(conn, key));
	}

	private String getValue(Connection conn, String key) {
		String sql = "SELECT cValue FROM tParameter WHERE cKey=?";
		return super.queryField(conn, sql, key);
	}

	public char getSeparator(Connection conn) {
		String seperator = getString(conn, "CSV_SEPARATOR");
		return seperator.toCharArray()[0];
	}

	public Integer getRecordsPerPage(Connection conn) {
		return getInteger(conn, "RECORDS_PER_PAGE");
	}

	public static String getFileSeparator() {
		return System.getProperty("file.separator");
	}
}
