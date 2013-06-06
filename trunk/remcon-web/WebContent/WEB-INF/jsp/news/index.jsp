<!DOCTYPE html>
<html>
<head>
<title>Responsive Menu - ProgrammingFree</title>
<!-- Mobile viewport optimized -->
<meta name="viewport" content="width=device-width">
<!-- CSS -->
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" type="text/css" />
<!--Scripts-->
<script src="${pageContext.request.contextPath}/js/common//jquery-1.7.1.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js" type="text/javascript"></script>

</head>
<body>
	<div class="container-fluid">
		<header>
			<h2>Menu</h2>


			<div class="row-fluid">
				<div class="navbar navbar-inverse">
					<div class="navbar-inner">
						<div class="container-fluid">
							<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span
								class="icon-bar"></span> <span class="icon-bar"></span>
							</a>
							<div class="nav-collapse collapse">



								<ul class="nav">
									<li class="active"><a href="#"><i class="icon-home icon-white"></i> Home</a></li>

									<li><a href="#">Features</a></li>
									<li><a href="">Pricing</a></li>
									<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Products<b class="caret"></b></a>
										<ul class="dropdown-menu">
											<li><a href="#">Latest Products</a></li>
											<li><a href="#">Popular Products</a></li>
										</ul></li>
									<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Membership<b class="caret"></b></a>
										<ul class="dropdown-menu">
											<li><a href="#">Personal Membership</a></li>
											<li><a href="#">Premium Membership</a></li>
										</ul></li>
									<li><a href="#">Offers</a></li>
									<li><a href="#">Gallery</a></li>
									<li><a href="#">About Us</a></li>
									<li><a href="#">Contact</a></li>
									<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Dropdown <b class="caret"></b></a>
										<ul class="dropdown-menu">
											<li><a href="#">Action</a></li>
											<li><a href="#">Another action</a></li>
										</ul></li>
								</ul>
							</div>
							<!-- /.nav-collapse -->
						</div>
						<!-- /.container -->
					</div>
					<!-- /.navbar-inner -->
				</div>
				<!-- /.navbar -->
			</div>



			<div class="row-fluid">
				<div class="navbar navbar-inverse">
					<div class="navbar-inner">
						<div class="container-fluid">
							<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span
								class="icon-bar"></span> <span class="icon-bar"></span>
							</a>
							<div class="nav-collapse collapse">



								<ul class="nav">
									<li><a href="/remcon-web/servlet/Home">Inicio</a></li>

									<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Sistema o configuración global de la aplicacion</a>
										<ul class="dropdown-menu">
											<li><a href="/remcon-web/servlet/system/user/UserManager?MTM3MDQ3MDQzMjI4Mw==">Usuarios</a></li>
											<li><a href="/remcon-web/servlet/system/role/RolManager?MTM3MDQ3MDQzMjI4NA==">Definición de Roles</a></li>
											<li><a href="/remcon-web/servlet/system/roleDef/RoleDef?MTM3MDQ3MDQzMjI4NA==">Permisos de roles</a></li>
											<li><a href="/remcon-web/servlet/system/changepassword/SearchPassword?MTM3MDQ3MDQzMjI4NA==">Cambio de clave</a></li>
											<li><a href="/remcon-web/servlet/system/changepassword/SearchPassword?MTM3MDQ3MDQzMjI4NA==">Configuración del sistema experto</a></li>
										</ul></li>
										
<!-- 										
									<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Reportes</a>
										<ul class="dropdown-menu">
											<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Favoritos</a>
												<ul class="dropdown-menu">
													<li><a href="#">Favoritos 01</a></li>
													<li>Favoritos 02</li>
													<li>Favoritos 03</li>
													<li>Favoritos 04 &gt;
														<ul class="dropdown-menu">
															<li>Informe 01</li>
															<li>Informe 02</li>
														</ul>
													</li>
												</ul>
											</li>
											<li>Informe Personalizado</li>
											<li>Gráfico</li>
											<li>Períodos</li>
											<li>Estadísticas</li>
											<li><a href="/remcon-web/servlet/news/MainNews?MTM3MDUzNTYyNzMzNw==">Noticias financieras</a></li>
										</ul>
									</li>
	 -->									
										
								</ul>
								
								
								
								
							</div>
						</div>
					</div>
				</div>
			</div>


		</header>
		<div>
			<p>Note: Reduce browser window size to see dropdown navigation menu</p>
		</div>
</body>
</html>


<!-- 
< %@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"% >

< %@ include file="/WEB-INF/jsp/common/header2.jsp"% >
< %@ include file="/WEB-INF/jsp/common/menu2.jsp"% >

<div class="row-fluid">
	<div class="span12">
		<h1>Título de la página</h1>
	</div>
</div>

<div class="row-fluid">
	<div class="text-center span2 btn">Hola</div>
	<div class="span1 btn btn-info"></div>
	<div class="text-center span9 btn-success btn">Mundo</div>
</div>



< %@ include file="/WEB-INF/jsp/common/footer2.jsp"% >
-->