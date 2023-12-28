package year.year2023.day01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Puzzle2 {
    private static final HashMap<String, String> digitStrings = new HashMap<String, String>() {{
        put("one", "1");
        put("two", "2");
        put("three", "3");
        put("four", "4");
        put("five", "5");
        put("six", "6");
        put("seven", "7");
        put("eight", "8");
        put("nine", "9");
    }};

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

        for (int i = 0; i < chars.length; i++) {
            String subLine = line.substring(0, i);

            for (String digitString : digitStrings.keySet()) {
                if (subLine.contains(digitString)) {
                    return digitStrings.get(digitString);
                }
            }

            if (Character.isDigit(chars[i])) {
                return String.valueOf(chars[i]);
            }
        }

        throw new NoSuchElementException();
    }

    private static String getLastDigit(String line) {
        char[] chars = line.toCharArray();

        for (int i = chars.length - 1; i >= 0; i--) {
            String subLine = line.substring(i);

            for (String digitString : digitStrings.keySet()) {
                if (subLine.contains(digitString)) {
                    return digitStrings.get(digitString);
                }
            }

            if (Character.isDigit(chars[i])) {
                return String.valueOf(chars[i]);
            }
        }

        throw new NoSuchElementException();
    }
}
