package year.year2023.day03;

import year.Point;
import year.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Puzzle2 {
    public int solve(char[][] schematic) {
        HashMap<Point, ArrayList<Integer>> gears = new HashMap<>();

        for (int row = 0; row < schematic.length; row++) {
            for (int col = 0; col < schematic[row].length; col++) {
                if (Character.isDigit(schematic[row][col])) {
                    String value = Utils.getValue(row, col, schematic);
                    int end = col + value.length();
                    Optional<Point> optional = getAdjacentGear(row, col, end, schematic);

                    if (optional.isPresent()) {
                        Point gear = optional.get();
                        int part = Integer.parseInt(value);

                        if (gears.containsKey(gear)) {
                            ArrayList<Integer> parts = gears.get(gear);
                            parts.add(part);
                        } else {
                            ArrayList<Integer> parts = new ArrayList<>();
                            parts.add(part);
                            gears.put(gear, parts);
                        }
                    }

                    col = end;
                }
            }
        }

        int sum = 0;

        for (ArrayList<Integer> parts : gears.values()) {
            if (parts.size() == 2) {
                sum += parts.get(0) * parts.get(1);
            }
        }

        return sum;
    }

    private Optional<Point> getAdjacentGear(int row, int colBegin, int colEnd, char[][] schematic) {
        for (int col = colBegin; col < colEnd; col++) {
            Optional<Point> optional = getAdjacentGear(row, col, schematic);

            if (optional.isPresent()) {
                return optional;
            }
        }

        return Optional.empty();
    }

    private Optional<Point> getAdjacentGear(int row, int col, char[][] schematic) {
        if (col > 0) {
            if (schematic[row][col - 1] == '*') {
                return Optional.of(new Point(col - 1, row));
            }
        }

        if (col < schematic[row].length - 1) {
            if (schematic[row][col + 1] == '*') {
                return Optional.of(new Point(col + 1, row));
            }
        }

        if (row > 0) {
            if (schematic[row - 1][col] == '*') {
                return Optional.of(new Point(col, row - 1));
            }

            if (col > 0) {
                if (schematic[row - 1][col - 1] == '*') {
                    return Optional.of(new Point(col - 1, row - 1));
                }
            }

            if (col < schematic[row].length - 1) {
                if (schematic[row - 1][col + 1] == '*') {
                    return Optional.of(new Point(col + 1, row - 1));
                }
            }
        }

        if (row < schematic.length - 1) {
            if (schematic[row + 1][col] == '*') {
                return Optional.of(new Point(col, row + 1));
            }

            if (col > 0) {
                if (schematic[row + 1][col - 1] == '*') {
                    return Optional.of(new Point(col - 1, row + 1));
                }
            }

            if (col < schematic[row].length - 1) {
                if (schematic[row + 1][col + 1] == '*') {
                    return Optional.of(new Point(col + 1, row + 1));
                }
            }
        }

        return Optional.empty();
    }
}
