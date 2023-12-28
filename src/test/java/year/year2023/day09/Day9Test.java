package year.year2023.day09;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day9Test extends TestCase {
    Day9 day = new Day9();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(114, day.puzzle1(new File("src/test/resources/year/year2023/input_09.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals(2, day.puzzle2(new File("src/test/resources/year/year2023/input_09.txt")));
    }
}