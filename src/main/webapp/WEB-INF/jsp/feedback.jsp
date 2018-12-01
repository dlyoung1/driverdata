<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<div style="height:40px"></div>

<section id="feedback-section">

<h3>Pass your comments below</h3>

<form action="feedbackInput" method="POST">

		<c:if test="${not empty errorMessages}">
			<c:forEach var="message" items="${errorMessages}">
				<div class="alert" role="alert">
					<p class="error">${message}</p>
				</div>
			</c:forEach>
		</c:if>

	<p><label for="name">Name/pseudonym/identifying mark:</label>
		<input type="text" name="name" required/></p>
		
	<p><label for="source">How did you learn about this app? </label>
		<select name="source" required>
			<option value="Linkedin">Linkedin</option>
			<option value="darrenyoung.design">darrenyoung.design</option>
			<option value="Word of mouth">Word of mouth</option>
			<option value="other">Other</option>
		</select></p>
	
	<p><label for="rating">How many stars do you feel this fine program deserves? </label>
		<input type="text" name="rating" placeholder="1(it's okay)-5(nice work!)" required/></p>
	
	<p><label for="notes">Provide some public feedback: </label>
		<textarea name="notes" rows="10" cols="50">Elaborate as much as necessary here</textarea></p>
	
	<input class="submit" type="submit" value="Post it"/>

</form>

</section>

<div style="height:40px"></div>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>