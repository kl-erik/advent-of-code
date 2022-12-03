import days.Day;
import days.day1.Day1;
import days.day2.Day2;

import java.io.File;
import java.io.FileNotFoundException;

public class AOC {
    public static void main(String[] args) {
        try {
            Day[] days = {new Day1(), new Day2()};
            int day = 2;
            System.out.println(days[day - 1].puzzle1(new File("src/main/resources/input_" + day + ".txt")));
            System.out.println(days[day - 1].puzzle2(new File("src/main/resources/input_" + day + ".txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
