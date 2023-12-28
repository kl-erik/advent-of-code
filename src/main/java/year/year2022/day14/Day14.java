package year.year2022.day14;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Day14 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        boolean[][] cave = parse(file);

        int i = 0;
        while (!addSand(cave, cave[0].length / 2, 0)) {
            i++;
        }

        return i;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        boolean[][] cave = parse(file);

        int i = 0;
        while (!cave[0][cave[0].length / 2]) {
            addSand(cave, cave[0].length / 2, 0);
            i++;
        }

        return i;
    }

    private boolean[][] parse(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<ArrayList<Point>> paths = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String[] strings = scanner.nextLine().split(" -> ");
            for (int i = 1; i < strings.length; i++) {
                String[] pointString1 = strings[i-1].split(",");
                String[] pointString2 = strings[i].split(",");
                Point point1 = new Point(Integer.parseInt(pointString1[0]), Integer.parseInt(pointString1[1]));
                Point point2 = new Point(Integer.parseInt(pointString2[0]), Integer.parseInt(pointString2[1]));
                ArrayList<Point> path = getPath(point1, point2);
                paths.add(path);
            }
        }

        return getCave(paths);
    }

    private ArrayList<Point> getPath(Point point1, Point point2) {
        if (point1.x < point2.x)
            return getHorizontalPath(point1, point2);
        if (point1.x > point2.x)
            return getHorizontalPath(point2, point1);
        if (point1.y < point2.y)
            return getVerticalPath(point1, point2);
        if (point1.y > point2.y)
            return getVerticalPath(point2, point1);
        return new ArrayList<>(Collections.singleton(point1));
    }

    private ArrayList<Point> getHorizontalPath(Point from, Point to) {
        ArrayList<Point> path = new ArrayList<>();
        for (int i = from.x; i <= to.x; i++)
            path.add(new Point(i, from.y));
        return path;
    }

    private ArrayList<Point> getVerticalPath(Point from, Point to) {
        ArrayList<Point> path = new ArrayList<>();
        for (int i = from.y; i <= to.y; i++)
            path.add(new Point(from.x, i));
        return path;
    }

    private boolean[][] getCave(ArrayList<ArrayList<Point>> paths) {
        int xOffset = Math.abs(500 - paths.get(0).get(0).x);
        int maxY = paths.get(0).get(0).y;

        for (int i = 1; i < paths.size(); i++) {
            for (Point point : paths.get(i)) {
                int offset = Math.abs(500 - point.x);

                if (offset > xOffset) {
                    xOffset = offset;
                }

                if (point.y > maxY) {
                    maxY = point.y;
                }
            }
        }

        boolean[][] cave = new boolean[maxY + 3][xOffset * 6];

        for (ArrayList<Point> path : paths) {
            for (Point point : path) {
                cave[point.y][point.x - 500 + xOffset * 3] = true;
            }
        }

        Arrays.fill(cave[cave.length - 1], true);

        return cave;
    }

    private boolean addSand(boolean[][] cave, int x, int y) {
        while (!cave[y + 1][x]) {
            y++;
        }

        if (!cave[y + 1][x-1]) {
            return addSand(cave, x-1, y + 1);
        } else if (!cave[y + 1][x+1]) {
            return addSand(cave, x+1, y + 1);
        } else {
            cave[y][x] = true;
            return y > cave.length - 3;
        }
    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
