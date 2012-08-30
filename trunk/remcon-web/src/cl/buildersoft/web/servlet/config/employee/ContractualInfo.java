package cl.buildersoft.web.servlet.config.employee;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Agreement;
import cl.buildersoft.business.beans.ContractType;
import cl.buildersoft.business.beans.Employee;
import cl.buildersoft.business.beans.GratificationType;
import cl.buildersoft.business.beans.Horary;
import cl.buildersoft.business.beans.Profile;
import cl.buildersoft.business.service.AgreementService;
import cl.buildersoft.business.service.impl.AgreementServiceImpl;
import cl.buildersoft.framework.beans.BSBean;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSBeanUtilsSP;
import cl.buildersoft.framework.util.BSDateTimeUtil;
import cl.buildersoft.framework.util.BSWeb;

@WebServlet("/servlet/config/employee/ContractualInfo")
public class ContractualInfo extends HttpServlet {
	private static final long serialVersionUID = -8599267568451620681L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("cId"));
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtilsSP bu = new BSBeanUtilsSP();

		Employee emp = getEmployee(conn, bu, id);
		Agreement agreement = getAgreement(conn, bu, id);

		List<BSBean> profiles = getList(conn, bu, new Profile(), "pListProfile");
		List<BSBean> contractTypes = getList(conn, bu, new ContractType(), "pListContractType");
		List<BSBean> gratificationType = getList(conn, bu, new GratificationType(), "pListGratificationType");
		List<BSBean> horary = getList(conn, bu, new Horary(), "pListHorary");

		request.setAttribute("Horary", horary);
		request.setAttribute("GratificationType", gratificationType);
		request.setAttribute("DateFormat", BSDateTimeUtil.getFormatDate(conn));
		request.setAttribute("Employee", emp);
		request.setAttribute("Agreement", agreement);
		request.setAttribute("Profiles", profiles);
		request.setAttribute("ContractTypes", contractTypes);
		request.getRequestDispatcher("/WEB-INF/jsp/config/employee/contractual-info.jsp").forward(request, response);
	}

	private List<BSBean> getList(Connection conn, BSBeanUtilsSP bu, BSBean object, String spName) {
		List<BSBean> out = (List<BSBean>) bu.listAll(conn, object); // , spName,
																	// null);
		return out;
	}

	public Agreement getAgreement(Connection conn, BSBeanUtilsSP bu, Long idEmployee) {
		AgreementService agreementService = new AgreementServiceImpl();
		Agreement out = agreementService.getAgreementByEmployee(conn, idEmployee);
		return out;
	}

	private Employee getEmployee(Connection conn, BSBeanUtilsSP bu, Long id) {
		Employee out = new Employee();
		out.setId(id);
		bu.search(conn, out);
		return out;
	}
}
