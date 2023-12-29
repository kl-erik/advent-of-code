package year.year2023.day13;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Day13 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        ArrayList<char[][]> patterns = parse(file);
        int sum = 0;

        for (char[][] pattern : patterns) {
            try {
                sum += findHorizontalMirror(pattern);
            } catch (NoSuchElementException ignore) {
                sum += findVerticalMirror(pattern);
            }
        }

        return sum;
    }

    private int findVerticalMirror(char[][] pattern) {
        for (int i = 1; i < pattern[0].length; i++) {
            if (verticalMirror(i, i - 1, pattern)) {
                return i;
            }
        }

        throw new NoSuchElementException("No vertical line");
    }

    private int findHorizontalMirror(char[][] pattern) {
        for (int i = 1; i < pattern.length; i++) {
            if (horizontalMirror(i, i - 1, pattern)) {
                return i * 100;
            }
        }

        throw new NoSuchElementException("No horizontal line");
    }

    private boolean verticalMirror(int i, int j, char[][] pattern) {
        char[] columnI = new char[pattern.length];
        char[] columnJ = new char[pattern.length];

        while (i < pattern[0].length && j >= 0) {
            for (int k = 0; k < pattern.length; k++) {
                columnI[k] = pattern[k][i];
                columnJ[k] = pattern[k][j];
            }

            if (!equal(columnI, columnJ)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    private boolean horizontalMirror(int i, int j, char[][] pattern) {
        while (i < pattern.length && j >= 0) {
            if (!equal(pattern[i], pattern[j])) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    private boolean equal(char[] chars, char[] chars1) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != chars1[i]) {
                return false;
            }
        }

        return true;
    }

    private ArrayList<char[][]> parse(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            ArrayList<char[][]> patterns = new ArrayList<>();

            while (scanner.hasNextLine()) {
                ArrayList<char[]> list = new ArrayList<>();

                String line;
                while (scanner.hasNext() && !(line = scanner.nextLine()).isEmpty()) {
                    list.add(line.toCharArray());
                }

                char[][] pattern = new char[list.size()][];
                for (int i = 0; i < pattern.length; i++) {
                    pattern[i] = list.get(i);
                }

                patterns.add(pattern);
            }

            return patterns;
        }
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        ArrayList<char[][]> patterns = parse(file);
        int sum = 0;

        for (char[][] pattern : patterns) {
            try {
                sum += findVerticalErrorLine(pattern);
            } catch (NoSuchElementException ignore) {
                sum += findHorizontalErrorLine(pattern);
            }
        }

        return sum;
    }

    private int findVerticalErrorLine(char[][] pattern) {
        for (int i = 0; i < pattern[0].length; i++) {
            if (findVerticalError(i, i - 1, pattern)) {
                return i;
            }
        }

        throw new NoSuchElementException("Found no errors");
    }

    private int findHorizontalErrorLine(char[][] pattern) {
        for (int i = 1; i < pattern.length; i++) {
            if (findHorizontalError(i, i - 1, pattern)) {
                return i * 100;
            }
        }

        throw new NoSuchElementException("Found no errors");
    }

    private boolean findVerticalError(int i, int j, char[][] pattern) {
        int errors = 0;

        char[] columnI = new char[pattern.length];
        char[] columnJ = new char[pattern.length];

        while (i < pattern[0].length && j >= 0) {
            for (int k = 0; k < pattern.length; k++) {
                columnI[k] = pattern[k][i];
                columnJ[k] = pattern[k][j];
            }

            if (!equal(columnI, columnJ)) {
                errors += numberOfErrors(columnI, columnJ);

                if (errors > 1) {
                    return false;
                }
            }

            i++;
            j--;
        }

        return errors == 1;
    }

    private boolean findHorizontalError(int i, int j, char[][] pattern) {
        int errors = 0;

        while (i < pattern.length && j >= 0) {
            if (!equal(pattern[i], pattern[j])) {
                errors += numberOfErrors(pattern[i], pattern[j]);

                if (errors > 1) {
                    return false;
                }
            }

            i++;
            j--;
        }

        return errors == 1;
    }

    private int numberOfErrors(char[] chars, char[] chars1) {
        int errors = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != chars1[i]) {
                errors++;
            }
        }

        return errors;
    }
}
