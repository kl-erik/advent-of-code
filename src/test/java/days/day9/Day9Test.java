package days.day9;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day9Test {
    Day9 day = new Day9();

    @Test
    void puzzle1() {
        try {
            assertEquals(13, day.puzzle1(new File("src/main/resources/example_9-1.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void puzzle2() {
        try {
            assertEquals(36, day.puzzle2(new File("src/main/resources/example_9-2.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}