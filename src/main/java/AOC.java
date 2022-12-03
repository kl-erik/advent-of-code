import days.Day1;

import java.io.File;
import java.io.FileNotFoundException;

public class AOC {
    public static void main(String[] args) {
        try {
            System.out.println(new Day1().puzzle1(new File("src/main/resources/input_1.txt")));
            System.out.println(new Day1().puzzle2(new File("src/main/resources/input_1.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
