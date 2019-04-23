<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Vehicle</title>
	<meta <%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c" %>>
</head>
<body>

	<c:if test="${!login}">
	<a href="./login">Log in</a>
	</c:if>
		
	<c:if test="${login}">
	<form method="POST" action="./addVehicle">
	
		Vehicle ID: <input type="text" name="vehicle_id"><br>
		Make: <input type="text" name="make"><br>
		Model: <input type="text" name="model"><br>
		Year: <input type="text" name="year"><br>
		Price: <input type="text" name="price"><br>
		License_number: <input type="text" name="license_number" pattern="[a-zA-Z][a-zA-Z][0-9][0-9][a-zA-Z][a-zA-Z][a-zA-Z]" title ="wrong license" ><br>
		Colour: <input type="text" name="colour"><br>
		Number_Doors: <input type="text" name="number_doors"><br>
		Transmission: <input type="text" name="transmission"><br>
		Mileage: <input type="text" name="mileage"><br>
		Fuel_type: <input type="text" name="fuel_type"><br>
		Engine_size: <input type="text" name="engine_size"><br>
		Body_style: <input type="text" name="body_style"><br>
		Condition: <input type="text" name="condition"><br>
		Notes: <input type="text" name="notes"><br>
		Sales: <input type="text" name="sales"><br>
		
		<input type="submit">	
	</form>
	</c:if>
</body>
</html>