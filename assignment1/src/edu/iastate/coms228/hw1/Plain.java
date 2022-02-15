package edu.iastate.coms228.hw1;

/**
 *  
 * @author Jacob Duba
 *
 */

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
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
		File fileContainingPlain = new File(inputFileName);
		Scanner scan = new Scanner(fileContainingPlain);
		String firstLine = scan.nextLine();
		scan.close();

		scan = new Scanner(firstLine);
		width = 0;
		while (scan.hasNext()) {
			width++;
			scan.next();
		}
		grid = new Living[getWidth()][getWidth()];
		scan.close();

		Scanner rowScanner = new Scanner(fileContainingPlain);
		for (int row = 0; row < getWidth(); row++) {
			Scanner colScanner = new Scanner(rowScanner.nextLine());
			for (int col = 0; col < getWidth(); col++) {
				String item = colScanner.next();
				Character type = item.charAt(0);
				if (type == 'B') {
					int age = Character.getNumericValue(item.charAt(1));
					grid[row][col] = new Badger(this, row, col, age);
				} else if (type == 'F') {
					int age = Character.getNumericValue(item.charAt(1));
					grid[row][col] = new Fox(this, row, col, age);
				} else if (type == 'R') {
					int age = Character.getNumericValue(item.charAt(1));
					grid[row][col] = new Rabbit(this, row, col, age);
				} else if (type == 'G') {
					grid[row][col] = new Grass(this, row, col);
				} else { // type == 'E'
					grid[row][col] = new Empty(this, row, col);
				}
			}
			colScanner.close();
		}
		rowScanner.close();
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

		for (int row = 0; row < getWidth(); row++) {
			for (int col = 0; col < getWidth(); col++) {
				int lifeForm = generator.nextInt(5);

				if (lifeForm == 0 ) {
					grid[row][col] = new Badger(this, row, col, 0);
				} else if (lifeForm == 1) {
					grid[row][col] = new Fox(this, row, col, 0);
				} else if (lifeForm == 2) {
					grid[row][col] = new Rabbit(this, row, col, 0);
				} else if (lifeForm == 3) {
					grid[row][col] = new Grass(this, row, col);
				} else { // lifeForm == 4
					grid[row][col] = new Empty(this, row, col);
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
		FileOutputStream fileStream = new FileOutputStream(outputFileName);
		PrintWriter outFS = new PrintWriter(fileStream);
		outFS.print(this);
		outFS.close();
	}
}
