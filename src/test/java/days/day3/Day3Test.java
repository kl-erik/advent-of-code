package days.day3;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {
    Day3 day = new Day3();

    @Test
    void testPuzzle1() {
        try {
            assertEquals(157, day.puzzle1(new File("src/main/resources/example_3.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testPuzzle2() {
        try {
            assertEquals(70, day.puzzle2(new File("src/main/resources/example_3.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}