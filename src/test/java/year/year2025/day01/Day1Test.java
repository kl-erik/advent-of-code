package year.year2025.day01;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day1Test extends TestCase {
    Day1 day = new Day1();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(3, day.puzzle1(new File("src/test/resources/year2025/input_01.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals(0, day.puzzle2(new File("src/test/resources/year2025/input_01.txt")));
    }
}