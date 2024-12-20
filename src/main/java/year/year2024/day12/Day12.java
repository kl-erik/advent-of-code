package year.year2024.day12;

import year.Day;
import year.Point;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Day12 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        char[][] plants = Utils.toChars(file);
        Point[][] points = initPoints(plants);
        HashMap<Character, Set<Set<Point>>> regionsMap = getRegionsMap(plants, points);
        return calcCost(points, plants, regionsMap);
    }

    private HashMap<Character, Set<Set<Point>>> getRegionsMap(char[][] plants, Point[][] points) {
        HashMap<Character, Set<Set<Point>>> regionsMap = new HashMap<>();
        Set<Point> visited = new HashSet<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[0].length; j++) {
                Point point = points[i][j];
                if (visited.contains(point)) continue;
                char plant = plants[i][j];
                Set<Point> region = getRegion(plants, point, points);
                regionsMap.computeIfAbsent(plant, k -> new HashSet<>()).add(region);
                visited.addAll(region);
            }
        }

        return regionsMap;
    }

    private Set<Point> getRegion(char[][] plants, Point point, Point[][] points) {
        Set<Point> region = new HashSet<>();
        Stack<Point> stack = new Stack<>();
        stack.push(point);
        while (!stack.isEmpty()) {
            Point current = stack.pop();
            if (region.contains(current)) continue;
            region.add(current);
            Set<Point> neighbors = getNeighbors(current, points);
            for (Point neighbor : neighbors) {
                if (plants[neighbor.getY()][neighbor.getX()] == plants[current.getY()][current.getX()]) {
                    stack.push(neighbor);
                }
            }
        }
        return region;
    }

    private Set<Point> getNeighbors(Point point, Point[][] points) {
        Set<Point> neighbors = new HashSet<>();
        if (point.getY() > 0) {
            neighbors.add(points[point.getY() - 1][point.getX()]);
        }
        if (point.getY() < points.length - 1) {
            neighbors.add(points[point.getY() + 1][point.getX()]);
        }
        if (point.getX() > 0) {
            neighbors.add(points[point.getY()][point.getX() - 1]);
        }
        if (point.getX() < points[0].length - 1) {
            neighbors.add(points[point.getY()][point.getX() + 1]);
        }
        return neighbors;
    }

    private static int calcCost(Point[][] points, char[][] plants, HashMap<Character, Set<Set<Point>>> regionsMap) {
        int cost = 0;
        for (Set<Set<Point>> regions : regionsMap.values()) {
            for (Set<Point> region : regions) {
                cost += getPerimeter(points, plants, region) * region.size();
            }
        }
        return cost;
    }

    private static int getPerimeter(Point[][] points, char[][] plants, Set<Point> region) {
        int perimeter = 0;
        for (Point point : region) {
            if (point.getY() == 0 || !region.contains(points[point.getY() - 1][point.getX()])) {
                perimeter++;
            }
            if (point.getY() == plants.length - 1 || !region.contains(points[point.getY() + 1][point.getX()])) {
                perimeter++;
            }
            if (point.getX() == 0 || !region.contains(points[point.getY()][point.getX() - 1])) {
                perimeter++;
            }
            if (point.getX() == plants[0].length - 1 || !region.contains(points[point.getY()][point.getX() + 1])) {
                perimeter++;
            }
        }
        return perimeter;
    }

    private static Point[][] initPoints(char[][] plants) {
        Point[][] points = new Point[plants.length][plants[0].length];
        for (int i = 0; i < plants.length; i++) {
            for (int j = 0; j < plants[0].length; j++) {
                points[i][j] = new Point(j, i);
            }
        }
        return points;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }
}
