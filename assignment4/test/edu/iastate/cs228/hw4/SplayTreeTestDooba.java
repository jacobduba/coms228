package edu.iastate.cs228.hw4;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * author: Jacob Duba
 */
public class SplayTreeTestDooba {

	@Test
	public void getRoot() {
		SplayTree<Integer> splay = new SplayTree<Integer>();
		assertEquals(null, splay.getRoot());
		splay.addBST(50);
		splay.addBST(30);
		splay.addBST(25);
		splay.addBST(35);
		splay.addBST(10);
		assertEquals(50, (int) splay.getRoot());
	}

	@Test
	public void size() {
		SplayTree<Integer> splay = new SplayTree<Integer>();
		splay.splay(10);
		assertEquals(0, splay.size());
		splay.addBST(50);
		splay.addBST(30);
		splay.addBST(25);
		splay.addBST(35);
		splay.addBST(10);
		splay.addBST(20);
		splay.addBST(31);
		splay.addBST(37);
		splay.addBST(55);
		splay.add(53);
		splay.add(60);
		splay.add(62);
		assertEquals(12, splay.size());
		splay.remove(30);
		assertEquals(11, splay.size());
		splay.unlinkBST(splay.root.left);
		assertEquals(10, splay.size());
		splay.unlinkBST(splay.root);
		assertEquals(9, splay.size());
	}

	@Test
	public void clear() {
		SplayTree<Integer> splay = new SplayTree<Integer>();
		assertEquals(0, splay.size());
		splay.clear();
		assertEquals(0, splay.size());
		splay.addBST(50);
		splay.addBST(30);
		splay.addBST(25);
		splay.addBST(35);
		splay.addBST(10);
		splay.addBST(20);
		splay.addBST(31);
		splay.addBST(37);
		assertEquals(8, splay.size());
		splay.clear();
		assertEquals(0, splay.size());
		assertEquals(null, splay.getRoot());
	}

	@Test
	public void addBST() {
		String expected = "null\n";
		SplayTree<Integer> splay = new SplayTree<Integer>();
		assertEquals(expected, splay.toString());


		expected = "50\n" +
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
		// Addition isn't already in array
		String expected = "80\n" +
				"    60\n" +
				"        50\n" +
				"            30\n" +
				"                10\n" +
				"                    null\n" +
				"                    20\n" +
				"                        15\n" +
				"                        null\n" +
				"                40\n" +
				"            null\n" +
				"        70\n" +
				"    90\n" +
				"        null\n" +
				"        100\n";
		SplayTree<Integer> splay = new SplayTree<Integer>();
		splay.add(50); // If this breaks, you're missing the size == 0 edge case;
		splay.addBST(30);
		splay.addBST(60);
		splay.addBST(10);
		splay.addBST(40);
		splay.addBST(20);
		splay.addBST(15);
		splay.addBST(90);
		splay.addBST(70);
		splay.addBST(100);
		assertTrue(splay.add(80));
		assertEquals(expected, splay.toString());

		// Addition is already in array
		expected = "70\n" +
		"    50\n" +
				"        30\n" +
				"            10\n" +
				"                null\n" +
				"                20\n" +
				"                    15\n" +
				"                    null\n" +
				"            40\n" +
				"        60\n" +
				"    90\n" +
				"        null\n" +
				"        100\n";
		splay = new SplayTree<Integer>();
		splay.addBST(50);
		splay.addBST(30);
		splay.addBST(60);
		splay.addBST(10);
		splay.addBST(40);
		splay.addBST(20);
		splay.addBST(15);
		splay.addBST(90);
		splay.addBST(70);
		splay.addBST(100);
		assertFalse(splay.add(70));
		assertEquals(expected, splay.toString());
	}

	@Test
	public void contains() {
		String expected = "70\n" +
				"    50\n" +
				"        30\n" +
				"            10\n" +
				"                null\n" +
				"                20\n" +
				"                    15\n" +
				"                    null\n" +
				"            40\n" +
				"        60\n" +
				"    90\n" +
				"        null\n" +
				"        100\n";
		SplayTree<Integer> splay = new SplayTree<Integer>();
		assertFalse(splay.contains(70));
		splay.addBST(50);
		splay.addBST(30);
		splay.addBST(60);
		splay.addBST(10);
		splay.addBST(40);
		splay.addBST(20);
		splay.addBST(15);
		splay.addBST(90);
		splay.addBST(70);
		splay.addBST(100);
		assertTrue(splay.contains(70));
		assertEquals(expected, splay.toString());

		// Using a number that's not in the tree should splay the last index.
		splay = new SplayTree<Integer>();
		assertFalse(splay.contains(70));
		splay.addBST(50);
		splay.addBST(30);
		splay.addBST(60);
		splay.addBST(10);
		splay.addBST(40);
		splay.addBST(20);
		splay.addBST(15);
		splay.addBST(90);
		splay.addBST(70);
		splay.addBST(100);
		assertFalse(splay.contains(80));
		assertEquals(expected, splay.toString());
	}

