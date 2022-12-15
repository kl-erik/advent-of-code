package days.day15;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day15Test {
    Day15 day = new Day15();

    @Test
    void puzzle1() {
        try {
            assertEquals(26, day.puzzle1(new File("src/main/resources/example_15.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void puzzle2() {
        try {
            assertEquals(56000011L, day.puzzle2(new File("src/main/resources/example_15.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}