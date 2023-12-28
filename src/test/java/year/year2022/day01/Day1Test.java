package year.year2022.day01;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day1Test extends TestCase {
    Day1 day = new Day1();

    public void testPuzzle1() {
        try {
            assertEquals(24000, day.puzzle1(new File("src/test/resources/year/year2022/example_01.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(45000, day.puzzle2(new File("src/test/resources/year/year2022/example_01.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}