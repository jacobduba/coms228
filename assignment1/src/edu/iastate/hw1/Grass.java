package edu.iastate.hw1;

/**
 *  
 * @author Jacob Duba
 *
 */

/**
 * Grass remains if more than rabbits in the neighborhood; otherwise, it is eaten. 
 *
 */
public class Grass extends Living 
{
	public Grass (Plain p, int r, int c) 
	{
		plain = p;
		row = r;
		column = c;
	}
	
	public State who()
	{
		return State.GRASS;
	}
	
	/**
	 * Grass can be eaten out by too many rabbits. Rabbits may also multiply fast enough to take over Grass.
	 */
	public Living next(Plain pNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for grass.

		Living nextTile;

		int[] censusArray = new int[5];
		census(censusArray);

		if (censusArray[4] >= censusArray[3] * 3) {
			nextTile = new Empty(pNew, row, column);
		} else if (censusArray[4] > 2) {
			nextTile = new Rabbit(pNew, row, column, 0);
		} else {
			nextTile = new Grass(pNew, row, column);
		}

		return nextTile;
	}
}