package cl.buildersoft.web.servlet.common.parentChild;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.parentChild.BSParentChild;

@WebServlet("/servlet/common/parentChild/ServletMain")
public class ServletMain extends HttpServlet {
	private static final long serialVersionUID = -3784716359388000018L;
	public static String URL = "/servlet/common/parentChild/ServletMain";

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		BSParentChild parentChild = null;
		synchronized (session) {
			parentChild = (BSParentChild) session.getAttribute(HttpServletParentChild.SESSION_ATTRIBUTE_NAME_PARENT_CHILD);
		}
		
		// Aqui se debe cargar la data desde la base de datos para ser mostada en la página.
		

		request.getRequestDispatcher("/WEB-INF/jsp/framework/parentChild/index.jsp").forward(request, response);
	}
}
