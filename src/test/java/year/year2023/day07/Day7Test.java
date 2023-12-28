package year.year2023.day07;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day7Test extends TestCase {
    Day7 day = new Day7();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(6440, day.puzzle1(new File("src/test/resources/year/year2023/input_07.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals(5905, day.puzzle2(new File("src/test/resources/year/year2023/input_07.txt")));
    }
}