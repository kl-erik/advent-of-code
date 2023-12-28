package year.year2023.day08;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day8 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            char[] instructions = scanner.nextLine().toCharArray();
            HashMap<String, String[]> nodes = new HashMap<>();
            while (scanner.hasNext()) {
                String node = scanner.next();
                String[] neighbours = scanner.nextLine().replaceAll("[=(),]", "").trim().split("\\s+");
                nodes.put(node, neighbours);
            }

            String node = "AAA";
            int steps = 0;
            while (!node.equals("ZZZ")) {
                for (char instruction : instructions) {
                    String[] neighbours = nodes.get(node);
                    node = instruction == 'L' ? neighbours[0] : neighbours[1];
                    steps++;
                    if (node.equals("ZZZ")) {
                        break;
                    }
                }
            }

            return steps;
        }
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            char[] instructions = scanner.nextLine().toCharArray();
            ArrayList<String> startNodes = new ArrayList<>();
            HashMap<String, String[]> nodes = new HashMap<>();
            while (scanner.hasNext()) {
                String node = scanner.next();
                String[] neighbours = scanner.nextLine().replaceAll("[=(),]", "").trim().split("\\s+");
                nodes.put(node, neighbours);
                if (node.endsWith("A")) {
                    startNodes.add(node);
                }
            }

            long[] totalSteps = new long[startNodes.size()];
            for (int i = 0; i < startNodes.size(); i++) {
                String node = startNodes.get(i);
                int steps = 0;
                while (!node.endsWith("Z")) {
                    for (char instruction : instructions) {
                        String[] neighbours = nodes.get(node);
                        node = instruction == 'L' ? neighbours[0] : neighbours[1];
                        steps++;
                        if (node.endsWith("Z")) {
                            totalSteps[i] = steps;
                            break;
                        }
                    }
                }
            }

            for (int i = totalSteps.length - 1; i >= 0; i--) {
                for (int j = 0; j < i; j++) {
                    long a = totalSteps[j];
                    long b = totalSteps[j+1];
                    long gcd = gcd(a, b);
                    totalSteps[j] = a * (b / gcd);
                }
            }

            return totalSteps[0];
        }
    }

    private long gcd(long a, long b) {
        return b==0 ? a : gcd(b, a%b);
    }
}
