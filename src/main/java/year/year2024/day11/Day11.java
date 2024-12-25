package year.year2024.day11;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day11 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        ArrayList<Long> stones = parse(file);
        for (int i = 0; i < 25; i++) {
            ArrayList<Long> newStones = new ArrayList<>();
            for (long stone : stones) {
                if (stone == 0) {
                    newStones.add(1L);
                } else {
                    String stoneString = Long.toString(stone);
                    if (stoneString.length() % 2 == 0) {
                        newStones.add(Long.parseLong(stoneString.substring(0, stoneString.length() / 2)));
                        newStones.add(Long.parseLong(stoneString.substring(stoneString.length() / 2)));
                    } else {
                        newStones.add(stone * 2024);
                    }
                }
            }
            stones = newStones;
        }
        return stones.size();
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        ArrayList<Long> list = parse(file);
        HashMap<Long, Long> stones = new HashMap<>();
        for (long stone : list) {
            stones.put(stone, 1L);
        }
        for (int i = 0; i < 75; i++) {
            HashMap<Long, Long> newStones = new HashMap<>();
            for (Map.Entry<Long, Long> entry : stones.entrySet()) {
                long stone = entry.getKey();
                long count = entry.getValue();
                if (stone == 0) {
                    newStones.put(1L, newStones.getOrDefault(1L, 0L) + count);
                } else {
                    String stoneString = Long.toString(stone);
                    if (stoneString.length() % 2 == 0) {
                        long firstHalf = Long.parseLong(stoneString.substring(0, stoneString.length() / 2));
                        long secondHalf = Long.parseLong(stoneString.substring(stoneString.length() / 2));
                        newStones.put(firstHalf, newStones.getOrDefault(firstHalf, 0L) + count);
                        newStones.put(secondHalf, newStones.getOrDefault(secondHalf, 0L) + count);
                    } else {
                        long newStone = stone * 2024;
                        newStones.put(newStone, newStones.getOrDefault(newStone, 0L) + count);
                    }
                }
            }
            stones = newStones;
        }
        return stones.values().stream().mapToLong(Long::longValue).sum();
    }

    private static ArrayList<Long> parse(File file) throws FileNotFoundException {
        ArrayList<Long> stones = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextInt()) {
            stones.add(scanner.nextLong());
        }
        return stones;
    }
}
