import days.Day;
import days.day1.Day1;
import days.day2.Day2;
import days.day3.Day3;
import days.day4.Day4;
import days.day5.Day5;
import days.day6.Day6;
import days.day7.Day7;
import days.day8.Day8;

import java.io.File;
import java.io.FileNotFoundException;

public class AOC {
    public static void main(String[] args) {
        try {
            Day[] days = {new Day1(), new Day2(), new Day3(), new Day4(), new Day5(), new Day6(), new Day7(), new Day8()};
            int day = 8;
            System.out.println(days[day - 1].puzzle1(new File("src/main/resources/input_" + day + ".txt")));
            System.out.println(days[day - 1].puzzle2(new File("src/main/resources/input_" + day + ".txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
