<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<section id="delete-expense-section">

	<form method="POST" action="deleteExpenseInput">
		<div id="delete-expense-header"><label for="deleteExpense">Select an expense to delete: </label></div>
		<select name="expenseId">
			<c:forEach var="expenses" items="${expenses}">
				<option value="${expenses.expenseId}">${expenses.name}&emsp; $${expenses.cost}&emsp; ${expenses.day}</option>
			</c:forEach>
		</select>
		
		<input class="submit" type="submit" value="Remove Expense"/>
	</form>

</section>

<div style="height:100px"></div>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>