package servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.VehicleDAO;

/**
 * The delete class takes in the ID of the vehicle and deletes it from the db.
 * @author Student ID: 17034209 Name: James Alan-Abbott Hull.
 */

public class ServletDelete extends HttpServlet {
	private static final long serialVersionUID = 1L; //prevents an InvalidClassException
	VehicleDAO Vehicle = new VehicleDAO();
	int vehicle_id;

	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		vehicle_id =  Integer.parseInt(req.getParameter("id"));
		try {
			boolean done = Vehicle.deleteVehicle(vehicle_id);
			System.out.println(done);
			if (done) 
			{
				resp.sendRedirect("./home");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		

}
}
