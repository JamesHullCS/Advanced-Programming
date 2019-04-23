package models;

/**
 * Users is used to store details about a Vehicle within the system.
 * @author Student ID: 17034209 Name: James Alan-Abbott Hull.
 **/
public class Users 
{
	private int id; //declares version of the variable in the program for the db parameter.
	private String firstname; //declares version of the variable in the program for the db parameter.
	private String surname; //declares version of the variable in the program for the db parameter.
	private String username; //declares version of the variable in the program for the db parameter.
	private String password; //declares version of the variable in the program for the db parameter.
	private int user_type; //declares version of the variable in the program for the db parameter.

	/**
	 * This is the constructor for the {@link Users} class which is created for
	 * the initialisation of objects within the class.
	 * 
	 * @param id param for vehicle
	 * @param firstname param for vehicle
	 * @param surname param for vehicle
	 * @param username param for vehicle
	 * @param password param for vehicle
	 * @param user_type param for vehicle
	 **/
	
	public Users(int id, String firstname, String surname, String username, String password, int user_type) 
	{
		this.id = id;
		this.firstname = firstname;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.user_type = user_type;
	}

	public int getId() {
		return id;
	}

	/**
	 * Sets a ID
	 * @param id param for vehicle
	 **/
	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	/**
	 * Sets a firstname
	 * @param firstname param for vehicle
	 **/
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	/**
	 * Sets a  surname
	 * @param surname param for vehicle
	 **/
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	/**
	 * Sets a username
	 * @param username param for vehicle
	 **/
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * Sets a password
	 * @param password param for vehicle
	 **/
	public void setPassword(String password) {
		this.password = password;
	}

	public int getUser_type() {
		return user_type;
	}

	/**
	 * Sets a user type
	 * @param user_type param for vehicle
	 **/
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}

	/**
	 * Output of every  when the user wants to RETRIEVE a user
	 * 
	 * returns the message we want the user to see when looking at all the
	 *          
	 **/
	public String toString() {
		return "ID is: " + id + " | " + "First name is: " + firstname + " | " + "The surname: " + surname + " | "
				+ "username " + username + " | " + "password " + password + " | " + "user_type: " + user_type;
	}


}
