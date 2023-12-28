package year.year2022.day13;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day13Test extends TestCase {
    Day13 day = new Day13();

    public void testPuzzle1() {
        try {
            assertEquals(13, day.puzzle1(new File("src/test/resources/year2022/example_13.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(140, day.puzzle2(new File("src/test/resources/year2022/example_13.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}