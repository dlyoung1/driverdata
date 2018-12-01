<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<section>

	<h3 id="maintenanceExpenses-header">Monthly Maintenance Services Performed: </h3>
	<h4>Total cost: $${totalCost}</h4>
	<table>
		<tr>
			<th>Service</th>
			<th>Date</th>
			<th>Cost</th>
		</tr>
		<c:forEach var="expenses" items="${expenses}">
			<tr>
				<td>${expenses.name}</td>
				<td>${expenses.day}</td>
				<td>${expenses.cost}</td>
			</tr>
		</c:forEach>
	</table>
        
</section>

	<div style="height:200px"></div>


<c:import url="/WEB-INF/jsp/common/footer.jsp"/>