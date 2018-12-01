<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>


<section id="settings-section">
	<c:url var="switchUser" value="/switchUser"/>
		<p><a href="${switchUser}"><button>Switch User</button></a></p>

	<c:url var="updateUser" value="/updateUser"/>
    		<p><a href="${updateUser}"><button>Update User Info</button></a></p>
	
	<c:url var="deleteUser" value="/deleteUserMileage"/>        
        <p><a href="${deleteUser}"><button>Delete User</button></a></p>
    
    <c:url var="addUser" value="/addNewUser"/>
    		<p><a href="${addUser}"><button>Add New User</button></a></p>
	
	<c:url var="modifyVehicle" value="/modifyVehicle"/>
        <p><a href="${modifyVehicle}"><button>Modify Current Vehicle</button></a></p>

	<c:url var="switchVehicle" value="/switchVehicle"/>
        <p><a href="${switchVehicle}"><button>Switch Vehicle</button></a></p>

	<c:url var="addNewVehicle" value="/addNewVehicle"/>
        <p><a href="${addNewVehicle}"><button>Add New Vehicle</button></a></p>
    
    <c:url var="deleteVehicle" value="/deleteVehicle"/>
    		<p><a href="${deleteVehicle}"><button>Delete Vehicle</button></a></p>
    	
    	
        <p><button>Display Options</button></p>
</section>

<div style="height:50px"></div>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>