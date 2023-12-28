package year.year2023.day02;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day2Test extends TestCase {
    private final Day2 day = new Day2();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(8, day.puzzle1(new File("src/test/resources/year/year2023/input_02.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals(2286, day.puzzle2(new File("src/test/resources/year/year2023/input_02.txt")));
    }
}