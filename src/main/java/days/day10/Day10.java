package days.day10;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day10 implements Day {
    // static int cycle = 1;
    // static int x = 1;

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        ArrayList<String> instructions = parse2(file);
        // List<String> instructions = Arrays.asList("noop", "addx 3", "noop", "addx -5", "noop");
        int x = 1;
        int v = 0;
        int signalSum = 0;
        int[] intervals = {0, 20, 60, 100, 140, 180, 220};

        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i - 1];
            int end = intervals[i];

            for (int cycle = start; cycle < end; cycle++) {
                String instruction = instructions.get(cycle);

                if (!instruction.equals("noop")) {
                    v = Integer.parseInt(instruction.split(" ")[1]);
                    continue;
                }

                x += v;
                v = 0;
            }

            signalSum = x * end;
        }

        return signalSum;
        // int sumSignals = getInteger(scanner, 0, 20, 20);
        // return sumSignals + getInteger(scanner, 20, 40, 220);
    }

    private ArrayList<String> parse2(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<String> instructions = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String instruction = scanner.nextLine();
            instructions.add(instruction);

            if (!instruction.equals("noop")) {
                instructions.add("noop");
            }
        }

        return instructions;
    }

    /*private static int getInteger(Scanner scanner, int offset, int mod, int maxIteration) {
        int sumSignals = 0;

        while (scanner.hasNextLine()) {
            String instruction = scanner.nextLine();

            if (!instruction.equals("noop")) {
                int v = Integer.parseInt(instruction.split(" ")[1]);
                cycle++;

                if ((cycle - offset) % mod == 0) {
                    sumSignals += cycle * x;
                }


                x += v;

                if (cycle >= maxIteration) {
                    cycle++;
                    break;
                }
            }

            cycle++;

            if ((cycle - offset) % mod == 0) {
                sumSignals += cycle * x;
            }

            if (cycle >= maxIteration) {
                break;
            }
        }

        return sumSignals;
    }*/


    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        String[] instructions = parse(file);
        char[][] CRT = new char[6][40];
        int x = 1;
        int instructionIndex = 0;
        String instruction = instructions[instructionIndex++];
        int v = 0;

        for (int row = 0; row < CRT.length; row++) {
            for (int col = 0; col < CRT[row].length; col++) {
                CRT[row][col] = x - 1 <= col && col <= x + 1 ? '#' : '.';

                if (!instruction.equals("noop")) {
                    v = Integer.parseInt(instruction.split(" ")[1]);
                    instruction = "noop";
                } else {
                    x += v;
                    v = 0;
                    instruction = instructions[instructionIndex++];
                }
            }
        }

        for (char[] pixels : CRT) {
            for (char pixel : pixels) {
                System.out.print(pixel);
            }

            System.out.println();
        }

        return "";
    }

    private static String[] parse(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String[] instructions = new String[240];

        int i = 0;
        while (scanner.hasNextLine()) {
            instructions[i] = scanner.nextLine();
            i++;
        }

        for (int j = i; j < instructions.length; j++) {
            instructions[j] = "noop";
        }

        return instructions;
    }
}
