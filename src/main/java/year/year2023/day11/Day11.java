package year.year2023.day11;

import year.Day;
import year.Point;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Day11 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        return solve(file, 2);
    }

    public Object puzzle2Test(File file) throws FileNotFoundException {
        return solve(file, 100);
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return solve(file, 1000000);
    }

    private long solve(File file, int distanceFactor) throws FileNotFoundException {
        char[][] image = Utils.toChars(file);

        ArrayList<Integer> emptyRows = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            if (hasOnlyEmptySpace(image[i])) {
                emptyRows.add(i);
            }
        }

        ArrayList<Integer> emptyCols = new ArrayList<>();
        char[][] tmpImage = Utils.rotateCharMatrix90Degrees(image);
        for (int i = 0; i < tmpImage.length; i++) {
            if (hasOnlyEmptySpace(tmpImage[i])) {
                emptyCols.add(i);
            }
        }

        ArrayList<Point> galaxies = getGalaxies(image);

        long sum = 0;

        for (int i = 0; i < galaxies.size(); i++) {
            Point galaxy = galaxies.get(i);

            for (int j = i + 1; j < galaxies.size(); j++) {
                Point other = galaxies.get(j);

                int emptyRowsWithin = 0;
                for (int emptyRow : emptyRows) {
                    if (emptyRow > galaxy.getY() && emptyRow < other.getY()
                    || emptyRow < galaxy.getY() && emptyRow > other.getY()) {
                        emptyRowsWithin++;
                    }
                }

                int emptyColsWithin = 0;
                for (int emptyCol : emptyCols) {
                    if (emptyCol > galaxy.getX() && emptyCol < other.getX()
                    || emptyCol < galaxy.getX() && emptyCol > other.getX()) {
                        emptyColsWithin++;
                    }
                }

                int yDiff = Math.abs(galaxy.getY() - other.getY()) + emptyRowsWithin * distanceFactor - emptyRowsWithin;
                int xDiff = Math.abs(galaxy.getX() - other.getX()) + emptyColsWithin * distanceFactor - emptyColsWithin;
                sum += yDiff + xDiff;
            }
        }

        return sum;
    }

    private ArrayList<Point> getGalaxies(char[][] image) {
        ArrayList<Point> galaxies = new ArrayList<>();

        for (int y = 0; y < image.length; y++) {
            for (int x = 0; x < image[y].length; x++) {
                if (image[y][x] == '#') {
                    galaxies.add(new Point(x, y));
                }
            }
        }

        return galaxies;
    }

    private boolean hasOnlyEmptySpace(char[] row) {
        for (char c : row) {
            if (c != '.') {
                return false;
            }
        }

        return true;
    }
}
