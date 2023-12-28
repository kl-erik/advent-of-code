package year.year2022.day18;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day18 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        int[][] cubes = parse(file);
        boolean[][][] matrix = getMatrix(cubes);
        return getArea(matrix);
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        int[][] cubes = parse(file);
        boolean[][][] matrix = getMatrix(cubes);
        int area = getArea(matrix);
        inverse(matrix);
        removeEdges(matrix);
        return area - getArea(matrix);
    }

    private static boolean[][][] getMatrix(int[][] cubes) {
        int maxX = -1;
        int maxY = -1;
        int maxZ = -1;

        for (int[] cube : cubes) {
            maxX = Math.max(maxX, cube[0]);
            maxY = Math.max(maxY, cube[1]);
            maxZ = Math.max(maxZ, cube[2]);
        }

        boolean[][][] matrix = new boolean[maxX + 1][maxY + 1][maxZ + 1];
        for (int[] cube : cubes)
            matrix[cube[0]][cube[1]][cube[2]] = true;
        return matrix;
    }

    private int getArea(boolean[][][] matrix) {
        int area = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                for (int k = 0; k < matrix[i][j].length; k++) {
                    if (matrix[i][j][k]) {
                        if (i == matrix.length - 1 || !matrix[i + 1][j][k])
                            area++;
                        if (i == 0 || !matrix[i - 1][j][k])
                            area++;
                        if (j == matrix[i].length - 1 || !matrix[i][j + 1][k])
                            area++;
                        if (j == 0 || !matrix[i][j - 1][k])
                            area++;
                        if (k == matrix[i][j].length - 1 || !matrix[i][j][k + 1])
                            area++;
                        if (k == 0 || !matrix[i][j][k - 1])
                            area++;
                    }
                }
            }
        }

        return area;
    }

    private void inverse(boolean[][][] droplet) {
        for (int i = 0; i < droplet.length; i++)
            for (int j = 0; j < droplet[i].length; j++)
                for (int k = 0; k < droplet[i][j].length; k++)
                    droplet[i][j][k] = !droplet[i][j][k];
    }

    private void removeEdges(boolean[][][] inverse) {
        for (int i = 0; i < inverse.length; i++) {
            for (int j = 0; j < inverse[i].length; j++) {
                if (inverse[i][j][0])
                    removeEdges(inverse, i, j, 0);
                if (inverse[i][j][inverse[i][j].length - 1])
                    removeEdges(inverse, i, j, inverse[i][j].length - 1);
            }
        }

        for (int i = 0; i < inverse.length; i++) {
            for (int k = 0; k < inverse[i][0].length; k++) {
                if (inverse[i][0][k])
                    removeEdges(inverse, i, 0, k);
                if (inverse[i][inverse[i].length - 1][k])
                    removeEdges(inverse, i, inverse[i].length - 1, k);
            }
        }

        for (int j = 0; j < inverse[0].length; j++) {
            for (int k = 0; k < inverse[0][j].length; k++) {
                if (inverse[0][j][k])
                    removeEdges(inverse, 0, j, k);
                if (inverse[inverse.length - 1][j][k])
                    removeEdges(inverse, inverse.length - 1, j, k);
            }
        }
    }

    private void removeEdges(boolean[][][] inverse, int i, int j, int k) {
        if (inverse[i][j][k]) {
            inverse[i][j][k] = false;
            if (i > 0)
                removeEdges(inverse, i - 1, j, k);
            if (i < inverse.length - 1)
                removeEdges(inverse, i + 1, j, k);
            if (j > 0)
                removeEdges(inverse, i, j - 1, k);
            if (j < inverse[i].length - 1)
                removeEdges(inverse, i, j + 1, k);
            if (k > 0)
                removeEdges(inverse, i, j, k - 1);
            if (k < inverse[i][j].length - 1)
                removeEdges(inverse, i, j, k + 1);
        }
    }

    private int[][] parse(File file) {
        try (Scanner scanner = new Scanner(file)) {
            ArrayList<int[]> cubes = new ArrayList<>();

            while (scanner.hasNextLine()) {
                int[] cube = new int[3];
                String[] split = scanner.nextLine().split(",");
                for (int i = 0; i < split.length; i++)
                    cube[i] = Integer.parseInt(split[i]);
                cubes.add(cube);
            }

            return toArray(cubes);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static int[][] toArray(ArrayList<int[]> list) {
        int[][] array = new int[list.size()][];
        for (int i = 0; i < list.size(); i++)
            array[i] = list.get(i);
        return array;
    }
}
