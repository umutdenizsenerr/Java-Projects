
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package vehicles;
import locations.*;
/**
 * Defines an abstract class that defines Public Transportation vehicles' properties 
 */
import passengers.*;
public abstract class PublicTransport {
	/**
	 * Vehicle ID
	 */
protected int ID;
/**
 * one of the X values of rectangular area that restrict vehicles' range
 */
protected double x1;
/**
 * one of the Y values of rectangular area that restrict vehicles' range
 */
protected double y1;
/**
 * one of the X values of rectangular area that restrict vehicles' range
 */
protected double x2;
/**
 * one of the Y values of rectangular area that restrict vehicles' range
 */
protected double y2;
/**
 * Defines the constructor of PublicTransport class
 * @param ID Vehicle ID
 * @param x1 one of the X values of rectangular area that restrict vehicles' range
 * @param y1 one of the Y values of rectangular area that restrict vehicles' range
 * @param x2 one of the X values of rectangular area that restrict vehicles' range
 * @param y2 one of the Y values of rectangular area that restrict vehicles' range
 */
public PublicTransport(int ID,double x1,double y1,double x2,double y2) {
	
	this.x1=x1;
	this.ID=ID;
	this.x2=x2;
	this.y1=y1;
	this.y2=y2;
	
}
/**
 * Checks whether the location that passenger currently in and the location passenger wants to reach is in the range of the vehicle
 * @param departure the location that passenger currently in
 * @param arrival the location passenger wants to reach
 * @return whether the location that passenger currently in and the location passenger wants to reach is in the range of the vehicle
 */
public boolean canRide(Location departure, Location arrival) {
	return departure.getlocationX()>=x1&&departure.getlocationY()>=y1&&
			departure.getlocationX()<=x2&&departure.getlocationY()<=y2&&arrival.getlocationX()>=x1&&arrival.getlocationY()>=y1&&
			arrival.getlocationX()<=x2&&arrival.getlocationY()<=y2&&departure.getID()!=arrival.getID();
	
	
	
}
/**
 * Gets x1 value of the rectangular area
 * @return x1 value of the rectangular area
 */
public double getx1() {
	return x1;
}
/**
 * Gets y1 value of the rectangular area
 * @return y1 value of the rectangular area
 */
public double gety1() {
	return y1;
}
/**
 * Gets x2 value of the rectangular area
 * @return x2 value of the rectangular area
 */
public double getx2() {
	return x2;
}
/**
 * Gets y2 value of the rectangular area
 * @return y2 value of the rectangular area
 */
public double gety2() {
	return y2;
}
/**
 * Gets vehicle ID
 * @return vehicle ID
 */
public int getID() {
	return ID;
}
/**
 * Sets vehicle ID
 * @param a  new vehicle ID
 */
public void setID(int a) {
	this.ID = a;
}
/**
 * Increases vehicle ID
 */
public void increaseID() {
	 ID++;
}

public abstract double getPrice(Passenger p) ;

public abstract double getPrice(double distance,Passenger p) ;

}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE





