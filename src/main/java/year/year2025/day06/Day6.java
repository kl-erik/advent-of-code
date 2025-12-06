package year.year2025.day06;

import year.Day;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static year.Utils.*;

public class Day6 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        int[][] input = new int[file.getPath().contains("test") ? 3 : 4][];
        Scanner sc = new Scanner(file);
        for (int i = 0; i < input.length; i++) {
            String[] line = sc.nextLine().trim().split("\\s+");
            int[] ints = toInts(line);
            input[i] = ints;
        }

        for (int i = 0; i < 3; i++) {
            input = rotateIntMatrix90Degrees(input);
        }
        reverse(input);

        long[] answers = new long[input.length];
        String[] ops = sc.nextLine().trim().split("\\s+");
        for (int i = 0; i < input.length; i++) {
            String op = ops[i];
            switch (op) {
                case "+":
                    long sum = 0;
                    for (int j : input[i]) {
                        sum += j;
                    }
                    answers[i] = sum;
                    break;
                case "*":
                    long product = 1;
                    for (int j : input[i]) {
                        product *= j;
                    }
                    answers[i] = product;
                    break;
            }
        }

        return Arrays.stream(answers).sum();
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        List<String> lines = Utils.getLines(file);
        char[] ops = lines.remove(lines.size() - 1).trim().replace(" ", "").toCharArray();
        reverse(ops);
        padWithSpaces(lines);
        char[][] charMatrix = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            charMatrix[i] = lines.get(i).toCharArray();
        }
        for (int i = 0; i < 3; i++) {
            charMatrix = rotateCharMatrix90Degrees(charMatrix);
        }
        List<Long> answers = new ArrayList<>();
        int i = 0;
        for (char op : ops) {
            switch (op) {
                case '+':
                    long sum = 0;
                    for (int j = i; j < charMatrix.length; j++) {
                        String value = new String(charMatrix[j]).trim();
                        if (value.isEmpty()) {
                            i = j + 1;
                            break;
                        }
                        sum += Integer.parseInt(value);
                    }
                    answers.add(sum);
                    break;
                case '*':
                    long product = 1;
                    for (int j = i; j < charMatrix.length; j++) {
                        String value = new String(charMatrix[j]).trim();
                        if (value.isEmpty()) {
                            i = j + 1;
                            break;
                        }
                        product *= Integer.parseInt(value);
                    }
                    answers.add(product);
                    break;
            }
        }
        return answers.stream().mapToLong(Long::longValue).sum();
    }

    private static void padWithSpaces(List<String> lines) {
        int longestLine = 0;
        for (String line : lines) {
            if (line.length() > longestLine) {
                longestLine = line.length();
            }
        }
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            lines.add(i, line + " ".repeat(longestLine - line.length()));
            lines.remove(i + 1);
        }
    }
}
