package year.year2023.day11;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day11Test extends TestCase {
    Day11 day = new Day11();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals((long) 374, day.puzzle1(new File("src/test/resources/year/year2023/input_11.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals((long) 8410, day.puzzle2Test(new File("src/test/resources/year/year2023/input_11.txt")));
    }
}