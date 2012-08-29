package cl.buildersoft.business.test;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class FormatTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Locale cl = new Locale("cl");
//		Locale cl = new Locale("de");
		Locale cl = new Locale("es");
//		Locale cl = Locale.GERMAN; 
//		Locale cl = Locale.CANADA; 

		// formatDate(cl);
//		listLocales();

		formatNumber(cl);
	}

	private static void listLocales() {
		Locale[] ls = Locale.getAvailableLocales();
		for (Locale l : ls) {
			System.out.println(l.getCountry() + ", " + l.getDisplayCountry() + ", " + l.getDisplayLanguage());
		}
	}

	private static void formatDate(Locale cl) {
		System.out.println(DateFormat.getDateInstance(DateFormat.DEFAULT, cl).format(new Date()));
		System.out.println(DateFormat.getDateInstance(DateFormat.SHORT, cl).format(new Date()));
		System.out.println(DateFormat.getDateInstance(DateFormat.MEDIUM, cl).format(new Date()));
		System.out.println(DateFormat.getDateInstance(DateFormat.LONG, cl).format(new Date()));
	}

	private static void formatNumber(Locale cl) {
		try {
			Double n1 = 1234567890.123456789;
			Double n2 = 0.12;
			Double n3 = -41000.12;
			Double n4 = 0D;

//			NumberFormat format = NumberFormat.getNumberInstance(cl);
			DecimalFormat fmt = (DecimalFormat) NumberFormat.getNumberInstance(cl);
//			fmt.applyLocalizedPattern("###,###,##0.0###");
			fmt.applyLocalizedPattern("###.###.##0,#####");

//			System.out.println(fmt.format(n1));
//			System.out.println(fmt.format(n2));
			System.out.println("CL " + fmt.format(n1));
			System.out.println("CL " + fmt.format(n2));
			System.out.println("CL " + fmt.format(n3));
			System.out.println("CL " + fmt.format(n4));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
