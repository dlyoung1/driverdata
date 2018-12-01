<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<h3 id="service-header">Service details</h3>
	<c:url var="addMaintenance" value="/maintenance"/>
	   <p><a href="${addMaintenance}"><button>Add a New Service Type</button></a></p>
	<c:url var="addMechanic" value="/mechanic"/>
	   <p><a href="${addMechanic}"><button>Add a new Location</button></a></p>
<section>
    <form action="maintenanceInput" method="POST">
    
    		<c:if test="${not empty errorMessages}">
			<c:forEach var="message" items="${errorMessages}">
				<div class="alert" role="alert">
					<p class="error">${message}</p>
				</div>
			</c:forEach>
		</c:if>
    
        <p><label for="category">Type: </label>
	        <select id="category" name="category" required>
	        		<c:forEach var="maintenanceCategory" items="${maintenanceSchedule}">
	            <option>${maintenanceCategory.name}</option>
	            </c:forEach>
	        </select></p>
        
        
	        <p><label for="mileage">Mileage: </label>
	        <input id="mileage" name="maintMileage" type="text" required/></p>
	        
	        <p><label for="cost">Cost: </label>
	        <input id="cost" name="cost" type="text" required/></p>
	        
	        <p><label for="location">Location: </label>
	        <select id="location" name="location" required>
	        		<c:forEach var="mechanic" items="${mechanic}">
	            		<option>${mechanic.name}</option>
	            </c:forEach>
	        </select></p>
	        
	        <textarea id="description" name="description" rows=4 cols=48>enter a description of the service performed here</textarea>
        
        <br>
        <p><label for="scan"><em>Coming Soon . . .</em> </label>
        <button id="scan" name="scan">scan receipt</button></p>
        
        <input class="submit" style="float:right" name="submit" type="submit" value="Submit"/>
    </form>
</section>

<div style="height:50px"></div>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>