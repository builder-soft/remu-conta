package cl.buildersoft.web.servlet.remuneration.events.license;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Employee;
import cl.buildersoft.business.beans.LicenseCause;
import cl.buildersoft.business.beans.Period;
import cl.buildersoft.business.service.EmployeeService;
import cl.buildersoft.business.service.impl.EmployeeServiceImpl;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSProgrammerException;
import cl.buildersoft.web.servlet.remuneration.events.overtime.OvertimeMain;

@WebServlet("/servlet/remuneration/events/license/LicenseMain")
public class LicenseMain extends HttpServlet {
	private static final long serialVersionUID = 3366328970103028341L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtils bu = new BSBeanUtils();

		Period period = getPeriod(conn, mysql, bu, request);
		Employee employee = getEmployee(conn, request);
		List<LicenseCause> licenseCause = getLicenseCause(conn);

		request.setAttribute("Period", period);
		request.setAttribute("Employee", employee);
		request.setAttribute("LicenseCauses", licenseCause);
		request.getRequestDispatcher("/WEB-INF/jsp/remuneration/events/license/license.jsp").forward(request, response);
	}

	private List<LicenseCause> getLicenseCause(Connection conn) {
		BSBeanUtils bu = new BSBeanUtils();
		List<LicenseCause> out = (List<LicenseCause>) bu.listAll(conn, new LicenseCause());
		return out;
	}

	private Period getPeriod(Connection conn, BSmySQL mysql, BSBeanUtils bu, HttpServletRequest request) {
		OvertimeMain overtime = new OvertimeMain();
		Period period = overtime.getPeriod(conn, mysql, bu, request);
		return period;
	}

	private Employee getEmployee(Connection conn, HttpServletRequest request) {
		Employee employee = null;
		String idAsParameter = request.getParameter("cId");
		String employeeId =null;
		
		if(idAsParameter!= null){
			employeeId = idAsParameter;
		}else{
			Object idAsAttribute = request.getAttribute("cId");
			if(idAsAttribute== null){
				throw new BSProgrammerException("No hay parametro de ID de empleado");
			}else{
				employeeId = idAsAttribute.toString();
			}			
		}
/*
		String employeeId =  idAsParameter== null ? (String) request.getAttribute("cId") : request
				.getParameter("cId");
	*/	
		
		Long id = Long.parseLong(employeeId);
		EmployeeService employeeService = new EmployeeServiceImpl();
		employee = employeeService.getEmployee(conn, id);
		return employee;
	}
}
