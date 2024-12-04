package year.year2024.day04;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static year.Utils.rotate90Degrees;
import static year.Utils.toChars;

public class Day4 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        char[][] wordSearch = toChars(file);

        ArrayList<ArrayList<String>> lineDirections = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            lineDirections.add(getHorizontalLines(wordSearch));
            lineDirections.add(getDiagonalLines(wordSearch));
            wordSearch = rotate90Degrees(wordSearch);
        }

        int sum = 0;

        Pattern pattern = Pattern.compile("XMAS");

        for (ArrayList<String> lines : lineDirections) {
            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    sum++;
                }
            }
        }

        return sum;
    }

    private ArrayList<String> getHorizontalLines(char[][] wordSearch) {
        ArrayList<String> horizontalLines = new ArrayList<>();
        for (char[] search : wordSearch) {
            StringBuilder line = new StringBuilder();
            for (char c : search) {
                line.append(c);
            }
            horizontalLines.add(line.toString());
        }
        return horizontalLines;
    }

    private ArrayList<String> getDiagonalLines(char[][] wordSearch) {
        ArrayList<String> diagonalLines = new ArrayList<>();
        for (int i = 0; i < wordSearch.length; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < wordSearch[i].length; j++) {
                if (i + j < wordSearch.length) {
                    line.append(wordSearch[i + j][j]);
                }
            }
            diagonalLines.add(line.toString());
        }
        for (int i = 1; i < wordSearch[0].length; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < wordSearch.length; j++) {
                if (i + j < wordSearch[0].length) {
                    line.append(wordSearch[j][i + j]);
                }
            }
            diagonalLines.add(line.toString());
        }
        return diagonalLines;
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }
}
