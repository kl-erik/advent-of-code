package year.year2022.day09;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day9Test extends TestCase {
    Day9 day = new Day9();

    public void testPuzzle1() {
        try {
            assertEquals(13, day.puzzle1(new File("src/test/resources/year2022/example_09-1.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(36, day.puzzle2(new File("src/test/resources/year2022/example_09-2.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}