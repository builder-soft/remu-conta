package cl.buildersoft.web.servlet.conta.voucher;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Voucher;
import cl.buildersoft.business.beans.VoucherType;
import cl.buildersoft.business.service.VoucherService;
import cl.buildersoft.business.service.impl.VoucherServiceImpl;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;

/**
 * Servlet implementation class ReadVoucher
 */
@WebServlet("/servlet/conta/voucher/ReadVoucher")
public class ReadVoucher extends HttpServlet {
	private static final long serialVersionUID = -7985070005740174266L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/WEB-INF/jsp/conta/voucher/edit-voucher.jsp";
		VoucherService vs = new VoucherServiceImpl();
		BSBeanUtils bu = new BSBeanUtils();

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		Long voucherId = getVoucherId(request);
		Voucher voucher = vs.get(conn, voucherId);

		List<VoucherType> voucherTypeList = (List<VoucherType>) bu.listAll(conn, new VoucherType());

		request.setAttribute("VoucherTypeList", voucherTypeList);
		request.setAttribute("Voucher", voucher);

		mysql.closeConnection(conn);
		request.getRequestDispatcher(url).forward(request, response);
	}

	private Long getVoucherId(HttpServletRequest request) {
		Long out = null;
		String voucherId = request.getParameter("VoucherId");
		if (voucherId == null) {
			out = (Long) request.getAttribute("VoucherId");
		} else {
			out = Long.parseLong(voucherId);
		}
		return out;
	}

}
