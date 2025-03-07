package year.year2024.day08;

import year.Day;
import year.Point;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day8 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        char[][] map = Utils.toChars(file);

        HashMap<Character, ArrayList<Point>> antennaLocations = getAntennaLocations(map);
        for (char c : antennaLocations.keySet()) {
            ArrayList<Point> points = antennaLocations.get(c);

            for (int i = 0; i < points.size(); i++) {
                for (int j = i + 1; j < points.size(); j++) {
                    int ix = points.get(i).getX();
                    int iy = points.get(i).getY();
                    int jx = points.get(j).getX();
                    int jy = points.get(j).getY();
                    int dxi = ix - jx;
                    int dyi = iy - jy;
                    int dxj = jx - ix;
                    int dyj = jy - iy;
                    int pxi = ix + dxi;
                    int pyi = iy + dyi;
                    int pxj = jx + dxj;
                    int pyj = jy + dyj;

                    try {
                        if (map[pyi][pxi] != c) {
                            map[pyi][pxi] = '#';
                        }
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }

                    try {
                        if (map[pyj][pxj] != c) {
                            map[pyj][pxj] = '#';
                        }
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }
            }
        }

        int sum = 0;

        for (char[] row : map) {
            for (char c : row) {
                if (c == '#') {
                    sum++;
                }
            }
        }

        return sum;
    }

    private HashMap<Character, ArrayList<Point>> getAntennaLocations(char[][] map) {
        HashMap<Character, ArrayList<Point>> antennaLocations = new HashMap<>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                char c = map[i][j];

                if (c == '.') {
                    continue;
                }

                if (antennaLocations.containsKey(c)) {
                    Point point = new Point(j, i);
                    ArrayList<Point> points = antennaLocations.get(c);
                    if (!points.contains(point)) {
                        points.add(point);
                    }
                } else {
                    ArrayList<Point> points = new ArrayList<>();
                    points.add(new Point(j, i));
                    antennaLocations.put(c, points);
                }
            }
        }

        return antennaLocations;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        char[][] map = Utils.toChars(file);

        HashMap<Character, ArrayList<Point>> antennaLocations = getAntennaLocations(map);
        for (char c : antennaLocations.keySet()) {
            ArrayList<Point> points = antennaLocations.get(c);

            for (int i = 0; i < points.size(); i++) {
                for (int j = i + 1; j < points.size(); j++) {
                    int ix = points.get(i).getX();
                    int iy = points.get(i).getY();
                    int jx = points.get(j).getX();
                    int jy = points.get(j).getY();
                    int dxi = ix - jx;
                    int dyi = iy - jy;
                    int dxj = jx - ix;
                    int dyj = jy - iy;

                    drawLine(map, ix, iy, dxi, dyi);
                    drawLine(map, jx, jy, dxj, dyj);

                    map[points.get(i).getY()][points.get(i).getX()] = '#';
                    map[points.get(j).getY()][points.get(j).getX()] = '#';
                }
            }
        }

        int sum = 0;

        for (char[] row : map) {
            for (char c : row) {
                if (c == '#') {
                    sum++;
                }
            }
        }

        return sum;
    }

    private void drawLine(char[][] map, int ix, int iy, int dxi, int dyi) {
        while (true) {
            try {
                int pxi = ix + dxi;
                int pyi = iy + dyi;

                map[pyi][pxi] = '#';

                ix = pxi;
                iy = pyi;
            } catch (ArrayIndexOutOfBoundsException ignored) {
                break;
            }
        }
    }
}
