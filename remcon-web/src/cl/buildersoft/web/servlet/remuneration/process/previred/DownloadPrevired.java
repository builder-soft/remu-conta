package cl.buildersoft.web.servlet.remuneration.process.previred;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSProgrammerException;
import cl.buildersoft.framework.util.BSConfig;
import cl.buildersoft.framework.util.BSDateTimeUtil;
import cl.buildersoft.framework.util.BSWeb;
import cl.buildersoft.web.servlet.common.AbstractServletUtil;
import cl.buildersoft.web.servlet.csv.CsvWriter;

/**
 * Servlet implementation class DownloadPrevired
 */
@WebServlet("/servlet/remuneration/process/previred/DownloadPrevired")
public class DownloadPrevired extends AbstractServletUtil {
	private static final long serialVersionUID = -8251150694998447141L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long periodId = Long.parseLong(request.getParameter("cPeriod"));

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		ResultSet rs = mysql.callSingleSP(conn, "pListPrevired", periodId);

		ServletOutputStream output = configHeaderAsCSV(response, "previred");
		CsvWriter csv = new CsvWriter(output, new BSConfig().getCSVSeparator(conn), Charset.defaultCharset());

		try {
			while (rs.next()) {
				process(rs, csv);

				// csv.write(rs.getString(1));

				csv.endRecord();
			}
			csv.flush();
			csv.close();
		} catch (SQLException e) {
			throw new BSProgrammerException("", e.getMessage());
		} finally {
			mysql.closeSQL(rs);
			mysql.closeConnection(conn);
		}
	}

	private void process(ResultSet rs, CsvWriter csv) throws SQLException,
			IOException {
		employeeData_1_25(rs, csv);
		pfmData_26_39(rs, csv);
		savingIndividualData_40_44(rs, csv);
		savingCollectiveData_45_49(rs, csv);
		affiliateData_50_61(rs, csv);
		healthSystemData_62_74(rs, csv);
		healthData_75_82(rs, csv);
		compensationFoundData_83_95(rs, csv);
		mutualData_96_99(rs, csv);
		insuranceManagerData_100_102(rs, csv);
		subsidyData_103_104(rs, csv);
		otherEnterpriseData_105(rs, csv);
	}
	
	private void otherEnterpriseData_105(ResultSet rs, CsvWriter csv) throws SQLException, IOException {
		csv.write("0"); /* 105 */
	}

	private void subsidyData_103_104(ResultSet rs, CsvWriter csv) throws SQLException, IOException {
		csv.write("0"); /* 103 */
		csv.write("0"); /* 104 */
	}

	private void insuranceManagerData_100_102(ResultSet rs, CsvWriter csv) throws SQLException, IOException {
		csv.write("0"); /* 100 */
		csv.write("0"); /* 101 */
		csv.write("0"); /* 102 */
	}

	private void mutualData_96_99(ResultSet rs, CsvWriter csv) throws SQLException, IOException {
		csv.write("0"); /* 96 */
		csv.write("0"); /* 97 */
		csv.write("0"); /* 98 */
		csv.write("0"); /* 99 */
	}

	private void compensationFoundData_83_95(ResultSet rs, CsvWriter csv) throws SQLException, IOException {
		csv.write("0"); /* 83 */
		csv.write("0"); /* 84 */
		csv.write("0"); /* 85 */
		csv.write("0"); /* 86 */
		csv.write("0"); /* 87 */
		csv.write("0"); /* 88 */
		csv.write("0"); /* 89 */
		csv.write("0"); /* 90 */
		csv.write("0"); /* 91 */
		csv.write("0"); /* 92 */
		csv.write("0"); /* 93 */
		csv.write("0"); /* 94 */
		csv.write("0"); /* 95 */
	}

	private void healthData_75_82(ResultSet rs, CsvWriter csv) throws SQLException, IOException {
		csv.write("0"); /* 75 */
		csv.write("0"); /* 76 */
		csv.write("0"); /* 77 */
		csv.write("0"); /* 78 */
		csv.write("0"); /* 79 */
		csv.write("0"); /* 80 */
		csv.write("0"); /* 81 */
		csv.write("0"); /* 82 */
	}

	private void healthSystemData_62_74(ResultSet rs, CsvWriter csv) throws SQLException, IOException {
		csv.write("0"); /* 62 */
		csv.write("0"); /* 63 */
		csv.write("0"); /* 64 */
		csv.write("0"); /* 65 */
		csv.write("0"); /* 66 */
		csv.write("0"); /* 67 */
		csv.write("0"); /* 68 */
		csv.write("0"); /* 69 */
		csv.write("0"); /* 70 */
		csv.write("0"); /* 71 */
		csv.write("0"); /* 72 */
		csv.write("0"); /* 73 */
		csv.write("0"); /* 74 */
	}

	private void affiliateData_50_61(ResultSet rs, CsvWriter csv) throws SQLException, IOException {
		csv.write("0"); /* 50 */
		csv.write("0"); /* 51 */
		csv.write("0"); /* 52 */
		csv.write("0"); /* 53 */
		csv.write("0"); /* 54 */
		csv.write("0"); /* 55 */
		csv.write("0"); /* 56 */
		csv.write("0"); /* 57 */
		csv.write("0"); /* 58 */
		csv.write("0"); /* 59 */
		csv.write("0"); /* 60 */
		csv.write("0"); /* 61 */
	}

	private void savingCollectiveData_45_49(ResultSet rs, CsvWriter csv) throws SQLException, IOException {
		csv.write("0"); /* 45 */
		csv.write("0"); /* 46 */
		csv.write("0"); /* 47 */
		csv.write("0"); /* 48 */
		csv.write("0"); /* 49 */
	}

	private void savingIndividualData_40_44(ResultSet rs, CsvWriter csv) throws SQLException, IOException {
		csv.write("0"); /* 40 */
		csv.write("0"); /* 41 */
		csv.write("0"); /* 42 */
		csv.write("0"); /* 43 */
		csv.write("0"); /* 44 */
	}

	private void pfmData_26_39(ResultSet rs, CsvWriter csv) throws SQLException, IOException {
		csv.write(rs.getString("cPFMKey")); /* 26 - Código de la AFP */
		csv.write(rs.getString("cIncome")); /* 27 */
		csv.write(rs.getString("cObligatoryQuote")); /*
													 * 28 - Monto Cotizacion
													 * Obligatoria
													 */
		csv.write(rs.getString("cEmployerDisabilityInsurance")); /*
																 * 29 - Monto
																 * Seguro
																 * invalidez y
																 * sobrevivencia
																 * empleador
																 */
		csv.write(rs.getString("cAPVAmount")); /*
												 * 30 - depósito en cuenta de
												 * ahorro voluntario
												 */
		csv.write("0"); /* 31 */
		csv.write("0"); /* 32 */
		csv.write("0"); /* 33 */
		csv.write("0"); /* 34 */
		csv.write("0"); /* 35 */
		csv.write("0"); /* 36 */
		csv.write("0"); /* 37 */
		csv.write("0"); /* 38 */
		csv.write("0"); /* 39 */
	}

	private void employeeData_1_25(ResultSet rs, CsvWriter csv) throws SQLException, IOException {
		String rut = rs.getString("cRut");
		Integer delimiterPosition = rut.indexOf('-');

		csv.write(rut.substring(0, delimiterPosition));
		csv.write(rut.substring(delimiterPosition + 1));

		csv.write(rs.getString("cLastName1"));
		csv.write(rs.getString("cLastName2"));
		csv.write(rs.getString("cName"));

		csv.write(rs.getString("cGenere"));
		csv.write("" + (rs.getInt("cCountry") == 1 ? 0 : 1));

		/**
		 * <code>
		 01=Remuneracion del mes 
		 02=Gratificaciones 
		 03=Bono Ley de Modernización Empresas Públicas
		 </code>
		 */
		csv.write("01");
		String period = BSDateTimeUtil.date2String(rs.getDate("cDate"), "MMyyyy");
		csv.write(period);
		csv.write(period); /* 10 */

		pensionRegime(rs, csv); /* 11 */
		workerType(rs, csv); /* 12 */
		csv.write("" + rs.getInt("cWorkedDays")); /* 13 */
		lineType(rs, csv); /* 14 */
		movingType(rs, csv); /* 15 */

		period = BSDateTimeUtil.date2String(rs.getDate("cDate"), "dd-MM-yyyy");
		csv.write(period);
		period = BSDateTimeUtil.date2String(rs.getDate("cLastDay"), "dd-MM-yyyy");
		csv.write(period); /* 17 */
		csv.write(rs.getString("cFamilyAssignmentStretch")); /* 18 */
		csv.write(rs.getString("cSimpleLoads"));/* 19 */
		csv.write(rs.getString("cMaternalLoads"));/* 20 */
		csv.write(rs.getString("cDisabilityBurdens"));/* 21 */
		csv.write(rs.getString("cFamilyAssignmentAmount"));/* 22 */
		csv.write(rs.getString("cFamilyRetroactive"));/* 23 */

		csv.write("0"); /* 24 */
		csv.write("N"); /* 25 */

	}

	private void movingType(ResultSet rs, CsvWriter csv) throws SQLException, IOException {
		csv.write("00");

	}

	private void lineType(ResultSet rs, CsvWriter csv) throws SQLException, IOException {
		csv.write("00");

	}

	private void workerType(ResultSet rs, CsvWriter csv) throws SQLException, IOException {
		Integer age = rs.getInt("cAge");
		Long pfm = rs.getLong("cPFM");
		Long exBoxSystem = rs.getLong("cExBoxSystem");
		Boolean pensionary = rs.getBoolean("cPensionary");

		if (age > 65 && (pfm > 1L || exBoxSystem > 1L)) {
			csv.write("3");
		} else {
			if (!pensionary && age <= 65) {
				csv.write("0");
			} else {
				if (pensionary && (pfm > 1L || exBoxSystem > 1L)) {
					csv.write("1");
				} else {
					csv.write("2");
				}
			}
		}
	}

	private void pensionRegime(ResultSet rs, CsvWriter csv) throws SQLException, IOException {
		Long pfm = rs.getLong("cPFM");
		Long exBoxSystem = rs.getLong("cExBoxSystem");
		if (pfm == 1L && exBoxSystem == 1L) {
			csv.write("SIP");
		} else {
			if (pfm > 1L) {
				csv.write("AFP");
			} else {
				csv.write("INP");
			}
		}
	}
}
