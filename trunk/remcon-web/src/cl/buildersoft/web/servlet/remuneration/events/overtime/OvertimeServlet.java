package cl.buildersoft.web.servlet.remuneration.events.overtime;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSAction;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.type.BSActionType;
import cl.buildersoft.web.servlet.BSHttpServlet;

 
@WebServlet("/servlet/remuneration/events/overtime/OvertimeServlet")
public class OvertimeServlet extends BSHttpServlet {
	private static final long serialVersionUID = 2108493719700543508L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = initTable(request, "tEmployee");
		table.setTitle("Horas Hextras");
	
		table.getField("cLastName1").setLabel("A. Paterno");
		table.getField("cLastName2").setLabel("A. Materno");
		table.getField("cName").setLabel("Nombre");
		table.getField("cBirthDate").setLabel("Nacimiento");
		table.getField("cAddress").setLabel("Dirección");
		table.getField("cGenere").setLabel("Género");
		table.getField("cCountry").setLabel("Nacionalidad");
		table.getField("cMaritalStatus").setLabel("Estado Civil");

		table.removeAction("INSERT");
		table.removeAction("DELETE");
		table.removeAction("EDIT");
	
		BSAction overtimeAction = new BSAction("OVERTIME", BSActionType.Record);
		overtimeAction.setLabel("Horas Extras");
		overtimeAction.setUrl("/servlet/remuneration/events/overtime/OvertimeEmployee");
		table.addAction(overtimeAction);
		
		return table;
	}
}
