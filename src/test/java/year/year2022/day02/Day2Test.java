package year.year2022.day02;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day2Test extends TestCase {
    Day2 day = new Day2();

    public void testPuzzle1() {
        try {
            assertEquals(15, day.puzzle1(new File("src/test/resources/year2022/example_02.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(12, day.puzzle2(new File("src/test/resources/year2022/example_02.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
