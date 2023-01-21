package days.day20;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day20Test {
    Day20 day = new Day20();

    @Test
    void puzzle1() {
        try {
            assertEquals(3L, day.puzzle1(new File("src/main/resources/example_20.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void puzzle2() {
        try {
            assertEquals(1623178306L, day.puzzle2(new File("src/main/resources/example_20.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}