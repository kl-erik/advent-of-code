package year.year2024.day06;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day6Test extends TestCase {
    Day6 day = new Day6();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(41, day.puzzle1(new File("src/test/resources/year2024/input_06.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals(6, day.puzzle2(new File("src/test/resources/year2024/input_06.txt")));
    }
}