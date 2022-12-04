package days.day4;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {
    Day4 day = new Day4();

    @Test
    void testPuzzle1() {
        try {
            assertEquals(2, day.puzzle1(new File("src/main/resources/example_4.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testPuzzle2() {
        try {
            assertEquals(4, day.puzzle2(new File("src/main/resources/example_4.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}