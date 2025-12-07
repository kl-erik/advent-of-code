package year.year2025.day07;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;

import static year.Utils.toChars;

public class Day7 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        char[][] matrix = toChars(file);
        int splits = 0;
        for (int row = 1; row < matrix.length - 1; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row-1][col] == 'S') {
                    matrix[row][col] = '|';
                } else if (matrix[row-1][col] == '|' && matrix[row][col] == '.') {
                    matrix[row][col] = '|';
                } else if (matrix[row-1][col] == '|' && matrix[row][col] == '^') {
                    matrix[row][col-1] = '|';
                    matrix[row][col+1] = '|';
                    splits++;
                }
            }
        }
        return splits;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        // TODO: Implement puzzle2
        return null;
    }
}
