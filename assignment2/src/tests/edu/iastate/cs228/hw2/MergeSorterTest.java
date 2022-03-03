package edu.iastate.cs228.hw2;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class MergeSorterTest {
    @Test
    public void mergeSortTest() throws FileNotFoundException {
        PointScanner p = new PointScanner("example1.txt", Algorithm.MergeSort);
        p.scan();
        String expected = "MCP: (0, 1)";
        String actual = p.toString();
        assertEquals(expected, actual);
    }
}