package year.year2024.day03;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day3Test extends TestCase {
    private final Day3 day = new Day3();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(161, day.puzzle1(new File("src/test/resources/year2024/input_03-1.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals(48, day.puzzle2(new File("src/test/resources/year2024/input_03-2.txt")));
    }
}