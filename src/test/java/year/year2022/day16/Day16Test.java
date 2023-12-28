package year.year2022.day16;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day16Test extends TestCase {
    Day16 day = new Day16();

    public void testPuzzle1() {
        try {
            assertEquals(1651, day.puzzle1(new File("src/test/resources/year2022/example_16.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(1707, day.puzzle2(new File("src/test/resources/year2022/example_16.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}