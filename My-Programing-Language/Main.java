

import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class Main {
		 
	static int lineNumber = -1;
	static int tempCounter = 1;
	static int chooseCounter = 1;
	static int ifCounter = 1;
	static int whileCounter = 1;
	
	static LinkedHashMap<String, Integer> data = new LinkedHashMap<String, Integer>(); // insertion order
	
	static StringBuilder postFix = new StringBuilder(""); //to store postfix notaion at all methods
	
	static File outFile;
		
	public static void main(String args[]) throws IOException {
		
		String fName = args[0];
		
		File f = new File(fName);
		
		String outName = fName.substring(0, fName.lastIndexOf('.')) + ".ll";
		
		outFile = new File(outName);
				
		FileOutputStream fout = new FileOutputStream(outFile);
		
        System.setOut(new PrintStream(fout));
		
		System.out.println("declare i32 @printf(i8*, ...)\n" + 
				"@print.str = constant [4 x i8] c\"%d\\0A\\00\"\ndefine i32 @main() {");
		
				
		Scanner assign = new Scanner(f);
			
		while(assign.hasNextLine()) {  
			String s = assign.nextLine();
				
			if(s.contains("=")) {	
				
				String varName = s.substring(0, s.indexOf("="));
				varName = varName.replaceAll(" ", "");
				varName = varName.replaceAll("	", "");		
				if(!data.containsKey(varName)) {
					data.put(varName, 0);	
				}
			}
		}
		// end of assigning
			
		Set<String> variables = data.keySet();
		String[] arr = variables.toArray(new String[0]);
			
		// allocate memory
		for(int i = 0; i < arr.length; i++) { //for allocating variables before the code starts
			System.out.println("	%" + arr[i] + " = alloca i32");
		}
			
		System.out.println();
			
		// default value will be 0
		for(int i = 0; i < arr.length; i++) { //for assign variables to zero before the code starts
			System.out.println("	store i32 0, i32* %" + arr[i]);
		}
			
		System.out.println();
			
		assign.close();
			
		Scanner input = new Scanner(f);
			
		while(input.hasNextLine()) {
			String s = input.nextLine();
			s = s.replaceAll("	"," ");
			lineNumber++;
			
			if(s.contains("#")) { // erases comments
				s = s.substring(0, s.indexOf("#"));
			}
				
			if(s.contains("=")) { // assignment statement
				assigner(s);
			}
				
			else if(s.contains("while")) { // while-loop statement
				
				boolean completed = false;
				
				if(s.contains("(") && s.contains(")") && s.contains("{")) {
					s = s.replaceAll("	", " ");
					s = s.substring(0, s.indexOf("while")+5).replaceAll(" ", "")+ s.substring(s.indexOf("while")+5, s.indexOf("(",s.indexOf("while"))+1).replaceAll(" ", "")+ s.substring(s.indexOf("(")+1, s.lastIndexOf(")")) + s.substring(s.lastIndexOf(")")).replaceAll(" ", "");
					
					String start = s.substring(0, s.indexOf("(") + 1);
					String end = s.substring(s.lastIndexOf(")"));
					if(start.equals("while(") && end.equals("){")) {	
						
						String cond = s.substring(s.indexOf("(")+1, s.lastIndexOf(")"));	
						
						if(cond.matches("[0-9]+")){ // number >> infinite loop
							error();
						}else if(expr(cond)){ // expression
							System.out.println("	br label %whcond" + whileCounter);
							System.out.println("whcond" + whileCounter + ":");
							int x = tempCounter++;
							cond = evaluatePostfix(postFix.toString());
							System.out.println("	%t_"+ x + " = icmp ne i32 " + cond + ", 0");
							System.out.println("	br i1 %t_" + x + ", label %whbody"+ whileCounter +", label %whend" + whileCounter);
							
						}else {
							error();
						}
						
						System.out.println("whbody" + whileCounter + ":");
							
						while(input.hasNextLine()) {
							String body = input.nextLine();
							body = body.replaceAll("	"," ");
							lineNumber++;
							
							if(body.contains("#")) {
								body = body.substring(0, body.indexOf("#"));
							}
								
							if(body.contains("=")) {
								assigner(body);
							}
							else if(body.contains("print")){
								body = body.substring(0, body.indexOf("print")+5).replaceAll(" ", "")+ body.substring(body.indexOf("print")+5, body.indexOf("(",body.indexOf("print"))+1).replaceAll(" ", "")+ body.substring(body.indexOf("(")+1, body.lastIndexOf(")")) + body.substring(body.lastIndexOf(")")).replaceAll(" ", "");
								printer(body);
							}
							else if(body.contains("}")) {
								body = body.replaceAll(" ", "");
								if(body.equals("}")) {
									System.out.println("	br label %whcond" + whileCounter);
									System.out.println("whend" + whileCounter + ":\n");
									completed = true;
									break;
								}else {
									error();
								}
							}
							else {
								body = body.replaceAll(" ", "");
								if(body.equals("")) {
									
								}else {
									error();
								}
							}
						}		
					}
					else {
						error();
					}
				}
				else {
					error();
				}	
				
				if(!completed) {
					error();
				}
				
				whileCounter++;
			}
				
			else if(s.contains("if")) { // if-loop statement
				
				boolean completed = false;

				if(s.contains("(") && s.contains(")") && s.contains("{")) {
					s = s.replaceAll("	", " ");
					s = s.substring(0, s.indexOf("if")+2).replaceAll(" ", "")+ s.substring(s.indexOf("if")+2, s.indexOf("(",s.indexOf("if"))+1).replaceAll(" ", "")+ s.substring(s.indexOf("(")+1, s.lastIndexOf(")")) + s.substring(s.lastIndexOf(")")).replaceAll(" ", "");
					
					String start = s.substring(0, s.indexOf("(") + 1);
					String end = s.substring(s.lastIndexOf(")"));
					if(start.equals("if(") && end.equals("){")) {	
						String cond = s.substring(s.indexOf("(")+1, s.lastIndexOf(")"));
						
						if(expr(cond)){ // expression?
							System.out.println("	br label %ifcond" + ifCounter);
							System.out.println("ifcond" + ifCounter + ":");
							int x = tempCounter++;
							cond = evaluatePostfix(postFix.toString());
							System.out.println("	%t_"+ x + " = icmp ne i32 " + cond + ", 0");
							System.out.println("	br i1 %t_" + x + ", label %ifbody" + ifCounter + ", label %ifend" + ifCounter);
							
						}else {
							error();
						}
							
						System.out.println("ifbody" + ifCounter + ":");
						while(input.hasNextLine()) {
							String body = input.nextLine();
							body = body.replaceAll("	"," ");
							lineNumber++;
							
							if(body.contains("#")) {
								body = body.substring(0, body.indexOf("#"));
							}
							if(body.contains("=")) {
								assigner(body);
							}
							else if(body.contains("print")){
								body = body.substring(0, body.indexOf("print")+5).replaceAll(" ", "")+ body.substring(body.indexOf("print")+5, body.indexOf("(",body.indexOf("print"))+1).replaceAll(" ", "")+ body.substring(body.indexOf("(")+1, body.lastIndexOf(")")) + body.substring(body.lastIndexOf(")")).replaceAll(" ", "");
								printer(body);
							}
							else if(body.contains("}")) {
								body = body.replaceAll(" ", "");
								if(body.equals("}")) {
									System.out.println("	br label %ifend" + ifCounter);
									System.out.println("ifend" + ifCounter + ":\n");
									completed = true;
									break;
								}else {
									error();
								}
							}
							else {
								body = body.replaceAll(" ", "");
								if(body.equals("")) {
									
								}else {
									error();
								}
							}			
						}		
					}
					else {
						error();
					}
				}
				else {
					error();
				}
				
				if(!completed) {
					error();
				}
				ifCounter++;
			}
			else if(s.contains("print")) { // print statement
				s = s.substring(0, s.indexOf("print")+5).replaceAll(" ", "")+ s.substring(s.indexOf("print")+5, s.indexOf("(",s.indexOf("print"))+1).replaceAll(" ", "")+ s.substring(s.indexOf("(")+1, s.lastIndexOf(")")) + s.substring(s.lastIndexOf(")")).replaceAll(" ", "");
				printer(s);
			}			
			else{
				s = s.replaceAll(" ","");
				s = s.replaceAll("	","");
				if(s.equals("")) { // empty line
					
				}else {
					error();
				}
			}
		}
		
		System.out.println("ret i32 0\n}");
		
	}
	
	static String choose(String str) throws FileNotFoundException { // replace all choose function in format choose(a,b,c,d) with the required value
		String left,right, inner, expr1,expr2, expr3, expr4;
		left = right = inner = expr1 = expr2 = expr3 = expr4 = "";
		String temp = str;
	
		while(str.contains("choose(")){
			while(temp.contains("choose(")) {
			
				expr1 = expr2 = expr3 = expr4 = "";
				left = temp.substring(0, temp.indexOf("choose("));
				inner = temp.substring(temp.indexOf("choose("));
					
				int idx = inner.indexOf(")");
				while(!(notBetweenParanthesis(inner, idx) && inner.charAt(idx) == ')')) {
					idx++;
				}
				
				right = inner.substring(idx+1);
				inner = inner.substring(0,idx+1);
				inner = inner.substring(7, inner.length()-1);
			
				temp = inner;
				
			}
			
			if(inner.contains(",")) {
				expr1 = inner.substring(0, inner.indexOf(","));
			}
			else {	
				error();
			}
			inner = inner.substring(inner.indexOf(",") + 1);
			if(inner.contains(",")) {
					expr2 = inner.substring(0, inner.indexOf(","));
			}
			else {
				error();
			}
			inner = inner.substring(inner.indexOf(",") + 1);
			if(inner.contains(",")) {
				expr3 = inner.substring(0, inner.indexOf(","));
			}
			else {
				error();
			}
			inner = inner.substring(inner.indexOf(",") + 1);
			expr4 = inner;
		
			String wholeExpression = expr1+","+expr2+","+expr3+","+expr4;
			
			int leftIndex = str.indexOf(wholeExpression)-7;
			String leftSide = str.substring(0,leftIndex);
			int rightIndex = leftIndex+wholeExpression.length()+8;
			String rightSide = str.substring(rightIndex);
			
			if(check(expr1)) {
				expr1 = expr1.replaceAll(" ","");
				expr1 = expr1.replaceAll("	","");
			}else {
				error();
			}
			if(check(expr2)) {
				expr2 = expr2.replaceAll(" ","");
				expr2 = expr2.replaceAll("	","");
			}else {
				error();
			}
			if(check(expr3)) {
				expr3 = expr3.replaceAll(" ","");
				expr3 = expr3.replaceAll("	","");
			}else {
				error();
			}
			if(check(expr4)) {
				expr4 = expr4.replaceAll(" ","");
				expr4 = expr4.replaceAll("	","");
			}else {
				error();
			}
			

			String val1 = "";
			String val2="";
			String val3 ="";
			String val4="";
			
			if(expr(expr1)) {
				val1 = evaluatePostfix(postFix.toString());
				expr(expr2);
				val2 = evaluatePostfix(postFix.toString());
				expr(expr3);
				val3 = evaluatePostfix(postFix.toString());
				expr(expr4);
				val4 = evaluatePostfix(postFix.toString());
				
				System.out.println("	br label %chCond" + chooseCounter);
				System.out.println("chCond" + chooseCounter + ":");
				if(Character.isAlphabetic(val1.charAt(0)) && data.containsKey(val1)) {
                	System.out.println("	%t_" + tempCounter + " = load i32* %" + val1);
                	val1 = "%t_" + tempCounter++;
                }else if(Character.isAlphabetic(val1.charAt(0))){
                	val1 = "%" + val1;
                }
				
				System.out.println("	%udsley" + chooseCounter + " = alloca i32");
				String var = "udsley" + chooseCounter;
				data.put(var,0);
				System.out.println("	store i32 0, i32* %udsley" + chooseCounter);
				System.out.println("	%t_"+tempCounter +" = icmp sgt i32 "+val1+", 0");
				
				System.out.println("	br i1 %t_"+tempCounter++ +", label %cTrue" + chooseCounter+ ", label %cFalse" + chooseCounter);
				
				System.out.println("cTrue" + chooseCounter + ":");	
				System.out.println("	store i32 " + val3 + ", i32* %udsley" + chooseCounter);
				System.out.println("	br label %cEnd" + chooseCounter);
				
				System.out.println("cFalse" + chooseCounter + ":");
				
				System.out.println("	%t_"+tempCounter +" = icmp eq i32 "+val1+", 0");	
				System.out.println("	br i1 %t_"+tempCounter++ +", label %chTrue" + chooseCounter + ", label %chFalse" + chooseCounter);
				
				System.out.println("chTrue" + chooseCounter + ":");
				System.out.println("	store i32 " + val2 + ", i32* %udsley" + chooseCounter);
				System.out.println("	br label %cEnd" + chooseCounter);
				
				System.out.println("chFalse" + chooseCounter + ":");
				System.out.println("	store i32 " + val4 + ", i32* %udsley" + chooseCounter);
				System.out.println("	br label %cEnd" + chooseCounter);
				
				System.out.println("cEnd" + chooseCounter + ":\n");
							
			}
			
			temp = leftSide + "udsley" + chooseCounter++ + rightSide;
			str = temp;
			
		}
		return str;
	}
		
	static boolean expr(String str) throws FileNotFoundException { 
		
		if(str.contains("choose(")) { //giving highest precidence to choose function 

			str = choose(str);
		}

		if(str=="") {
			
			return true;
		}
		
		if(check(str)) {
			str = str.replaceAll(" ", "");
			str = str.replaceAll("	", ""); 
		}else {
			error();
		}
			
		String str1, str2; 
		if(str.charAt(0)=='+'||str.charAt(0)=='-') {
			str = 0+ str;
		}
		String[] arr = tokenizer1(str);
		str1 = arr[0];
		str2 = arr[1];
		
		
		
		if(!term(str1)) {
			return false;
		}
		if(!moreterms(str2)) {
			return false;
		}

		return true;
	}
		
	static boolean term(String str) throws FileNotFoundException {
			
		String str1, str2; 
			
		String[] arr = tokenizer2(str);
		str1 = arr[0];
		str2 = arr[1];
		
		
		if(!factor(str1)) {
			return false;
		}
		if(!morefactors(str2)) { 
			return false;
		}
			
		return true;
	}
		
	static boolean moreterms(String str) throws FileNotFoundException {
		String str1, str2;
		
		if(str.length() > 1) {
			String operation = str.substring(0,1);
			str = str.substring(1);
			str1=(tokenizer1(str)[0]);  // left part of moreterm
			str2= (tokenizer1(str)[1]); // right part of moreterm
				if(!term(str1)) { 
					return false;
				}
				postFix.append(operation); // adding to post-fix notation + or - 
				
				if(!moreterms(str2)) {
					return false;
				}
		}	
		return true;
	}
		
	static boolean factor(String str) throws FileNotFoundException {
			
		if(str.matches("[0-9]+")) { 
			postFix.append(str+" "); 
			return true;
		}else if(str.matches("[A-Za-z0-9]+") && Character.isAlphabetic(str.charAt(0))) { // variable
			if(data.containsKey(str)) {
				postFix.append(str+" ");
			}else { // uninitialized variable
				if(str.equals("while" ) || str.equals("if")) { // keywords can not be variable name
					error();
				}
				System.out.println("	%" + str + " = alloca i32");
				System.out.println("	store i32 0, i32* %" + str);
				data.put(str, 0);
				postFix.append(0+" ");
				
			}
			return true;
		}
		
		if(str.length() > 0) {
			if(str.charAt(0) == '(') { // (expr)
				String str1 = str.substring(1, str.length()-1); // (expr) >> expr
				if(!expr(str1)) {
					error();
					return false;
				}
				if(str.charAt(str.length() - 1) != ')') {
					error();
					return false;
				}
				return true;
			}
			else{
				error();
			}
		}
			
		error();
		return false;
	}
		
	static boolean morefactors(String str) throws FileNotFoundException {
		String str1, str2;
		str1 = str2 = "";
		
		if(str.length() > 1) {
			String operation = str.substring(0,1);
			str = str.substring(1);
			str1=(tokenizer2(str)[0]);  // left part of morefactor  
			str2= (tokenizer2(str)[1]); // right part of morefactor
				if(!factor(str1)) { 
					return false;
				}
				postFix.append(operation); // adding to post-fix notation * or / 
				
				if(!morefactors(str2)) {
					return false;
				}
		}
		return true;
	}
		
	static String[] tokenizer1(String str) { // split from + or -
		String str1;
		String str2;
		int index = 0;
		if(str.contains("+") || str.contains("-")) {
			
			if((str.indexOf("+") < str.indexOf("-") && str.indexOf("+") != -1) || str.indexOf("-") == -1) {
				index = str.indexOf("+");
			}else {
				index = str.indexOf("-");
			}
			
			if(notBetweenParanthesis(str, index)) {
				str1 = str.charAt(index) + " ";
			}else {
				while(!(notBetweenParanthesis(str, index) && (str.charAt(index) == '-' || str.charAt(index) == '+')) && index < str.length() - 1) {
					index++;
				}
			}
			if(index == str.length() - 1) {
				String[] arr= {str,""};
				return arr;
			}else {
				str1 = str.substring(0,index);
				str2 = str.substring(index);
				String[] arr = {str1,str2};
				return arr;
			}
		}else {
			String[] arr= {str,""};
			return arr;
		}
	}
		
	static String[] tokenizer2(String str) { // split from * or / 
		String str1;
		String str2;
		int index;
		
		if(str.contains("*") || str.contains("/")) { 
			
			if((str.indexOf("*") < str.indexOf("/") && str.indexOf("*") != -1) || str.indexOf("/") == -1) {
				index = str.indexOf("*");
			}else {
				index = str.indexOf("/");
			}
			
			if(notBetweenParanthesis(str, index)) {
				str1 = str.charAt(index) + " ";
			}else {
				while(!(notBetweenParanthesis(str, index) && (str.charAt(index) == '/' || str.charAt(index) == '*')) && index < str.length() - 1) {
					index++;
				}
			}
			if(index == str.length() - 1) {
				String[] arr= {str,""};
				return arr;
			}else {
				str1 = str.substring(0,index);
				str2 = str.substring(index);
				String[] arr = {str1,str2};
				return arr;
			}
		}else {
			String[] arr= {str,""};
			return arr;
				
		}	
	}
	
	static boolean notBetweenParanthesis(String str, int index) { // looks whether this index of string is between paranthesis or not 
		int count = 0;
		
		for(int i = 0; i <= index; i++) {
			if(str.charAt(i) == '(') {
				count++;
			}
			if(str.charAt(i) == ')') {
				count--;
			}
		}
		
		if(count == 0) {
			return true;
		}else {
			return false;
		}
	}

	  static String evaluatePostfix(String postfix) { // evaluates post-fix notation
	    	
	    	if(postfix.equals("")) {
	    		return ""; 
	    	}
	    	
	        Stack<String> stack = new Stack<>();
	        
	        for(int i = 0; i < postfix.length(); i++){
	            char c = postfix.charAt(i);
	              
	            if(c == ' ')
	            continue;
	              
	            else if(Character.isDigit(c) || Character.isAlphabetic(c)) {
	                String n = "";

	                while(Character.isDigit(c) || Character.isAlphabetic(c)) {
	                    n += c;
	                    i++;
	                    c = postfix.charAt(i);
	                }
	                i--;
	  
	                stack.push(n);
	              
	            }else {
	            
	                String val1 = stack.pop();
	                String val2 = stack.pop();
	                
	                if(Character.isAlphabetic(val2.charAt(0)) && data.containsKey(val2)) {
	                	System.out.println("	%t_" + tempCounter + " = load i32* %" + val2);
	                	val2 = "%t_" + tempCounter++;
	                }else if(Character.isAlphabetic(val2.charAt(0))){
	                	val2 = "%" + val2;
	                }
	                if(Character.isAlphabetic(val1.charAt(0)) && data.containsKey(val1)) {
	                	System.out.println("	%t_" + tempCounter + " = load i32* %" + val1);
	                	val1 = "%t_" + tempCounter++;
	                }else if(Character.isAlphabetic(val1.charAt(0))){
	                	val1 = "%" + val1;
	                }
	                      
	                switch(c){
	                    case '+':
	                    	System.out.println("	%t_"+ tempCounter +"= add i32 "+val2 +", " +val1);
	                    stack.push("%t_" + tempCounter++);
	                    break;
	                      
	                    case '-':
	                    	System.out.println("	%t_"+ tempCounter +"= sub i32 "+val2 +", " +val1);
	                    stack.push("%t_" + tempCounter++);
	                    break;
	                      
	                    case '/':
	                    	System.out.println("	%t_"+ tempCounter +"= sdiv i32 "+val2 +", " +val1);
	                    	stack.push("%t_" + tempCounter++);
	                    break;
	                      
	                    case '*':
	                    	System.out.println("	%t_"+ tempCounter +"= mul i32 "+val2 +", " +val1);
	                    	
	                    stack.push("%t_" + tempCounter++);
	                    break;
	                }
	            }
	        }
	       
	        String last = stack.pop();
			if(last.matches("[A-Za-z0-9]+") && data.containsKey(last)){ // assigning initialized variable
				System.out.println("	%t_"+ tempCounter +" = load i32* %" + postfix);
				last = "%t_" + tempCounter++;	
			}
			
	        postFix = new StringBuilder("");
	        return last;
	    }
	  
	  static void printer(String str) throws FileNotFoundException { // when line contains print function >> print(expr)
				
			if(str.substring(0,6).equals("print(") && str.charAt(str.length()-1) == ')'){
					String printed  = str.substring(6, str.length()-1);
					
					if(expr(printed)){ // expression
						System.out.println("	call i32 (i8*, ...)* @printf(i8* getelementptr ([4 x i8]* @print.str, i32 0, i32 0), i32 " + evaluatePostfix(postFix.toString()) + ")");
					}
					else {
						error();
					}
			}else {
				error();
			}
	  }
	  
	  static void assigner(String str) throws FileNotFoundException { // when line is in assigning format >> variable = expression
		  
		  String varName = str.substring(0, str.indexOf("="));
		  if(check(varName)) {
			  varName = varName.replaceAll(" ", "");
			  varName = varName.replaceAll("	", "");
		  }else {
			  error();
		  }
		  		  
		  if(!(varName.matches("[A-Za-z0-9]+") && Character.isAlphabetic(varName.charAt(0)))){ // variable name must be alphanumeric
			  error();
		  }

		  
		  if(varName.equals("while") || varName.equals("if") ) { // keywords can not be variable name
			  error();
		  }
		  
		  String rhs = str.substring(str.indexOf("=") + 1);
		  if(expr(rhs)){ // expression
			  System.out.println("	store i32 " + evaluatePostfix(postFix.toString()) + ", i32* %" + varName);
		  }
		  else {
			  error();
		  }
	  }
	  
	  static boolean check(String str) { // checking operand operator format
		  int count = 0;
		  int i = 0;
		  while(count <= 1 && count >= 0 && i < str.length()) {
			 
			  if(str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/') {
				  count--;
				  i++;
			  }
			  
			  if(i < str.length()) {
				  if(str.charAt(i) == ' ' ) {
					  i++;
				  }
			  }
			 
			  boolean inc = false; 
			  while(i < str.length() && !(str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/' || str.charAt(i) == ' ')
){
				  inc = true;
				  i++;
			  }
			  if(inc) {
				  count++;
			  }
		  }
		  
		  if(count == 1 || count == 0) {
			  return true;
		  }else {
			  return false;
		  }
	  }
	  
	  static void error() throws FileNotFoundException { // if an error occurs, clear .ll file and make it to give syntax error
		  PrintWriter writer = new PrintWriter(outFile);
		  writer.print("declare i32 @printf(i8*, ...)\n" + 
					"@print.str = constant [23 x i8] c\"Line %d: syntax error\\0A\\00\"  \ndefine i32 @main() {\n");
		  writer.print("call i32 (i8*, ...)* @printf(i8* getelementptr ([23 x i8]* @print.str, i32 0, i32 0), i32 " + lineNumber + ")");
		  writer.print("\nret i32 0\n}");
		  writer.close();
		  System.exit(1);
	  }
	
}