package cl.buildersoft.framework.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BSSecurityTest {
	String crypted = "+yd80m0xf88TYCB6/hEwUQ==";
	String clear = "Claudio Moscoso";

	@Test
	public void testEncript3des() {
		BSSecurity s = new BSSecurity();
		crypted = s.encript3des(clear);

		assertTrue(crypted.equals(crypted));
	}

	@Test
	public void testDecript3des() {
		BSSecurity s = new BSSecurity();
		String response = s.decript3des(crypted);

		assertTrue(response.equals(clear));
	}

}
