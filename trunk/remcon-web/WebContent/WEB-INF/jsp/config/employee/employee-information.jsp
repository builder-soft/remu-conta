<%@page import="cl.buildersoft.business.beans.Comuna"%>
<%@page import="cl.buildersoft.framework.database.BSBeanUtils"%>
<%@page import="java.sql.Connection"%>
<%@page import="cl.buildersoft.framework.database.BSmySQL"%>
<%@page import="cl.buildersoft.framework.type.BSFieldType"%>
<%@page import="cl.buildersoft.framework.type.BSFieldDataType"%>
<%@page import="cl.buildersoft.framework.type.BSTypeFactory"%>
<%@page import="cl.buildersoft.business.beans.Employee"%>
<%=write_Employee_Information(session, request)%>

<%!private String write_Employee_Information(HttpSession session, HttpServletRequest request) {
		Employee employee = (Employee) request.getAttribute("Employee");

		BSFieldDataType dataType = BSTypeFactory.create(BSFieldType.Date);
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		String out = "<table>";
		out += "<tr><td class='cLabel'>RUT:</td>";
		out += "<td class='cData'>" + employee.getRut() + "</td>";

		out += "<td class='cLabel'>Nombre:</td>";
		out += "<td class='cData'>" + employee.getName() + " " + employee.getLastName1() + " " + employee.getLastName2()
				+ "</td></tr>";

		out += "<tr><td class='cLabel'>Fecha Nacimiento:</td>";
		out += "<td class='cData'>" + dataType.format(conn, employee.getBirthDate()) + "</td>";

		out += "<td class='cLabel'>Dierección:</td>";
		out += "<td class='cData'>" + employee.getAddress() + "</td></tr>";

		out += "<tr><td class='cLabel'>Comuna:</td>";
		out += "<td class='cData'>" + getComunaName(conn, employee.getComuna()) + "</td>";

		out += "<td class='cLabel'>Teléfono:</td>";
		out += "<td class='cData'>" + employee.getPhone() + "</td></tr>";

		out += "<tr><td class='cLabel'>Celular:</td>";
		out += "<td class='cData'>" + employee.getMovil() + "</td>";

		out += "<td class='cLabel'>Correo electrónico:</td>";
		out += "<td class='cData'>" + employee.getEmail() + "</td></tr>";

		out += "</table>";

		return out;
	}

	private String getComunaName(Connection conn, Long comunaId) {
		BSBeanUtils bu = new BSBeanUtils();
		Comuna comuna = new Comuna();
		comuna.setId(comunaId);
		bu.search(conn, comuna);
		
		return comuna.getName();
	}%>





