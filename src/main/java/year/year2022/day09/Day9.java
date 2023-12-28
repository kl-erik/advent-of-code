package year.year2022.day09;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day9 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        return solve(file, 2);
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return solve(file, 10);
    }

    private static int solve(File file, int nKnots) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<String> directions = new ArrayList<>();
        ArrayList<Integer> steps = new ArrayList<>();
        int maxSize = 0;

        while (scanner.hasNextLine()) {
            String[] motion = scanner.nextLine().split(" ");
            directions.add(motion[0]);
            int step = Integer.parseInt(motion[1]);
            steps.add(step);
            maxSize += step;
        }

        boolean[][] visitedPoints = new boolean[maxSize * 2][maxSize * 2];
        Point[] knots = new Point[nKnots];
        for (int i = 0; i < knots.length; i++) {
            knots[i] = new Point(maxSize, maxSize);
        }

        Point head = knots[0];
        Point tail = knots[knots.length - 1];
        visitedPoints[tail.x][tail.y] = true;

        for (int i = 0; i < directions.size(); i++) {
            String direction = directions.get(i);
            int step = steps.get(i);

            switch (direction) {
                case "R":
                    for (int j = 0; j < step; j++) {
                        head.y++;
                        update(knots);
                        visitedPoints[tail.x][tail.y] = true;
                    }
                    break;
                case "L":
                    for (int j = 0; j < step; j++) {
                        head.y--;
                        update(knots);
                        visitedPoints[tail.x][tail.y] = true;
                    }
                    break;
                case "U":
                    for (int j = 0; j < step; j++) {
                        head.x++;
                        update(knots);
                        visitedPoints[tail.x][tail.y] = true;
                    }
                    break;
                case "D":
                    for (int j = 0; j < step; j++) {
                        head.x--;
                        update(knots);
                        visitedPoints[tail.x][tail.y] = true;
                    }
                    break;
            }
        }

        int totalVisited = 0;

        for (boolean[] row : visitedPoints) {
            for (boolean visitedPoint : row) {
                if (visitedPoint) {
                    totalVisited++;
                }
            }
        }

        return totalVisited;
    }

    private static void update(Point[] positions) {
        for (int k = 1; k < positions.length; k++) {
            if (!positions[k].canReach(positions[k-1])) {
                if (positions[k-1].y - positions[k].y > 0) {
                    positions[k].y++;
                } else if (positions[k-1].y - positions[k].y < 0) {
                    positions[k].y--;
                }

                if (positions[k-1].x - positions[k].x > 0) {
                    positions[k].x++;
                } else if (positions[k-1].x - positions[k].x < 0) {
                    positions[k].x--;
                }
            } else {
                break;
            }
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean canReach(Point position) {
            return (x == position.x || x - 1 == position.x || x + 1 == position.x)
                && (y == position.y || y - 1 == position.y || y + 1 == position.y);
        }
    }
}
