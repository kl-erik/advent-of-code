package year.year2023.day12;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day12Test extends TestCase {
    Day12 day = new Day12();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(21, day.puzzle1(new File("src/test/resources/year/year2023/input_12.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals((long) 525152, day.puzzle2(new File("src/test/resources/year/year2023/input_12.txt")));
    }
}