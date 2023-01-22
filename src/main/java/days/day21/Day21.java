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
        HashMap<String, String> map = parse(file);
        return solve(map, "root");
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        HashMap<String, String> map = parse(file);
        refactor(map, map.get("root"));
        return solve(map, "humn");
    }

    private void refactor(HashMap<String, String> map, String equation) {
        String[] split = equation.split(" ");

        if (containsHuman(map, split[0])) {
            refactor(map, map.get(split[0]), split[2]);
        } else {
            refactor(map, map.get(split[2]), split[0]);
        }
    }

    private void refactor(HashMap<String, String> map, String equation, String v) {
        String[] split = equation.split(" ");

        if (containsHuman(map, split[0]) || split[0].equals("humn")) {
            String oldV = map.get(split[0]);
            map.remove(split[0]);
            String newV = flip(split[2], split[1], v, true);
            map.put(split[0], newV);
            if (!split[0].equals("humn"))
                refactor(map, oldV, split[0]);
        } else {
            String oldV = map.get(split[2]);
            map.remove(split[2]);
            String newV = flip(split[0], split[1], v, false);
            map.put(split[2], newV);
            if (!split[2].equals("humn"))
                refactor(map, oldV, split[2]);
        }
    }

    private String flip(String v1, String op, String v2, boolean fst) {
        switch (op) {
            case "+": return v2 + " - " + v1;
            case "-": return fst ? v2 + " + " + v1 : v1 + " - " + v2;
            case "*": return v2 + " / " + v1;
            case "/": return fst ? v2 + " * " + v1 : v1 + " / " + v2;
            default: throw new IllegalArgumentException(op);
        }
    }

    private boolean containsHuman(HashMap<String, String> map, String key) {
        String value = map.get(key);

        if (value.contains("humn")) {
            return true;
        } else {
            String[] split = value.split(" ");
            return split.length > 1 && (containsHuman(map, split[0]) || containsHuman(map, split[2]));
        }
    }

    private long solve(HashMap<String, String> map, String key) {
        String value = map.get(key);

        try {
            return Long.parseLong(value);
        } catch (NumberFormatException ignore) {
            String[] split = value.split(" ");
            long v1 = solve(map, split[0]);
            long v2 = solve(map, split[2]);

            switch (split[1]) {
                case "+": return v1 + v2;
                case "-": return v1 - v2;
                case "*": return v1 * v2;
                case "/": return v1 / v2;
                default: throw new NoSuchElementException();
            }
        }
    }

    private static HashMap<String, String> parse(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        HashMap<String, String> map = new HashMap<>();

        while (scanner.hasNextLine()) {
            String[] split = scanner.nextLine().split(": ");
            map.put(split[0], split[1]);
        }

        return map;
    }
}
