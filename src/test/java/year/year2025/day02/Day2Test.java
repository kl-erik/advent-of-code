package year.year2025.day02;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day2Test extends TestCase {
    Day2 day = new Day2();

    public void testPuzzle1() throws FileNotFoundException {
        assertEquals(1227775554L, day.puzzle1(new File("src/test/resources/year2025/input_02.txt")));
    }

    public void testPuzzle2() throws FileNotFoundException {
        assertEquals(4174379265L, day.puzzle2(new File("src/test/resources/year2025/input_02.txt")));
    }
}