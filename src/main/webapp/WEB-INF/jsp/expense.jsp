<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<h3 id="expense-header">Enter Expense Item:</h3>
<section>
        <form action="addExpense" method="POST">
        	
	        	<c:if test="${not empty errorMessages}">
				<c:forEach var="message" items="${errorMessages}">
					<div class="alert" role="alert">
						<p class="error">${message}</p>
					</div>
				</c:forEach>
			</c:if>
        
            <p><label for="name">Item Description: </label>
            <input id="name" type="text" name="name" required/></p>
            <p><label for="category">Expense Category: </label>
            <select id="category" name="category" required>
                <option>maintenance</option>
                <option>tolls</option>
                <option>misc</option>
                <option>insurance</option>
                <option>mobile</option>
                <option>+Add new</option>
            </select></p>
            <p><label for="cost">Cost: </label>
            <input id="cost" name="cost" type="text" required/></p>
            <textarea id="comments" name="comments" rows=4 cols=48>additional notes here</textarea><br>
            
            <p><label for="scan"><em>Coming Soon:</em> </label>
            <button id="scan">scan receipt</button></p>
            
            <input class="submit" style="float:right" name="submit" type="Submit" value="Save"/>
        </form>
</section>

<div style="height:50px"></div>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>