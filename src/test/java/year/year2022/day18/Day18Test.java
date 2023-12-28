package year.year2022.day18;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day18Test extends TestCase {
    Day18 day = new Day18();

    public void testPuzzle1() {
        try {
            assertEquals(64, day.puzzle1(new File("src/test/resources/year2022/example_18.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(58, day.puzzle2(new File("src/test/resources/year2022/example_18.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}