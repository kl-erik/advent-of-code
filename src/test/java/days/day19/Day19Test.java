package days.day19;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day19Test {
    Day19 day = new Day19();

    @Test
    void puzzle1() {
        try {
            assertEquals(33, day.puzzle1(new File("src/main/resources/example_19.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void puzzle2() {
        try {
            assertEquals(56 * 62, day.puzzle2(new File("src/main/resources/example_19.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}