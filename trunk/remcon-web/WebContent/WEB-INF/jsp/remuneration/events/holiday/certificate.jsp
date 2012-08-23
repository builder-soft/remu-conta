<%@page import="cl.buildersoft.framework.util.BSWeb"%>
<%@page import="cl.buildersoft.framework.util.BSUtils"%>
<%@page import="cl.buildersoft.business.beans.HolidayDetail"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="cl.buildersoft.business.beans.Holiday"%>
<%@page import="cl.buildersoft.business.beans.Agreement"%>
<%@page import="cl.buildersoft.framework.util.BSDateTimeUtil"%>
<%@page import="cl.buildersoft.business.beans.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Employee employee = (Employee) request.getAttribute("Employee");
	Agreement agreement = (Agreement) request.getAttribute("Agreement");
	Holiday holiday = (Holiday) request.getAttribute("Holiday");
	Integer days = (Integer) request.getAttribute("Days");
	Date to = (Date) request.getAttribute("To");
	List<HolidayDetail> holidayDetails = (List<HolidayDetail>) request.getAttribute("HolidayDetails");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
h1 {
	text-align: center;
	text-transform: uppercase;
	text-family: Verdana;
}

p {
	text-indent: 50px;
	text-align: justify;
	text-family: Verdana;
	letter-spacing: 3px;
}

td {
	text-align: center;
	text-family: Verdana;
	letter-spacing: 3px;
}

table.info {
	border-width: 1px;
	border-spacing: 0px;
	border-style: outset;
	border-color: gray;
	border-collapse: separate;
	background-color: white;
} /*
table.info th {
	border-width: 1px;
	padding: 0px;
	border-style: inset;
	border-color: gray;
	background-color: white;
	-moz-border-radius: ;
}
*/
table.info td {
	width: 100%;
	border-width: 1px;
	padding: 0px;
	border-style: inset;
	border-color: gray;
	background-color: white;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Certificado</title>
</head>
<body onload="javascript:print()">

	<h1>Comprobante de feraido legal</h1>
	<p>
		En cumplimiento de las disposiciones legales vigentes, se deja
		constancia que el trabajador(a)
		<%=employee.getName() + " " + employee.getLastName1() + " " + employee.getLastName2()%>,
		RUT
		<%=employee.getRut()%>, contratado(a) con fecha
		<%=BSDateTimeUtil.date2LongString(agreement.getStartContract())%>,
		hará uso de
		<%=days%>
		días hábiles de su Feriado Legal entre los días
		<%=BSDateTimeUtil.date2LongString(holiday.getFrom())%>
		y
		<%=BSDateTimeUtil.date2LongString(to)%>, ambos inclusive.
	</p>

	<center>
		<table border="0" style="width: 50%;">
			<tr>
				<%=writeCellWithTable(request, holidayDetails, 1L, "Detalle períodos")%>
				<%=writeCellWithTable(request, holidayDetails, 2L, "Detalle saldo")%>
			</tr>
		</table>
	</center>
	
	<p>
	<table width="100%" border="0">
		<tr>
			<td align="center">______________________</td>
			<td align="center">______________________</td>
		</tr>
		<tr>
			<td align="center">Trabajador</td>
			<td align="center">Recursos Humanos</td>
		</tr>
	</table>
	</p>
	<p>SANTIAGO, <%=BSDateTimeUtil.date2LongString(new Date()) %>
	</p>
</body>
</html>


<%!private String writeCellWithTable( HttpServletRequest request, List <HolidayDetail> holidayDetails, Long type, String title){
	String out = "<td style='width:50%;align:center;' valign='top'>";
	out += "<table class='info'>";
	out += "<caption>"+title+"</caption>";
	out += "<tr>";
	out += "<td>Días</td>";
	out += "<td>Año</td>";
	out += "</tr>";
	for (HolidayDetail holidayDetail : holidayDetails) {
		if (holidayDetail.getHolidayDetailType().equals(type)) {
	out += "<tr>";
	out += "<td>"+BSWeb.formatDecimal(request, holidayDetail.getDays())+"</td>";
	out += "<td>"+holidayDetail.getYear()+"</td>";
	out += "</tr>";}
	}
	out += "</table></td>";
	return out;
}%>