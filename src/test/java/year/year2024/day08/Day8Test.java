package year.year2024.day08;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day8Test extends TestCase {
    Day8 day = new Day8();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(14, day.puzzle1(new File("src/test/resources/year2024/input_08.txt")));
    }

    public void testPuzzle2() {
    }
}