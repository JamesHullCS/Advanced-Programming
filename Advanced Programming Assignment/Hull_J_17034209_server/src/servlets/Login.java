package servlets;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import models.Users;
import models.UsersDAO;
/**
 * This class is the servlet for the login which allows the user to login and gain access to there admin privileges
 * ,checking that the password matches the hashed one but allows the user to enter the unhashed password.
 * @author Student ID: 17034209 Name: James Alan-Abbott Hull.
 */
public class Login extends HttpServlet {
private static final long serialVersionUID = 1L; //prevents an InvalidClassException
UsersDAO Users = new UsersDAO(); //Links to the Users class and allows me to use the methods from them by simply making a call.
	
	/**
	 * The doGet is for directing you to the login page after clicking on the login href
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		RequestDispatcher view = req.getRequestDispatcher("jsp/LoginPage.jsp");
		view.forward(req, resp);
	}
	/**
	 * The doPost is used to grab the data that you enter for user and password, and compare it to the the data in the database.
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String USERNAME = req.getParameter("username"); //gets the username
		String PASSWORD = req.getParameter("password"); //gets the password
		
		MessageDigest MessageDigests = null; //creates an empty 'string' MessageDigest
		try {
			MessageDigests = MessageDigest.getInstance("MD5"); //Tells the program that this string is an MD5 Hash
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		MessageDigests.update(PASSWORD.getBytes()); 
		byte[] digest = MessageDigests.digest();
		PASSWORD = DatatypeConverter.printHexBinary(digest).toUpperCase(); //This is used to finish the MD5 hash.

		ArrayList<Users> allUsers = null;
		
			try {
				allUsers = Users.getAllUsers();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		for(Users u : allUsers) //for all users
		{
			if (USERNAME.equals(u.getUsername()) && PASSWORD.equals(u.getPassword())) //compare the entered username and password with the ones from the database to get a match.
			{
				HttpSession HttpSession = req.getSession(); //requests to make a session variable
				HttpSession.setAttribute("login", true); //creates a the login session variable and sets it to true
				resp.sendRedirect("./home"); //after all of the above it done it'll redirect
			}
		}
	}

}
