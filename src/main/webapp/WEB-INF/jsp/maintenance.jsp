<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>


<section>

    <h3 id="maintenance-header">Maintenance schedule:</h3>
        <table>
            <tr id="maintenance-table">
                <th>Service</th>
                <th>Frequency</th>
                <th>Last Service<!--user defined miles or date--></th>
                <th>Next Service</th>
            </tr>
            <c:forEach var="serviceItems" items="${serviceItems}" varStatus="status">
	            <tr>
	            		<c:forEach var="list" items="${serviceItems.value}">
	                		<td>${list}</td>
	            		</c:forEach>
	            </tr>
	         </c:forEach>
        </table>

	<h4 id="maintenance-item">Add a new Service Type</h4>
	<form method="POST" action="addMaintenance">
	
		<c:if test="${not empty errorMessages}">
			<c:forEach var="message" items="${errorMessages}">
				<div class="alert" role="alert">
					<p class="error">${message}</p>
				</div>
			</c:forEach>
		</c:if>
	
		<p><label for="name">Service Type: </label>
			<input type="text" name="name"/></p>
		<p><label for="interval">Frequency: </label>
			<input type="text" placeholder="miles" name="interval"/></p>
		
		<input class="submit" type="submit" value="Add new Service"/>
    </form>
</section>

<div style="height:50px"></div>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>