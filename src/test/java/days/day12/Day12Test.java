package days.day12;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {
    Day12 day = new Day12();

    @Test
    void puzzle1() {
        try {
            assertEquals(31, day.puzzle1(new File("src/main/resources/example_12.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void puzzle2() {
        try {
            assertEquals(29, day.puzzle2(new File("src/main/resources/example_12.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}