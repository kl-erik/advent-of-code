package year.year2025.day10;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day10 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        List<Machine> machines = parse(file);
        return machines.stream().mapToInt(this::findMinToggles).sum();
    }

    private int findMinToggles(Machine machine) {
        char[] finalState = machine.state;
        int[][] buttons = machine.buttons;

        char[] initState = new char[finalState.length];
        Arrays.fill(initState, '.');
        for (int n = 0; n < buttons.length; n++) {
            Set<Result> results = toggleButtons(initState, buttons, n, 0);

            for (Result result : results) {
                if (Arrays.equals(result.state, finalState)) {
                    return n;
                }
            }
        }

        throw new IllegalArgumentException("No solution found");
    }

    private Set<Result> toggleButtons(char[] state, int[][] buttons, int n, int i) {
        if (n == 0) {
            return Collections.singleton(new Result((state)));
        }

        Set<Result> results = new HashSet<>();

        for (int j = i; j < buttons.length; j++) {
            char[] newState = toggleButton(state, buttons[j]);
            results.addAll(toggleButtons(newState, buttons, n - 1, j + 1));
        }

        return results;
    }

    private char[] toggleButton(char[] state, int[] button) {
        char[] newState = Arrays.copyOf(state, state.length);
        for (int index : button) {
            newState[index] = newState[index] == '.' ? '#' : '.';
        }
        return newState;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        // TODO: Implement puzzle2
        return null;
    }

    private List<Machine> parse(File file) throws FileNotFoundException {
        List<Machine> machines = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String[] split = sc.nextLine().split(" ");
            char[] state = split[0].substring(1, split[0].length() - 1).toCharArray();
            int[][] buttons = new int[split.length - 2][];
            for (int i = 1; i < split.length - 1; i++) {
                int[] button = Arrays.stream(split[i].substring(1, split[i].length() - 1).split(",")).mapToInt(Integer::parseInt).toArray();
                buttons[i - 1] = button;
            }
            machines.add(new Machine(state, buttons));
        }
        return machines;
    }

    private static class Machine {
        private final char[] state;
        private final int[][] buttons;

        public Machine(char[] state, int[][] buttons) {
            this.state = state;
            this.buttons = buttons;
        }
    }

    private static class Result {
        private final char[] state;

        public Result(char[] state) {
            this.state = state;
        }
    }
}
