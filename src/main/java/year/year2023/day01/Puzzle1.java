package year.year2023.day01;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Puzzle1 {
    public static int solve(ArrayList<String> lines) {
        int sum = 0;

        for (String line : lines) {
            String firstDigit = getFirstDigit(line);
            String secondDigit = getLastDigit(line);
            sum += Integer.parseInt(firstDigit + secondDigit);
        }

        return sum;
    }

    private static String getFirstDigit(String line) {
        char[] chars = line.toCharArray();

        for (char aChar : chars) {
            if (Character.isDigit(aChar)) {
                return String.valueOf(aChar);
            }
        }

        throw new NoSuchElementException();
    }

    private static String getLastDigit(String line) {
        char[] chars = line.toCharArray();

        for (int i = chars.length - 1; i >= 0; i--) {
            if (Character.isDigit(chars[i])) {
                return String.valueOf(chars[i]);
            }
        }

        throw new NoSuchElementException();
    }
}
