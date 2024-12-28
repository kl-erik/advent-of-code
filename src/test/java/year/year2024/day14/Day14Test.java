package year.year2024.day14;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day14Test extends TestCase {
    Day14 day = new Day14();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(12, day.puzzle1(new File("src/test/resources/year2024/input_14.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        day.puzzle2(new File("src/test/resources/year2024/input_14.txt"));
    }
}