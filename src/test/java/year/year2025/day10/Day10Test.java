package year.year2025.day10;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day10Test extends TestCase {
    Day10 day = new Day10();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(7, day.puzzle1(new File("src/test/resources/year2025/input_10.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        // assertEquals(null, day.puzzle2(new File("src/test/resources/year2025/input_10.txt")));
    }
}
