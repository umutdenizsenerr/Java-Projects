
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package locations;

import java.util.ArrayList;


import passengers.*;
/**
 * Defines a class that defines locations' coordinates and other properties
 */
public class Location{
	
	/**
	 * Location ID
	 */
	private int ID= -1 ;
	/**
	 * Location's X Value
	 */
	private double locationX;
	/**
	 * Location's Y Value
	 */
	private double locationY;
	/**
	 * Array List that contains passengers who has visited the location.
	 */
	private ArrayList<Passenger> history=new ArrayList<Passenger>();
	/**
	 * Array list that contains passengers who are currently in the location.
	 */
	private ArrayList<Passenger> current=new ArrayList<Passenger>();
	/**
	 * Defines a constructor for locations
	 * 
	 * @param ID Location ID
	 * @param locationX Location's X Value
	 * @param locationY Location's Y Value
	 */
	
	public Location(int ID,double locationX,double locationY) {
		this.ID=ID;
		this.locationX=locationX;
		this.locationY=locationY;
		
		
	}
	/**
	 * Gets distance one location to another location
	 * @param other Location that is reached
	 * @return distance between one location to another location
	 */
	public double getDistance(Location other) {
		double k = Math.abs(other.locationX-locationX);
		double j = Math.abs(other.locationY-locationY);
		return Math.sqrt(k*k+j*j);
		
	}
	/**
	 * Adds passenger who reach the location to history array list and current array list of that location
	 * @param p Passenger who reach the location
	 */
	public void incomingPassenger(Passenger p) {
		if(!history.contains(p)) {
		history.add(p);}
		current.add(p);
	}
	/**
	 * Removes passenger  who leave the location from current array list
	 * @param p Passenger who leave the location
	 */
	public void outgoingPassenger(Passenger p) {
		
		current.remove(p);
	}
	/**
	 * Increases Location ID
	 * 
	 */
	public void increaseID() {
		ID++;
	}
	/**
	 * Gets Location ID
	 * @return Location ID
	 */
	public int getID() {
		return ID;
	}
	/**
	 * Gets Location's X Value
	 * @return Location's X Value
	 */
	public double getlocationX() {
		return locationX;
	}
	/**
	 * Gets Location's Y Value
	 * @return Location's Y Value
	 */
	public double getlocationY() {
		return locationY;
	}
	/**
	 * Sets Location's X value
	 * @param a Location's new X value
	 */
	public void setlocationX(double a) {
		this.locationX=a;
	}
	/**
	 * Sets Location's Y value
	 * @param b Location's new Y value
	 */
	public void setlocationY(double b) {
		this.locationY=b;
	}
	/**
	 * Gets history array list of location
	 * @return history array list of location
	 */
	public ArrayList<Passenger> getHist() {
		return history;
	}
	/**
	 * Gets current array list of location
	 * @return current array list of location
	 */
	public ArrayList<Passenger> getCurr(){
		return current;
	}
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

