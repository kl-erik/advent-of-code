package year.year2022.day11;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day11Test extends TestCase {
    Day11 day = new Day11();

    public void testPuzzle1() {
        try {
            assertEquals(10605L, day.puzzle1(new File("src/test/resources/year/year2022/example_11.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(2713310158L, day.puzzle2(new File("src/test/resources/year/year2022/example_11.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}