<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<c:url value="/css/style.css" var="cssURL" />
	<link rel="stylesheet" type="text/css" href="${cssURL}" >
	<title>DriverData - </title>
</head>

<body>
	<header id="header-links">
		
		<nav>
			<c:url var="home" value="/home"/>
			<div id="nav1"><a href="${home}"><img style="width:20px" src="img/home.png"/></a></div>
			<c:url var="settings" value="/settings"/>
			<div id="nav2"><a href="${settings}"><img style="width:20px" src="img/settings.png"></a></div>
		</nav>
	</header>


<div class="container">