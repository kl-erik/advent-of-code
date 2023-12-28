package year.year2022.day22;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day22Test extends TestCase {
    Day22 day = new Day22();

    public void testPuzzle1() {
        try {
            assertEquals(6032, day.puzzle1(new File("src/test/resources/year/year2022/example_22.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(5031, day.puzzle2(new File("src/test/resources/year/year2022/example_22.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}