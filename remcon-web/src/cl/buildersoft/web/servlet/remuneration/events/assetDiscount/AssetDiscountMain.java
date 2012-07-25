package cl.buildersoft.web.servlet.remuneration.events.assetDiscount;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.AssetDiscount;
import cl.buildersoft.business.beans.Employee;
import cl.buildersoft.business.beans.Period;
import cl.buildersoft.business.service.EmployeeService;
import cl.buildersoft.business.service.impl.EmployeeServiceImpl;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.web.servlet.remuneration.events.overtime.OvertimeMain;

@WebServlet("/servlet/remuneration/events/assetDiscount/AssetDiscountMain")
public class AssetDiscountMain extends HttpServlet {
	private static final long serialVersionUID = 6336621423830251342L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtils bu = new BSBeanUtils();

		Period period = getPeriod(conn, mysql, bu, request);
		Employee employee = getEmployee(conn, request);
		List<AssetDiscount> assetDiscount = (List<AssetDiscount>) bu.listAll(conn, new AssetDiscount());

		request.setAttribute("Period", period);
		request.setAttribute("Employee", employee);
		request.setAttribute("AssetDiscount", assetDiscount);
		request.getRequestDispatcher("/WEB-INF/jsp/remuneration/events/assetDiscount/asset-discount.jsp").forward(request,
				response);
	}

	private Period getPeriod(Connection conn, BSmySQL mysql, BSBeanUtils bu, HttpServletRequest request) {
		OvertimeMain overtime = new OvertimeMain();
		Period period = overtime.getPeriod(conn, mysql, bu, request);
		return period;
	}

	private Employee getEmployee(Connection conn, HttpServletRequest request) {
		Employee employee = null;
		String employeeId = request.getParameter("cId") == null ? (String) request.getAttribute("cId") : request
				.getParameter("cId");
		Long id = Long.parseLong(employeeId);
		EmployeeService employeeService = new EmployeeServiceImpl();
		employee = employeeService.getEmployee(conn, id);
		return employee;
	}
}
