package models;

/**
 * {@link Vehicle} is used to store details about a Vehicle within the system.
 * @author Student ID: 17034209 Name: James Alan-Abbott Hull.
 **/
public class Vehicle {

	// All the variables that are used to store an instance of a vehicle.
	private int vehicle_id;
	private String make;
	private String model;
	private int year;
	private int price;
	private String license_number;
	private String colour;
	private int number_doors;
	private String transmission;
	private int mileage;
	private String fuel_type;
	private int engine_size;
	private String body_style;
	private String condition;
	private String notes;
	private Boolean sales;

	/**
	 * This is the constructor for the {@link Vehicle} class which is created for
	 * the initialisation of objects within the class.
	 * 
	 * @param vehicle_id param for vehicle
	 * @param make param for vehicle
	 * @param model param for vehicle
	 * @param year param for vehicle
	 * @param price param for vehicle
	 * @param license_number param for vehicle
	 * @param colour param for vehicle
	 * @param number_doors param for vehicle
	 * @param transmission param for vehicle
	 * @param mileage param for vehicle
	 * @param fuel_type param for vehicle
	 * @param engine_size param for vehicle
	 * @param body_style param for vehicle
	 * @param condition param for vehicle
	 * @param notes param for vehicle
	 * @param sales param for vehicle
	 **/

	public Vehicle(int vehicle_id, String make, String model, int year, int price, String license_number, String colour,
			int number_doors, String transmission, int mileage, String fuel_type, int engine_size, String body_style,
			String condition, String notes, Boolean sales) {

		// Each initialised variable.
		this.vehicle_id = vehicle_id;
		this.make = make;
		this.model = model;
		this.year = year;
		this.price = price;
		this.license_number = license_number;
		this.colour = colour;
		this.number_doors = number_doors;
		this.transmission = transmission;
		this.mileage = mileage;
		this.fuel_type = fuel_type;
		this.engine_size = engine_size;
		this.body_style = body_style;
		this.condition = condition;
		this.notes = notes;
		this.sales = sales;
	}

	/**
	 *  blank constructor
	 **/
	public Vehicle() {

	}

	/**
	 * Get a Vehicle ID
	 * @return vehicle_id
	 **/
	public int getVehicle_id() {
		return vehicle_id;
	}

	/**
	 * Sets a Vehicle ID
	 * @param vehicle_id param for vehicle
	 **/
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	/**
	 * Get a Vehicle MAKE
	 * 
	 * @return MAKE
	 **/
	public String getMake() {
		return make;
	}

	/**
	 * Sets a  MAKE
	 * @param make param for vehicle
	 **/
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * Get a MODEL
	 * 
	 * @return MODEL
	 **/
	public String getModel() {
		return model;
	}

	/**
	 * Sets a MODEL
	 * @param model param for vehicle
	 **/
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Get a  YEAR
	 * 
	 * @return  YEAR
	 **/
	public int getYear() {
		return year;
	}

	/**
	 * Sets a YEAR
	 * @param year param for vehicle
	 **/
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Get a PRICE
	 * 
	 * @return  PRICE
	 **/
	public int getPrice() {
		return price;
	}

	/**
	 * Sets a  PRICE
	 * @param price param for vehicle
	 **/
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * Get a  LICENSE_NUMBER
	 * 
	 * @return LICENSE_NUMBER
	 **/
	public String getLicense_number() {
		return license_number;
	}

	/**
	 * Sets a LICENSE_NUMBER
	 * @param license_number param for vehicle
	 **/
	public void setLicense_number(String license_number) {
		this.license_number = license_number;
	}

	/**
	 * Get a COLOUR
	 * 
	 * @return COLOUR
	 **/
	public String getColour() {
		return colour;
	}

	/**
	 * Sets a COLOUR
	 * @param colour param for vehicle
	 **/
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * Get a NUMBER OF DOORS
	 * @return number_doors
	 **/
	public int getNumber_doors() {
		return number_doors;
	}

	/**
	 * Sets a NUMBER OF DOORS
	 * @param number_doors param for vehicle
	 **/
	public void setNumber_doors(int number_doors) {
		this.number_doors = number_doors;
	}

	/**
	 * Get a TRANSMISSION
	 * 
	 * @return TRANSMISSION
	 **/
	public String getTransmission() {
		return transmission;
	}

	/**
	 * Sets a  TRANSMISSION
	 * @param transmission param for vehicle
	 **/
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	/**
	 * Get a MILEAGE
	 * 
	 * @return MILEAGE
	 **/
	public int getMileage() {
		return mileage;
	}

	/**
	 * Sets a MILEAGE
	 * @param mileage param for vehicle
	 **/
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	/**
	 * Get a  FUEL TYPE
	 * 
	 * @return FUEL TYPE
	 **/
	public String getFuel_type() {
		return fuel_type;
	}

	/**
	 * Sets a  FUEL TYPE
	 * @param fuel_type param for vehicle
	 **/
	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
	}

	/**
	 * Get a  ENGINE SIZE
	 * 
	 * @return ENGINE SIZE
	 **/
	public int getEngine_size() {
		return engine_size;
	}

	/**
	 * Sets a  ENGINE SIZE
	 * @param engine_size param for vehicle
	 **/
	public void setEngine_size(int engine_size) {
		this.engine_size = engine_size;
	}

	/**
	 * Get a  BODY STYLE
	 * 
	 * @return BODY STYLE
	 **/
	public String getBody_style() {
		return body_style;
	}

	/**
	 * Sets a BODY STYLE
	 * @param body_style param for vehicle
	 **/
	public void setBody_style(String body_style) {
		this.body_style = body_style;
	}

	/**
	 * Get a  CONDITION
	 * 
	 * @return  CONDITION
	 **/
	public String getCondition() {
		return condition;
	}

	/**
	 * Sets a  CONDITION
	 * @param condition param for vehicle
	 **/
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * Get a  NOTES
	 * 
	 * @return  NOTES
	 **/
	public String getNotes() {
		return notes;
	}

	/**
	 * Sets a  NOTES
	 * @param notes param for vehicle
	 **/
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * Get a sales
	 * 
	 * @return sales
	 **/
	public Boolean getSales() {
		return sales;
	}

	/**
	 * Sets a  sales
	 * @param sales param for vehicle
	 **/
	public void setSales(Boolean sales) {
		this.sales = sales;
	}

	/**
	 * Output of every  when the user wants to RETRIEVE a
	 * 
	 * 
	 **/
	public String toString() {
		return "Vehicle ID is: " + vehicle_id + " The make is: " + make + " The model is: " + model
				+ " The year of this vehicle is: " + year + " The Price of this vehicle is: " + price
				+ " The license plate is: " + license_number + " The colour is: " + colour
				+ " The amount of doors this vehicle has is: " + number_doors + " The transmittion of the car is: "
				+ transmission + " The current mileage of the car is: " + mileage + " The fuel type is: " + fuel_type
				+ " The engine size is: " + engine_size + " The body style is: " + body_style
				+ " The condition of the vehicle is: " + condition + " Additional Notes: " + notes + " Sold: " + sales;
	}

}
