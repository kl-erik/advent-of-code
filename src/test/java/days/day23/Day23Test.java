package days.day23;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day23Test {
    Day23 day = new Day23();

    @Test
    void puzzle1() {
        try {
            assertEquals(110, day.puzzle1(new File("src/main/resources/example_23.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void puzzle2() {
        try {
            assertEquals(20, day.puzzle2(new File("src/main/resources/example_23.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}