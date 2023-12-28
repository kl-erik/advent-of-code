package year.year2022.day12;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day12Test extends TestCase {
    Day12 day = new Day12();

    public void testPuzzle1() {
        try {
            assertEquals(31, day.puzzle1(new File("src/test/resources/year/year2022/example_12.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(29, day.puzzle2(new File("src/test/resources/year/year2022/example_12.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}