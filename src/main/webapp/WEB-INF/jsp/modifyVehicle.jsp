<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>
<div style="height:40px"></div>
<section id="modify-vehicle-section">

<form action="modifyVehicleInput" method="POST">

	<c:if test="${not empty errorMessages}">
		<c:forEach var="message" items="${errorMessages}">
			<div class="alert" role="alert">
				<p class="error">${message}</p>
			</div>
		</c:forEach>
	</c:if>

	<h3 id="modify-vehicle-header">Current Vehicle Details: </h3>
	<table>
		<tr>
			<th>Make</th>
			<th>Model</th>
			<th>Year</th>
			<th>Color</th>
		</tr>
		<tr>
			<td>${vehicleDetails.make}</td>
			<td>${vehicleDetails.model}</td>
			<td>${vehicleDetails.year}</td>
			<td>${vehicleDetails.color}</td>
		</tr>
	</table>
	<img id="user-vehicle" src="${vehicleDetails.image}"/>
		
	<p><label for="make">Enter the new Make: </label>
		<input type="text" name="make" required/></p>
	
	<p><label for="model">Enter the new Model: </label>
		<input type="text" name="model" required/></p>
		
	<p><label for="year">Enter the new Year: </label>
		<input type="text" name="year" required/></p>
		
	<p><label for="color">Enter the new Color: </label>
		<input type="text" name="color" required/></p>
	
	<p><label for="image">*Enter the Image url: </label>
		<input type="text" name="image"/></p>
	
	<input class="submit" type="submit" value="Update Vehicle"/>

</form>

	<p style="font-size:8px">*optional</p>

</section>
<div style="height:20px"></div>
<c:import url="/WEB-INF/jsp/common/footer.jsp"/>