<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<div style="height:40px"></div>

<section id="feedbackresult-section">

	<c:url var="feedback" value="/feedback"/>
	<p id="feedback-button"><a href="${feedback}"><button class="submit">Post Your Feedback!</button></a></p>

	<!-- piechart speedometer -->
	<h2>Current Place: ${totalRating} out of ${possibleRating}</h2>
	
	<h3 id="expert-drivers">Expert Driver Reviews: </h3>
		<c:forEach var="experts" items="${experts}">
			<p class="name-date">${experts.name} &emsp; ${experts.day} &emsp; ${experts.rating} stars</p>
			<p class="notes">${experts.notes}</p>
		</c:forEach><hr>
	
	<h3 id="suspended-license">Drivers without a License: </h3>
		<c:forEach var="trolls" items="${trolls}">
			<p class="name-date">${trolls.name} &emsp; ${trolls.day}</p>
			<p class="notes">${trolls.notes}</p>
		</c:forEach>

</section>

<div style="height:40px"></div>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>