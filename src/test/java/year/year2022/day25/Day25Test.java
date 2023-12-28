package year.year2022.day25;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day25Test extends TestCase {
    Day25 day = new Day25();

    public void testPuzzle1() {
        try {
            assertEquals("2=-1=0", day.puzzle1(new File("src/test/resources/year/year2022/example_25.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals("2=-1=0", day.puzzle2(new File("src/test/resources/year/year2022/example_25.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}