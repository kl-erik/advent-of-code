package year.year2025.day05;

import year.Day;
import year.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day5 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        List<long[]> points = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            String[] bounds = line.split("-");
            long lower = Long.parseLong(bounds[0]);
            long upper = Long.parseLong(bounds[1]);
            long[] point = new long[]{lower, upper};
            points.add(point);
        }
        int value = 0;
        while (sc.hasNextLine()) {
            long id = Long.parseLong(sc.nextLine());
            for (long[] point : points) {
                if (point[0] <= id && id <= point[1]) {
                    value++;
                    break;
                }
            }
        }
        return value;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        List<long[]> points = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            String[] bounds = line.split("-");
            long lower = Long.parseLong(bounds[0]);
            long upper = Long.parseLong(bounds[1]);
            long[] point = new long[]{lower, upper};
            addOverlapping(points, point);
        }
        long value = 0;
        for (long[] point : points) {
            value += point[1] - point[0] + 1;
        }
        return value;
    }

    private void addOverlapping(List<long[]> points, long[] newPoint) {
        for (long[] point : points) {
            if (newPoint[0] >= point[0] && newPoint[1] <= point[1]) {
                return;
            }
            if (newPoint[0] <= point[0] && newPoint[1] >= point[1]) {
                points.remove(point);
                addOverlapping(points, newPoint);
                return;
            }
            if (newPoint[0] <= point[1] && newPoint[1] > point[1]) {
                point[1] = newPoint[1];
                points.remove(point);
                addOverlapping(points, point);
                return;
            }
            if (newPoint[1] >= point[0] && newPoint[0] < point[0]) {
                point[0] = newPoint[0];
                points.remove(point);
                addOverlapping(points, point);
                return;
            }
        }
        points.add(newPoint);
    }
}
