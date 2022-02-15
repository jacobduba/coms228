package edu.iastate.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 *  
 * @author Jacob Duba
 *
 */

/**
 * 
 * The Wildlife class performs a simulation of a grid plain with
 * squares inhabited by badgers, foxes, rabbits, grass, or none. 
 *
 */
public class Wildlife 
{
	/**
	 * Update the new plain from the old plain in one cycle. 
	 * @param pOld  old plain
	 * @param pNew  new plain 
	 */
	public static void updatePlain(Plain pOld, Plain pNew)
	{
		// TODO 
		// 
		// For every life form (i.e., a Living object) in the grid pOld, generate  
		// a Living object in the grid pNew at the corresponding location such that 
		// the former life form changes into the latter life form. 
		// 
		// Employ the method next() of the Living class. 
	}
	
	/**
	 * Repeatedly generates plains either randomly or from reading files. 
	 * Over each plain, carries out an input number of cycles of evolution. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{	
		// TODO 
		// 
		// Generate wildlife simulations repeatedly like shown in the 
		// sample run in the project description. 
		// 
		// 1. Enter 1 to generate a random plain, 2 to read a plain from an input
		//    file, and 3 to end the simulation. (An input file always ends with 
		//    the suffix .txt.)
		// 
		// 2. Print out standard messages as given in the project description. 
		// 
		// 3. For convenience, you may define two plains even and odd as below. 
		//    In an even numbered cycle (starting at zero), generate the plain 
		//    odd from the plain even; in an odd numbered cycle, generate even 
		//    from odd. 
		//
		// 4. Print out initial and final plains only.  No intermediate plains should
		//    appear in the standard output.  (When debugging your program, you can 
		//    print intermediate plains.)
		// 
		// 5. You may save some randomly generated plains as your own test cases. 
		// 
		// 6. It is not necessary to handle file input & output exceptions for this 
		//    project. Assume data in an input file to be correctly formatted.

		System.out.println("Simulation of the Wildlife of the Plain");
		System.out.println("keys: 1 (random plain) 2 (file input) 3 (exit)\n");

		Plain even;   				 // the plain after an even number of cycles
		Plain odd;                   // the plain after an odd number of cycles

		int trialCount = 1;

		Scanner scan = new Scanner(System.in);
		while (trialCount != -1) {
			System.out.print("Trial " + trialCount++ + ": ");
			int option = scan.nextInt();
			Plain initialPlain;
			int cycles;
			if (option == 3) {
				trialCount = -1;
			} else if (option == 1 || option == 2){
				if (option == 1) {
					System.out.println("Random Plain");
					System.out.print("Enter grid width: ");
					int width = scan.nextInt();
					System.out.print("Enter the number of cycles: ");
					cycles = scan.nextInt();
					initialPlain = new Plain(width);
					initialPlain.randomInit();
				} else { // else option must be 2
					// TODO
					System.out.println("Plain input from a file");
					System.out.print("File name: ");
					String fileName = scan.next();
					initialPlain = new Plain(fileName);
					System.out.print("Enter the number of cycles: ");
					cycles = scan.nextInt();
				}
				System.out.println("Initial plain:\n");
				System.out.println(initialPlain);

				// Even and odd both point at initialPlain...
				even = initialPlain;
				odd = initialPlain;
				for (int cycleCount = cycles; cycleCount > 0; cycleCount--) {
					if (cycleCount % 2 == 0) {
						odd = evolvePlain(even);
						System.out.println("Cycle: " + cycleCount);
						System.out.println(odd.toString());
					} else {
						even = evolvePlain(odd);
						System.out.println("Cycle: " + cycleCount);
						System.out.println(odd.toString());
					}
				}
				System.out.println("Final plain:\n");
				System.out.println(even); // Plain at 0 should be even.
			}
		}
		scan.close();
	}

	private static Plain evolvePlain(Plain prevPlain) {
		Plain newPlain = new Plain(prevPlain.getWidth());

		for (int row = 0; row < prevPlain.getWidth(); row++) {
			for (int col = 0; col < prevPlain.getWidth(); col++) {
				newPlain.grid[row][col] = prevPlain.grid[row][col].next(newPlain);
			}
		}

		return newPlain;
	}
}