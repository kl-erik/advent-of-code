package year.year2024.day05;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day5Test extends TestCase {
    Day5 day = new Day5();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(143, day.puzzle1(new File("src/test/resources/year2024/input_05.txt")));
    }

    public void testPuzzle2() {
    }
}