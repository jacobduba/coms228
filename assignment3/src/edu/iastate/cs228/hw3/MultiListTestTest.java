package edu.iastate.cs228.hw3;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

/**
 * Author: Varun
 */
public class MultiListTestTest {

    @Test
    public void add() {
        MultiList<Character> list = new MultiList<>(4);
        list.add('A');
        Assert.assertEquals(1, list.size());
        Assert.assertEquals("[(A, -, -, -)]", list.toStringInternal());

        list.add('B');
        Assert.assertEquals(2, list.size());
        Assert.assertEquals("[(A, B, -, -)]", list.toStringInternal());

        list.add('C');
        Assert.assertEquals(3, list.size());
        Assert.assertEquals("[(A, B, C, -)]", list.toStringInternal());

        list.add('E');
        Assert.assertEquals(4, list.size());
        Assert.assertEquals("[(A, B, C, E)]", list.toStringInternal());

        list.add(3, 'D');
        Assert.assertEquals(5, list.size());
        Assert.assertEquals("[(A, B, -, -), (C, D, E, -)]", list.toStringInternal());

        list.add('V');
        Assert.assertEquals(6, list.size());
        Assert.assertEquals("[(A, B, -, -), (C, D, E, V)]", list.toStringInternal());

        list.add('W');
        Assert.assertEquals(7, list.size());
        Assert.assertEquals("[(A, B, -, -), (C, D, E, V), (W, -, -, -)]", list.toStringInternal());

        list.add(2, 'X');
        Assert.assertEquals(8, list.size());
        Assert.assertEquals("[(A, B, X, -), (C, D, E, V), (W, -, -, -)]", list.toStringInternal());

        list.add(2, 'Y');
        Assert.assertEquals(9, list.size());
        Assert.assertEquals("[(A, B, Y, X), (C, D, E, V), (W, -, -, -)]", list.toStringInternal());

        list.add(2, 'Z');
        Assert.assertEquals(10, list.size());
        Assert.assertEquals("[(A, B, Z, -), (Y, X, -, -), (C, D, E, V), (W, -, -, -)]", list.toStringInternal());
    }

    @Test
    public void testRemove() {
        MultiList<Character> list = new MultiList<>(4);
        list.add('A');
        list.add('B');
        list.add('C');
        list.add('E');
        list.add(3, 'D');
        list.add('V');
        list.add('W');
        list.add(2, 'X');
        list.add(2, 'Y');
        list.add(2, 'Z');

        list.remove(9);
        Assert.assertEquals(9, list.size());
        Assert.assertEquals("[(A, B, Z, -), (Y, X, -, -), (C, D, E, V)]", list.toStringInternal());

        list.remove(3);
        Assert.assertEquals(8, list.size());
        Assert.assertEquals("[(A, B, Z, -), (X, C, -, -), (D, E, V, -)]", list.toStringInternal());


        list.remove(3);
        Assert.assertEquals(7, list.size());
        Assert.assertEquals("[(A, B, Z, -), (C, D, -, -), (E, V, -, -)]", list.toStringInternal());

        list.remove(5);
        Assert.assertEquals(6, list.size());
        Assert.assertEquals("[(A, B, Z, -), (C, D, -, -), (V, -, -, -)]", list.toStringInternal());

        list.remove(3);
        Assert.assertEquals(5, list.size());
        Assert.assertEquals("[(A, B, Z, -), (D, V, -, -)]", list.toStringInternal());
    }

    @Test
    public void testSort() {
        MultiList<Integer> list = new MultiList<>();

        Random random = new Random(1);
        int limit = 10000;
        int n = 10;
        for (int i = 0; i < n; i++) {
            list.add(random.nextInt(limit));
        }

        list.sort();

        Iterator<Integer> iter = list.iterator();
        int prev = iter.next();
        while (iter.hasNext()) {
            int curr = iter.next();
            Assert.assertTrue(prev <= curr);
            prev = curr;
        }
    }

    @Test
    public void testSortReverse() {
        MultiList<Integer> list = new MultiList<>();

        Random random = new Random(1);
        int limit = 10000;
        int n = 10;
        for (int i = 0; i < n; i++) {
            list.add(random.nextInt(limit));
        }

        list.sortReverse();

        Iterator<Integer> iter = list.iterator();
        int prev = iter.next();
        while (iter.hasNext()) {
            int curr = iter.next();
            Assert.assertTrue(prev >= curr);
            prev = curr;
        }
    }

    @Test
    public void testIterator() {
        MultiList<Integer> list = new MultiList<Integer>();
        for (int i = 0; i<10; i++) {
            list.add(i);
        }
        ListIterator<Integer> listIterator = list.listIterator();
        for(int i = 0; i<11; i++) {
            if (i < 10) {
                Assert.assertTrue(listIterator.hasNext());
            }
            else {
                Assert.assertFalse(listIterator.hasNext());
                break;
            }
            Assert.assertEquals(i-1, listIterator.previousIndex());
            Assert.assertEquals(i, listIterator.nextIndex());
            Assert.assertEquals(Integer.valueOf(i), listIterator.next());
        }

        listIterator = list.listIterator(list.size());
        for(int i = 10; i>=0; i--) {
            if (i > 0) {
                Assert.assertTrue(listIterator.hasPrevious());
            }
            else {
                Assert.assertFalse(listIterator.hasPrevious());
                break;
            }
            Assert.assertEquals(i-1, listIterator.previousIndex());
            Assert.assertEquals(i, listIterator.nextIndex());
            Assert.assertEquals(Integer.valueOf(i - 1), listIterator.previous());
        }

        listIterator = list.listIterator();
        for(int i = 0; i<10; i++) {
            Assert.assertEquals(Integer.valueOf(i), listIterator.next());
        }
        Assert.assertFalse(listIterator.hasNext());
        for(int i = 10; i>0; i--) {
            Assert.assertEquals(Integer.valueOf(i - 1), listIterator.previous());
        }
        Assert.assertFalse(listIterator.hasPrevious());


        listIterator = list.listIterator();
        for(int i = 0; i<10; i++) {
            listIterator.set(listIterator.next() + 100);
        }
        listIterator = list.listIterator();
        for(int i = 0; i<10; i++) {
            Assert.assertEquals(Integer.valueOf(i + 100), listIterator.next());
        }

        list = new MultiList<>();
        listIterator = list.listIterator();
        for(int i = 0; i<10; i++) {
            listIterator.add(2*i);
            Assert.assertEquals(Integer.valueOf(2*i), list.get(i));
        }
        listIterator = list.listIterator();
        for(int i = 0; i<10; i++) {
            listIterator.next();
            listIterator.add(2*i+1);
            Assert.assertEquals(Integer.valueOf(2*i+1), list.get(2*i+1));
        }
        listIterator = list.listIterator();
        for(int i = 0; i<20; i++) {
            Assert.assertEquals(Integer.valueOf(i), listIterator.next());
        }

        listIterator = list.listIterator();
        for(int i = 0; i<10; i++) {
            listIterator.next();
            listIterator.next();
            listIterator.remove();
        }
        for(int i = 0; i<10; i++) {
            Assert.assertEquals(Integer.valueOf(2*i), list.get(i));
        }
        Assert.assertFalse(listIterator.hasNext());

        while(listIterator.hasPrevious()) {
            System.out.println(list.toStringInternal(listIterator));
            System.out.println(listIterator.previous());
            System.out.println(list.toStringInternal(listIterator));
            listIterator.remove();
        }

        Assert.assertFalse(listIterator.hasPrevious());
        Assert.assertFalse(listIterator.hasNext());
    }
}
