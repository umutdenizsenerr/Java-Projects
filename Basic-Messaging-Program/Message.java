
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package elements;

import boxes.Inbox;
/**
 * 
 * Defines a class that defines messages
 *
 */
public class Message{
	/**
	 * number of total messages in the program
	 */
	public static int numOfMessages = 0; //number of total messages in the program.
	/**
	 * Message id
	 */
	private int id ;
	/**
	 * Message body
	 */
	private String body ;
	/**
	 * Sender of message
	 */
	private User sender ;
	/**
	 * Receiver of message
	 */
	private User receiver ;
	/**
	 * Time for stamping message as sent
	 */
	private int timeStampSent ;
	/**
	 * Time for stamping message as read
	 */
	private int timeStampRead ;
	/**
	 * Time for stamping message as received
	 */
	private int timeStampReceived ;
	/**
	 * Constructor of message class
	 * @param sender Sender of message
	 * @param receiver Receiver of message
	 * @param body Message body
	 * @param server Server of the program
	 * @param time Time
	 */
	public Message(User sender , User receiver , String body , Server server , int time) {
		this.sender=sender;
		this.receiver=receiver;
		this.body= body;
		this.id=id;
		
	}
	/**
	 * To set message id
	 * @param a message id
	 */
	public void setID(int a) {
		this.id=a;
		numOfMessages++;
	}
	/**
	 * to set message id for sent messages
	 * @param a message id
	 */ 
	public void setID1(int a) {
		this.id=a;
	
	}
	/**
	 * To set time stamp for sent messages
	 * @param a Time stamp for sent messages
	 */
	public void setTimeStampSent(int a){
		
		this.timeStampSent=a;
		
	}
	/**
	 * To set time stamp for read messages
	 * @param b Time stamp for read messages
	 */
	public void setTimeStampRead(int b){
		this.timeStampRead=b;
	}
	/**
	 * To set time stamp for received messages
	 * @param c Time stamp for received messages
	 */
	public void setTimeStampReceived(int c){
	this.timeStampReceived=c;
	}
	/**
	 * To get time stamp for sent messages
	 * @return time stamp for sent messages
	 */
	public int getTimeStampSent(){
		
	return this.timeStampSent;
		
	}
	/**
	 * To get time stamp for read messages
	 * @return time stamp for read messages
	 */
	public int getTimeStampRead(){
		return this.timeStampRead;
	}
	/**
	 * To get time stamp for received messages
	 * @return time stamp for received messages
	 */
	public int getTimeStampReceived(){
	return this.timeStampReceived;
	}
	/**
	 * To get message id
	 * @return message id
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * To get message body
	 * @return message body
	 */
	public String getBody() {
		
		return this.body;
	}
	/**
	 * For comparing messages according to their message body length
	 * @param o Message
	 * @return 
	 */
	public int compareTo(Message o) {
		if(this.body.length()>o.body.length()) {
			return +1;
		}
		else if(this.body.length()<o.body.length()) {
			return -1;
		}
		else {
			return 0;
		}
	}
	/**
	 * If message id's are equal messages are same
	 * @param o Message
	 * 
	 */
	public boolean equals(Object o) {
		if(o instanceof Message) {
			return ((Message) o).id==this.id;
		}
		else {
			return false;
		}
	}
	/**
	 * To return messages in specific string form
	 * @return messages in specific string form
	 */
	public String toString() {
		Inbox inbox =receiver.getInbox();
		String a="Message id : "+this.id+ "\t"+"From: <"+ sender.getID()+">"+" To: <"+receiver.getID()+">\n Received: ";
		if(inbox.getUnread().contains(this)||inbox.getRead().contains(this)) {
			a+=this.timeStampReceived+" ";
		}
		a+=" Read: " ;
		 if(inbox.getRead().contains(this)) {
			a+=this.timeStampRead+" ";
		}
		a+=this.body+"\n";
		
		
		return a;
	}
	/**
	 * To get receiver of message
	 * @return Receiver of message
	 */
	public User getReceiver() {
		return this.receiver;
	}
	/**
	 * To get sender of message
	 * @return Sender of message
	 */
	public User getSender() {
		return this.sender;
	}

	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

