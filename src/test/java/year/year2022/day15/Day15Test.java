package year.year2022.day15;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day15Test extends TestCase {
    Day15 day = new Day15();

    public void testPuzzle1() {
        try {
            assertEquals(26, day.puzzle1(new File("src/test/resources/year/year2022/example_15.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(56000011L, day.puzzle2(new File("src/test/resources/year/year2022/example_15.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}