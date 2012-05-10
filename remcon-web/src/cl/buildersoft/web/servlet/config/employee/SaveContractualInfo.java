package cl.buildersoft.web.servlet.config.employee;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.beans.Agreement;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.type.BSFieldDataType;
import cl.buildersoft.framework.type.BSFieldType;
import cl.buildersoft.framework.type.BSTypeFactory;
import cl.buildersoft.framework.util.BSBeanUtilsSP;

@WebServlet("/servlet/config/employee/SaveContractualInfo")
public class SaveContractualInfo extends HttpServlet {
	private static final long serialVersionUID = 5316369008384063620L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Long id = Long.parseLong(request.getParameter("cId"));

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtilsSP bu = new BSBeanUtilsSP();

		Agreement agreement = getAgreement(conn, bu, id);

		BSFieldDataType dateType = BSTypeFactory.create(BSFieldType.Date);
		BSFieldDataType doubleType = BSTypeFactory.create(BSFieldType.Double);

		Long profile = Long.parseLong(request.getParameter("cProfile"));
		Long contractType = Long.parseLong(request
				.getParameter("cContractType"));
		Date startContract = (Date) dateType.parse(conn,
				request.getParameter("cStartContract"));
		Date endContract = contractType.equals(1L) ? null : (Date) dateType
				.parse(conn, request.getParameter("cEndContract"));
		Long gratificationType = Long.parseLong(request
				.getParameter("cGratificationType"));
		Long horary = Long.parseLong(request.getParameter("cHorary"));

		Double mobilization = (Double) doubleType.parse(conn,
				request.getParameter("cMobilization"));
		Double feeding = (Double) doubleType.parse(conn,
				request.getParameter("cFeeding"));

		agreement.setProfile(profile);
		agreement.setContractType(contractType);
		agreement.setStartContract(startContract);
		agreement.setEndContract(endContract);
		agreement.setGratificationType(gratificationType);
		agreement.setHorary(horary);
		agreement.setMobilization(mobilization);
		agreement.setFeeding(feeding);

		bu.save(conn, agreement);

		request.getRequestDispatcher("/servlet/remu/EmployeeManager").forward(
				request, response);
	}

	private Agreement getAgreement(Connection conn, BSBeanUtilsSP bu,
			Long idEmployee) {
		ContractualInfo ci = new ContractualInfo();
		return ci.getAgreement(conn, bu, idEmployee);
	}

}
