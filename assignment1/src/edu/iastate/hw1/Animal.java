package edu.iastate.hw1;

/**
 *  
 * @author Jacob Duba
 *
 */

/*
 * This class is to be extended by the Badger, Fox, and Rabbit classes. 
 */
public abstract class Animal extends Living implements MyAge
{
	protected int age;   // age of the animal 

	public Animal(int a) {
		age = a;
	}

	@Override
	/**
	 * 
	 * @return age of the animal 
	 */
	public int myAge()
	{
		return age;
	}
}
