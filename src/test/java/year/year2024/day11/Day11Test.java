package year.year2024.day11;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day11Test extends TestCase {
    Day11 day = new Day11();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(55312, day.puzzle1(new File("src/test/resources/year2024/input_11.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals(55312L, day.puzzle2(new File("src/test/resources/year2024/input_11.txt")));
    }
}