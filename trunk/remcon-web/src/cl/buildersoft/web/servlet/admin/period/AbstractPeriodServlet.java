package cl.buildersoft.web.servlet.admin.period;

import java.sql.Connection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.business.beans.Period;
import cl.buildersoft.business.service.PeriodService;
import cl.buildersoft.business.service.impl.PeriodServiceImpl;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSProgrammerException;

public class AbstractPeriodServlet extends HttpServlet {
	private static final long serialVersionUID = 5175580005574523069L;

	protected String process(HttpServletRequest request, Status oldStatus, Status newStatus) {
		Long idPeriod = Long.parseLong(request.getParameter("cId"));
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		String url = "/servlet/admin/period/PeriodManager";

		PeriodService ps = new PeriodServiceImpl();
		Period period = ps.getPeriod(conn, idPeriod);

		Status status = getStatus(period);
		if (status.equals(oldStatus)) {
			changeStatus(conn, period, newStatus);
		}else{
			setPeriodInfoToRequest(request, conn, ps, period);

			url = "/WEB-INF/jsp/config/period/period-cant-change.jsp";
		}
		mysql.closeConnection(conn);
		return url;
	}

	protected void changeStatus(Connection conn, Period period, Status status) {
		BSmySQL mysql = new BSmySQL();
		if(status.equals(Status.INIT) ){
			throw new BSProgrammerException("You can change status to Open or Close");
		}
		
		String spName = status.equals(Status.OPEN) ? "pOpenPeriod" : "pClosePeriod";
		mysql.callSingleSP(conn, spName, period.getId());
	}

	protected Status getStatus(Period period) {
		Status out = null;

		switch (period.getPeriodStatus().intValue()) {
		case 1:
			out = Status.INIT;
			break;
		case 2:
			out = Status.OPEN;
			break;
		case 3:
			out = Status.CLOSE;
			break;
		}

		return out;
	}

	protected void setPeriodInfoToRequest(HttpServletRequest request, Connection conn, PeriodService ps, Period period) {
		request.setAttribute("PeriodName", ps.periodAsString(period));
		request.setAttribute("StatusName", ps.getStatusName(conn, period));
	}
}

enum Status {
	INIT, OPEN, CLOSE;
}