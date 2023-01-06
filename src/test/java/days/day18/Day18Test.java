package days.day18;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day18Test {
    Day18 day = new Day18();

    @Test
    void puzzle1() {
        try {
            assertEquals(64, day.puzzle1(new File("src/main/resources/example_18.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void puzzle2() {
        try {
            assertEquals(58, day.puzzle2(new File("src/main/resources/example_18.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}