package edu.iastate.coms228.hw1;

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
		int[] population = new int[NUM_LIFE_FORMS];
		census(population);

		if (population[RABBIT] >= population[GRASS] * 3) {
			return new Empty(pNew, row, column);
		} else if (population[RABBIT] > 2) {
			return new Rabbit(pNew, row, column, 0);
		} else {
			return new Grass(pNew, row, column);
		}
	}
}