package year.year2023.day05;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day5Test extends TestCase {
    Day5 day = new Day5();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals((long) 35, day.puzzle1(new File("src/test/resources/year2023/input_05.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals((long) 46, day.puzzle2(new File("src/test/resources/year2023/input_05.txt")));
    }
}