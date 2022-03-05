package edu.iastate.cs228.hw2;

import edu.iastate.cs228.hw2.Algorithm;
import edu.iastate.cs228.hw2.PointScanner;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class QuickSorterTest {
    @Test
    public void quickSortTest() throws FileNotFoundException {
        PointScanner p = new PointScanner("example1.txt", Algorithm.QuickSort);
        p.scan();
        String expected = "MCP: (0, 1)";
        String actual = p.toString();
        assertEquals(expected, actual);
    }
}