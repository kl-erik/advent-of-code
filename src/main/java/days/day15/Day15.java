package days.day15;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day15 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        HashMap<Point, Integer> sensorRanges = new HashMap<>();
        HashMap<Integer, Integer> xDistances = new HashMap<>();
        ArrayList<Integer> beaconXs = new ArrayList<>();
        int row = file.getName().contains("input") ? 4000000 : 10;

        while (scanner.hasNextLine()) {
            String[] split = scanner.nextLine().split("( at |: )");
            String sensorString = split[1];
            String beaconString = split[3];
            String[] sensorPoint = sensorString.split("(=|, )");
            String[] beaconPoint = beaconString.split("(=|, )");
            int xSensor = Integer.parseInt(sensorPoint[1]);
            int ySensor = Integer.parseInt(sensorPoint[3]);
            int xBeacon = Integer.parseInt(beaconPoint[1]);
            int yBeacon = Integer.parseInt(beaconPoint[3]);
            int distance = Math.abs(xBeacon - xSensor) + Math.abs(yBeacon - ySensor);
            if (ySensor - distance <= row && ySensor + distance >= row) {
                distance = distance - Math.abs(ySensor - row);
                if (!xDistances.containsKey(xSensor)) {
                    xDistances.put(xSensor, distance);
                } else if (xDistances.get(xSensor) < distance) {
                    xDistances.put(xSensor, distance);
                }
                if (yBeacon == row) beaconXs.add(xBeacon);
                sensorRanges.put(new Point(xSensor, ySensor), distance);
            }
        }

        int xOffsetMax = 0;
        int yOffsetMax = 0;
        int xOffsetMin = 0;
        int yOffsetMin = 0;

        for (Point sensor : sensorRanges.keySet()) {
            int distance = sensorRanges.get(sensor);
            xOffsetMax = Math.max(Math.abs(sensor.x) + distance, xOffsetMax);
            yOffsetMax = Math.max(Math.abs(sensor.y) + distance, yOffsetMax);
            xOffsetMin = Math.min(sensor.x - distance, xOffsetMin);
            yOffsetMin = Math.min(sensor.y - distance, yOffsetMin);
        }

        int xOffset = Math.abs(xOffsetMin);

        boolean[] line = new boolean[xOffsetMax + xOffset + 1];

        System.out.println();

        for (int i = 0; i < line.length - xOffset; i++) {
            if (xDistances.containsKey(i)) {
                int distance = xDistances.get(i);

                for (int j = i - distance; j <= i + distance; j++) {
                    line[j + xOffset] = true;
                }
            }
        }

        for (int x : beaconXs) {
            line[x + xOffset] = false;
        }

        int sum = 0;

        for (boolean point : line) {
            if (point) sum++;
        }

        return sum;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        HashMap<Point, Point> sensorBeacons = new HashMap<>();
        Scanner scanner = new Scanner(file);
        int rows = file.getName().contains("input") ? 4000000 : 20;

        while (scanner.hasNextLine()) {
            String[] split = scanner.nextLine().split("( at |: )");
            String sensorString = split[1];
            String beaconString = split[3];
            String[] sensorPoint = sensorString.split("(=|, )");
            String[] beaconPoint = beaconString.split("(=|, )");
            int xSensor = Integer.parseInt(sensorPoint[1]);
            int ySensor = Integer.parseInt(sensorPoint[3]);
            int xBeacon = Integer.parseInt(beaconPoint[1]);
            int yBeacon = Integer.parseInt(beaconPoint[3]);
            sensorBeacons.put(new Point(xSensor, ySensor), new Point(xBeacon, yBeacon));
        }

        for (int row = 0; row <= rows; row++) {
            Set<int[]> intervals = new HashSet<>();

            for (Point sensor : sensorBeacons.keySet()) {
                Point beacon = sensorBeacons.get(sensor);
                int distance = Math.abs(beacon.x - sensor.x) + Math.abs(beacon.y - sensor.y);
                if (sensor.y - distance <= row && sensor.y + distance >= row) {
                    distance = distance - Math.abs(sensor.y - row);
                    int[] interval = {Math.max(sensor.x - distance,0), Math.min(sensor.x + distance, rows)};
                    intervals.add(interval);
                    // TODO intervals.insert method which reorganizes the intervals
                }
            }

            for (int i = 0; i <= rows; i++) {
                int tmp = i;

                for (int[] interval : intervals) {
                    if (interval[0] <= i && interval[1] >= i) {
                        i = interval[1];
                        break;
                    }
                }

                if (i != rows && i == tmp) {
                    return (long) i * 4000000 + row;
                }
            }
        }

        return null;
    }

    private class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
