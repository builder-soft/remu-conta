package cl.buildersoft.web.servlet.remu;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSAction;
import cl.buildersoft.framework.beans.BSTableConfig;
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
//		BSTableConfig table = new BSTableConfig("remu", "tEmployee");
//		BSmySQL mysql = new BSmySQL();
//		Connection conn = mysql.getConnection(request.getServletContext(),
//				"remu");
//		table.configFields(conn, mysql);
		
		BSTableConfig table = initTable(request, "tEmployee");
		table.setTitle("Listado de empleados");
		table.setDeleteSP("pDelEmployee");

		table.getField("cLastName1").setLabel("A. Paterno");
		table.getField("cLastName2").setLabel("A. Materno");
		table.getField("cName").setLabel("Nombre");
		table.getField("cBirthDate").setLabel("Nacimiento");
		table.getField("cAddress").setLabel("Dirección");
		table.getField("cGenere").setLabel("Género");
		table.getField("cCountry").setLabel("Nacionalidad");
		table.getField("cMaritalStatus").setLabel("Estado Civil");
		//table.getField("cContractType").setLabel("Tipo de contrato");
		//table.getField("cStartContract").setLabel("Fecha ingreso");
		//table.getField("cEndContract").setLabel("Fecha fin");
		//table.getField("cProfile").setLabel("Perfil");
		//table.getField("cPFM").setLabel("AFP");
		//table.getField("cHealth").setLabel("Isapre");
		//table.getField("cGratificationType").setLabel("Tipo de gratificacion");
		//table.getField("cPaymentType").setLabel("Tipo de pago");
		//table.getField("cMobilization").setLabel("Movilizacion");

		//table.removeField("cPFM");
		
		
//		String[] noVisibleFields = { "cCountry", "cMaritalStatus", "cProfile", "cEmail", "cMovil", "cPhone", "cComuna",
//				"cAddress", "cBirthDate", "cGratificationType", "cFeeding", "cMobilization"};
		/**
		for (String fieldName : noVisibleFields) {
			table.getField(fieldName).setVisible(false);
		}
		*/
//		super.hideFields(table, noVisibleFields);
				
		
		BSAction previtionalInformation = new BSAction("PREVITIONAL",
				BSActionType.Record);
		previtionalInformation.setLabel("Información Previsional");
//		previtionalInformation.setUrl("/servlet/ShowParameters");
		previtionalInformation.setUrl("/servlet/table/EmployeeInformation");
		table.addAction(previtionalInformation);

		BSAction contractualInfo = new BSAction("CONTRACTUAL",
				BSActionType.Record);
		contractualInfo.setLabel("Información Contractual");
		contractualInfo.setUrl("/servlet/config/employee/ContractualInfo");
		table.addAction(contractualInfo);

		BSAction payMode = new BSAction("PAY_MODE", BSActionType.Record);
		payMode.setLabel("Modo de Pago");
		payMode.setUrl("/servlet/config/employee/PayMode");
		table.addAction(payMode);
		return table;
	}
}
