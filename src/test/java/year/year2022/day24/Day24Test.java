package year.year2022.day24;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day24Test extends TestCase {
    Day24 day = new Day24();

    public void testPuzzle1() {
        try {
            assertEquals(18, day.puzzle1(new File("src/test/resources/year2022/example_24.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(54, day.puzzle2(new File("src/test/resources/year2022/example_24.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}