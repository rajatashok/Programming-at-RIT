/* 
 * LinearSort.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */

import java.util.*;

/**
 * This program implements LinearSort
 *
 * @author      Rajat Ashok
 */

public class QuadraticSort {//Bubble sort

	/**
   	 * The main program
	 *
	 * @param       args         
	 */	

	public static void main(String args[]) {
	     long size, swap;
	     Scanner in = new Scanner(System.in);
 	     // Input Size
	     size = in.nextLong();
	     long[] data;	
	     data = new long[(int) size];
	     long cube = size*size*size;		
	     long min = 0;
	     Random random = new Random();

	     // Generating Random numbers
	     for(int count=0; count<size; count++) {
		        long randomnumber = Math.abs(min+random.nextLong() % (cube-min)); 
			if(randomnumber>(size*size*size-1)) {
				break;
			}
			else {
				data[count] = randomnumber;
			}
	     }	

	     // Printing the numbers generated
	     for(int count=0; count<size; count++) {
		 System.out.print(data[count]+" ");
	     }
     	     long startTime = System.currentTimeMillis();

     	     // Sorting
	     for (int count=0; count<size; count++) {
			for(int count1=0; count1<size-count-1; count1++) {
				if(data[count1] > data[count1+1]) {
					swap = data[count1];
					data[count1] = data[count1+1];
					data[count1+1] = swap;
			}
	     }
	}
	long endTime   = System.currentTimeMillis();
	long totalTime = endTime - startTime;
	     
        // Printing time taken
	System.out.println();
	for(int count=0; count<size; count++) {
		 System.out.print(+data[count]+" ");
	}
	System.out.println("\n"+totalTime+" milliseconds");
	}
}
