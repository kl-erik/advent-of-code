package year.year2023.day03;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day3Test extends TestCase {
    Day3 day = new Day3();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(4361, day.puzzle1(new File("src/test/resources/year/year2023/input_03.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals(467835, day.puzzle2(new File("src/test/resources/year/year2023/input_03.txt")));
    }
}