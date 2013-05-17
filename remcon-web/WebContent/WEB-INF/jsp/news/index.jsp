<!DOCTYPE html>
<html>
<head>
<title>Responsive Menu - ProgrammingFree</title>
<!-- Mobile viewport optimized -->
<meta name="viewport" content="width=device-width">
<!-- CSS -->
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />
<!--Scripts-->
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js" type="text/javascript"></script>

</head>
<body>
	<div class="container-fluid">
		<header>
			<h2>Responsive Navigation Menu using Twitter Bootstrap</h2>


			<div class="row-fluid">
				<div class="navbar navbar-inverse">
					<div class="navbar-inner">
						<div class="container-fluid">
							<a class="btn btn-navbar" data-toggle="collapse"
								data-target=".nav-collapse"> <span class="icon-bar"></span>
								<span class="icon-bar"></span> <span class="icon-bar"></span>
							</a>
							<div class="nav-collapse collapse">
								<ul class="nav">
									<li class="active"><a href="#"><i
											class="icon-home icon-white"></i> Home</a></li>
									<li><a href="#">Features</a></li>
									<li><a href="">Pricing</a></li>
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown">Products<b class="caret"></b></a>
										<ul class="dropdown-menu">
											<li><a href="#">Latest Products</a></li>
											<li><a href="#">Popular Products</a></li>
										</ul></li>
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown">Membership<b class="caret"></b></a>
										<ul class="dropdown-menu">
											<li><a href="#">Personal Membership</a></li>
											<li><a href="#">Premium Membership</a></li>
										</ul></li>
									<li><a href="#">Offers</a></li>
									<li><a href="#">Gallery</a></li>
									<li><a href="#">About Us</a></li>
									<li><a href="#">Contact</a></li>
									<li class="dropdown"><a class="dropdown-toggle"
										data-toggle="dropdown" href="#">Dropdown <b class="caret"></b></a>
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
				<p>Note: Reduce browser window size to see dropdown navigation
					menu</p>
			</div>
		</header>
		<div>
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