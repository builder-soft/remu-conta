package cl.buildersoft.business.service.impl;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cl.buildersoft.business.beans.Voucher;
import cl.buildersoft.business.beans.VoucherDetail;
import cl.buildersoft.business.service.VoucherService;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;

public class VoucherServiceImpl implements VoucherService {
	private static final long PENDING = 1L;
	private static final long DONE = 2L;

	@Override
	public List<Voucher> listPending(Connection conn, Long userId) {
		BSBeanUtils bu = new BSBeanUtils();
		List<Voucher> out = (List<Voucher>) bu.list(conn, new Voucher(), "cVoucherStatus=? AND cUser=? ORDER BY cCreationTime",
				PENDING, userId);
		return out;
	}

	@Override
	public Boolean save(Connection conn, Voucher voucher) {
		Boolean canSave = voucher.getVoucherStatus().equals(PENDING);
		if (canSave) {
			BSBeanUtils bu = new BSBeanUtils();
			bu.save(conn, voucher);
		}
		return canSave;
	}

	@Override
	public Boolean accounting(Connection conn, Long voucherId) {
		Voucher voucher = new Voucher();
		BSBeanUtils bu = new BSBeanUtils();
		Boolean commited = Boolean.FALSE;

		voucher.setId(voucherId);

		Boolean found = bu.search(conn, voucher);
		if (found) {
			if (isPending(voucher)) {
				voucher.setVoucherStatus(DONE);
				voucher.setNumber(getLastNumber(conn, voucher) + 1);

				bu.save(conn, voucher);
				commited = Boolean.TRUE;
			}
		}
		return found && commited;
	}

	@Override
	public Boolean delete(Connection conn, Long voucherId) {
		Voucher voucher = new Voucher();
		BSBeanUtils bu = new BSBeanUtils();
		Boolean deleted = Boolean.FALSE;

		voucher.setId(voucherId);

		Boolean found = bu.search(conn, voucher);
		if (found) {
			if (isPending(voucher)) {
				bu.delete(conn, voucher);
				deleted = Boolean.TRUE;
			}
		}
		return found && deleted;
	}

	private Boolean isPending(Voucher voucher) {
		return voucher.getVoucherStatus().equals(PENDING);
	}

	@Override
	public Voucher get(Connection conn, Long voucherId) {
		Voucher voucher = new Voucher();
		BSBeanUtils bu = new BSBeanUtils();
		voucher.setId(voucherId);
		if (!bu.search(conn, voucher)) {
			voucher = null;
		}
		return voucher;
	}

	@Override
	public List<VoucherDetail> getDetail(Connection conn, Long voucherId) {
		List<VoucherDetail> list = new ArrayList<VoucherDetail>();
		return list;
	}

	@Override
	public Voucher create(Connection conn, Long userId) {
		Voucher voucher = new Voucher();
		voucher.setVoucherType(1L);
		voucher.setAccountingDate(null);
		voucher.setCreationTime(new Timestamp(System.currentTimeMillis()));
		voucher.setNumber(null);
		voucher.setUser(userId);
		voucher.setVoucherStatus(PENDING);
		save(conn, voucher);
		return voucher;
	}

	private Integer getLastNumber(Connection conn, Voucher voucher) {
		BSmySQL mysql = new BSmySQL();

		String lastNumber = mysql.queryField(conn, "SELECT MAX(cNumber) FROM tVoucher WHERE cVoucherType = ?;",
				voucher.getVoucherType());

		Integer out = lastNumber == null ? 0 : Integer.parseInt(lastNumber);

		return out;
	}
}
