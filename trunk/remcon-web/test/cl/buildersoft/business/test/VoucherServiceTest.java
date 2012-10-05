package cl.buildersoft.business.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cl.buildersoft.business.beans.Voucher;
import cl.buildersoft.business.service.VoucherService;
import cl.buildersoft.business.service.impl.VoucherServiceImpl;
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
		new BSmySQL().closeConnection(conn);
	}

	@Test
	public void testListPending1() {
		List<Voucher> list = service.listPending(conn, 1L);
		assertTrue(list != null);
	}

	@Test
	public void testSave1() {
		Voucher voucher = new Voucher();
		voucher.setAccountingDate(new Date());
		voucher.setNumber(123);
		voucher.setUser(1L);
		voucher.setVoucherStatus(1L);
		voucher.setVoucherType(1L);

		assertTrue(service.save(conn, voucher));
	}

	@Test
	public void testSave2() {
		Voucher voucher = new Voucher();
		voucher.setAccountingDate(new Date());
		voucher.setNumber(123);
		voucher.setUser(1L);
		voucher.setVoucherStatus(2L);
		voucher.setVoucherType(1L);

		assertFalse(service.save(conn, voucher));
	}

	@Test
	public void testCommit1() {
		Voucher voucher = new Voucher();
		voucher.setAccountingDate(new Date());
		voucher.setNumber(123);
		voucher.setUser(1L);
		voucher.setVoucherStatus(1L);
		voucher.setVoucherType(1L);

		service.save(conn, voucher);

		assertTrue(service.commit(conn, voucher.getId()));
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDetail() {
		fail("Not yet implemented");
	}

}
