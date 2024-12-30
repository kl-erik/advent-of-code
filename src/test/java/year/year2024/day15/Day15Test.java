package year.year2024.day15;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day15Test extends TestCase {
    Day15 day = new Day15();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(2028, day.puzzle1(new File("src/test/resources/year2024/input_15.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        // assertEquals(null, day.puzzle2(new File("src/test/resources/year2024/input_15.txt")));
    }
}
