<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>
<div style="height:40px"></div>

<section id="add-mechanic-section">
<h3 class="mechanic-header">Add a new mechanic</h3>
<form action="addMechanic" method="POST">

	<p><label for="name">Enter shop name: </label>
	<input type="text" name="name"/></p>
	
	<p><label for="address">Enter address: </label>
	<input type="text" name="address"/></p>
	
	<p><label for="phone">Enter phone number: </label>
	<input type="text" name="phone"/></p>
	
	<input class="submit" type="submit" value="Add Mechanic"/>

</form>

<h3 class="mechanic-header">Stored Shop Locations</h3>
<form action="deleteMechanic" method="POST">
<table>
	<tr>
		<th></th>
		<th>Shop Name</th>
		<th>Address</th>
		<th>Phone Number</th>
	</tr>
	<c:forEach var="mechanics" items="${mechanics}">
	<tr>
		<td><input type="radio" name="mechanicId" value="${mechanics.mechanicId}" required></td>
		<td>${mechanics.name}</td>
		<td>${mechanics.address}</td>
		<td>${mechanics.phone}</td>
	</tr>
	</c:forEach>
</table>
<input type="submit" value="Delete Mechanic"/>
</form>

<c:url var="deleteMechanic" value="/deleteMechanic"/>
<a href="${deleteMechanic}"><button class="submit">Delete Mechanic</button></a>


</section>
<div style="height:100px"></div>
<c:import url="/WEB-INF/jsp/common/footer.jsp"/>