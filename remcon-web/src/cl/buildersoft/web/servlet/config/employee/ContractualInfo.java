package cl.buildersoft.web.servlet.config.employee;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.beans.Agreement;
import cl.buildersoft.framework.beans.BSBean;
import cl.buildersoft.framework.beans.ContractType;
import cl.buildersoft.framework.beans.Employee;
import cl.buildersoft.framework.beans.Profile;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSBeanUtilsSP;

@WebServlet("/servlet/config/employee/ContractualInfo")
public class ContractualInfo extends HttpServlet {
	private static final long serialVersionUID = -8599267568451620681L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("cId"));
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtilsSP bu = new BSBeanUtilsSP();

		Employee emp = getEmployee(conn, bu, id);
		Agreement agreement = getAgreement(conn, bu, id);
		List<Profile> profiles = getProfiles(conn, bu);
		List<BSBean> contractTypes = getList(conn, bu, new ContractType(),
				"pGetContractTypeList");

		request.setAttribute("Employee", emp);
		request.setAttribute("Agreement", agreement);
		request.setAttribute("Profiles", profiles);
		request.setAttribute("ContractTypes", contractTypes);
		request.getRequestDispatcher(
				"/WEB-INF/jsp/config/employee/contractual-info.jsp").forward(
				request, response);
	}

	private List<BSBean> getList(Connection conn, BSBeanUtilsSP bu,
			BSBean object, String spName) {
		List<BSBean> out = (List<BSBean>) bu.list(conn, object, spName, null);
		return out;
	}

	private List<Profile> getProfiles(Connection conn, BSBeanUtilsSP bu) {
		List<Profile> out = (List<Profile>) bu.list(conn, new Profile(),
				"pGetProfileList", null);
		return out;
	}

	private Agreement getAgreement(Connection conn, BSBeanUtilsSP bu, Long id) {
		List<Object> prms = new ArrayList<Object>();
		prms.add(id);
		List<BSBean> agreements = (List<BSBean>) bu.list(conn, new Agreement(),
				"pGetAgreementByEmployee", prms);
		return (Agreement) agreements.get(0);
	}

	private Employee getEmployee(Connection conn, BSBeanUtilsSP bu, Long id) {
		Employee out = new Employee();
		out.setId(id);
		bu.search(conn, out);
		return out;
	}

}