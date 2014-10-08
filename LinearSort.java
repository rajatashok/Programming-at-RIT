/* 
 * LinearSort.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */

import java.util.Random;
import java.util.Scanner;

/**
 * This program implements LinearSort
 *
 * @author      Rajat Ashok
 */

public class Linearsort {

	/**
   	 * The main program
	 *
	 * @param       args         
	 */	

	public static void main(String[] args) {
		LinearSort lin=new LinearSort();
		long size;
        	Scanner in = new Scanner(System.in);
	        // Input Size
		size = in.nextLong();
		long[] data;	
		data = new long[(int) size];
		long cube = size*size*size;		
		long min = 0;

	        // Generating Random numbers
     		Random random = new Random();
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
		
		// Calling Linear sort function 
		long startTime = System.currentTimeMillis();
		long[] sortedArray = lin.linear_sort(data);
		long endTime   = System.currentTimeMillis();
	    	long totalTime = endTime - startTime;
	    
		// Printing Sorted values
	    	System.out.println();
		for (int i = 0; i < sortedArray.length; i++) {
		        System.out.print(sortedArray[i] + " ");
	        }

	        // Printing time taken
		System.out.println();
		System.out.println(totalTime+" milliseconds");
 	}

 public long[] linear_sort(long[] data)
 {
       long maxValue = Long.MIN_VALUE;

       // Finding Max value in the data
       for (long value : data) {
            if (value>maxValue) {
                 maxValue=value;
            }
       }
	//Array for storing sorted elements
       long[] finalSortedArray = new long[data.length];
	//Array used to count the occurences of the data values
       long[] tempArray = new long[(int) (maxValue + 1)];
	
	//Increment the values in the array according to frequency of data
       for(int i=0; i<data.length; i++) {
            tempArray[(int) data[i]] = tempArray[(int) data[i]] + 1;
       }
	//Adds previous values, making a total
       for(int i=1; i<maxValue+1; i++) {
             tempArray[i] = tempArray[i] + tempArray[i-1];
       }
	//Writes out the sorted data into the array
      for(int i=(data.length-1); i>=0; i--) {
             finalSortedArray[ (int) (tempArray[(int) data[i]] - 1)] = (int) data[i];
             tempArray[(int) data[i]] = tempArray[(int)data[i]] - 1;
      }
      return finalSortedArray;
 }
}
