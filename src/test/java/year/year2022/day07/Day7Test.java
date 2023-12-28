package year.year2022.day07;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class Day7Test extends TestCase {
    Day7 day = new Day7();

    public void testPuzzle1() {
        try {
            assertEquals(95437, day.puzzle1(new File("src/test/resources/year/year2022/example_07.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPuzzle2() {
        try {
            assertEquals(24933642, day.puzzle2(new File("src/test/resources/year/year2022/example_07.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}