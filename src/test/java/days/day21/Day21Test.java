package days.day21;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day21Test {
    Day21 day = new Day21();

    @Test
    void puzzle1() {
        try {
            assertEquals(152L, day.puzzle1(new File("src/main/resources/example_21.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void puzzle2() {
        try {
            assertEquals(301L, day.puzzle2(new File("src/main/resources/example_21.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}