package edu.iastate.coms228.hw1;

/**
 *  
 * @author Jacob Duba
 *
 */

/**
 * A badger eats a rabbit and competes against a fox. 
 */
public class Badger extends Animal
{
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Badger (Plain p, int r, int c, int a) 
	{
		super(a);
		plain = p;
		row = r;
		column = c;
	}
	
	/**
	 * A badger occupies the square. 	 
	 */
	public State who()
	{
		return State.BADGER;
	}

	
	/**
	 * A badger dies of old age or hunger, or from isolation and attack by a group of foxes. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a badger.
		Living nextTile;

		int[] censusArray = new int[5];
		census(censusArray);

		if (age == 4) {
			nextTile = new Empty(pNew, row, column);
		} else if (censusArray[0] == 1 && censusArray[2] > 1) {
			nextTile = new Fox(pNew, row, column, 0);
		} else if (censusArray[0] + censusArray[2] > censusArray[4]) {
			// I could've combined this and the age, but I decided to split them up for readability.
			nextTile = new Empty(pNew, row, column);
		} else {
			nextTile = new Badger(pNew, row, column, age + 1);
		}

		return nextTile;
	}
}