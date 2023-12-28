package year.year2023.day10;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day10Test extends TestCase {
    Day10 day = new Day10();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(8, day.puzzle1(new File("src/test/resources/year/year2023/input_10-1.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals(10, day.puzzle2(new File("src/test/resources/year/year2023/input_10-2.txt")));
    }
}