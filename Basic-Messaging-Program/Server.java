
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package elements;

import java.io.*;
import java.util.*;
/**
 * 
 * Defines a class where all non-received messages are stored
 *
 */
public class Server{
	/**
	 * Server capacity
	 */
	private long capacity;
	/**
	 * Server's current size
	 */
	private long currentSize;
	/**
	 * Queue for storing messages
	 */
	public Queue<Message> msgs=new LinkedList<Message>();
	/**
	 * A boolean to arrange warning messages
	 */
	 public static boolean b ;
	 	/**
		 * A boolean to arrange warning messages
		 */
	 public static boolean c ;
	 	/**
		 * A boolean to arrange warning messages
		 */
	 public static boolean a ;
	 /**
	  * Server occupancy rate
	  */
	 public static int serverRate;
	 /**
	  * Constructor for server class
	  * @param capacity Server capacity
	  */
	public Server(long capacity) {
		this.capacity=capacity;
	}
	/**
	 * To print the warnings about the capacity
	 * @param printer To print messages in to output.txt
	 */
	public void checkServerLoad(PrintStream printer) {
		serverRate = (int) (this.currentSize*100/this.capacity);
	
		if(serverRate>=80&&b) {
			
	printer.println("Warning! Server is 80% full.");
	b=false;
	a=false;
		}
		else if(serverRate>=50&&a) {
		printer.println("Warning! Server is 50% full.");
		a=false;
		c=false;
		}
	}
	/**
	 * To get Current size
	 * @return Current size
	 */
	public long getCurrentSize() {
	int size = 0;
	for(Message m : msgs) {
		size+=m.getBody().length();
	}
	currentSize= size;
		return this.currentSize;
	}
	/**
	 * To get server capacity
	 * @return server capacity
	 */
	public long getCapacity() {
		return this.capacity;
	}
	/**
	 * To delete all messages in server
	 */
	public void flush() {
		this.msgs.clear();
		b=true;
		a=true;
		this.currentSize=0;
		serverRate = (int) (this.currentSize*100/this.capacity);
	}
	/**
	 * To get the queue for storing messages
	 * @return queue for storing messages
	 */
	public Queue<Message> getMsgs() {
		return this.msgs;
	}
	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

