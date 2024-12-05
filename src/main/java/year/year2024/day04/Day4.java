package year.year2024.day04;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;

import static year.Utils.toChars;

public class Day4 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        char[][] wordSearch = toChars(file);
        return Puzzle1.solve(wordSearch);
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        char[][] wordSearch = toChars(file);
        return Puzzle2.solve(wordSearch);
    }
}
