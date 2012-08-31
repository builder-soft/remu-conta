package cl.buildersoft.web.servlet.admin.period;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/admin/period/OpenPeriod")
public class OpenPeriod extends AbstractPeriodServlet {
	private static final long serialVersionUID = -4037994195970208860L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = process(request, Status.INIT, Status.OPEN);

		request.getRequestDispatcher(url).forward(request, response);
	}


}
