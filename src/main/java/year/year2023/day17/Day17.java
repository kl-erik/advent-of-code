package year.year2023.day17;

import year.Day;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day17 implements Day {
    protected static int[][] map;
    protected static int[][] solutions;
    protected static boolean[][] visited;

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        map = Utils.toInts(file);
        int n = map.length, m = map[0].length;
        solutions = new int[n][m];
        for (int[] row : solutions) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        solutions[0][0] = 0;
        visited = new boolean[n][m];

        Puzzle1.goRight(0, 1, 1, map[0][1], 0);
        Puzzle1.goDown(1, 0, 1, map[1][0], 0);
        return solutions[n-1][m-1];
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        map = Utils.toInts(file);
        int n = map.length, m = map[0].length;
        solutions = new int[n][m];
        for (int[] row : solutions) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        solutions[0][0] = 0;
        visited = new boolean[n][m];

        Puzzle2.goRight(0, 1, 1, map[0][1], 0);
        Puzzle2.goDown(1, 0, 1, map[1][0], 0);
        return solutions[n-1][m-1];
    }
}
