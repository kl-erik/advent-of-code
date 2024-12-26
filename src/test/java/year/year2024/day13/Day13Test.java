package year.year2024.day13;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day13Test extends TestCase {
    Day13 day = new Day13();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(480, day.puzzle1(new File("src/test/resources/year2024/input_13.txt")));
    }

    public void testPuzzle2() {
    }
}