	@Test
	public void splay() {
		// If this test fails, make sure to check if rotateLeft, rotateRight, zig, zigZig,
		// zigZag pass first.

		// Zig test
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
		splay.splay(splay.root.left);
		assertEquals(expected, splay.toString());

		// Zig-zig test
		expected = "15\n" +
				"    10\n" +
				"        5\n" +
				"        14\n" +
				"    20\n" +
				"        16\n" +
				"        30\n";
		splay = new SplayTree<Integer>();
		splay.addBST(20);
		splay.addBST(10);
		splay.addBST(30);
		splay.addBST(5);
		splay.addBST(15);
		splay.addBST(14);
		splay.addBST(16);
		splay.splay(splay.root.left.right);
		assertEquals(expected, splay.toString());

		// Zig-zag
		expected = "15\n" +
				"    10\n" +
				"        5\n" +
				"        14\n" +
				"    20\n" +
				"        16\n" +
				"        30\n";
		splay = new SplayTree<Integer>();
		splay.addBST(20);
		splay.addBST(10);
		splay.addBST(30);
		splay.addBST(5);
		splay.addBST(15);
		splay.addBST(14);
		splay.addBST(16);
		splay.splay(splay.root.left.right);
		assertEquals(expected, splay.toString());

		// All of the above
		splay = new SplayTree<Integer>();
		splay.addBST(100);
		splay.addBST(98);
		splay.addBST(101);
		splay.addBST(96);
		splay.addBST(99);
		splay.addBST(10);
		splay.addBST(97);
		splay.addBST(9);
		splay.addBST(50);
		splay.addBST(25);
		splay.addBST(51);
		splay.addBST(24);
		splay.addBST(27);
		splay.addBST(26);
		splay.addBST(29);
		splay.addBST(28);
		splay.addBST(31);
		splay.addBST(30);
		splay.addBST(32);
		expected = "31\n" +
				"    10\n" +
				"        9\n" +
				"        25\n" +
				"            24\n" +
				"            29\n" +
				"                27\n" +
				"                    26\n" +
				"                    28\n" +
				"                30\n" +
				"    98\n" +
				"        96\n" +
				"            50\n" +
				"                32\n" +
				"                51\n" +
				"            97\n" +
				"        100\n" +
				"            99\n" +
				"            101\n";
		splay.splay(splay.root.left.left.left.right.left.right.right.right);
		assertEquals(expected, splay.toString());
	}

	@Test
	public void remove() {
		String expected = "50\n" +
				"    20\n" +
				"        10\n" +
				"            null\n" +
				"            15\n" +
				"        40\n" +
				"    60\n" +
				"        null\n" +
				"        80\n" +
				"            70\n" +
				"            90\n" +
				"                null\n" +
				"                100\n";
		SplayTree<Integer> splay = new SplayTree<Integer>();
		splay.remove(30);
		splay.addBST(80);
		splay.addBST(60);
		splay.addBST(90);
		splay.addBST(50);
		splay.addBST(70);
		splay.addBST(100);
		splay.addBST(30);
		splay.addBST(10);
		splay.addBST(40);
		splay.addBST(20);
		splay.addBST(15);
		splay.remove(30);
		assertEquals(expected, splay.toString());

		// What happens when the node has null child(ren)?
		// Both children null
		expected = "80\n" +
				"    60\n" +
				"        50\n" +
				"            20\n" +
				"                10\n" +
				"                    null\n" +
				"                    15\n" +
				"                40\n" +
				"            null\n" +
				"        null\n" +
				"    90\n" +
				"        null\n" +
				"        100\n";
		assertTrue(splay.remove(70));
		assertEquals(expected, splay.toString());

		// One child null
		expected = "20\n" +
				"    15\n" +
				"    80\n" +
				"        50\n" +
				"            40\n" +
				"            60\n" +
				"        90\n" +
				"            null\n" +
				"            100\n";
		assertTrue(splay.remove(10));
		assertEquals(expected, splay.toString());

		// If the node was not found, the last node encountered on the search path is splayed to the root.
		expected = "60\n" +
				"    20\n" +
				"        15\n" +
				"        50\n" +
				"            40\n" +
				"            null\n" +
				"    80\n" +
				"        null\n" +
				"        90\n" +
				"            null\n" +
				"            100\n";
		assertFalse(splay.remove(55));
		assertEquals(expected, splay.toString());

		// Removing the root
		expected = "50\n" +
				"    20\n" +
				"        15\n" +
				"        40\n" +
				"    80\n" +
				"        null\n" +
				"        90\n" +
				"            null\n" +
				"            100\n";
		splay.remove(60);
		assertEquals(expected, splay.toString());
	}

