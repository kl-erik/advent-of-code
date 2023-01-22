package days.day21;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Day21 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        HashMap<String, String> map = getMap(file);
        return solve(map, "root");
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }

    private long solve(HashMap<String, String> map, String key) {
        String value = map.get(key);

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ignore) {
            String[] split = value.split(" ");

            switch (split[1]) {
                case "+": return solve(map, split[0]) + solve(map, split[2]);
                case "-": return solve(map, split[0]) - solve(map, split[2]);
                case "*": return solve(map, split[0]) * solve(map, split[2]);
                case "/": return solve(map, split[0]) / solve(map, split[2]);
                default: throw new NoSuchElementException();
            }
        }
    }

    private static HashMap<String, String> getMap(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        HashMap<String, String> map = new HashMap<>();

        while (scanner.hasNextLine()) {
            String[] split = scanner.nextLine().split(": ");
            map.put(split[0], split[1]);
        }
        return map;
    }
}
