package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * This is the class that connects to the database and tests it using CRUD;
 * @author Student ID: 17034209 Name: James Alan-Abbott Hull. 
 **/
public class VehicleDAO {

	/**
	 * This method {@link getDBConnection} connects the database to the program.
	 * This is the class that connects to the database and tests it using CRUD;
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
	 * This method grabs all the data from the database table
	 * and prints it to the console.
	 * @return listOfVehicles
	 * @throws java.sql.SQLException allows the use of sql
	 **/
	public ArrayList<Vehicle> getAllVehicles() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet resultset = null;
		ArrayList<Vehicle> listOfVehicles = new ArrayList<>();

		try {
			String query = "SELECT * FROM vehicles;";
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			resultset = statement.executeQuery(query);

			while (resultset.next()) {
				int vehicle_id = resultset.getInt("vehicle_id");
				String make = resultset.getString("make");
				String model = resultset.getString("model");
				int year = resultset.getInt("year");
				int price = resultset.getInt("price");
				String license_number = resultset.getString("license_number");
				String colour = resultset.getString("colour");
				int number_doors = resultset.getInt("number_doors");
				String transmission = resultset.getString("transmission");
				int mileage = resultset.getInt("mileage");
				String fuel_type = resultset.getString("fuel_type");
				int engine_size = resultset.getInt("engine_size");
				String body_style = resultset.getString("body_style");
				String condition = resultset.getString("condition");
				String notes = resultset.getString("notes");
				Boolean sales = resultset.getBoolean("sales");
				listOfVehicles.add(new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors,
				transmission, mileage, fuel_type, engine_size, body_style, condition, notes, sales));
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
		return listOfVehicles;
	}

	/**
	 * This method grabs data about a vehicle from the database
	 * table based on the ID you enter. It is printed to console using a prepared
	 * SQL statement to protect against sql injections.
	 * @return exambleCar
	 * @throws java.sql.SQLException allows the use of sql
	 * @param id param for vehicle
	 **/
	public Vehicle getVehicle(int id) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		Vehicle exampleVehicle = null;

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement("SELECT * FROM vehicles WHERE vehicle_id = ?");
			preparedStatement.setInt(1, id);
			resultset = preparedStatement.executeQuery();
			int vehicle_id = resultset.getInt("vehicle_id");
			String make = resultset.getString("make");
			String model = resultset.getString("model");
			int year = resultset.getInt("year");
			int price = resultset.getInt("price");
			String license_number = resultset.getString("license_number");
			String colour = resultset.getString("colour");
			int number_doors = resultset.getInt("number_doors");
			String transmission = resultset.getString("transmission");
			int mileage = resultset.getInt("mileage");
			String fuel_type = resultset.getString("fuel_type");
			int engine_size = resultset.getInt("engine_size");
			String body_style = resultset.getString("body_style");
			String condition = resultset.getString("condition");
			String notes = resultset.getString("notes");
			Boolean sales = resultset.getBoolean("sales");

			exampleVehicle = new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors,
					transmission, mileage, fuel_type, engine_size, body_style, condition, notes, sales);
			System.out.print(exampleVehicle);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (resultset != null) {
				resultset.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return exampleVehicle;
	}

	/**
	 * This method takes in an ID and based on the ID entered
	 * it will delete the vehicle from the database.
	 * @throws java.sql.SQLException allows the use of sql
	 * @param vehicle_id param for vehicle
	 * @return true
	 **/
	public Boolean deleteVehicle(int vehicle_id) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement("DELETE FROM vehicles WHERE vehicle_id = ?");
			preparedStatement.setInt(1, vehicle_id);
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
//		/System.out.println(getAllVehicles());
		return true;
	}

	/**
	 * This method allows you to enter in an instance of a
	 * vehicle. Once it's taken all the inputs it'll create the vehicle by entering
	 * it into the database.
	 * @param Car param for vehicle
	 * @return true
	 * @throws SQLException allows you to use sql
	 **/
	public Boolean insertVehicle(Vehicle vehicleDetails) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement("INSERT INTO vehicles (vehicle_id,make,model,year,price,license_number,colour,number_doors,transmission,mileage,fuel_type,engine_size,body_style,condition,notes,sales) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			preparedStatement.setInt(1, vehicleDetails.getVehicle_id());
			preparedStatement.setString(2, vehicleDetails.getMake());
			preparedStatement.setString(3, vehicleDetails.getModel());
			preparedStatement.setInt(4, vehicleDetails.getYear());
			preparedStatement.setInt(5, vehicleDetails.getPrice());
			preparedStatement.setString(6, vehicleDetails.getLicense_number());
			preparedStatement.setString(7, vehicleDetails.getColour());
			preparedStatement.setInt(8, vehicleDetails.getNumber_doors());
			preparedStatement.setString(9, vehicleDetails.getTransmission());
			preparedStatement.setInt(10, vehicleDetails.getMileage());
			preparedStatement.setString(11, vehicleDetails.getFuel_type());
			preparedStatement.setInt(12, vehicleDetails.getEngine_size());
			preparedStatement.setString(13, vehicleDetails.getBody_style());
			preparedStatement.setString(14, vehicleDetails.getCondition());
			preparedStatement.setString(15, vehicleDetails.getNotes());
			preparedStatement.setBoolean(16, vehicleDetails.getSales());
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
	 * This method allows you to enter in changes to a
	 * vehicle. Once it's taken all the inputs it'll update the vehicle by taking an
	 * ID and altering it in the database.
	 * @param vehicle_id param for vehicle
	 * @param Car param for vehicle
	 * @throws SQLException allows you to use sql
	 * @return true
	 **/
	public Boolean updateVehicle(Vehicle vehicleDetails, int vehicle_id) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement("UPDATE vehicles SET make = ?,model =?,year=?,price=?,license_number=?,colour=?,number_doors=?,transmission=?,mileage=?,fuel_type=?,engine_size=?,body_style=?,Condition=?,notes=?,sales=? WHERE vehicle_id= ?");
			preparedStatement.setString(1, vehicleDetails.getMake());
			preparedStatement.setString(2, vehicleDetails.getModel());
			preparedStatement.setInt(3, vehicleDetails.getYear());
			preparedStatement.setInt(4, vehicleDetails.getPrice());
			preparedStatement.setString(5, vehicleDetails.getLicense_number());
			preparedStatement.setString(6, vehicleDetails.getColour());
			preparedStatement.setInt(7, vehicleDetails.getNumber_doors());
			preparedStatement.setString(8, vehicleDetails.getTransmission());
			preparedStatement.setInt(9, vehicleDetails.getMileage());
			preparedStatement.setString(10, vehicleDetails.getFuel_type());
			preparedStatement.setInt(11, vehicleDetails.getEngine_size());
			preparedStatement.setString(12, vehicleDetails.getBody_style());
			preparedStatement.setString(13, vehicleDetails.getCondition());
			preparedStatement.setString(14, vehicleDetails.getNotes());
			preparedStatement.setBoolean(15, vehicleDetails.getSales());
			
			preparedStatement.setInt(16, vehicleDetails.getVehicle_id());
			
			
			
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
