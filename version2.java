package assignment2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class version2 {
	
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
        	if(ariExpression.indexOf("!")+1==ariExpression.length()||ariExpression.charAt(ariExpression.indexOf("!")+1)!='=')
        	ariExpression= ariExpression.replace("!", " ! ");
        	else ariExpression= ariExpression.replace("!=", " != ");
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
    	System.out.println("line 53:"+ariExpression);
        return ariExpression;
    }

	public static double factorial(double x) {
		if (x==0) return 1;
		else  return x*factorial(x-1);
	}
	
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
	
	
	public static String evalExp(String ariExpression) {
		String newExpr=null;
		//String[] items = ariExpression.split(" ");
		int lIndex=0;
		int rIndex=ariExpression.length()-1;
		
		if(isNumber(ariExpression)){
			System.out.println("line 81 Final result is: "+ariExpression);
			return ariExpression;
		}
		else if (ariExpression.contains("(")) {
			
			 lIndex = ariExpression.lastIndexOf("(");			
			 rIndex = ariExpression.indexOf(")", lIndex); 
        
			String subExpr = ariExpression.substring(lIndex+1, rIndex);
			System.out.println("line 90 "+subExpr);
			String tempresult=evalExp(subExpr);
			System.out.println("line 92  "+tempresult);
			newExpr = ariExpression.substring(0, lIndex-1) + tempresult
					+ ariExpression.substring(rIndex + 2);
			System.out.println("line 94  "+ariExpression);
			System.out.println("line 95  "+newExpr);
			return evalExp(newExpr);
		}
		else if(ariExpression.contains("!")) {
			rIndex=ariExpression.indexOf("!");
			for(int i=rIndex-2;i>0;i--) {				
				if (ariExpression.charAt(i)==' ') {
					lIndex=i;
					break;
				}
			}			
			String subExpr = ariExpression.substring(lIndex, rIndex);
			double x=0;
			if(isNumber(subExpr)) x=Double.parseDouble(subExpr);	
			System.out.println("line107"+subExpr);
			System.out.println("line108Êý×ÖÊÇ£º "+x);
			newExpr = ariExpression.substring(0, lIndex) + factorial(x) 
			+ ariExpression.substring(rIndex + 1);
	        return evalExp(newExpr);
			
		}
		
		else if(ariExpression.contains("-")&&!ariExpression.contains("--")) {
			System.out.println("arrive here line 116");
			int temp=ariExpression.indexOf("-");

			if(temp+2>=ariExpression.length()) {
		    	for(int i=temp-2;i>0;i--) {				
					if (ariExpression.charAt(i)==' ') {
						lIndex=i;
						break;
					}
				}
		    	String subExpr1 = ariExpression.substring(lIndex, temp);
				double x=0;
				if(isNumber(subExpr1)) x=Double.parseDouble(subExpr1);
					newExpr = ariExpression.substring(0, lIndex) + (x-1) 
				+ ariExpression.substring(rIndex + 1);
		        return evalExp(newExpr);
		    	
			}
			
			else {				
				for(int i=temp+2;i<ariExpression.length();i++) {	

					if(ariExpression.charAt(i)==' ') {
						rIndex=i;
						System.out.println("arrive here line 132");						
						break;
					}
				}
				
	    		String subExpr= ariExpression.substring(temp+2, rIndex+1);
	    		System.out.println("arrive here line 145"+subExpr);
	    		if(!isNumber(subExpr)) {

	    			for(int i=temp-2;i>0;i--) {				
	    				if (ariExpression.charAt(i)==' ') {
	    					lIndex=i;
	    					break;
	    				}
	    			}				
	    			String subExpr1 = ariExpression.substring(lIndex, temp);
	    			double x=0;
	    			if(isNumber(subExpr1)) x=Double.parseDouble(subExpr1);
	    				newExpr = ariExpression.substring(0, lIndex) + (x-1) 
	    			+ ariExpression.substring(rIndex + 1);
	    	        return evalExp(newExpr);}
	    		else  {
	    			System.out.println("arrive here line 161"+subExpr);	    			
	    			evalExp(ariExpression.replaceFirst("-", "--"));}
			}
			
		}
						
	    

	    else if (ariExpression.contains("^")) {
	    	
	    	int temp=ariExpression.indexOf("^");
	    	
	    	for(int i=temp-2;i>0;i--) {	
	    		if (ariExpression.charAt(i)==' ') {
					lIndex=i;
					break;
				}
			}
	    	for(int i=temp+2;i<ariExpression.length();i++) {				
				if (ariExpression.charAt(i)==' ') {
					rIndex=i;
					break;
				}
			}

	    	String subExpr1 = ariExpression.substring(temp+1, rIndex+1);
			double x=0;
			if(isNumber(subExpr1)) x=Double.parseDouble(subExpr1);
			String subExpr2 = ariExpression.substring(lIndex, temp);
			double y=0;
			if(isNumber(subExpr2)) y=Double.parseDouble(subExpr2);
			System.out.println("line 177 x and y:"+x+" y: "+y);			
			newExpr = ariExpression.substring(0, lIndex) + Math.pow(y, x) 
			+ ariExpression.substring(rIndex + 1);

	        return evalExp(newExpr);
	    	
	    }
		
	    else if (ariExpression.contains("*")) {
	    	
	    	int temp=ariExpression.indexOf("*");
	    	
	    	for(int i=temp-2;i>0;i--) {				
				if (ariExpression.charAt(i)==' ') {
					lIndex=i;
					break;
				}
			}
	    	for(int i=temp+2;i<ariExpression.length();i++) {				
				if (ariExpression.charAt(i)==' ') {
					rIndex=i;
					break;
				}
			}
	    	
	    	String subExpr1 = ariExpression.substring(temp+1, rIndex+1);
			double x=0;
			if(isNumber(subExpr1)) x=Double.parseDouble(subExpr1);
			String subExpr2 = ariExpression.substring(lIndex, temp);
			double y=0;
			if(isNumber(subExpr2)) y=Double.parseDouble(subExpr2);
			
			
			newExpr = ariExpression.substring(0, lIndex) + (y*x) 
			+ ariExpression.substring(rIndex + 1);
	        return evalExp(newExpr);
	    	
	    }
	    else if (ariExpression.contains("/")) {
	    	
	    	int temp=ariExpression.indexOf("/");
	    	
	    	for(int i=temp-2;i>0;i--) {				
				if (ariExpression.charAt(i)==' ') {
					lIndex=i;
					break;
				}
			}
	    	for(int i=temp+2;i<ariExpression.length();i++) {				
				if (ariExpression.charAt(i)==' ') {
					rIndex=i;
					break;
				}
			}
	    	
	    	String subExpr1 = ariExpression.substring(temp+1, rIndex+1);
			double x=0;
			if(isNumber(subExpr1)) x=Double.parseDouble(subExpr1);
			String subExpr2 = ariExpression.substring(lIndex, temp);
			double y=0;
			if(isNumber(subExpr2)) y=Double.parseDouble(subExpr2);
			
			
			 newExpr = ariExpression.substring(0, lIndex) + (y/x) 
			+ ariExpression.substring(rIndex + 1);
			 
			 System.out.println("line 243"+newExpr);
	        return evalExp(newExpr);
	    	
	    }
	    else if (ariExpression.contains("+")) {
	    	
	    	int temp=ariExpression.indexOf("+");
	    	
	    	for(int i=temp-2;i>0;i--) {				
				if (ariExpression.charAt(i)==' ') {
					lIndex=i;
					break;
				}
			}
	    	for(int i=temp+2;i<ariExpression.length();i++) {				
				if (ariExpression.charAt(i)==' ') {
					rIndex=i;
					break;
				}
			}
	    	
	    	String subExpr1 = ariExpression.substring(temp+1, rIndex+1);
			double x=0;
			if(isNumber(subExpr1)) x=Double.parseDouble(subExpr1);
			String subExpr2 = ariExpression.substring(lIndex, temp);
			double y=0;
			if(isNumber(subExpr2)) y=Double.parseDouble(subExpr2);
			
			
			 newExpr = ariExpression.substring(0, lIndex) + (y+x) 
			+ ariExpression.substring(rIndex + 1);
			 
			 System.out.println("line 275"+newExpr);
	        return evalExp(newExpr);
	    	
	    }
	    else if (ariExpression.contains("--")) {
	    	
	    	 System.out.println("line 299"+ariExpression);
	    	int temp=ariExpression.indexOf("--");
	    	 System.out.println("line 292"+ariExpression);
	    	for(int i=temp-2;i>0;i--) {				
				if (ariExpression.charAt(i)==' ') {
					lIndex=i;
					break;
				}
			}
	    	for(int i=temp+3;i<ariExpression.length();i++) {				
				if (ariExpression.charAt(i)==' ') {
					rIndex=i;
					break;
				}
			}
	    	
	    	String subExpr1 = ariExpression.substring(temp+2, rIndex+1);
			double x=0;
			if(isNumber(subExpr1)) x=Double.parseDouble(subExpr1);
			String subExpr2 = ariExpression.substring(lIndex, temp);
			double y=0;
			if(isNumber(subExpr2)) y=Double.parseDouble(subExpr2);
			
			
			 newExpr = ariExpression.substring(0, lIndex) + (y-x) 
			+ ariExpression.substring(rIndex + 1);
			 
			 System.out.println("line 324"+newExpr);
	        return evalExp(newExpr);
	    	
	    }		
		return newExpr;
		
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
			ariExpression=insetBlanks(ariExpression);
			evalExp(ariExpression);
			}
		
	}
}
