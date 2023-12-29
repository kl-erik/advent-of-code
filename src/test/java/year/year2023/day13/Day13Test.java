package year.year2023.day13;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day13Test extends TestCase {
    Day13 day = new Day13();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(405, day.puzzle1(new File("src/test/resources/year2023/input_13.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals(400, day.puzzle2(new File("src/test/resources/year2023/input_13.txt")));
    }
}