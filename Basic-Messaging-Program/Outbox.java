
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package boxes;

import java.util.*;

import elements.*;
/**
 * 
 * Defines a class that stores user's sent messages
 *
 */
public class Outbox extends Box{
	/**
	 * A queue for user's sent messages
	 */
	private Queue<Message> sent = new LinkedList<Message>();
	/**
	 * User
	 */
	User user;
	/**
	 * Constructor of Outbox class
	 * @param owner User
	 */
	public Outbox(User owner){
	this.sent=sent;
	this.user=owner;
		
	}
	/**
	 * To get queue for user's sent messages
	 * @return queue for user's sent messages
	 */
	public Queue getSent() {
		return sent;
	}
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

