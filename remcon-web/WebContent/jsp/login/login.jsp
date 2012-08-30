<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.getSession().invalidate();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<LINK rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/default.css?<%=Math.random()%>" />

<title>Ingreso de usuario</title>
<script>
	function posImage() {
		var img = document.getElementById("html5");
		//alert(screen.height);
		//alert(img.height);
		//img.top = (screen.height+500) + 'px';
//		img.vspace = (screen.availHeight / 2);
		img.style.position= 'absolute';
		
		//img.style.pixelTop = (screen.width-1000);
		img.style.pixelTop = (screen.height-300);
	}
</script>
</head>
<body onload="javascript:posImage()">

	<form
		action="${pageContext.request.contextPath}/login/ValidateLoginServlet"
		method="post">
		<span class="cLabel">Mail:</span> <input type="text" name="mail"
			placeholder="Correo personal"><br> <span class="cLabel">Clave:
		</span> <input type="password" placeholder="Clave de acceso" name="password"><br>

		<button type="submit">Acceder...</button>
	</form>
	<img src="${pageContext.request.contextPath}/img/html5-icon.png" 
		width="80px" id="html5" alt="Pensando en HTML5 como estandar" />
</body>
</html>