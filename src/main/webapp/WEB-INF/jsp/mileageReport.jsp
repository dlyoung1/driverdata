<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<section>

	<h3>${period} Mileage Report</h3>
	
	<c:forEach var="mileage" items="${mileageMap}" varStatus="status">
		<p>${mileage.getKey()} - ${endDate[status.index]} &emsp; ${mileage.getValue()}</p><hr>
	</c:forEach>
	
	<h4>Total Mileage: ${totalMiles}</h4>
	
	<c:url var="redirect" value="${redirect}"/>
		<a href="${redirect}"><button class="submit">get ${periodRev} Mileage</button></a>
	
	<div style="height:200px"></div>
</section>




<c:import url="/WEB-INF/jsp/common/footer.jsp"/>