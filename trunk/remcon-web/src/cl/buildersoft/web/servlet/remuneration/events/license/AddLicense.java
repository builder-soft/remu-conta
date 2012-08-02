package cl.buildersoft.web.servlet.remuneration.events.license;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Employee;
import cl.buildersoft.business.beans.EmployeeFile;
import cl.buildersoft.business.beans.License;
import cl.buildersoft.business.service.EmployeeService;
import cl.buildersoft.business.service.impl.EmployeeServiceImpl;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSFileUtil;

@WebServlet("/servlet/remuneration/events/license/AddLicense")
public class AddLicense extends HttpServlet {
	private static final long serialVersionUID = 6064178375419567365L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSFileUtil fu = new BSFileUtil();
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		Map<String, String> values = fu.uploadFile(request);

		Long employeeId = Long.parseLong(values.get("cId"));
		Long periodId = Long.parseLong(values.get("cPeriod"));

		String fileCategory = null;
		String cause = values.get("cCause");
		Integer indexOfSign = cause.indexOf("#");
		fileCategory = cause.substring(indexOfSign+1);
		cause = cause.substring(0, indexOfSign);

		saveLicense(conn, Long.parseLong(cause), values, employeeId, periodId);

		if (fileCategory.length() > 0) {
			String fileName = values.get("file.fileName");

			EmployeeService employeeService = new EmployeeServiceImpl();
			Employee employee = employeeService.getEmployee(conn, employeeId);
			fileName = fu.getFileName(employee, fileName);

			saveFileToDatabase(conn, values, fileName, employeeId, Long.parseLong(fileCategory));
		}

		request.setAttribute("cId", employeeId);
		request.getRequestDispatcher("/servlet/remuneration/events/license/LicenseMain").forward(request, response);
	}

	private void saveLicense(Connection conn, Long cause, Map<String, String> values, Long employeeId, Long periodId) {
		Integer to = Integer.parseInt(values.get("cTo"));
		Integer from = Integer.parseInt(values.get("cFrom"));
		BSBeanUtils bu = new BSBeanUtils();

		License license = new License();
		license.setEmployee(employeeId);
		license.setFrom(from);
		license.setLicenseCause(cause);
		license.setPeriod(periodId);
		license.setTo(to);

		bu.save(conn, license);

	}

	private void saveFileToDatabase(Connection conn, Map<String, String> values, String fileName, Long employeeId,
			Long fileCategory) {
		EmployeeFile employeeFile = new EmployeeFile();
		employeeFile.setContentType(values.get("file.contentType"));
		employeeFile.setDateTime(new Timestamp(System.currentTimeMillis()));
		employeeFile.setDesc(values.get("cReason"));
		employeeFile.setEmployee(employeeId);
		employeeFile.setFileCategory(fileCategory);
		employeeFile.setFileName(values.get("file.fileName"));
		employeeFile.setFileRealName(fileName);

		Long size = Math.round(Double.parseDouble("" + (Long.parseLong(values.get("file.size")) / 1024)));
		employeeFile.setSize(size);
		BSBeanUtils bu = new BSBeanUtils();
		bu.insert(conn, employeeFile);

	}
}
