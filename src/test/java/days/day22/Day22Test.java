package days.day22;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day22Test {
    Day22 day = new Day22();

    @Test
    void puzzle1() {
        try {
            assertEquals(6032, day.puzzle1(new File("src/main/resources/example_22.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void puzzle2() {
        try {
            assertEquals(5031, day.puzzle2(new File("src/main/resources/example_22.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}