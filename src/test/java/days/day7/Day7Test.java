package days.day7;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day7Test {
    Day7 day = new Day7();

    @Test
    void puzzle1() {
        try {
            assertEquals(95437, day.puzzle1(new File("src/main/resources/example_7.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void puzzle2() {
        try {
            assertEquals(24933642, day.puzzle2(new File("src/main/resources/example_7.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}