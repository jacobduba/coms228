package edu.iastate.hw1;

/**
 *  
 * @author Jacob Duba
 *
 */

/*
 * A rabbit eats grass and lives no more than three years.
 */
public class Rabbit extends Animal 
{	
	/**
	 * Creates a Rabbit object.
	 * @param p: plain  
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Rabbit (Plain p, int r, int c, int a) 
	{
		super(a);
		plain = p;
		row = r;
		column = c;
	}
		
	// Rabbit occupies the square.
	public State who()
	{
		return State.RABBIT;
	}
	
	/**
	 * A rabbit dies of old age or hunger. It may also be eaten by a badger or a fox.  
	 * @param pNew     plain of the next cycle 
	 * @return Living  new life form occupying the same square
	 */
	public Living next(Plain pNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a rabbit.

		Living nextTile;

		int[] censusArray = new int[5];
		census(censusArray);

		if (age == 3) {
			nextTile = new Empty(pNew, row, column);
		} else if (censusArray[4] == 0) {
			nextTile = new Empty(pNew, row, column);
		} else if (censusArray[0] + censusArray[2] >= censusArray[4] || censusArray[2] > censusArray[0]) {
			nextTile = new Fox(pNew, row, column, 0);
		} else if (censusArray[0] > censusArray[4]) {
			nextTile = new Badger(pNew, row, column, 0);
		} else {
			nextTile = new Rabbit(pNew, row, column, age + 1);
		}

		return nextTile;
	}
}
