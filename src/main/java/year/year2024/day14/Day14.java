package year.year2024.day14;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

import static year.Utils.even;

public class Day14 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        int width, height;
        if (file.getPath().contains("test")) {
            width = 11;
            height = 7;
        } else {
            width = 101;
            height = 103;
        }
        int[][] bathroom = new int[height][width];
        int steps = 100;
        Pattern pattern = Pattern.compile("p=(?<x>\\d+),(?<y>\\d+) v=(?<vX>-?\\d+),(?<vY>-?\\d+)");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            var matcher = pattern.matcher(line);
            if (matcher.find()) {
                int x = Integer.parseInt(matcher.group("x"));
                int y = Integer.parseInt(matcher.group("y"));
                int vX = Integer.parseInt(matcher.group("vX"));
                int vY = Integer.parseInt(matcher.group("vY"));
                x = (x + vX * steps) % width;
                y = (y + vY * steps) % height;
                if (x < 0) x += width;
                if (y < 0) y += height;
                bathroom[y][x]++;
            } else {
                throw new RuntimeException("Invalid input line: " + line);
            }
        }
        int xOffset = even(width) ? 0 : 1;
        int yOffset = even(height) ? 0 : 1;
        return sumArea(bathroom, 0, height / 2, 0, width / 2)
                * sumArea(bathroom, 0, height / 2, width / 2 + xOffset, width)
                * sumArea(bathroom, height / 2 + yOffset, height, 0, width / 2)
                * sumArea(bathroom, height / 2 + yOffset, height, width / 2 + xOffset, width);
    }

    private int sumArea(int[][] bathroom, int iStart, int iEnd, int jStart, int jEnd) {
        int sum = 0;
        for (int i = iStart; i < iEnd; i++) {
            for (int j = jStart; j < jEnd; j++) {
                sum += bathroom[i][j];
            }
        }
        return sum;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }
}
