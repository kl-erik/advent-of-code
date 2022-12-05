package days.day5;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {
    Day5 day = new Day5();

    @Test
    void testPuzzle1() {
        try {
            assertEquals("CMZ", day.puzzle1(new File("src/main/resources/example_5.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testPuzzle2() {
        try {
            assertEquals("MCD", day.puzzle2(new File("src/main/resources/example_5.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}