	@Test
	public void findElement() {
		String expected = "4\n" +
				"    null\n" +
				"    5\n" +
				"        null\n" +
				"        10\n" +
				"            6\n" +
				"            11\n";
		SplayTree<Integer> splay = new SplayTree<Integer>();
		assertNull(splay.findEntry(10));
		splay.addBST(10);
		splay.addBST(5);
		splay.addBST(11);
		splay.addBST(4);
		splay.addBST(6);
		assertEquals(4, (int) splay.findElement(4));
		assertEquals(expected, splay.toString());
		expected = "11\n" +
				"    4\n" +
				"        null\n" +
				"        10\n" +
				"            5\n" +
				"                null\n" +
				"                6\n" +
				"            null\n" +
				"    null\n";
		assertNull(splay.findElement(69));
		assertEquals(expected, splay.toString());
	}

	@Test
	public void findEntry() {
		String expected = "10\n" +
				"    5\n" +
				"        4\n" +
				"        6\n" +
				"    11\n";
		SplayTree<Integer> splay = new SplayTree<Integer>();
		assertNull(splay.findEntry(10));
		splay.addBST(10);
		splay.addBST(5);
		splay.addBST(11);
		splay.addBST(4);
		splay.addBST(6);
		assertEquals(4, (int) splay.findEntry(4).data);
		assertEquals(6, (int) splay.findEntry(6).data);
		assertEquals(11, (int) splay.findEntry(69).data);
		assertEquals("You should not splay for findEntry", expected, splay.toString());
	}

	@Test
	public void join() {
		String expected = "100\n" +
				"    50\n" +
				"        30\n" +
				"            10\n" +
				"                null\n" +
				"                20\n" +
				"                    15\n" +
				"                    null\n" +
				"            40\n" +
				"        90\n" +
				"            60\n" +
				"                null\n" +
				"                70\n" +
				"            null\n" +
				"    120\n" +
				"        110\n" +
				"        140\n";
		SplayTree<Integer> t1 = new SplayTree<Integer>();
		t1.addBST(50);
		t1.addBST(30);
		t1.addBST(60);
		t1.addBST(10);
		t1.addBST(40);
		t1.addBST(90);
		t1.addBST(20);
		t1.addBST(70);
		t1.addBST(100);
		t1.addBST(15);
		SplayTree<Integer> t2 = new SplayTree<Integer>();
		t2.addBST(120);
		t2.addBST(110);
		t2.addBST(140);

		SplayTree.Node root = t1.join(t1.root, t2.root);
		SplayTree<Integer> t3 = new SplayTree<>();
		t3.root = root;
		assertEquals(expected, t3.toString());
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
		String expected = "5\n" +
				"    4\n" +
				"    10\n" +
				"        6\n" +
				"        15\n" +
				"            11\n" +
				"            20\n";
		SplayTree<Integer> splay = new SplayTree<Integer>();
		splay.addBST(15);
		splay.addBST(10);
		splay.addBST(20);
		splay.addBST(5);
		splay.addBST(11);
		splay.addBST(4);
		splay.addBST(6);
		splay.zigZig(splay.root.left.left);
		assertEquals(expected, splay.toString());

		expected = "15\n" +
				"    10\n" +
				"        5\n" +
				"            4\n" +
				"            6\n" +
				"        11\n" +
				"    20\n";
		splay.zigZig(splay.root.right.right);
		assertEquals(expected, splay.toString());
	}

	@Test
	public void zigZag() {
		String expected = "15\n" +
				"    10\n" +
				"        5\n" +
				"        14\n" +
				"    20\n" +
				"        16\n" +
				"        30\n";
		SplayTree<Integer> splay = new SplayTree<Integer>();
		splay.addBST(20);
		splay.addBST(10);
		splay.addBST(30);
		splay.addBST(5);
		splay.addBST(15);
		splay.addBST(14);
		splay.addBST(16);
		splay.zigZag(splay.root.left.right);
		assertEquals(expected, splay.toString());
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

		try {
			si.next();
			fail("Should throw NoSuchElementException");
		} catch (NoSuchElementException e) {

		} catch (Exception e) {
			fail("Should throw NoSuchElementException");
		}

		// test remove
		String expected = "50\n" +
				"    30\n" +
				"        10\n" +
				"            null\n" +
				"            20\n" +
				"        35\n" +
				"            31\n" +
				"            37\n" +
				"    55\n" +
				"        53\n" +
				"        60\n" +
				"            null\n" +
				"            62\n";
		si = splay.iterator();
		si.next();
		si.next();
		si.remove();
		assertEquals(expected, splay.toString());
		assertEquals(30, si.next());

		// Remove when size == 1
		splay = new SplayTree<Integer>();
		si = splay.iterator();
		try {
			si.remove();
			fail("Should throw IllegalStateException");
		} catch (IllegalStateException e) {

		} catch (Exception e) {
			fail("Should throw IllegalStateException");
		}
		splay.add(1);
		si.remove();

	}

