package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import models.Vehicle;
import models.VehicleDAO;

public class ServletApi extends HttpServlet{
// CREATED BY JAMES HULL, 17034209
	/**
	 * This servlets purpose is to be able to doget/dopost/doput/dodelete using CRUD functionality.
	 */
	private static final long serialVersionUID = 1L;
	
	VehicleDAO Vehicle = new VehicleDAO(); //Allows me to use the methods in my VehicleDAO.
	ArrayList<Vehicle> allVEH = null; //Creates an instance of the vehicle array.
	Gson gson = new Gson();
	PrintWriter writer;
	
	@Override //Overrides doGet method's
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
		try {
			allVEH = Vehicle.getAllVehicles(); //Get's all vehicles.
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		resp.setContentType("application/json"); //Tells the program it's JSON.
		writer = resp.getWriter();
		String conJSON = gson.toJson(allVEH); //Converts to JSON Data format.
		writer.write(conJSON); //Writes to the page
		writer.close();
		
	}
	
	@Override //Overrides doPost method's
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		writer = resp.getWriter(); 
		resp.setContentType("application/json"); //Sets the contentType to be text/html
		boolean inserted = false; //creates a boolean and sets it to false. We do this because it allows us to say well if something is true do this.
		Vehicle v =gson.fromJson(req.getParameter("json"), Vehicle.class);

		//Prints the the vehicle object to console
		System.out.println(v.getVehicle_id() + " " + v.getMake() + " " + v.getModel() 
		+ " " + v.getYear() + " " + v.getPrice() + " " + v.getLicense_number() + " " + v.getColour() 
		+ " " + v.getNumber_doors() + " " + v.getTransmission() + " " + v.getMileage() + " " + v.getFuel_type()
		+ " " + v.getEngine_size() + " " + v.getBody_style() + " " + v.getCondition() + " " + v.getNotes() + " " + v.getSales());
		
		try {
		inserted = Vehicle.insertVehicle(v); //inserts a vehicle.
		}
		catch (SQLException e) {
			e.printStackTrace(); 
		}
		
		if(inserted) {
			writer.write("New vehicle inserted"); //will write this to confirm that the Vehicle has been inserted.
		}
		else {
			writer.write("An error has occurred"); //will write this to confirm an error has occurred.
		}
		String conJSON = gson.toJson(v); //Converts to JSON Data format.
		writer.write(conJSON); //Writes to the page
		writer.close();
		
	}
	
	@Override //Overrides doPut method's
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		writer = resp.getWriter(); 
		resp.setContentType("application/json"); //Sets the contentType to be text/html
		boolean updated = false; //creates a boolean and sets it to false. We do this because it allows us to say well if something is true do this.
		Vehicle v =gson.fromJson(req.getParameter("json"), Vehicle.class);
		
		//Prints the the vehicle object to console
		System.out.println(v.getVehicle_id() + " " + v.getMake() + " " + v.getModel() 
		+ " " + v.getYear() + " " + v.getPrice() + " " + v.getLicense_number() + " " + v.getColour() 
		+ " " + v.getNumber_doors() + " " + v.getTransmission() + " " + v.getMileage() + " " + v.getFuel_type()
		+ " " + v.getEngine_size() + " " + v.getBody_style() + " " + v.getCondition() + " " + v.getNotes() + " " + v.getSales());
		
		try {
			updated = Vehicle.updateVehicle(v, v.getVehicle_id()); //updates a vehicle.
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(updated) {
			writer.write("Vehicle has been updated"); //will write this to confirm that the Vehicle has been updated.
		}
		else {
			writer.write("An error has occurred"); //will write this to confirm an error has occurred.
		}
		String conJSON = gson.toJson(v); //Converts to JSON Data format.
		writer.write(conJSON); //Writes to the page
		writer.close();
	}
	
	@Override //Overrides doDelete method's
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		writer = resp.getWriter(); 
		resp.setContentType("text/html);charset=UTF-8"); //Sets the contentType to be text/html
		
		int id = Integer.parseInt(req.getParameter("id")); //Gets the vehicle id
		System.out.println(id); //Prints the vehicle ID
		boolean deleted = false; //creates a boolean and sets it to false. We do this because it allows us to say well if something is true do this.
		
		try {
			deleted = Vehicle.deleteVehicle(id); //Deletes a vehicle specific to it's id.
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		if(deleted) {
			writer.write("Vehicle deleted"); //Will write this to confirm that the Vehicle has been deleted.
		}
		else {
			writer.write("An error has occurred"); //will write this to confirm an error has occurred.
		}
		writer.close();
	}
}

