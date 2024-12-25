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
    Point[] directions = new Point[]{
            new Point(0, -1),
            new Point(1, 0),
            new Point(0, 1),
            new Point(-1, 0)
    };

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
        for (Point direction : directions) {
            Point neighbor = new Point(point.getX() + direction.getX(), point.getY() + direction.getY());
            if (neighbor.getX() >= 0 && neighbor.getX() < plants[0].length && neighbor.getY() >= 0 && neighbor.getY() < plants.length) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    private static int getPerimeter(Set<Point> region) {
        int perimeter = 0;
        for (Point point : region) {
            for (Point direction : new Point[]{new Point(0, -1), new Point(1, 0), new Point(0, 1), new Point(-1, 0)}) {
                Point neighbor = new Point(point.getX() + direction.getX(), point.getY() + direction.getY());
                if (!region.contains(neighbor)) {
                    perimeter++;
                }
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
            for (int i = 0; i < directions.length; i++) {
                Point direction1 = directions[i];
                Point direction2 = directions[(i + 1) % directions.length];
                Point neighbor1 = new Point(point.getX() + direction1.getX(), point.getY() + direction1.getY());
                Point neighbor2 = new Point(point.getX() + direction2.getX(), point.getY() + direction2.getY());
                if (!region.contains(neighbor1) && !region.contains(neighbor2)) {
                    sides++;
                } else {
                    Point diagonalNeighbour = new Point(point.getX() + direction1.getX() + direction2.getX(),
                            point.getY() + direction1.getY() + direction2.getY());
                    if (region.contains(neighbor1) && region.contains(neighbor2) && !region.contains(diagonalNeighbour)) {
                        sides++;
                    }
                }
            }
        }
        return sides;
    }
}
