package days.day24;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day24Test {
    Day24 day = new Day24();

    @Test
    void puzzle1() {
        try {
            assertEquals(18, day.puzzle1(new File("src/main/resources/example_24.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void puzzle2() {
        try {
            assertEquals(54, day.puzzle2(new File("src/main/resources/example_24.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}