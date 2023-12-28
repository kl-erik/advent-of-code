package year.year2022.day03;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day3Test extends TestCase {
    Day3 day = new Day3();

    public void testPuzzle1() {
        try {
            assertEquals(157, day.puzzle1(new File("src/test/resources/year2022/example_03.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(70, day.puzzle2(new File("src/test/resources/year2022/example_03.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}