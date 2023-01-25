package days.day22;

import days.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day22 implements Day {
    @Override
    public Object puzzle1(File file) throws FileNotFoundException {
        Parser parser = new Parser();
        Scanner scanner = new Scanner(file);
        Board board = parser.getBoard(scanner);
        String[] path = parser.getPath(scanner);
        Player player = new Player(board.getFirstX(0), 0);
        return solve(board, path, player);
    }

    private int solve(Board board, String[] path, Player player) {
        for (String instruction : path) {
            try {
                int steps = Integer.parseInt(instruction);
                update(board, player, steps);
            } catch (NumberFormatException ignore) {
                turn(player, instruction);
            }
        }

        return 1000 * (player.getY() + 1) + 4 * (player.getX() + 1) + player.getDirection().getVal();
    }

    private void update(Board board, Player player, int steps) {
        for (int step = 0; step < steps; step++) {
            int oldX = player.getX();
            int oldY = player.getY();

            player.move();

            if (player.getY() < 0 || player.getY() >= board.getBoard().length
                    || player.getX() < 0 || player.getX() >= board.getBoard()[player.getY()].length
                    || board.getBoard()[player.getY()][player.getX()] == ' ') {
                switch (player.getDirection()) {
                    case RIGHT:
                        player.setX(board.getFirstX(player.getY()));
                        break;
                    case DOWN:
                        player.setY(board.getFirstY(player.getX()));
                        break;
                    case LEFT:
                        player.setX(board.getLastX(player.getY()));
                        break;
                    case UP:
                        player.setY(board.getLastY(player.getX()));
                        break;
                }
            }

            if (board.getBoard()[player.getY()][player.getX()] == '#') {
                player.setX(oldX);
                player.setY(oldY);
                return;
            }
        }
    }

    private void turn(Player player, String instruction) {
        switch (instruction) {
            case "R":
                player.turnRight();
                break;
            case "L": player.turnLeft();
        }
    }

    @Override
    public Object puzzle2(File file) throws FileNotFoundException {
        return null;
    }
}
