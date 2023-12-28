package year.year2022.day21;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day21Test extends TestCase {
    Day21 day = new Day21();

    public void testPuzzle1() {
        try {
            assertEquals(152L, day.puzzle1(new File("src/test/resources/year/year2022/example_21.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(301L, day.puzzle2(new File("src/test/resources/year/year2022/example_21.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}