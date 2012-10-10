package cl.buildersoft.web.servlet.conta.voucher;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Voucher;
import cl.buildersoft.business.service.VoucherService;
import cl.buildersoft.business.service.impl.VoucherServiceImpl;
import cl.buildersoft.framework.database.BSmySQL;

@WebServlet("/servlet/conta/voucher/SaveVoucher")
public class SaveVoucher extends HttpServlet {
	private static final long serialVersionUID = 2166978785911620100L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long voucherId = Long.parseLong(request.getParameter("cId"));
		Long voucherType = Long.parseLong(request.getParameter("cVoucherType"));

		String numberString = request.getParameter("cNumber");
		Integer number = numberString == null || numberString.trim().length() == 0 ? null : Integer.parseInt(numberString);

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		VoucherService vs = new VoucherServiceImpl();
		Voucher voucher = vs.get(conn, voucherId);

		voucher.setVoucherType(voucherType);
		voucher.setNumber(number);

		vs.save(conn, voucher);

		mysql.closeConnection(conn);

		PrintWriter writter = response.getWriter();
		writter.write("OK");
		writter.flush();
		writter.close();

	}

}
