package year.year2022.day20;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day20Test extends TestCase {
    Day20 day = new Day20();

    public void testPuzzle1() {
        try {
            assertEquals(3L, day.puzzle1(new File("src/test/resources/year2022/example_20.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(1623178306L, day.puzzle2(new File("src/test/resources/year2022/example_20.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}