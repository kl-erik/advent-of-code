package year.year2024.day10;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day10Test extends TestCase {
    Day10 day = new Day10();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(36, day.puzzle1(new File("src/test/resources/year2024/input_10.txt")));
    }

    public void testPuzzle2() {
    }
}