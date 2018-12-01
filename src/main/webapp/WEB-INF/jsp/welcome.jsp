<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:url value="/css/style.css" var="cssURL" />
<link rel="stylesheet" type="text/css" href="${cssURL}" >
<title>DriverData Welcome</title>
</head>
<body id="welcome">

<h3 id="h3">Welcome to DriverData v1.0</h3>

<form id="welcome-form" action="setSession" method="GET">
	<label for="userName"><em>Please select a user: </em></label>
		<select id="userName" name="userName">
			<c:forEach var="name" items="${profile}">
			<option>${name.firstName}</option>
			</c:forEach>
		</select>
	<input class="submit" type="submit" value="Submit"/>
</form>

<h4>OR . . .</h4>

<form id="add-new" action="addNewUser" method="GET">
	<input class="submit" type="submit" value="Add new user"/>
</form>

<img id="hov" src="img/HOV.png">

</body>
</html>