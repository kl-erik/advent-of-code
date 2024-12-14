package year.year2024.day09;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day9Test extends TestCase {
    Day9 day = new Day9();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(1928L, day.puzzle1(new File("src/test/resources/year2024/input_09.txt")));
    }

    public void testPuzzle2() {
    }
}