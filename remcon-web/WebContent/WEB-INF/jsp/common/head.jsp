<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<LINK rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/default.css?<%=Math.random() %>" />
<LINK rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/jdMenu.css?<%=Math.random() %>" />
		
<title>Buildersoft &radic;</title>
<script>
	var contextPath = "${pageContext.request.contextPath}";
	var speed = "fast";
</script>
<script src="${pageContext.request.contextPath}/js/common/framework.js"></script>
<!-- 
<script src="${pageContext.request.contextPath}/js/common/menu.js"></script>
 -->
<script
	src="${pageContext.request.contextPath}/js/common/jquery-1.7.1.js"></script>
	
<script src="${pageContext.request.contextPath}/js/common/jdMenu/jquery.dimensions.js"></script>
<script src="${pageContext.request.contextPath}/js/common/jdMenu/jquery.jdMenu.js"></script>
<script src="${pageContext.request.contextPath}/js/common/jdMenu/jquery.jdMenu.packed.js"></script>	
	
	<script type="text/javascript">
	$(function() {
		$('ul.jd_menu').jdMenu();
	});
</script>
</head>



<body marginwidth="25" marginheight="25" bgcolor="#EDEDED"
	onload="javaScript:loadFormat();try{onLoadPage();}catch(e){}">

	<table border="0" style="width: 100%">
		<tr>
			<td style="width: 18%">&nbsp;</td>
			<td>&nbsp;</td>
			<td style="width: 10%" align="right"><a
				href="${pageContext.request.contextPath}/jsp/login/logout.jsp">
					<img src="${pageContext.request.contextPath}/img/common/logout.gif">
			</a></td>
		</tr>
		<tr>
			<td valign="top">