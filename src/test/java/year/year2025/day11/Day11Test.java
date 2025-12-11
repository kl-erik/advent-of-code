package year.year2025.day11;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day11Test extends TestCase {
    Day11 day = new Day11();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(5, day.puzzle1(new File("src/test/resources/year2025/input_11.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        // assertEquals(null, day.puzzle2(new File("src/test/resources/year2025/input_11.txt")));
    }
}
