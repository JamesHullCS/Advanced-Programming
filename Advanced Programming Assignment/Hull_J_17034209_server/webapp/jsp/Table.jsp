<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="models.Vehicle"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/TablePage.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Vehicles</title>
	<meta <%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c" %>>
</head>
<body>
	<h1>All Vehicles</h1>
			<c:if test="${!login}">
				<a href="./login">Log in</a>
			</c:if>
		
			<c:if test="${login}" >
				<a href="./loggedout">Log out</a><br>
				<a href="./addVehicle">+ New Vehicle</a>
			</c:if>	
	<table>
		<tr>
			<th>Vehicle_id</th>
			<th>Make</th>
			<th>Model</th>
			<th>Year</th>
			<th>price</th>
			<th>License_plate</th>
			<th>Colour</th>
			<th>Number of doors</th>
			<th>Transmission</th>
			<th>Mileage</th>
			<th>Fuel_type</th>
			<th>Engine_size</th>
			<th>Body_style</th>
			<th>Condition</th>
			<th>Notes</th>
			<th>Sales (true or false)</th>
		</tr>

		<c:forEach items="${allVEH}" var="v">
			<tr>
				<td>${v.getVehicle_id()}</td>
				<td>${v.getMake()}</td>
				<td>${v.getModel()}</td>
				<td>${v.getYear()}</td>
				<td>${v.getPrice()}</td>
				<td>${v.getLicense_number()}</td>
				<td>${v.getColour()}</td>
				<td>${v.getNumber_doors()}</td>
				<td>${v.getTransmission()}</td>
				<td>${v.getMileage()}</td>
				<td>${v.getFuel_type()}</td>
				<td>${v.getEngine_size()}</td>
				<td>${v.getBody_style()}</td>
				<td>${v.getCondition()}</td>
				<td>${v.getNotes() }</td>
				<td>${v.getSales() }</td>
				<c:if test="${login}" >
				<td>
						<form>
							<a href="./EditVehicles?id=${v.getVehicle_id()}"> Update  <br>
							</a>
							<a href="./deletevehicle?id=${v.getVehicle_id()}"> Delete 
							</a>
						</form>
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>

</body>
</html>