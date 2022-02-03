
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package vehicles;
/**
 * Defines a class that defines car's properties 
 * 
 */
public class Car{

/**
 * Car owner's ID
 */
private int ownerID;
/**
 * Car's Fuel Amount
 */
private double fuelAmount;
/**
 * Car's Fuel Consumption Rate
 */
private double fuelConsumption;
/**
 * Defines constructor of Car class
 * @param ID Car owner's ID
 * @param fuelConsumption Car's Fuel Consumption Rate
 */
public Car(int ID, double fuelConsumption){
	
	this.ownerID=ID;
	this.fuelConsumption=fuelConsumption;
	
	
}
/**
 * Refuels passenger's car 
 * @param amount Amount of fuel that will refuel the car
 */
public void refuel(double amount) {
	this.fuelAmount+=amount;
	
	
}
/**
 * Decreases fuel amount
 * @param fuel Amount of fuel that will decrease
 */
public void decreasefuelAmount(double fuel) {
	this.fuelAmount-=fuel;
	
}
/**
 * Gets Fuel Amount
 * @return Fuel Amount
 */
public double getfuelAmount() {
	return this.fuelAmount;
}
/**
 * Gets Fuel Consumption Rate
 * @return Fuel Consumption Rate
 */
public double getfuelConsumption() {
	return this.fuelConsumption;
}
/**
 * Sets Fuel Consumption Rate
 * @param n Fuel Consumption Rate
 */
public void setfuelConsumption(double n) {
	n=fuelConsumption;
}
/**
 * Gets owner ID
 * @return owner ID
 */
public int getownerID() {
	return ownerID;
}
/**
 * Sets owner ID
 * @param g owner ID
 */
public void setownerID(int g) {
	
}


}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

