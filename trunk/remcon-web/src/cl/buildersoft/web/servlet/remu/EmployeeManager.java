package cl.buildersoft.web.servlet.remu;

import javax.servlet.annotation.WebServlet;

import cl.buildersoft.framework.beans.BSHeadConfig;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.BSHttpServlet;

/**
 * Servlet implementation class EmployeeManager
 */
@WebServlet("/servlet/remu/EmployeeManager")
public class EmployeeManager extends BSHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected BSTableConfig getBSTableConfig() {
		BSTableConfig tabla = new BSTableConfig ("remu", "tEmployee");
		
		return tabla;
	}

	@Override
	protected BSHeadConfig getBSHeadConfig() {
		// TODO Auto-generated method stub
		return null;
	}
       
    

}
