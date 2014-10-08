/*
 * MorseCounter.java
 * 
 * $Id: v1.3		03/17/2014
 * 
 * $Revision: 
 * v1.1: Designed the class outline
 * 
 * v1.2: Designed the functions and interfaces.
 * 
 * v1.3: Revised the class details
 * 
 * 
 * Author:
 * Rajat Ashok
 * 
 */
import java.util.Scanner;
public class MorseCounter {	
	public static void main(String args[]) {
		double count1=1.0;
		long count2=0;
		Scanner in = new Scanner (System.in);
		String input,output="";
		//Input as string
		input = in.nextLine();
		int n=input.length();
		String[][] a=new String[n+1][1000];
		/* Hardcoding the possibile combination for length 1
		   The number represents the number of characters read 
		   at a time */ 
		a[1][0]="1";
		
		/* Finding all combinations upto length n
		   count 1 is the number of combinations possible which is a constant.
		   Runs with a complexity of O(n*(number of combinations))*/
		for(int i=2;i<=n;i++){
			for(int j=0;j<count1;j++) {
				// Appending "1" with all previous combinations
				a[i][(int)count2++]="1"+a[i-1][j];
				int x=Character.getNumericValue(a[i-1][j].charAt(0));
				// Adding 1 to the first character of previous combinations
				if(a[i-1][j].length()>1 && x<3) {
					a[i][(int)count2++]="" + (1+x) + a[i-1][j].substring(1,a[i-1][j].length());
				}
				else if(x<3) {
					a[i][(int)count2++]="" + (1+x);
				}
			}
			count1=count2;
			count2=0;
		}
		System.out.println(count1);
		count2=(long)count1;
	
		// PART 2
		for(int i=0;i<count1;i++) {
			int start=0,count=-1,flag=0;
			output="";
			for(int j=0;j<a[n][i].length();j++) {
				// The value of characters in the combination is read.
				//   So many characters from input string are compared at a time
				int end=start+Character.getNumericValue(a[n][i].charAt(j));
				switch(input.substring(start,end)) {
					case ".":
						output=output+"E";	
						break;
					case "-":
						output=output+"T";
						break;
					case ".-":
						output=output+"A";
						break;
					case "..":
						output=output+"I";
						break;
					case "--":
						output=output+"M";
						break;
					case "-.":
						output=output+"N";
						break;
					case "-..":
						output=output+"D";
						break;
					case "--.":
						output=output+"G";
						break;
					case "-.-":
						output=output+"K";
						break;
					case "---":
						output=output+"O";
						break;
					case ".-.":
						output=output+"R";
						break;
					case "...":
						output=output+"S";
						break;
					case "..-":
						output=output+"U";
						break;
					case ".--":
						output=output+"W";
						break;
				}
				String y=input.substring(start,end);
				// Checking if vowels and consonants alternate
				if(y.equals(".")||y.equals(".-")||y.equals("..")||y.equals("---")||y.equals("..-")) {
					if(count==0 || count==-1) {
						count=1;
					}
					else {
						flag=1;
					}
				}
				else {
					if(count==1 || count==-1) {
						count=0;
					}
					else {
						flag=1;
					}
				}
				start=end;
			}
			if(flag==1) {
				count2--;
			}
		}
		System.out.println(count2);
	}
}
