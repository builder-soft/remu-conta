package cl.buildersoft.business.conta;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cl.buildersoft.business.beans.Voucher;
import cl.buildersoft.business.service.VoucherService;
import cl.buildersoft.business.service.impl.VoucherServiceImpl;
import cl.buildersoft.business.test.AbstractTestUtil;
import cl.buildersoft.framework.database.BSmySQL;

public class VoucherServiceTest extends AbstractTestUtil {
	private VoucherService service = null;
	private Connection conn = null;

	@Before
	public void setUp() throws Exception {
		service = new VoucherServiceImpl();
		conn = getConnection();
	}

	@After
	public void tearDown() throws Exception {
		BSmySQL mysql = new BSmySQL();
		mysql.update(conn, "DELETE FROM tVoucher;", null);
		mysql.closeConnection(conn);
	}

	@Test
	public void testListPending1() {
		List<Voucher> list = service.listPending(conn, 1L);
		assertTrue(list.size() == 0);
	}

	@Test
	public void testSave1() {
		Voucher voucher = getVoucher();
		voucher.setVoucherStatus(1L);

		assertTrue(service.save(conn, voucher));
	}

	@Test
	public void testSave2() {
		Voucher voucher = getVoucher();
		voucher.setVoucherStatus(2L);

		assertFalse(service.save(conn, voucher));
	}

	@Test
	public void testCommit1() {
		Voucher voucher = getVoucher();

		service.save(conn, voucher);

		assertTrue(service.commit(conn, voucher.getId()));
	}

	@Test
	public void testDelete() {
		Voucher voucher = getVoucher();
		service.save(conn, voucher);
		Long id = voucher.getId();
		service.delete(conn, id);

		Voucher v = service.get(conn, id);

		assertTrue(v == null);
	}

	@Test
	public void testGet1() {
		Voucher voucher = getVoucher();
		service.save(conn, voucher);

		Voucher v = service.get(conn, voucher.getId());
		assertTrue(voucher.getId().equals(v.getId()));
	}

	@Test
	public void testGet2() {
		Voucher voucher = getVoucher();
		service.save(conn, voucher);

		Voucher v = service.get(conn, 2L);
		assertTrue(v == null);

	}

	@Test
	public void testGetDetail() {
		assertTrue(service.getDetail(conn, 1L) != null);
	}

	private Voucher getVoucher() {
		Voucher voucher = new Voucher();
		voucher.setAccountingDate(new Date());
		voucher.setCreationTime(new Timestamp(System.currentTimeMillis()));
		voucher.setNumber(432);
		voucher.setUser(1L);
		voucher.setVoucherStatus(1L);
		voucher.setVoucherType(1L);
		System.out.println(voucher.toString());
		return voucher;
	}
}
