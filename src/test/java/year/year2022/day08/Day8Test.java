package year.year2022.day08;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day8Test extends TestCase {
    Day8 day = new Day8();

    public void testPuzzle1() {
        try {
            assertEquals(21, day.puzzle1(new File("src/test/resources/year2022/example_08.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(8, day.puzzle2(new File("src/test/resources/year2022/example_08.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}