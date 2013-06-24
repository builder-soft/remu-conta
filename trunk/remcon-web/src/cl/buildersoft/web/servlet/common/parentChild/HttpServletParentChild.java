package cl.buildersoft.web.servlet.common.parentChild;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.parentChild.BSParentChild;
import cl.buildersoft.framework.service.BSParentChildService;
import cl.buildersoft.framework.util.BSFactory;

public abstract class HttpServletParentChild extends HttpServlet {
	public static final String SESSION_ATTRIBUTE_NAME_PARENT_CHILD = "BSSessionAttributeNameParentChild";
	private static final long serialVersionUID = 2938870521855504597L;

	protected abstract BSParentChild getBSParentChildConfig(HttpServletRequest request);

	public HttpServletParentChild() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSParentChild parentChildConfig = getBSParentChildConfig(request);
		
		
		// Aqui se debe cargar la información de estructura de las tablas.
		
				BSParentChildService service = BSFactory.getParentChildService();
				//service .init(conn, dataBase, parentTable, childName);
		
		HttpSession session = request.getSession();
		synchronized (session) {
			session.setAttribute(SESSION_ATTRIBUTE_NAME_PARENT_CHILD, parentChildConfig);
		}
		
		
		request.getRequestDispatcher(ServletMain.URL).forward(request, response);
	}

}
