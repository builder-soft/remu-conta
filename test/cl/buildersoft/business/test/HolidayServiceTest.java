package cl.buildersoft.business.test;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cl.buildersoft.business.beans.HolidayDevelop;
import cl.buildersoft.business.service.HolidayService;
import cl.buildersoft.business.service.impl.HolidayServiceImpl;
import cl.buildersoft.framework.database.BSmySQL;

public class HolidayServiceTest extends AbstractTestUtil {
	Connection conn = null;

	@Before
	public void setUp() throws Exception {
		conn = getConnection(new BSmySQL());
	}

	@After
	public void tearDown() throws Exception {
		conn.close();
		System.out.println("--------------------------------------");
	}

	@Test
	public void testListDevelop1() {
		HolidayService hs = new HolidayServiceImpl();
		List<HolidayDevelop> list = hs.listDevelop(conn, 1L);

		showList(list);
		assertTrue(list.size() > 0);
	}

	@Test
	public void testListDevelop2() {
		HolidayService hs = new HolidayServiceImpl();
		List<HolidayDevelop> list = hs.listDevelop(conn, 2L);

		showList(list);
		assertTrue(list.size() > 0);
	}

	private void showList(List<HolidayDevelop> list) {
		System.out.println("  \t    \tNormal\t     \t       \tCreeping      \t       \tTotal");
		System.out.println("Id\tYear\tRatio \tTaken\tBalance\tRatio\tTaken \tBalance\tBalance");

		for (HolidayDevelop hd : list) {
			System.out.print(hd.getId() + "\t");
			System.out.print(hd.getYear() + "\t");
			System.out.print(formatNumber(hd.getNormalRatio()) + "\t");
			System.out.print(formatNumber(hd.getNormalTaken()) + "\t");
			System.out.print(formatNumber(hd.getNormalBalance()) + "\t");
			System.out.print(formatNumber(hd.getCreepingRatio() ) + "\t");
			System.out.print(formatNumber(hd.getCreepingTaken()) + "\t");
			System.out.print(formatNumber(hd.getCreepingBalance()) + "\t");
			System.out.print(formatNumber(hd.getTotalBalance()) + "\t");

			System.out.println();
		}

	}

	private String formatNumber(Double n) {
		String out = "NULL";
		if (n != null) {
			DecimalFormat formatter = new DecimalFormat("#.###");
			out = formatter.format(n);
		}
		return out;
	}
}
