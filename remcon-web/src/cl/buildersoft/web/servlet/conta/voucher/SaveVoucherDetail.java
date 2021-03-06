package cl.buildersoft.web.servlet.conta.voucher;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.VoucherDetail;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.web.servlet.ajax.AbstractAjaxServlet;

@WebServlet("/servlet/conta/voucher/SaveVoucherDetail")
public class SaveVoucherDetail extends AbstractAjaxServlet {
	private static final long serialVersionUID = -6039113676579640546L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long voucherId = getParameterAsLong(request, "cId");

		Long detailId = getParameterAsLong(request, "cDetailId");

		Long chartAccount = getParameterAsLong(request, "cChartAccount");
		Long documentType = getParameterAsLong(request, "cDocumentType");
		Integer documentNumber = getParameterAsInteger(request, "cDocumentNumber");
		BigDecimal netAmount = getParameterAsBigDecimal(request, "cNetAmount");
		Long costCenter = getParameterAsLong(request, "cCostCenter");

		VoucherDetail voucherDetail = new VoucherDetail();
		voucherDetail.setId(detailId);
		voucherDetail.setChartAccount(chartAccount);
		voucherDetail.setCostCenter(costCenter);
		voucherDetail.setDocumentNumber(documentNumber);
		voucherDetail.setDocumentType(documentType);
		voucherDetail.setNetAmount(netAmount);
		voucherDetail.setVoucher(voucherId);

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtils bu = new BSBeanUtils();

		bu.save(conn, voucherDetail);
		mysql.closeConnection(conn);

		endWrite(writeToBrowser(response, "OK"));
	}

}
