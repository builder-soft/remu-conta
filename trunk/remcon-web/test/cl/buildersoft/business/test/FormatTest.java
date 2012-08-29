package cl.buildersoft.business.test;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class FormatTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Locale cl = new Locale("en");

		
 
		System.out.println(DateFormat.getDateInstance(DateFormat.DEFAULT, cl).format(new Date()));
		System.out.println(DateFormat.getDateInstance(DateFormat.SHORT, cl).format(new Date()));
		System.out.println(DateFormat.getDateInstance(DateFormat.MEDIUM, cl).format(new Date()));
		System.out.println(DateFormat.getDateInstance(DateFormat.LONG, cl).format(new Date()));

		 formatNumber(cl);
	}

	private static void formatNumber(Locale cl) {
		try {
			Double n = 1234567890.123456789;

//			Locale[] ls = Locale.getAvailableLocales();
//			for (Locale l : ls) {
//				System.out.println(l.getCountry() + ", " + l.getDisplayCountry() + ", " + l.getDisplayLanguage());
//			}

			NumberFormat format = NumberFormat.getNumberInstance(cl);

			System.out.println(format.format(n));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
