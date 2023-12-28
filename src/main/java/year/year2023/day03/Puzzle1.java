package year.year2023.day03;

import year.Utils;

public class Puzzle1 {
    public int solve(char[][] schematic) {
        int sum = 0;

        for (int row = 0; row < schematic.length; row++) {
            for (int col = 0; col < schematic[row].length; col++) {
                if (Character.isDigit(schematic[row][col])) {
                    String value = Utils.getValue(row, col, schematic);
                    int end = col + value.length();

                    if (hasAdjacentSymbol(row, col, end, schematic)) {
                        sum += Integer.parseInt(value);
                    }

                    col = end;
                }
            }
        }

        return sum;
    }

    private boolean hasAdjacentSymbol(int row, int colBegin, int colEnd, char[][] schematic) {
        for (int col = colBegin; col < colEnd; col++) {
            if (hasAdjacentSymbol(row, col, schematic)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasAdjacentSymbol(int row, int col, char[][] schematic) {
        if (col > 0) {
            if (isSymbol(schematic[row][col - 1])) {
                return true;
            }
        }

        if (col < schematic[row].length - 1) {
            if (isSymbol(schematic[row][col + 1])) {
                return true;
            }
        }

        if (row > 0) {
            if (isSymbol(schematic[row - 1][col])) {
                return true;
            }

            if (col > 0) {
                if (isSymbol(schematic[row - 1][col - 1])) {
                    return true;
                }
            }

            if (col < schematic[row].length - 1) {
                if (isSymbol(schematic[row - 1][col + 1])) {
                    return true;
                }
            }
        }

        if (row < schematic.length - 1) {
            if (isSymbol(schematic[row + 1][col])) {
                return true;
            }

            if (col > 0) {
                if (isSymbol(schematic[row + 1][col - 1])) {
                    return true;
                }
            }

            if (col < schematic[row].length - 1) {
                return isSymbol(schematic[row + 1][col + 1]);
            }
        }

        return false;
    }

    private boolean isSymbol(char c) {
        return !(Character.isDigit(c) || c == '.');
    }

}
