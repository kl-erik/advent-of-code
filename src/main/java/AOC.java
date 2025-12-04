import year.Day;

import java.io.File;
import java.io.FileNotFoundException;

public class AOC {
    public static void main(String[] args) throws FileNotFoundException {
        Day[][] aoc = new Day[4][];
        aoc[0] = new Day[]{
                new year.year2022.day01.Day1(), new year.year2022.day02.Day2(), new year.year2022.day03.Day3(),
                new year.year2022.day04.Day4(), new year.year2022.day05.Day5(), new year.year2022.day06.Day6(),
                new year.year2022.day07.Day7(), new year.year2022.day08.Day8(), new year.year2022.day09.Day9(),
                new year.year2022.day10.Day10(), new year.year2022.day11.Day11(), new year.year2022.day12.Day12(),
                new year.year2022.day13.Day13(), new year.year2022.day14.Day14(), new year.year2022.day15.Day15(),
                new year.year2022.day16.Day16(), new year.year2022.day17.Day17(), new year.year2022.day18.Day18(),
                new year.year2022.day19.Day19(), new year.year2022.day20.Day20(), new year.year2022.day21.Day21(),
                new year.year2022.day22.Day22(), new year.year2022.day23.Day23(), new year.year2022.day24.Day24(),
                new year.year2022.day25.Day25()
        };
        aoc[1] = new Day[]{
                new year.year2023.day01.Day1(), new year.year2023.day02.Day2(), new year.year2023.day03.Day3(),
                new year.year2023.day04.Day4(), new year.year2023.day05.Day5(), new year.year2023.day06.Day6(),
                new year.year2023.day07.Day7(), new year.year2023.day08.Day8(), new year.year2023.day09.Day9(),
                new year.year2023.day10.Day10(), new year.year2023.day11.Day11(), new year.year2023.day12.Day12(),
                new year.year2023.day13.Day13(), new year.year2023.day14.Day14(), new year.year2023.day15.Day15(),
                new year.year2023.day16.Day16(), new year.year2023.day17.Day17()
        };
        aoc[2] = new Day[]{
                new year.year2024.day01.Day1(), new year.year2024.day02.Day2(), new year.year2024.day03.Day3(),
                new year.year2024.day04.Day4(), new year.year2024.day05.Day5(), new year.year2024.day06.Day6(),
                new year.year2024.day07.Day7(), new year.year2024.day08.Day8(), new year.year2024.day09.Day9(),
                new year.year2024.day10.Day10(), new year.year2024.day11.Day11(), new year.year2024.day12.Day12(),
                new year.year2024.day13.Day13(), new year.year2024.day14.Day14(), new year.year2024.day15.Day15(),
                new year.year2024.day16.Day16(), new year.year2024.day17.Day17()
        };
        aoc[3] = new Day[]{
                new year.year2025.day01.Day1(), new year.year2025.day02.Day2(), new year.year2025.day03.Day3(),
                new year.year2025.day04.Day4(), /*new year.year2025.day05.Day5(), new year.year2025.day06.Day6(),
                new year.year2025.day07.Day7(), new year.year2025.day08.Day8(), new year.year2025.day09.Day9(),
                new year.year2025.day10.Day10(), new year.year2025.day11.Day11(), new year.year2025.day12.Day12(),
                new year.year2025.day13.Day13(), new year.year2025.day14.Day14(), new year.year2025.day15.Day15(),
                new year.year2025.day16.Day16(), new year.year2025.day17.Day17()*/
        };

        int day = 4;
        int year = 2025;

        String path = "src/main/resources/year" + year + "/input_" + (day <= 9 ? "0" : "") + day + ".txt";
        // System.out.println(aoc[year - 2022][day - 1].puzzle1(new File(path)));
        System.out.println(aoc[year - 2022][day - 1].puzzle2(new File(path)));
    }
}
