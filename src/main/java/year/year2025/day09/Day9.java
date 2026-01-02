package year.year2025.day09;

import year.Day;
import year.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day9 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        List<Point> points = parse(file);
        long maxArea = 0;
        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);
            for (int j = i + 1; j < points.size(); j++) {
                Point p2 = points.get(j);
                long area = getArea(p2, p1);
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    private static long getArea(Point p2, Point p1) {
        int deltaX = Math.abs(p2.x - p1.x) + 1;
        int deltaY = Math.abs(p2.y - p1.y) + 1;
        return (long) deltaX * deltaY;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        List<Point> points = parse(file);
        List<Rectangle> rectangles = getRectangles(points);

        points.sort(Comparator.comparingInt((Point p) -> p.x).thenComparingInt(p -> p.y));

        long maxArea = 0;
        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);
            for (int j = i + 1; j < points.size(); j++) {
                Point p2 = points.get(j);

                List<Rectangle> filteredRectangles = rectangles.stream()
                        .filter(rectangle -> p1.x <= rectangle.xLb && rectangle.xUb <= p2.x)
                        .filter(rectangle -> p1.y <= p2.y
                                ? rectangle.yLb <= p1.y && p2.y <= rectangle.yUb
                                : rectangle.yLb <= p2.y && p1.y <= rectangle.yUb)
                        .collect(Collectors.toList());

                // after filtering by both x and y, area is only valid if there exists a full path of rectangles from
                // p1's x-pos to p2's x-pos
                boolean valid = true;
                int x = p1.x;
                while (x < p2.x) {
                    int finalX = x;
                    Optional<Rectangle> optionalRectangle = filteredRectangles.stream()
                            .filter(rectangle -> rectangle.xLb == finalX).findFirst();

                    if (optionalRectangle.isEmpty()) {
                        valid = false;
                        break;
                    }

                    x = optionalRectangle.get().xUb;
                }

                if (!valid) continue;

                long area = getArea(p2, p1);
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    private List<Rectangle> getRectangles(List<Point> points) {
        List<Rectangle> rectangles = new ArrayList<>();
        List<Point> clone = new ArrayList<>(points);

        while (clone.size() > 2) {
            clone.sort(Comparator.comparingInt((Point p) -> p.x).thenComparingInt(p -> p.y));
            Point p1 = clone.remove(0);
            Point p2 = clone.remove(0);
            Point p3 = clone.stream().filter(p -> p.y == p1.y).findFirst().get();
            Point p4 = clone.stream().filter(p -> p.y == p2.y).findFirst().get();

            if (p3.x == p4.x) {
                rectangles.add(new Rectangle(p1.x, p3.x, p1.y, p2.y));

                clone.remove(p1);
                clone.remove(p2);
                clone.remove(p3);
                clone.remove(p4);
            } else {
                if (p4.x < p3.x) {
                    p3 = p4;
                }

                rectangles.add(new Rectangle(p1.x, p3.x, p1.y, p2.y));

                clone.remove(p1);
                clone.remove(p2);
                clone.remove(p3);
                clone.add(p1.y == p3.y ? new Point(p3.x, p2.y) : new Point(p3.x, p1.y));
            }
        }

        return rectangles;
    }

    private static List<Point> parse(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        List<Point> points = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] coordinates = sc.nextLine().split(",");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            points.add(new Point(x, y));
        }
        return points;
    }

    private static class Rectangle {
        private final int xLb;
        private final int xUb;
        private final int yLb;
        private final int yUb;

        public Rectangle(int xLb, int xUb, int yLb, int yUb) {
            this.xLb = xLb;
            this.xUb = xUb;
            this.yLb = yLb;
            this.yUb = yUb;
        }
    }
}
