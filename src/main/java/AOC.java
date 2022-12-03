import days.day1.Day1;
import days.day2.Day2;

import java.io.File;
import java.io.FileNotFoundException;

public class AOC {
    public static void main(String[] args) {
        try {
            /*Day1 day1 = new Day1();
            System.out.println(day1.puzzle1(new File("src/main/resources/input_1.txt")));
            System.out.println(day1.puzzle2(new File("src/main/resources/input_1.txt")));*/

            Day2 day2 = new Day2();
            System.out.println(day2.puzzle1(new File("src/main/resources/input_2.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
