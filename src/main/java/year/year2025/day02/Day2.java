package year.year2025.day02;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day2 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        ArrayList<Long> invalidNumbers = new ArrayList<>();
        Scanner sc = new Scanner(file);
        String input = sc.nextLine();
        String[] ranges = input.split(",");
        for (String range : ranges) {
            String[] bounds = range.split("-");
            long lower = Long.parseLong(bounds[0]);
            long upper = Long.parseLong(bounds[1]);

            if (bounds[0].length() % 2 != 0) {
                bounds[0] = "1" + "0".repeat(bounds[0].length());
            }

            long i = Long.parseLong(bounds[0].substring(0, bounds[0].length() / 2));

            while (true) {
                long repeating = Long.parseLong(String.valueOf(i) + i);
                if (repeating < lower) {
                    i++;
                } else if (repeating <= upper) {
                    invalidNumbers.add(repeating);
                    i++;
                } else {
                    break;
                }
            }
        }
        return invalidNumbers.stream().mapToLong(Long::longValue).sum();
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        ArrayList<Long> allInvalidNumbers = new ArrayList<>();
        Scanner sc = new Scanner(file);
        String input = sc.nextLine();
        String[] ranges = input.split(",");
        for (String range : ranges) {
            String[] bounds = range.split("-");
            long lower = Long.parseLong(bounds[0]);
            long upper = Long.parseLong(bounds[1]);
            long i = 1;
            int j = 2;

            Set<Long> invalidNumbers = new HashSet<>();

            while (true) {
                long repeating = Long.parseLong(String.valueOf(i).repeat(j));

                if (repeating < lower) {
                    j++;
                } else if (repeating <= upper) {
                    invalidNumbers.add(repeating);
                    j++;
                } else if (j == 2) {
                    break;
                } else {
                    i++;
                    j = 2;
                }
            }

            allInvalidNumbers.addAll(invalidNumbers);
        }
        return allInvalidNumbers.stream().mapToLong(Long::longValue).sum();
    }
}
