package edu.iastate.coms228.hw1;

/**
 *  
 * @author Jacob Duba
 *
 */

/**
 * A fox eats rabbits and competes against a badger. 
 */
public class Fox extends Animal 
{
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Fox (Plain p, int r, int c, int a) 
	{
		super(a);
		plain = p;
		row = r;
		column = c;
	}
		
	/**
	 * A fox occupies the square. 	 
	 */
	public State who()
	{
		return State.FOX;
	}
	
	/**
	 * A fox dies of old age or hunger, or from attack by numerically superior badgers. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a fox.

		Living nextTile;

		int[] censusArray = new int[5];
		census(censusArray);

		if (age == 6) {
			nextTile = new Empty(pNew, row, column);
		} else if (censusArray[2] < censusArray[0]) {
			nextTile = new Badger(pNew, row, column, 0);
		} else if (censusArray[0] + censusArray[2] > censusArray[4]) {
			// I could've combined this and the age, but I decided to split them up for readability.
			nextTile = new Empty(pNew, row, column);
		} else {
			nextTile = new Fox(pNew, row, column, age + 1);
		}

		return nextTile;
	}
}
