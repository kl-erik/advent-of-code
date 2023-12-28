package year.year2022.day10;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day10Test extends TestCase {
    Day10 day = new Day10();

    public void testPuzzle1() {
        try {
            assertEquals(13140, day.puzzle1(new File("src/test/resources/year/year2022/example_10.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            day.puzzle2(new File("src/test/resources/year/year2022/example_10.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}