package edu.iastate.coms228.hw1;

/**
 *  
 * @author Jacob Duba
 *
 */

/**
 * 
 * Living refers to the life form occupying a square in a plain grid. It is a 
 * superclass of Empty, Grass, and Animal, the latter of which is in turn a superclass
 * of Badger, Fox, and Rabbit. Living has two abstract methods awaiting implementation. 
 *
 */
public abstract class Living 
{
	protected Plain plain; // the plain in which the life form resides
	protected int row;     // location of the square on which 
	protected int column;  // the life form resides
	
	// constants to be used as indices. 
	protected static final int BADGER = 0; 
	protected static final int EMPTY = 1; 
	protected static final int FOX = 2; 
	protected static final int GRASS = 3; 
	protected static final int RABBIT = 4; 
	
	public static final int NUM_LIFE_FORMS = 5; 
	
	// life expectancies 
	public static final int BADGER_MAX_AGE = 4; 
	public static final int FOX_MAX_AGE = 6; 
	public static final int RABBIT_MAX_AGE = 3; 
	
	/**
	 * Censuses all life forms in the 3 X 3 neighborhood in a plain. 
	 * @param population  counts of all life forms
	 */
	protected void census(int population[ ])
	{
		// Set every value in array to zero.
		for (int i = 0; i < population.length; i++) {
			population[i] = 0;
		}

		// So here I want to census every tile around in 9x9 radius
		// First I find the bounds
		int lowerYBound = Math.max(0, row - 1);
		int upperYBound = Math.min(plain.getWidth() - 1, row + 1);
		int lowerXBound = Math.max(0, column - 1);
		int upperXBound = Math.min(plain.getWidth() - 1, column + 1);

		// Then visit every tile in the bound.
		for (int y = lowerYBound; y <= upperYBound; y++) {
			for (int x = lowerXBound; x <= upperXBound; x++) {
				State lifeForm = plain.grid[y][x].who();
				if (lifeForm == State.BADGER) {
					population[0]++;
				} else if (lifeForm == State.EMPTY) {
					population[1]++;
				} else if (lifeForm == State.FOX) {
					population[2]++;
				} else if (lifeForm == State.GRASS) {
					population[3]++;
				} else { // lifeForm == STATE.RABBIT
					population[4]++;
				}
			}
		}
	}

	/**
	 * Gets the identity of the life form on the square.
	 * @return State
	 */
	public abstract State who();

	// To be implemented in each class of Badger, Empty, Fox, Grass, and Rabbit.
	// 
	// There are five states given in State.java. Include the prefix State in   
	// the return value, e.g., return State.Fox instead of Fox.  
	
	/**
	 * Determines the life form on the square in the next cycle.
	 * @param  pNew  plain of the next cycle
	 * @return Living 
	 */
	public abstract Living next(Plain pNew); 
	// To be implemented in the classes Badger, Empty, Fox, Grass, and Rabbit. 
	// 
	// For each class (life form), carry out the following: 
	// 
	// 1. Obtain counts of life forms in the 3x3 neighborhood of the class object.
	//
	// 2. Applies the survival rules for the life form to determine the life form  
	//    (on the same square) in the next cycle.  These rules are given in the  
	//    project description. 
	// 
	// 3. Generate this new life form at the same location in the plain pNew.      

}
