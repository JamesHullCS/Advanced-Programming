package servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Logout class removes the login session variable and redirects the user back to HOME
 * @author Student ID: 17034209 Name: James Alan-Abbott Hull.
 */
public class Logout extends HttpServlet 
{
	private static final long serialVersionUID = 1L; //prevents an InvalidClassException
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		req.getSession().removeAttribute("login");
		resp.sendRedirect("./home");
	}
	
}
