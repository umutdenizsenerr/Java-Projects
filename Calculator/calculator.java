package work;

import java.util.Scanner;

public class UDS2018400225 {
public static void main(String[] args) {
		
		Scanner console = new Scanner(System.in); //to create a scanner
		String x = console.next(); // to get input from user
		String y = console.next();	// to get input from user
		String z = console.next();	// to get input from user
		console.nextLine();  //to fix scanner's curser problem
		String equation = console.nextLine(); // to get input from user
		String result = ""; // to initialize an empty string
		equation=equation.replace(" ",""); // to delete spaces
		equation = equation.replace(";", "");//to delete semicolon
		String empty ="";	// to initialize an empty string
		String empty2="";	// to initialize an empty string
		while(equation.contains("-")||equation.contains("+")||equation.contains("*")||equation.contains("/")) {// to do all operations
		if(equation.contains("(")) { // to do operations if it contains parantheses
		empty+=equation.substring(equation.lastIndexOf("("), equation.lastIndexOf(")")+1); //to take substring of last starting paranthese to last closing paranthese
		empty = empty.substring(empty.lastIndexOf("("), empty.indexOf(")")+1);			//to take substring of innermost paranthese 
		empty2 = empty.substring(empty.lastIndexOf("(")+1, empty.indexOf(")"));  		//to take the substring of innermost paranthese without parantheses
		String a =	Integer.toString(additionSubstraction(multiplicationDivision(empty2))); //to do operations in the innermost parantheses
	    equation = equation.replace(empty, a);// and import the result of innermost paranthese in the equation
	    empty="";//to discharge string
	    empty2="";//to discharge string
		result=equation; // to take the result of equation
		}
		else { // to do operations if it do not contain parantheses
			result =Integer.toString(additionSubstraction(multiplicationDivision(equation)));// to take the result of equation
			
			break; // to end the loop
		}
		
		
		
		}
		System.out.println(result); // final result :)
	}
	
	
public static String multiplicationDivision(String islem) {
	
		
	String empty = "";	// to initialize empty string
	String empty2 = "";	// to initialize empty string
	
	int a= 0 ; 	// to initialize an integer
	int b = 0;	// to initialize an integer
	int j = 0;	// to initialize an integer

	int m = 0;	// to initialize an integer
	int k=0;	// to initialize an integer
			while(islem.contains("*")||islem.contains("/")) {   //for multiplications and divisions
				
				
				if(islem.indexOf('*')<islem.indexOf('/')&&islem.indexOf('*')!=-1||islem.indexOf('/')==-1) { // to do multiplication first if it is before the division and exists 
					
					empty+=islem.substring(0,islem.indexOf('*')); // to take string till multiplication sign
				
					k = Math.max(empty.lastIndexOf('+'), empty.lastIndexOf('-')); //to determine which operation last and take the first operation's index
						
						if(k==-1) {   // if addition and substraction signs do not exist k should be 0 because we should take from start point if there is no addition and substraction
														
							k=0;   
						}
						
							if(k!=0) { // if addition or substraction exist 
							 a = Integer.parseInt(empty.substring(k+1)); // to take the number from last addition or substraction sign to multiplication sign
							 m = islem.indexOf('*')-k;//to determine number of digits
							 } 
							else {
								 a = Integer.parseInt(empty.substring(k)); // to take the number before multiplication sign
								 m = islem.indexOf('*')-k+1; //to determine number of digits
							}
							
					empty2 = islem.substring(islem.indexOf('*')+1);		// to take string after the multiplication sign	
					
					if(empty2.contains("-")&&empty2.contains("+")&&empty2.contains("*")) {			// all possibilities after multiplication sign
						j=Math.min(empty2.indexOf('*'), Math.min(empty2.indexOf('+'),empty2.indexOf('-')));;} //to determine which operation first
						else if(empty2.contains("-")&&empty2.contains("+")) {		// all possibilities after multiplication sign
						j=Math.min(empty2.indexOf('-'),(empty2.indexOf('+')));}			//to determine which operation first
						else if(empty2.contains("-")&&empty2.contains("*")) {			// all possibilities after multiplication sign
							j=Math.min(empty2.indexOf('-'),(empty2.indexOf('*')));}			//to determine which operation first
						else if(empty2.contains("*")&&empty2.contains("+")) {	// all possibilities after multiplication sign
							j=Math.min(empty2.indexOf('*'),(empty2.indexOf('+')));}		//to determine which operation first
						else if(empty2.contains("*")&&!empty2.contains("+")&&!empty2.contains("-")) {		// all possibilities after multiplication sign								
							j=empty2.indexOf("*");		//to determine which operation first
						}
						else if(empty2.contains("-")&&!empty2.contains("+")&&!empty2.contains("*")) {	// all possibilities after multiplication sign
							j=empty2.indexOf("-");		//to determine which operation first
						}
						else if(empty2.contains("+")&&!empty2.contains("*")&&!empty2.contains("-")) {	// all possibilities after multiplication sign
							j=empty2.indexOf("+");	//to determine which operation first
						}
						else if(empty2.contains("/")){		// all possibilities after multiplication sign
							j=empty2.indexOf("/");		//to determine which operation first
						}
						else {							// all possibilities after multiplication sign
							j=empty2.length();		// if there is no operation, j should be length of empty2
						}
					
					b=Integer.parseInt(empty2.substring(0,j)); // to take the number after multiplication sign
					
				islem = empty.substring(0,empty.length()-m+1)+(a*b)+empty2.substring(j,empty2.length()); // to do multiplication operation and import the result in the equation
						empty=""; // to discharge the string
						empty2=""; // to discharge the string
						
					}
				else if(islem.indexOf('*')>islem.indexOf('/')&&islem.indexOf('/')!=-1||islem.indexOf('*')==-1) {// to do division first if it is before the multiplication and exists 
					
					empty+=islem.substring(0,islem.indexOf('/')); // to take string till multiplication sign
					k = Math.max(empty.lastIndexOf('+'), empty.lastIndexOf('-')); //to determine which operation last and take the first operation's index
					
					if(k==-1) {// if addition and substraction signs do not exist k should be 0 because we should take from start point if there is no addition and substraction
						
						
						k=0;
					}
					if(k!=0) { // if addition or substraction exist 
						 a = Integer.parseInt(empty.substring(k+1)); // to take the number from last addition or substraction sign to multiplication sign
						 m = islem.indexOf('/')-k;//to determine number of digits
						 } 
						else {
							 a = Integer.parseInt(empty.substring(k)); // to take the number before multiplication sign
							 m = islem.indexOf('/')-k+1; //to determine number of digits
						}
					empty2 = islem.substring(islem.indexOf('/')+1); // to take string after the division sign	
				
					
				if(empty2.contains("-")&&empty2.contains("+")&&empty2.contains("*")) { // all possibilities after division sign
				j=Math.min(empty2.indexOf('*'), Math.min(empty2.indexOf('+'),empty2.indexOf('-')));;}//to determine which operation first
				else if(empty2.contains("-")&&empty2.contains("+")) { // all possibilities after division sign
				j=Math.min(empty2.indexOf('-'),(empty2.indexOf('+')));}//to determine which operation first
				else if(empty2.contains("-")&&empty2.contains("*")) { // all possibilities after division sign
					j=Math.min(empty2.indexOf('-'),(empty2.indexOf('*')));}//to determine which operation first
				else if(empty2.contains("*")&&empty2.contains("+")) { // all possibilities after division sign
					j=Math.min(empty2.indexOf('*'),(empty2.indexOf('+')));}//to determine which operation first
				else if(empty2.contains("*")&&!empty2.contains("+")&&!empty2.contains("-")) { // all possibilities after division sign
					j=empty2.indexOf("*");//to determine which operation first
				}
				else if(empty2.contains("-")&&!empty2.contains("+")&&!empty2.contains("*")) { // all possibilities after division sign
					j=empty2.indexOf("-");//to determine which operation first
				}
				else if(empty2.contains("+")&&!empty2.contains("*")&&!empty2.contains("-")) { // all possibilities after division sign
					j=empty2.indexOf("+");//to determine which operation first
				}
				else if(empty2.contains("*")){ // all possibilities after division sign
					j=empty2.indexOf("*");//to determine which operation first
				}
				else {// all possibilities after division sign
					j=empty2.length();// if there is no operation, j should be length of empty2
				}
				
					
				
					b=Integer.parseInt(empty2.substring(0,j)); // to take the number after division sign
			
					 
				islem = empty.substring(0,empty.length()-m+1)+(a/b)+empty2.substring(j,empty2.length());// to do division operation and import the result in the equation
				
				
						empty="";// to discharge the string
						empty2="";// to discharge the string
						
					
				}
					
					
			
			
			}
					
			return islem; //to return the result
				}
public static int additionSubstraction(String islem) { 
	
	 
	String empty = "";	// to initialize empty string
	String empty2 = "";	// to initialize empty string
	
	int a= 0 ; 	// to initialize an integer
	int b = 0;	// to initialize an integer
	int j = 0;	// to initialize an integer

	while(islem.contains("-")||islem.contains("+")) { //for addition and substraction

		
		if(islem.indexOf('+')<islem.indexOf('-')&&islem.indexOf('+')!=-1||islem.indexOf('-')==-1) { // to do addition first if it is before the substraction and exists 
			
			empty+=islem.substring(0,islem.indexOf('+')); // to take string till addition sign
			
			a=Integer.parseInt(empty); // to take the number before addition sign
			empty2=islem.substring(islem.indexOf('+')+1); // to take substring after addition sign
			
			if(empty2.contains("+")&&empty2.contains("-")) { // all possibilities after addition sign
				
				j=Math.min(empty2.indexOf('-'),(empty2.indexOf('+'))); //to determine which operation first
				
			}
			else if(empty2.contains("+")&&!empty2.contains("-")) {// all possibilities after addition sign
				j=empty2.indexOf('+'); //to determine which operation first
			}
			else if(empty2.contains("-")&&!empty2.contains("+")) {// all possibilities after addition sign
				j=empty2.indexOf('-');  //to determine which operation first
			} 
			else {// all possibilities after addition sign
				j=empty2.length(); // if there is no operation, j should be length of empty2
			}
				
				b=Integer.parseInt(empty2.substring(0,j)); // to take the number after addition sign
				islem =(a+b)+empty2.substring(j,empty2.length()); // to do addition operation and import the result in the equation
				empty=""; //to discharge string
				empty2="";	//to discharge string
		}
		else if(islem.indexOf('-')<islem.indexOf('+')&&islem.indexOf('-')!=-1||islem.indexOf('+')==-1) {// to do substraction first if it is before the addition and exists 
			
			empty+=islem.substring(0,islem.indexOf('-'));// to take string till substraction sign
			
			a=Integer.parseInt(empty); // to take the number before substraction sign
			empty2=islem.substring(islem.indexOf('-')+1); // to take substring after substraction sign
			if(empty2.contains("-")&&empty2.contains("+")) { // all possibilities after substraction sign
				
				j=Math.min(empty2.indexOf('-'),(empty2.indexOf('+')));//to determine which operation first
				
			}
			else if(empty2.contains("+")&&!empty2.contains("-")) {// all possibilities after substraction sign
				j=empty2.indexOf('+');//to determine which operation first
			}
			else if(empty2.contains("-")&&!empty2.contains("+")) {// all possibilities after substraction sign
				j=empty2.indexOf('-');//to determine which operation first
			}
			else {// all possibilities after substraction sign
				j=empty2.length();// if there is no operation, j should be length of empty2
			}
				b=Integer.parseInt(empty2.substring(0,j));// to take the number after substraction sign
				islem =(a-b)+empty2.substring(j,empty2.length());// to do substraction operation and import the result in the equation
				empty="";//to discharge string
				empty2="";//to discharge string
			
			
			
		}
		
	
		
	}
		return Integer.parseInt(islem);
	
}
}
