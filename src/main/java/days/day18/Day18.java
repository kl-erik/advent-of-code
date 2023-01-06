package days.day18;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day18 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        int[][] cubes = parse(file);
        int maxX = -1;
        int maxY = -1;
        int maxZ = -1;

        for (int[] cube : cubes) {
            maxX = Math.max(maxX, cube[0]);
            maxY = Math.max(maxY, cube[1]);
            maxZ = Math.max(maxZ, cube[2]);
        }

        boolean[][][] droplet = new boolean[maxX + 1][maxY + 1][maxZ + 1];
        int area = 0;
        for (int[] cube : cubes)
            area += 6 - covered(cube, droplet);
        return area;
    }

    private int covered(int[] cube, boolean[][][] droplet) {
        int covered = 0;
        int x = cube[0];
        int y = cube[1];
        int z = cube[2];
        droplet[x][y][z] = true;
        if (x + 1 < droplet.length)
            if (droplet[x + 1][y][z])
                covered++;
        if (x - 1 >= 0)
            if (droplet[x - 1][y][z])
                covered++;
        if (y + 1 < droplet[x].length)
            if (droplet[x][y + 1][z])
                covered++;
        if (y - 1 >= 0)
            if (droplet[x][y - 1][z])
                covered++;
        if (z + 1 < droplet[x][y].length)
            if (droplet[x][y][z + 1])
                covered++;
        if (z - 1 >= 0)
            if (droplet[x][y][z - 1])
                covered++;
        return covered * 2;
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

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }
}
