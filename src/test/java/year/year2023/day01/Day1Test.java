package year.year2023.day01;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day1Test extends TestCase {
    private final Day1 day = new Day1();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(142, day.puzzle1(new File("src/test/resources/year2023/input_01-1.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals(281, day.puzzle2(new File("src/test/resources/year2023/input_01-2.txt")));
    }
}