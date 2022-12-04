package days.day2;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Test {
    Day2 day = new Day2();

    @Test
    void testPuzzle1() {
        try {
            assertEquals(15, day.puzzle1(new File("src/main/resources/example_2.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testPuzzle2() {
        try {
            assertEquals(12, day.puzzle2(new File("src/main/resources/example_2.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
