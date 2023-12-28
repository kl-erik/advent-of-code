package year.year2022.day14;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day14Test extends TestCase {
    Day14 day = new Day14();

    public void testPuzzle1() {
        try {
            assertEquals(24, day.puzzle1(new File("src/test/resources/year/year2022/example_14.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(93, day.puzzle2(new File("src/test/resources/year/year2022/example_14.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}