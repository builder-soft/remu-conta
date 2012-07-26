<%@ page import="cl.buildersoft.framework.beans.Menu"%>
<%@ page import="cl.buildersoft.framework.beans.Submenu"%>
<%@ page import="cl.buildersoft.framework.beans.Option"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<ul class="jd_menu">

	<%=write_menu_in_menu_jsp(session, request)%>


	<!-- 
		<li>Sistema
			<ul>
				<li><a href="/remcon-web/servlet/system/user/UserManager">Usuarios</a></li>
				<ul></ul>
				<li><a href="/remcon-web/servlet/system/roleDef/RoleDef">Permisos
						de roles</a></li>
				<ul></ul>
				<li><a
					href="/remcon-web/servlet/system/changepassword/SearchPassword">Cambio
						de clave</a></li>
				<ul></ul>
				<li><a href="/remcon-web/servlet/system/role/RolManager">Definición
						de Roles</a></li>
				<ul></ul>
			</ul>
		</li>
		<li>Remuneraciones
			<ul>
				<li><a
					href="/remcon-web/servlet/remuneration/events/EventsEmployeeServlet">Eventos
						de Empleados</a></li>
				<ul></ul>
			</ul>
		</li>
	 -->
</ul>

</td>
<td valign="top" width="5%" colspan="2"><%!private String write_menu_in_menu_jsp(HttpSession session, HttpServletRequest request) {
		Menu menuUser = (Menu) session.getAttribute("Menu");
		String out = "";
		if (menuUser != null) {
			String ctxPath = request.getContextPath();
			List<Submenu> main = menuUser.list();
			Option opt = null;
			String url = null;
			String label = null;
			for (Submenu submenu : main) {
				opt = submenu.getOption();

				out += "<li>";
				out += option2String(opt, ctxPath, true);
				out += writeSubMenu(submenu, ctxPath);
				out += "</li>\n";
			}
		}
		return out;
	}

	private String option2String(Option opt, String contextPath, Boolean isRoot) {
		String out = "";
		String url = opt.getUrl();
		String label = opt.getLabel();
		String urlPath = "";
		String endTag = "";
		String startTag = "";

		url = url == null ? "" : url;

		if (url.length() > 0) {
			startTag = "<a ";
			if (url.startsWith("/")) {
				url = contextPath + url;
				endTag = "</a>";
			}
			out += startTag;
			out += "href=\"" + url + "\">";
			endTag = "</a>";
		} else {
			if (isRoot) {
				out += "";
				endTag = "";
			} else {
				out += "<li>";
				endTag = "</li>";

			}
		}

		out += label + endTag + "";

		return out;
	}

	private String writeSubMenu(Submenu menu, String contextPath) {
		Option opt = null;
		String url = null;
		String label = null;
		List<Submenu> menuList = menu.list();
		Integer count = menuList.size();
		String out = count > 0 ? "<ul>" : "";

		for (Submenu submenu : menuList) {
			out += option2String(submenu.getOption(), contextPath, false);
			out += writeSubMenu(submenu, contextPath);
		}
		out += count > 0 ? "</ul>\n" : "\n";
		return out;
	}%>