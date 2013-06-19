package cl.buildersoft.web.filter;

import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSConfig;
import cl.buildersoft.framework.util.BSDateTimeUtil;
import cl.buildersoft.framework.util.BSSecurity;

/**
 * Servlet Filter implementation class LicenseValidation
 * 
 * @WebFilter("/LicenseValidation")
 */

@WebFilter(urlPatterns = { "/servlet/*" }, dispatcherTypes = { DispatcherType.REQUEST })
public class LicenseValidation implements Filter {

	public LicenseValidation() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest rq, ServletResponse rs, FilterChain chain) throws IOException, ServletException {
		BSConfig config = new BSConfig();
		BSmySQL mysql = new BSmySQL();
		HttpServletRequest request = (HttpServletRequest) rq;
		HttpServletResponse response = (HttpServletResponse) rs;

		// String failUrl = request.getContextPath() +
		// "/jsp/error/system-fail.jsp";
		// String failUrl = "/jsp/error/system-fail.jsp";
		String failUrl = "/jsp/login/logout.jsp";
		// String failUrl = "/WEB-INF/jsp/common/no-access.jsp";
		if (request.getSession(false) != null) {
			Connection conn = mysql.getConnection(request);
			String license = config.getString(conn, "LICENSE");
			Boolean success = true;
			try {
				license = new BSSecurity().decript3des(license);
				String hostName = request.getLocalName();
				if (!hostName.equalsIgnoreCase("localhost")) {
					success = hostName.equalsIgnoreCase(license);
				}
			} catch (Exception e) {
				success = Boolean.FALSE;
			}

			if (success || validateLicense(license)) {
				chain.doFilter(rq, rs);
			} else {
				request.getSession().invalidate();
				// response.sendRedirect(failUrl);
				request.getRequestDispatcher(failUrl).forward(request, response);
			}
		} else {
			request.getRequestDispatcher(failUrl).forward(request, response);
		}
	}

	private boolean validateLicense(String license) {
		Boolean validLicense = null;
		Double rnd = Math.random();
		rnd *= 100;
		Integer random = rnd.intValue() + 1;
		if (license == null || license.length() < 8) {
			validLicense = false;
		} else {
			Calendar expireDate = license2Calendar(license.substring(0, 8));
			Integer dateDiff = dateDiff(expireDate);

			validLicense = (random >= dateDiff);
			System.out.println("random: " + random + " >= dateDiff: " + dateDiff + " = " + validLicense);
		}
		return validLicense;
	}

	private Calendar license2Calendar(String license) {
		Calendar out = null;
		if (license.length() == 8) {
			String year = license.substring(0, 4);
			String month = license.substring(4, 6);
			String day = license.substring(6, 8);
			String date = year + "-" + month + "-" + day;

			// System.out.println(date);
			/**
			 * Boolean dateValid = Boolean.TRUE; try { dateValid =
			 * BSDateTimeUtil.isValidDate(date, "yyyy-MM-dd"); } catch
			 * (Exception e) { e.printStackTrace(); dateValid = Boolean.FALSE; }
			 */
			if (BSDateTimeUtil.isValidDate(date, "yyyy-MM-dd")) {
				out = Calendar.getInstance();
				out.set(Calendar.YEAR, Integer.parseInt(year));
				out.set(Calendar.MONTH, Integer.parseInt(month) - 1);
				out.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
			} else {
				out = Calendar.getInstance();
				out.set(Calendar.YEAR, 2000);
				out.set(Calendar.MONTH, 0);
				out.set(Calendar.DAY_OF_MONTH, 1);

			}
			// System.out.println(BSDateTimeUtil.calendar2String(out,
			// "yyyy-MM-dd"));
		}
		return out;
	}

	private Integer dateDiff(Calendar expireDate) {
		Integer out = 101;
		if (expireDate != null) {
			Calendar start = Calendar.getInstance();
			long diff = start.getTimeInMillis() - expireDate.getTimeInMillis();
			Long diffDays = diff / (24 * 60 * 60 * 1000);
			out = diffDays.intValue();
		}
		return out;
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
