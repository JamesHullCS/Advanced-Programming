package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;
/**
 * This is the class takes all the users from the db and tests them by selecting all, inserting and deleting users.
 * @author Student ID: 17034209 Name: James Alan-Abbott Hull.
 **/
public class UsersDAO {

	/**
	 * This method  connects the database to the program.
	 **/
	private static Connection getDBConnection() {
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			String dbURL = "jdbc:sqlite:vehicles.sqlite";
			connection = DriverManager.getConnection(dbURL);
			return connection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}

	/**
	 * This method getAllVehicles grabs all the data from the database table
	 * and prints it to the console.
	 * @return listOfUsers
	 * @throws SQLException this allows the use of sql
	 **/
	public ArrayList<Users> getAllUsers() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultset = null;
		ArrayList<Users> listOfUsers = new ArrayList<>();

		try {
			String query = "SELECT * FROM Users;";
			
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(query);

			// execute SQL query
			resultset = statement.executeQuery(query);

			while (resultset.next()) {
				int id = resultset.getInt("id");
				String firstname = resultset.getString("firstname");
				String surname = resultset.getString("surname");
				String username = resultset.getString("username");
				String password = resultset.getString("password");
				int user_type = resultset.getInt("user_type");

				listOfUsers.add(new Users(id, firstname, surname, username, password, user_type));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (resultset != null) {
				resultset.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return listOfUsers;
	}

	/**
	 * This method insertVehicle allows you to enter in an instance of a
	 * vehicle. Once it's taken all the inputs it'll create the vehicle by entering
	 * it into the database.
	 * @param USER for user
	 * @return true
	 * @throws SQLException allows me to use sql
	 * @throws NoSuchAlgorithmException allows me to use the md5 hash without error
	 **/
	public Boolean insertUsers(Users USER) throws SQLException, NoSuchAlgorithmException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {
			MessageDigest MD = null;
			String HASH;

			MD = MessageDigest.getInstance("MD5");
			MD.update(USER.getPassword().getBytes());
			byte[] digest = MD.digest();
			HASH = DatatypeConverter.printHexBinary(digest).toUpperCase();
			USER.setPassword(HASH);

			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(
					"INSERT INTO users (id,firstname,surname,username,password,user_type) " + "VALUES (?,?,?,?,?,?);");

			preparedStatement.setInt(1, USER.getId());
			preparedStatement.setString(2, USER.getFirstname());
			preparedStatement.setString(3, USER.getSurname());
			preparedStatement.setString(4, USER.getUsername());
			preparedStatement.setString(5, USER.getPassword());
			preparedStatement.setInt(6, USER.getUser_type());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return true;
	}

	/**
	 * This method @link deleteVehicle takes in an ID and based on the ID entered
	 * it will delete the vehicle from the database.
	 * @param id is the paramater
	 * @throws SQLException allows me to use sql
	 * @return true
	 **/
	public Boolean deleteUsers(int id) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement("DELETE FROM users WHERE id = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return true;
	}

}
