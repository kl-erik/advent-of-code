package days.day13;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day13Test {
    Day13 day = new Day13();

    @Test
    void puzzle1() {
        try {
            assertEquals(13, day.puzzle1(new File("src/main/resources/example_13.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void puzzle2() {
        try {
            assertEquals(140, day.puzzle2(new File("src/main/resources/example_13.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}