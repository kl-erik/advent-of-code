package year.year2024.day07;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day7Test extends TestCase {
    Day7 day = new Day7();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(3749L, day.puzzle1(new File("src/test/resources/year2024/input_07.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals(11387L, day.puzzle2(new File("src/test/resources/year2024/input_07.txt")));
    }
}