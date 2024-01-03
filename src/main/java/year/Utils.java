package year;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public abstract class Utils {
    public static char[][] toChars(File file) throws FileNotFoundException {
        int rows = getLength(file);

        char[][] matrix = new char[rows][];
        try (Scanner scanner = new Scanner(file)) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i] = scanner.nextLine().toCharArray();
            }

            return matrix;
        }
    }

    private static int getLength(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)){
            int rows = 0;
            while (scanner.hasNext()) {
                rows++;
                scanner.nextLine();
            }
            return rows;
        }
    }

    public static String getValue(int row, int col, char[][] schematic) {
        StringBuilder value = new StringBuilder(String.valueOf(schematic[row][col++]));

        while (col < schematic.length && Character.isDigit(schematic[row][col])) {
            value.append(schematic[row][col]);
            col++;
        }

        return value.toString();
    }


    public static char[][] rotate90Degrees(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        char[][] rotatedState = new char[n][m];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                rotatedState[col][m-row-1] = matrix[row][col];
            }
        }

        return rotatedState;
    }

    public static int[] toInts(String[] split) {
        int[] ints = new int[split.length];
        for (int i = 0; i < ints.length; i++) {
            if (!split[i].isEmpty()) {
                ints[i] = Integer.parseInt(split[i]);
            }
        }
        return ints;
    }


    public static <T> Stack<T> clone(Stack<T> orig) {
        Stack<T> clone = new Stack<>();
        clone.addAll(orig);
        return clone;
    }

    public static boolean even(int v) {
        return v % 2 == 0;
    }

    public static void print(char[][] chars) {
        for (char[] row : chars) {
            for (char c : row) {
                System.out.print(c);
            }

            System.out.println();
        }

        System.out.println();
    }

    public static char[][] clone(char[][] chars) {
        char[][] clone = new char[chars.length][];

        for (int i = 0; i < chars.length; i++) {
            char[] row = new char[chars[i].length];
            System.arraycopy(chars[i], 0, row, 0, chars[i].length);
            clone[i] = row;
        }

        return clone;
    }

    public static int[][] toInts(File file) throws FileNotFoundException {
        char[][] chars = toChars(file);
        int[][] ints = new int[chars.length][];

        for (int i = 0; i < chars.length; i++) {
            int[] row = new int[chars[i].length];

            for (int j = 0; j < chars[i].length; j++) {
                row[j] = Integer.parseInt(String.valueOf(chars[i][j]));
            }

            ints[i] = row;
        }

        return ints;
    }
}
