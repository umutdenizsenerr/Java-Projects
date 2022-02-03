
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package elements;

import java.util.ArrayList;

import boxes.*;
import executable.Main;
/**
 * Defines a class that defines users of the program
 * 
 *
 */
public class User{
	/**
	 * User ID
	 */
	private int id ;
	/**
	 * User's inbox
	 */
	private Inbox inbox  ;
	/**
	 * User's outbox
	 */
	private Outbox outbox ;
	/**
	 * Array List of users's friends
	 */
	private ArrayList<User> friends ;
	
	/**
	 * Constructor of user class
	 * @param id User ID
	 */
	public User(int id) {
		
		this.id=id;
		this.inbox=new Inbox(this);
		this.outbox=new Outbox(this);
		this.friends=new ArrayList<User>() ;
		}
	/**
	 * To add friend
	 * @param other new friend of user
	 */
	public void addFriend(User other) {
		this.friends.add(other);
		other.friends.add(this);
		
	}
	/**
	 * To remove friend
	 * @param other a user that is removed from the user's friends' list
	 */
	public void removeFriend(User other) {
		this.friends.remove(other);
		other.friends.remove(this);
		
	}
	/**
	 * To determine whether user and another user is friends
	 * @param other another user
	 * @return whether user and another user is friends
	 */
	public boolean isFriendsWith(User other) {
		return this.friends.contains(other);
		
	}
	/**
	 *  For sending message 
	 * @param receiver Receiver of message
	 * @param body Message Body
	 * @param time Sent Time
	 * @param server Server of the program
	 */
	public void sendMessage(User receiver , String body , int time , Server server) {
		
			
		    Message message = new Message(this,receiver,body,server,time);
		    		message.setID(Message.numOfMessages);
		    		message.setTimeStampSent(time);
			 		server.getMsgs().add(message);
	}
	/**
	 * To get user ID
	 * @return user ID
	 */
	public int getID() {
		return this.id;
	}
	/**
	 * To get user's friends' list
	 * @return user's friends' list
	 */
	public ArrayList<User> getFriends() {
		return this.friends;
	}
	/**
	 * To get user's Inbox
	 * @return user's Inbox
	 */
	public Inbox getInbox() {
	return this.inbox;
	}
	/**
	 * To get user's Outbox
	 * @return user's Outbox
	 */
	public Outbox getOutbox() {
		
		return this.outbox;
	}

}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

