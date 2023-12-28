package year.year2022.day19;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day19Test extends TestCase {
    Day19 day = new Day19();

    public void testPuzzle1() {
        try {
            assertEquals(33, day.puzzle1(new File("src/test/resources/year2022/example_19.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /*public void testPuzzle2() {
        try {
            assertEquals(56 * 62, day.puzzle2(new File("src/test/resources/year2022/example_19.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }*/
}