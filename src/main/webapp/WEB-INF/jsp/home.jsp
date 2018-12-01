<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<section>


        <h3 id="greeting">${greeting} <!--time of day function--> ${currentUser.firstName} <!--user name function--></h3>
        
        <h5 id="date"><em>${date}</em></h5>
        
<!--calculates miles driven on today's date-->
		<div id="mileage"><c:url var="mileage" value="/mileage"/>
        <p>Total Mileage Today: ${miles} <a href="${mileage}"><button>${button}</button></a></p></div>
        
<!--adds total expenses from today's date-->        
        <div id="expenses"><c:url var="expense" value="/expense"/>
        <p>Daily Expenses: $${totalExpenses} <a href="${expense}"><button>Add Expense</button></a></p></div>
        
<!--function user vehicle photo with special effects to match the app design-->        
        <img id="user-vehicle" src="${image}"/>

       <table id="home-table">
       		<tr>
       		<c:url var="maintenanceService" value="/maintenanceService"/>
	        <th>Upcoming Maintenance: <!--pulls maintenance items which are due within the user specified time--></th><th><a href="${maintenanceService}"><button>Add New Service</button></a></th>
	       	</tr>
		        <c:forEach var="maintenance" items="${upcomingMaintenance}" varStatus="status">
		    			<tr>
		    				<td>${maintenance}</td>
		    				<c:choose>
		    					<c:when test="${upcomingMiles[status.index] < 0}">
		    						<td style="color:red">overdue ${upcomingMiles[status.index]}</td>
		    					</c:when>
		    					<c:otherwise>
		    						<td>${upcomingMiles[status.index]}</td>
		    					</c:otherwise>
		    				</c:choose>
		    			</tr>    
		        </c:forEach>
        </table>
        
        <div id="summary-links">
        		<c:url var="reports" value="/reports"/>
            <span><a href="${reports}">REPORTS</a></span> 
            <c:url var="feedbackResult" value="/feedbackResult"/>
            <span><a href="${feedbackResult}">FEEDBACK</a></span>
        </div>
</section>




<c:import url="/WEB-INF/jsp/common/footer.jsp"/>