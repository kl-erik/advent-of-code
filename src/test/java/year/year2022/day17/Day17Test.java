package year.year2022.day17;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day17Test extends TestCase {
    Day17 day = new Day17();

    public void testPuzzle1() {
        try {
            assertEquals(3068, day.puzzle1(new File("src/test/resources/year/year2022/example_17.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(1514285714288L, day.puzzle2(new File("src/test/resources/year/year2022/example_17.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}