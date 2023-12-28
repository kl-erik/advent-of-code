package year.year2023.day06;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day6Test extends TestCase {
    Day6 day = new Day6();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(288, day.puzzle1(new File("src/test/resources/year2023/input_06.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals(71503, day.puzzle2(new File("src/test/resources/year2023/input_06.txt")));
    }
}