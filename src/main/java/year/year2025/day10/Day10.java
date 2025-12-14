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
        Scanner sc = new Scanner(file);
        List<Machine> machines = parse(sc);
        int minPresses = 0;
        for (Machine machine : machines) {
            minPresses += findMinPresses(machine);
        }
        return minPresses;
    }

    private int findMinPresses(Machine machine) {
        char[] lights = machine.lights;
        int[][] buttons = machine.buttons;

        for (int n = 0; n < buttons.length; n++) {
            char[] newLights = new char[lights.length];
            Arrays.fill(newLights, '.');

            Set<Light> results = pressButtons(newLights, buttons, n, 0);

            for (Light result : results) {
                if (Arrays.equals(result.lights, lights)) {
                    return n;
                }
            }
        }

        throw new IllegalArgumentException("No solution found");
    }

    private Set<Light> pressButtons(char[] lights, int[][] buttons, int n, int i) {
        if (n == 0) {
            return Collections.singleton(new Light((lights)));
        }

        Set<Light> results = new HashSet<>(Collections.emptySet());

        char[] newLights = Arrays.copyOf(lights, lights.length);
        for (int j = i; j < buttons.length; j++) {
            char[] pressedLights = pressButton(newLights, buttons[j]);
            results.addAll(pressButtons(pressedLights, buttons, n - 1, j + 1));
        }

        return results;
    }

    private char[] pressButton(char[] lights, int[] button) {
        char[] newLights = Arrays.copyOf(lights, lights.length);
        for (int index : button) {
            newLights[index] = newLights[index] == '.' ? '#' : '.';
        }
        return newLights;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        // TODO: Implement puzzle2
        return null;
    }

    private List<Machine> parse(Scanner sc) {
        List<Machine> machines = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] split = sc.nextLine().split(" ");
            char[] lights = split[0].substring(1, split[0].length() - 1).toCharArray();
            int[][] buttons = new int[split.length - 2][];
            for (int i = 1; i < split.length - 1; i++) {
                int[] button = Arrays.stream(split[i].substring(1, split[i].length() - 1).split(",")).mapToInt(Integer::parseInt).toArray();
                buttons[i - 1] = button;
            }
            machines.add(new Machine(lights, buttons));
        }
        return machines;
    }

    private static class Machine {
        private final char[] lights;
        private final int[][] buttons;

        public Machine(char[] lights, int[][] buttons) {
            this.lights = lights;
            this.buttons = buttons;
        }
    }

    private static class Light {
        private final char[] lights;

        public Light(char[] lights) {
            this.lights = lights;
        }
    }
}
