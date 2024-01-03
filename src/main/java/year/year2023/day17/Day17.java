package year.year2023.day17;

import year.Day;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day17 implements Day {
    int[][] map;
    int[][] solutions;
    boolean[][] visited;

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

        goRight(0, 1, 0, map[0][1], 0);
        goDown(1, 0, 0, map[1][0], 0);
        return solutions[n-1][m-1];
    }

    private void goRight(int i, int j, int steps, int sum, int fails) {
        if (sum > solutions[solutions.length-1][solutions[solutions.length-1].length-1]) {
            return;
        }

        if (solutions[i][j] > sum) {
            solutions[i][j] = sum;
            fails = 0;
        } else {
            fails++;
        }

        if (fails > 3) {
            return;
        }

        visited[i][j] = true;

        if (j < solutions.length - 1 && !visited[i][j+1] && steps < 2) {
            goRight(i, j+1, steps+1, sum + map[i][j+1], fails);
        }

        if (i < solutions.length - 1 && !visited[i+1][j]) {
            goDown(i+1, j, 0, sum + map[i+1][j], fails);
        }

        if (i > 0 && !visited[i-1][j]) {
            goUp(i-1, j, 0, sum + map[i-1][j], fails);
        }

        visited[i][j] = false;
    }

    private void goLeft(int i, int j, int steps, int sum, int fails) {
        if (sum > solutions[solutions.length-1][solutions[solutions.length-1].length-1]) {
            return;
        }

        if (solutions[i][j] > sum) {
            solutions[i][j] = sum;
            fails = 0;
        } else {
            fails++;
        }

        if (fails > 3) {
            return;
        }

        visited[i][j] = true;

        if (j > 0 && !visited[i][j-1] && steps < 2) {
            goLeft(i, j-1, steps+1, sum + map[i][j-1], fails);
        }

        if (i < solutions.length - 1 && !visited[i+1][j]) {
            goDown(i+1, j, 0, sum + map[i+1][j], fails);
        }

        if (i > 0 && !visited[i-1][j]) {
            goUp(i-1, j, 0, sum + map[i-1][j], fails);
        }

        visited[i][j] = false;
    }

    private void goDown(int i, int j, int steps, int sum, int fails) {
        if (sum > solutions[solutions.length-1][solutions[solutions.length-1].length-1]) {
            return;
        }

        if (solutions[i][j] > sum) {
            solutions[i][j] = sum;
            fails = 0;
        } else {
            fails++;
        }

        if (fails > 3) {
            return;
        }

        visited[i][j] = true;


        if (i < solutions.length - 1 && !visited[i+1][j] && steps < 2) {
            goDown(i+1, j, steps+1, sum + map[i+1][j], fails);
        }

        if (j < solutions.length - 1 && !visited[i][j+1]) {
            goRight(i, j+1, 0, sum + map[i][j+1], fails);
        }

        if (j > 0 && !visited[i][j-1]) {
            goLeft(i, j-1, 0, sum + map[i][j-1], fails);
        }

        visited[i][j] = false;
    }

    private void goUp(int i, int j, int steps, int sum, int fails) {
        if (sum > solutions[solutions.length-1][solutions[solutions.length-1].length-1]) {
            return;
        }

        if (solutions[i][j] > sum) {
            solutions[i][j] = sum;
            fails = 0;
        } else {
            fails++;
        }

        if (fails > 3) {
            return;
        }

        visited[i][j] = true;

        if (i > 0 && !visited[i-1][j] && steps < 2) {
            goUp(i-1, j, steps+1, sum + map[i-1][j], fails);
        }

        if (j < solutions.length - 1 && !visited[i][j+1]) {
            goRight(i, j+1, 0, sum + map[i][j+1], fails);
        }

        if (j > 0 && !visited[i][j-1]) {
            goLeft(i, j-1, 0, sum + map[i][j-1], fails);
        }

        visited[i][j] = false;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }
}
