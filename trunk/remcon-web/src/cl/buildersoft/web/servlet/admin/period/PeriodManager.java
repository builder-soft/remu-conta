package cl.buildersoft.web.servlet.admin.period;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.util.crud.BSAction;
import cl.buildersoft.framework.util.crud.BSActionType;
import cl.buildersoft.framework.util.crud.BSTableConfig;
import cl.buildersoft.web.servlet.common.crud.HttpServletCRUD;

@WebServlet("/servlet/admin/period/PeriodManager")
public class PeriodManager extends HttpServletCRUD implements Servlet {
	private static final long serialVersionUID = -5347446304866453415L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = super.initTable(request, "tPeriod");
		table.setTitle("Períodos");

		table.setSortField("cDate");
		
		table.removeAction("INSERT");
		table.removeAction("DELETE");
		table.getAction("EDIT").setUrl("/servlet/admin/period/UpdatePeriod");
		table.addAction(newAction("DUPLICATE", "Duplicar", "/servlet/admin/period/ReadPeriod"));

		table.addAction(newAction("OPEN", "Abrir", "/servlet/admin/period/OpenPeriod"));
		table.addAction(newAction("CLOSE", "Cerrar", "/servlet/admin/period/ClosePeriod"));

		table.getField("cDate").setLabel("Fecha período");
		table.getField("cPeriodStatus").setLabel("Estado");
		table.getField("cUF").setLabel("U.F.");
		table.getField("cOvertimeFactor").setLabel("Factor de horas extras");
		table.getField("cMinSalary").setLabel("Sueldo mínimo");
		table.getField("cLimitGratification").setLabel("Tope Gratificación");
		table.getField("cGratificationFactor").setLabel("Factor de Gratificación");
		table.getField("cLimitIPS").setLabel("Tope IPS");
		table.getField("cLimitInsurance").setLabel("Tope seguro");
		table.getField("cLimitHealth").setLabel("Tope salud");
		table.getField("cUTM").setLabel("U.T.M.");
		table.getField("cDaysForYear").setLabel("Días de vacaciones por año");
		

		return table;
	}

	private BSAction newAction(String key, String label, String url) {
		BSAction out = new BSAction(key, BSActionType.Record);
		out.setUrl(url);
		out.setLabel(label);
//		out.setDisabled(true);
		return out;
	}

}
