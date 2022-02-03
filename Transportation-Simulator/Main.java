
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
/**
 * 
 * @author Umut Deniz Þener
 * @version 1.0
 * 
 */

package main;

import java.util.*;
import vehicles.*;
import java.io.*;
import java.text.DecimalFormat;

import passengers.*;
import locations.*;
/**
 * Defines a class that implements a simulation of a transportation system.
 * 
 *
 */
public class Main {
	/**
	 * Reads the input and takes the operation number from input in order to understand which operation will be done.
	 * After that does the necessary operations in simulation of a transportation system and writes every passengers' location in an output file.
	 * Also calls <code>twoDecimal</code> method in order to make all double values two decimal 
	 * 
	 * @param args takes input file to test the code.
	 * @throws FileNotFoundException prevents the file not found exception error
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner input = new Scanner(new File(args[0])); // to scan the input file
		PrintStream output = new PrintStream(new File(args[1])); // to write the necessary things to the output file
		ArrayList<Passenger> passengers = new ArrayList<Passenger>(); // to create an array list that will save the every valid passenger
		ArrayList<Location> locations = new ArrayList<Location>();// to create an array list that will save the every valid location
		ArrayList<PublicTransport> vehicles = new ArrayList<PublicTransport>();// to create an array list that will save the every valid vehicle
		
	
		 Location l = new Location(0, 0, 0); // The first location is always (0,0).
		
		 locations.add(l); //to add first location
		 
		 
		 
		 Location l2 = new Location(1, 0, 0); // to initialize location object
		
		 
		 Bus bus=new Bus(0,0.0,0.0,0.0,0.0);// to initialize bus object
		 
		 Train train = new Train(0,0.0,0.0,0.0,0.0);// to initialize train object
		 
		 Passenger p1 = new Passenger(0, false, l);// to initialize passenger object
		 
		 Car c = new Car (0,0.0);// to initialize car variable
		
		 DiscountedPassenger dp = new DiscountedPassenger(0,false,l);// to initialize discounted passenger object
		
		 StandardPassenger sp = new StandardPassenger(0,false,l);	// to initialize standard passenger object

		 int operations = input.nextInt(); // operation count
		 int codedNumber = input.nextInt(); //to take operation number from input
	
		 
			 
		while(operations>0) { //for starting a while loop in order to take all input from input file
		
			 while(codedNumber==1) { //if our operation number is 1
			
			if(input.next().charAt(0)=='D') { //if the passenger is discounted passenger
			
			dp = new DiscountedPassenger(p1.getID(),input.nextInt()==1,l); //creating a discounted passenger object
			

			
			if(input.nextInt()==1) { // if he/she has car 
				
				 c = new Car(p1.getID(),input.nextDouble()); //creating a car object
				 dp = new DiscountedPassenger(p1.getID(),l,c.getfuelConsumption()); // to use other constructor of passenger object
				 dp.setDriversLicense(true); // to set drivers license true
				
			}

			p1.increaseID(); // to arrange the passengers' ID numbers in ascending sort
			
			 passengers.add(dp); // adding the discounted passenger object to passengers' array list
			 l.incomingPassenger(dp); //every passenger will start from starting location (0.00,0.00)
			}
	
			else  { //if the passenger is standard passenger
			
				sp = new StandardPassenger(p1.getID(),input.nextInt()==1,l); //creating a standard passenger object
				
				
	
				if(input.nextInt()==1) { // if he/she has car
					
					 c = new Car(p1.getID(),input.nextDouble()); //creating a car object
					 sp = new StandardPassenger(p1.getID(),l,c.getfuelConsumption()); // to use other constructor of passenger object
					 sp.setDriversLicense(true); // to set drivers license true
				}
				

				p1.increaseID(); // to arrange the passengers' ID numbers in ascending sort
				 passengers.add(sp); // adding the discounted passenger object to passengers' array list
				 l.incomingPassenger(sp); //every passenger will start from starting location (0.00,0.00)
				
			}
		
			operations--; //one operation has done so we need to decrease operation number to arrange first while loop
			if(input.hasNext()) {//if input file contains more input
			codedNumber=input.nextInt(); //to take new operation number 
			}
			break;
			
		}
			

		while(codedNumber==2) { //if our operation number is 2

		Location l1 =new Location(l2.getID(),input.nextDouble(),input.nextDouble()); //to create new location object
		l2.increaseID(); // to arrange the locations' ID numbers in ascending sort
		locations.add(l1); // adding the location object to locations' array list
		
		
		operations--; //one operation has done so we need to decrease operation number to arrange first while loop
		if(input.hasNext()) { //if input file contains more input
		codedNumber=input.nextInt();//to take new operation number 
		}
		break;
			
		}
		while(codedNumber==3) { //if our operation number is 3
			
		if(input.nextInt()==1) { //if the vehicle is bus

			 Bus bus1 = new Bus(bus.getID(),input.nextDouble(),input.nextDouble(),input.nextDouble(),input.nextDouble()); //to create bus object
			
			
			
			vehicles.add(bus1); // adding the vehicle object to vehicles' array list
			bus.increaseID(); // to arrange the vehicles' ID numbers in ascending sort
	
			train.setID(bus.getID()); // to arrange buses' and trains' ID numbers increase in same order
		

		}
		else  { //if the vehicle is train
			
			Train train1 = new Train(train.getID(),input.nextDouble(),input.nextDouble(),
					input.nextDouble(),input.nextDouble()); //to create train object

			vehicles.add(train1); // adding the vehicle object to vehicles' array list
			train.increaseID(); // to arrange the vehicles' ID numbers in ascending sort
		
			bus.setID(train.getID()); //to arrange buses' and trains' ID numbers increase in same order
			
		}
		
		operations--; //one operation has done so we need to decrease operation number to arrange first while loop
		if(input.hasNext()) { //if input file contains more input
		codedNumber=input.nextInt(); //to take new operation number 
		}
			break;
		}
		while(codedNumber==4) { // if our operation number is 4
				
			int PassengerID = input.nextInt(); //takes passenger id from input
			int LocationID = input.nextInt(); //takes location id from input
			int TransportationType = input.nextInt(); //takes transportation from input
			int vehicleID; //initializes vehicleID variable
			if(TransportationType == 1 || TransportationType == 2) { //if passenger will use public transportation
				vehicleID=input.nextInt(); //takes vehicle id from input if it exists
			
				if(TransportationType==2) { // if transportation type is train
					if(vehicles.get(vehicleID) instanceof Train&&vehicles.get(vehicleID).canRide(passengers.get(PassengerID).getCurrentLocation(), locations.get(LocationID))) {
			//checks if vehicle ID is a train ID and passenger is in the boundaries of train
			passengers.get(PassengerID).ride(vehicles.get(vehicleID),
			passengers.get(PassengerID), locations.get(LocationID)); //moves passenger from its current location to new location with train and do the necessary operation to the card balance
				}}
				else if(TransportationType==1) { // if transportation type is bus
					
					if(vehicles.get(vehicleID) instanceof Bus&&vehicles.get(vehicleID).canRide(passengers.get(PassengerID).getCurrentLocation(), locations.get(LocationID))) {
						//checks if vehicle ID is a bus ID and passenger is in the boundaries of bus
						
						passengers.get(PassengerID).ride(vehicles.get(vehicleID),
						passengers.get(PassengerID), locations.get(LocationID));  //moves passenger from its current location to new location with bus and do the necessary operation to the card balance
						
					}
				}
			
			}
			else { // if passenger will use his own car.
				passengers.get(PassengerID).drive(locations.get(LocationID), passengers.get(PassengerID));//moves passenger from its current location to new location and do the necessary operation to fuel amount of car
			}
	
			operations--; //one operation has done so we need to decrease operation number to arrange first while loop
			if(input.hasNext()) { //if input file contains more input
			codedNumber=input.nextInt();//to take new operation number 
			}
			break;
		}
		while(codedNumber==5) { // if our operation number is 5
			int PassengerID = input.nextInt(); //takes passenger id from input
			double FuelConsumptionRate = input.nextDouble(); //takes fuel consumption rate from input
			passengers.get(PassengerID).setDriversLicense(true); // makes passengers' driver license true
			passengers.get(PassengerID).purchaseCar(FuelConsumptionRate); //passenger purchases a new car
		
			operations--; //one operation has done so we need to decrease operation number to arrange first while loop

			if(input.hasNext()) { //if input file contains more input
			codedNumber=input.nextInt(); //to take new operation number 
			}
			
			break;
		}
		while(codedNumber==6) { // if our operation number is 6
			int PassengerID = input.nextInt(); //takes passenger id from input
			double FuelAmount = input.nextDouble(); //takes fuel amount from input
			if(passengers.get(PassengerID).getDriversLicense()) { //if passenger has drivers license
		passengers.get(PassengerID).refuel(FuelAmount); //passenger refuel its car
		}
		operations--; //one operation has done so we need to decrease operation number to arrange first while loop
		if(input.hasNext()) { //if input file contains more input
		codedNumber=input.nextInt(); //to take new operation number 
		}
			break;
		}
		while(codedNumber==7) { // if our operation number is 7
			
			int PassengerID = input.nextInt(); //takes passenger id from input
			double TravelCardAmount = input.nextDouble(); //takes the amount of money that will refill in passenger's travel card from input
			passengers.get(PassengerID).refillCard(TravelCardAmount); //refills the passenger's travel card
			
			operations--; //one operation has done so we need to decrease operation number to arrange first while loop
			if(input.hasNext()) { //if input file contains more input
			codedNumber=input.nextInt(); //to take new operation number 
			}
			
			break;
		}
		
		
		}
	
		
		for(int a = 0; a<locations.size();a++) { //for loop in order to print the locations in ascending order one by one

			output.println("Location "+locations.get(a).getID()+": ("+
		twoDecimal(locations.get(a).getlocationX())+", "+twoDecimal(locations.get(a).getlocationY())+")");
			
		for(int k = 0 ; k<locations.get(a).getCurr().size();k++) { //for loop in order to print passengers under locations which they are currently in
			Collections.sort(locations.get(a).getCurr(),new Sorting()); //for sort the passenger under locations in ascending order
		if(!locations.get(a).getCurr().get(k).getDriversLicense()) { //if passenger hasn't got car it prints passengers' card balance next to passenger ID number
		output.println("Passenger "+locations.get(a).getCurr().get(k).getID()+": "+(twoDecimal(locations.get(a).getCurr().get(k).getCardBalance())));}
		else { //if passenger has car it prints passengers' car's fuel amount next to the passenger ID number
			output.println("Passenger "+locations.get(a).getCurr().get(k).getID()+": "+twoDecimal(locations.get(a).getCurr().get(k).getFuelAmount()));
		}
		
		}
		
		}

		
		
		
		
		
	
		
		
	}
	/**
	 * Makes all double values two decimal 
	 * @param b double value
	 * @return double values with two decimal
	 */
	public static String twoDecimal(double b) { //in order to make all double values two decimal
		String number = Double.toString(b)+"00";
		return number.substring(0,number.indexOf(".")+3);
		
	}
}
/**
 * Defines a class that will help to sort the passengers in passengers' array list in ascending order
 * 
 *
 */
class Sorting implements Comparator<Passenger> 
{ 
   
    public int compare(Passenger a, Passenger b) 
    { 
        return a.getID() - b.getID(); 
    } 
} 


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

