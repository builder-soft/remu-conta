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
		saveAssetAndDiscount(request, mysql, conn, period, employee,1L);

		saveDiscount(request, mysql, conn, period, employee);
		saveAssetAndDiscount(request, mysql, conn, period, employee,2L);

		mysql.closeConnection(conn);
		
		request.setAttribute("cId", employee);
		request.getRequestDispatcher("/servlet/remuneration/events/EventsEmployeeServlet").forward(request, response);
	}

	private void saveAssetAndDiscount(HttpServletRequest request, BSmySQL mysql, Connection conn, Long period, Long employee,
			Long type) {
		List<Object> params = new ArrayList<Object>();
		String fieldName, fieldValue = null;
		String preFix = type.equals(1L) ? "cB" : "cD";
		Double value = null;
		params.add(period);
		params.add(employee);
		params.add(type);
		for (int i = 1; i < 11; i++) {
			fieldName = preFix + (i < 10 ? "0" + i : i);
			fieldValue = request.getParameter(fieldName);
			if (fieldValue != null) {
				value = Double.parseDouble(fieldValue);
				params.add(fieldName);
				params.add(value);

				mysql.callSingleSP(conn, "pSaveAssetOrDiscount", params);

				params.remove(4);
				params.remove(3);
			}
		}
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

	private void saveDiscount(HttpServletRequest request, BSmySQL mysql, Connection conn, Long period, Long employee) {
		Double loanEnterprise = Double.parseDouble(request.getParameter("cLoanEnterprise"));
		Double loanCompensationFund = Double.parseDouble(request.getParameter("cLoanCompensationFund"));
		Double savingCompensationFund = Double.parseDouble(request.getParameter("cSavingCompensationFund"));
		Double judicialRetention = Double.parseDouble(request.getParameter("cJudicialRetention"));

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(period);
		parameters.add(employee);
		parameters.add(loanEnterprise);
		parameters.add(loanCompensationFund);
		parameters.add(savingCompensationFund);
		parameters.add(judicialRetention);

		mysql.callSingleSP(conn, "pSaveDiscount", parameters);
	}

}
