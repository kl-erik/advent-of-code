package year.year2025.day10;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
        return 0;
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

    private class Machine {
        private final char[] lights;
        private final int[][] buttons;

        public Machine(char[] lights, int[][] buttons) {
            this.lights = lights;
            this.buttons = buttons;
        }
    }
}
