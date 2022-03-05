package edu.iastate.cs228.hw2;

/**
 *  
 * @author Jacob Duba
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


@SuppressWarnings("ALL")
public class CompareSorters
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		// Conducts multiple rounds of comparison of four sorting algorithms.  Within each round,
		// set up scanning as follows: 
		// 
		//    a) If asked to scan random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		// 
		//    b) Reassigns to the array scanners[] (declared below) the references to four new 
		//       PointScanner objects, which are created using four different values  
		//       of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort. 
		// 
		// 	
		PointScanner[] scanners = new PointScanner[4];
		
		// For each input of points, do the following. 
		// 
		//     a) Initialize the array scanners[].  
		//
		//     b) Iterate through the array scanners[], and have every scanner call the scan() 
		//        method in the PointScanner class.  
		//
		//     c) After all four scans are done for the input, print out the statistics table from
		//		  section 2.
		//
		// A sample scenario is given in Section 2 of the project description.

		System.out.println("Performances of Four Sorting Algorithms in Point Sorting");
		System.out.println();
		System.out.println("keys:  1 (random integers)  2 (file input)  3 (exit)");

		Scanner scan = new Scanner(System.in);
		int trialNumber = 0;
		while (trialNumber++ != -1) {
			System.out.print("Trial " + trialNumber + ": ");
			int option = scan.nextInt();
			if (option == 3) {
				trialNumber = -1; // Stop program
			} else if (option == 1 || option == 2) {
				if (option == 1) {
					System.out.print("Enter number of random points: ");
					int numPts = scan.nextInt();
					Point[] pts = generateRandomPoints(numPts, new Random());
					scanners[0] = new PointScanner(pts, Algorithm.SelectionSort);
					scanners[1] = new PointScanner(pts, Algorithm.InsertionSort);
					scanners[2] = new PointScanner(pts, Algorithm.MergeSort);
					scanners[3] = new PointScanner(pts, Algorithm.QuickSort);
				} else {
					System.out.println("Points from a file");
					System.out.print("File name: ");
					String fileName = scan.next();
					scanners[0] = new PointScanner(fileName, Algorithm.SelectionSort);
					scanners[1] = new PointScanner(fileName, Algorithm.InsertionSort);
					scanners[2] = new PointScanner(fileName, Algorithm.MergeSort);
					scanners[3] = new PointScanner(fileName, Algorithm.QuickSort);
				}

				System.out.println("\nalgorithm   size  time (ns)");
				System.out.println("----------------------------------");
				for (PointScanner s : scanners) {
					s.scan();
					System.out.println(s.stats());
				}
				System.out.println("----------------------------------\n");
			}
		}
		scan.close();
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{
		Point[] p = new Point[numPts];
		for (int i = 0; i < numPts; i++) {
			p[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		return p;
	}
	
}
