package days.day11;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {
    Day11 day = new Day11();

    @Test
    void puzzle1() {
        try {
            assertEquals(10605, day.puzzle1(new File("src/main/resources/example_11.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void puzzle2() {
        try {
            day.puzzle2(new File("src/main/resources/example_11.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}