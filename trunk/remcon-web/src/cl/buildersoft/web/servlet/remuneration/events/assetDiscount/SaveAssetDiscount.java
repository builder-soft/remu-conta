package cl.buildersoft.web.servlet.remuneration.events.assetDiscount;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.database.BSmySQL;

@WebServlet("/servlet/remuneration/events/assetDiscount/SaveAssetDiscount")
public class SaveAssetDiscount extends HttpServlet {
	private static final long serialVersionUID = 4541324920181198318L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		Long period = Long.parseLong(request.getParameter("cPeriod"));
		Long employee = Long.parseLong(request.getParameter("cEmployee"));

		saveAsset(request, mysql, conn, period, employee);
		
		
		
		
		request.setAttribute("cId", employee);
		request.getRequestDispatcher("/servlet/remuneration/events/EventsEmployeeServlet").forward(request, response);

	}

	private void saveAsset(HttpServletRequest request, BSmySQL mysql, Connection conn, Long period, Long employee) {
		Double participation = Double.parseDouble(request.getParameter("cParticipation"));
		Double extraPay = Double.parseDouble(request.getParameter("cExtraPay"));
		Double ias = Double.parseDouble(request.getParameter("cIAS"));
		Double bounty = Double.parseDouble(request.getParameter("cBounty"));
		Double familyRetroactive = Double.parseDouble(request.getParameter("cFamilyRetroactive"));
		Double monthNotification = Double.parseDouble(request.getParameter("cMonthNotification"));
		Double feeding = Double.parseDouble(request.getParameter("cFeeding"));
		Double mobilization = Double.parseDouble(request.getParameter("cMobilization"));

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(period);
		parameters.add(employee);
		parameters.add(participation);
		parameters.add(extraPay);
		parameters.add(ias);
		parameters.add(bounty);
		parameters.add(familyRetroactive);
		parameters.add(monthNotification);
		parameters.add(feeding);
		parameters.add(mobilization);

		mysql.callSingleSP(conn, "pSaveAsset", parameters);
	}

}
