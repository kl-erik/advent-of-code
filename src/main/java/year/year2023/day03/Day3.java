package year.year2023.day03;

import year.Day;
import year.Utils;

import java.io.File;
import java.io.FileNotFoundException;

public class Day3 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        char[][] schematic = Utils.toMatrix(file);
        return new Puzzle1().solve(schematic);
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        char[][] schematic = Utils.toMatrix(file);
        return new Puzzle2().solve(schematic);
    }
}
