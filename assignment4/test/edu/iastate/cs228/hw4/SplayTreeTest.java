package edu.iastate.cs228.hw4;

import edu.iastate.cs228.hw4.SplayTree;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class SplayTreeTest {

	@Test
	public void getRoot() {
	}

	@Test
	public void size() {
	}

	@Test
	public void clear() {
	}

	@Test
	public void addBST() {
		String expected = "50\n" +
				"    30\n" +
				"        25\n" +
				"            10\n" +
				"                null\n" +
				"                20\n" +
				"            null\n" +
				"        35\n" +
				"            31\n" +
				"            37\n" +
				"    55\n" +
				"        53\n" +
				"        60\n" +
				"            null\n" +
				"            62\n";
		SplayTree<Integer> splay = new SplayTree<Integer>();
		splay.addBST(50);
		splay.addBST(30);
		splay.addBST(25);
		splay.addBST(35);
		splay.addBST(10);
		splay.addBST(20);
		splay.addBST(31);
		splay.addBST(37);
		splay.addBST(55);
		splay.addBST(53);
		splay.addBST(60);
		splay.addBST(62);
		assertEquals(expected, splay.toString());
	}

	@Test
	public void add() {
	}

	@Test
	public void contains() {
	}

	@Test
	public void splay() {
	}

	@Test
	public void remove() {
	}

	@Test
	public void findElement() {
	}

	@Test
	public void findEntry() {
	}

	@Test
	public void join() {
	}

	@Test
	public void testSplay() {
	}

	@Test
	public void zig() {
		String expected = "5\n" +
				"    4\n" +
				"    10\n" +
				"        6\n" +
				"        11\n";
		SplayTree<Integer> splay = new SplayTree<Integer>();
		splay.addBST(10);
		splay.addBST(5);
		splay.addBST(11);
		splay.addBST(4);
		splay.addBST(6);
		splay.zig(splay.root.left);
		assertEquals(expected, splay.toString());

		expected = "10\n" +
				"    5\n" +
				"        4\n" +
				"        6\n" +
				"    11\n";
		splay = new SplayTree<Integer>();
		splay.addBST(5);
		splay.addBST(4);
		splay.addBST(10);
		splay.addBST(6);
		splay.addBST(11);
		splay.zig(splay.root.right);
		assertEquals(expected, splay.toString());
	}

	@Test
	public void zigZig() {
		// TODO fix buggie
		SplayTree<Integer> splay = new SplayTree<Integer>();
		splay.addBST(15);
		splay.addBST(10);
		splay.addBST(20);
		splay.addBST(5);
		splay.addBST(11);
		splay.addBST(4);
		splay.addBST(6);
		System.out.println(splay.toString());
		splay.zigZig(splay.root.left.left);
		System.out.println(splay.toString());
	}

	@Test
	public void zigZag() {
	}

	@Test
	public void iterator() {
		SplayTree<Integer> splay = new SplayTree<Integer>();
		splay.addBST(50);
		splay.addBST(30);
		splay.addBST(25);
		splay.addBST(35);
		splay.addBST(10);
		splay.addBST(20);
		splay.addBST(31);
		splay.addBST(37);
		splay.addBST(55);
		splay.addBST(53);
		splay.addBST(60);
		splay.addBST(62);
		Iterator si = splay.iterator();
		assertEquals(10, si.next());
		assertTrue(si.hasNext());
		assertEquals(20, si.next());
		assertTrue(si.hasNext());
		assertEquals(25, si.next());
		assertTrue(si.hasNext());
		assertEquals(30, si.next());
		assertTrue(si.hasNext());
		assertEquals(31, si.next());
		assertTrue(si.hasNext());
		assertEquals(35, si.next());
		assertTrue(si.hasNext());
		assertEquals(37, si.next());
		assertTrue(si.hasNext());
		assertEquals(50, si.next());
		assertTrue(si.hasNext());
		assertEquals(53, si.next());
		assertTrue(si.hasNext());
		assertEquals(55, si.next());
		assertTrue(si.hasNext());
		assertEquals(60, si.next());
		assertTrue(si.hasNext());
		assertEquals(62, si.next());
		assertFalse(si.hasNext());
	}

	@Test
	public void	cloneTreeRec() {

	}

	@Test
	public void leftRotate() {
		String expected = "50\n" +
				"    45\n" +
				"        40\n" +
				"            30\n" +
				"            44\n" +
				"        46\n" +
				"    60\n";
		SplayTree<Integer> splay = new SplayTree<Integer>();
		splay.addBST(50);
		splay.addBST(40);
		splay.addBST(60);
		splay.addBST(30);
		splay.addBST(45);
		splay.addBST(44);
		splay.addBST(46);
		splay.leftRotate(splay.root.left.right);
		assertEquals(expected, splay.toString());

		expected = "10\n" +
				"    5\n" +
				"        4\n" +
				"        6\n" +
				"    11\n";
		splay = new SplayTree<Integer>();
		splay.addBST(5);
		splay.addBST(4);
		splay.addBST(10);
		splay.addBST(6);
		splay.addBST(11);
		splay.leftRotate(splay.root.right);
		assertEquals(expected, splay.toString());
	}

	@Test
	public void rightRotate() {
		String expected = "45\n" +
				"    40\n" +
				"        30\n" +
				"        44\n" +
				"    50\n" +
				"        46\n" +
				"        60\n";
		SplayTree<Integer> splay = new SplayTree<Integer>();
		splay.addBST(50);
		splay.addBST(45);
		splay.addBST(60);
		splay.addBST(40);
		splay.addBST(46);
		splay.addBST(30);
		splay.addBST(44);
		splay.rightRotate(splay.root.left);
		assertEquals(expected, splay.toString());

		expected = "5\n" +
				"    4\n" +
				"    10\n" +
				"        6\n" +
				"        11\n";
		splay = new SplayTree<Integer>();
		splay.addBST(10);
		splay.addBST(5);
		splay.addBST(11);
		splay.addBST(4);
		splay.addBST(6);
		splay.rightRotate(splay.root.left);
		assertEquals(expected, splay.toString());
	}

	@Test
	public void unlinkBST() {

	}

	@Test
	public void successor() {

	}
}