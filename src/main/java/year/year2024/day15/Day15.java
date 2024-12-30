package year.year2024.day15;

import year.Day;
import year.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day15 implements Day {
    static Point robot;

    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        return Puzzle1.solve(file);
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return Puzzle2.solve(file);
    }

    static Point getRobotPosition(char[][] warehouse) {
        for (int i = 0; i < warehouse.length; i++) {
            for (int j = 0; j < warehouse[i].length; j++) {
                if (warehouse[i][j] == '@') {
                    return new Point(j, i);
                }
            }
        }
        throw new IllegalArgumentException("Robot not found");
    }

    static class Input {
        char[][] warehouse;
        ArrayList<Character> directions;

        public Input(char[][] warehouse, ArrayList<Character> directions) {
            this.warehouse = warehouse;
            this.directions = directions;
        }
    }
}
