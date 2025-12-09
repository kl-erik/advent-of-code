package year.year2025.day09;

import year.Day;
import year.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day9 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        List<Point> points = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] coordinates = sc.nextLine().split(",");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            points.add(new Point(x, y));
        }
        long maxArea = 0;
        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);
            for (int j = i + 1; j < points.size(); j++) {
                Point p2 = points.get(j);
                int deltaX = Math.abs(p2.x - p1.x) + 1;
                int deltaY = Math.abs(p2.y - p1.y) + 1;
                long area = (long) deltaX * deltaY;
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        // TODO: Implement puzzle2
        return null;
    }
}
