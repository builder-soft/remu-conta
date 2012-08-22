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

p,td {
	text-indent: 50px;
	text-align: justify;
	text-family: Verdana;
	letter-spacing: 3px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Certificado</title>
</head>
<body>

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
	<p>
		Correspondientes al siguiente periodo: <br>1,1 días
		correspondientes al periodo 2008 <br> 0,9 días correspondientes
		al periodo 2009 <br> <br> Saldo de vacaciones a esta fecha:
		<br> 14,1 días correspondientes al periodo 2009 <br> 15,0
		días correspondientes al periodo 2010 <br>15,0 días
		correspondientes al periodo 2011 <br> 9,2 días correspondientes
		al periodo 2012 <br> <br>
	</p>

	<p>
	<table width="100%">
		<tr>
			<td align="center">______________________</td>
			<td align="center">______________________</td>
		</tr>
		<tr>
			<td align="center">Trabajador</td>
			<td align="center">Recursos Humanos</td>
		</tr>
	</table>
	<br> SANTIAGO, 10 de Agosto de 2012
	</p>
</body>
</html>