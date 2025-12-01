package year.year2025.day01;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        int value = 0;
        int pointer = 50;

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String rotation = sc.nextLine();
            char direction = rotation.charAt(0);
            int steps = Integer.parseInt(rotation.substring(1));

            if (direction == 'R') {
                pointer = (pointer + steps) % 100;
            } else {
                pointer = (pointer - steps + 100) % 100;
            }

            if (pointer == 0) {
                value++;
            }
        }

        return value;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        int value = 0;
        int pointer = 50;

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String rotation = sc.nextLine();
            char direction = rotation.charAt(0);
            int steps = Integer.parseInt(rotation.substring(1));

            value += steps / 100;
            steps %= 100;

            if (direction == 'R') {
                if (pointer + steps >= 100) {
                    value++;
                }
                pointer = (pointer + steps) % 100;
            } else {
                if (pointer != 0) {
                    if (pointer - steps <= 0) {
                        value++;
                    }
                }
                pointer = (pointer - steps + 100) % 100;
            }
        }

        return value;
    }
}
