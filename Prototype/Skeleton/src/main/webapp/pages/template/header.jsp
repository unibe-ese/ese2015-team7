<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>Awesome Website</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<!--[if lte IE 8]><script src="/Skeleton/css/ie/html5shiv.js"></script><![endif]-->
		<script src="/Skeleton/js/jquery.min.js"></script>
		<script src="/Skeleton/js/jquery.dropotron.min.js"></script>
		<script src="/Skeleton/js/skel.min.js"></script>
		<script src="/Skeleton/js/skel-layers.min.js"></script>
		<script src="/Skeleton/js/init.js"></script>
		<noscript>
			<link rel="stylesheet" href="/Skeleton/css/skel.css" />
			<link rel="stylesheet" href="/Skeleton/css/style.css" />
			<link rel="stylesheet" href="/Skeleton/css/style-desktop.css" />
			<link rel="stylesheet" href="/Skeleton/css/main.css" />
		</noscript>
		<!--[if lte IE 8]><link rel="stylesheet" href="/Skeleton/css/ie/v8.css" /><![endif]-->
	</head>
	<body class="no-sidebar">

		<!-- Header -->

		
			<div id="header-wrapper">
				<header id="header" class="container">
				<h3><% out.println(request.getSession().getAttribute("username") ); %></h3>

					<!-- Nav -->
						<nav id="nav">
							<ul>
								<li><a href="/Skeleton/help">Help</a></li>
								<li><a href="/Skeleton/search">Search</a></li>
								<li>
									<a href="/Skeleton/requests">Requests</a>
									<ul>
										<li><a href="/Skeleton/requests">Incoming Requests</a></li>
										<li><a href="/Skeleton/myRequests">Outgoing Requests</a></li>
									</ul>
								</li>
								
								<li>
									<a href="/Skeleton/profile">My Profile</a>
								</li>
								<li><a href="<c:url value="j_spring_security_logout" />">Logout</a></li>
							</ul>
						</nav>
					
				</header>
			</div>
		
		<!-- Main -->
			<div id="main-wrapper">
				<div class="container">
					<div id="content">

						<!-- Content -->
							<article>