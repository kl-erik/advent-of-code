package year.year2023.day08;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day8Test extends TestCase {
    Day8 day = new Day8();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(6, day.puzzle1(new File("src/test/resources/year/year2023/input_08-1.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals((long) 6, day.puzzle2(new File("src/test/resources/year/year2023/input_08-2.txt")));
    }
}