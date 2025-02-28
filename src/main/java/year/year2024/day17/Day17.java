package year.year2024.day17;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

public class Day17 implements Day {
    static List<Integer> comboOpCodesList = Arrays.asList(0, 2, 5, 6, 7);

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Input computer = parse(file);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < computer.prog.length; i += 2) {
            int opcode = computer.prog[i];
            int operand = computer.prog[i + 1];

            if (comboOpCodesList.contains(opcode)) {
                operand = (int) getCombo(operand, computer);
            }

            if (opcode == 3) {
                i = computer.a != 0 ? operand - 2 : i;
                continue;
            }

            if (opcode == 5) {
                result.append(operand % 8).append(",");
                continue;
            }

            update(opcode, operand, computer);
        }

        return result.substring(0, result.length() - 1);
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        Input computer = parse(file);
        computer.a = 0;
        return file.getPath().contains("test")
                ? outputA(computer, computer.prog.length - 1)
                : outputB(computer, computer.prog.length - 1);
    }

    private static long outputA(Input computer, int i) {
        if (i < 0) {
            return computer.a;
        }

        long tmp = computer.a << 3;
        computer.a = 0;

        while (run(computer).a % 8 != computer.prog[i]) {
            computer.a++;
        }

        computer.a += tmp;

        return outputA(computer, i - 1);
    }

    private static long outputB(Input computer, int i) {
        if (i < 0) {
            return computer.a;
        }

        Set<Long> solutions = new HashSet<>();

        long tmp = computer.a << 3;

        for (int j = 0; j < 8; j++) {
            computer.a = j + tmp;

            if (run(computer).b % 8 == computer.prog[i]) {
                try {
                    solutions.add(outputB(computer, i - 1));
                } catch (NoSuchElementException ignored) {
                }
            }
        }

        return solutions.stream().mapToLong(Long::longValue).min().orElseThrow();
    }

    private static Input run(Input computer) {
        Input clone = new Input(computer.a, computer.b, computer.c, computer.prog);

        for (int k = 0; k < clone.prog.length - 4; k += 2) {
            int opcode = clone.prog[k];
            int operand = clone.prog[k + 1];

            if (comboOpCodesList.contains(opcode)) {
                update(opcode, getCombo(operand, clone), clone);
            } else {
                update(opcode, operand, clone);
            }
        }

        return clone;
    }

    private static void update(int opcode, long operand, Input computer) {
        switch (opcode) {
            case 0 -> computer.a /= (int) Math.pow(2, operand);
            case 1 -> computer.b ^= operand;
            case 2 -> computer.b = operand % 8;
            case 4 -> computer.b ^= computer.c;
            case 6 -> computer.b = computer.a / (int) Math.pow(2, operand);
            case 7 -> computer.c = computer.a / (int) Math.pow(2, operand);
            default -> throw new IllegalArgumentException("Invalid opcode");
        }
    }

    private static long getCombo(int operand, Input computer) {
        return switch (operand) {
            case 4 -> computer.a;
            case 5 -> computer.b;
            case 6 -> computer.c;
            case 7 -> throw new IllegalArgumentException("Invalid operand");
            default -> operand;
        };
    }

    private Input parse(File file) throws FileNotFoundException {
        int a, b, c;
        int[] prog;

        try (Scanner scanner = new Scanner(file)) {
            a = Integer.parseInt(scanner.nextLine().split(" ")[2]);
            b = Integer.parseInt(scanner.nextLine().split(" ")[2]);
            c = Integer.parseInt(scanner.nextLine().split(" ")[2]);
            scanner.nextLine();
            prog = Arrays.stream(scanner.nextLine().split(" ")[1].split(",")).mapToInt(Integer::parseInt).toArray();
            return new Input(a, b, c, prog);
        }
    }

    private static class Input {
        private long a, b, c;
        private final int[] prog;

        public Input(long a, long b, long c, int[] prog) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.prog = prog;
        }
    }
}
