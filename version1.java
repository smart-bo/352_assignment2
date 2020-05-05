/**
 * @author zhang bo 
 * @ID 40108654
 * COMP352 assignment 1
 * Programming Questions 
 * Version1
 */

package assignment2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;



public class version1 {
	
	static Stack<String> opStk = new Stack<String>();
	static Stack<Double> valStk = new Stack<Double>();
	/**
	 * do binary operation 
	 */
	public static void doOpb1() {
     String op=opStk.pop();
     double x=valStk.pop();
     double y=valStk.pop();
    
     if (op.equals("+")) {
    	 valStk.push(x+y);
     }
     else if (op.equals("-")) {
    	 valStk.push(y-x);
     }
     else if (op.equals("*")) {
    	 valStk.push(x*y);
     }
    	
     else if (op.equals("/")) {
    	 valStk.push(y/x); 
     }  	
     else if (op.equals("^")) {
    	 valStk.push(Math.pow(y,x));
     }

	}
	/**
	 * do unary operation 
	 */
	public static boolean doOpb2() {
	     String op=opStk.pop();
	     double x=valStk.pop();
	     double y=valStk.pop();
	     
	     if (op.equals(">")) {
	    	 return (y>x);

	     }
	     else if (op.equals("<")) {
	    	 return(y<x);
	     }

	     else if (op.equals("¡Ý")) {
	    	 return (y>=x);
	     }
	  
	     else if (op.equals("¡Ü")) {
	    	 return (y<=x);
	     }
	     else if (op.equals("==")) {
	    	 return(y==x);
	     }
	     else 
	    	 return(y!=x);
	}
	     
	public static void doOpu() {
	     String op=opStk.pop();
	     double x=valStk.pop();
	     if (op.equals("!"))  {
	    	 valStk.push(factorial(x));
	     }
	     else if (op.equals("-")) {
	    	 valStk.push(x-1);
	     }
	    	
	}
	
	public static double factorial(double x) {
		if (x==0) return 1;
		else  return x*factorial(x-1);
	}
	
	public static void repeatOp(String op) {
		if (valStk.size()>0&&opStk.size()>0&&priority(op)<=priority((opStk.peek()))) {
			if (opStk.peek().equals("<")||opStk.peek().equals(">")||opStk.peek().equals("==")
					||opStk.peek().equals("!=")||opStk.peek().equals("¡Ý")||opStk.peek().equals("¡Ü"))
			     {System.out.println("final boolean result:  "+doOpb2());}
			else doOpb1();
		}
		//	System.out.println("after cal the value: "+valStk);
		//	System.out.println("after cal the op: "+opStk);
	
	}
	
	public static int priority(String op) {
		switch(op) {
		case "(": return 1;
		case ")": return 1;
		case "!": return 8;
		case "^": return 6;
		case "*": return 5;
		case "/": return 5;
		case "+": return 4;
		case "-": return 4;
		case ">": return 3;
		case "<": return 3;
		case "<=": return 3;
		case ">=": return 3;
		case "==": return 2;
		case "!=": return 2;
		default: return 0;
		}
	}
	/**
	 * @param ariExpression
	 * add blanks between elements
	 * return a new ariExpression can separate the number and operator
	 */
	public static String insetBlanks(String ariExpression) {

        if(ariExpression.contains("(")) {
        	ariExpression=ariExpression.replace("(", " ( ");
        }
        if(ariExpression.contains(")")) {
        	ariExpression=ariExpression.replace(")", " ) ");
        }
        if(ariExpression.contains("-")) {
        	ariExpression=ariExpression.replace("-", " - ");
        }
        if(ariExpression.contains("+")) {
        	ariExpression=ariExpression.replace("+", " + ");
        }
        if(ariExpression.contains("*")) {
        	ariExpression=ariExpression.replace("*", " * ");
        }
        if(ariExpression.contains("/")) {
        	ariExpression=ariExpression.replace("/", " / ");
        }
        if(ariExpression.contains("!")) {
        	if(ariExpression.charAt(ariExpression.indexOf("!")+1)=='=')
        	ariExpression= ariExpression.replace("!=", " != ");
        	else ariExpression= ariExpression.replace("!", " ! ");
        }
        if(ariExpression.contains("^")) {
        	ariExpression=ariExpression.replace("^", " ^ ");
        }
        if(ariExpression.contains("¡Ý")) {
        	ariExpression=ariExpression.replace("¡Ý", " ¡Ý ");
        }
        if(ariExpression.contains("¡Ü")) {
        	ariExpression=ariExpression.replace("¡Ü", " ¡Ü ");
        }
        if(ariExpression.contains(">")) {
        	ariExpression=ariExpression.replace(">", " > ");
        }
        if(ariExpression.contains("<")) {
        	ariExpression=ariExpression.replace("<", " < ");
        }
        if(ariExpression.contains("==")) {
        	ariExpression=ariExpression.replace("==", " == ");
        
        }
    	System.out.println(ariExpression);
        return ariExpression;
    }

	/**
	 * @param string
	 * check this element is number or not
	 */
	public static boolean isNumber(String item) {
		if (item == null) 
	        return false;
	    try {
	        double d = Double.parseDouble(item);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	/**
	 * @param ariExpression
	 * evaluate the expression
	 */
	public static void evalExp(String ariExpression) {
		ariExpression=insetBlanks(ariExpression);
		String[] items = ariExpression.split(" ");

		for (int i=0;i<items.length;i++) {			
			if (items[i].length() == 0)  
                continue;
			else if (isNumber(items[i])) {
				double x=Double.parseDouble(items[i]);
				valStk.push(x);		

			}
			else { 				
			    if(items[i].equals("!")) {
					valStk.push(factorial(valStk.pop()));							
				}
			    else if (items[i].equals("-")) {
			    	if((i+1)==items.length)
			    		valStk.push(valStk.pop()-1);
			    	for (int x=i+1;x<items.length;x++) {
			    		if (items[x].length()!=0) {
			    			if(isNumber(items[x])){
								repeatOp(items[i]);
								opStk.push(items[i]);
								break;
			    			}
			    			else {valStk.push(valStk.pop()-1);break;}
			    		}

			    	}
			    }

				else if (opStk.size()==0||items[i].equals("(")||(opStk.peek().equals("(")))	{	
					opStk.push(items[i]);
					}
				else if(items[i].equals(")")){

					while(!opStk.peek().equals("(")){						
					      repeatOp(items[i]);	

					      }
					opStk.pop();
					}

				else {
					
					repeatOp(items[i]);
					opStk.push(items[i]);

				    }

			}				
		}
	    while(opStk.size()>0) {repeatOp("$");}
	    if(valStk.size()>0) System.out.println("Final result : "+valStk.peek());
	}

	public static void main(String[] args) {
				
		Scanner sc = new Scanner(System.in);
		try {
			sc = new Scanner(new FileInputStream("file.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(sc.hasNext()) {
			String ariExpression=sc.nextLine();
			evalExp(ariExpression);
			}
		
	}
}
