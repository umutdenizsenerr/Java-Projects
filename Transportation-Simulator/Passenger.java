
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package passengers;
import java.util.Comparator;
import interfaces.ownCar;
import interfaces.usePublicTransport;
import locations.*;
import vehicles.Car;
import vehicles.PublicTransport;
import vehicles.Train;
/**
 * 
 * Defines a passenger class that defines passengers' properties
 *
 */
public class Passenger implements ownCar,usePublicTransport{
	
	/**
	 * Passenger ID
	 */
	private int ID;
	/**
	 * Checks if passenger has a driver's license
	 */
	private boolean hasDriversLicense;
	/**
	 * Balance of passenger's travel card
	 */
	private double cardBalance;
	/**
	 * Passengers current location
	 */
	private Location currentLocation;
	/**
	 * Fuel consumption rate of the car
	 */
	private double fuelConsumption;
	/**
	 * Passenger's car
	 */
	Car car=new Car(ID, fuelConsumption);
	/**
	 * Defines a constructor for who hasn't got car
	 * @param ID Passenger ID
	 * @param hasDriversLicense checks if passenger has a driver's license
	 * @param l defines passenger's current location
	 */

	public Passenger(int ID,boolean hasDriversLicense,Location l) {
		this.ID=ID;
		this.currentLocation=l;
		this.hasDriversLicense=hasDriversLicense;
	}
	/**
	 * Defines a constructor for who has got car
	 * @param ID Passenger ID
	 * @param l defines passenger's current location
	 * @param fuelConsumption defines car's fuel consumption rate
	 */
	public Passenger(int ID,Location l,double fuelConsumption) {
		this.ID=ID;
		this.currentLocation=l;
		this.fuelConsumption=fuelConsumption;
		
	}
	/**
	 * Moves passenger from its current location to a new location with public transportation and do the necessary operation to the card balance with using getDistance method from location class and getPrice method from PublicTransport class
	 * Also checks passengers' card balance	if he/she has enough balance to ride if not operation is not valid 	
	 * @param p public transport vehicle
	 * @param pass passenger who will ride
	 * @param l2 target location
	 */
	public void ride(PublicTransport p, Passenger pass,Location l2) {
		if(pass.getCardBalance()>=p.getPrice(l2.getDistance(this.currentLocation), pass)+p.getPrice(pass)) {
		l2.incomingPassenger(pass);
		this.currentLocation.outgoingPassenger(pass);
	double distance =l2.getDistance(this.currentLocation);
		this.cardBalance-=(p.getPrice(distance, pass)+p.getPrice(pass));

		this.currentLocation=l2;
		}
	}
	/**
	 * Moves passenger from its current location to a new location with its own car and do the necessary operation to fuel amount of car with using getDistance method from location class and decreaseFuelAmount method from Car class
	 * Also checks cars' fuel amount if amount is enough to drive if not operation is not valid 		
	 * 
	 * @param l target location
	 * @param pass passenger who will drive
	 * 
	 */
	public void drive(Location l , Passenger pass) {
		if(pass.hasDriversLicense&&l.getDistance(this.currentLocation)*this.fuelConsumption<=car.getfuelAmount()&&this.currentLocation.getID()!=l.getID()) {
		l.incomingPassenger(pass);
		this.currentLocation.outgoingPassenger(pass);
		double distance =l.getDistance(this.currentLocation);
		
		car.decreasefuelAmount(this.fuelConsumption*distance);

		this.currentLocation=l;
		}
	}
	
	public void drive(Location l) {
	
	}
 /**
  * Refuels passenger's car 
  * 
  * @param amount Amount of fuel that will refuel the car
  * 
  */
	public void refuel(double amount) {
		
		car.refuel(amount);

		
	}
	/**
	 * Refills passenger's travel card
	 * 
	 * @param amount Amount of money that will refill the travel card
	 */
	public void refillCard(double amount) {
		this.cardBalance+=amount;
		
	}
	/**
	 * Makes passenger the car owner
	 * 
	 * @param fuelConsumption Fuel Consumption Rate of the car
	 */
	public void purchaseCar(double fuelConsumption) {
		
		car = new Car (this.ID,fuelConsumption);
		this.fuelConsumption=fuelConsumption;
		
	}
	/**
	 * Gets current location of passenger
	 * 
	 * @return the current location of passenger
	 * 
	 */
	public Location getCurrentLocation() {
		return this.currentLocation;
	}
	/**
	 * Sets the current location of passenger
	 * 
	 * @param l Location that passenger will reach
	 */
	public void setCurrentLocation(Location l) {
		this.currentLocation=l;
	}
	/**
	 * Sets passenger's driver license
	 * @param m Shows whether passenger has driver license or not
	 */
	
	public void setDriversLicense(boolean m) {
		this.hasDriversLicense=m;
	}
	/** 
	 * Gets passenger's driver license
	 * 
	 * @return passenger's driver license
	 */
	public boolean getDriversLicense() {
		return hasDriversLicense;
	}
	/**
	 * Gets passenger's ID
	 * @return passenger's ID
	 */
	public int getID() {
		return ID;
	}
	/**
	 * Sets passenger's ID
	 * @param k passenger's ID
	 */
	public void setID(int k) {
		this.ID=k;
	}
	/**
	 * Increases passenger's ID by one
	 * 
	 */
	public void increaseID() {
		this.ID++;
	}
	/**
	 * Gets fuel consumption rate of passenger's car
	 * @return fuel consumption rate of passenger's car
	 */
	public double getFuelConsumption() {
		return fuelConsumption;
	}
	/**
	 * Gets balance of passenger's travel card
	 * @return balance of passenger's travel card
	 */
	public double getCardBalance() {
		return this.cardBalance;
	}
	
	public void ride(PublicTransport p, Location l) {

	}
	/**
	 * Gets fuel amount of passenger's car
	 * @return fuel amount of passenger's car
	 */
	public double getFuelAmount() {
		return car.getfuelAmount();
	}

}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

