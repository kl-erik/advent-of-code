package year.year2024.day11;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day11 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        ArrayList<Long> stones = parse(file);
        for (int i = 0; i < 25; i++) {
            HashMap<Integer, Long> newStones = new HashMap<>();
            for (int j = 0; j < stones.size(); j++) {
                long stone = stones.get(j);
                if (stone == 0) {
                    stones.set(j, 1L);
                } else {
                    String stoneString = Long.toString(stone);
                    if (stoneString.length() % 2 == 0) {
                        long newStone = Long.parseLong(stoneString.substring(0, stoneString.length() / 2));
                        newStones.put(j, newStone);
                        stones.set(j, Long.parseLong(stoneString.substring(stoneString.length() / 2)));
                    } else {
                        stones.set(j, stone * 2024);
                    }
                }
            }
            for (int j : newStones.keySet()) {
                stones.add(j, newStones.get(j));
            }
        }
        return stones.size();
    }

    private final HashMap<Long, HashMap<Integer, Long>> map = new HashMap<>();

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        ArrayList<Long> stones = parse(file);
        long newStones = 0;
        for (long stone : stones) {
            newStones += expandTree(stone, 0);
        }
        return stones.size() + newStones;
    }

    private long expandTree(long stone, int i) {
        long newStones = 0;
        long originalStone = stone;

        for (int j = i; j < 75; j++) {
            if (map.containsKey(stone) && map.get(stone).containsKey(j)) {
                newStones += map.get(stone).get(j);
                break;
            }

            if (stone == 0) {
                stone = 1L;
            } else {
                String stoneString = Long.toString(stone);
                if (stoneString.length() % 2 == 0) {
                    long firstHalf = Long.parseLong(stoneString.substring(0, stoneString.length() / 2));
                    long secondHalf = Long.parseLong(stoneString.substring(stoneString.length() / 2));
                    stone = firstHalf;
                    newStones += 1 + expandTree(secondHalf, j + 1);
                } else {
                    stone *= 2024;
                }
            }
        }

        map.computeIfAbsent(originalStone, k -> new HashMap<>()).put(i, newStones);
        return newStones;
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
