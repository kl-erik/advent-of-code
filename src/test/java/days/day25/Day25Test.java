package days.day25;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day25Test {
    Day25 day = new Day25();

    @Test
    void puzzle1() {
        try {
            assertEquals("2=-1=0", day.puzzle1(new File("src/main/resources/example_25.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void puzzle2() {
        try {
            assertEquals("2=-1=0", day.puzzle2(new File("src/main/resources/example_25.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}