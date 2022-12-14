package days.day14;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day14 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        boolean[][] cave = parse(file);

        int i = 0;
        while (produceSand(cave, cave[0].length / 2, 0)) {
            i++;
        }

        return i;
    }

    private boolean produceSand(boolean[][] cave, int x, int y) {
        while (!cave[y][x]) {
            y++;

            if (y == cave.length) {
                return false;
            }
        }

        if (!cave[y][x-1]) {
            return produceSand(cave, x-1, y);
        } else if (!cave[y][x+1]) {
            return produceSand(cave, x+1, y);
        } else {
            cave[y - 1][x] = true;
            return true;
        }
    }

    private boolean[][] parse(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<ArrayList<Point>> paths = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String[] strings = scanner.nextLine().split(" -> ");
            for (int i = 1; i < strings.length; i++) {
                ArrayList<Point> path = getPath(strings[i-1].split(","), strings[i].split(","));
                paths.add(path);
            }
        }

        return getCave(paths);
    }

    private boolean[][] getCave(ArrayList<ArrayList<Point>> paths) {
        int maxX = paths.get(0).get(0).x;
        int maxY = paths.get(0).get(0).y;
        int minX = maxX;
        int minY = maxY;

        for (ArrayList<Point> path : paths) {
            for (Point point : path) {
                if (point.x > maxX) {
                    maxX = point.x;
                } else if (point.x < minX) {
                    minX = point.x;
                }

                if (point.y > maxY) {
                    maxY = point.y;
                } else if (point.y < minY) {
                    minY = point.y;
                }
            }
        }

        int xOffset = Math.max(Math.abs(maxX - 500), Math.abs(500 - minX)) + 1;

        boolean[][] cave = new boolean[maxY + 1][xOffset * 2];

        for (ArrayList<Point> path : paths) {
            for (Point point : path) {
                cave[point.y][point.x - 500 + xOffset] = true;
            }
        }

        return cave;
    }

    private ArrayList<Point> getPath(String[] start, String[] dest) {
        int x1 = Integer.parseInt(start[0]);
        int y1 = Integer.parseInt(start[1]);
        int x2 = Integer.parseInt(dest[0]);
        int y2 = Integer.parseInt(dest[1]);

        if (y1 < y2) {
            return getVerticalPath(y1, y2, x1);
        }

        if (y1 > y2) {
            return getVerticalPath(y2, y1, x1);
        }

        if (x1 < x2) {
            return getHorizontalPath(x1, x2, y1);
        }

        if (x1 > x2) {
            return getHorizontalPath(x2, x1, y1);
        }

        return new ArrayList<>();
    }

    private ArrayList<Point> getHorizontalPath(int x1, int x2, int y) {
        ArrayList<Point> path = new ArrayList<>();
        for (int i = x1; i <= x2; i++)
            path.add(new Point(y, i));
        return path;
    }

    private ArrayList<Point> getVerticalPath(int y1, int y2, int x) {
        ArrayList<Point> path = new ArrayList<>();
        for (int i = y1; i <= y2; i++)
            path.add(new Point(i, x));
        return path;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }

    private static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
