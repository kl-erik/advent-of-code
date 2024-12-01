package year.year2024.day01;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day1Test extends TestCase {
    private final Day1 day = new Day1();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(11, day.puzzle1(new File("src/test/resources/year2024/input_01.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals(31, day.puzzle2(new File("src/test/resources/year2024/input_01.txt")));
    }
}