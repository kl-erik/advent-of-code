package year.year2025.day06;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day6Test extends TestCase {
    Day6 day = new Day6();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(4277556L, day.puzzle1(new File("src/test/resources/year2025/input_06.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals(3263827L, day.puzzle2(new File("src/test/resources/year2025/input_06.txt")));
    }
}
