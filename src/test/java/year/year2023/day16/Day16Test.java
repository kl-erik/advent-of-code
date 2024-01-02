package year.year2023.day16;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day16Test extends TestCase {
    Day16 day = new Day16();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(46, day.puzzle1(new File("src/test/resources/year2023/input_16.txt")));
    }

    public void testPuzzle2() {
        // assertEquals(1320, day.puzzle2(new File("src/test/resources/year2023/input_16.txt")));
    }
}