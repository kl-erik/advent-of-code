import year.Day;
import year.year2022.day01.Day1;
import year.year2022.day02.Day2;
import year.year2022.day03.Day3;
import year.year2022.day04.Day4;
import year.year2022.day05.Day5;
import year.year2022.day06.Day6;
import year.year2022.day07.Day7;
import year.year2022.day08.Day8;
import year.year2022.day09.Day9;
import year.year2022.day10.Day10;
import year.year2022.day11.Day11;
import year.year2022.day12.Day12;
import year.year2022.day13.Day13;
import year.year2022.day14.Day14;
import year.year2022.day15.Day15;
import year.year2022.day16.Day16;
import year.year2022.day17.Day17;
import year.year2022.day18.Day18;
import year.year2022.day19.Day19;
import year.year2022.day20.Day20;
import year.year2022.day21.Day21;
import year.year2022.day22.Day22;
import year.year2022.day23.Day23;
import year.year2022.day24.Day24;
import year.year2022.day25.Day25;

import java.io.File;
import java.io.FileNotFoundException;

public class AOC {
    public static void main(String[] args) {
        try {
            Day[] days = {new Day1(), new Day2(), new Day3(), new Day4(), new Day5(), new Day6(), new Day7(),
                    new Day8(), new Day9(), new Day10(), new Day11(), new Day12(), new Day13(), new Day14(),
                    new Day15(), new Day16(), new Day17(), new Day18(), new Day19(), new Day20(), new Day21(),
                    new Day22(), new Day23(), new Day24(), new Day25()};
            int day = 25;
            int year = 2022;
            System.out.println(days[day - 1].puzzle1(new File("src/main/resources/year/year" + year + "/input_" + day + ".txt")));
            System.out.println(days[day - 1].puzzle2(new File("src/main/resources/year/year" + year + "/input_" + day + ".txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
