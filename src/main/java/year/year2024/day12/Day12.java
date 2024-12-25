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
        HashMap<Character, Set<Set<Point>>> regionsMap = getRegionsMap(plants);
        int cost = 0;
        for (Set<Set<Point>> regions : regionsMap.values()) {
            for (Set<Point> region : regions) {
                cost += getPerimeter(region) * region.size();
            }
        }
        return cost;
    }

    private HashMap<Character, Set<Set<Point>>> getRegionsMap(char[][] plants) {
        HashMap<Character, Set<Set<Point>>> regionsMap = new HashMap<>();
        Set<Point> visited = new HashSet<>();
        for (int i = 0; i < plants.length; i++) {
            for (int j = 0; j < plants[i].length; j++) {
                Point point = new Point(j, i);
                if (visited.contains(point)) continue;
                Set<Point> region = getRegion(plants, point);
                char plant = plants[i][j];
                regionsMap.computeIfAbsent(plant, k -> new HashSet<>()).add(region);
                visited.addAll(region);
            }
        }

        return regionsMap;
    }

    private Set<Point> getRegion(char[][] plants, Point point) {
        Set<Point> region = new HashSet<>();
        Stack<Point> stack = new Stack<>();
        stack.push(point);
        while (!stack.isEmpty()) {
            Point current = stack.pop();
            if (region.contains(current)) continue;
            region.add(current);
            Set<Point> neighbors = getNeighbors(current, plants);
            for (Point neighbor : neighbors) {
                if (plants[neighbor.getY()][neighbor.getX()] == plants[current.getY()][current.getX()]) {
                    stack.push(neighbor);
                }
            }
        }
        return region;
    }

    private Set<Point> getNeighbors(Point point, char[][] plants) {
        Set<Point> neighbors = new HashSet<>();
        if (point.getY() > 0) {
            neighbors.add(new Point(point.getX(), point.getY() - 1));
        }
        if (point.getY() < plants.length - 1) {
            neighbors.add(new Point(point.getX(), point.getY() + 1));
        }
        if (point.getX() > 0) {
            neighbors.add(new Point(point.getX() - 1, point.getY()));
        }
        if (point.getX() < plants[point.getY()].length - 1) {
            neighbors.add(new Point(point.getX() + 1, point.getY()));
        }
        return neighbors;
    }

    private static int getPerimeter(Set<Point> region) {
        int perimeter = 0;
        for (Point point : region) {
            if (!region.contains(new Point(point.getX(), point.getY() - 1))) {
                perimeter++;
            }
            if (!region.contains(new Point(point.getX(), point.getY() + 1))) {
                perimeter++;
            }
            if (!region.contains(new Point(point.getX() - 1, point.getY()))) {
                perimeter++;
            }
            if (!region.contains(new Point(point.getX() + 1, point.getY()))) {
                perimeter++;
            }
        }
        return perimeter;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        char[][] plants = Utils.toChars(file);
        HashMap<Character, Set<Set<Point>>> regionsMap = getRegionsMap(plants);
        int cost = 0;
        for (Set<Set<Point>> regions : regionsMap.values()) {
            for (Set<Point> region : regions) {
                cost += getSides(region) * region.size();
            }
        }
        return cost;
    }

    private int getSides(Set<Point> region) {
        int sides = 0;
        for (Point point : region) {
            if (isInnerTopLeftCorner(point, region)) {
                sides++;
            }
            if (isInnerTopRightCorner(point, region)) {
                sides++;
            }
            if (isInnerBottomLeftCorner(point, region)) {
                sides++;
            }
            if (isInnerBottomRightCorner(point, region)) {
                sides++;
            }
            if (isOuterTopLeftCorner(point, region)) {
                sides++;
            }
            if (isOuterTopRightCorner(point, region)) {
                sides++;
            }
            if (isOuterBottomLeftCorner(point, region)) {
                sides++;
            }
            if (isOuterBottomRightCorner(point, region)) {
                sides++;
            }
        }
        return sides;
    }

    private boolean isInnerTopLeftCorner(Point point, Set<Point> region) {
        return !region.contains(new Point(point.getX() - 1, point.getY()))
                && !region.contains(new Point(point.getX(), point.getY() - 1));
    }

    private boolean isInnerTopRightCorner(Point point, Set<Point> region) {
        return !region.contains(new Point(point.getX() + 1, point.getY()))
                && !region.contains(new Point(point.getX(), point.getY() - 1));
    }

    private boolean isInnerBottomLeftCorner(Point point, Set<Point> region) {
        return !region.contains(new Point(point.getX() - 1, point.getY()))
                && !region.contains(new Point(point.getX(), point.getY() + 1));
    }

    private boolean isInnerBottomRightCorner(Point point, Set<Point> region) {
        return !region.contains(new Point(point.getX() + 1, point.getY()))
                && !region.contains(new Point(point.getX(), point.getY() + 1));
    }

    private boolean isOuterTopLeftCorner(Point point, Set<Point> region) {
        return !region.contains(new Point(point.getX() + 1, point.getY() + 1))
                && region.contains(new Point(point.getX() + 1, point.getY()))
                && region.contains(new Point(point.getX(), point.getY() + 1));
    }

    private boolean isOuterTopRightCorner(Point point, Set<Point> region) {
        return !region.contains(new Point(point.getX() - 1, point.getY() + 1))
                && region.contains(new Point(point.getX() - 1, point.getY()))
                && region.contains(new Point(point.getX(), point.getY() + 1));
    }

    private boolean isOuterBottomLeftCorner(Point point, Set<Point> region) {
        return !region.contains(new Point(point.getX() + 1, point.getY() - 1))
                && region.contains(new Point(point.getX() + 1, point.getY()))
                && region.contains(new Point(point.getX(), point.getY() - 1));
    }

    private boolean isOuterBottomRightCorner(Point point, Set<Point> region) {
        return !region.contains(new Point(point.getX() - 1, point.getY() - 1))
                && region.contains(new Point(point.getX() - 1, point.getY()))
                && region.contains(new Point(point.getX(), point.getY() - 1));
    }
}
