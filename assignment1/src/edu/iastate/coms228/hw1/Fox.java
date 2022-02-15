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
		int[] population = new int[NUM_LIFE_FORMS];
		census(population);

		if (age == 6) {
			return new Empty(pNew, row, column);
		} else if (population[FOX] < population[BADGER]) {
			return new Badger(pNew, row, column, 0);
		} else if (population[BADGER] + population[FOX] > population[RABBIT]) {
			// I could've combined this and the age, but I decided to split them up for readability.
			return new Empty(pNew, row, column);
		} else {
			return new Fox(pNew, row, column, age + 1);
		}
	}
}
