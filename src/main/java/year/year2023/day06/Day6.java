package year.year2023.day06;

import year.Day;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int[] time = Utils.toInts(scanner.nextLine().split("Time:\\s+")[1].split("\\s+"));
        int[] distance = Utils.toInts(scanner.nextLine().split("Distance:\\s+")[1].split("\\s+"));
        int product = 1;

        for (int race = 0; race < time.length; race++) {
            int wins = 0;

            for (int i = 1; i < time[race]; i++) {
                int newDistance = i * (time[race] - i);
                if (newDistance > distance[race]) {
                    wins++;
                }
            }

            product *= wins;
        }

        return product;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int time = Integer.parseInt(scanner.nextLine().substring(5).replace(" ", ""));
        long distance = Long.parseLong(scanner.nextLine().substring(9).replace(" ", ""));

        int start = 1;
        int end = time - 1;

        for (int i = 1; i < time; i++) {
            long newDistance = (long) i * (time - i);

            if (newDistance > distance) {
                start = i;
                break;
            }
        }

        for (int i = time - 1; i > 0; i--) {
            long newDistance = (long) i * (time - i);

            if (newDistance > distance) {
                end = i;
                break;
            }
        }

        return end - start + 1;
    }
}
