package cl.buildersoft.web.servlet.remuneration.events;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSAction;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.type.BSActionType;
import cl.buildersoft.web.servlet.common.BSHttpServlet;

@WebServlet("/servlet/remuneration/events/EventsEmployeeServlet")
public class EventsEmployeeServlet extends BSHttpServlet {
	private static final long serialVersionUID = 2108493719700543508L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = initTable(request, "tEmployee");
		table.setTitle("Eventos de empleados");

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

		createAction(table, "OVERTIME", "Horas Extras", "/servlet/remuneration/events/overtime/OvertimeMain");
		createAction(table, "AANDD", "Haberes y descuentos", "/servlet/remuneration/events/assetDiscount/AssetDiscountMain");
		createAction(table, "LICENSE", "Movimiento de personal", "/servlet/remuneration/events/license/LicenseMain");
		createAction(table, "HOLIDAYS", "Vacaciones", "/servlet/remuneration/events/holiday/HolidayMain");

		return table;
	}

	private void createAction(BSTableConfig table, String id, String label, String url) {
		BSAction action = new BSAction(id, BSActionType.Record);
		action.setLabel(label);
		action.setUrl(url);
		action.setDisabled(url == null);
		table.addAction(action);
	}
}
