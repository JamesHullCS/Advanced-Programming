package defaultpackage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.Users;
import models.UsersDAO;
import models.Vehicle;
import models.VehicleDAO;
/**
 * This runs the console User Interface (UI) using a database in the background to allows the user to retrieve vehicles or a vehicle, insert a new vehicle,
 * update an existing vehicle, delete a new Vehicle and see admin settings such as retrieve all users, insert a new user and Delete a user.
 * @author Student ID: 17034209 Name: James Alan-Abbott Hull. 
 */
public class SystemControls {

	public static void main(String[] args) throws Exception {
		VehicleDAO Vehicle = new VehicleDAO(); // Calls in and allows me to use the VehicleDAO class
		UsersDAO Users = new UsersDAO(); // Calls in and allows me to use the UsersDAO class
		char USERINPUT; // saves a character variable.
		Scanner Scan = new Scanner(System.in); // declaring the use of a scanner
		ArrayList<Vehicle> allVEH = null; // Vehicles array list
		ArrayList<Users> allUsers = null; // users array list

		int vehicle_id, year, price, number_doors, mileage, engine_size;
		String make, model, license_number = null, colour, transmission, fuel_type, body_style, condition, notes;
		Boolean sales = null;

		do {
			System.out.println("-------------------------");
			System.out.println("Vehicle Inventory System");
			System.out.println("Choose from these options");
			System.out.println("-------------------------");
			System.out.println("1 - Retrieve all vehicles");
			System.out.println("2 - Search for vehicle");
			System.out.println("3 - Insert new vehicle into database");
			System.out.println("4 - Update existing vehicle details");
			System.out.println("5 - Delete vehicle from database");
			System.out.println("6 - Retrieve all users");
			System.out.println("7 - Insert new user into the database");
			System.out.println("8 - Delete a user from the database");
			System.out.println("9 - Exit");
			System.out.println("Enter choice >");

			USERINPUT = Scan.next().charAt(0); // Allows me to enter a number from 1-9
			switch (USERINPUT) {
			case '1':
				try {
					allVEH = Vehicle.getAllVehicles();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

				for (Vehicle v : allVEH) {
					System.out.println(v);
				}
				break;

			case '2':
				System.out.println("Enter ID number of vehicles >");
				try {
					Vehicle.getVehicle(Scan.nextInt());
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;

			case '3':
				System.out.println("Your enterering a Vehicle into the system");
				System.out.println("Enter Vehicle_id: ");
				
				vehicle_id = Scan.nextInt();
				System.out.println("Enter make: ");
				make = Scan.next();
				System.out.println("Enter model: ");
				model = Scan.next();
				System.out.println("Enter year: ");
				year = Scan.nextInt();
				System.out.println("Enter price: ");
				price = Scan.nextInt();
				System.out.println("Enter license_number (e.g MM15GFG (No spaces)): ");
				license_number = Scan.next();
				System.out.println("Enter colour: ");
				colour = Scan.next();
				System.out.println("Enter number_doors: ");
				number_doors = Scan.nextInt();
				System.out.println("Enter transmission: ");
				transmission = Scan.next();
				System.out.println("Enter mileage: ");
				mileage = Scan.nextInt();
				System.out.println("Enter fuel_type: ");
				fuel_type = Scan.next();
				System.out.println("Enter engine_size: ");
				engine_size = Scan.nextInt();
				System.out.println("Enter body_style: ");
				body_style = Scan.next();
				System.out.println("Enter condition: ");
				condition = Scan.next();
				System.out.println("Enter notes: ");
				notes = Scan.next();
				System.out.println("Enter (true) if sold (false) if not: ");
				sales = Scan.nextBoolean();
				

				Vehicle insertVehicle = new Vehicle(vehicle_id, make, model, year, price, license_number, colour,
						number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, notes,
						sales);
				try 
				{
					Vehicle.insertVehicle(insertVehicle);
				}

				catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;

			case '4':
				System.out.println("Your enterering a Vehicle into the system");
				System.out.println("Enter Vehicle_id: ");
				vehicle_id = Scan.nextInt();
				System.out.println("Enter make: ");
				make = Scan.next();
				System.out.println("Enter model: ");
				model = Scan.next();
				System.out.println("Enter year: ");
				year = Scan.nextInt();
				System.out.println("Enter price: ");
				price = Scan.nextInt();
				System.out.println("Enter license_number (e.g MM15GFG (No spaces)): ");
				license_number = Scan.next();
				System.out.println("Enter colour: ");
				colour = Scan.next();
				System.out.println("Enter number_doors: ");
				number_doors = Scan.nextInt();
				System.out.println("Enter transmission: ");
				transmission = Scan.next();
				System.out.println("Enter mileage: ");
				mileage = Scan.nextInt();
				System.out.println("Enter fuel_type: ");
				fuel_type = Scan.next();
				System.out.println("Enter engine_size: ");
				engine_size = Scan.nextInt();
				System.out.println("Enter body_style: ");
				body_style = Scan.next();
				System.out.println("Enter condition: ");
				condition = Scan.next();
				System.out.println("Enter notes: ");
				notes = Scan.next();
				System.out.println("Enter (true) if sold (false) if not: ");
				sales = Scan.nextBoolean();
				

				Vehicle updateVehicle = new Vehicle(vehicle_id, make, model, year, price, license_number, colour,
						number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, notes,
						sales);

				try {
					Vehicle.updateVehicle(updateVehicle, vehicle_id);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;

			case '5':
				System.out.println("Enter ID number of vehicles >");
				Vehicle.deleteVehicle(Scan.nextInt());
				break;

			case '6':
				try {
					allUsers = Users.getAllUsers();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

				for (Users u : allUsers) {
					System.out.println(u);
				}
				break;

			case '7':
				int id, user_type;
				String firstname, surname, username, password;
				System.out.println("Your enterering a User into the system");
				System.out.println("Enter an ID: ");
				id = Scan.nextInt();
				System.out.println("Enter your firstname: ");
				firstname = Scan.next();
				System.out.println("Enter your lastname: ");
				surname = Scan.next();
				System.out.println("Enter a username: ");
				username = Scan.next();
				System.out.println("Enter a password: ");
				password = Scan.next();
				System.out.println("Enter a user_type: ");
				user_type = Scan.nextInt();

				Users insertUsers = new Users(id, firstname, surname, username, password, user_type);
				Users.insertUsers(insertUsers);
				break;


			case '8':
				System.out.println("Enter ID number of vehicles >");
				Users.deleteUsers(Scan.nextInt());
				break;

			case '9':
				System.exit(1);
				break;
			}
		} while (USERINPUT != 9);
		Scan.close();
	}
}
