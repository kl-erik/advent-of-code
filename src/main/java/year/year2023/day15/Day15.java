package year.year2023.day15;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day15 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        String[] steps = new Scanner(file).nextLine().split(",");
        int sum = 0;

        for (String step : steps) {
            int value = 0;

            for (int code : step.toCharArray()) {
                value += code;
                value *= 17;
                value %= 256;
            }

            sum += value;
        }

        return sum;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }
}
