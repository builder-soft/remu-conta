package cl.buildersoft.web.servlet.remuneration.events.license;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/remuneration/events/license/AddLicense")
public class AddLicense extends HttpServlet {
	private static final long serialVersionUID = 6064178375419567365L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String employeeId = request.getParameter("cId");
		String reason = request.getParameter("cReason");
		String cause = request.getParameter("cCause");

		request.setAttribute("cId", employeeId);
		request.getRequestDispatcher("/servlet/remuneration/events/license/LicenseMain").forward(request, response);
	}

}
