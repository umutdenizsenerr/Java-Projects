
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package vehicles;

import passengers.Passenger;
import passengers.StandardPassenger;
/**
 *  Defines a class that defines bus's properties 
 *
 *
 */
public class Bus extends PublicTransport{
	/**
	 * Defines constructor of Train class
	 * @param ID Vehicle ID
	 * @param x1 one of the X values of rectangular area that restrict vehicles' range
	 * @param y1 one of the Y values of rectangular area that restrict vehicles' range
	 * @param x2 one of the X values of rectangular area that restrict vehicles' range
	 * @param y2 one of the Y values of rectangular area that restrict vehicles' range
	 */

	public Bus(int ID, double x1, double y1, double x2, double y2) {
		super(ID, x1, y1, x2, y2);
		this.ID=ID;
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
		
	
	}
	/**
	 *Calculates the price that will taken according to type of passenger
	 *@param p Passenger that will ride
	 */
	public double getPrice(Passenger p) {
		if (p instanceof StandardPassenger) {
		return 2.00;}
		else {
		return 1.00;
		}
	}

	public double getPrice(double distance, Passenger p) {
				return 0;
	}
	
	
	
	
}
//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

