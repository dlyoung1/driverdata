<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<section id="switch-vehicle-section">

<form action="switchVehicleInput" method="POST">

	<p id="switch-vehicle-header"><label for="vehicleId">Which vehicle would you like to drive? </label></p>
		<c:forEach var="vehicles" items="${vehicles}">
			<input type="radio" name="vehicleId" value="${vehicles.vehicleId}" required>
				${vehicles.make} &emsp; ${vehicles.model} &emsp; ${vehicles.year} &emsp; ${vehicles.color} <br><br>
		</c:forEach>
	
	<div id="switch-vehicle-submit"><input class= "submit" type="submit" value="Submit"/></div>

</form>

</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>