package year.year2022.day04;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day4Test extends TestCase {
    Day4 day = new Day4();

    public void testPuzzle1() {
        try {
            assertEquals(2, day.puzzle1(new File("src/test/resources/year2022/example_04.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(4, day.puzzle2(new File("src/test/resources/year2022/example_04.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
