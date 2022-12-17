package days.day16;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day16Test {
    Day16 day = new Day16();

    @Test
    void puzzle1() {
        try {
            assertEquals(1651, day.puzzle1(new File("src/main/resources/example_16.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void puzzle2() {
        try {
            assertEquals(1707, day.puzzle2(new File("src/main/resources/example_16.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}