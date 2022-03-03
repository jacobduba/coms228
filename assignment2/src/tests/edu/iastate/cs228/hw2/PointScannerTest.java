package edu.iastate.cs228.hw2;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class PointScannerTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void constructorTest1() throws FileNotFoundException {
        PointScanner p = new PointScanner("example1.txt", Algorithm.SelectionSort);
        p.scan();
        System.out.println(p.stats());
        System.out.println("SelectionSort     1000  49631547");
        assertNotNull(p.stats());
    }

    @Test
    public void statsTest() {
    }
}