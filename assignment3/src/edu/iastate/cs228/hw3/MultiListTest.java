package edu.iastate.cs228.hw3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

class MultiListTest {
    MultiList<Integer> ml;
    ListIterator<Integer> iMl;

    @BeforeEach
    void beforeAll() {
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
    void addTest() {
        assertEquals("[1, 2, 3, 4, 5, 6]", ml.toString());
        assertEquals("[(| 1, 2, 3, 4), (5, 6, -, -)]", ml.toStringInternal());
    }

    @Test
    void iteratorNextPreviousTest() {
        assertEquals(1, iMl.next());
        assertEquals("[(1, | 2, 3, 4), (5, 6, -, -)]", ml.toStringInternal(iMl));
        iMl.next();
        iMl.next();
        iMl.next();
        assertEquals(4, iMl.previous());
        iMl.next();
        iMl.next();
        iMl.previous();
        assertEquals(4, iMl.previous());
    }

    @Test
    void iteratorSetTest() {
        iMl.next();
        iMl.next();
        iMl.set(9);
        assertEquals(9, iMl.previous());
        iMl.next();
        iMl.next();
        iMl.next();
        iMl.set(69);
        iMl.set(10);
        assertEquals(10, iMl.previous());
    }

    @Test
    void getTest() {
        assertEquals(4, ml.get(3));
        assertEquals(5, ml.get(4));
        assertEquals(6, ml.get(5));
    }
}