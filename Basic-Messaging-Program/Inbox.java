
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package boxes;

import java.util.*;

import elements.*;
/**
 * 
 * Defines a class that can used for receiving messages from the server, and reading messages.
 *
 */
public class Inbox extends Box{
	/**
	 * a stack for unread messages
	 */
	private Stack<Message> unread=new Stack<Message>();
	/**
	 * a Queue for read messages
	 */
	private Queue <Message >read=new LinkedList<Message>();
	/**
	 * a stack for read messages
	 */
	Stack<Message> stackRead = new Stack <Message>();
	/**
	 * Owner of inbox
	 */
	User user;
	/**
	 * Constructor of inbox class
	 * @param user Owner of inbox
	 */
	public Inbox(User user) {
		this.unread=unread;
		this.read=read;
		this.user=user;
	}
	/**
	 * receives messages from the server,
     * adds to the unread stack.
	 * @param server Server of the system
	 * @param time Time for changing receive time stamp 
	 */
	public void receiveMessages(Server server , int time ) {
		Queue <Message >copyServer=new LinkedList<Message>();
		
		copyServer.addAll(server.getMsgs());
		
		for(Message m :copyServer) {
			if(m.getReceiver()==this.user&&m.getSender().isFriendsWith(user)) {
				
					if(!unread.contains(m)) {
						
				unread.push(m);
				
				server.getMsgs().remove(m);
					}
				
				m.setTimeStampReceived(time);
				
				
			}
		

		}
		
		if((int) (server.getCurrentSize()*100/server.getCapacity())<50) {
			Server.c=true;
		}
	if((int) (server.getCurrentSize()*100/server.getCapacity())<80&&(server.b==false||server.c==true)) {
		Server.a=true;
		Server.b=true;
		Server.c=false;
	}

	
	
	
	

	}
	/**
	 * for reading a certain
	amount of messages from the unread stack
	 * @param num Amount of messages
	 * @param time Time for stamping read time
	 * @return Amount of messages that are read
	 */
	public int readMessages( int num , int time ) {
	
		if(num >unread.size()) {
			num=unread.size();
			}
		
		if(unread.isEmpty()) {
			time++;
			return 1;
			
		}
		if(num==0) {
			int size  = unread.size();
			Stack<Message> copyUnread = new Stack<Message>();
			copyUnread.addAll(unread);
			Iterator<Message> itr = copyUnread.iterator();

			for(int i = 0 ; itr.hasNext();i++) {
				itr.next();
		
			read.add(unread.peek());
		
			unread.peek().setTimeStampRead(time);
		
			time++;

			unread.pop();
			}
			stackRead.removeAll(read);
			stackRead.addAll(read);
			
			return size;
		
		}
		else {
		for(int i = 0 ; i<num; i++) {
			
			unread.peek().setTimeStampRead(time);
			read.add(unread.peek());
			time ++;
			unread.pop();
	


		}
		stackRead.removeAll(read);
		stackRead.addAll(read);
		
		return num;
	
		}
	
		

			
		
	}
	/**
	 * for reading a specified sender’s messages.
	 * @param sender Specified sender
	 * @param time Time for stamping read time 
	 * @return Amount of messages that are read
	 */
	public int readMessages(User sender , int time ) {

	


		int k = 0 ;
		if(unread.isEmpty()) {
			time++;
			return 1;
		}
	
		Stack<Message> copyUnread = new Stack<Message>();
		Stack<Message> copyUnread2 = new Stack<Message>();
		copyUnread.addAll(unread);
		copyUnread2.addAll(unread);
		Iterator<Message> itr = copyUnread2.iterator();
	
	


		for(int i = 0 ; itr.hasNext();i++) {
			itr.next();
			if(copyUnread.peek().getSender()==sender) {
				
				read.add(copyUnread.peek());
			
				
				copyUnread.peek().setTimeStampRead(time);
				time++;
				k++;
				
				unread.remove(copyUnread.peek());
				
			}
			copyUnread.pop();
			
		}
		if(k==0) {
			k++;
			time++;
		}

		stackRead.removeAll(read);
		stackRead.addAll(read);
		
		return k ;
		
	}
	/**
	 * For reading specified message with the ID number
	 * @param msgId Message ID
	 * @param time Time for stamping read time 
	 */
	public void readMessage( int msgId , int time ) {
		Stack<Message> copyUnread = new Stack<Message>();
		copyUnread.addAll(unread);
		
		for(Message m : copyUnread) {
			
			if(m.getId()==msgId) {
				
				read.add(m);
				
				m.setTimeStampRead(time);
				time++;
	
				unread.remove(m);
			}
	}
		stackRead.removeAll(read);
		stackRead.addAll(read);
}
	/**
	 * to get unread stack
	 * @return unread stack
	 */
	public Stack<Message> getUnread(){
		return this.unread;
	}
	/**
	 * to get read queue
	 * @return read queue
	 */
	public Queue<Message> getRead(){
		return this.read;
	}
	/**
	 * to get read stack
	 * @return read stack
	 */
	public Stack<Message> getstackRead(){
		return this.stackRead;
	}


}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

