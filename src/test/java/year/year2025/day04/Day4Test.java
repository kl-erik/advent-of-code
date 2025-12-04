package year.year2025.day04;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day4Test extends TestCase {
    Day4 day = new Day4();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(13, day.puzzle1(new File("src/test/resources/year2025/input_04.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals(43, day.puzzle2(new File("src/test/resources/year2025/input_04.txt")));
    }
}
