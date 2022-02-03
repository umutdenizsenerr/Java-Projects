

//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package executable;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import boxes.*;
import elements.*;
/**
 * 
 * @author Umut Deniz Sener
 * Defines a main class that implements a basic messaging program
 */
public class Main{
	/**
	 * 
	 * @param args takes input file to test the code.
	 * @throws FileNotFoundException prevents the file not found exception error
	 */
public static void main (String[] args) throws FileNotFoundException {
	
	Scanner input = new Scanner(new File(args[0]));
	PrintStream output = new PrintStream(new File(args[1]));
	ArrayList<User> users =new ArrayList<User>();
	int numberOfUsers = input.nextInt();
	int numberOfQueries= input.nextInt();
	int capacityOfServer = input.nextInt();
	int time = 0;
	Server server = new Server(capacityOfServer);
	Server.a = true;
	Server.b = true;
	Server.c = true;
	for(int i = 0 ; i<numberOfUsers; i++) {
		User user = new User(i);
		
		users.add(user);
	}
	int serverOccRate = (int) (server.getCurrentSize()*100/capacityOfServer);

	int operationNum = input.nextInt();
	while(numberOfQueries >0) {
		
		
		while(operationNum==0) { //for sending a message
			Stack <Message> serverStack= new Stack<Message>();
			serverStack.addAll(server.getMsgs());
		
			int senderID=input.nextInt();
			int receiverID=input.nextInt();
			String messageBody=input.nextLine().substring(1);
			Message m = new Message(users.get(senderID),users.get(receiverID),messageBody,server,time);
			users.get(senderID).getOutbox().getSent().add(m);
			m.setID1(Message.numOfMessages);
		if(server.getCurrentSize()+messageBody.length()<capacityOfServer) {
		users.get(senderID).sendMessage(users.get(receiverID), messageBody, time, server);



serverOccRate = (int) (server.getCurrentSize()*100/capacityOfServer);

		
		}
		else {
			output.println("Server is full. Deleting all messages...");
			server.flush();
			
		}
		server.checkServerLoad(output);

		time++;
		numberOfQueries--;
		if(input.hasNext()) {
		 operationNum = input.nextInt();}
		break;
		}
		while (operationNum==1) { // recieves messages

			int receiverID=input.nextInt();
			users.get(receiverID).getInbox().receiveMessages(server, time);
	server.checkServerLoad(output);
			numberOfQueries--;
		time++;
		if(input.hasNext()) {
		 operationNum = input.nextInt();}
		break;
			
		}
		while (operationNum==2) { //reads a certain amount of messages
			
			int receiverID=input.nextInt();
			int numOfMsgs= input.nextInt();

			Inbox inbox = users.get(receiverID).getInbox();
			
			time+=inbox.readMessages(numOfMsgs, time);
			
			numberOfQueries--;
			if(input.hasNext()) {
			 operationNum = input.nextInt();}
			break;
		}
		while(operationNum==21) { //reads all the messages from a sender
			
			int receiverID=input.nextInt();
			int senderID=input.nextInt();
		
			Inbox inbox = users.get(receiverID).getInbox();
		time+=	inbox.readMessages(users.get(senderID), time);
			
			
			numberOfQueries--;
			
			
			if(input.hasNext()) {
			 operationNum = input.nextInt();}
			break;
}
		while(operationNum==22) { //reads a specific message
			
			int receiverID=input.nextInt();
			int msgID=input.nextInt();
			Inbox inbox =  users.get(receiverID).getInbox();
			inbox.readMessage(msgID, time);
			time++;
			numberOfQueries--;
			if(input.hasNext()) {
			 operationNum = input.nextInt();}
			break;
}
		while (operationNum==3) {//adds a friend
			
			int user1ID = input.nextInt();
			int user2ID = input.nextInt();

			if(!users.get(user1ID).isFriendsWith(users.get(user2ID))) {
				users.get(user1ID).addFriend(users.get(user2ID));
				
			}
			time++;
			numberOfQueries--;
			if(input.hasNext()) {
			 operationNum = input.nextInt();}
			
			break;
}
		while(operationNum==4) { //removes a friend
		
			int user1ID = input.nextInt();
			int user2ID = input.nextInt();
			if(users.get(user1ID).isFriendsWith(users.get(user2ID))) {
				users.get(user1ID).removeFriend(users.get(user2ID));
			
			}
			time++;
			numberOfQueries--;
			if(input.hasNext()) {
			 operationNum = input.nextInt();}
			
			break;
}
		while (operationNum==5) { //flushes server
		
			server.flush();
			time++;
			 numberOfQueries--;
				
				if(input.hasNext()) {
				 operationNum = input.nextInt();}
				break;
}
		while (operationNum==6) { //prints the current size of the server
			
	output.println("Current load of the server is "+server.getCurrentSize()
	+" characters.");
	time++;
	numberOfQueries--;

	if(input.hasNext()) {
	 operationNum = input.nextInt();}
	break;
	

}
		while (operationNum==61) { //prints the last message a user has read
	

			int userID = input.nextInt();
			
			if(!users.get(userID).getInbox().getstackRead().isEmpty()) {
				
				
	output.println("\tFrom: "+users.get(userID).getInbox().getstackRead().peek().getSender().getID()+" To: "+userID+"\n\tReceived: "+ users.get(userID).getInbox().getstackRead().peek().getTimeStampReceived()+" Read: "+ users.get(userID).getInbox().getstackRead().peek().getTimeStampRead()+ "\n\t"+users.get(userID).getInbox().getstackRead().peek().getBody());
	}
			time++;
	numberOfQueries--;
	if(input.hasNext()) {
	 operationNum = input.nextInt();}
	break;
	
}
		
	}
	
}
}
//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

