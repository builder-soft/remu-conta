package cl.buildersoft.web.servlet.invoice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.parentChild.BSParentChild;
import cl.buildersoft.web.servlet.common.parentChild.HttpServletParentChild;

@WebServlet("/servlet/invoice/InvoiceManager")
public class InvoiceManager extends HttpServletParentChild {
	private static final long serialVersionUID = -3816227256019074321L;

	@Override
	protected BSParentChild getBSParentChild(HttpServletRequest request) {
		BSParentChild invoice = new BSParentChild("remcon", "tParent", "tChild");

		invoice.setTitle("Factura");

		return invoice;
	}

}
