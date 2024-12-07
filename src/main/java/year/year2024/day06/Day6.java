package year.year2024.day06;

import year.Day;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;

public class Day6 implements Day {

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        char[][] map = Utils.toChars(file);
        return Puzzle1.solve(map);
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        char[][] map = Utils.toChars(file);
        return Puzzle2.solve(map);
    }
}