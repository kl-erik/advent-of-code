package year.year2025.day04;

import year.Day;
import year.Point;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class Day4 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        char[][] diagram = Utils.toChars(file);
        int[][] neighbours = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1},  {1, 0},  {1, 1}};
        int accessible = 0;

        for (int i = 0; i < diagram.length; i++) {
            for (int j = 0; j < diagram[i].length; j++) {
                if (diagram[i][j] != '@') {
                    continue;
                }

                int count = 0;
                for (int[] neighbour : neighbours) {
                    int newRow = i + neighbour[0];
                    int newCol = j + neighbour[1];

                    if (newRow >= 0 && newRow < diagram.length && newCol >= 0 && newCol < diagram[i].length) {
                        if (diagram[newRow][newCol] == '@') {
                            count++;
                        }
                    }
                }
                if (count < 4) {
                    accessible++;
                }
            }
        }

        return accessible;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        char[][] diagram = Utils.toChars(file);
        int[][] neighbours = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1},  {1, 0},  {1, 1}};

        int accessible = 0;
        Set<Point> toRemove = new HashSet<>();

        do {
            toRemove.clear();

            for (int i = 0; i < diagram.length; i++) {
                for (int j = 0; j < diagram[i].length; j++) {
                    if (diagram[i][j] != '@') {
                        continue;
                    }

                    int count = 0;
                    for (int[] neighbour : neighbours) {
                        int newRow = i + neighbour[0];
                        int newCol = j + neighbour[1];

                        if (newRow >= 0 && newRow < diagram.length && newCol >= 0 && newCol < diagram[i].length) {
                            if (diagram[newRow][newCol] == '@') {
                                count++;
                            }
                        }
                    }
                    if (count < 4) {
                        accessible++;
                        toRemove.add(new Point(j, i));
                    }
                }
            }

            for (Point point : toRemove) {
                diagram[point.y][point.x] = 'x';
            }
        } while (!toRemove.isEmpty());

        return accessible;
    }
}
