<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<div style="height:40px"></div>

<section id="add-vehicle-section">

<form action="addVehicle" method="POST">

	<c:if test="${not empty errorMessages}">
		<c:forEach var="message" items="${errorMessages}">
			<div class="alert" role="alert">
				<p class="error">${message}</p>
			</div>
		</c:forEach>
	</c:if>

	<p><label for="make">Enter the Make: </label>
	<input type="text" name="make" required/></p>
	
	<p><label for="model">Enter the Model: </label>
	<input type="text" name="model" required/></p>
	
	<p><label for="year">Enter the Year: </label>
	<input type="text" name="year" required/></p>
	
	<p><label for="color">Enter the color: </label>
	<input type="text" name="color" required/></p>
	
	<p><label for="image">Enter path to image: </label>
	<input type="text" name="image"/></p>
	
	<input class="submit" type="submit" value="input new vehicle"/>

</form>

</section>

<div style="height:40px"></div>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>