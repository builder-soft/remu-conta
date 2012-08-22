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
	Integer days =(Integer) request.getAttribute("Days");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Certificado</title>
</head>
<body>

	<h1 align="center">COMPROBANTE DE FERIADO LEGAL</h1>
<p>
	En cumplimiento de las disposiciones legales vigentes, se deja
	constancia que el trabajador(a)
	<%=employee.getName() + " " + employee.getLastName1() + " " + employee.getLastName2()%>,
	RUT
	<%=employee.getRut()%>, contratado(a) con fecha
	<%=BSDateTimeUtil.date2LongString(agreement.getStartContract())%>,
	hará uso de <%=days%> días hábiles de su Feriado Legal entre los días Jueves
	09 de Agosto de 2012 y Viernes 10 de Agosto de 2012, ambos inclusive.
</p>
	Correspondientes al siguiente periodo: 1,1 días correspondientes al
	periodo 2008 0,9 días correspondientes al periodo 2009 Saldo de
	vacaciones a esta fecha: 14,1 días correspondientes al periodo 2009
	15,0 días correspondientes al periodo 2010 15,0 días correspondientes
	al periodo 2011 9,2 días correspondientes al periodo 2012


	______________________ Trabajador ______________________ Recursos
	Humanos SANTIAGO, 10 de Agosto de 2012



</body>
</html>