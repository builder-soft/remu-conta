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
import cl.buildersoft.business.beans.DocumentType;
import cl.buildersoft.business.beans.Voucher;
import cl.buildersoft.business.beans.VoucherDetail;
import cl.buildersoft.business.beans.VoucherStatus;
import cl.buildersoft.business.beans.VoucherType;
import cl.buildersoft.business.service.VoucherService;
import cl.buildersoft.business.service.impl.VoucherServiceImpl;
import cl.buildersoft.framework.beans.User;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSDateTimeUtil;

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
		List<DocumentType> documentTypeList = (List<DocumentType>) bu.listAll(conn, new DocumentType());
		List<BusinessArea> businessAreaList = (List<BusinessArea>) bu.listAll(conn, new BusinessArea());
		List<VoucherDetail> voucherDetailList = (List<VoucherDetail>) bu.list(conn, new VoucherDetail(), "cVoucher=?", voucherId);
		
		
		request.setAttribute("VoucherDetailList", voucherDetailList);
		request.setAttribute("VoucherTypeList", voucherTypeList);
		request.setAttribute("DocumentTypeList", documentTypeList);
		request.setAttribute("BusinessAreaList", businessAreaList);
		request.setAttribute("Voucher", voucher);
		request.setAttribute("VoucherStatus", getVoucherStatus(conn, voucher));
		request.setAttribute("UserVoucher", getUserVoucher(conn, voucher));

		mysql.closeConnection(conn);
		request.getRequestDispatcher(url).forward(request, response);
	}

	private Object getUserVoucher(Connection conn, Voucher voucher) {
		BSBeanUtils bu = new BSBeanUtils();
		User user = new User();
		user.setId(voucher.getUser());
		bu.search(conn, user);

		return user.getName();
	}

	private String getVoucherStatus(Connection conn, Voucher voucher) {
		BSBeanUtils bu = new BSBeanUtils();
		VoucherStatus voucherStatus = new VoucherStatus();
		voucherStatus.setId(voucher.getVoucherStatus());
		bu.search(conn, voucherStatus);

		return voucherStatus.getName();
	}

	private Long getVoucherId(HttpServletRequest request) {
		Long out = null;

		Object voucherObject = request.getAttribute("VoucherId");

		if (voucherObject != null) {
			out = (Long) voucherObject;
		} else {
			String voucherId = request.getParameter("VoucherId");
			out = Long.parseLong(voucherId);
		}

		return out;
	}

}
