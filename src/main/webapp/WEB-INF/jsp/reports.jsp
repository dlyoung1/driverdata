<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<section>

	<h3 id="report-header">Custom Reports</h3>

	<form action="reportInput" method="POST">
	
		<p><label for="maintenance">Maintenance: </label>
			<select name="maintenance">
				<option value="null"></option>
				<option value="serviceSchedule">Service Schedule</option>
				<option value="maintenanceExpenses">Maintenance Expenses</option>
			</select></p>
			
		<p><label for="expenses">Expenses: </label>
			<select name="expenses">
				<option value="null"></option>
				<option value="Daily">Daily</option>
				<option value="Weekly">Weekly</option>
				<option value="Monthly">Monthly</option>
			</select></p>
			
		<p><label for="mileage">Mileage: </label>
			<select name="mileage">
				<option value="null"></option>
				<option value="weekly">Weekly</option>
				<option value="monthly">Monthly</option>
			</select></p>
			
		<input class="submit" type="submit" value="Generate Report"/>
			
	</form>
        
</section>

	<div style="height:200px"></div>


<c:import url="/WEB-INF/jsp/common/footer.jsp"/>