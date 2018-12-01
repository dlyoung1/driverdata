<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<section id="delete-user-section">

	<form method="POST" action="deleteUserInput">
		<div id="delete-user-header"><label for="deleteUser">Which user would you like to remove? </label></div>
		<select name="deleteUser">
			<c:forEach var="users" items="${users}">
				<option>${users.firstName}</option>
			</c:forEach>
		</select>
		
		<input type="submit" value="Delete User Profile"/>
	</form>

</section>

<div style="height:100px"></div>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>