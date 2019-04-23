package servlets;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Vehicle;
import models.VehicleDAO;
/** 
* This Servlet is used to request parameters from the db and use them to enter a new vehicle into the db using the online webpage
* @author Student ID: 17034209 Name: James Alan-Abbott Hull.
**/
public class addVehicles extends HttpServlet {
	private static final long serialVersionUID = 1L; //prevents an InvalidClassException
	VehicleDAO vehicle = new VehicleDAO();  //allows the use of the VehicleDAO methods.
	/**
	 * This doGet sends a request to the server asking to be moved to the addNewVehicle form.
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher view = req.getRequestDispatcher("jsp/addNewVehicle.jsp"); //makes a request to the server to send the user to the add vehicle form.
		view.forward(req, resp); //forwards the request to the server
	}
	/**
	 * This doPost gets all the data from the the textfields in the jsp and assigns them a vehicle to place into the database.
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int vehicle_id = Integer.parseInt(req.getParameter("vehicle_id")); //gets the data as an int
		String make = (String) req.getParameter("make"); //gets the data as a String
		String model = (String) req.getParameter("model"); //gets the data as a String
		int year = Integer.parseInt(req.getParameter("year")); //gets the data as an int
		int price = Integer.parseInt(req.getParameter("price")); //gets the data as an int
		String license_number = (String) req.getParameter("license_number"); //gets the data as a String
		String colour = (String) req.getParameter("colour");
		int number_doors = Integer.parseInt(req.getParameter("number_doors")); //gets the data as an int
		String transmission = (String) req.getParameter("transmission"); //gets the data as a String
		int mileage = Integer.parseInt(req.getParameter("mileage")); //gets the data as an int
		String fuel_type = (String) req.getParameter("fuel_type"); //gets the data as a String
		int engine_size =  Integer.parseInt(req.getParameter("engine_size")); //gets the data as an int
		String body_style = (String) req.getParameter("body_style"); //gets the data as a String
		String condition = (String) req.getParameter("condition"); //gets the data as a String
		String notes = (String) req.getParameter("notes"); //gets the data as a String
		Boolean sales = Boolean.parseBoolean(req.getParameter("sales")); //gets the data as a Boolean

			Vehicle Vehicles = new Vehicle(vehicle_id,make,model,year,price,license_number,colour,number_doors,transmission,mileage,fuel_type,engine_size,body_style,condition,notes,sales); //places the requested params into a car.
			try {
				boolean done = vehicle.insertVehicle(Vehicles); //inserts the vehicle into the table and db.
				System.out.println(done); //if it works it'll redirect home
				if (done) 
				{
					resp.sendRedirect("./home"); //redirects home after the insert.
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
	}
}