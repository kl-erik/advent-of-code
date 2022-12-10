package days.day10;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {
    Day10 day = new Day10();

    @Test
    void puzzle1() {
        try {
            assertEquals(13140, day.puzzle1(new File("src/main/resources/example_10.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void puzzle2() {
        try {
            day.puzzle2(new File("src/main/resources/example_10.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}