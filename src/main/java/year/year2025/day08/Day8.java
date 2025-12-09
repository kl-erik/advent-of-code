package year.year2025.day08;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Day8 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        List<Point> points = parse(file);
        PriorityQueue<Pair> pairsSortedByShortestDistance = new PriorityQueue<>(Comparator.comparingDouble(a -> a.distance));
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            for (int j = i + 1; j < points.size(); j++) {
                Point p2 = points.get(j);
                Pair pair = new Pair(p1, p2, p1.distanceTo(p2));
                pairsSortedByShortestDistance.add(pair);
            }
        }
        List<Set<Point>> circuits = new ArrayList<>();
        for (Point point : points) {
            Set<Point> circuit = new HashSet<>();
            circuit.add(point);
            circuits.add(circuit);
        }
        int i = 0;
        while (!pairsSortedByShortestDistance.isEmpty() && i++ < 1000) {
            Pair pair = pairsSortedByShortestDistance.poll();
            Set<Point> p1Circuit = findCircuit(circuits, pair.p1);
            Set<Point> p2Circuit = findCircuit(circuits, pair.p2);
            if (p1Circuit == p2Circuit) {
                continue;
            }
            p1Circuit.addAll(p2Circuit);
            circuits.remove(p2Circuit);
        }
        List<Integer> sizes = circuits.stream().map(Set::size).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        return sizes.get(0) * sizes.get(1) * sizes.get(2);
    }

    private static Set<Point> findCircuit(List<Set<Point>> circuits, Point pair) {
        for (Set<Point> circuit : circuits) {
            if (circuit.contains(pair)) {
                return circuit;
            }
        }
        throw new IllegalArgumentException("Point not found in any circuit");
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        List<Point> points = parse(file);
        PriorityQueue<Pair> pairsSortedByShortestDistance = new PriorityQueue<>(Comparator.comparingDouble(a -> a.distance));
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            for (int j = i + 1; j < points.size(); j++) {
                Point p2 = points.get(j);
                Pair pair = new Pair(p1, p2, p1.distanceTo(p2));
                pairsSortedByShortestDistance.add(pair);
            }
        }
        List<Set<Point>> circuits = new ArrayList<>();
        for (Point point : points) {
            Set<Point> circuit = new HashSet<>();
            circuit.add(point);
            circuits.add(circuit);
        }
        while (!pairsSortedByShortestDistance.isEmpty()) {
            Pair pair = pairsSortedByShortestDistance.poll();
            Set<Point> p1Circuit = findCircuit(circuits, pair.p1);
            Set<Point> p2Circuit = findCircuit(circuits, pair.p2);
            if (p1Circuit == p2Circuit) {
                continue;
            }
            p1Circuit.addAll(p2Circuit);
            circuits.remove(p2Circuit);
            if (circuits.size() == 1) {
                return pair.p1.x * pair.p2.x;
            }
        }
        throw new IllegalStateException("All points are not connected");
    }

    private List<Point> parse(File file) throws FileNotFoundException {
        List<Point> points = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String[] coordinates = sc.nextLine().split(",");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            int z = Integer.parseInt(coordinates[2]);
            points.add(new Point(x, y, z));
        }
        return points;
    }

    private static class Point {
        private final int x;
        private final int y;
        private final int z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public double distanceTo(Point other) {
            return Math.sqrt(Math.pow(x - other.x, 2) +
                             Math.pow(y - other.y, 2) +
                             Math.pow(z - other.z, 2));
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y && z == point.z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ", " + z + ')';
        }
    }

    private static class Pair {
        private final Point p1;
        private final Point p2;
        private final double distance;

        public Pair(Point p1, Point p2, double distance) {
            this.p1 = p1;
            this.p2 = p2;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair)) return false;
            Pair pair = (Pair) o;
            return Objects.equals(p1, pair.p1) && Objects.equals(p2, pair.p2)
                    || Objects.equals(p1, pair.p2) && Objects.equals(p2, pair.p1);
        }

        @Override
        public int hashCode() {
            return Objects.hash(p1, p2);
        }

        @Override
        public String toString() {
            return "(" + p1 + ", " + p2 + ") : " + distance;
        }
    }
}
