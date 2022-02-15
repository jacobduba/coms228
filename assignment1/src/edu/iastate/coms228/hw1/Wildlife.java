package edu.iastate.coms228.hw1;

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
	public static void updatePlain(Plain pOld, Plain pNew) {
		for (int row = 0; row < pOld.getWidth(); row++) {
			for (int col = 0; col < pOld.getWidth(); col++) {
				pNew.grid[row][col] = pOld.grid[row][col].next(pNew);
			}
		}
	}
	
	/**
	 * Repeatedly generates plains either randomly or from reading files. 
	 * Over each plain, carries out an input number of cycles of evolution. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{	
		System.out.println("Simulation of the Wildlife of the Plain");
		System.out.println("keys: 1 (random plain) 2 (file input) 3 (exit)\n");

		Plain even;   				 // the plain after an even number of cycles
		Plain odd;                   // the plain after an odd number of cycles

		int option = 0;
		int trialCount = 0;

		Scanner scan = new Scanner(System.in);
		while (option != 3) {
			System.out.print("Trial " + ++trialCount + ": ");
			option = scan.nextInt();
			Plain initialPlain;
			int cycles;
			if (option == 1 || option == 2){
				if (option == 1) {
					System.out.println("Random Plain");
					System.out.print("Enter grid width: ");
					int width = scan.nextInt();
					System.out.print("Enter the number of cycles: ");
					cycles = scan.nextInt();
					initialPlain = new Plain(width);
					initialPlain.randomInit();
				} else { // else option must be 2
					System.out.println("Plain input from a file");
					System.out.print("File name: ");
					String fileName = scan.next();
					initialPlain = new Plain(fileName);
					System.out.print("Enter the number of cycles: ");
					cycles = scan.nextInt();
				}
				System.out.println("Initial plain:\n");
				System.out.println(initialPlain);

				if (cycles % 2 == 0) {
					even = initialPlain;
					odd = new Plain(even.getWidth());
				} else {
					odd = initialPlain;
					even = new Plain(odd.getWidth());
				}

				for (int cycleCount = cycles; cycleCount > 0; cycleCount--) {
					if (cycleCount % 2 == 0) {
						updatePlain(even, odd);
						System.out.println(odd.toString());
					} else {
						updatePlain(odd, even);
						System.out.println(even.toString());
					}
				}

				System.out.println("Final plain:\n");
				System.out.println(even); // Plain at 0 should be even.
			}
		}
		scan.close();
	}
}