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
                value += 1;
            }
        }

        return value;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();
        if (line.equals("hej")) {
            return 0;
        }
        return 1;
    }
}
