package cl.buildersoft.web.servlet.remuneration.events.holiday;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
@WebServlet("/servlet/remuneration/events/holiday/ValidateHolidayParameters")
public class ValidateHolidayParameters extends HttpServlet {
	private static final long serialVersionUID = -122693918942394203L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		System.out.println(request.getParameter("p1"));
		response.getWriter().print("hola");
		
		
		
	}

}
