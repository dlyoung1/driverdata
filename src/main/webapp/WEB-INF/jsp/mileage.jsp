<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<section>

	<h4 id="mileage-message">${message}</h4>

	<c:if test="${not empty errorMessages}">
		<c:forEach var="message" items="${errorMessages}">
			<div class="alert" role="alert">
				<p class="error">${message}</p>
			</div>
		</c:forEach>
	</c:if>

	<c:choose>
		<c:when test="${message == 'Enter starting mileage to begin trip'}">
			<form class="enter-miles" action="startMileageInput" method="POST">
				<label for="startMileage">Enter Starting Mileage: </label>
				<input name="startMileage" type="text" required/>
				<input class="submit" type="submit" value="Start Trip"/>
			</form>
		</c:when>
		<c:when test="${message == 'Trip in Progress'}">	
			<h5>Starting mileage: ${miles}</h5>	
			<form class="enter-miles" action="endMileageInput" method="POST">
				<label for="endMileage">Enter Ending Mileage: </label>
				<input name="endMileage" type="text" required/>
				<input class="submit" type="submit" value="End Trip"/>
			</form>
		</c:when>
		<c:otherwise>	
			<h5>Last entered starting mileage: ${miles} &emsp; The date was: ${date}</h5>	
			<form class="enter-miles" action="endMileageInput" method="POST">
				<label for="endMileage">Enter Ending Mileage: </label>
				<input name="endMileage" type="text" required/>
				<input class="submit" type="submit" value="End Trip"/>
			</form>
		</c:otherwise>
	</c:choose>
	
	<div style="height:200px"></div>
</section>




<c:import url="/WEB-INF/jsp/common/footer.jsp"/>