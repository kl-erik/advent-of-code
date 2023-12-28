package year.year2022.day05;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day5Test extends TestCase {
    Day5 day = new Day5();

    public void testPuzzle1() {
        try {
            assertEquals("CMZ", day.puzzle1(new File("src/test/resources/year2022/example_05.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals("MCD", day.puzzle2(new File("src/test/resources/year2022/example_05.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}