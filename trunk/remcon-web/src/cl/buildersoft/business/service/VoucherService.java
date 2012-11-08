package cl.buildersoft.business.service;

import java.sql.Connection;
import java.util.List;

import cl.buildersoft.business.beans.Voucher;
import cl.buildersoft.business.beans.VoucherDetail;

public interface VoucherService {
	public List<Voucher> listPending(Connection conn, Long userId);

	public Boolean save(Connection conn, Voucher voucher);

	public Voucher create(Connection conn, Long userId);

	public Boolean commit(Connection conn, Long voucherId);

	public Boolean delete(Connection conn, Long voucherId);

	public Voucher get(Connection conn, Long voucherId);

	public List<VoucherDetail> getDetail(Connection conn, Long voucherId);

//	public Long getLastNumber(Connection conn, Voucher voucher);
}
