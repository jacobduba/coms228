package edu.iastate.cs228.hw3.tests;


import com.sun.org.apache.xpath.internal.operations.Mult;
import edu.iastate.cs228.hw3.MultiList;
import org.junit.Before;
import org.junit.Test;

import java.util.ListIterator;

import static org.junit.Assert.assertEquals;

/**
 * MultiListTest
 * author: Jacob Duba
 */

public class MultiListTest {
    MultiList<Integer> ml;
    ListIterator<Integer> iMl;
    ListIterator<Integer> iMl2;

    @Before
    public void setup() {
        ml = new MultiList<Integer>();
        ml.add(1);
        ml.add(2);
        ml.add(3);
        ml.add(4);
        ml.add(5);
        ml.add(6);
        iMl = ml.listIterator();
    }

    @Test
    public void addTest() {
        assertEquals("[1, 2, 3, 4, 5, 6]", ml.toString());
        assertEquals("[(1, 2, 3, 4), (5, 6, -, -)]", ml.toStringInternal());
    }

    @Test
    public void iteratorNextPreviousTest() {
        assertEquals(1, (int) iMl.next());
        assertEquals("[(1, | 2, 3, 4), (5, 6, -, -)]", ml.toStringInternal(iMl));
        iMl.next();
        iMl.next();
        iMl.next();
        assertEquals(4, (int) iMl.previous());
        iMl.next();
        iMl.next();
        iMl.previous();
        assertEquals(4, (int) iMl.previous());
    }

    @Test
    public void iteratorSetTest() {
        iMl.next();
        iMl.next();
        iMl.set(9);
        System.out.println(ml.toStringInternal(iMl));
        assertEquals(9, (int) iMl.previous());
        iMl.next();
        iMl.next();
        iMl.next();
        iMl.set(69);
        iMl.set(10);
        assertEquals(10, (int) iMl.previous());
    }

    @Test
    public void getTest() {
        assertEquals(4, (int) ml.get(3));
        assertEquals(5, (int) ml.get(4));
        assertEquals(6, (int) ml.get(5));
    }

    @Test
    public void addAtPosTest() {
        // Test using the examples given in section 5.2
        MultiList<Character> example = new MultiList<>();
        example.add('A');
        example.add('B');
        example.add('C');
        example.add('E');
        example.add(3, 'D');
        assertEquals("[(A, B, -, -), (C, D, E, -)]", example.toStringInternal());
        example.add('V');
        assertEquals("[(A, B, -, -), (C, D, E, V)]", example.toStringInternal());
        example.add('W');
        assertEquals("[(A, B, -, -), (C, D, E, V), (W, -, -, -)]", example.toStringInternal());
        example.add(2, 'X');
        assertEquals("[(A, B, X, -), (C, D, E, V), (W, -, -, -)]", example.toStringInternal());
        example.add(2, 'Y');
        assertEquals("[(A, B, Y, X), (C, D, E, V), (W, -, -, -)]", example.toStringInternal());
        example.add(2, 'Z');
        assertEquals("[(A, B, Z, -), (Y, X, -, -), (C, D, E, V), (W, -, -, -)]", example.toStringInternal());
    }

    @Test
    public void removeAtPosTest() {
        // Test using the examples given in section 5.2
        MultiList<Character> example = new MultiList<>();
        example.add('A');
        example.add('B');
        example.add('Y');
        example.add('X');
        example.add('C');
        example.add('D');
        example.add('E');
        example.add('V');
        example.add('W');
        example.add(2, 'Z');
        assertEquals("[(A, B, Z, -), (Y, X, -, -), (C, D, E, V), (W, -, -, -)]", example.toStringInternal());
        assertEquals((Character) 'W', example.remove(9));
        assertEquals("[(A, B, Z, -), (Y, X, -, -), (C, D, E, V)]", example.toStringInternal());
        assertEquals((Character) 'Y', example.remove(3));
        assertEquals("[(A, B, Z, -), (X, C, -, -), (D, E, V, -)]", example.toStringInternal());
        assertEquals((Character) 'X', example.remove(3));
        assertEquals("[(A, B, Z, -), (C, D, -, -), (E, V, -, -)]", example.toStringInternal());
        assertEquals((Character) 'E', example.remove(5));
        assertEquals("[(A, B, Z, -), (C, D, -, -), (V, -, -, -)]", example.toStringInternal());
        assertEquals((Character) 'C', example.remove(3));
        assertEquals("[(A, B, Z, -), (D, V, -, -)]", example.toStringInternal());
    }

    @Test
    public void addIteratorTest() {
        iMl.next();
        assertEquals("[(1, | 2, 3, 4), (5, 6, -, -)]", ml.toStringInternal(iMl));
        iMl.add(69);
        assertEquals("[(1, 69, | 2, -), (3, 4, -, -), (5, 6, -, -)]", ml.toStringInternal(iMl));
    }

    @Test
    public void removeIteratorTest() {
        iMl.next();
        iMl.next();
        iMl.next();
        iMl.next();
        iMl.next();
        iMl.remove();
        iMl.next();
        iMl.remove();
        assertEquals("[(1, 2, 3, 4)]", ml.toStringInternal());
    }
}