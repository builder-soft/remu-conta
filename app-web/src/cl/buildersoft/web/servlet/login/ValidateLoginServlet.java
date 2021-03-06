package cl.buildersoft.web.servlet.login;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.Rol;
import cl.buildersoft.framework.beans.User;
import cl.buildersoft.framework.services.impl.BSUserServiceImpl;
import cl.buildersoft.framework.util.BSDataUtils;

/**
 * Servlet implementation class ValidateServlet
 */

@WebServlet(urlPatterns = "/login/ValidateLoginServlet")
public class ValidateLoginServlet extends HttpServlet {
	private static final long serialVersionUID = -4481703270849068766L;

	 
	public ValidateLoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/common/no-access.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String mail = request.getParameter("mail");
		String password = request.getParameter("password");

		BSUserServiceImpl userService = new BSUserServiceImpl();
		BSDataUtils dau = new BSDataUtils();

		User user = null;
		Rol rol = null;
		Connection conn = null;

		try {
			conn = dau.getConnection(getServletContext(), "bsframework");
			user = userService.login(conn, mail, password);
			if (user != null) {
				rol = userService.getRol(conn, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		String page = null;

		if (user != null) {
			HttpSession session = request.getSession(true);
			synchronized (session) {
				session.setAttribute("User", user);
				session.setAttribute("Rol", rol);
			}
			page = "/servlet/login/GetMenuServlet";
		} else {
			page = "/WEB-INF/jsp/login/not-found.jsp";
		}

		request.getRequestDispatcher(page).forward(request, response);
	}
}
