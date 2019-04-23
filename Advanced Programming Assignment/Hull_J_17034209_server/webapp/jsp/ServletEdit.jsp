<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "models.Vehicle"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title> Edit Vehicle </title>
	<meta <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>>
</head>
<body>
	<c:if test="${!login}">
	<a href="./login">Log in</a>
	</c:if>
		<c:if test="${login}">
<h1> Edit Vehicle</h1>
		<form method="POST" action="./EditVehicles">
		ID: <input type="text" name = "vehicle_id" value="${WantedVehicle.getVehicle_id()}"><br>
		Make: <input type="text" name = "make" value="${WantedVehicle.getMake()}"><br>
		Model: <input type="text" name = "model" value="${WantedVehicle.getModel()}"><br>
		Year: <input type="text" name = "year" value="${WantedVehicle.getYear()}"><br>
		Price: <input type="text" name = "price" value="${WantedVehicle.getPrice()}"><br>
		License Number: <input type="text" name = "license_number" pattern="[a-zA-Z][a-zA-Z][0-9][0-9][a-zA-Z][a-zA-Z][a-zA-Z]" title ="wrong license" value="${WantedVehicle.getLicense_number()}"><br>
		Colour: <input type="text"name = "colour" value="${WantedVehicle.getColour()}"><br>
		Number Of Doors: <input type="text"name = "number_doors"  value="${WantedVehicle.getNumber_doors()}"><br>
		Transmission: <input type="text"name = "transmission" value="${WantedVehicle.getTransmission()}"><br>
		Mileage: <input type="text"name = "mileage" value="${WantedVehicle.getMileage()}"><br>
		Fuel Type: <input type="text"name = "fuel_type" value="${WantedVehicle.getFuel_type()}"><br>
		Engine Size: <input type="text"name = "engine_size" value="${WantedVehicle.getEngine_size()}"><br>
		Body Style: <input type="text"name = "body_style" value="${WantedVehicle.getBody_style()}"><br>
		Condition: <input type="text"name = "condition" value="${WantedVehicle.getCondition()}"><br>
		Notes: <input type="text"name = "notes" value="${WantedVehicle.getNotes()}"><br>
		Sales: <input type="text"name = "sales" value="${WantedVehicle.getSales()}"><br>
		<input type="submit">
		</form>
	</c:if>
</body>
</html>