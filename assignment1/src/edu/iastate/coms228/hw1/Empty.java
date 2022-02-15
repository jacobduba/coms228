package edu.iastate.coms228.hw1;

/**
 *  
 * @author Empty
 *
 */

/** 
 * Empty squares are competed by various forms of life.
 */
public class Empty extends Living 
{
	public Empty (Plain p, int r, int c) 
	{
		plain = p;
		row = r;
		column = c;
	}
	
	public State who()
	{
		return State.EMPTY;
	}
	
	/**
	 * An empty square will be occupied by a neighboring Badger, Fox, Rabbit, or Grass, or remain empty. 
	 * @param pNew     plain of the next life cycle.
	 * @return Living  life form in the next cycle.   
	 */
	public Living next(Plain pNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for corresponding survival rules.

		Living nextTile;

		int[] censusArray = new int[5];
		census(censusArray);

		if (censusArray[4] > 1) {
			nextTile = new Rabbit(pNew, row, column, 0);
		} else if (censusArray[2] > 1) {
			nextTile = new Fox(pNew, row, column, 0);
		} else if (censusArray[0] > 1) {
			nextTile = new Badger(pNew, row, column, 0);
		} else if (censusArray[3] > 0) {
			nextTile = new Grass(pNew, row, column);
		} else {
			nextTile = new Empty(pNew, row, column);
		}

		return nextTile;
	}
}
