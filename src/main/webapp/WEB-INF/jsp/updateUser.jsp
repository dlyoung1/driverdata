<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<section id="update-user-section">

<form action="updateUserInput" method="POST">

	<c:if test="${not empty errorMessages}">
		<c:forEach var="message" items="${errorMessages}">
			<div class="alert" role="alert">
				<p class="error">${message}</p>
			</div>
		</c:forEach>
	</c:if>

	<div id="update-user-header"><label for="firstName">Select User profile to update: </label></div><br>
		<c:forEach var="users" items="${users}">
			<input type="radio" name="firstName" value="${users.firstName}" required>
				${users.firstName} &emsp; ${users.lastName} &emsp; ${users.email} <br><br>
		</c:forEach>
		
	<p><label for="newFirstName">Enter new First Name: </label>
		<input type="text" name="newFirstName" required/></p>
	
	<p><label for="lastName">Enter new Last Name: </label>
		<input type="text" name="lastName" required/></p>
		
	<p><label for="email">Enter new Email: </label>
		<input type="text" name="email" required/></p>
	
	<div id="update-user-submit"><input class="submit" type="submit" value="Update Profile"/></div>

</form>

</section>

<div style="height:20px"></div>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>