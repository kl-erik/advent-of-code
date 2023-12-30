package year.year2023.day14;

import year.Day;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day14 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        char[][] platform = Utils.toMatrix(file);
        tiltNorth(platform);
        return calcTotalLoad(platform);
    }

    private static void tiltNorth(char[][] platform) {
        for (int i = 0; i < platform.length; i++) {
            for (int j = 0; j < platform[i].length; j++) {
                if (platform[i][j] == 'O') {
                    platform[i][j] = '.';
                    int k = i;

                    while (k > 0 && platform[k-1][j] == '.') {
                        k--;
                    }

                    platform[k][j] = 'O';
                }
            }
        }
    }

    private int calcTotalLoad(char[][] platform) {
        int sum = 0;

        for (int i = 0; i < platform.length; i++) {
            for (int j = 0; j < platform[i].length; j++) {
                if (platform[i][j] == 'O') {
                    sum += platform.length - i;
                }
            }
        }

        return sum;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        Platform platform = new Platform(Utils.toMatrix(file));
        ArrayList<Platform> pattern = findPattern(platform);
        int trim = pattern.size();
        pattern.retainAll(findPattern(platform)); // run again with updated platform to remove initial states
        trim -= pattern.size();
        int cyclePattern = (1000000000 - trim) % pattern.size();
        return calcTotalLoad(pattern.get(cyclePattern == 0 ? pattern.size() - 1 : cyclePattern - 1).matrix);
    }

    private static ArrayList<Platform> findPattern(Platform platform) {
        ArrayList<Platform> pattern = new ArrayList<>();

        for (int cycle = 0; cycle < 1000000000; cycle++) {
            cycle(platform);
            Platform clone = new Platform(Utils.clone(platform.matrix));
            if (pattern.contains(clone)) {
                return pattern;
            } else {
                pattern.add(clone);
            }
        }

        return pattern;
    }

    private static void cycle(Platform platform) {
        for (int i = 0; i < 4; i++) {
            tiltNorth(platform.matrix);
            platform.matrix = Utils.rotate90Degrees(platform.matrix);
        }
    }

    private static class Platform {
        private char[][] matrix;

        public Platform(char[][] matrix) {
            this.matrix = matrix;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Platform platform = (Platform) o;
            return Arrays.deepEquals(matrix, platform.matrix);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(matrix);
        }
    }
}
