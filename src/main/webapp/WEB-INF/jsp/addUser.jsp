<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>
<div style="height:40px"></div>
<section id="add-user-section">

<form action="addUser" method="POST">

	<c:if test="${not empty errorMessages}">
		<c:forEach var="message" items="${errorMessages}">
			<div class="alert" role="alert">
				<p class="error">${message}</p>
			</div>
		</c:forEach>
	</c:if>

	<p><label for="firstName">Enter first name: </label>
	<input type="text" name="firstName" required/></p>
	
	<p><label for="lastName">Enter last name: </label>
	<input type="text" name="lastName" required/></p>
	
	<p><label for="email">Enter email address: </label>
	<input type="text" name="email" required/></p>
	
	<input class="submit" type="submit" value="Create new user"/>

</form>

</section>
<div style="height:100px"></div>
<c:import url="/WEB-INF/jsp/common/footer.jsp"/>