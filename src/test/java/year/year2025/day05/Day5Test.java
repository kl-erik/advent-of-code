package year.year2025.day05;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day5Test extends TestCase {
    Day5 day = new Day5();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(3, day.puzzle1(new File("src/test/resources/year2025/input_05.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        // assertEquals(null, day.puzzle2(new File("src/test/resources/year2025/input_05.txt")));
    }
}
