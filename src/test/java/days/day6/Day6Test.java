package days.day6;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {
    Day6 day = new Day6();

    @Test
    void testPuzzle1() {
        try {
            assertEquals(7, day.puzzle1(new File("src/main/resources/example_6.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testPuzzle2() {
        try {
            assertEquals(19, day.puzzle2(new File("src/main/resources/example_6.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
