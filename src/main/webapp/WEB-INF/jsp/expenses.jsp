<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<section>
    <h3 id="expenses-header">${period} Expenses:</h3>
    
    <c:choose>
    		<c:when test="${period == 'Daily'}">
    			<table>
        				<tr id="expenses-table">
        				    <th>Description</th>
        				    <th>Category</th>
        				    <th>Cost</th>
        				</tr>
       			<c:forEach var="expense" items="${expenses}">
        				<tr>
        				    <td>${expense.name}</td>
        				    <td>${expense.category}</td>
        				    <td>$${expense.cost}</td>
        				</tr>
        			</c:forEach>
   			</table>
    		</c:when>
    		<c:when test="${period == 'Weekly' || period == 'Monthly'}">
    			<table>
        				<tr>
        				    <th>Date</th>
        				    <th>Description</th>
        				    <th>Cost</th>
        				</tr>
       			<c:forEach var="expense" items="${expenses}">
        				<tr>
        				    <td>${expense.day}</td>
        				    <td>${expense.name}</td>
        				    <td>$${expense.cost}</td>
        				</tr>
        			</c:forEach>
   			</table>
    		</c:when>
    </c:choose>
    <br>
    
    <c:url var="deleteExpense" value="/deleteExpense"/>
    	<p><a href="${deleteExpense}"><button class="submit">Remove an Expense</button></a>
    
    <p><a href="/RideShare/${period2link}">${period2}-at-a-glance</a></p>
    <p><a href="/RideShare/${period3link}">${period3}-at-a-glance</a></p>
    <h4>Generate a custom report:</h4>
    <form action="home.html">
        <p><label for="start">From date: </label>
        <input id="start" type="text"/></p>
        <p><label for="end">To date: </label>
        <input id="end" type="text"/></p>
        <input class="submit" style="float:right" name="submit" type="submit" value="Email Report"/>
    </form>
</section>

<div style="height:50px"></div>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>