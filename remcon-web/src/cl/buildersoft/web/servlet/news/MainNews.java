package cl.buildersoft.web.servlet.news;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/news/MainNews")
public class MainNews extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainNews() {
		super();

	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = "/WEB-INF/jsp/news/index.jsp";
		request.getRequestDispatcher(page).forward(request, response);
	}

}
