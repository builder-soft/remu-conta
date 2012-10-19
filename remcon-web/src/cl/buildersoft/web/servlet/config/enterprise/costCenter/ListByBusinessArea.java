package cl.buildersoft.web.servlet.config.enterprise.costCenter;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.CostCenter;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSWeb;
import cl.buildersoft.web.servlet.ajax.AbstractAjaxServlet;
import flexjson.JSONSerializer;

@WebServlet("/servlet/config/enterprise/costCenter/ListByBusinessArea")
public class ListByBusinessArea extends AbstractAjaxServlet {
	private static final long serialVersionUID = -8397471341795865371L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long businessArea = Long.parseLong(request.getParameter("cBusinessArea"));
//		String out = null;

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		JSONSerializer jsonSerializer = new JSONSerializer();
		BSBeanUtils bu = new BSBeanUtils();
		StringBuffer json = new StringBuffer(1024);

		List<CostCenter> costCenterList = (List<CostCenter>) bu.list(conn, new CostCenter(), "cBusinessArea=?", businessArea);

		for (CostCenter cc : costCenterList) {
			json.append(jsonSerializer.exclude("class").serialize(cc));
		}


		setHeaders(response);
		json.trimToSize();
		endWrite(writeToBrowser(response, json.toString()));

	}

}
