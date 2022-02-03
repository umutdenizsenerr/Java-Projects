
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package passengers;

import locations.Location;
/**
 * 
 * Defines a class that extends to Passenger class that defines discounted passengers properties
 *
 */
public class DiscountedPassenger extends Passenger{
	/**
	 * Defines a constructor for who hasn't got car
	 * @param ID passenger ID
	 * @param hasDriversLicense checks if passenger has a driver's license
	 * @param l defines passenger's current location
	 */
	
	public DiscountedPassenger(int ID, boolean hasDriversLicense, Location l) {
		super(ID, hasDriversLicense, l);
	
	}
/**
 * Defines a constructor for who has got car
 * 
 * @param ID Passenger ID
 * @param l defines passenger's current location
 * @param fuelConsumption defines car's fuel consumption rate
 */
	public DiscountedPassenger(int ID, Location l, double fuelConsumption) {
		super(ID, l, fuelConsumption);
		
	}

	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

