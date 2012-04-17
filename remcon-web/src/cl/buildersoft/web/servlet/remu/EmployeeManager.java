package cl.buildersoft.web.servlet.remu;

import java.sql.Connection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSAction;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.type.BSActionType;
import cl.buildersoft.web.servlet.BSHttpServlet;

/**
 * Servlet implementation class EmployeeManager
 */
@WebServlet("/servlet/remu/EmployeeManager")
public class EmployeeManager extends BSHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = new BSTableConfig("remu", "tEmployee");
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request.getServletContext(),
				"remu");
		table.configFields(conn, mysql);
		table.setTitle("Listado de empleados");

		table.getField("cLastName1").setLabel("A. Paterno");
		table.getField("cLastName2").setLabel("A. Materno");
		table.getField("cName").setLabel("Nombre");
		table.getField("cBirthDate").setLabel("Nacimiento");
		table.getField("cAddress").setLabel("Direcci�n");
		table.getField("cGenere").setLabel("G�nero");
		table.getField("cCountry").setLabel("Nacionalidad");
		table.getField("cMaritalStatus").setLabel("Estado Civil");

		String[] noVisibleFields = { "cEmail", "cMovil", "cPhone", "cComuna",
				"cAddress", "cBirthDate" };
		for (String fieldName : noVisibleFields) {
			table.getField(fieldName).setVisible(false);
		}

		BSAction previtionalInformation = new BSAction("PREVITIONAL",
				BSActionType.Record);
		previtionalInformation.setLabel("Informaci�n Previsional");
		table.addAction(previtionalInformation);

		BSAction contractualInformation = new BSAction("CONTRACTUAL",
				BSActionType.Record);
		contractualInformation.setLabel("Informaci�n Contractual");
		table.addAction(contractualInformation);

		BSAction payMode = new BSAction("PAY_MODE", BSActionType.Record);
		payMode.setLabel("Modo de Pago");
		table.addAction(payMode);

		return table;
	}
}