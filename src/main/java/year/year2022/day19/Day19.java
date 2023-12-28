package year.year2022.day19;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.max;

public class Day19 implements Day {
    private int oreRobotOreCost;
    private int clayRobotOreCost;
    private int obsidianRobotOreCost;
    private int obsidianRobotClayCost;
    private int geodeRobotOreCost;
    private int geodeRobotObsidianCost;
    private int maxOreRobots;
    private int maxClayRobots;
    private int maxObsidianRobots;
    private int limit;

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        limit = 24;
        int qualitySum = 0;
        int id = 1;

        while (scanner.hasNextLine()) {
            parse(scanner.nextLine());
            qualitySum += id * getMaxGeodes(0, 0, 0, 0, 1, 0, 0, 0, 1, new ArrayList<>());
            id++;
        }

        return qualitySum;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        limit = 32;
        int i = 0;
        int[] geodes = new int[3];
        Arrays.fill(geodes, 1);

        while (scanner.hasNextLine() && i < 3) {
            parse(scanner.nextLine());
            geodes[i] = getMaxGeodes(0, 0, 0, 0, 1, 0, 0, 0, 1, new ArrayList<>());
            i++;
        }

        return geodes[0] * geodes[1] * geodes[2];
    }

    private int getMaxGeodes(int ore, int clay, int obsidian, int geode,
                             int oreRobots, int clayRobots, int obsidianRobots, int geodeRobots,
                             int minutes, ArrayList<String> skip) {
        if (minutes > limit)
            return geode;

        ore += oreRobots;
        clay += clayRobots;
        obsidian += obsidianRobots;
        geode += geodeRobots;

        int[] solutions = new int[5];

        if (!skip.contains("ore") && oreRobots < maxOreRobots && ore >= oreRobotOreCost) {
            solutions[0] = getMaxGeodes(ore - oreRobotOreCost - 1, clay, obsidian, geode,
                    oreRobots + 1, clayRobots, obsidianRobots, geodeRobots, minutes + 1, new ArrayList<>());
            skip.add("ore");
        }

        if (!skip.contains("clay") && clayRobots < maxClayRobots && ore >= clayRobotOreCost) {
            solutions[1] = getMaxGeodes(ore - clayRobotOreCost, clay - 1, obsidian, geode,
                    oreRobots, clayRobots + 1, obsidianRobots, geodeRobots, minutes + 1, new ArrayList<>());
            skip.add("clay");
        }

        if (!skip.contains("obsidian") && obsidianRobots < maxObsidianRobots && ore >= obsidianRobotOreCost && clay >= obsidianRobotClayCost) {
            solutions[2] = getMaxGeodes(ore - obsidianRobotOreCost, clay - obsidianRobotClayCost, obsidian - 1, geode,
                    oreRobots, clayRobots, obsidianRobots + 1, geodeRobots, minutes + 1, new ArrayList<>());
            skip.add("obsidian");
        }

       if (ore >= geodeRobotOreCost && obsidian >= geodeRobotObsidianCost) {
           solutions[3] = getMaxGeodes(ore - geodeRobotOreCost, clay, obsidian - geodeRobotObsidianCost, geode - 1,
                   oreRobots, clayRobots, obsidianRobots, geodeRobots + 1, minutes + 1, new ArrayList<>());
        } else {
           solutions[4] = getMaxGeodes(ore, clay, obsidian, geode,
                    oreRobots, clayRobots, obsidianRobots, geodeRobots, minutes + 1, skip);
        }

        int max = 0;

        for (int solution : solutions)
            if (solution > max)
                max = solution;

        return max;
    }

    private void parse(String line) {
        String[] split = line.split("Each (ore|clay|obsidian|geode) robot");
        oreRobotOreCost = Integer.parseInt(split[1].split("costs | ore")[1]);
        clayRobotOreCost = Integer.parseInt(split[2].split("costs | ore")[1]);
        String[] split2 = split[3].split("costs | ore and | clay");
        obsidianRobotOreCost = Integer.parseInt(split2[1]);
        obsidianRobotClayCost = Integer.parseInt(split2[2]);
        split2 = split[4].split("costs | ore and | obsidian");
        geodeRobotOreCost = Integer.parseInt(split2[1]);
        geodeRobotObsidianCost = Integer.parseInt(split2[2]);
        maxOreRobots = max(oreRobotOreCost, max(clayRobotOreCost, max(obsidianRobotOreCost, geodeRobotOreCost)));
        maxClayRobots = obsidianRobotClayCost;
        maxObsidianRobots = geodeRobotObsidianCost;
    }
}
