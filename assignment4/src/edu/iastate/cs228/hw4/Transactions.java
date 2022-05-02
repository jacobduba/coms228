package edu.iastate.cs228.hw4;


import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Jacob Duba
 */

/**
 *
 * The Transactions class simulates video transactions at a video store. 
 *
 */
public class Transactions {

	private static final int RENT = 1;
	private static final int BULK_RENT = 2;
	private static final int RETURN = 3;
	private static final int BULK_RETURN = 4;
	private static final int SUMMARY = 5;
	private static final int EXIT = 6;

	/**
	 * The main method generates a simulation of rental and return activities.  
	 *
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// 1. Construct a VideoStore object.
		// 2. Simulate transactions as in the example given in Section 4 of the 
		//    the project description.

		VideoStore videoStore = new VideoStore("videoList1.txt");

		System.out.println(
				"Transactions at a Video Store\n" +
				"keys: 1 (rent)     2 (bulk rent)\n" +
				"      3 (return)   4 (bulk return)\n" +
				"      5 (summary)  6 (exit)"
				);

		Scanner scan = new Scanner(System.in);
		int state = -1;
		while (state != EXIT) {
			System.out.print("\nTransaction: ");
			state = Integer.valueOf(scan.nextLine());

			if (state == RENT) {
				System.out.print("Film to rent: ");
				String line = scan.nextLine();
				try {
					videoStore.videoRent(VideoStore.parseFilmName(line), VideoStore.parseNumCopies(line));
				// Preferably, error message would be printed here, but assignment required it to be inside videoRent()
				} catch (FilmNotInInventoryException | AllCopiesRentedOutException e) {}
			} else if (state == BULK_RENT) {
				System.out.print("Video file (rent): ");
				try {
					videoStore.bulkRent(scan.nextLine());
				} catch (FilmNotInInventoryException | AllCopiesRentedOutException e) {}
			} else if (state == RETURN) {
				System.out.print("Film to return: ");
				String line = scan.nextLine();
				try {
					videoStore.videoReturn(VideoStore.parseFilmName(line), VideoStore.parseNumCopies(line));
				} catch (FilmNotInInventoryException e) {}
			} else if (state == BULK_RETURN) {
				System.out.print("Video file (return): ");
				try {
					videoStore.bulkReturn(scan.nextLine());
				} catch (FilmNotInInventoryException e) {}
			} else if (state == SUMMARY) {
				System.out.print(videoStore.transactionsSummary());
			} else if (state == EXIT) {
				scan.close();
			}
		}
	}
}
