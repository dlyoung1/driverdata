<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<section id="delete-vehicle-section">

	<form method="POST" action="deleteMileage">
		<div id="delete-vehicle-header"><label for="vehicleId">Select a Vehicle to delete: </label></div>
		<select name="vehicleId">
			<c:forEach var="vehicles" items="${vehicles}">
				<option value="${vehicles.vehicleId}">${vehicles.make}&emsp; ${vehicles.model}&emsp; ${vehicles.year}</option>
			</c:forEach>
		</select>
		
		<input class="submit" type="submit" value="Remove Vehicle"/>
	</form>

</section>

<div style="height:100px"></div>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>