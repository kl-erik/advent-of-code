package year.year2022.day23;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day23Test extends TestCase {
    Day23 day = new Day23();

    public void testPuzzle1() {
        try {
            assertEquals(110, day.puzzle1(new File("src/test/resources/year/year2022/example_23.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(20, day.puzzle2(new File("src/test/resources/year/year2022/example_23.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}