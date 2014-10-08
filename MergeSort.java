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
public class MergeSort{

	 /**
	  * The main program
	  *
	  * @param       args         
	  */	

	  public static void main(String a[]){
	  	int i;
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
		for(i = 0; i <data.length; i++) {
			System.out.print(data[i]+"  ");  
		}

	     	// Calling Merge Sort function
		long startTime = System.currentTimeMillis();
	  	mergesort(data,0, data.length-1);
	  	long endTime   = System.currentTimeMillis();
	  	long totalTime = endTime - startTime;

		// Printing Sorted values
	  	System.out.println();
	  	for(i = 0; i <data.length; i++) {
	  		System.out.print(data[i]+"  ");  

	  	}
	        // Printing time taken
	  	System.out.println();
	  	System.out.println(totalTime+" milliseconds");
	  }
	  

	  public static void mergesort(long[] data,int lo, int n){
		  int low = lo;
		  int high = n;
		  if (low >= high) {
			  return;
		  }

		  int middle = (low + high) / 2;
	     	  // Calling Merge Sort function recursively 
		  mergesort(data, low, middle);
		  mergesort(data, middle + 1, high);
		  int endlow = middle;
		  int starthigh = middle + 1;

		  // Comparing the values and sorting
		  while ((lo <= endlow) && (starthigh <= high)) {
			  if (data[low] < data[starthigh]) {
				  low++;
			  } 
			  else {
				long Temp = data[starthigh];
				for (int k = starthigh- 1; k >= low; k--) {
					data[k+1] = data[k];
				}
				data[low] = Temp;
				low++;
				endlow++;
				starthigh++;
			  }
		  }
	  }  
}
