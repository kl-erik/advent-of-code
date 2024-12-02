package year.year2023.day01;

import year.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static year.Utils.getLines;

public class Day1 implements Day {
    public Object puzzle1(File file) throws FileNotFoundException {
        ArrayList<String> lines = getLines(file);
        return Puzzle1.solve(lines);
    }

    public Object puzzle2(File file) throws FileNotFoundException {
        ArrayList<String> lines = getLines(file);
        return Puzzle2.solve(lines);
    }
}
