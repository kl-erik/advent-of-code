package year.year2022.day06;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day6Test extends TestCase {
    Day6 day = new Day6();

    public void testPuzzle1() {
        try {
            assertEquals(7, day.puzzle1(new File("src/test/resources/year/year2022/example_06.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(19, day.puzzle2(new File("src/test/resources/year/year2022/example_06.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
