package year.year2024.day06;

import year.Day;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;

public class Day6 implements Day {
    private char[][] map;

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        map = Utils.toChars(file);
        markMap();
        return countMarked();
    }

    private void markMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                char c = map[i][j];

                if (c == '^' || c == '>' || c == 'v' || c == '<') {
                    markMap(i, j, c);
                }
            }
        }
    }

    private void markMap(int i, int j, char c) {
        try {
            while (true) {
                switch (c) {
                    case '^':
                        while (i >= 0) {
                            map[i][j] = 'X';
                            if (map[i - 1][j] == '#') {
                                c = '>';
                                break;
                            }
                            i--;
                        }
                        break;
                    case '>':
                        while (j < map[i].length) {
                            map[i][j] = 'X';
                            if (map[i][j + 1] == '#') {
                                c = 'v';
                                break;
                            }
                            j++;
                        }
                        break;
                    case 'v':
                        while (i < map.length) {
                            map[i][j] = 'X';
                            if (map[i + 1][j] == '#') {
                                c = '<';
                                break;
                            }
                            i++;
                        }
                        break;
                    case '<':
                        while (j >= 0) {
                            map[i][j] = 'X';
                            if (map[i][j - 1] == '#') {
                                c = '^';
                                break;
                            }
                            j--;
                        }
                        break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // done
        }
    }

    private int countMarked() {
        int sum = 0;

        for (char[] row : map) {
            for (char c : row) {
                if (c == 'X') {
                    sum++;
                }
            }
        }

        return sum;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }
}