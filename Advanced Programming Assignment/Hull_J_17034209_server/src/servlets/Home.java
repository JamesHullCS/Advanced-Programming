package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Vehicle;
import models.VehicleDAO;

/**
 * This is the Home servlets that uses the VehicleDAO class while uses a select query to get all the vehicles assign them to the array and places them on on the Table.jsp 
 * @author Student ID: 17034209 Name: James Alan-Abbott Hull.
 **/
public class Home extends HttpServlet {
private static final long serialVersionUID = 1L; //prevents an InvalidClassException
VehicleDAO Vehicle = new VehicleDAO();

	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Vehicle> allVEH = null;
		try {
			allVEH = Vehicle.getAllVehicles();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher view = req.getRequestDispatcher("jsp/Table.jsp");
		req.setAttribute("allVEH", allVEH);
		view.forward(req, resp);
	} //end method
	} 
