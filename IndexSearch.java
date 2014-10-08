/* 
 * IndexSearch.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */

import java.util.Scanner;

/**
 * This program implements IndexSearch
 *
 * @author      Rajat Ashok
 */

public class IndexSearch {

	/**
   	 * The main program
	 *
	 * @param       args         
	 */	

	public static void main(String args[]) {
		int size,flag=0;
		int[] data;
		Scanner in = new Scanner(System.in);

		// Input Size
		size = in.nextInt();
		data = new int[size+1];

		// Input data
		for(int count=1; count<=size; count++) {
			data[count] = in.nextInt();
		}

		// Searching
		for(int count=1; count<=size; count++) {
			if(data[count] == count) {
				System.out.println("TRUE");
				flag=1;
				break;
			}
		}
		if(flag==0) {
			System.out.println("FALSE");
		}
	}
}
