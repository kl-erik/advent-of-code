import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {
    @Test
    void testPuzzle1() {
        Day1 day = new Day1();
        assertEquals(24000, day.puzzle1(new File("src/main/resources/example_1.txt")));
    }
}