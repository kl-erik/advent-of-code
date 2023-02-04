package days.day22;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day22 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        Square square = Parser.getBoard(scanner);
        String[] path = Parser.getPath(scanner);
        return solve(square, path);
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        Cube cube = file.getName().contains("example")
                ? Parser.getExampleCube(scanner)
                : Parser.getInputCube(scanner);
        String[] path = Parser.getPath(scanner);
        return solve(cube, path);
    }

    private int solve(Board board, String[] path) {
        for (String instruction : path) {
            try {
                int steps = Integer.parseInt(instruction);
                board.move(steps);
            } catch (NumberFormatException ignore) {
                board.turn(instruction);
            }
        }

        return 1000 * board.getRow() + 4 * board.getColumn() + board.getDirection().getVal();
    }
}
