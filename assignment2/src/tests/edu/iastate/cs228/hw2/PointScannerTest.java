package edu.iastate.cs228.hw2;

import edu.iastate.cs228.hw2.Algorithm;
import edu.iastate.cs228.hw2.PointScanner;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import static org.junit.Assert.*;

public class PointScannerTest {
    private String path = new File("").getAbsolutePath();

    @Test
    public void constructorTest1() throws FileNotFoundException {
        PointScanner p = new PointScanner("example1.txt", Algorithm.SelectionSort);
        p.scan();
        System.out.println(p.stats());
        System.out.println("SelectionSort     1000  49631547");
        assertNotNull(p.stats());
    }

    @Test
    public void writeMCPtoFileTest() throws FileNotFoundException {
        // Should be manually checked.
        PointScanner p = new PointScanner("example1.txt", Algorithm.SelectionSort);
        p.scan();
        p.writeMCPToFile();
    }
}