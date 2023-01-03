package days.day17;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day17Test {
    Day17 day = new Day17();

    @Test
    void puzzle1() {
        try {
            assertEquals(3068, day.puzzle1(new File("src/main/resources/example_17.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void puzzle2() {
        try {
            assertEquals(1514285714288L, day.puzzle2(new File("src/main/resources/example_17.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}