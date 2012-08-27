package cl.buildersoft.business.test;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class FormatTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String x = "-1234.5678";
			NumberFormat nf = NumberFormat.getInstance();

			DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
			String decimalSeparator = String.valueOf(decimalFormatSymbols.getDecimalSeparator());
			String groupingSeparator = String.valueOf(decimalFormatSymbols.getGroupingSeparator());

			System.out.println("getDecimalSeparator()" + decimalSeparator);
			System.out.println("getGroupingSeparator()" + groupingSeparator);
			// System.out.println(nf.parseObject(x));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
