package year.year2025.day06;

import year.Day;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
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
        // TODO: Implement puzzle2
        return null;
    }
}
