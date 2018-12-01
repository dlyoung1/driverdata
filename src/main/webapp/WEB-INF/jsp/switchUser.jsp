<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<section id="switch-user-section">

<form action="switchUserInput" method="POST">

	<p id="switch-user-header"><label for="userName">Which User would you like to make current? </label></p>
		<c:forEach var="users" items="${users}">
			<input type="radio" name="userName" value="${users.firstName}" required>
				${users.firstName} &emsp; ${users.lastName} &emsp; ${users.email}<br><br>
		</c:forEach>
	
	<div id="switch-user-submit"><input class="submit" type="submit" value="Submit"/></div>

</form>

</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>