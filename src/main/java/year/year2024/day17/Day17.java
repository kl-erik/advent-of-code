package year.year2024.day17;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day17 implements Day {
    List<Integer> comboOpCodes = Arrays.asList(0, 2, 5, 6, 7);

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Input computer = parse(file);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < computer.prog.length; i += 2) {
            int opcode = computer.prog[i];
            int operand = computer.prog[i + 1];

            if (comboOpCodes.contains(opcode)) {
                operand = getCombo(operand, computer);
            }

            switch (opcode) {
                case 0 -> computer.a /= (int) Math.pow(2, operand);
                case 1 -> computer.b ^= operand;
                case 2 -> computer.b = operand % 8;
                case 3 -> i = computer.a != 0 ? operand - 2 : i;
                case 4 -> computer.b ^= computer.c;
                case 5 -> result.append(operand % 8).append(",");
                case 6 -> computer.b = computer.a / (int) Math.pow(2, operand);
                case 7 -> computer.c = computer.a / (int) Math.pow(2, operand);
                default -> throw new IllegalArgumentException("Invalid opcode");
            }
        }

        return result.substring(0, result.length() - 1);
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        Input computer = parse(file);
        return findA(0, 0, computer.prog);
    }

    private static int findA(int a, int i, int[] ints) {
        if (i == ints.length) {
            return a;
        }

        while (fun(a, i) % 8 != ints[i]) {
            a += (int) Math.pow(8, i + 1);
        }

        return findA(a, i + 1, ints);
    }

    private static int fun(int a, int i) {
        return Math.floorDiv(a, (int) Math.pow(8, i + 1));
    }

    private int getCombo(int operand, Input computer) {
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

    private class Input {
        private int a, b, c;
        private final int[] prog;

        public Input(int a, int b, int c, int[] prog) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.prog = prog;
        }
    }
}
