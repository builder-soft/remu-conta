package cl.buildersoft.web.servlet.admin.period;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSAction;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.type.BSActionType;
import cl.buildersoft.web.servlet.common.BSHttpServlet;

@WebServlet("/servlet/admin/period/PeriodManager")
public class PeriodManager extends BSHttpServlet implements Servlet {
	private static final long serialVersionUID = -5347446304866453415L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = super.initTable(request, "tPeriod");
		table.setTitle("Períodos");

		table.removeAction("INSERT");
		table.removeAction("DELETE");
		table.getAction("EDIT").setUrl("/servlet/admin/period/UpdatePeriod");

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
		
//		table.addAction(newAction("EDIT", "Modificar", "/servlet/admin/period/Update"));
		table.addAction(newAction("DUPLICATE", "Duplicar", ""));
		table.addAction(newAction("OPEN", "Abrir", ""));
		table.addAction(newAction("CLOSE", "Cerrar", ""));

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
