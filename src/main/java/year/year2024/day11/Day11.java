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
        ArrayList<Long> stones = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextInt()) {
            stones.add(scanner.nextLong());
        }
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

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }
}
