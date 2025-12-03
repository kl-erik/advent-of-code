package year.year2025.day03;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day3Test extends TestCase {
    Day3 day = new Day3();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(357, day.puzzle1(new File("src/test/resources/year2025/input_03.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals(3121910778619L, day.puzzle2(new File("src/test/resources/year2025/input_03.txt")));
    }
}