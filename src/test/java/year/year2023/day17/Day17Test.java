package year.year2023.day17;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day17Test extends TestCase {
    Day17 day = new Day17();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(102, day.puzzle1(new File("src/test/resources/year2023/input_17.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        // assertEquals(51, day.puzzle2(new File("src/test/resources/year2023/input_17.txt")));
    }
}