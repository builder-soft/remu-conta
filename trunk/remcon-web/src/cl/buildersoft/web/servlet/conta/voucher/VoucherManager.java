package cl.buildersoft.web.servlet.conta.voucher;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.BusinessArea;
import cl.buildersoft.business.beans.Voucher;
import cl.buildersoft.business.beans.VoucherType;
import cl.buildersoft.business.service.VoucherService;
import cl.buildersoft.business.service.impl.VoucherServiceImpl;
import cl.buildersoft.framework.beans.User;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;

@WebServlet("/servlet/conta/voucher/VoucherManager")
public class VoucherManager extends HttpServlet {
	private static final long serialVersionUID = -4939813797311895299L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VoucherService vs = new VoucherServiceImpl();
		String url = null;

		Boolean force = Boolean.FALSE;
		String forceString = request.getParameter("Force");
		if (forceString != null) {
			force = forceString.equals("1");
		}

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		User user = (User) request.getSession().getAttribute("User");

		List<Voucher> voucherList = vs.listPending(conn, user.getId());

		if (voucherList.size() == 0 || force) {
			Voucher voucher = vs.create(conn, user.getId());
			// vs.save(conn, voucher);

			request.setAttribute("VoucherId", voucher.getId());

			url = "/servlet/conta/voucher/ReadVoucher";
		} else {
			BSBeanUtils bu = new BSBeanUtils();
			List<VoucherType> voucherTypeList = (List<VoucherType>) bu.listAll(conn, new VoucherType());
			List<BusinessArea> businessAreaList = (List<BusinessArea>) bu.listAll(conn, new BusinessArea());

			request.setAttribute("VoucherTypeList", voucherTypeList);
			request.setAttribute("VoucherList", voucherList);
			request.setAttribute("BusinessAreaList", businessAreaList);
			url = "/WEB-INF/jsp/conta/voucher/voucher-list.jsp";
		}
		mysql.closeConnection(conn);
		request.getRequestDispatcher(url).forward(request, response);

	}

}
