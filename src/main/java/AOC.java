import days.Day;
import days.day1.Day1;
import days.day10.Day10;
import days.day11.Day11;
import days.day12.Day12;
import days.day13.Day13;
import days.day14.Day14;
import days.day15.Day15;
import days.day16.Day16;
import days.day17.Day17;
import days.day18.Day18;
import days.day19.Day19;
import days.day2.Day2;
import days.day20.Day20;
import days.day21.Day21;
import days.day22.Day22;
import days.day23.Day23;
import days.day24.Day24;
import days.day3.Day3;
import days.day4.Day4;
import days.day5.Day5;
import days.day6.Day6;
import days.day7.Day7;
import days.day8.Day8;
import days.day9.Day9;

import java.io.File;
import java.io.FileNotFoundException;

public class AOC {
    public static void main(String[] args) {
        try {
            Day[] days = {new Day1(), new Day2(), new Day3(), new Day4(), new Day5(), new Day6(), new Day7(),
                    new Day8(), new Day9(), new Day10(), new Day11(), new Day12(), new Day13(), new Day14(),
                    new Day15(), new Day16(), new Day17(), new Day18(), new Day19(), new Day20(), new Day21(),
                    new Day22(), new Day23(), new Day24()};
            int day = 24;
            System.out.println(days[day - 1].puzzle1(new File("src/main/resources/input_" + day + ".txt")));
            System.out.println(days[day - 1].puzzle2(new File("src/main/resources/input_" + day + ".txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
