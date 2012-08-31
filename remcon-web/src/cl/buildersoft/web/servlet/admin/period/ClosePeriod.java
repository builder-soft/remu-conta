package cl.buildersoft.web.servlet.admin.period;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/admin/period/ClosePeriod")
public class ClosePeriod extends AbstractPeriodServlet {
	private static final long serialVersionUID = 2540912588511910150L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = process(request, Status.OPEN, Status.CLOSE);

		request.getRequestDispatcher(url).forward(request, response);

	}

}
