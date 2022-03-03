package edu.iastate.cs228.hw2;

/**
 *  
 * @author Jacob Duba
 *
 */

import java.util.Arrays;

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "MergeSort";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		mergeSortRec(points);
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		int n = pts.length;
		if (n == 1) return;
		int m = n / 2;

		// Merge left side
		Point[] left = new Point[m];
		for (int i = 0; i < m; i++) {
			left[i] = pts[i];
		}
		mergeSortRec(left);

		// Merge right side
		Point[] right = new Point[n - m];
		for (int i = m; i < n; i++) {
			right[i - m] = pts[i];
		}
		mergeSortRec(right);

		Point[] tmp = merge(left, right);
		for (int i = 0; i < n; i++) {
			pts[i] = tmp[i];
		}
	}

	// Other private methods if needed ...

	private Point[] merge(Point[] b, Point[] c) {
		int p = b.length, q = c.length;
		Point[] d = new Point[p + q];
		int i = 0, j = 0;
		while (i < p && j < q) {
			if (b[i].compareTo(c[j]) <= 0) {
				d[i + j] = b[i];
				i++;
			} else {
				d[i + j] = c[j];
				j++;
			}
		}
		if (i >= p) {
			while (j < q) {
				d[i + j] = c[j];
				j++;
			}
		} else {
			while (i < p) {
				d[i + j] = b[i];
				i++;
			}
		}
		return d;
	}
}
