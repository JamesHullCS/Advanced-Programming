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
 * The edit class takes in the id but then based on the id displays all the data in the textfields from the id so you can edit it.
 * @author Student ID: 17034209 Name: James Alan-Abbott Hull.
 */
public class ServletEdit extends HttpServlet {
	
	private static final long serialVersionUID = 1L; //prevents an InvalidClassException
	
	VehicleDAO Vehicle = new VehicleDAO();
	int id;
	Vehicle WantedVehicle = null;
	
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getParameter("id") != null) {
			id = Integer.parseInt(req.getParameter("id"));
			
		}
		System.out.println(id);
		try {
			WantedVehicle = Vehicle.getVehicle(id);
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher view = req.getRequestDispatcher("jsp/ServletEdit.jsp");
		req.setAttribute("WantedVehicle", WantedVehicle);
		view.forward(req, resp);

	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			int vehicle_id = Integer.parseInt(req.getParameter("vehicle_id"));
			String make = (String) req.getParameter("make");
			String model = (String) req.getParameter("model");
			int year = Integer.parseInt(req.getParameter("year"));
			int price = Integer.parseInt(req.getParameter("price"));
			String license_number = (String) req.getParameter("license_number");
			String colour = (String) req.getParameter("colour");
			int number_doors = Integer.parseInt(req.getParameter("number_doors"));
			String transmission = (String) req.getParameter("transmission");
			int mileage = Integer.parseInt(req.getParameter("mileage"));
			String fuel_type = (String) req.getParameter("fuel_type");
			int engine_size = Integer.parseInt(req.getParameter("engine_size"));
			String body_style = (String) req.getParameter("body_style");
			String condition = (String) req.getParameter("condition");
			String notes = (String) req.getParameter("notes");
			Boolean sales = Boolean.parseBoolean(req.getParameter("sales"));
			
			Vehicle c = new Vehicle(vehicle_id,model,make,year,price,license_number,colour,number_doors,transmission,mileage,fuel_type,engine_size,body_style,condition,notes,sales);
			try {
				boolean done = Vehicle.updateVehicle(c,id);
				System.out.println(done);
				if (done) {
					resp.sendRedirect("./home");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
}
