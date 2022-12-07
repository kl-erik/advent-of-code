package days.day7;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day7 implements Day {
    private final ArrayList<Integer> sizes = new ArrayList<>();
    private final int MAX_ALLOWED_DISK_SPACE = 40000000;

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        solve(scanner);
        int atMost100k = 0;
        for (int size : sizes) {
            if (size <= 100000)
                atMost100k += size;
        }
        return atMost100k;
    }

    private int solve(Scanner scanner) {
        int size = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("cd ..")) {
                sizes.add(size);
                return size;
            } else if (line.contains(" cd ")) {
                size += solve(scanner);
            } else if (Character.isDigit(line.charAt(0))) {
                size += Integer.parseInt(line.split(" ")[0]);
            }
        }

        sizes.add(size);
        return size;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        solve(scanner);
        int total = sizes.get(sizes.size() - 1);
        int min = total;

        for (int size : sizes) {
            if (total - size <= MAX_ALLOWED_DISK_SPACE) {
                if (size < min) {
                    min = size;
                }
            }
        }

        return min;
    }
}
