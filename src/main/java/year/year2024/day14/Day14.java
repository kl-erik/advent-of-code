package year.year2024.day14;

import year.Day;
import year.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        int width, height;
        if (file.getPath().contains("test")) {
            width = 11;
            height = 7;
        } else {
            width = 101;
            height = 103;
        }
        ArrayList<Point[]> robots = parse(file);
        for (int step = 1; step <= width * height; step++) {
            int[][] bathroom = new int[height][width];
            for (Point[] robot : robots) {
                Point position = robot[0];
                Point velocity = robot[1];
                position.x = (position.x + velocity.x) % width;
                position.y = (position.y + velocity.y) % height;
                if (position.x < 0) position.x += width;
                if (position.y < 0) position.y += height;
                bathroom[position.y][position.x]++;
            }

            for (int i = 0; i < bathroom.length; i++) {
                for (int j = 0; j < bathroom[i].length; j++) {
                    if (bathroom[i][j] > 0) {
                        if (hasTreeAtPoint(bathroom, i, j)) {
                            return step;
                        }
                    }
                }
            }
        }
        throw new RuntimeException("No solution found");
    }

    private boolean hasTreeAtPoint(int[][] bathroom, int y, int x) {
        for (int triangles = 0; triangles < 4; triangles++) {
            for (int i = 5 * triangles; i < 5 * triangles + 5; i++) {
                for (int j = -i + 3 * triangles; j < i - 3 * triangles; j++) {
                    if (!inside(bathroom, y + i, x + j) || bathroom[y + i][x + j] == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean inside(int[][] bathroom, int i, int j) {
        return i >= 0 && i < bathroom.length && j >= 0 && j < bathroom[i].length;
    }

    private static ArrayList<Point[]> parse(File file) throws FileNotFoundException {
        ArrayList<Point[]> robots = new ArrayList<>();
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
                robots.add(new Point[]{new Point(x, y), new Point(vX, vY)});
            } else {
                throw new RuntimeException("Invalid input line: " + line);
            }
        }
        return robots;
    }
}
