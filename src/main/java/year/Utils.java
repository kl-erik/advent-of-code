package year;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
    public static int[][] rotateIntMatrix90Degrees(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] rotatedState = new int[n][m];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                rotatedState[col][m-row-1] = matrix[row][col];
            }
        }

        return rotatedState;
    }

    public static char[][] rotateCharMatrix90Degrees(char[][] matrix) {
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

    public static <T> T[][] rotateMatrix90Degrees(T[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        T[][] rotatedMatrix = (T[][]) Array.newInstance(matrix[0][0].getClass(), n, m);

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                rotatedMatrix[col][m - row - 1] = matrix[row][col];
            }
        }

        return rotatedMatrix;
    }

    public static void reverse(int[][] input) {
        int n = input.length;
        for (int i = 0; i < n / 2; i++) {
            int[] temp = input[i];
            input[i] = input[n - i - 1];
            input[n - i - 1] = temp;
        }
    }

    public static void reverse(char[] input) {
        int n = input.length;
        for (int i = 0; i < n / 2; i++) {
            char temp = input[i];
            input[i] = input[n - i - 1];
            input[n - i - 1] = temp;
        }
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

    public static <T> T[][] clone(T[][] array) {
        T[][] clone = (T[][]) Array.newInstance(array[0][0].getClass(), array.length, array[0].length);
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, clone[i], 0, array[i].length);
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

    public static ArrayList<String> getLines(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<String> lines = new ArrayList<>();

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        return lines;
    }
}