	@Test
	public void	cloneTreeRec() {
		SplayTree<Integer> splay = new SplayTree<Integer>();
		splay.addBST(50);
		splay.addBST(40);
		splay.addBST(60);
		splay.addBST(30);
		splay.addBST(45);
		splay.addBST(44);
		splay.addBST(46);
		SplayTree<Integer> splayClone = new SplayTree<Integer>(splay);
		assertEquals(splay.toString(), splayClone.toString());
		// Check to see if you remembered to copy the parent
		assertEquals(splay.root.left.parent.data, splayClone.root.left.parent.data);
		// Did you copy the size?
		assertEquals(7, splayClone.size());
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

		// When child.left is null!
		expected = "50\n" +
				"    30\n" +
				"        10\n" +
				"            null\n" +
				"            20\n" +
				"                15\n" +
				"                null\n" +
				"        40\n" +
				"    60\n" +
				"        null\n" +
				"        70\n" +
				"            null\n" +
				"            90\n" +
				"                null\n" +
				"                100\n";
		splay = new SplayTree<Integer>();
		splay.addBST(50);
		splay.addBST(30);
		splay.addBST(60);
		splay.addBST(10);
		splay.addBST(40);
		splay.addBST(20);
		splay.addBST(15);
		splay.addBST(90);
		splay.addBST(70);
		splay.addBST(100);
		splay.rightRotate(splay.root.right.right.left);
		assertEquals(expected, splay.toString());
	}

	@Test
	public void unlinkBST() {
		// Case 1: no children
		String expected = "11\n" +
				"    7\n" +
				"        null\n" +
				"        9\n" +
				"            8\n" +
				"            null\n" +
				"    14\n";
		SplayTree<Integer> splay = new SplayTree<Integer>();
		splay.addBST(11);
		splay.addBST(7);
		splay.addBST(14);
		splay.addBST(9);
		splay.addBST(8);
		splay.addBST(10);
		splay.unlinkBST(splay.root.left.right.right);
		assertEquals(expected, splay.toString());

		// Case 2: one child
		expected = "11\n" +
				"    9\n" +
				"        8\n" +
				"        10\n" +
				"    14\n";
		splay = new SplayTree<Integer>();
		splay.addBST(11);
		splay.addBST(7);
		splay.addBST(14);
		splay.addBST(9);
		splay.addBST(8);
		splay.addBST(10);
		splay.unlinkBST(splay.root.left);
		assertEquals(expected, splay.toString());

		// Case 3: two children
		expected = "15\n" +
				"    7\n" +
				"        2\n" +
				"            1\n" +
				"            3\n" +
				"        11\n" +
				"            9\n" +
				"                8\n" +
				"                10\n" +
				"            14\n" +
				"    20\n" +
				"        null\n" +
				"        25\n";
		splay = new SplayTree<Integer>();
		splay.addBST(15);
		splay.addBST(4);
		splay.addBST(20);
		splay.addBST(2);
		splay.addBST(11);
		splay.addBST(25);
		splay.addBST(1);
		splay.addBST(3);
		splay.addBST(7);
		splay.addBST(14);
		splay.addBST(9);
		splay.addBST(8);
		splay.addBST(10);
		splay.unlinkBST(splay.root.left);
		assertEquals(expected, splay.toString());
	}

	@Test
	public void successor() {
		SplayTree<Integer> splay = new SplayTree<Integer>();
		splay.addBST(15);
		splay.addBST(4);
		splay.addBST(20);
		splay.addBST(2);
		splay.addBST(11);
		splay.addBST(25);
		splay.addBST(1);
		splay.addBST(3);
		splay.addBST(7);
		splay.addBST(14);
		splay.addBST(9);
		splay.addBST(8);
		splay.addBST(10);
		assertEquals(7, (int) splay.successor(splay.findEntry(4)).data);
		assertEquals(25, (int) splay.successor(splay.findEntry(20)).data);
		assertEquals(2, (int) splay.successor(splay.findEntry(1)).data);
		assertEquals(15, (int) splay.successor(splay.findEntry(14)).data);
		assertNull(splay.successor(splay.findEntry(25)));
	}
}