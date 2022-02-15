package edu.iastate.coms228.hw1;

/**
 *  
 * @author Jacob Duba
 *
 */

import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random; 

/**
 * 
 * The plain is represented as a square grid of size width x width. 
 *
 */
public class Plain 
{
	private int width; // grid size: width X width 
	
	public Living[][] grid;
	
	/**
	 *  Default constructor reads from a file 
	 */
	public Plain(String inputFileName) throws FileNotFoundException
	{		
        // TODO 
		// 
		// Assumption: The input file is in correct format. 
		// 
		// You may create the grid plain in the following steps: 
		// 
		// 1) Reads the first line to determine the width of the grid.
		//
		// 2) Creates a grid object.
		//
		// 3) Fills in the grid according to the input file. 
		// 
		// Be sure to close the input file when you are done.
		File fileContainingPlain = new File(inputFileName);
		Scanner lineScanner = new Scanner(fileContainingPlain);
		String firstLine = lineScanner.nextLine();
		lineScanner.close();

		Scanner countWidth = new Scanner(firstLine);
		width = 0;
		while (countWidth.hasNext()) {
			width++;
			countWidth.next();
		}
		grid = new Living[width][width];

		lineScanner = new Scanner(fileContainingPlain);
		Scanner colScanner;
		for (int row = 0; row < width; row++) {
			colScanner = new Scanner(lineScanner.nextLine());
			for (int col = 0; col < width; col++) {
				String item = colScanner.next();
				Character type = item.charAt(0);
				int age;
				switch (type) {
					case 'B':
						age = Character.getNumericValue(item.charAt(1));
						grid[row][col] = new Badger(this, row, col, age);
						break;
					case 'F':
						age = Character.getNumericValue(item.charAt(1));
						grid[row][col] = new Fox(this, row, col, age);
						break;
					case 'R':
						age = Character.getNumericValue(item.charAt(1));
						grid[row][col] = new Rabbit(this, row, col, age);
						break;
					case 'G':
						grid[row][col] = new Grass(this, row, col);
						break;
					case 'E':
						grid[row][col] = new Empty(this, row, col);
						break;
				}
			}
		}
	}
	
	/**
	 * Constructor that builds a w x w grid without initializing it. 
	 * @param w the grid
	 */
	public Plain(int w)
	{
		width = w;
		grid = new Living[width][width];
	}
	
	
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Initialize the plain by randomly assigning to every square of the grid  
	 * one of BADGER, FOX, RABBIT, GRASS, or EMPTY.  
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit()
	{
		Random generator = new Random(); 

		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				switch (generator.nextInt(5)) {
					case 0:
						grid[row][col] = new Badger(this, row, col, 0);
						break;
					case 1:
						grid[row][col] = new Fox(this, row, col, 0);
						break;
					case 2:
						grid[row][col] = new Rabbit(this, row, col, 0);
						break;
					case 3:
						grid[row][col] = new Grass(this, row, col);
						break;
					case 4:
						grid[row][col] = new Empty(this, row, col);
						break;
				}
			}
		}
	}
	
	
	/**
	 * Output the plain grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal 
	 * followed by a blank space; otherwise, output two blanks.  
	 */
	public String toString()
	{
		String temp = "";
		for (int row = 0; row < getWidth(); row++) {
			for (int col = 0; col < getWidth(); col++) {
				Living l = grid[row][col];
				if (l.who() == State.BADGER) {
					temp += "B" + ((Animal) l).myAge() + " ";
				} else if (l.who() == State.FOX) {
					temp += "F" + ((Animal) l).myAge() + " ";
				} else if (l.who() == State.RABBIT) {
					temp += "R" + ((Animal) l).myAge() + " ";
				} else if (l.who() == State.GRASS) {
					temp += "G  ";
				} else if (l.who() == State.EMPTY) {
					temp += "E  ";
				}
			}
			temp += "\n";
		}
		return temp;
	}
	

	/**
	 * Write the plain grid to an output file.  Also useful for saving a randomly 
	 * generated plain for debugging purpose. 
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException
	{
		// TODO 
		// 
		// 1. Open the file. 
		// 
		// 2. Write to the file. The five life forms are represented by characters 
		//    B, E, F, G, R. Leave one blank space in between. Examples are given in
		//    the project description. 
		// 
		// 3. Close the file. 
	}			
}
