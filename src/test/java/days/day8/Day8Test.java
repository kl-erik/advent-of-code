package days.day8;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day8Test {
    Day8 day = new Day8();

    @Test
    void puzzle1() {
        try {
            assertEquals(21, day.puzzle1(new File("src/main/resources/example_8.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void puzzle2() {
        try {
            assertEquals(8, day.puzzle2(new File("src/main/resources/example_8.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}