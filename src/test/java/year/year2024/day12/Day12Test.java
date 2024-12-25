package year.year2024.day12;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day12Test extends TestCase {
    Day12 day = new Day12();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(1930, day.puzzle1(new File("src/test/resources/year2024/input_12.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals(1206, day.puzzle2(new File("src/test/resources/year2024/input_12.txt")));
    }
}