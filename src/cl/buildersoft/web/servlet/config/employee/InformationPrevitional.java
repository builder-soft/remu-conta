package cl.buildersoft.web.servlet.config.employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.APV;
import cl.buildersoft.business.beans.Agreement;
import cl.buildersoft.business.beans.Currency;
import cl.buildersoft.business.beans.Employee;
import cl.buildersoft.business.beans.ExBoxSystem;
import cl.buildersoft.business.beans.FamilyAssignmentStretch;
import cl.buildersoft.business.beans.Health;
import cl.buildersoft.business.beans.PFM;
import cl.buildersoft.business.beans.RagreementAPV;
import cl.buildersoft.business.service.AgreementService;
import cl.buildersoft.business.service.EmployeeService;
import cl.buildersoft.business.service.impl.AgreementServiceImpl;
import cl.buildersoft.business.service.impl.EmployeeServiceImpl;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.util.BSBeanUtilsSP;
import cl.buildersoft.web.servlet.table.AbstractServletUtil;

@WebServlet("/servlet/config/employee/InformationPrevitional")
public class InformationPrevitional extends AbstractServletUtil {

	private static final long serialVersionUID = -5785656616097922095L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long employeeId = Long.parseLong(request.getParameter("cId"));

		Connection conn = null;

		BSmySQL mysql = new BSmySQL();
		conn = mysql.getConnection(request);
		BSBeanUtilsSP bu = new BSBeanUtilsSP();

		EmployeeService service = new EmployeeServiceImpl();
		Employee employee = service.getEmployee(conn, employeeId);

		List<APV> listadoApv = (List<APV>) bu.list(conn, new APV(), "pListAPV");
		List<PFM> listadoAfp = (List<PFM>) bu.list(conn, new PFM(), "pListPFM");
		List<Currency> listadoCurrency = (List<Currency>) bu.list(conn, new Currency(), "pListCurrency");
		List<Health> listadoHealth = (List<Health>) bu.list(conn, new Health(), "pListHealth");
		List<ExBoxSystem> listadoExBox = (List<ExBoxSystem>) bu.list(conn, new ExBoxSystem(), "pListExBoxSystem");
		List<FamilyAssignmentStretch> familyAssignmentStretch = (List<FamilyAssignmentStretch>) bu.list(conn,
				new FamilyAssignmentStretch(), "pListFamilyAssignmentStretch");

		List<RagreementAPV> listadoApvEmp = listAPVForEmployee(conn, mysql, employeeId);

		Agreement agreementEmp = getAgreement(conn, new BSBeanUtilsSP(), employeeId);

		request.setAttribute("listadoAfp", listadoAfp);
		request.setAttribute("listadoApv", listadoApv);
		request.setAttribute("listadoCurrency", listadoCurrency);
		request.setAttribute("listadoApvEmp", listadoApvEmp);
		request.setAttribute("listadoExBox", listadoExBox);
		request.setAttribute("listadoHealth", listadoHealth);
		request.setAttribute("agreementEmp", agreementEmp);
		request.setAttribute("FamilyAssignmentStretch", familyAssignmentStretch);
		request.setAttribute("Employee", employee);

		request.setAttribute("Action", "Update");
		request.getRequestDispatcher("/WEB-INF/jsp/config/employee/previtional-information.jsp").forward(request, response);
	}

	private List<RagreementAPV> listAPVForEmployee(Connection conn, BSmySQL mysql, Long employeeId) {
		ResultSet rs = mysql.callSingleSP(conn, "pListAPVForEmployee", array2List(employeeId));

		// List<RagreementAPV> listadoApvEmp = (List<RagreementAPV>)
		// bu.list(conn,
		// new RagreementAPV(), "pListAPVForEmployee", parameter);
		List<RagreementAPV> out = new ArrayList<RagreementAPV>();
		try {
			while (rs.next()) {
				RagreementAPV agreementAPV = new RagreementAPV();
				agreementAPV.setAgreement(rs.getLong("cAgreement"));
				agreementAPV.setApv(rs.getLong("cAPV"));
				agreementAPV.setCurrency(rs.getLong("cCurrency"));
				agreementAPV.setAmount(rs.getDouble("cAmount"));
				out.add(agreementAPV);
			}
		} catch (SQLException e) {
			throw new BSDataBaseException("", e.getMessage());
		}

		return out;
	}

	public Agreement getAgreement(Connection conn, BSBeanUtilsSP bu, Long idEmployee) {
		AgreementService agreementService = new AgreementServiceImpl();
		Agreement out = agreementService.getAgreementByEmployee(conn, idEmployee);
		return out;
	}

}
