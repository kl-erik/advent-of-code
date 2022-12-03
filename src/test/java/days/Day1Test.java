package days;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {
    Day1 day = new Day1();

    @Test
    void testPuzzle1() {
        try {
            assertEquals(24000, day.puzzle1(new File("src/main/resources/example_1.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testPuzzle2() {
        try {
            assertEquals(45000, day.puzzle2(new File("src/main/resources/example_1.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}