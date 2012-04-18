package cl.buildersoft.web.servlet.remu;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.BSHttpServlet;

/**
 * Servlet implementation class EmployeeManager
 */
@WebServlet("/servlet/remu/EmployeeManager")
public class EmployeeManager extends BSHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
//		BSTableConfig table = new BSTableConfig("remu", "tEmployee");
//		BSmySQL mysql = new BSmySQL();
//		Connection conn = mysql.getConnection(request.getServletContext(),
//				"remu");
//		table.configFields(conn, mysql);
		
		BSTableConfig table = initTable(request, "remu", "tEmployee");
		table.setTitle("Listado de empleados");

		
		/**
		table.getField("cLastName1").setLabel("A. Paterno");
		table.getField("cLastName2").setLabel("A. Materno");
		table.getField("cName").setLabel("Nombre");
		table.getField("cBirthDate").setLabel("Nacimiento");
		table.getField("cAddress").setLabel("Dirección");
		table.getField("cGenere").setLabel("Género");
		table.getField("cCountry").setLabel("Nacionalidad");
		table.getField("cMaritalStatus").setLabel("Estado Civil");

		String[] noVisibleFields = { "cEmail", "cMovil", "cPhone", "cComuna",
				"cAddress", "cBirthDate" };
		for (String fieldName : noVisibleFields) {
			table.getField(fieldName).setVisible(false);
		}

		BSAction previtionalInformation = new BSAction("PREVITIONAL",
				BSActionType.Record);
		previtionalInformation.setLabel("Información Previsional");
		table.addAction(previtionalInformation);

		BSAction contractualInformation = new BSAction("CONTRACTUAL",
				BSActionType.Record);
		contractualInformation.setLabel("Información Contractual");
		table.addAction(contractualInformation);

		BSAction payMode = new BSAction("PAY_MODE", BSActionType.Record);
		payMode.setLabel("Modo de Pago");
		table.addAction(payMode);
*/
		return table;
	}
}
